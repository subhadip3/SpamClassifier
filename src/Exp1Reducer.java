import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class Exp1Reducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, FloatWritable>
{
      //reduce method accepts the Key Value pairs from mappers do the aggregation based on keys and produce the final out put
      public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, FloatWritable> output, Reporter reporter) throws IOException
      {
            float fin,w,x,y,sum = 0,count;
            System.out.println("********Key*******:"+key.toString());
           
            String a = values.toString();
            System.out.println("***"+a+"***");
             
           
            count=0;
            //x=1;
            //y=1;
            x=0;
            fin=0;
          while (values.hasNext())
          {
        	  //Text a=values.next().get();
        	 // String[] rows = values.toString().split(" ");
        	  
        	  //FileSplit fileSplit = (FileSplit)reporter.getInputSplit();
    	        //String filename = fileSplit.getPath().getName();  
        	  w=(float)values.next().get();
        	  System.out.println("****"+w+"***");
        	  if(w>0)
        	  {
        		  x=x+w;
        		  sum=sum+x;
        	  }
        	  else
        	  {
        		  y=(-1)*w;
        		  sum=sum+y;
        	  }
        	  
        	  //else if(count==1)
        	  //y=(float)values.next().get();
        	   /*if(count==0)
        	   sum=(float)values.next().get(); 	
        	   else
        		{
        		   x=(float)values.next().get();
        		
        	       sum=x/(x+sum);
        		}*/
        	  
        	  
        		  fin=x/sum;
        	  
        	   
        	   count++;
        	   //values.next();
               //sum += values.next().get();
          }
          output.collect(key, new FloatWritable(fin));
      }
}