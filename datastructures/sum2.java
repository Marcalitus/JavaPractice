package net.datastructures;

public class sum2 {
static int x; static int y; static int S = 0; static boolean inject=false; static boolean Cloop=false;
static int count=0; 
sum2(int u, int v, int e)
{
x = u; y = v;S = e;
}
public int SUM3 () // I>=0;J>=0
{while ((x!=0)||(inject))
{

count++;
if ((count>0)&&(count<=4))    {inject=true;}
if (x>=0)
{
x--; //inside the enclave- Trusted area y++;//inside the enclave- Trusted area count++;
y++;
}
if (x<0)
{ y--;x++;  System.out.println("recovering");
};
if (count>7) inject=false;
}
inject=false;
return y;
}
public static void main(String[] args)
{
sum2 sum=new sum2 (2, 3,1);
int S1=sum.SUM3();
System.out.println("x= "+x+ " y= "+y + " S1= "+S1);
int S2=sum.SUM3();
System.out.println("x= "+x+ " y= "+y + " S2= "+S2);
while (S1!=S2) {
S1=S2; S2=sum.SUM3();
System.out.println("x= "+x+ " y= "+y + " S1= "+S1); System.out.println("x= "+x+ " y= "+y + " S2= "+S2);
};
System.out.println(" x= "+x+ " y= "+y + " S= "+S2);
}
}
