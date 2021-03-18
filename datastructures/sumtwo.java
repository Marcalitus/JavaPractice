package net.datastructures;

public class sumtwo {
	
		int F(int n,int m)
		{
		while (n!=0) 
		{ n--;
		m++;
		}
		return m;
		}
		
		public static void main(String[] args) 
	{
	sumtwo object=new sumtwo();
	int result= object.F(12,18);
	System.out.println(result);
	}


	}

