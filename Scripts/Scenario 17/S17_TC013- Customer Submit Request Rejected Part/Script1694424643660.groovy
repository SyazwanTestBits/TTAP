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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Contract Route List/button_Master'))

WebUI.click(findTestObject('Scenario 9/SC9_TC002/li_Request List'))

WebUI.setText(findTestObject('Scenario 9/SC9_TC008/input_RequestNo', [:]), RequestNo)

WebUI.verifyElementText(findTestObject('Scenario 9/SC9_TC008/p_verifyStatus', [('requestNo') : RequestNo]), 'Rejected')

WebUI.click(findTestObject('Scenario 17/S17_TC013/p_submitButton', [('requestNo') : RequestNo]))

WebUI.click(findTestObject('Scenario 17/S17_TC003/Page_Request Remove Part - Brivge/span_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC003/Page_Request Remove Part - Brivge/div_Submit Request DetailThe operation was successful'), 
    0)

WebUI.closeBrowser()
