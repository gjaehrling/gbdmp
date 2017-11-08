
public class ThreadExample implements Runnable{
	private String path;
	public static void main(String[] args) {
		// thread passing with parameters:
		Thread t = new Thread(new ThreadExample("test"));
		t.start();
	}
	
	public ThreadExample(String path){
		this.path = path;	
	}

	public void run(){
		System.out.println("run methode:" + path);
	}
}
