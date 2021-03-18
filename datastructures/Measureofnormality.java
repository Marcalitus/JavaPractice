package net.datastructures;


public class Measureofnormality  {
	 static int n=0;

	 Measureofnormality (int  u)  
	{ 
	
	n=u;
	
	}

public int LIST ()  // I>=0;J>=0
	{int x=1;int y=2;int nombre=x;
	int i=1;double H=(Math.pow(2, 10))-1;
	nombre=(int) Math.floor(H);
	for (int k=0;k<(n-1);k++)
	{ int nombreI=nombre;
	  for (int l=k+1;l<n;l++)
	  {nombreI=nombre-(int) Math.floor((Math.pow(2, k)));
	  nombreI=nombreI-(int) Math.floor((Math.pow(2, l)));
	  System.out.println(nombreI);
	  } 
	}
	 System.out.println();
	return y;
	}


	public static void main(String[] args) 
	{   int x=10; 
		Measureofnormality  sum=new Measureofnormality  (x);
		int S1=sum.LIST();
		
	}
}
