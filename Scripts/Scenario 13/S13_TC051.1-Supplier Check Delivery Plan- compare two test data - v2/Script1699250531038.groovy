import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
//import org.apache.poi.xssf.usermodel.*
import java.io.FileInputStream as FileInputStream
import java.io.IOException as IOException
import java.io.File as File
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

latestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

partIndex = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndicesWithSheetList'(latestPath, [6, 7], 5, 0)

println(partIndex)

for (int row = 1; row <= testData.getRowNumbers(); row++) {
    def searchPart = ''

    for (int colTD = 1; colTD <= 2; colTD++) {
        String part = testData.getValue(colTD, row)

        searchPart = ((searchPart + part) + ' ')
    }
	
	println(searchPart)
    
    rowExcel = (partIndex[searchPart])
	
	for(int col=3;col<=5;col++) {
		
		String expectDP = testData.getValue(col, row)
		
		def excelValueDP = CustomKeywords.'util.compareTestData.getCellValue2'(latestPath, rowExcel, 20+col, 0)
		
		if(excelValueDP!='') {
			
			BigDecimal dataValueDouble = new BigDecimal(excelValueDP)
			
			int intValue = (int) dataValueDouble
			
			excelValueDP=intValue.toString()
			
		}
		
		
		KeywordUtil.logInfo("For part:"+searchPart+" in col:"+col+", Actual value:"+excelValueDP+" Expectation:"+expectDP)
		
		WebUI.verifyMatch(expectDP, excelValueDP, false)
		
	}

    
}

