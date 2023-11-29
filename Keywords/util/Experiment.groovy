package util

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.util.KeywordUtil

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

public class Experiment {

	@Keyword(keywordObject='Excel Verification')
	static void copyDataBasedOnDates(String sourceFilePath, String targetFilePath, String sourceSheetName, String targetSheetName, int dateRowIndexA, int dateRowIndexB, List<Integer> columnsToCopy) {
		try {
			def sourceFis = new FileInputStream(new File(sourceFilePath))
			def targetFis = new FileInputStream(new File(targetFilePath))

			def sourceWorkbook = new XSSFWorkbook(sourceFis)
			def targetWorkbook = new XSSFWorkbook(targetFis)

			def sourceSheet = sourceWorkbook.getSheet(sourceSheetName)
			def targetSheet = targetWorkbook.getSheet(targetSheetName)

			// Get the row containing dates from both sheets
			def datesRowSource = sourceSheet.getRow(dateRowIndexA)
			def datesRowTarget = targetSheet.getRow(dateRowIndexB)

			columnsToCopy.each { colIndex ->
				def dateCellSource = datesRowSource.getCell(colIndex)

				if (dateCellSource) {
					// Find the corresponding column in the target sheet
					def colIndexTarget = findColumnByDate(targetSheet, dateCellSource.getStringCellValue(), dateRowIndexB)

					if (colIndexTarget != -1) {
						// Copy values from source to target based on the date
						println("Successful")
					}
				}
			}

			def outFile = new FileOutputStream(targetFilePath)
			targetWorkbook.write(outFile)

			KeywordUtil.logInfo("Data matched and copied successfully.")

			sourceFis.close()
			targetFis.close()

		} catch (Exception e) {
			KeywordUtil.logInfo("Error copying data based on dates: ${e.message}")
			KeywordUtil.markFailed("Data copy based on dates failed.")
		}
	}

	static int findColumnByDate(Sheet sheet, String date, int dateRowIndex) {
		def datesRow = sheet.getRow(dateRowIndex)
		(0..<datesRow.getLastCellNum()).each { col ->
			def dateCell = datesRow.getCell(col)
			if (dateCell && dateCell.getStringCellValue() == date) {
				return col
			}
		}
		return -1
	}
	
	@Keyword
	def writeExcel(String value) {
		
		workbook01 = CustomKeywords.'com.kms.katalon.keyword.excel.ExcelKeywords.getWorkbook'(excelFilePath)
		
		//Get sheet path
		sheet01 = CustomKeywords.'com.kms.katalon.keyword.excel.ExcelKeywords.getExcelSheet'(workbook01, 'Sheet1')
		
		//Get Value of Cell By Index
		CustomKeywords.'com.kms.katalon.keyword.excel.ExcelKeywords.setValueToCellByIndex'(sheet01, 2, 2, textToWrite)
		
		//Write value to Cell
		CustomKeywords.'com.kms.katalon.keyword.excel.ExcelKeywords.saveWorkbook'(excelFilePath, workbook01)
		
		
		
		
		
		
	}


}



