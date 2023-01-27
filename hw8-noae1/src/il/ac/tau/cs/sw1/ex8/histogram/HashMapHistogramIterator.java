package il.ac.tau.cs.sw1.ex8.histogram;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;






/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class HashMapHistogramIterator<T extends Comparable<T>> 
							implements Iterator<Map.Entry<T, Integer>>{
	
	
	private  List <Map.Entry<T, Integer>> mapHistogram; // list of pairs : <T,Integer>
	private int currIndex;
	
	public HashMapHistogramIterator (Map <T,Integer> mapHistogram) {
		// create list of pairs
		Set <Map.Entry<T, Integer>> s = mapHistogram.entrySet();
		List <Map.Entry<T, Integer>> lst = new ArrayList <Map.Entry<T, Integer>> (s);
		
		Collections.sort(lst, new mapEntryComparator());   // sorted by comparator 
		
		this.mapHistogram = lst;
		this.currIndex = 0;
	}
	
	
	// inner class:
	public class mapEntryComparator implements Comparator<Map.Entry<T, Integer>>{
		
		// T is comparable, so it already has compareTo method 
        public int compare (Map.Entry<T, Integer> t1 , Map.Entry<T, Integer> t2) {
        	return t1.getKey().compareTo(t2.getKey());
        }
    }
	
	
	@Override
	public boolean hasNext() {
		return currIndex < mapHistogram.size(); 
	}

	@Override
	public Map.Entry<T, Integer> next() {
		// create list of pairs
		List <Map.Entry<T, Integer>> lst = new ArrayList <Map.Entry<T, Integer>> (mapHistogram);
		Map.Entry<T, Integer> item = lst.get(currIndex);
		currIndex ++;
		return item; 
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
		
	}
	
	//add private methods here, if needed
}
