import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class FinalTrailerMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, FloatWritable> {
	// hadoop supported data types
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	// map method that performs the tokenizer job and framing the initial key
	// value pairs
	// after all lines are converted into key-value pairs, reducer is called.
	public void map(LongWritable key, Text value, OutputCollector<Text, FloatWritable> output, Reporter reporter) throws IOException
	{
		FileSplit fileSplit = (FileSplit) reporter.getInputSplit();
		String filename = fileSplit.getPath().toString();
		System.out.println("********Filename:********" + filename);
		String str;
		// taking one line at a time from input file and tokenizing the same
		// String line = value.toString();
		String[] rows = value.toString().split("\t");
		Text word = new Text(rows[0]);
		float i = Float.parseFloat(rows[1]);
		if(filename.equals("hdfs://localhost:9000/user/jetfire/output3/part-00000"))
		i=(-1)*i;	
		// IntWritable x=new IntWritable(Integer.parseInt(rows[2]));

		output.collect(word, new FloatWritable(i));
		// }
	}
}