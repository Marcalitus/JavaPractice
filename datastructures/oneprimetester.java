// JAVA – TESTER
package net.datastructures;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/* This code works on the idea of checking if the two formal contexts cover the same information or not. 
 * 
 * Then we insert each object from the second FC into the first FC and apply reduction to see if the newly inserted
 * object gets reduced by the existing objects of the first FC. We repeat this for all objects of the second FC.
 * 
 * If we have a scenario that all objects of the second FC are reduced by the objects of the first FC, then we can
 * say that that the first FC covers the same information as that of the second FC.
 * 
 * Otherwise, if we have some objects of the second FC that do not get reduced by the first FC, then the second FC
 * has some new information which does not exist in the first FC.
 * 
 * The code only checks consistency in one directions (fc1 -> fc2) 
 * 
 * After this, the remaining objects in the second FC (which did not get reduced by the first FC) will get appended
 * into the first FC and then written out to a file  
 *  
 * 
 */
public class oneprimetester 
{
	// static variables don't need a constructor to be initialized

	// another 2d arraylist for the formal context
	private static ArrayList<ArrayList<Double>> formalContext1 = new ArrayList<ArrayList<Double>>();
	private static ArrayList<ArrayList<Double>> formalContext2 = new ArrayList<ArrayList<Double>>();

	static int Totalanomalies=0;
	static int steps=0;
	static int truenormal=0;
	static int falseanomaly=0;
	static int prevcycle=0;
	static int newcycle=0;
	static int normal=0;
	static int xm=0;
	static int totalfc2size=0;
	static double average=0;
	static double densite=0;

	// this one saves the object names in the first column of the the formal context table
	// eg: obj1-obj2,obj1-obj3, obj2-obj3, etc...
	private static ArrayList<String> fcObjectNames1 = new ArrayList<String>();
	private static ArrayList<String> fcObjectNames2 = new ArrayList<String>();

	// A simple arraylist to save the attribute names separately from the FC file.
	// this is only used if we are reading the FC file directly, else the above variable
	// already has the names if we read the CSV file
	private	static ArrayList<String> fcAttributeNames1;
	private	static ArrayList<String> fcAttributeNames2;

	// These 2d arraylists store copies of the original formal contexts as reference
	private static ArrayList<ArrayList<Double>> fc1_original = new ArrayList<ArrayList<Double>>();
	private static ArrayList<ArrayList<Double>> fc2_original = new ArrayList<ArrayList<Double>>();

	public static void main(String[] args) throws ClassNotFoundException 
	{
		System.out.println("RUNNING squaretester");
		ArrayList<Integer> reducedRows;
		boolean FC2reducedByFC1;
		Instant programStart = Instant.now();
		LocalDateTime ldt = LocalDateTime.ofInstant(programStart, ZoneId.systemDefault());
		System.out.printf("Started on %s %d %d at %d:%d%n", ldt.getMonth(), ldt.getDayOfMonth(),
				ldt.getYear(), ldt.getHour(), ldt.getMinute());

		prevcycle=newcycle;
		// ------------------------------------------------------------------------------------------------------------
		// SET CODE PARAMETERS BELOW
		// ------------------------------------------------------------------------------------------------------------
		String inputFile1 ="C:\\Users\\AliJaoua\\Documents\\QUTAIBAH-MEETING-7-7-2020\\NEWVERSION-Implications\\oneSlearneroutputprimes_FC.csv";
		String inputFile2 = "C:\\Users\\AliJaoua\\Documents\\QUTAIBAH-MEETING-7-7-2020\\NEWVERSION-Implications\\oneserialprimes_FC.csv";
		String outputFilename ="C:\\Users\\AliJaoua\\Documents\\QUTAIBAH-MEETING-7-7-2020\\NEWVERSION-Implications\\oneanotherfile.csv";
		String[] S=new String[2];S[0]="L"; S[1]="1";

		// Generate Kruskal trace program 
		try {
			oneserialprimes.main(args);
		} catch (IOException e) {
			e.printStackTrace();
		}

		double delta = 0.9;	// delta to use for reduction

		// Take Kruskal trace dataset and transform into reduced FC 
		// this code uses fuzzy formal context comparing to max values
		String dataset_filename = "C:\\Users\\AliJaoua\\Documents\\QUTAIBAH-MEETING-7-7-2020\\NEWVERSION-Implications\\oneserialprimes.csv";
		String settings_filename = "C:\\Users\\AliJaoua\\Documents\\QUTAIBAH-MEETING-7-7-2020\\NEWVERSION-Implications\\onestest_settings_primes.csv";
		Transformer_Exp23_updatedoneprime.run_code(dataset_filename, settings_filename);



		readFCFiles(inputFile1, inputFile2);

		System.out.println("Size of FC files: " + formalContext1.size() + "," + formalContext2.size());

		// if the input FCs have not been reduced already, run reduction to prevent any problems in the later stages of the code
		// This wouldn't affect FCs that have already been reduced
		reducedRows = LukasiewiczReduction(formalContext1,delta);
		removeRows(formalContext1, fcObjectNames1, reducedRows);
		reducedRows = LukasiewiczReduction(formalContext2,delta);
		removeRows(formalContext2, fcObjectNames2, reducedRows);

		//printBothFCs();

		//if (1==1) System.exit(0);

		FC2reducedByFC1 = reduceFC2usingFC1(delta);

		newcycle=Kruskal_correct.pass;
		if ((!FC2reducedByFC1))	// second FC has objects that were not reduced by first FC 
		{
			writeFCtoFile(outputFilename, formalContext1, fcObjectNames1, fcAttributeNames1);
			Totalanomalies++;
		} else if (newcycle!=prevcycle) normal++;
		System.out.println("Total Anomalies : " +Totalanomalies+ " total normal= "+ normal);    
		Instant end = Instant.now();
		System.out.println("Time Taken : " + Duration.between(programStart, end));
		System.out.println("DONE");

		steps=1; int N=10;
		for (int i=0;i<N;i++)	// set number of trials here
		{
			System.out.println("_________________________");
			System.out.println("STARTING TEST " + (i+1));
			steps++;
			prevcycle=newcycle;
			String inputFile4 = "C:\\Users\\AliJaoua\\Documents\\QUTAIBAH-MEETING-7-7-2020\\NEWVERSION-Implications\\oneSlearneroutputprimes_FC.csv";
			String inputFile5 = "C:\\Users\\AliJaoua\\Documents\\QUTAIBAH-MEETING-7-7-2020\\NEWVERSION-Implications\\oneserialprimes_FC.csv"; 
			String outputFilename6 = "C:\\Users\\AliJaoua\\Documents\\QUTAIBAH-MEETING-7-7-2020\\NEWVERSION-Implications\\oneanotherfile.csv";
			;

			// run trace program
			try {
				oneserialprimes.main(args);
			} catch (IOException e) {
				e.printStackTrace();
			}

			fc1_original.clear();
			fc2_original.clear();
			formalContext1.clear();
			formalContext2.clear();
			fcObjectNames1.clear();
			fcObjectNames2.clear();
			fcAttributeNames1.clear();
			fcAttributeNames2.clear();

			// transform trace to reduced FC
			Transformer_Exp23_updatedoneprime.run_code(dataset_filename, settings_filename);

			// ------------------------------------------------------------------------------------------------------------
			// BEGIN PROCESSING CODE BELOW, SEE ABOVE FOR MODIFYING PARAMETERS
			// ------------------------------------------------------------------------------------------------------------

			readFCFiles(inputFile4, inputFile5);

			System.out.println("Size of FC files: " + formalContext1.size() + "," + formalContext2.size());

			// if the input FCs have not been reduced already, run reduction to prevent any problems in the later stages of the code
			// This wouldn't affect FCs that have already been reduced
			reducedRows = LukasiewiczReduction(formalContext1,delta);
			removeRows(formalContext1, fcObjectNames1, reducedRows);
			reducedRows = LukasiewiczReduction(formalContext2,delta);
			removeRows(formalContext2, fcObjectNames2, reducedRows);

			FC2reducedByFC1 = reduceFC2usingFC1(delta);

			newcycle=Kruskal_correct.pass;
			if ((!FC2reducedByFC1))	// second FC has objects that were not reduced by first FC 
			{
				writeFCtoFile(outputFilename6, formalContext1, fcObjectNames1, fcAttributeNames1);
				Totalanomalies++;
			} else if (newcycle!=(prevcycle)) normal++;
			System.out.println("Total Anomalies : " +Totalanomalies+ " total normal= "+ (N-Totalanomalies));
			end = Instant.now();
			System.out.println("Time Taken : " + Duration.between(programStart, end));
			System.out.println("DONE");

		}
		System.out.println("Early Anomalies : " + (double)xm/totalfc2size );	
		if (Totalanomalies!=0) System.out.println("Average Early Anomalies : " + (double)average/Totalanomalies );	
		System.out.println("detection density : " + (double)densite/totalfc2size );	
	}

	/* This function just dumps the complete FC contents having 1s and 0s without
	 * any attribute or object names into a file for file matching using other software
	 */
	public static void dumpFCtoFile(String filename)
	{
		System.out.println("Dumping FC to File : " + filename);
		int i,j;
		double value;
		FileWriter writer = null;
		String line;
		int FC_size = formalContext1.size();

		try 
		{
			line = "";

			// Create the file reader
			writer = new FileWriter(filename);

			for (i = 0; i < FC_size; i++)
			{
				line = "";
				for (j = 0; j<formalContext1.get(i).size(); j++)
				{
					value = (formalContext1.get(i).get(j));
					line = line.concat(value + "");
				}
				line = line.concat("\n");
				//System.out.print("FC line "+ (i+1) + "= " + line);
				writer.append(line);
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in dumpFCtoFile");
			e.printStackTrace();
		} 
		finally
		{
			try 
			{
				writer.flush();
				writer.close();
			} 
			catch (IOException e) 
			{
				System.out.println("IO Exception in dumpFCtoFile");
				e.printStackTrace();
			}
		}
	}

	public static void readFCFiles(String fileName1, String fileName2) 
	{
		System.out.println("Reading Formal Context files : " + fileName1 + " and " + fileName2);
		fcAttributeNames1 = new ArrayList<String>();
		BufferedReader fileReader = null;
		String[] tokens;
		int i, fcRow;
		double value;

		try 
		{
			/////////// READING THE FIRST FORMAL CONTEXT FILE
			String line = "";
			// Create the file reader
			fileReader = new BufferedReader(new FileReader(fileName1));
			// Read the FC file header to read columns names or skip it
			// we can use this to skip the header line containing the column names if required
			line = fileReader.readLine();
			tokens = line.split(",|;");
			if (tokens.length > 0)
			{
				// the first column has no attribute name since it has the object names
				// so skip the first token since its an empty string (i=1 instead of 0)
				for (i = 1; i < tokens.length; i++)
				{
					//System.out.println(tokens[i]);
					fcAttributeNames1.add(tokens[i]);
				}
			}

			fcRow = 0;			
			while ((line = fileReader.readLine()) != null) 
			{
				tokens = line.split(",|;");  // some files use semicolons as delimiters, so we put support for both , and ;
				if (tokens.length > 0) 
				{
					formalContext1.add(new ArrayList<Double>());
					fcObjectNames1.add(tokens[0]);
					for (i = 1; i < tokens.length; i++)
					{
						//System.out.println(tokens[i]);
						value = Double.parseDouble(tokens[i]);
						formalContext1.get(fcRow).add(value);
					}
				}
				fcRow++;
			}		
			fileReader.close();


			///////////// READING THE SECOND FORMAL CONTEXT FILE
			fcAttributeNames2 = new ArrayList<String>();
			line = "";
			fileReader = new BufferedReader(new FileReader(fileName2));
			// Read the FC file header to read columns names or skip it
			// we can use this to skip the header line containing the column names if required
			line = fileReader.readLine();
			tokens = line.split(",|;");
			if (tokens.length > 0)
			{
				// the first column has no attribute name since it has the object names
				// so skip the first token since its an empty string (i=1 instead of 0)
				for (i = 1; i < tokens.length; i++)
				{
					//System.out.println(tokens[i]);
					fcAttributeNames2.add(tokens[i]);
				}
			}

			fcRow = 0;			
			while ((line = fileReader.readLine()) != null) 
			{
				tokens = line.split(",|;");  // some files use semicolons as delimiters, so we put support for both , and ;
				if (tokens.length > 0) 
				{
					formalContext2.add(new ArrayList<Double>());
					fcObjectNames2.add(tokens[0]);
					for (i = 1; i < tokens.length; i++)
					{
						//System.out.println(tokens[i]);
						value = Double.parseDouble(tokens[i]);
						formalContext2.get(fcRow).add(value);
					}
				}
				fcRow++;
			}

			// now that we have loaded both FCs into memory, check if they have the same number of columns
			// because that is the key requirement that will allow the rest of the code to run
			if (formalContext1.get(0).size() != formalContext2.get(0).size())
			{
				System.out.println("WARNING !!! FORMAL CONTEXTS DO NOT HAVE THE SAME NUMBER OF COLUMNS, EXITING ");
				//printBothFCs();
				System.exit(0);   //REMOVED 25/7/2020
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Error in FCFileReader !!!");
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				fileReader.close();
			} 
			catch (IOException e) 
			{
				System.out.println("Error while closing fileReader of FC!!!");
				e.printStackTrace();
			}
		}
	}

	// this function performs a complete deep copy of the source FC into destination FC while replacing all the contents of the destination FC
	public static void deepCopyFC(ArrayList<ArrayList<Double>> src_fc, ArrayList<String> src_fcObjNames,
			ArrayList<ArrayList<Double>> dest_fc, ArrayList<String> dest_fcObjNames)
	{
		int i,j;

		for (i = 0; i < src_fc.size(); i++)
		{
			dest_fc.add(new ArrayList<Double>());
			dest_fcObjNames.add(src_fcObjNames.get(i));
			for (j = 0; j<src_fc.get(i).size(); j++)
			{
				dest_fc.get(i).add(src_fc.get(i).get(j));
			}
		}
	}

	// The reduction function only gives us a list of rows to remove
	// This function actually does the grunt work of deletion of rows
	public static void removeRows(ArrayList<ArrayList<Double>> formalContext, 
			ArrayList<String> fcObjectNames,
			ArrayList<Integer> rowIndexes)
	{
		int i, removeObjectIndex ;		
		// we go in reverse order since removing an object from FC in the beginning
		// would reduce the indexes of the remaining objects by one and this would 
		// in turn invalidate the other rowIndexes we get from the reduction.
		// Here LukasiewiczReduction already gives us reverse indexes so we iterate
		// in standard order

		for (i=0; i<rowIndexes.size(); i++)
		{
			removeObjectIndex = rowIndexes.get(i);
			formalContext.remove(removeObjectIndex);
			fcObjectNames.remove(removeObjectIndex);
		}
	}


	// The code basically moves a group of objects from source FC to destination FC
	// It does this by first going through the source FC and copying the rows into the destination FC
	// Then, it will remove those rows from the source FC
	public static void moveRows(ArrayList<ArrayList<Boolean>> src_fc, ArrayList<String> src_fcObjNames,
			ArrayList<ArrayList<Boolean>> dest_fc, ArrayList<String> dest_fcObjNames, ArrayList<Integer> rowIndexes)
	{
		int i, removeObjectIndex ;		
		//System.out.println("Moving the reduced Rows of FC");

		for (i=0; i<rowIndexes.size(); i++)
		{
			removeObjectIndex = rowIndexes.get(i);
			dest_fcObjNames.add(src_fcObjNames.get(removeObjectIndex));
			dest_fc.add(src_fc.get(removeObjectIndex));
		}

		// We go in reverse order since removing an object from FC in the beginning would reduce the indexes  
		// of the remaining objects by one and this would in turn invalidate the other rowIndexes 
		for (i=0; i<rowIndexes.size(); i++)
		{
			removeObjectIndex = rowIndexes.get(i);
			src_fc.remove(removeObjectIndex);
			src_fcObjNames.remove(removeObjectIndex);
		}
	}


	// The most important function around which this whole code file was created
	// This 
	public static boolean reduceFC2usingFC1(double delta)
	{
		ArrayList<String> fc1_ObjNames_copy = new ArrayList<String>();
		ArrayList<String> fc2_ObjNames_copy = new ArrayList<String>();
		ArrayList<Double> currentCopiedObject;
		//ArrayList<Integer> removedObjects;
		ArrayList<Integer> nonReducedObjectIndexesinFC2 = new ArrayList<Integer>();
		boolean fc2_ObjReduced;
		int i, count=0;

		// Store a copy of input FCs for reference 
		deepCopyFC(formalContext1, fcObjectNames1, fc1_original, fc1_ObjNames_copy);
		deepCopyFC(formalContext2, fcObjectNames2, fc2_original, fc2_ObjNames_copy);


		// go through each object in FC 2 
		
		for (i=0;i<formalContext2.size();i++)
		{  

			// try to reduce this object in second FC using the first FC
			fc2_ObjReduced = reduceObjectinFC2usingFC1(i, delta);

			//System.out.println(fc2_ObjReduced);

			// if the object did not get reduced, we need to append it to our first FC
			if (!fc2_ObjReduced)
			{
				count++;
				//**************************************************
		System.out.println(" Alert of Anomaly object   "+ i +" not reducible "+ "total ="+ count +"   "+formalContext2.size());
		if (count==1) {xm=xm+i;totalfc2size+=formalContext2.size();average=average+(double)i/formalContext2.size();}
           
				nonReducedObjectIndexesinFC2.add(i);
				// add the non-reduced object from second FC to the first FC
				currentCopiedObject = formalContext2.get(i);
				formalContext1.add(currentCopiedObject);
				fcObjectNames1.add(fcObjectNames2.get(i));
			}
          
			// the last object in the first FC is the newly inserted object from the second FC
			// so only try to reduce that
			//removedObjects = reduceSingleObjectinFC(formalContext1, formalContext1.size()-1, 1);	

			//System.out.println("STEP : " + (i+1) + " REDUCED OBJECTS : " + removedObjects);
			//printFC(formalContext1, fcObjectNames1);
			//System.out.println(removedObjects);
			//moveRows(formalContext1, fcObjectNames1, removedObjects1, removedObjectNames1, removedObjects);
		}
		densite=densite+count;
		System.out.println("----------------------------");
		System.out.println("FC 1 metrics : Old objects = " + (formalContext1.size()-count) + " New Objects = " + count);

		if (count==0)
		{   truenormal++;
		System.out.println("FC 2 is completely reducible by FC 1"+ " in " + steps + " steps "+ " Truenormal "+ truenormal);
		System.out.println("No output file created, FC 1 file is consistent");
		//System.exit(0);
		return true;
		}
		else
		{   falseanomaly++;
		System.out.println(" Alert of Anomaly: FC 2 is not completely reducible by FC 1    "+ falseanomaly);

		return false;
		}
	}


	// This code is a variation of reduceSingleObjectinFC function below
	// The reduceSingleObjectinFC function tries to analyze and reduce a single object in the FC
	// This code instead works across FCs by taking a single object index from FC_2 and only 
	// tries to reduce it using all rows from FC_1 
	public static boolean reduceObjectinFC2usingFC1(int indexOfFC2ObjectToReduce, double LR_delta)
	{
		//System.out.println("Trying to Reduce the " + indexOfFC2ObjectToReduce + " Row of FC_2 using information from FC_1");
		double m, a, b, min, weight, referenceValue;
		int  w, k1, k2, i;

		ArrayList<Integer> satisfiedIndexes = new ArrayList<Integer>(); 
		//ArrayList<Integer> removedIndexes = new ArrayList<Integer>();
		boolean all_satisfy, all_min_smaller; 

		// mark k1 to only point to the object we want to reduce in the second FC
		k1 = indexOfFC2ObjectToReduce;
		for (k2 = 0; k2 < fc1_original.size(); k2++)
		{
			all_satisfy = true;
			for (w = 0; w < fc1_original.get(0).size(); w++)
			{
				//K1=a is the first object and K2=b is the second object, W to move between between columns  
				a = (fc2_original.get(k1).get(w));
				b = (fc1_original.get(k2).get(w));
				m = Math.min(1, 1 - a + b);
				all_satisfy = all_satisfy && (m >= LR_delta);
				if (!all_satisfy)
					break;
			}
			if (all_satisfy)
				satisfiedIndexes.add(k2);

		}
		all_min_smaller = true;
		for (w = 0; w < fc1_original.get(0).size(); w++)
		{
			min = Integer.MAX_VALUE;
			for (i = 0; i < satisfiedIndexes.size(); i++)
			{
				weight = (fc1_original.get(satisfiedIndexes.get(i)).get(w)); 
				if (weight < min)
					min = weight;
			}
			referenceValue = (fc2_original.get(k1).get(w));
			m = Math.min(1, 1 - min + referenceValue);
			all_min_smaller = all_min_smaller && (m >= LR_delta);
			if (!all_min_smaller)
				break;
		}
		if (all_min_smaller)
			return true;
		else
			return false;	
	}



	// This code is a variation of LukasiewiczReduction function below
	// The LukasiewiczReduction function tries to analyze and reduce all objects in the FC
	// this code only takes a single object index and only checks if it can be reduced or not 
	public static ArrayList<Integer> reduceSingleObjectinFC(ArrayList<ArrayList<Double>> formalContext, int indexOfObjectToReduce, float delta)
	{
		System.out.println("Trying to Lukasiewicz Reduce the " + indexOfObjectToReduce + " Row of FC");
		double m, a, b, min, weight, referenceValue;
		int  w, k1, k2, i;
		ArrayList<Integer> satisfiedIndexes = new ArrayList<Integer>(); 
		ArrayList<Integer> removedIndexes = new ArrayList<Integer>();
		boolean all_satisfy, all_min_smaller; 

		// instead of analyzing all objects in the FC, mark k1 to only the object we want to reduce
		//for (k1 = formalContext.size()-1; k1 >= 0; k1--)
		k1 = indexOfObjectToReduce;
		{
			for (k2 = 0; k2 < formalContext.size(); k2++)
			{
				if ((k1 != k2) && (!removedIndexes.contains(k2)))
				{
					all_satisfy = true;
					for (w = 0; w < formalContext.get(0).size(); w++)
					{
						//K1=a is the first object and K2=b is the second object, W to move between between columns  
						a = (formalContext.get(k1).get(w));
						b = (formalContext.get(k2).get(w));
						m = Math.min(1, 1 - a + b);
						all_satisfy = all_satisfy && (m >= delta);
						if (!all_satisfy)
							break;
					}
					if (all_satisfy)
						satisfiedIndexes.add(k2);
				}
			}
			all_min_smaller = true;
			for (w = 0; w < formalContext.get(0).size(); w++)
			{
				min = Integer.MAX_VALUE;
				for (i = 0; i < satisfiedIndexes.size(); i++)
				{
					weight = (formalContext.get(satisfiedIndexes.get(i)).get(w)); 
					if (weight < min)
						min = weight;
				}
				referenceValue = (formalContext.get(k1).get(w));
				m = Math.min(1, 1 - min + referenceValue);
				all_min_smaller = all_min_smaller && (m >= delta);
				if (!all_min_smaller)
					break;
			}
			if (all_min_smaller)
				removedIndexes.add(k1);

			// remove all objects from set of satisfied indexes for preparation for next check 
			// preparation for next check
			satisfiedIndexes.clear();
		}
		return removedIndexes;	
	}

	/* ///////////////////// START Eman Reduction Code //////////////////////////// */
	// Converted from C++ to Java by Fahad
	// reduces the number of rows
	// Check slides for explanation for the algorithm
	public static ArrayList<Integer> LukasiewiczReduction(ArrayList<ArrayList<Double>> formalContext, double delta)
	{
		//System.out.println("Reducing the Rows of FC");
		double m, a, b, min, weight, referenceValue, col_delta;
		int  w, k1, k2, i;
		ArrayList<Integer> satisfiedIndexes = new ArrayList<Integer>();
		ArrayList<Integer> removedIndexes = new ArrayList<Integer>();
		boolean all_satisfy, all_min_smaller;

		// Slight tweak to start reducing objects from end of FC instead of beginning.
		// This is primarily useful since we add new objects to end of FC for consistency checks
		for (k1 = formalContext.size()-1; k1 >= 0; k1--)
		{
			for (k2 = 0; k2 < formalContext.size(); k2++)
			{
				if ((k1 != k2) && (!removedIndexes.contains(k2)))
				{
					all_satisfy = true;
					for (w = 0; w < formalContext.get(0).size(); w++)
					{
						//K1=a is the first object and K2=b is the second object, W to move between between columns 
						a = (formalContext.get(k1).get(w));
						b = (formalContext.get(k2).get(w));
						m = Math.min(1, 1 - a + b);
						col_delta = delta;
						all_satisfy = all_satisfy && (m >= col_delta);
						if (!all_satisfy)
						{
							break;
						}
					}
					if (all_satisfy)
						satisfiedIndexes.add(k2);
				}
			}
			all_min_smaller = true;
			for (w = 0; w < formalContext.get(0).size(); w++)
			{   
				min = Integer.MAX_VALUE;
				for (i = 0; i < satisfiedIndexes.size(); i++)
				{
					weight = (formalContext.get(satisfiedIndexes.get(i)).get(w));

					if (weight < min)
						min = weight;
				}
				referenceValue = (formalContext.get(k1).get(w));
				m = Math.min(1, 1 - min + referenceValue);
				col_delta = delta;
				all_min_smaller = all_min_smaller && (m >= col_delta);

				if (!all_min_smaller)
					break;
			}
			if (all_min_smaller)
				removedIndexes.add(k1);
			//else {System.out.println("early anomalies detection "+ k1);}

			// remove all objects from set of satisfied indexes for preparation for next check
			// preparation for next check
			satisfiedIndexes.clear();
		}
		return removedIndexes;
	}

	/* ///////////////////// END Eman Reduction Code //////////////////////////// */


	// Auxiliary functions
	public static void printBothFCs()
	{
		System.out.println("Printing both formal contexts");
		System.out.println("----------------------------");
		int i,j;
		Double value;
		// Print the 2d arraylist to check the formal Context
		System.out.println("FC 1 (" + formalContext1.size() + " objects) :");
		for (i = 0; i < formalContext1.size(); i++)
		{
			System.out.print((i) + "\t" + fcObjectNames1.get(i) + ", ");
			for (j = 0; j<formalContext1.get(i).size(); j++)
			{
				value = (formalContext1.get(i).get(j));
				System.out.print(value + ", ");
			}
			System.out.println();
		}
		System.out.println("----------------------------");
		System.out.println("FC 2 (" + formalContext2.size() + " objects) :");
		for (i = 0; i < formalContext2.size(); i++)
		{
			System.out.print((i) + "\t" + fcObjectNames2.get(i) + ", ");
			for (j = 0; j<formalContext2.get(i).size(); j++)
			{
				value = (formalContext2.get(i).get(j));
				System.out.print(value + ", ");
			}
			System.out.println();
		}
		System.out.println("----------------------------");
	}

	public static void printFC(ArrayList<ArrayList<Boolean>> formalContext, ArrayList<String> fcObjectNames)
	{
		//System.out.println("Printing formal context");
		int i,j;
		String value;
		// Print the 2d arraylist to check the formal Context
		for (i = 0; i < formalContext.size(); i++)
		{
			System.out.print((i+1) + "\t" + fcObjectNames.get(i) + ", ");
			for (j = 0; j<formalContext.get(i).size(); j++)
			{
				value = (formalContext.get(i).get(j))?"1":"0";
				System.out.print(value + ", ");
			}
			System.out.println();
		}
	}

	public static void writeFCtoFile(String datasetFileName, ArrayList<ArrayList<Double>> formalContext, ArrayList<String> fcObjectNames, ArrayList<String> attributeNames)
	{
		int i,j;
		double value;
		String delimiter = ",";
		FileWriter writer = null;
		String line;
		String filename = datasetFileName.replaceAll(".csv", "_FC.csv");
		System.out.println("Writing FC to File : " + filename);
		try
		{
			line = "";

			// Create the file reader
			writer = new FileWriter(filename);

			// Write the attribute names of the formal context in the first line
			for (i = 0; i < attributeNames.size(); i++)
			{
				// The concat() method returns a new String that contains the result of the operation
				// It does not change the string itself so line.concat does not work, line = line.concat is valid
				line = line.concat(delimiter + attributeNames.get(i).toString());
			}
			line = line.concat("\n");
			writer.append(line);

			for (i = 0; i < formalContext.size(); i++)
			{
				line = "";
				line = line.concat(fcObjectNames.get(i) + delimiter);
				for (j = 0; j<formalContext.get(i).size(); j++)
				{
					value = (formalContext.get(i).get(j));
					line = line.concat(value + delimiter);
				}
				line = line.concat("\n");
				//System.out.print("FC line "+ (i+1) + "= " + line);
				writer.append(line);
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception in writeFCtoFile");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				writer.flush();
				writer.close();
			}
			catch (IOException e)
			{
				System.out.println("IO Exception in writeFCtoFile");
				e.printStackTrace();
			}
		}
	}

}
