import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
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
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Scenario 12/SC12_TC020/li_ChangeCancel Request List'))

WebUI.setText(findTestObject('Object Repository/Scenario 1/S1_TC031/input_textField'), requestNo)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_requestNo', [('row') : 1]), requestNo)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_requestFrom', [('row') : 1]), GlobalVariable.S1_BAF_SUP1)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_requestType', [('row') : 1]), 'Cancel')

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_requestStatus', [('row') : 1]), 'New')

WebUI.click(findTestObject('Scenario 1/S1_TC034/p_detailButton', [('row') : 1]))

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC034/input_ContractNo'), 'value', BU1toCus_contract, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC034/input_PO'), 'value', CO_no, 0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 1/S1_TC037_TC038/button_Reject'), 0)

WebUI.setText(findTestObject('Scenario 1/S1_TC037_TC038/input_Input Reject Reason_remark'), 'S1_TC060-Customer Reject Cancel')

WebUI.click(findTestObject('Scenario 1/S1_TC037_TC038/button_RejectReason'))

WebUI.click(findTestObject('Scenario 1/S1_TC037_TC038/button_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Scenario 1/S1_TC037_TC038/div_Reject.The operation was successful'), 0)

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Scenario 12/SC12_TC021/p_ChangeCancel Request List'))

WebUI.setText(findTestObject('Object Repository/Scenario 1/S1_TC031/input_textField'), requestNo)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC037_TC038/p_status', [('requestNo') : requestNo]), 'Rejected')

WebUI.closeBrowser()

