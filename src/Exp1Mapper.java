import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

import com.sun.tools.javac.util.Context;

public class Exp1Mapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, IntWritable> {
	// hadoop supported data types
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	// map method that performs the tokenizer job and framing the initial key
	// value pairs
	// after all lines are converted into key-value pairs, reducer is called.
	public void map(LongWritable key, Text value,
			OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		FileSplit fileSplit = (FileSplit) reporter.getInputSplit();
		String filename = fileSplit.getPath().getName();
		System.out.println("********Filename:********" + filename);
		String str;
		// taking one line at a time from input file and tokenizing the same
		// String line = value.toString();
		String[] rows = value.toString().split("\t");
		Text word = new Text(rows[1]);
		int i = Integer.parseInt(rows[2]);
		// IntWritable x=new IntWritable(Integer.parseInt(rows[2]));
		System.out.println("***" + rows[0] + "***");
		str=rows[0].substring(0,1);
		if (str.equals("h")) {
			i = (-1) * i;
			System.out.println("negative" + i);
		}
		IntWritable x = new IntWritable(i);

		output.collect(word, x);
		// }

	}

}