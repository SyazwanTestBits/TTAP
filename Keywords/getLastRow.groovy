import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class getLastRow {

	@Keyword
	public String lastRow(String path, String sheetName) throws IOException{

		FileInputStream file = new FileInputStream(path)
		Workbook workbook = WorkbookFactory.create(file)

		Sheet sheet = workbook.getSheet(sheetName)

		int lastRowIndex = sheet.getLastRowNum()

		return lastRowIndex
	}


	@Keyword
	public void exeldef(def name, int rowNum, int colNum, String file, String sheetName) throws IOException {

		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet(sheetName);

		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum); // Create a new row if it doesn't exist
		}

		Cell cell = row.createCell(colNum);
		cell.setCellValue(name);

		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);

		fos.close();
		fis.close();
	}
}
