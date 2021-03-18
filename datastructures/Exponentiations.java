package net.datastructures;

public class Exponentiations {
// Function Exponentiation is split to three parts: IndEXP  //(Trusted code in the enclave), the remaining code in untrusted //area including a  driver in the main).
static int x;  static int y; static int E = 1;  //input x,y, output E
void Exponentiation (int  u,  int v, int e)  //Constructor
{ x = u; y = v; E = e;}
int  IndExp() { //Function calculating x power y to E
while (y != 0) 
{  
if (y!=0)
{

// BEGINNING OF A TRUSTED CODE IN THE ENCLAVE 
if ((y % 2) == 0) 
{x = x*x ; y = y/2;}
else 
if ((y % 2) == 1)
{ E=x*E; y=y-1;}
}
else 
{y=1; // reverse an action if y==0
E=E/x;
}	
// END OF A TRUSTED CODE IN THE ENCLAVE
}// end of while loop.
return E;
}
public static void main(String[] args) // THE DRIVER
{Exponentiation Exp=new Exponentiation (2, 7,1);
int x0=2, y0=7;
int E1=Exp.IndExp();// First call of function IndExp
int E2=Exp.IndExp();// Second call of function IndExp
while (E1!=E2) { // CHECKING THE GOAL
E1=E2;
E2=Exp.IndExp();
}; 
System.out.println(" x0= "+x0+ " y0= "+y0 + " E= "+E1);
}}
