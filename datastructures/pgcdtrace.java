package net.datastructures;

 class pgcdtrace 
{
	
	 
	public Integer x=new Integer(36);
	public Integer y=new Integer(24);
	public Integer z;
	
public	void f()
	{	
	//System.out.println(x);
	//System.out.println(y);
while (!(x==y))
{
	if (x>y) x=x-y;
	else y=y-x;
	System.out.println("x= "+x+" y= "+y);
}
}

 public static void main (String[] argv) 
 {
	pgcdtrace g=new pgcdtrace();
	g.f();
 }
}

