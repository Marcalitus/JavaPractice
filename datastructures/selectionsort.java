//package net.datastructures;
package net.datastructures;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Arrays;
//class Transformer_B2_experiment12;
//import net.datastructures.*;



/**
* Class containing various sorting algorithms.
*
* @author Michael Goodrich, Roberto Tamassia, Eric Zamore
*/
public class selectionsort {
  //begin#fragment mergeSort
  /**
   * Sorts the elements of list in in nondecreasing order according
   * to comparator c, using the merge-sort algorithm.
   **/
    static int datasetRow=0;
    static int reduceAfterInsertions =1;
    static int comparekeys=0;
  

  //begin#fragment mergeSort2
  /** Sorts an array with a comparator using nonrecursive merge sort.  */
 public static  <E> void selectionsort1(Integer[] orig, Comparator<Integer> c) { 
    int n =10;
    boolean notend;
    for (int i=0; (i <= (n-1)); i++) { // each iteration sorts all length-2*i runs 
    
      Integer temp=orig[i];int indexmin=i;
      
      for (int j=i; ((j < n)); j++)
      {comparekeys++;
      if (c.compare(temp,orig[j] )>0) 
      {temp=orig[j]; indexmin=j;
      }
      //System.out.println(Arrays.asList(orig));
    }
      int x=orig[i];
      orig[i]=temp;
      orig[indexmin]=x;
       
    }
  }
  /** Merges two subarrays, specified by a start and increment. */

 

  public static  void main (String[] argv) throws IOException {
	for (int rep=1;rep<=1000;rep++)  
	{
    //out("Start your engines...");
   // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    Random r = new Random();
    Comparator<Integer> c = new DefaultComparator<Integer>();
    //out("Enter number of elements:");
  //  String num = in.readLine();
    int n = 10;
    Integer[] A = new Integer[n];
    for (int i = 0; i < n; i++) {
        int x = r.nextInt(100);
        A[i] = (Integer)x;
      }
     Arrays.asList(A).sort(c);
      System.out.println(Arrays.asList(A));
      selectionsort1(A, c);
      System.out.println(Arrays.asList(A));
	}
 
     System.out.println(comparekeys/1000);
      }
}