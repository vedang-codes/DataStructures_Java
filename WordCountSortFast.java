
/**
 * A2 starter code for 2540, 2020. 
 * Author: Jianguo Lu 
 * The purpose is to understand sorting algorithms and their performances. 
 * It prints out the frequency of the 200-th most frequent word.
 */
import java.io.*;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;

public class WordCountSortFast {

	/** Merge two strings. See the textbook for explanation. **/
	public static void merge(String[] S1, String[] S2, String[] S) {
		int i = 0, j = 0;
		while (i + j < S.length) {
			if (j == S2.length || (i < S1.length && S1[i].compareTo(S2[j]) < 0))
				S[i + j] = S1[i++];
			else
				S[i + j] = S2[j++];
		}
	}

	public static void mergeSort(String[] S) {
		int n = S.length;
		if (n < 2)
			return;
		int mid = n / 2;
		// partition the string into two strings
		String[] S1 = Arrays.copyOfRange(S, 0, mid);
		String[] S2 = Arrays.copyOfRange(S, mid, n);
		mergeSort(S1);
		mergeSort(S2);
		merge(S1, S2, S);
	}

	private static void swap(String[] array, int i, int j) {
		String tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
// need to edit
	public static Integer countFAST(String fileName ) throws Exception {
		StringBuilder str = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = " ";
        while ((line = br.readLine()) != null) {
			str.append(line).append(" ");
		}
		String[] tokens = str.toString().trim().toLowerCase().split("[^a-zA-Z]+");
        br.close();
		Arrays.sort(tokens);
		int count[] = new int[1000000];
		String maxWord = tokens[0];
		int j = 0, k = 0;
		while (j < tokens.length - 1) {
			int duplicates = 1;
			if(j < tokens.length - 2 & tokens[j].equals(tokens[j + 1])) {
				j++;
				duplicates++;
			}
			else {
				maxWord = tokens[j];
				count[k] = duplicates;
				j++;
				k++;
			}
			count[k] = duplicates;
		}
		String[] copyOfWords=new String[k];
		Integer[] copyOfCounts=new Integer[k];
		
		for (int i=0; i<k; i++) {
			copyOfCounts[i] = count[i];
		}
			
		Arrays.sort(copyOfCounts);
		
		return copyOfCounts[k-200];		
	}

	public static void main(String[] args) throws Exception {
		String PATH = "C:/Users/vedan/Desktop/UWindsor/SEM-3 FALL 2021/COMP-2540/Lab-Assignments/Assignment2/dblp1280k.txt";
		//String[] DATASETS = { "200", "400","800","1600","3200","6400", "10000","20000","40000", "80000","160000", "320000", "640000", "1280000"};//, "10k","100k"}; //, "5k", "10k", "100k", "1m", "" };
		long startTime = System.currentTimeMillis();
	    Integer _200th_freq= countFAST(PATH);
	    long endTime = System.currentTimeMillis();
	    String time = String.format("%12d", endTime - startTime);
	    System.out.println(time + ".\t 200th freq is"  + _200th_freq);
    }
} 