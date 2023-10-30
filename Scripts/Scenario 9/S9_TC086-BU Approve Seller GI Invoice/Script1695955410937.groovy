import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
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
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Accounting'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/li_Seller(GI) Invoice List'))

// GET INVOICE NO
def invoiceNoList = []

def outboundNoList = []

WebUI.click(findTestObject('Scenario 9/SC9_TC074/p_Invoice No'))

for (int rowIndex = 1; rowIndex <= 2; rowIndex++) {
    String outboundNo = testData.getValue('OutboundNo', rowIndex)

    outboundNoList.add(outboundNo)

    // Get the invoiceNo using WebUI.getText
    TestObject invoiceNoTestObject = findTestObject('Scenario 12/SC12_TC045/p_getInvoiceNo', [('outboundNoSys') : outboundNo])

    String invoiceNo = WebUI.getText(invoiceNoTestObject)

    invoiceNoList.add(invoiceNo)

    KeywordUtil.logInfo("Verified Invoice No: $invoiceNo for Outbound No $outboundNo")

    // Verify the status is "New"
    TestObject verifyStatusTestObject = findTestObject('Scenario 12/SC12_TC045/p_verifyStatusNew', [('invoiceNoSys') : invoiceNo])

    WebUI.verifyElementText(verifyStatusTestObject, 'New', FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo("Verified Invoice No: $invoiceNo with status: New")

    // Copy the invoice number to Excel
    CustomKeywords.'copyToExcel.exel'(invoiceNo, rowIndex, 1, 'Excel Files\\Scenario 9', 'S9_TestCases_Data.xlsx', 'TC86-BU Seller GI Invoice')
}

WebUI.delay(2)

String invoiceToEdit = invoiceNoList[0]

KeywordUtil.logInfo("Invoice No to Edit: $invoiceToEdit")

WebUI.click(findTestObject('Scenario 9/SC9_TC086/p_edit', [('invoiceNo') : invoiceToEdit]))

WebUI.verifyElementText(findTestObject('Scenario 9/SC9_TC086/p_verifyInvoiceNo'), invoiceToEdit)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 9/SC9_TC086/p_GRAND TOTAL'), 0)

WebUI.setText(findTestObject('Scenario 9/SC9_TC086/input_Invoice Amount_invoiceAdjustmentAmount'), '100')

WebUI.setText(findTestObject('Scenario 9/SC9_TC086/input_Invoice Amount_invoiceAdjustmentReason'), 'Incoterms Adjustment')

WebUI.click(findTestObject('Scenario 9/SC9_TC086/button_OK'))

WebUI.click(findTestObject('Scenario 9/SC9_TC086/button_Save'))

WebUI.verifyElementPresent(findTestObject('Scenario 9/SC9_TC086/div_Save Invoice Detail.The operation was successful'), 
    0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 9/SC9_TC086/p_Seller(GI) Invoice List'), 0)

WebUI.delay(2)

for (String invoiceNo : invoiceNoList) {
    WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : invoiceNo]))

    WebUI.click(findTestObject('Scenario 12/SC12_TC056/button_approve'))

    WebUI.click(findTestObject('Scenario 12/SC12_TC056/button_CONFIRMapprove'))

    WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC056/div_Do Approve.The operation was successful'), 0)

    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC056/p_verifyStatus', [('invoiceNo') : invoiceNo]), 'Approved')

    WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : invoiceNo]))
}

WebUI.closeBrowser()

