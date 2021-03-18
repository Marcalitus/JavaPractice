package net.datastructures;
//package net.datastructures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Arrays;
import net.datastructures.*;
//class Transformer_B2_Experimentt{};
/**
* Class containing various sorting algorithms.
*
* @author Michael Goodrich, Roberto Tamassia, Eric Zamore
*/
public class sortll {
//begin#fragment mergeSort
/**
 * Sorts the elements of list in in nondecreasing order according
 * to comparator c, using the merge-sort algorithm.
 **/
static Integer[] A = {62, 1, 40, 60, 3, 79, 49, 90, 68, 97};// identify an arr
static int datasetRow=0;
static int reduceAfterInsertions =1;
static boolean start=true;
static int ne=0;
static boolean error=false;
static int err=0;
//static File file = new File("C:\\Users\\AliJaoua\\eclipse-workspace\\datastructures\\sort_100.csv");
static String sort_filename = "C:\\Users\\AliJaoua\\Documents\\QUTAIBAH-MEETING-7-7-2020\\NEWVERSION-Implications\\sorterrone_100.csv";
static File file = new File(sort_filename);	
private static void writenew(String value) {
		//if(value=="*") 
		//if (value=="\n") System.out.println(); 
				//else System.out.print(value+",");	
			// file = new File("D:\\Users\\jaoua\\workspace3\\datastructures\\Kruskalerrone.csv");
			FileWriter fr = null;
		        try {
		            fr = new FileWriter(file,true);
		            if (!(start && (value=="\n"))) fr.write(value);
		            if((value!="\n")) {   
		            
		            fr.write(",");}
		        } catch (IOException e) {
		            e.printStackTrace();
		        }finally{
		            //close resources}
		            try {
		                fr.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		        }
		    }
		        start=false;
	}

//begin#fragment quickSort 
private static  <E> void quickSort3 (Integer[] s, Comparator<Integer> c) {
  datasetRow=0;
	if (s.length < 2) return; // the array is already sorted in this case
  quickSortStep3(s, c, 0, s.length-1); // recursive sort method
}
//end#fragment quickSort

/**
 * Sorts in nondecreasing order the elements of sequence s between
 * ranks leftBound and rightBound, using a recursive, in-place,
 * implementation of the quick-sort algorithm.
 **/
//begin#fragment quickSortStep
public static  void quickSortStep3 (Integer[] s, Comparator<Integer> c,
                            int leftBound, int rightBound ) {
  if (leftBound >= rightBound) return; // the indices have crossed
  int temp;  // temp object used for swapping
  int pivot= s[rightBound];
  int leftInd = leftBound;     // will scan rightward
  int rightInd = rightBound-1; // will scan leftward, devrait etre rightInd = rightBound-1;
  while (leftInd <= rightInd) { // scan right until larger than the pivot, erreur injectee initialement while (leftInd <= rightInd)
    while ( (leftInd <= rightInd) && (c.compare(s[leftInd], pivot)<=0) )// changed <= initially
      leftInd++; // devrait etre leftInd=leftInd+1; 
    while ( (rightInd >= leftInd) && (c.compare(s[rightInd], pivot)>=0))
      {rightInd--;}
    int x;
    Random r = new Random();
    //x=r.nextInt(100)%2;
    if ((leftInd < rightInd)&&!(c.compare(s[rightInd], s[leftInd])==0)) 
    { // both elements were found, so swap
      temp = s[rightInd]; 
      int templeft=  s[leftInd]; 
      s[rightInd] =templeft;
      s[leftInd] = temp;
      out(" "+Arrays.asList(s));
      //for (int f=0;f<s.length;f++) writenew(s[f].toString());
     
      int y;
    //  Random r = new Random();
     y=r.nextInt(100)%2;
      //if ((datasetRow<3))
     {
    	     
    	    for (int i = 0; i <7; i++) {
    	      int z = r.nextInt(100);
    	      s[i] =  new Integer(i*7+z);
    	     //System.out.println("I AM HERE");
    	      
    	    }
      }
      for (int f=0;f<s.length;f++) writenew(s[f].toString());
     if ((s[rightInd]==templeft)&&(s[leftInd]==temp)) writenew("1");else writenew("0");//not consider this invariant check
     writenew("\n");
     // t1.get_dataset().add(new ArrayList<String>());
      datasetRow++;
      for (int i = 0; i < s.length; i++)
		{
      //	t1.get_dataset().get(datasetRow).add(s[i].toString());
	  }
      
   
      
    }
  } // the loop continues until the indices cross
  
  if (!(c.compare(s[rightBound], s[leftInd])==0))
  {
	  temp = s[rightBound]; 
      int templeft=  s[leftInd]; 
      s[rightBound] =templeft;
      s[leftInd] = temp;
      out(" "+Arrays.asList(s));
     
  for (int f=0;f<s.length;f++) writenew(s[f].toString());
  /*if ((s[rightBound]==templeft)&&(s[leftInd]==temp)) 
  {writenew("1");} else { writenew("0");};*/
  writenew("\n");
  out(" "+Arrays.asList(s));
  
  //t1.get_dataset().add(new ArrayList<String>());
  //t1.convertToReducedFC(reduceAfterInsertions);// to call converToReducedFC function from the transform class
	// t1.printFC();
  datasetRow++;
 
  
  for (int i = 0; i < s.length; i++)
	{
	//  t1.get_dataset().get(datasetRow).add(s[i].toString());
	  }	  
  }
  quickSortStep3(s, c, leftBound, leftInd-1); // left recursive call
  //out(" "+Arrays.asList(s));
  //out(" pivot: "+s[rightBound] );
 quickSortStep3(s, c, leftInd+1, rightBound); // right recursive call
  //out(" " + Arrays.asList(s));
}
//static //end#fragment quickSortStep

public static void main (String[] argv) throws IOException {
	  
	datasetRow=0;
	 reduceAfterInsertions =1;
	start=true;
		ne=0;
		error=false;
		err=0;  
		if (file.exists()) file.delete();
		File file =new File(sort_filename);
//Transformer_B2_Experiment2 t1= new Transformer_B2_Experiment2();// to invoke an object from Transform class  
  out("Start your engines...");
  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// to read a data
  Random r = new Random();
  Comparator<Integer> c = new DefaultComparator<Integer>();// C will be used to call compare function() 
 // out("Enter number of elements:");
 // String num = in.readLine();// to read input line
  //int n = (new Integer(num)).intValue();// to convert the num into int
  int n=10;
  String cont;
  Integer[] B= {62, 1, 40, 60, 3, 79, 49, 90, 68, 97};
  //= {62, 1, 40, 60, 3, 79, 49, 90, 68, 97};B= {45, 14, 62, 29, 79, 73, 2, 45, 5, 12} ;
  //Integer[] B=new Integer[n];
  Integer[] A = new Integer[n];
  float msin=0f,qsin=0f,msout=0f,qsout=0f;
  long t;
    PositionList<Integer> C = new NodePositionList<Integer>();
    PositionList<Integer> D = new NodePositionList<Integer>();
    for (int i = 0; i < n; i++) {
      int x = r.nextInt(100);
      A[i] = new Integer(x);
      B[i] = new Integer(x);
      C.addLast(new Integer(x));
      D.addLast(new Integer(x));
    }
    out("Array-Based Sorting");
    out("Before: " + Arrays.asList(B));// to print the input array
    t = System.currentTimeMillis();
    msin = (System.currentTimeMillis()-t)/1000f;
    String correct = Arrays.asList(A).toString();
    //if (file.exists())file.delete();
    file = new File(sort_filename);
    t = System.currentTimeMillis();
    quickSort3(B, c);
    qsin = (System.currentTimeMillis()-t)/1000f;
    out(" " + Arrays.asList(B)); 
} 
public void initiate () throws IOException {
	datasetRow=0;
	 reduceAfterInsertions =1;
	start=true;
		ne=0;
		error=false;
		err=0;  
	  
	//Transformer_B2_Experiment2 t1= new Transformer_B2_Experiment2();// to invoke an object from Transform class  
	  out("Start your engines...");
	  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// to read a data
	  Random r = new Random();
	  Comparator<Integer> c = new DefaultComparator<Integer>();// C will be used to call compare function() 
	 // out("Enter number of elements:");
	 // String num = in.readLine();// to read input line
	  //int n = (new Integer(num)).intValue();// to convert the num into int
	  int n=10;
	  String cont;
	  Integer[] B= {62, 1, 40, 60, 3, 79, 49, 90, 68, 97};
	  //= {62, 1, 40, 60, 3, 79, 49, 90, 68, 97};B= {45, 14, 62, 29, 79, 73, 2, 45, 5, 12} ;
	  //Integer[] B=new Integer[n];
	  Integer[] A = new Integer[n];
	  float msin=0f,qsin=0f,msout=0f,qsout=0f;
	  long t;
	    PositionList<Integer> C = new NodePositionList<Integer>();
	    PositionList<Integer> D = new NodePositionList<Integer>();
	    for (int i = 0; i < n; i++) {
	      int x = r.nextInt(100);
	      A[i] = new Integer(x);
	      B[i] = new Integer(x);
	      C.addLast(new Integer(x));
	      D.addLast(new Integer(x));
	    }
	    out("Array-Based Sorting");
	    out("Before: " + Arrays.asList(B));// to print the input array
	    t = System.currentTimeMillis();
	    msin = (System.currentTimeMillis()-t)/1000f;
	    String correct = Arrays.asList(A).toString();
	  //  if (file.exists())file.delete();
	    file = new File(sort_filename);
	    t = System.currentTimeMillis();
	    quickSort3(B, c);
	    qsin = (System.currentTimeMillis()-t)/1000f;
	    out(" " + Arrays.asList(B)); 
	} 

private static void out (String s) {
  System.out.println(s);
}
}
