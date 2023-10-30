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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Received Request List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/input_Filter_Received Request List'), RequestNo)

WebUI.verifyElementText(findTestObject('Scenario 9/SC9_TC007/p_verifyStatus', [('requestNo') : RequestNo]), 'Submitted')

WebUI.click(findTestObject('Scenario 9/SC9_TC007/button_View'))

WebUI.click(findTestObject('Scenario 9/SC9_TC011/button_Reject'))

WebUI.focus(findTestObject('Scenario 9/SC9_TC011/input__rejectReason'))

WebUI.setText(findTestObject('Scenario 9/SC9_TC011/input__rejectReason'), RejectReason)

WebUI.click(findTestObject('Scenario 9/SC9_TC011/button_RejectConfirm'))

WebUI.click(findTestObject('Scenario 17/S17_TC012/Page_Received Request Remove Part - Brivge/span_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Scenario 9/SC9_TC011/div_Reject Request.The operation was successful'), 0)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/input_Filter_Received Request List'), RequestNo)

WebUI.verifyElementNotPresent(findTestObject('Scenario 9/SC9_TC011/p_verifyRequestNo', [('RequestNo') : RequestNo]), 0)

WebUI.closeBrowser()

