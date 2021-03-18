package net.datastructures;

public class Exponentiation {
	 static int x;  static int y; static int E = 1;  
	 static boolean inject=false;
	 static boolean Cloop=false;
	Exponentiation(int  u,  int v, int e)  
	{ 
	x = u;
	y = v;
	E = e;
	}
	public  int  IndExp()   
	{
		boolean NCloop=true;
	while (((y != 0)&& !inject) || (Cloop))
	{   
		if (Cloop) {NCloop=false;Cloop=false;}
	 //do the error only one time 
	// To be included in the enclave area
	if (y!=0)// This condition is added to preserve the invariant in case of endless loop 
	{  //corresponding to y==0
	if ((y % 2) == 0) {
	x = x*x ; y = y/2;
	}
	else { E=x*E;
	       y=y-1;
	     }
	
	}
	else {y=1; // reverse an action if y==0
	   E=E/x;
	   NCloop=false;
	   System.out.println("recovering x"+ x + " E = "+ E);
	}
	
	//end of enclave area
	// beginning of untrusted area
		if ((y%2)==0) inject=true; // leaving the loop for each odd value of y
	
	}
	inject=false;Cloop=false;
	return E;
}

	public static void main(String[] args) 
	{
		Exponentiation Exp=new Exponentiation (2, 7,1);
		int E1=Exp.IndExp();
		System.out.println("x= "+x+ " y= "+y + " E1= "+E);
		int E2=Exp.IndExp();
		System.out.println("x= "+x+ " y= "+y + " E2= "+E);
		while (E1!=E2) {
			E1=E2;
			E2=Exp.IndExp();
			System.out.println("x= "+x+ " y= "+y + " E1= "+E);
			System.out.println("x= "+x+ " y= "+y + " E2= "+E);
		};
		System.out.println(" x= "+x+ " y= "+y + " E= "+E);
		
	}
}

/*
x= 2 y= 6 E1= 2
x= 4 y= 2 E2= 8
x= 16 y= 0 E1= 128
x= 16 y= 0 E2= 128
x= 16 y= 0 E1= 128
x= 16 y= 0 E2= 128
 x= 16 y= 0 E= 128 
 // Part in bold here below should be stored in the enclave
int SUM (int &I, int &J)  // I>=0;J>=0
{while (I!=0)
  { I--; 
   J++;
  while (I<0) { I++;J--};// if we enter by mistake in the loop even if I=0;
}
Return J;
}

 */
