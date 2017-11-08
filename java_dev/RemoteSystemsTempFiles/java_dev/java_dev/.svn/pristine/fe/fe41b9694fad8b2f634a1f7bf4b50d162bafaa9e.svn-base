package hadoop.template;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
//import org.apache.hadoop.io.Writable;
//import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MRTemplateMapper extends MapReduceBase 
	implements Mapper<LongWritable, Text, Text, IntWritable> {

//	private Text word = new Text();
	private final static IntWritable one = new IntWritable(1);
	
	public void map(LongWritable key, 
			Text value,
			OutputCollector<Text, IntWritable> output, 
			Reporter reporter)
					throws IOException {
		
		// get the value as a string:
		String text = value.toString();
		
		// retrieve the date and the time out of the log message as first 15 characters:
		String SyslogDateTime = text.substring(0,15);
		
		// output the syslog date and time as key and 1 as value
		output.collect(new Text(SyslogDateTime), one);
		
	}
}
