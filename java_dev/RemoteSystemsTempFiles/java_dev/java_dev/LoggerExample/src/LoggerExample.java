import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerExample {
private static final Logger LOG = Logger.getLogger(LoggerExample.class.getName());

	public static void main(String[] args) throws SecurityException, IOException {
		
		setLogLevel(Level.SEVERE);
		
		LOG.info("Logger Name: "+LOG.getName());
		
		LOG.warning("Can cause ArrayOutOfBoundsException");
		
		int []a = {1,2,3};
		int index = 4;
		
		LOG.config("index is set to: "+ index);

		try{
			System.out.println(a[index]);
		}catch(Exception e){
			LOG.warning("Exception occurs" + e);
		}
	}
	
	public static void setLogLevel(Level level){
		LOG.setLevel(level);
	}
}
