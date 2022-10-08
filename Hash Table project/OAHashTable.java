

public abstract class OAHashTable implements IHashTable {
	
	protected HashTableElement [] table;
	protected int m;
	
	public OAHashTable(int m) {
		this.table = new HashTableElement[m];
		this.m = m;
		
	}
	
	
	@Override
	public HashTableElement Find(long key) {
		int place = 0;
		for(int i =0; i< table.length ; i++){
			place = Hash(key, i);
			if(table[place] == null){
				return null;
			}
			else if(table[place].GetKey() == key){
				return table[place];
			}
		}
		return null;
	}


	@Override
	public void Insert(HashTableElement hte) throws TableIsFullException,KeyAlreadyExistsException {

		if (Find(hte.GetKey()) != null) {
			throw new KeyAlreadyExistsException(hte);
		}

		int place = 0;
		for (int i =0; i< table.length ; i++){
			place = Hash(hte.GetKey(), i);
			if(table[place] == null || table[place].GetKey() == -1){

				table[place] = hte;
				return;
			}
		}
		throw new TableIsFullException(hte);
	}
	
	@Override
	public void Delete(long key) throws KeyDoesntExistException {
		int place = 0;

		for(int i =0; i< table.length ; i++){
			place = Hash(key, i);

			if(table[place] == null){
				throw new KeyDoesntExistException(key);
			}

			else if(table[place].GetKey() == key){
				table[place] = new HashTableElement(-1 , -1);	
				return;
			}

		}
		throw new KeyDoesntExistException(key);
	}
	
	/**
	 * 
	 * @param x - the key to hash
	 * @param i - the index in the probing sequence
	 * @return the index into the hash table to place the key x
	 */
	public abstract int Hash(long x, int i);
}
