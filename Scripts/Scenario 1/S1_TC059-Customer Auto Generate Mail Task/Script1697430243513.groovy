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

def actualText = CustomKeywords.'getEmail.extract'('ttaptesternasa@outlook.com', 'dyfpmobqkjkyvpud', 'Order Revised')

//Extract RegularSalesNo from Excel
int col=2
int row=1

def regularSalesOrderNo=findTestData('Scenario 12/SC12_TC016-Supplier SO').getValue(col,row)

println(regularSalesOrderNo)

//Validate actualText against expectedText
def expectedText = "Dear PK-CUS-POC., You have received change/cancel request for below order: ${regularSalesOrderNo} Please login to the system to check and approve the request. Thank you for your continued support! Best regards."

	if (actualText == expectedText) {
		println('actualText and expectedText match!')
	} else {
		throw new Error('actualText and expectedText do not match!')
	}
	
