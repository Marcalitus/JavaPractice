package net.datastructures;
import java.util.*; 
import java.lang.*;
import java.io.*; 



import java.math.BigInteger;
import java.util.concurrent.*;
import java.util.function.*;



public class oneserialprimes  // correct pas de fausse anomalie: 100% correct
{
	boolean isAppend=false;
	static boolean start=true;
	static int ne=0;
	static boolean error=false;
	static int err=0;
	static int pass=0;
	static int nocycle=0;
	// creating graph edge 
	static String doubleprimes_filename = "C:\\Users\\AliJaoua\\Documents\\QUTAIBAH-MEETING-7-7-2020\\NEWVERSION-Implications\\oneserialprimes.csv";
	static File file = new File(doubleprimes_filename);

	private static void writenew(String value) {
			FileWriter fr = null;
		        try {
		            fr = new FileWriter(file,true);
		            if (!(start && (value=="\n"))) fr.write(value);
		            if((value!="\n")) {   
		            
		            fr.write(",");}
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

	


	static void primeduo() 
	{
	
    BigInteger one=new BigInteger("1");
    BigInteger two=new BigInteger("2");
    file = new File(doubleprimes_filename);
   Random r=new Random();Integer v1= (r.nextInt(200)+1);
   v1=v1+2;
    Integer q=0;
    BigInteger bx=one;
    BigInteger by=one;
		  for (int p=1;p<v1;p++)
		{   
		while (!(bx.isProbablePrime(30))) { bx=bx.add(one);}; q++;
		//if (p==(v1-1)) bx=bx.add(two.pow(5)).multiply(two);
		//by=bx.add(one);
		//while (!(by.isProbablePrime(30)))by= by.add(one);
		  writenew(q.toString());
			writenew(bx.toString());
			writenew("\n");
			System.out.println(q.toString()+ " " +bx.toString());
			bx=bx.add(one);;
			
			
		}		
			
	}

	// Driver Program 
	public static void main (String[] args) throws IOException 
	{
		{
			
			File file = new File("C:\\Users\\AliJaoua\\Documents\\QUTAIBAH-MEETING-7-7-2020\\NEWVERSION-Implications\\oneserialprimes.csv"); 
			if (file.exists()) file.delete();
			primeduo();
			System.out.println(); 
		}
		}
}