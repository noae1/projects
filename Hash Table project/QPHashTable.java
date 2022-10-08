import java.util.Random;

public class QPHashTable extends OAHashTable {

	private long p;
	private ModHash func;

	public QPHashTable(int m, long p) {
		super(m);
		this.p = p;
		this.func = ModHash.GetFunc(m,p);

	}
	
	@Override
	public int Hash(long x, int i) {
		long result = (this.func.Hash(x) + (long)Math.pow(i,2)) % m;
		return (int)result;
	}
}
