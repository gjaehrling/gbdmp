
public class SomeClass {
	public void printClassLoader() {
		System.out.println(this.getClass().getClassLoader());
		System.out.println(Thread.currentThread().getContextClassLoader());
	}
}
