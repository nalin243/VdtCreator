package application;


import java.io.*;
import java.util.LinkedList;

public class vdt_Creator {
	
	public void createVdt(int numOfVdts,String files[],String filePaths[]) throws Exception {
		
		extract_Data extractor = new extract_Data();//using extractor to extract data from the file
		create_Excel_File creator = new create_Excel_File();//using creator to create the excel file

		for(int i=0;i<numOfVdts;i++) {
			extractor.filePath = filePaths[i];
			extractor.extractVariables();
			System.out.println("Doing important stuff for file "+files[i]+".....");
			
			creator.createFile();
			creator.writeVdt(extractor.variables,extractor.dataTypes,extractor.description);
			creator.writeToFile(files[i]);
			
			extractor.variables.clear();
			extractor.dataTypes.clear();
			extractor.description.clear();
		}
	}
}
