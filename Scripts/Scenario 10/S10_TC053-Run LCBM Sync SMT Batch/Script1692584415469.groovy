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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.ADMIN_USERNAME
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.ADMIN_COMPANY], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.navigateToUrl(batch_url)

WebUI.waitForElementPresent(findTestObject('Page_Batch/h3_Batch'), 0)

WebUI.click(findTestObject('Scenario 13/S13_TC039/button_ETL Batch'))

WebUI.click(findTestObject('Scenario 13/S13_TC039/li_STEP 1TRANSFER DATA TO SMT'))

WebUI.delay(1)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC039/div_Sync ETL Data.The operation was successful'), 0)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC039/p_The operation was successful'), 0)

WebUI.click(findTestObject('Scenario 13/S13_TC039/button_ETL Batch'))

WebUI.click(findTestObject('Scenario 13/S13_TC039/li_STEP 2PROCESS ETL DATA'))

WebUI.delay(1)

not_run: WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC039/div_Run ETL Data Processing.The operation was successful'), 
    0)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC039/p_The operation was successful'), 0)

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

