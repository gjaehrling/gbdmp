package hadoop.template;

import hadoop.template.MRTemplateDriver;
import hadoop.template.MRTemplateMapper;
import hadoop.template.MRTemplateReducer;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MRTemplateDriver extends Configured implements Tool{

	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		JobConf conf = new JobConf(getConf(), getClass()); 
		conf.setJobName("MRTemplate");
		
		FileInputFormat.addInputPath(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		conf.setMapperClass(MRTemplateMapper.class);
		conf.setCombinerClass(MRTemplateReducer.class);
		conf.setReducerClass(MRTemplateReducer.class);

		JobClient.runJob(conf);
		return 0;
	}
	
	public static void main(String args[]) throws Exception {
		int exitCode = ToolRunner.run(new MRTemplateDriver(), args);
		System.exit(exitCode);
		
	}
}
