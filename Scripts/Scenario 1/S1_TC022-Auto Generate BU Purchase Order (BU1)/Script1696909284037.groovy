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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_LUQMAN
        , ('password') : GlobalVariable.PNA_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 10/S10_TC073/button_Order'))

WebUI.click(findTestObject('Scenario 10/S10_TC049/li_PO Monitoring List'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC049/h3_PO Monitoring List (1)'), 0)

WebUI.setText(findTestObject('Scenario 10/S10_TC049/input_PO Monitoring List_Search'), contractNo)

WebUI.click(findTestObject('Scenario 10/S10_TC049/input_tick all'))

WebUI.click(findTestObject('Scenario 10/S10_TC049/button_download'))

WebUI.click(findTestObject('Scenario 10/S10_TC049/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC049/p_The operation was successful'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC049/button_Detail'))

contractNoSys = WebUI.getAttribute(findTestObject('Scenario 1/S1_TC022/Page_PO Monitoring Detail - Brivge/input_Basic Info_contractNo'), 
    'value')

orderNo = WebUI.getAttribute(findTestObject('Scenario 1/S1_TC022/Page_PO Monitoring Detail - Brivge/input_Basic Info_orderNo'), 
    'value')

println(contractNoSys)

if (contractNoSys == contractBU2) {
    CustomKeywords.'copyToExcel.exel'(orderNo, 1, 1, 'Excel Files\\Scenario 1', 'S1_TestCases_Data.xlsx', 'TC20-Autogen SOPO')
} else if (contractNoSys == contractBU3) {
    CustomKeywords.'copyToExcel.exel'(orderNo, 2, 1, 'Excel Files\\Scenario 1', 'S1_TestCases_Data.xlsx', 'TC20-Autogen SOPO')
} else {
    WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC049/input_Basic Info_orderType'), 'Value', orderType, 
        3)
}

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC049/input_Basic Info_status'), 'Value', status, 3)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC049/input_Basic Info_orderFrequency'), 'Value', orderFrequency, 
    3)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC049/input_Basic Info_shippingMode'), 'Value', shippingMode, 
    3)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC049/input_Basic Info_seller'), 'Value', seller, 3)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC049/input_Basic Info_receiver'), 'Value', receiver, 
    3)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC049/input_Basic Info_receivedDate'), 'Value', orderDate, 
    3)

WebUI.closeBrowser()

