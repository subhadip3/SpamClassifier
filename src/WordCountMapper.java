import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

import com.sun.tools.javac.util.Context;

public class WordCountMapper extends MapReduceBase implements
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
		FileSplit fileSplit = (FileSplit)reporter.getInputSplit();
	      String filename = fileSplit.getPath().getName();
	      System.out.println("********Filename:********"+filename);
		// taking one line at a time from input file and tokenizing the same
		String line = value.toString();
		StringTokenizer tokenizer = new StringTokenizer(line);
		//String fileName = ((FileSplit) ((Reporter) context).getInputSplit()).getPath().getName();
		
		// iterating through all the words available in that line and forming
		// the key value pair
		while (tokenizer.hasMoreTokens()) {
			word.set(tokenizer.nextToken());
			// sending to output collector which inturn passes the same to
			// reducer
			Text w=new Text(filename+"\t" +word);
			output.collect(w, one);
		}
		
	}
}