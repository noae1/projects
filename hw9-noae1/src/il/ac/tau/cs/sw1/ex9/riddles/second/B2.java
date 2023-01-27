package il.ac.tau.cs.sw1.ex9.riddles.second;

public class B2 extends A2{
	
	private static boolean bool = false;
	
	public A2 getA (boolean b) {
		A2 a2 = new A2();
		bool = b;
		return a2;
	}
	
	public String foo (String s) {
		if (bool) {
			return s.toUpperCase();
		}
		return s.toLowerCase();
	}
}
