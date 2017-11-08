package HadoopBook.Chapter1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.util.StringUtils;

public class Map implements Mapper <LongWritable, Text, Text, Text>{

	private Text documentID;
	private Text word = new Text();

	protected void setup(Context context){
		String filename = ((FileSplit) context.getInputSplit()).getPath().getName();
		
		documentID = new Text(filename);
	}
	
	protected void map(LongWritable key, Text value,
			Context context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		for(String token : StringUtils.split(value.toString())){
			word.set(token);
			context.write(word, documentID);
		}
		
	}

	public void configure(JobConf arg0) {
		// TODO Auto-generated method stub
		
	}

	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	public void map(LongWritable arg0, Text arg1,
			OutputCollector<Text, Text> arg2, Reporter arg3) throws IOException {
		// TODO Auto-generated method stub
		
	}	
	
}
