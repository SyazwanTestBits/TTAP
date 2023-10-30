import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_SUP2], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Accounting'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/li_Seller(GI) Invoice List'))

WebUI.setText(findTestObject('Scenario 1/S1_TC032/input_Text'), contractNo)

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
    CustomKeywords.'copyToExcel.exel'(invoiceNo, rowIndex, 1, 'Excel Files\\Scenario 1', 'S1_TestCases_Data.xlsx', 'TC156-Sup2 SellerGI Invoice')
}

def invoiceNoEdit = testData.getValue('InvoiceNo', 1)

WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_editInvoice', [('invoiceNo') : invoiceNoEdit]))

WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_GRAND TOTAL'))

WebUI.setText(findTestObject('Scenario 12/SC12_TC056/input_Invoice Amount_invoiceAdjustmentAmount'), '150')

WebUI.setText(findTestObject('Scenario 12/SC12_TC056/input_Invoice Amount_invoiceAdjustmentReason'), 'adjust by 150')

WebUI.click(findTestObject('Scenario 12/SC12_TC056/button_OK'))

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 12/SC12_TC056/button_Save'), 0)

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC056/div_Save Invoice Detail.The operation was successful'), 
    0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 1/S1_TC124/Page_Seller(GI) Invoice Detail - Brivge/p_Seller(GI) Invoice List'), 
    0)

WebUI.setText(findTestObject('Scenario 1/S1_TC032/input_Text'), contractNo)

WebUI.click(findTestObject('Scenario 9/SC9_TC074/p_Invoice No'))

WebUI.click(findTestObject('Scenario 1/S1_TC088/Page_Seller(GI) Invoice List - Brivge/div_checkAll'))

WebUI.click(findTestObject('Scenario 9/SC9_TC074/button_approve'))

WebUI.click(findTestObject('Scenario 9/SC9_TC074/button_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC056/div_Do Approve.The operation was successful'), 0)

def invoiceNo1 = testData.getValue('InvoiceNo', 1)

def invoiceNo2 = testData.getValue('InvoiceNo', 2)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC132/Page_Seller(GI) Invoice List - Brivge/div_Status', [('row') : 1]), 
    'Approved', FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo("Verified Invoice No: $invoiceNo1 with status: Approved")

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC132/Page_Seller(GI) Invoice List - Brivge/div_Status', [('row') : 2]), 
    'Approved', FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo("Verified Invoice No: $invoiceNo2 with status: Approved")

WebUI.closeBrowser()

