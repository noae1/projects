import java.util.Random;

public class ModHash {
	private long a;
	private long b;
	private int m;
	private long p;


	private ModHash (long a, long b, int m, long p){
		this.a = a;
		this.b = b;
		this.m = m;
		this.p = p;

	}

	public static ModHash GetFunc(int m, long p){
		Random rand = new Random();
		long a = 1 + (long) (Math.random() * (p - 1));
		long b = (long) (Math.random() * p);
		ModHash func = new ModHash(a,b,m,(int)p);
		return func;
	}
	
	public int Hash(long key) {
		long result = (((a * key + b)% p)% m);
		return (int)result;
	}
}

