package test_java;

import junit.framework.Test;

import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class test_java {

	public static void main(String[] args) throws InterruptedException, IOException{
		// write path of the class to stdout
		System.out.println("-------------------------- Test File-Path -----------------------------");
		java.net.URL location = Test.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
        
        
        ArrayList<String> cmdArray = new ArrayList<String>();
        cmdArray.add("python");
        cmdArray.add("-c");
        cmdArray.add("print \"Hello World\"");
        
        System.out.println(cmdArray.get(0));
        
        List<String> testList = new ArrayList<String>();
        testList.add("foo");
        testList.add("--json");
        testList.add("bar");
        
        if(testList.contains("--json")){
        	System.out.println("JSON found!");
        }
        
        if(testList != null){
        	for (String element : testList){
        		System.out.println(element);
        	}
        }
        
        //cmdArray.remove(0);
        //System.out.println(cmdArray.get(1));
        
        //ProcessBuilder pb = new ProcessBuilder("echo", "This is ProcessBuilder Example from JCG");
        ProcessBuilder pb = new ProcessBuilder(cmdArray);
		System.out.println("Run command: " + cmdArray.get(0));
		Process process = pb.start();
		int errCode = process.waitFor();
		
		//test environment:
		System.out.println("Test Environment: ");
		System.out.println(pb.environment().entrySet());
		System.out.println("Echo command executed, any errors? " + (errCode == 0 ? "No" : "Yes"));
		System.out.println("Echo Output:\n" + output(process.getInputStream()));	
		
		// test sleep for 5 seconds 
		for(int i=0;i<10;i++){
			Thread.sleep(50);
			System.out.println(i);
		}
		
	}

	private static String output(InputStream inputStream) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + System.getProperty("line.separator"));
			}
		} finally {
			br.close();
		}
		return sb.toString();
	}
}
