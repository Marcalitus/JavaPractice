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
public class sortingmerge {
  //begin#fragment mergeSort
  /**
   * Sorts the elements of list in in nondecreasing order according
   * to comparator c, using the merge-sort algorithm.
   **/
    static int datasetRow=0;
    static int reduceAfterInsertions =1;
    static int comparekeys=0;
  public <E> void mergeSort2 (PositionList<E> in, Comparator<E> c) {
    int n = 10;
    if (n < 2) 
      return;  // the in list is already sorted in this case
    // divide
    PositionList<E> in1 = new NodePositionList<E>(); 
    PositionList<E> in2 = new NodePositionList<E>(); 
    int i = 0;
    while (i < n/2) {
      in1.addLast(in.remove(in.first())); // move the first n/2 elements to in1
      i++;
    }
    while (!in.isEmpty())
      in2.addLast(in.remove(in.first())); // move the rest to in2
    // recur
    mergeSort2(in1,c);
    mergeSort2(in2,c);
    merge2(in1,in2,c,in);
  }
  //end#fragment mergeSort
  
  //begin#fragment merge
  /**
   * Merges two sorted lists, in1 and in2, into a sorted list in.
   **/
  public  <E> void merge2(PositionList<E> in1, PositionList<E> in2, 
         Comparator<E> c, PositionList<E> in) {
    while (!in1.isEmpty() && !in2.isEmpty())
      if (c.compare(in1.first().element(), in2.first().element()) <= 0)
        in.addLast(in1.remove(in1.first()));
      else
        in.addLast(in2.remove(in2.first()));
    while(!in1.isEmpty()) // move the remaining elements of in1
      in.addLast(in1.remove(in1.first()));
    while(!in2.isEmpty()) // move the remaining elements of in2
      in.addLast(in2.remove(in2.first()));
  }
  //end#fragment merge
 
  //begin#fragment mergeSort2
  /** Sorts an array with a comparator using nonrecursive merge sort.  */
 public static  <E> void mergeSort1(E[] orig, Comparator<E> c) { 
    E[] in = (E[]) new Object[10]; // make a new temporary array
    System.arraycopy(orig,0,in,0,10); // copy the input
    E[] out = (E[]) new Object[10]; // output array
    E[] temp; // temp array reference used for swapping
    int n =10;
    for (int i=1; i < n; i*=2) { // each iteration sorts all length-2*i runs 
      for (int j=0; j < n; j+=2*i)  // each iteration merges two length-i pairs
      {  
       merge1(in,out,c,j,i); // merge from in to out two length-i runs at j
      }
      temp = in; in = out; out = temp; // swap arrays for next iteration
    }
    // the "in" array contains the sorted array, so re-copy it
    System.arraycopy(in,0,orig,0,10);
  }
  /** Merges two subarrays, specified by a start and increment. */
  public static   <E> void merge1(E[] in, E[] out, Comparator<E> c, int start, 
      int inc)
 { // merge in[start..start+inc-1] and in[start+inc..start+2*inc-1]
    int x = start; // index into run #1
    int end1 = Math.min(start+inc, 10); // boundary for run #1
    int end2 = Math.min(start+2*inc, 10); // boundary for run #2
    int y = start+inc; // index into run #2 (could be beyond array boundary)
    int z = start; // index into the out array
    while ((x < end1) && (y < end2)) 
    { 
      if (c.compare(in[x],in[y]) <= 0) out[z++] = in[x++];
      else out[z++] = in[y++];comparekeys++;}
    if (x < end1) // first run didn't finish
      System.arraycopy(in, x, out, z, end1 - x);
    else if (y < end2) // second run didn't finish
      System.arraycopy(in, y, out, z, end2 - y);
  }
 

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
    //Arrays.asList(A).sort(c);
      mergeSort1(A, c);
     
	}
 
     System.out.println(comparekeys/1000); 
      }
}
