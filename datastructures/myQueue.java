package net.datastructures;
import java.util.*;
import java.io.Console;
public class myQueue 
{ 
    private int[] entry;		         // array to hold the elements of the queue - integers
	private  int  front;  			// position of the front of the queue 
	private  int  rear ;  			// position of the rear of the queue
	private int  size;  			// number of elements in the queue
	private final int  maxSize = 100; 	// assume maximum size of the queue is maxSize
	

	
	public myQueue() {       		 // construct an empty queue
            entry  =  new int [maxSize];
            size = 0;
            front = 0;
            rear = maxSize - 1 ;
        }					//end  of constructor



   	public void  enqueue(int item ){
	    if	(size == maxSize)  {				// check the array is full 
      		    System.out.println("array is full");    	// Print  a warning message 
		    System.exit(1);				// terminate the program 
            }
	    else { 			              		// insert  the element at the rear  of queue  
 		   if (rear  == maxSize - 1)    		// move  the rear  one position
                      	rear  =  0; 	     			//  in  clockwise direction
		   else  
              	        rear ++;
		 
		   entry[rear] = item;     		         // insert the item where rear  points in the array
		
		   size++; 	                                 // increase the value of size by 1
	         }
        }



	public void  dequeue (){
	    if  ( isEmpty() )  {				// check the queue is empty
      		  System.out.println("queue is empty");     	// Print  a warning message 
		  System.exit(1);				// terminate the program 
            }
	    else { 			               		// remove  the element at the front  of queue  
 		   if (front  == maxSize - 1)    		// move  the front  one position
                      	front  =  0; 	     			// in  clockwise direction
		   else  
              	         front ++;
		 
		   size --; 					// decrease the value of size by 1
	         }
	}




	public int  front (){
	    if  ( isEmpty () )  {				// check the queue is empty
      		  System.out.println("queue is empty");  	// Print  a warning message 
		  System.exit(1);		 		// terminate the program 
            }
	    else 
		return 	entry[front];				// return the front element
	
	    return -1;					
        }



	public boolean  isEmpty(){
           if (size == 0)
    		return true; 					// queue is empty
	   else
     		return false;					// queue is not empty 
	}



  
   
 } // end of class myQueue
