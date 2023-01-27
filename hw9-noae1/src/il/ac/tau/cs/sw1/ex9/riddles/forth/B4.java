package il.ac.tau.cs.sw1.ex9.riddles.forth;

import java.util.Iterator;

public class B4 implements Iterator{

	private String[] strings;
	private int k;
	private int j;  // 0 <= j < k
	private int curr;
	private int passed;
	
	public B4 (String[] strings, int k) {
		this.strings = strings;
		this.k = k;
		this.j = 0;
		this.curr = 0;
		this.passed = 0;
	}
	
	
	@Override
	public boolean hasNext() {
		return passed < strings.length +(k-1)*strings.length;
	}

	@Override
	public Object next() {
		String s = strings[(curr + strings.length * j) % strings.length];
		if (curr == strings.length - 1) {
			j++;
		}
		curr = (curr + 1) % strings.length;
		passed ++;
		return s;
	}
	
}
