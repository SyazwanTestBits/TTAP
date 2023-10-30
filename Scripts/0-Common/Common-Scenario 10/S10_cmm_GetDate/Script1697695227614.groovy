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

String firstInboundDate = CustomKeywords.'util.compareTestData.getExcelDateCellValue'(latestPath, 15, 23, 0, 'd MMM yyyy')

String nextInboundDate = CustomKeywords.'util.compareTestData.getExcelDateCellValue'(latestPath, 15, 24, 0, 'd MMM yyyy')

int lastdate = 24

while (nextInboundDate != firstInboundDate) {
	lastdate = (lastdate + 1)

	println(lastdate)

	nextInboundDate = CustomKeywords.'util.compareTestData.getExcelDateCellValue'(latestPath, 15, lastdate, 0, 'd MMM yyyy')
}