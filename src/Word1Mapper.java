import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class Word1Mapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, IntWritable> {
	// hadoop supported data types
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	
	public void map(LongWritable key, Text value,
			OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		String[] rows = value.toString().split("\t");
		
		int i = Integer.parseInt(rows[2]);
		// IntWritable x=new IntWritable(Integer.parseInt(rows[2]));
		System.out.println("***" + rows[0] + "***");
		
		Text w=new Text(rows[1]);
		
		
		
		output.collect(w,one);
		
	}
}