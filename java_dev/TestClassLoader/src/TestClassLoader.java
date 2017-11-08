import java.net.URL;

public class TestClassLoader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Thread.currentThread().setContextClassLoader(new MyClassLoader());
		SomeClass someClass = new SomeClass();
		someClass.printClassLoader();
		
		System.out.println("Class loader from main method:");
		System.out.println(TestClassLoader.class.getClassLoader());
		//ClassLoader loader = TestClassLoader.class.getClassLoader();
		ClassLoader loader = someClass.getClass().getClassLoader();
		
		System.out.println("loader Object hashCode: " + (loader.getClass().hashCode()));
		System.out.println("loader Object identityHashCode : " + Integer.toHexString(System.identityHashCode(loader)));
		System.out.println("loader Object reference: " + loader);
		
		System.out.println("Print ClassLoader hierarchie: ");
		
		while(loader != null){
			System.out.println(loader);
			loader = loader.getParent();
			}
		
	}
	
	public static class MyClassLoader extends ClassLoader {
		public MyClassLoader(){
			super();
		}
		public MyClassLoader(ClassLoader parent){
			super(parent);
		}
	}
}

