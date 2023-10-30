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

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;



import internal.GlobalVariable

public class xlsmToXlsx {

	@Keyword
	public static void convertXLSMtoXLSX(String xlsmFilePath, String xlsxFilePath) throws Exception {
		FileInputStream fis = new FileInputStream(xlsmFilePath);
		Workbook wb = new XSSFWorkbook(fis);
		fis.close();

		FileOutputStream fos = new FileOutputStream(xlsxFilePath);
		wb.write(fos);
		fos.close();
		wb.close();
	}
}
