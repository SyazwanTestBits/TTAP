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
        , ('password') : password, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/button_Master'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/li_Contract List'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/button_Download_contract list - Customer'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/li_Download Contract'))

'Verify Download Contract'
WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/h6_Download Contract Master By filters'), 
    'Download Contract Master By filters.')

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/p_The operation was successful'), 
    'The operation was successful.')

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/button_Download_contract list - Customer'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/li_Download Contract Parts'))

'Verify Download Contract by Part'
WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/h6_Download Contract Parts Master By filters'), 
    'Download Contract Parts Master By filters.')

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/p_The operation was successful'), 
    'The operation was successful.')

WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/svg_close notification download contract'))

WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/svg_close notification download contract part'))

WebUI.setText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/input_Search Contract List'), description)

//not_run: WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/input_Search Contract List'), 'CNTWSUP TO PKCUS 001')
//not_run: WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/button-Search Cotract List'))
//not_run: WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/p_first row contract list'), 'CNTWSUP TO PKCUS 001')
not_run: contractNo = WebUI.getText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/p_first row contract list'), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/button_view contract list'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/h3_header page view and modify'), 'View Contract')

WebUI.closeBrowser()

