import java.util.Random;

public class AQPHashTable extends OAHashTable {

	private long p;
	private ModHash func;

	public AQPHashTable(int m, long p) {
		super(m);
		this.p = p;
		this.func = ModHash.GetFunc(m,p);
	}
	
	@Override
	public int Hash(long x, int i) {
		int sign = (((i%2)*2)-1)*(-1);
		long sol = this.func.Hash(x) + (long)(sign * Math.pow(i,2));
		long result = Math.floorMod(sol, m);
		return (int)result;
	}


}
