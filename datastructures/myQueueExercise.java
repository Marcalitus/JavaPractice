package net.datastructures;
import java.util.*;
import java.io.Console;

public class myQueueExercise{
	public static void main(String[] args)
	{
	  Scanner dataInput = new Scanner(System.in);     
         int element;
	 int choice;
         
         boolean quit = false;
 
          myQueue q = new myQueue() ; 

		do
		{						//display menu
		  System.out.println("Enter the number of your option:");   
      		  System.out.println("1. Insert an element");
      		  System.out.println("2. Delete an element");
      		  System.out.println("3. Print the front");
                  System.out.println("4. Exit");
	   		
		   choice = dataInput.nextInt();	// Get user choice

							
			switch(choice){				
				case 1:	System.out.print("Enter the element to insert:");
					element = dataInput.nextInt();
                                        q.enqueue(element);
					break;

				case 2:	if (q.isEmpty())
                                           System.out.println("queue is empty");
					else q.dequeue();	
					break;

				case 3:	if (q.isEmpty())
                                           System.out.println("queue is empty");
					else System.out.println("front element is:" + q.front());	
					break;
				
				case 4:	quit = true;
					break;

				default:	// Invalid choice
					System.out.println("Invalid menu choice, try again."); 
			}
		} while (!quit);

		
         }
   }

