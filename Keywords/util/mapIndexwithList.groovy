package util

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


@Grab(group='org.apache.poi', module='poi', version='5.1.0')
@Grab(group='org.apache.poi', module='poi-ooxml', version='5.1.0')

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*



public class mapIndexwithList {

	@Keyword
	def getRowIndexInExcelCell(String filePath, int sheetIndex, int startingRow, List<Integer> listColumn) {

		def partToIndexMap = [:]
		def fileInputStream = new FileInputStream(filePath)
		def workbook = new XSSFWorkbook(fileInputStream)
		def sheet = workbook.getSheetAt(sheetIndex)


		for (int rowIndex = startingRow; rowIndex <= sheet.lastRowNum; rowIndex++) {

			String searchValue=''

			for (int columnIndex : listColumn) {

				def cell = sheet.getRow(rowIndex).getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
				def partNumber = cell.toString()


				searchValue=searchValue+partNumber+" "
			}


			if (partToIndexMap.containsKey(searchValue)) {
				partToIndexMap[searchValue] = rowIndex
			} else {
				partToIndexMap.put(searchValue, rowIndex)
			}
		}


		workbook.close()
		fileInputStream.close()
		return partToIndexMap
	}
}


