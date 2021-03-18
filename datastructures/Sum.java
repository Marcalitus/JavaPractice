package net.datastructures;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.*; 
import java.lang.*;
import java.io.*; 


public class Sum {
	 static int x;  static int y; static int S = 0;  
	 static boolean inject=false;
	 static boolean Cloop=false;
	 static int count=0;
     static boolean start =true;
     static boolean error=false;
     
Sum(int  u,  int v, int e)  
	{ 
	x = u;
	y = v;
	S = e;
	}
static File file = new File("D:\\Users\\jaoua\\workspace3\\datastructures\\sum.csv");
//static FileWriter fr = null;
static void writeOnCsvFile(String value) {
	//if(value=="*") 
//	if (value=="\n") System.out.println(); 
		//	else System.out.print(value+",");
		//File file = new File("D:\\Users\\jaoua\\workspace3\\datastructures\\Kruskalerrone.csv");
		// file.delete();
		// file = new File("D:\\Users\\jaoua\\workspace3\\datastructures\\Kruskalerrone.csv");
		FileWriter fr = null;
	        try {
	            fr = new FileWriter(file,true);
	            fr.write(value);
	           
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
public int SUM ()  // I>=0;J>=0
{
error=false;
file.delete();
file = new File("D:\\Users\\jaoua\\workspace3\\datastructures\\sum.csv");

	while ((x!=0))
	  { // extra loops
	   x--; 
	   if (y%2==0) y++;else y--;
	   count++;
	   //System.out.println(x+","+y);
	   writeOnCsvFile(Integer.toString(x));
	   writeOnCsvFile(",");
	   writeOnCsvFile(Integer.toString(y));
	   writeOnCsvFile("\n");
	  
	  
	}
	//inject=false;
	return y;
	}


	public static void main(String[] args) throws IOException 
	{   File file = new File("D:\\Users\\jaoua\\workspace3\\datastructures\\sum.csv"); 
	if (file.exists()) file.delete();
		Random r = new Random();
		Sum sum=new Sum (r.nextInt(100), r.nextInt(100),1);
		int S1=sum.SUM();
		System.out.println(" x= "+x+ " y= "+y);
	//fr.close();	
	}
}

/*
recovering
recovering
recovering
recovering
recovering
recovering
recovering
recovering
recovering
recovering
recovering
recovering
recovering
recovering
recovering
x= 0 y= 5 S1= 5
x= 0 y= 5 S2= 5
 x= 0 y= 5 S= 5

 */