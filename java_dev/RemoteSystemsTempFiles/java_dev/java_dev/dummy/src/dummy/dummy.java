package dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

public class dummy {
	
	public static final String JSON_LOGGER = "--logger";
	public Map<String, String> testVar = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<String> cmdArray = new ArrayList<String>();
		ArrayList<String> cmdParameterArray = new ArrayList<String>();
		String cmdExec = "python";
		
		cmdParameterArray.add("arg1");
		cmdParameterArray.add("arg2");
		
		cmdArray.add(cmdExec);
		cmdArray.addAll(cmdParameterArray);
		String logLevel = "DEBUG";
		cmdArray.add(0, JSON_LOGGER);
		cmdArray.add("-L "+logLevel);
		
		//System.out.println(cmdArray);
		
		//testCollection();
		
		parseList();
	}
	
	public static void parseList(){
		Map<String, String> scriptPath = null;
		Map<String, String> addVars = new HashMap<String, String>();
		Map<String, String> allVars = new Hashtable<String, String>();
		
		Map<String, String> testVar = new HashMap<String, String>();
		testVar.put("BDMP", "test");
		
		if(testVar.isEmpty()){
			System.out.println("testVar is empty:" + testVar.toString());
		}
		else {
			System.out.println("testVar is not empty: " + testVar.toString());
		}
		
		addVars.put("BDMP", "test");
		addVars.put("JAVA_HOME", "keine.Ahnung.de");
		addVars.put("BDMP_CONFIG_EXT", "");
		//addVars = null;

		if(scriptPath == null){
			if(addVars == null){
				System.out.println("allVars created as unmodifiable list!");
				allVars = System.getenv();
			}
			else {
				System.out.println("allVars combined addVars and system environment!");
				allVars.putAll(addVars);
				allVars.putAll(System.getenv());
			}			
		}
		else {
			System.out.println("allVars will be filled using script input");
		}
		
		for(String allVarsName : allVars.keySet()){
			System.out.print(allVarsName);
			System.out.println(" = " + allVars.get(allVarsName));
		}
		
		for(String v : allVars.values()){
			System.out.println("Values: " + v);
		}
		
		dummy test = new dummy();
		test.testCollection();
		if(test != null){
			System.out.println("test is instance: "+ test);
		}
				
	}
	
	@SuppressWarnings("unchecked")
	public static void testCollection(){
		System.out.println("Tree Map Example!\n");
	      @SuppressWarnings("rawtypes")
		TreeMap tMap = new TreeMap();
	      tMap.put(1, "Sunday");
	      tMap.put(2, "Monday");
	      tMap.put(3, "Tuesday");
	      tMap.put(4, "Wednesday");
	      tMap.put(5, "Thursday");
	      tMap.put(6, "Friday");
	      tMap.put(7, "Saturday");
	      System.out.println("Keys of tree map: " 
	      + tMap.keySet());
	      System.out.println("Test the toString method: " + tMap.toString());
	      System.out.println("Values of tree map: " 
	      + tMap.values());
	      System.out.println("Key: 5 value: " + tMap.get(5)+ "\n");
	      System.out.println("First key: " + tMap.firstKey() 
	      + " Value: " 
	      + tMap.get(tMap.firstKey()) + "\n");
	      System.out.println("Last key: " + tMap.lastKey() 
		  + " Value: "+ tMap.get(tMap.lastKey()) + "\n");
	      System.out.println("Removing first data: " 
	      + tMap.remove(tMap.firstKey()));
	      System.out.println("Now the tree map Keys: " 
	      + tMap.keySet());
	      System.out.println("Now the tree map contain: " 
	      + tMap.values() + "\n");
	      System.out.println("Removing last data: " 
	      + tMap.remove(tMap.lastKey()));
	      System.out.println("Now the tree map Keys: " 
	      + tMap.keySet());
	      System.out.println("Now the tree map contain: " 
	      + tMap.values());
	}

}
