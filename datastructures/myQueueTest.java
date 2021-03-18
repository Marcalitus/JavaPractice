package net.datastructures;
import java.util.*;
import java.io.Console;

public class myQueueTest{
	public static void main(String[] args)
	{
		
		myQueue q = new myQueue() ;
		q.enqueue(5);
              	
		System.out.println("Front is: "+ q.front());
                 q.enqueue(3);
                 q.enqueue(7);
             
		System.out.println("Front is:"  + q.front());
                  q.dequeue();
		  q.dequeue();
		System.out.println("Front: " + q.front());
         }
   }

