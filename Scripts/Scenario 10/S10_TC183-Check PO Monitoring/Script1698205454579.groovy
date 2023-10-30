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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : url, ('username') : username, ('password') : password
        , ('verificationCode') : verificationCode, ('company') : company], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.scrollToElement(findTestObject('Scenario 10/S10_TC183/div_Inbound Record'), 0)

for (int row = 1; row <= 2; row++) {
    WebUI.setText(findTestObject('Scenario 10/S10_TC183/input_Po Management_Inbound Record_search'), purchaseID)

    WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC183/div_Inbound Record_POId', [('row') : row]), purchaseID)

    String colorFirstBar = WebUI.getCSSValue(findTestObject('Scenario 10/S10_TC183/div_Inbound Record_Status bar', [('row') : row]), 
        'background-color')

    println(colorFirstBar)

    colormatch = 'N'

    if ((colorFirstBar == 'rgba(0, 166, 237, 1)') || (colorFirstBar == 'rgba(12, 167, 143, 1)')) {
        colormatch = 'Y'
    }
    
    WebUI.verifyMatch(colormatch, 'Y', true)

    WebUI.click(findTestObject('Scenario 10/S10_TC183/div_Inbound Record_POId', [('row') : row]))

    WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC183/input_Basic Info_status'), 'value', 'Processing', 
        0)

    WebUI.back()
}

WebUI.setText(findTestObject('Scenario 10/S10_TC183/input_Po Management_Inbound Record_search'), purchaseID)

WebUI.takeScreenshot()

