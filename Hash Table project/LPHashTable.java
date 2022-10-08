import java.util.Random;

public class LPHashTable extends OAHashTable {

	private long p;
	private ModHash func;


	public LPHashTable(int m, long p) {
		super(m);
		this.p = p;
		this.func = ModHash.GetFunc(m,p);
	}
	
	@Override
	public int Hash(long x, int i) {
		long result = (this.func.Hash(x) + i) % m;
		return (int)result;
	}
	
}
