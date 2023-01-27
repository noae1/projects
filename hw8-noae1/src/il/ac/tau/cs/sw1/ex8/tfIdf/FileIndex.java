package il.ac.tau.cs.sw1.ex8.tfIdf;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;




/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class FileIndex {
	
	private boolean isInitialized = false;
	
	
	private Map <String,HashMapHistogram<String>> HistogramAllFiles;   
	private Map <String , List<String>> sortedByTFIDE; 
	
	
	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 * @pre: isInitialized() == false;
	 */
  	
	
	public class ComparatorByTFIDE implements Comparator <String>{
			
		private String fileName;
		
		public ComparatorByTFIDE(String fileName) {
			this.fileName = fileName;
		}
			
		// pre: fileName exists in HistogramAllFiles.keysSet()
	    public int compare (String s1 , String s2) {
	    	
	    	try {
				double s1_TFIDE = getTFIDF(s1,fileName);
				double s2_TFIDE = getTFIDF(s2,fileName);
				if (s1_TFIDE != s2_TFIDE) {
					return Double.compare(-s1_TFIDE, -s2_TFIDE);
				}
				return s1.compareTo(s2);
				
	    	} catch (FileIndexException e) {
	    		System.out.println("File name doesn't exist");
	    		return 0;
			}
	    }
	    
	}
	
	
	
	public void indexDirectory(String folderPath) { //Q1
		//This code iterates over all the files in the folder. add your code wherever is needed
  		
  		List<String> tokens = new ArrayList <String>();  
  		HashMapHistogram<String> hist = null;
  		Map <String,HashMapHistogram<String>> HistogramAllFiles = new HashMap <String,HashMapHistogram<String>>();
  		Map <String , List<String>> sortedByTFIDE = new HashMap <String,List<String>>(); 
  		List<String> words = null;
  		
		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		for (File file : listFiles) {
			// for every file in the folder
			if (file.isFile()) {
				/*******************/
				try {
					hist = new HashMapHistogram<String>();
					tokens = FileUtils.readAllTokens(file);
					hist.addAll(tokens);   // create histogram for each file
					HistogramAllFiles.put(file.getName(), hist);
					
					
					if (hist.getItemsSet() instanceof Set) {
						words = new ArrayList <String> ((Set<String>)hist.getItemsSet()); 
						
						sortedByTFIDE.put(file.getName(), words);
					}
					
					
				}
				catch (Exception e) {
					System.out.println("An exception has occured");
					e.printStackTrace();
				}
				/*******************/
			}
		}
		/*******************/
		this.HistogramAllFiles = HistogramAllFiles;
		
		for (Map.Entry<String, List<String>> entry: sortedByTFIDE.entrySet()) {
			List<String> lst = entry.getValue();
			Collections.sort(lst, new ComparatorByTFIDE(entry.getKey()));
		}
		this.sortedByTFIDE = sortedByTFIDE;
		/*******************/
		isInitialized = true;
	}
	
	
	
	// Q2
  	
	/* @pre: isInitialized() */
	public int getCountInFile(String word, String fileName) throws FileIndexException{ 
		word = word.toLowerCase();
		return histogramForFileName(fileName).getCountForItem(word);
	}
	
	/* @pre: isInitialized() */
	public int getNumOfUniqueWordsInFile(String fileName) throws FileIndexException{ 
		return histogramForFileName(fileName).getItemsSet().size();
	}
	
	/* @pre: isInitialized() */
	public int getNumOfFilesInIndex(){
		return HistogramAllFiles.keySet().size();
	}

	
	/* @pre: isInitialized() */
	public double getTF(String word, String fileName) throws FileIndexException{ // Q3
		word = word.toLowerCase();
		int repetitionsForWord = getCountInFile(word,fileName);
		int numOfWordsInDoc = histogramForFileName(fileName).getCountsSum();
		double result = calcTF(repetitionsForWord , numOfWordsInDoc);
		return result; 
	}
	
	/* @pre: isInitialized() 
	 * @pre: exist fileName such that getCountInFile(word) > 0*/
	public double getIDF(String word){ //Q4
		word = word.toLowerCase();
		int numOfDocs = getNumOfFilesInIndex();
		int numOfDocsContainingWord = numOfDocsContainingWord(word);
		double result = calcIDF(numOfDocs , numOfDocsContainingWord);
		return result;
	}
	
	
	
	/*
	 * @pre: isInitialized()
	 * @pre: 0 < k <= getNumOfUniqueWordsInFile(fileName)
	 * @post: $ret.size() = k
	 * @post for i in (0,k-2):
	 * 		$ret[i].value >= $ret[i+1].value
	 */
	public List<Map.Entry<String, Double>> getTopKMostSignificantWords(String fileName, int k) 
													throws FileIndexException{ //Q5
		if (!sortedByTFIDE.containsKey(fileName)) {
			throw new FileIndexException ("File name doesn't exist");
		}
		List<String> sortedWords = this.sortedByTFIDE.get(fileName);
		
		List<Map.Entry<String, Double>> result = new ArrayList <Map.Entry<String, Double>> ();
		int i = 0;
		for (String word : sortedWords) {
			if (i == k) {
				break;
			}
			//Map.Entry<String, Double> entry = Map.entry(word, getTFIDF(word,fileName));
			
			result.add(new AbstractMap.SimpleEntry<String, Double>(word , getTFIDF(word,fileName)));
			i++;
		}
		
		return result; 
	}
	
	
	
	
	/* @pre: isInitialized() */
	public double getCosineSimilarity(String fileName1, String fileName2) throws FileIndexException{ //Q6
		HashMapHistogram<String> hist1 = histogramForFileName(fileName1);
		HashMapHistogram<String> hist2 = histogramForFileName(fileName2);
		
		Set <String> words1 =hist1.getItemsSet();
		Set <String> words2 =hist2.getItemsSet();
		Set <String> Allwords = new HashSet<String>();
		Allwords.addAll(words1);
		Allwords.addAll(words2);
		
		double numerator = calculateSumOfMult(Allwords,fileName1,fileName2);
		double s1 = calculateSumOfMult(words1,fileName1,fileName1);
		double s2 = calculateSumOfMult(words2,fileName2,fileName2);
		double denominator = Math.sqrt(s1*s2);
		
		return numerator/denominator ; 
	}
	
	
	private double calculateSumOfMult (Set <String> Allwords, String fileName1, String fileName2) throws FileIndexException{
		double sum = 0.0;
		
		for (String word : Allwords) {
			sum += (getTFIDF(word , fileName1) * getTFIDF(word , fileName2)); 
		}
			
		return sum;
	}
	
	
	
	/*
	 * @pre: isInitialized()
	 * @pre: 0 < k <= getNumOfFilesInIndex()-1
	 * @post: $ret.size() = k
	 * @post for i in (0,k-2):
	 * 		$ret[i].value >= $ret[i+1].value
	 */
	public List<Map.Entry<String, Double>> getTopKClosestDocuments(String fileName, int k) 
			throws FileIndexException{ //Q7
		Double cosSim = 0.0;
		
		List<Map.Entry<String, Double>> result = new ArrayList <Map.Entry<String, Double>> ();
		for (String otherFile : HistogramAllFiles.keySet()) {
			
			if (! otherFile.equals(fileName)) {
				cosSim = getCosineSimilarity(fileName , otherFile);
				//Map.Entry<String, Double> entry = Map.entry(otherFile, cosSim);
				
				result.add(new AbstractMap.SimpleEntry<String, Double>(otherFile , cosSim));
			}
		}
		
		// result's size <= getNumOfFilesInIndex()-1
		Collections.sort(result, new cosSimComparator());
		
		return result.subList(0, k);
	}

	
	public class cosSimComparator implements Comparator<Map.Entry<String, Double>>{
		
		public int compare (Map.Entry<String, Double> t1 , Map.Entry<String, Double> t2) {
			Double diff1 = 1-t1.getValue();
			Double diff2 = 1-t2.getValue();
			return diff1.compareTo(diff2);
		}		
		
	}
	        	
		       
	        
	
	//add private methods here, if needed
	private HashMapHistogram<String> histogramForFileName (String fileName) throws FileIndexException{
		for (Map.Entry<String,HashMapHistogram<String>> entry: HistogramAllFiles.entrySet()) {
			if (entry.getKey().equals(fileName)) {
				return entry.getValue();
			}
		}
		throw new FileIndexException ("File name doesn't exist");
	}
	
	private int numOfDocsContainingWord (String word) {
		int cnt = 0;
		for (HashMapHistogram<String> hist: HistogramAllFiles.values()) {
			if (hist.getCountForItem(word) > 0) {
				cnt ++;
			}
		}
		return cnt;
	}

	
	/*************************************************************/
	/********************* Don't change this ********************/
	/*************************************************************/
	
	public boolean isInitialized(){
		return this.isInitialized;
	}
	
	/* @pre: exist fileName such that getCountInFile(word) > 0*/
	public double getTFIDF(String word, String fileName) throws FileIndexException{
		return this.getTF(word, fileName)*this.getIDF(word);
	}
	
	private static double calcTF(int repetitionsForWord, int numOfWordsInDoc){
		return (double)repetitionsForWord/numOfWordsInDoc;
	}
	
	private static double calcIDF(int numOfDocs, int numOfDocsContainingWord){
		return Math.log((double)numOfDocs/numOfDocsContainingWord);
	}
	
}
