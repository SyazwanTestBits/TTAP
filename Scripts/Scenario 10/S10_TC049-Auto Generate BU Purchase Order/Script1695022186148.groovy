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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_LUQMAN
        , ('password') : GlobalVariable.PNA_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 10/S10_TC073/button_Order'))

WebUI.click(findTestObject('Scenario 10/S10_TC049/li_PO Monitoring List'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC049/h3_PO Monitoring List (1)'), 0)

searchString = ((contractELA + ' ') + contractYAZ)

not_run: WebUI.click(findTestObject('Scenario 10/S10_TC024/button_search AND'))

not_run: WebUI.setText(findTestObject('Scenario 10/S10_TC049/input_PO Monitoring List_Search'), searchString)

not_run: WebUI.click(findTestObject('Scenario 10/S10_TC049/input_tick all'))

not_run: WebUI.click(findTestObject('Scenario 10/S10_TC049/button_download'))

not_run: WebUI.click(findTestObject('Scenario 10/S10_TC049/li_Download by Excel'))

not_run: WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC049/p_The operation was successful'), 0)

not_run: CustomKeywords.'util.clearTextJS.clearElementText'(findTestObject('Scenario 10/S10_TC049/input_PO Monitoring List_Search'))

rowELA = 0

rowYAZ = 0

rowDT = 0

for (int row = 1; row <= 3; row++) {
    WebUI.setText(findTestObject('Scenario 10/S10_TC049/input_PO Monitoring List_Search'), testData.getValue('ContractNo', 
            row))

    if (testData.getValue('Seller', row) == 'MY-ELA-SUP') {
        rowELA = (rowELA + 1)

        rowDT = rowELA
    } else {
        rowYAZ = (rowYAZ + 1)

        rowDT = rowYAZ
    }
    
    orderNo = WebUI.getText(findTestObject('Page_PO_MonitoringList/div_Dt_PurchaseOrderNo - Copy', [('row') : rowDT]))

    CustomKeywords.'copyToExcel.exel4'(orderNo, row, 0, 'Excel Files/Scenario 10/S10_TestCases_Data.xlsx', 'TC049 autogen')

    WebUI.click(findTestObject('Scenario 10/S10_TC049/button_Detail', [('row') : rowDT]))

    WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC049/input_Basic Info_orderType'), 'Value', testData.getValue(
            'OrderType', row), 3)

    WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC049/input_Basic Info_status'), 'Value', testData.getValue(
            'Status', row), 3)

    WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC049/input_Basic Info_orderFrequency'), 'Value', 
        testData.getValue('OrderFrequency', row), 3)

    WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC049/input_Basic Info_shippingMode'), 'Value', testData.getValue(
            'ShippingMode', row), 3)

    WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC049/input_Basic Info_seller'), 'Value', testData.getValue(
            'Seller', row), 3)

    WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC049/input_Basic Info_receiver'), 'Value', testData.getValue(
            'Receiver', row), 3)

    WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC049/input_Basic Info_receivedDate'), 'Value', testData.getValue(
            'OrderDate', row), 3)

    WebUI.back() //WebUI.click(findTestObject('Scenario 10/S10_TC024/button_search AND'))
    //WebUI.setText(findTestObject('Scenario 10/S10_TC049/input_PO Monitoring List_Search'), searchString)
}

WebUI.closeBrowser()

