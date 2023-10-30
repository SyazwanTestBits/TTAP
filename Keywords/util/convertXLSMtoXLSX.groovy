package util

@Grapes([
	@Grab(group='org.apache.poi', module='poi', version='5.0.0'),
	@Grab(group='org.apache.poi', module='poi-ooxml', version='5.0.0')
])

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

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import internal.GlobalVariable

public class convertXLSMtoXLSX {

	@Keyword
	public void xlsmToXLSX(String xlsmFilePath) {

		String xlsxFilePath = 'Excel Files/Scenario 10/S10-Daily.xlsx'

		// Open the .xlsm file
		def xlsmFileInputStream = new FileInputStream(xlsmFilePath)
		def workbook = new XSSFWorkbook(xlsmFileInputStream)

		// Create a new .xlsx workbook
		def newXSSFWorkbook = new XSSFWorkbook()

		// Copy sheets from the original .xlsm workbook to the new .xlsx workbook
		workbook.each { oldSheet ->
			def newSheet = newXSSFWorkbook.createSheet(oldSheet.sheetName)

			oldSheet.eachWithIndex { oldRow, rowIndex ->
				def newRow = newSheet.createRow(rowIndex)

				if (oldRow) {
					oldRow.eachWithIndex { oldCell, cellIndex ->
						def newCell = newRow.createCell(cellIndex)

						if (oldCell) {
							def cellType = oldCell.cellType

							switch (cellType) {
								case CellType.BLANK:
									newCell.setCellValue("")
									break
								case CellType.BOOLEAN:
									newCell.setCellValue(oldCell.booleanCellValue)
									break
								case CellType.ERROR:
								// Handle errors as needed
									break
								case CellType.FORMULA:
								// Copy the formula string as-is
									newCell.setCellFormula(oldCell.cellFormula)
									break
								case CellType.NUMERIC:
									newCell.setCellValue(oldCell.numericCellValue)
									break
								case CellType.STRING:
									newCell.setCellValue(oldCell.stringCellValue)
									break
								default:
									newCell.setCellValue(oldCell.toString())
							}
						}
					}
				}
			}
		}

		// Save the new .xlsx workbook to the specified path
		def outputStream = new FileOutputStream(xlsxFilePath)
		newXSSFWorkbook.write(outputStream)
		outputStream.close()

		// Close the original .xlsm workbook
		workbook.close()

		println 'Conversion completed!'
	}



	@Keyword
	public void xlsxWithName222(String xlsmFilePath) {


			//Open the xlsm file
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(xlsmFilePath);

		//Create a new xlsx workbook
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();

		//Copy all of the sheets from the xlsm workbook to the xlsx workbook
		for (int i = 0; i < hssfWorkbook.getNumberOfSheets(); i++) {
			XSSFWorkbook sheet = xssfWorkbook.createSheet();
			hssfWorkbook.getSheetAt(i).copyRowsTo(0, hssfWorkbook.getSheetAt(i).getLastRowNum(), sheet, 0);
		}

		//Save the xlsx file
		xssfWorkbook.write("Excel Files/Scenario 10/Convert from XLSM to XLSX/testFile.xlsx");

		//Close the workbooks
		hssfWorkbook.close();
		xssfWorkbook.close();
	}

	@Keyword
	public void filterExcel(String filePath) {

		def file = new File(filePath)
		def workbook = new XSSFWorkbook(file)

		// Get the sheet you want to work with
		def sheet = workbook.getSheetAt(0)

		// Define the column index to filter (0-based index)
		def columnIndexToFilter = 1 // Change this to the desired column index

		// Define the starting row index (0-based index)
		def startingRowIndex = 3 // Change this to the desired starting row index

		// Define the filter value
		def filterValue = 'BLM' // Change this to the desired filter value

		// Apply the filter
		def autoFilterRange = CellRangeAddress.valueOf("${((char)('A' + columnIndexToFilter))}${startingRowIndex+1}:${((char)('A' + columnIndexToFilter))}${sheet.getLastRowNum()}")
		def autoFilter = sheet.setAutoFilter(autoFilterRange)
		autoFilter.applyFilter(EqualsFilter(), filterValue)

		// Save the changes to the Excel file
		def outputFilePath = filePath
		def outputFile = new File(outputFilePath)
		workbook.write(outputFile)
		workbook.close()

		println("Filter applied and file saved.")

	}

	@Keyword
	public getfilter(String filePath) {
		String outputFilePath = "path/to/output/file.xlsx";

		int targetColumnIndex = 1; // Column index (0-based) to filter on
		String filterValue = "BLM"; // The value to filter

		FileInputStream inputStream = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);

		for (int rowIndex = sheet.getFirstRowNum() + 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				Cell cell = row.getCell(targetColumnIndex);
				if (cell != null && cell.getCellType() == CellType.STRING) {
					String cellValue = cell.getStringCellValue();
					if (cellValue.equals(filterValue)) {
						sheet.removeRow(row);
					}
				}
			}
		}

		inputStream.close();

		// Cleanup the sheet by shifting rows up to remove the filtered rows
		sheet.shiftRows(sheet.getFirstRowNum() + 1, sheet.getLastRowNum(), -1);

		FileOutputStream outputStream = new FileOutputStream(outputFilePath);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}
	
	
}
