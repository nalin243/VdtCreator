

import java.io.*;
import java.util.LinkedList;

public class vdt_Creator {
	
	public static void main(String[] args) throws Exception {
		
		LinkedList<String> files = new LinkedList<String>();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		extract_Data extractor = new extract_Data();//using extractor to extract data from the file
		create_Excel_File creator = new create_Excel_File();//using creator to create the excel file
		
		System.out.println("***Welcome to the automatic vdt creator!***");
		System.out.println("**The program file should be where the jar file is.***");
		System.out.println("");
		System.out.print("How many vdts do you want to create?: ");
		int numOfVdts = Integer.parseInt(in.readLine());
		for(int i = 0;i<numOfVdts;i++) {
			System.out.print("Enter the file name: ");
			files.add(in.readLine()+".java");
		}
		
		for(int i=0;i<numOfVdts;i++) {
			extractor.filePath = files.get(i);
			extractor.extractVariables();
			System.out.println("Doing important stuff for file "+i+".....");
			
			creator.createFile();
			creator.writeVdt(extractor.variables,extractor.dataTypes,extractor.description);
			creator.writeToFile(i);
			
			extractor.variables.clear();
			extractor.dataTypes.clear();
			extractor.description.clear();
		}
		
		System.out.println("Done! You will find that a new .xls file has been created.\nThis will have your vdt");
		
	}
}
