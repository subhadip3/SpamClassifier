import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;


public class WordCount extends Configured implements Tool{
      public int run(String[] args) throws Exception
      {
            //creating a JobConf object and assigning a job name for identification purposes
            JobConf conf = new JobConf(getConf(), WordCount.class);
            conf.setJobName("WordCount");

            //Setting configuration object with the Data Type of output Key and Value
            conf.setOutputKeyClass(Text.class);
            conf.setOutputValueClass(IntWritable.class);

            //Providing the mapper and reducer class names
            conf.setMapperClass(WordCountMapper.class);
            conf.setReducerClass(WordCountReducer.class);
            //We wil give 2 arguments at the run time, one in input path and other is output path
            Path inp = new Path(args[0]);
            Path out = new Path(args[1]);
            //the hdfs input and output directory to be fetched from the command line
            FileInputFormat.addInputPath(conf, inp);
            FileOutputFormat.setOutputPath(conf, out);

            
            JobClient.runJob(conf);
            
            
            
            JobConf conf1 = new JobConf(getConf(), WordCount.class);
            conf1.setJobName("Exp");

             //conf1.addDependingJob(conf);
            //Setting configuration object with the Data Type of output Key and Value
            conf1.setOutputKeyClass(Text.class);
            conf1.setOutputValueClass(IntWritable.class);

            //Providing the mapper and reducer class names
            conf1.setMapperClass(Exp1Mapper.class);
            conf1.setReducerClass(Exp1Reducer.class);
            //We wil give 2 arguments at the run time, one in input path and other is output path
            Path inp1 = new Path(args[1]);
            Path out1 = new Path(args[2]);
            //the hdfs input and output directory to be fetched from the command line
            FileInputFormat.addInputPath(conf1, inp1);
            FileOutputFormat.setOutputPath(conf1,out1);

            //JobClient.runJob(conf);
            //return conf1.waitForCompletion(true) ? 0 : 1;
            JobClient.runJob(conf1);
            
            
            
            
            JobConf conf2 = new JobConf(getConf(), WordCount.class);
            conf2.setJobName("Word1");

            //Setting configuration object with the Data Type of output Key and Value
            conf2.setOutputKeyClass(Text.class);
            conf2.setOutputValueClass(IntWritable.class);

            //Providing the mapper and reducer class names
            conf2.setMapperClass(Word1Mapper.class);
            conf2.setReducerClass(Word1Reducer.class);
            //We wil give 2 arguments at the run time, one in input path and other is output path
            Path inp2 = new Path(args[1]);
            Path out2 = new Path(args[3]);
            //the hdfs input and output directory to be fetched from the command line
            FileInputFormat.addInputPath(conf2, inp2);
            FileOutputFormat.setOutputPath(conf2, out2);

            
            JobClient.runJob(conf2);
            
            
            JobConf conf3 = new JobConf(getConf(), WordCount.class);
            conf3.setJobName("FinalTrainer");

            //Setting configuration object with the Data Type of output Key and Value
            conf3.setOutputKeyClass(Text.class);
            conf3.setOutputValueClass(FloatWritable.class);

            //Providing the mapper and reducer class names
            conf3.setMapperClass(FinalTrailerMapper.class);
            conf3.setReducerClass(FinalTrailerReducer.class);
            //We wil give 2 arguments at the run time, one in input path and other is output path
            Path inp31 = new Path(args[2]);
            Path inp32 = new Path(args[3]);
            Path out3 = new Path(args[4]);
            //the hdfs input and output directory to be fetched from the command line
            FileInputFormat.addInputPath(conf3, inp31);
            FileInputFormat.addInputPath(conf3, inp32);
            FileOutputFormat.setOutputPath(conf3, out3);

            
            JobClient.runJob(conf3);
            
            
            return 0;
      }
     
      public static void main(String[] args) throws Exception
      {
            // this main function will call run method defined above.
        int res = ToolRunner.run(new Configuration(), new WordCount(),args);
		System.exit(res);
	}
}