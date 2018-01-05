package featureCreation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel2Feature {
	public static String excelToFeaturConversion(String tcName) throws InvalidFormatException, IOException {
		File testcaseExcelSheet = new File("C:\\Users\\susov\\Desktop\\Tests\\Cucumber Session\\TCs\\Cucumber TCs.xlsx");
		FileInputStream testcaseFile = new FileInputStream(testcaseExcelSheet);
		Calendar cal = Calendar.getInstance();
		String time = "_"+cal.getTime().toString().replaceAll(":", "-");
		String featureFileName = "C:\\Users\\susov\\Desktop\\Tests\\Cucumber Session\\Feature Files\\NewFeature"+time+".feature";
		File featureFile = new File(featureFileName);
		PrintWriter writer = new PrintWriter(featureFile);
		XSSFWorkbook workbook = new XSSFWorkbook(testcaseFile);
		XSSFSheet sheet = workbook.getSheet("Regression");
		int lastRowNum = sheet.getLastRowNum();
		for(int i=1; i<=lastRowNum; i++) {
			Row row = sheet.getRow(i);
			if(row.getCell(1)!=null && tcName.equalsIgnoreCase(row.getCell(1).toString())) {
				String writeInFeature = readExcelFile(writer, sheet, i, row, tcName);
				switch(writeInFeature) {
				case "1": writer.println(row.getCell(4).toString());
						  break;
				case "2": writer.println(row.getCell(4)+" \""+row.getCell(5).toString()+"\"");
						  break;
				case "3": writer.println(row.getCell(4)+" \""+row.getCell(6).toString()+"\"");
						  break;
				case "4": writer.println(row.getCell(4)+" \""+row.getCell(5).toString()+"\""+" "+"\""+row.getCell(6).toString()+"\"");
						  break;
				}
			}
		}
		writer.close();
		workbook.close();
		return featureFileName;
	}
	private static String readExcelFile(PrintWriter writer, XSSFSheet sheet, int i, Row row, String tcName) {
		DataFormatter format = new DataFormatter();
		Row rowToCheck = sheet.getRow(i-1);
		Cell firstCellEntry = rowToCheck.getCell(0);
		String valueToCheck = format.formatCellValue(firstCellEntry);
		if(valueToCheck.equalsIgnoreCase(null) || valueToCheck.equalsIgnoreCase("New TC")) {
			writer.println("Feature: To create automation test suite");
			writer.println("\n Scenario: To check "+tcName);
		}
		else {
			System.out.println("This is not first line.");
		}
		if(row.getCell(5)==null && row.getCell(6)==null) {
			return "1";
		}
		else
			if(row.getCell(5)!=null && row.getCell(6)==null) {
				return "2";
			}
		else 
			if(row.getCell(5)==null && row.getCell(6)!=null) {
				return "3";
			}
			else {
				return "4";
			}
	}
	
}
