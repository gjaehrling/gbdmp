package parseSimpleJSON;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class parseSimpleJSON {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		String jsonString = ("{\"asctime\": \"2016-06-06 10:55:09,373\", \"created\": 1465203309.3731771, \"levelname\": \"DEBUG\", \"module\": \"debughelper\", \"message\": \"encoding_stdin = utf-8\"}");
		
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(jsonString);
		
		JSONObject jsonObj = (JSONObject) obj;
		
		System.out.print("AscTime: " + jsonObj.get("asctime").toString());
		System.out.println(" Message: " + jsonObj.get("message").toString());
		
	}

}
