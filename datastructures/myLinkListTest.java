
package net.datastructures;
import java.util.*;
import java.io.Console;
public class myLinkListTest{
	public static void main(String[] args)
	{
	  Scanner dataInput = new Scanner(System.in);     // setup data input and declare variables

         int newitem;
	 int position;
	 int choice;
         
         boolean quit = false;
 
          myLinkList L = new myLinkList() ; 

		do
		{						              //display menu
		  System.out.println("Enter the number of your option:");   
      		  System.out.println("1. Add an item");
      		  System.out.println("2. Remove an item");
      		  System.out.println("3. Print");
	   	  System.out.println("4. quit");
	
		   choice = dataInput.nextInt();			// Get user choice

							
			switch(choice){				
				case 1:	System.out.print("Enter the element to insert:");
					newitem = dataInput.nextInt();
					System.out.print("Enter the position:");
					position = dataInput.nextInt();
                                        if ( L.add(position, newitem))
						System.out.println("The element is added in the List");
					else
						System.out.println("Add is not success");
					break;

				case 2:	System.out.print("Enter the position:");
					position = dataInput.nextInt();
				 	if (L.remove(position))
						System.out.println("The element is removed");
					else
						System.out.println("Remove is not success");	
					break;
			

				case 3:	if (L.isEmpty())
						System.out.println("The list is empty");
					else{	
					     	System.out.println("The list elements are:");
						L.display();
					     }
					break;
					
	
				case 4:	quit = true;
					break;

				default:	// Invalid choice
					System.out.println("Invalid menu choice, try again."); 
			}
		} while (!quit);

		
         }
   }

