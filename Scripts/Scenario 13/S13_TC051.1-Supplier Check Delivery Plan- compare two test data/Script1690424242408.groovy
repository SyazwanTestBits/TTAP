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

LatestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

//String LatestPath = 'C:/Katalon_Downloads/DeliveryPlanDownload.xlsx'
absoluteExpectPath = CustomKeywords.'ManageFiles.getFileAbsolutePath'(expectationExcelPath)

nomatch = CustomKeywords.'util.compareTestData.compareExcelFiles'(LatestPath, absoluteExpectPath, 6, 8, 3, 24)

println('Number of error: ' + nomatch)

WebUI.verifyEqual(nomatch, NumberOfNoMatch, FailureHandling.STOP_ON_FAILURE)

