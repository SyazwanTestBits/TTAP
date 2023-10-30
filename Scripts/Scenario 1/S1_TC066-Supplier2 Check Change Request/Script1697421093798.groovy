import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_SUP2], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Scenario 12/SC12_TC035/li_ChangeCancel Request List'))

WebUI.waitForElementPresent(findTestObject('Scenario 12/SC12_TC035/h3_ChangeCancel Request List'), 0)

WebUI.setText(findTestObject('Scenario 1/S1_TC062/input_ChangeCancel RequestNo'), Supplier2_RequestNo)

'Verify Order Change with Status = Approved'
WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC034/p_Order Change', [('requestNo') : Supplier2_RequestNo]), 
    'Approved')

KeywordUtil.logInfo("Verified Request No: $Supplier2_RequestNo Request Type: Order Change Status: Approved")

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC062/p_requestFrom', [('requestNo') : Supplier2_RequestNo]), GlobalVariable.S1_BAF_SUP2)

KeywordUtil.logInfo("Verified Request From: $GlobalVariable.S1_BAF_SUP2")

WebUI.click(findTestObject('Scenario 12/SC12_TC019/p_detailButton', [('requestNo') : Supplier2_RequestNo]))

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC062/Page_ChangeCancel Request Detail - Brivge/input_Request Detail Information_requestNo'), 
    'value', Supplier2_RequestNo, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC062/Page_ChangeCancel Request Detail - Brivge/input_Request Detail Information_orderType'), 
    'value', 'Regular', 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC062/Page_ChangeCancel Request Detail - Brivge/input_Request Detail Information_requestType'), 
    'value', 'Order Change', 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC062/Page_ChangeCancel Request Detail - Brivge/input_Request Detail Information_requestStatus'), 
    'value', 'Approved', 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC062/Page_ChangeCancel Request Detail - Brivge/input_Request Detail Information_contractNo'), 
    'value', Sup2toBU2_Contract, 0)

WebUI.closeBrowser()

