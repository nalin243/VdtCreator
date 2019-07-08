package application;


import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.util.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.*;



public class create_Excel_File {
	Workbook wb;
	CreationHelper createHelper;
	org.apache.poi.ss.usermodel.Sheet sheet;
	
	void createFile() {
		wb = new HSSFWorkbook();
		createHelper = wb.getCreationHelper();
		sheet = wb.createSheet("sheet");
	}
	
	void writeVdt(LinkedList<String> variables,LinkedList<String> datatypes, LinkedList<String> descrption) {
		sheet.setColumnWidth(0,3500);
		sheet.setColumnWidth(1, 3500);
		sheet.setColumnWidth(2, 6000);
		
		CellStyle style = wb.createCellStyle();
		HSSFFont font = (HSSFFont) wb.createFont();
		font.setBold(true);
		style.setFont(font);
		
		Row headRow  = sheet.createRow((short)0);
		headRow.createCell(0).setCellValue("Variable Name");
		headRow.getCell(0).setCellStyle(style);
		
		headRow.createCell(1).setCellValue("Data Type");
		headRow.getCell(1).setCellStyle(style);
		
		headRow.createCell(2).setCellValue("Description");
		headRow.getCell(2).setCellStyle(style);
		
		Row row;
		for(int i = 0;i<variables.size();i++) {
			row = sheet.createRow((short)i+1);
			row.createCell(0).setCellValue(variables.get(i));
			row.createCell(1).setCellValue(datatypes.get(i));
			row.createCell(2).setCellValue(descrption.get(i));
		}
	}
	
	void writeToFile(String fileName) throws IOException {
		FileOutputStream writer = new FileOutputStream("vdt"+fileName+".xls");
		wb.write(writer);
		writer.close();
	}
}
 