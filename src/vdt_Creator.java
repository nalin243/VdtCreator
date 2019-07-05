

import java.util.Scanner;

public class vdt_Creator {
	
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("***Welcome to the automatic vdt creator!***");
		System.out.println("**The program file should be where the jar file is.***");
		System.out.println("");
		System.out.print("Enter the file name: ");
		
		extract_Data extractor = new extract_Data();//using extractor to extract data from the file
		extractor.filePath = sc.nextLine();
		extractor.extractVariables();
		System.out.println("Doing important stuff.....");
		
		create_Excel_File creator = new create_Excel_File();//using creator to create the excel file
		creator.createFile();
		creator.writeVdt(extractor.variables,extractor.dataTypes,extractor.description);
		creator.writeToFile();
		
		System.out.println("Done! You will find that a new .xls file has been created.\nThis will have your vdt");
		
	}
}
