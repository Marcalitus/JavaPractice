package net.datastructures;
import java.util.Random;
import java.lang.Math;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


public class expmod {
	     public Integer x=new Integer(35);
	     public Integer y=new Integer(10);
	     public Integer q=new Integer(127);
	  // r=(x power y)%q  
	
	     public void selectrandom()
	     {
	    	 Random random = new Random();
	         
	    	 {
	    	      x =Math.abs( (random.nextInt()%100));
	    	      y= Math.abs( (random.nextInt()%100));
	    	      q= Math.abs( (random.nextInt()%100));
	    	      
	    	      
	    	      //System.out.println("x="+ x + "y="+ y+ "q="+q);
	    	 }


	    //	Read more: https://javarevisited.blogspot.com/2013/05/how-to-generate-random-numbers-in-java-between-range.html#ixzz6AEBDDzRB	 
	     }
	     public   void f()
	     {    
	     selectrandom();
	     
	     int i=1;
	     int r=1;
	     
	while ((i<=y))
	{   System.out.println(i+", "+x+" ,"+r+", "+y+", "+q);
	     r=r*x;
	     r=r%q-x;
	    
	     i++;
	    
	}
	 System.out.println(i+", "+x+" ,"+r+", "+y+", "+q);
	
	//System.out.println(i+" "+x+" "+r+" "+y+" "+q);
	}

	     /*public WriteFile( String file_path , boolean append_value ) {

	    	 path = file_path;
	    	 append_to_file = append_value;

	    	 }*/

	public static void main (String[] argv) 
	 {
	     expmod g=new expmod();
	     g.f();
	}
	}
