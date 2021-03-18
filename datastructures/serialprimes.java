package net.datastructures;
import java.util.*; 
import java.lang.*;
import java.io.*; 



import java.math.BigInteger;
import java.util.concurrent.*;
import java.util.function.*;



public class serialprimes  // correct pas de fausse anomalie: 100% correct
{
	boolean isAppend=false;
	static boolean start=true;
	static int ne=0;
	static boolean error=false;
	static int err=0;
	static int pass=0;
	static int nocycle=0;
	// creating graph edge 
	static String kruskal_filename = "C:\\Users\\AliJaoua\\Documents\\QUTAIBAH-MEETING-7-7-2020\\NEWVERSION-Implications\\serialprimes.csv";
	static File file = new File(kruskal_filename);

	static void writeOnCsvFile(String value)
	{
		
		if (value=="\n") System.out.println(); 
		else System.out.print(value+",");
		FileWriter fr = null;
		try {
			fr = new FileWriter(file,true);
			if ( (value=="\n")) fr.write("\n");
			else if((value!="\n")) {
			//if (value=="0")fr.write("1");else fr.write("0"); // Inversion 100% anomalies detected.
		    // instead of value="1";// wrong value added
			fr.write(value);
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
	//file.delete();
    file = new File(kruskal_filename);
    Random r=new Random();Integer v1= (r.nextInt(2000)+1)%2000;
    Integer v2=(r.nextInt(2000)+1)%2000;
    int min=0;int max=0; int npairs=0;
    if (v1<v2) {min=v1; max=v2;} else {min=v2;max=v1;}
    if ((max-min)<50) {min=1;max=50;};
		{  for (int p=1;p<2000;p++)
		{   
			Integer x=p;
		    Integer y=p+1;
		    BigInteger bx=new BigInteger(x.toString() );
		    BigInteger by=new BigInteger(y.toString() );
		if (bx.isProbablePrime(30)||by.isProbablePrime(30))
		    {writeOnCsvFile(Integer.toString(x));
			writeOnCsvFile(Integer.toString(y));
			writeOnCsvFile("\n");
			npairs++;
			}
			
			
		}		
			
		}
		System.out.println("npairs = "+npairs);
	}

	// Driver Program 
	public static void main (String[] args) throws IOException 
	{
		{
			
			File file = new File("C:\\Users\\AliJaoua\\Documents\\QUTAIBAH-MEETING-7-7-2020\\NEWVERSION-Implications\\serialprimes.csv"); 
			if (file.exists()) file.delete();
			primeduo();
			System.out.println(); 
		}
		}
}