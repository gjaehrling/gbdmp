package processBuilderExample;

import java.util.ArrayList;

public class runner {
	
	public static final String PYTHON_EXEC = "python"; 
	public ArrayList<String> cmdArray = null;
	
	public runner(ArrayList<String> cmdArray){
		cmdArray.add(0, PYTHON_EXEC);
		this.cmdArray = cmdArray;
	}
	
	public ArrayList<String> getCmdArray(){
		return cmdArray;
	}
}
