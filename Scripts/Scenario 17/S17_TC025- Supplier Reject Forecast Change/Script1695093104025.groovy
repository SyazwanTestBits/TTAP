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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_ChangeCancel Request List'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC034/h3_ChangeCancel Request List'), 0)

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC025/p_verifyRequestType', [('requestNo') : requestNo]), 0)

WebUI.check(findTestObject('Scenario 17/S17_TC039/input_CheckboxrequestNo', [('requestType') : requestType, ('requestNo') : requestNo]))

WebUI.click(findTestObject('Scenario 17/S17_TC039/Page_ChangeCancel Request List - Brivge/button_RejectChangeCancelRequest'))

WebUI.setText(findTestObject('Scenario 17/S17_TC039/Page_ChangeCancel Request List - Brivge/input_Input Reject Reason_remark'), 
    'Reject')

WebUI.click(findTestObject('Scenario 17/S17_TC025/button_Reject'))

WebUI.click(findTestObject('Scenario 17/S17_TC039/Page_ChangeCancel Request List - Brivge/span_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC039/Page_ChangeCancel Request List - Brivge/div_Reject.The operation was successful'), 
    0)

WebUI.verifyElementText(findTestObject('Scenario 17/S17_TC039/p_verifyRequestStatusRejected', [('requestNo') : requestNo]), 
    'Rejected')

//
WebUI.closeBrowser()

