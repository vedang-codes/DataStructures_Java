
import java.io.File;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;

public class WordCountLinkedList254 {
	public static Entry<String, Integer> count_ARRAY(String[] tokens) {
		/**
		    a program that can improve the time complexity that is much better than count ARRAY (i.e., in
			orders of magnitude). One hint of improvement is to sort the data before counting. 
		 */
		Arrays.sort(tokens);
		int maxCount = 1;
		String maxWord = tokens[0];
		int tempCount = 0;
		String tempWord;
		for (int i = 0; i < tokens.length -1; i++) {
			tempWord = tokens[i];
			if(tokens[i].equals(tokens[i + 1])){
				tempCount++;
				if(tempCount >  maxCount) {
					maxCount = tempCount;
					maxWord = tempWord;
				}
			} else {
				tempCount = 1;
			}
		}
		return new AbstractMap.SimpleEntry<String, Integer>(maxWord, maxCount);
	}
	/**
	 * 	The implementation logic is very simple{it scans the
		word frequency list one by one until it reaches the end of wordFreqList or ends the word. The problem is the get(i)
		method{it iterates through the list to get the i-th element. Your task is to improve this algorithm while keeping the
		LinkedList data structure. A hint is to avoid to use get(i) by changing the way to iterate the elements in the list.
	 */
	public static Entry<String, Integer> count_LINKED_LIST(String[] tokens) {
		LinkedList<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>();
		for (int j = 0; j < tokens.length; j++) {
			String word = tokens[j];
			boolean found = false;
			Iterator<Entry<String, Integer>> itr = list.iterator();
			for (int i = 0; i < list.size(); i++) {
				Entry<String, Integer> e = itr.next();
				if (word.equals(e.getKey())) {
					e.setValue(e.getValue() + 1);
					list.set(i, e);
					found = true;
					break;
				}
			}
			if (!found)
				list.add(new AbstractMap.SimpleEntry<String, Integer>(word, 1));
		}
		
		int maxCount = 0;
		String maxWord = "";
		for (int i = 0; i < list.size(); i++) {
			int count = list.get(i).getValue();
			if (count > maxCount) {
				maxWord = list.get(i).getKey();
				maxCount = count;
			}
		}
		return new AbstractMap.SimpleEntry<String, Integer>(maxWord, maxCount);
	}

	static String[] readText(String PATH) throws Exception {
		Scanner doc = new Scanner(new File(PATH)).useDelimiter("[^a-zA-Z]+");
		int length = 0;
		while (doc.hasNext()) {
			doc.next();
			length++;
		}

		String[] tokens = new String[length];
		Scanner s = new Scanner(new File(PATH)).useDelimiter("[^a-zA-Z]+");
		length = 0;
		while (s.hasNext()) {
			tokens[length] = s.next().toLowerCase();
			length++;
		}
		doc.close();

		return tokens;
	}

	public static void main(String[] args) throws Exception {

		String PATH = "C:/Users/vedan/Desktop/UWindsor/SEM-3 FALL 2021/COMP-2540/Lab-Assignments/Assignment1/dblp200.txt";
		String[] tokens = readText(PATH);
		long startTime = System.currentTimeMillis();
		Entry<String, Integer> entry = count_LINKED_LIST(tokens);
		long endTime = System.currentTimeMillis();
		String time = String.format("%12d", endTime - startTime);
		System.out.println("time\t" + time + "\t" + entry.getKey() + ":" + entry.getValue());

		tokens = readText(PATH);
		startTime = System.currentTimeMillis();
		entry = count_ARRAY(tokens);
		endTime = System.currentTimeMillis();
		time = String.format("%12d", endTime - startTime);
		System.out.println("time\t" + time + "\t" + entry.getKey() + ":" + entry.getValue());
	}

}