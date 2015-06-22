import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;


public class FinalTrailerReducer extends MapReduceBase implements Reducer<Text, FloatWritable, Text, Text>
{
      //reduce method accepts the Key Value pairs from mappers do the aggregation based on keys and produce the final out put
      public void reduce(Text key, Iterator<FloatWritable> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException
      {
            String sum,a;
            int count;
            System.out.println("********Key*******:"+key.toString());
           
            /*String a = values.toString();
            System.out.println("***"+a+"***");*/
             
           
            count=0;
            //x=1;
            //y=1
           float x,y;
           String s;
            x=0;
            y=0;
          while (values.hasNext())
          {
        	  //Text a=values.next().get();
        	 // String[] rows = values.toString().split(" ");
        	  
        	  float f=values.next().get();
        	  if(f<0)
        	  {
        		  y=(-1)*f;
        	  }
        	  else
        	  {
        		  x=f;
        	  }
        	 
          }
          s=x+"\t"+y;
          output.collect(key, new Text(s));
      }
}