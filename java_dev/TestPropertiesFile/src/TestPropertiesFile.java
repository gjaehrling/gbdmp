
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestPropertiesFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Call getProperties method:");
		List<String> tmp = getProperties();
		if(tmp.contains("foo")){
			System.out.println("exists");
		}
		
		for(String t:tmp){
			if(t.split("=").length > 1){
				String[] fields = t.split("=");
				//System.out.println("key: " + fields[0] + " value: " + fields[1]);
				if(fields[0].startsWith("BDMP_TMP_")){
					System.out.println(t + " starts correct");
				}
			}
		}
	  }
	
	public static List<String> getProperties(){
		List<String> allowedValues = new ArrayList<String>();
		
		try {
			File file = new File("src/main/resources/configuration.cfg");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				allowedValues.add(line);
			}
			fileReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return allowedValues;
	}

}
