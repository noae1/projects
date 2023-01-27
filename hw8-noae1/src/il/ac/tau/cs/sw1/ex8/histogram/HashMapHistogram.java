package il.ac.tau.cs.sw1.ex8.histogram;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T>{

	Map <T,Integer> mapHistogram;
	
	
	//add constructor here, if needed

	
	public HashMapHistogram(){
		mapHistogram = new HashMap <T,Integer> ();
	}
	
	
	@Override
	public void addItem(T item) {
		int curr = 0;
		if (ifExist(item)) {
			curr = getCountForItem(item);
		}
		mapHistogram.put(item , curr+1);
	}
	
	@Override
	public boolean removeItem(T item)  {
		int curr = 0;
		if (ifExist(item)) {
			curr = getCountForItem(item);
			if (curr != 0) {
				mapHistogram.put(item , curr-1);
			}
			return true;
		}
		return false; 
	}
	
	@Override
	public void addAll(Collection<T> items) {
		for (T currItem : items) {
			addItem(currItem);
		}
	}

	@Override
	public int getCountForItem(T item) {
		int cnt = 0;
		if (ifExist(item)) {
			cnt = mapHistogram.get(item);
		}
		return cnt; 
	}

	@Override
	public void clear() {
		for (T item : mapHistogram.keySet()) {
			mapHistogram.put(item , 0);
		}
	}

	@Override
	public Set<T> getItemsSet() {
		Set<T> itemsSet = new HashSet <T>();
				
		Iterator <Map.Entry<T, Integer>> it = iterator();
		
		while (it.hasNext()) {
			Entry<T, Integer> item = it.next();
			if (item.getValue() > 0) {
				itemsSet.add(item.getKey());
			}
		}
		
		return itemsSet; 
	}
	
	@Override
	public int getCountsSum() {
		int sum = 0;
		int val = 0;
		Iterator <Map.Entry<T, Integer>> it = iterator();
		
		while (it.hasNext()) {
			val = it.next().getValue();
			sum += val;
		}
		return sum; 
	}

	
	@Override
	public Iterator<Map.Entry<T, Integer>> iterator() {  
		return new HashMapHistogramIterator<T>(mapHistogram);
	}
	
	
	private boolean ifExist (T item) {
		return mapHistogram.containsKey(item);
	}



}
