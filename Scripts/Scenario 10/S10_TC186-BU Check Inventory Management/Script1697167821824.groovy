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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 10/S10_TC186/button_remote_Search'))

WebUI.click(findTestObject('Scenario 10/S10_TC186/input_remote-year'))

WebUI.click(findTestObject('Scenario 10/S10_TC186/li_remote-year', [('year') : year]))

WebUI.click(findTestObject('Scenario 10/S10_TC186/input_remote_month'))

WebUI.click(findTestObject('Scenario 10/S10_TC186/li_remote_month', [('month') : month]))

WebUI.click(findTestObject('Scenario 10/S10_TC186/input_remote_warehouses'))

WebUI.click(findTestObject('Scenario 10/S10_TC186/li_remote_warehouses', [('warehouse_company') : warehouse_company]))

WebUI.click(findTestObject('Scenario 10/S10_TC186/input_remote_currency'))

WebUI.click(findTestObject('Scenario 10/S10_TC186/li_remote_currency', [('currency') : currency]))

WebUI.click(findTestObject('Scenario 10/S10_TC186/button_remote_Search'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC186/span_Inventory Management-Last Updated'), 0)

WebUI.takeFullPageScreenshot()

