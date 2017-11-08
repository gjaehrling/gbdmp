package hadoop.template;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class MRTemplateReducer extends MapReduceBase 
	implements Reducer<Text, IntWritable, Text, IntWritable> {

	public void reduce(Text key, Iterator<IntWritable> values,
			OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub
		
		// counts the occurrences of the data and time
		int count = 0;
		while (values.hasNext()){
			// add the value to out count
			count += values.next().get();
		}
		
		// output the date and time with this count: 
		output.collect(key, new IntWritable(count));
	}
}
