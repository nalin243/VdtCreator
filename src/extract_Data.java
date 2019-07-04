

import java.util.*;
import java.io.*;

public class extract_Data {
	
	String filePath;
	LinkedList<String> dataTypes = new LinkedList<String>();
	LinkedList<String> variables = new LinkedList<String>();
	LinkedList<String> description  = new LinkedList<String>();
	
	void extractVariables() throws FileNotFoundException,IOException {
		
		String line;
		int checker = 0;
		
		BufferedReader filein = new BufferedReader(new FileReader(filePath));
		
		while((line = filein.readLine())!=null) {
			line = line.trim();
			if(!((line.equals(""))||line.equals("}")||line.equals("{") )) {
				String statements[] = line.split(";");
				for(String statement:statements) {
					String words[] = statement.split(" ");
					for(int i = 0;i<words.length;i++) {
						try {
							if((words[i].equals("String")|| words[i].equals("long")|| words[i].equals("byte") ||words[i].equals("int")||words[i].equals("boolean") || words[i].equals("void") || words[i].equals("char")||words[i].equals("double")||words[i].equals("float")||words[i].equals("int"))) {
								String letters[] = words[i+1].split("");
								for(String letter:letters) {
									if(letter.equals("(")) checker = 1;
								}
								if(checker != 1) {
									dataTypes.add(words[i]);
									variables.add(words[i+1]);
									description.add("To store "+words[i+1]);
								}
								else { 
									try {
										statement = (statement.substring(statement.indexOf('(')+1,statement.indexOf(')'))).trim();
									}
									catch(StringIndexOutOfBoundsException e){}
									if(statement.indexOf(',') != -1) {
										String tokens[] = statement.split(",");
										for(String token:tokens) {
											token = token.trim();
											String dataVar[] = token.split(" ");
											dataTypes.add(dataVar[0]);
											variables.add(dataVar[1]);
											description.add("To store "+dataVar[1]);
										}
									}
									break;
								}
								
							}
						}
						catch(ArrayIndexOutOfBoundsException e){}
					}
					checker = 0;
				}
			}
		}
	}
}
