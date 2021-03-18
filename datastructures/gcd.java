package net.datastructures;

public class gcd {
	static int F(int n,int m)
	{
	while (n!=m) 
	{ if (n>m)n=n-m;
	else m=m-n;
	}
	return n;
	}
	
	public static void main(String[] args) 
{
//gcd object=new gcd();
int result= F(12,18);
System.out.println(result);
}


}
