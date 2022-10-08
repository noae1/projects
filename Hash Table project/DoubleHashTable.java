
public class DoubleHashTable extends OAHashTable {

	private long p;
	private ModHash func1;
	private ModHash func2;

	public DoubleHashTable(int m, long p) {
		super(m);
		this.p = p;
		this.func1 = ModHash.GetFunc(m,p);
		this.func2 = ModHash.GetFunc(m-1,p);
	}
	
	@Override
	public int Hash(long x, int i) {
		long h1x = this.func1.Hash(x);
		long h2x = this.func2.Hash(x) + 1;
		long result = (h1x + (i*h2x))%m;
		return (int)result;
	}
	
}
