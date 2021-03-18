package net.datastructures;
import java.util.*; 
import java.lang.*;
import java.io.*; 


public class sumxy
{
	boolean isAppend=false;
	static boolean start=true;
	static int ne=0;
	static boolean error=false;
	static int err=0;
	static int pass=0;
	static int nocycle=0;
	// creating graph edge 
	static String sum_filename = "C:\\Users\\AliJaoua\\Documents\\QUTAIBAH-MEETING-7-7-2020\\NEWVERSION-Implications\\sumcorrect.csv";
	static File file;

	static void writeOnCsvFile2(String value)
	{

		if (value=="\n") 
			System.out.println(); 
		else System.out.print(value);
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

	class Edge implements Comparable<Edge> // we implemented this interface to use compare to() method
	{ 
		int src, dest, weight;
		boolean used; 

		// this compareTo function is to sort the edges depending on their weights
		public int compareTo(Edge compareEdge) 
		{ 
			return this.weight-compareEdge.weight; 
		} 
	}; 

	// A class to represent a subset for union-find 
	class subset 
	{ 
		int parent, rank; 
	};

	int X, Y; // v is the number of vertices and E is the number of edges
	//Edge edge[]; 
	// initialize the graph with V vertices and E edges
	sumxy(int x, int y) 
	{ 
		X = x; 
		Y = y; 
	} 

	
	// the main function to generate the KruskalMST
	int sum2(Integer x, Integer y) 
	{
		file = new File(sum_filename);
		boolean inject=true;int times=0;
		{

			if (x<y) {Integer t=x; x=y; y=t;};

			while (y!=0 )
			{
				String s=x.toString();//inversing printed traces x and y : 49% anomalies detected with delta=1.
				                                                         //10 % anomalies detected with delta=0.8
				                                                         // preventing endless loop
				writeOnCsvFile2(s);
				writeOnCsvFile2(",");
				s=y.toString();
				writeOnCsvFile2(s);
				writeOnCsvFile2(",");
				writeOnCsvFile2("\n");
				x++;   //15% anomalies, delta=0.99999, 100% if bound=10000, , included statement if (times<100) {x--;times++;} else x++;
				y--;
				/*if ((y%2==0)&&(y!=0)) // invariance preservation of old version, good, but if we add 
					//if (times<100) {x--;times++;} else x++;  delta=0.99999, 24% anomalies justified.
				{y=y-2;
				x=x+2;
				}*/
				//if (times<100) {x--;times++;} else x++;   //no anomalies detected
				//x=x+100; //70% anomalies if delta=0.8, 100% if Delta=0.99999
				//x=(x+50)%100;//38% anomalie delta=0.9; 51% if delta=0.99999
				//x=(x+87)%100; //69 % anomalies delta=0.9; 83%, with delta=0.99999
				//x=(x+37)%100; // 71% anomalies, delta=0.9, delta=0.99999, 100% if bound=10000, , included statement if (times<100) {x--;times++;} else x++;
				//x=(x+71)%100;//65% anomalies, delta=0.9; 77 % if delta=0.99999
				//x=(x+1000);  // 100% anomalies with delta=0.999999
				//x=x+10000;  // 100% anomalies with delta=0.999999
				//x=(x+7)%100; //57 % anomalies delta=0.9; 82% if delta=0.99999
				//x=x-20;  //60% anomalies, delta=0.8, 98% anomalies if delta=0.99999; if delta=0.99999 and bound control=100%.
				//x=x+100000; // with delta=0.8, also 57%, delta=0.99999 anomalies 100% and bound control
				//x=x*x*x;//x=x+x*2; // or x=x*x;  gives 100% anomalies. with precision delta=1. and delta=0.8, also 96%. if delta= 0.99999, 100% anomalies.
				//x=x*x;// anomalies 100%, delta 0.99999
				//if (times<100) {y++; times++;} else {if (y>0)y--;};  //100% anomalies,  delta=0.99999
				//x=-10;  // delta=0.8, also 58%; delta=0.99999 and bound control 100%
				//x=x*x; //100% anomaliles
				//x=x+2*x;  //0.8, 69% anomalies, 97% with delta=0.9; 100% with delta=0.99999
				//x=x-100000; //58%,  0.8 , 100% anomalies when delta= 0.9; 0.99999 anomalies 100%
				//x=x-10; //58%, o.8; 91% for delta=0.99999; with detection outofbound 98%
				//x=(x+10)%100;//more anomalies. 0.9, 62%; 0.99999 76% bound control:74%
				//x=0; // 100% if delta=0.99999
				//if (inject) {inject=false;y=(y+1000);}  //  no anomalies delta =0.8. Delta=0.99999 : 100%
				//if (inject) {inject=false;y=(y+x)%100;}  // 50% anomalies, delta=0.99999
				//if (inject) {inject=false;if (y>10) y=y+1000;} //100% anomalies 
				//x=10000000;//100% anomalies  out of bounds
			    //x=100; //100% anomalies, 0.999999
				if (inject) {inject=false;y=105;} //100% anomalies delta=0.99999, bound control
			}

		}
		String s=x.toString();
		writeOnCsvFile2(s);
		writeOnCsvFile2(",");
		s=y.toString();
		writeOnCsvFile2(s);
		writeOnCsvFile2(",");
		writeOnCsvFile2("\n");
		return x;
	}

	// Driver Program 
	public static void main (String[] args) throws IOException 
	{
		//for (int stat=0;stat<1000;stat++)
		{
			int x=0; // Number of vertices in graph 
			int y=0; // Number of edges in graph 
			File file = new File(sum_filename); 
			error=false;
			file.delete();
			sumxy sums = new sumxy(x, y); 
			Random r=new Random();
			System.out.println("New sum run");
			nocycle=0;
			int X=r.nextInt(50)+ 1;
			int Y=r.nextInt(50)+1;
			sums.sum2(13,2);
			System.out.println("Sum ends");
		} 
		
	}
}