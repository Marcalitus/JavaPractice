
import java.nio.file.Files
import java.util.ArrayList;
import java.util.Iteratior;
import java.util.Scanner;

public class controlledbrowsing {
	public int data;
	public String topic; 
	public String coursedescription
	public int semester;
	public String path; 
	public ArrayList<controlledbrowsing> parent;
	static controlledbrowsing parent; 
	static public boolean first=true; 
	public controlledbrowsing(int data, String topic ) {
		children = new ArrayList <controlledbrowsing>();
		//children of all of the subtopics; 
		this.data = data;
		this.topic = topic;
		
	}
	public controlledbrowsing addChild(controllledbrowsing)
	children.add(node);
	node.parent = this; 
	return this;
	 	
	
	public static void browse (controlledbvrowsing node )
	{
		Scanner sc=new Scanner (System.in); 
		for ( controlledbrowsing iter:node.children) 
	}
	
	
	public static void build (controlledbrowsing node ) { 
	Scanner sc = new Scanner ( System.in);
	int s = 1;
	while (s==1)
	{
		System.out.println("Do you have subtopic to add? Print 1 or 0"); 
		int id = sc.nextInt(); 
		String topicname= sc.next();
		controlledbrowsing node1; new controlledbrowsing (id, topic );
		node.addChildren(node1); 
		build (node1);
		
	}
	}
	
	public static void browse 
	
	
	
	public static void main(String[] args) {
		int i = 0;
		controlledbrowsing node = new controlledbrowsing(1, "Joua");
		{
			build(node);
			system.out.println(node.data + node.topic);
			browse(node,4); 
			i++
			
		}
	}
}
//
//Do you have subtopic to add? Print 1 if you yes, 0 if not joua
//1
//enter id and name of the subject
//2 courses
//do you have subtopi to add? Print 1 if yes, 0 if not courses 
//1
//enter id and name of the topic 
// 3 OOSD
// Do you have subtopic to add? print 1 if yes, 0 if not courses
//1
//enter id 
//4 programming-workshop 
// do you have subtopic to add print to add ? print 1 ... of programming workshop
//1
// enter id of the subtopic of the programming workshop 
// java course workshop 
// do you still have subtopic of the progrmaming workshop 1 or 0 java course workshop ? 
//0
//// do you have subtopic to add print to add ? print 1 ... of programming workshop
//1 
//// enter id of the subtopic of the programming workshop 
//python course workshop 
//// do you have subtopic to add print to add ? print 1 .or 0.. of programming python course workshop
// 0 
//// do you have subtopic to add print to add ? print 1 ... of programming workshop 
//0
//output 
//1
//	2courses
//.   3 OOSD
//.     4.Programming workshop
//.        5. Javacourse workshop
//.        6.Python course workshop

//A program that doesn't fail
//Associate the path
// Update/Edit/ Delete modules
// Save or quit the program //binary file or txt 
//Make the interface nicer 
// He wants it as a gui as a prefrence only
//give the path on each entry
//manage the browsing tree
//Genoristiy lecture 15 