package net.datastructures;

public class Factorial {
 int result=5;
 int m=5;
 int F(int n)
	{
	 if ((n==0)||(n==1)) return 1;
	 else return (n*F(n-1));
	}
	
public static void main(String[] args) 
{
Factorial amin=new Factorial();
int m=5;
for (int i=1;i<10;i++)
{
int result= amin.F(i);
System.out.println(result);
}
}
}
