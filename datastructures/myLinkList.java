package net.datastructures;
import java.io.Console;
public class myLinkList
{
	
	private int size ;
	private myNode firstNode,currentNode, nodeBefore, nodeAfter, nodeToRemove ;
  	
	
	public myLinkList()
	{	
		size = 0;
		
	} // end constructor


	public boolean isEmpty ()
	{
   		return (size == 0);
	}


	private myNode getNodeAt(int p)   //Returns a reference to the node at  position p
	{	
		currentNode = firstNode;
						
		for (int counter = 1; counter < p; counter++)
			currentNode = currentNode.next;
		return currentNode;
	} // end getNodeAt	


	public boolean add(int p, int item) 
	{	
		boolean isSuccessful = true;

		if ((p >= 1) && (p <= size+1)) 
		{	myNode newNode = new myNode(item);
			if (isEmpty() || (p == 1)) 		// case 1
			{	newNode.next = firstNode;
				firstNode = newNode;
			}
			else           				 	// case 2: newPosition > 1, list is not empty
			{	myNode nodeBefore = getNodeAt(p-1);
				myNode nodeAfter = nodeBefore.next;
				newNode.next = nodeAfter;
				nodeBefore.next = newNode;
			} 
		
			size++;
		}	else						//position p is not valid
			isSuccessful = false;
	
		return isSuccessful;
	} // end add

boolean isThere(int value)
{myNode Temp=firstNode;
boolean found=false;

 while ((Temp!=null)&&!found)
		 {if (Temp.data==value) found=true;else Temp=Temp.next;}
 return found;
	}

	public boolean remove(int p)
	{
	
		boolean isSuccessful = true;
		if (!isEmpty() && (p >= 1) && (p <= size))
		{
			if (p == 1)        // case 1: remove first entry
			{
			
				firstNode = firstNode.next;

			}else                           // case 2: givenPosition > 1
			{       myNode nodeBefore = getNodeAt(p - 1);
				myNode nodeToRemove = nodeBefore.next;
				myNode nodeAfter = nodeToRemove.next;
				nodeBefore.next = nodeAfter; 
			
			}  // end if
		
			size--;
		}
		else isSuccessful = false;   			//position p is not valid
	
		return isSuccessful;
	

	}  // end remove

	
	public int  display()		// Displays all the items that 
	{				// are in the list in the order in which they occur.
		
		currentNode = firstNode;
						
		for (int counter = 1; counter <= size; counter++)
		{
			System.out.println(currentNode.data);
			currentNode = currentNode.next;
                }    
		return -1;
	}  // end display	


}