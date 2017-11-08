package processBuilderExample;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class processBuilderExample {

	public static void main(String[] args) throws IOException {
		String[] command = {"./test_process_builder.sh", "pwd", "ls -la"};
		//String[] command = {"python", "test_python.py", "foo", "bar"};
		
		final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
		
				ProcessBuilder pb = new ProcessBuilder(command);
		pb.directory(new File(dir));
		
		Process process = pb.start();
		System.out.println("Post the environment: " + pb.environment());
		
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String line;
		System.out.printf("Output of running %s is: \n", Arrays.toString(command));
		
		while((line = br.readLine()) != null){
			System.out.println(line);
		}
		
		try {
			int exitValue = process.exitValue();
			System.out.println("\n\nExit Value = " + exitValue);
		}finally{
			br.close();
		}
		
		ArrayList<String> cmdArray = new ArrayList<String>();
		cmdArray.add("script");
		cmdArray.add("args");
		cmdArray.add(0, "bla");
		
		runner r = new runner(cmdArray);
		System.out.println(r.getCmdArray());
		

	}
	
	

}

