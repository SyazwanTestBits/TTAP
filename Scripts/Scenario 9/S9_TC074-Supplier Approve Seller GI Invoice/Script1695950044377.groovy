import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Accounting'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/li_Seller(GI) Invoice List'))

// GET INVOICE NO
def invoiceNoList = []

def outboundNoList = []

WebUI.click(findTestObject('Scenario 9/SC9_TC074/p_Invoice No'))

for (int rowIndex = 1; rowIndex <= 3; rowIndex++) {
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
    CustomKeywords.'copyToExcel.exel'(invoiceNo, rowIndex, 1, 'Excel Files\\Scenario 9', 'S9_TestCases_Data.xlsx', 'TC74-Supplier Seller GI Invoice')
}

//COMBINE INVOICE
String inv1 = invoiceNoList[0]

KeywordUtil.logInfo("Invoice No 1: $inv1")

String inv3 = invoiceNoList[2]

KeywordUtil.logInfo("Invoice No 3: $inv3")

WebUI.delay(1)

WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : inv1]))

WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : inv3]))

WebUI.click(findTestObject('Scenario 12/SC12_TC056/button_combine'))

WebUI.click(findTestObject('Scenario 12/SC12_TC056/button_CONFIRMcombine'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC056/div_Do Combine.The operation was successful'), 0)

KeywordUtil.logInfo("$inv1 and $inv3 has been successfully combined")

def combinedString = (outbound3 + ',') + outbound1

println(combinedString)

combineInvoiceNo = WebUI.getText(findTestObject('Scenario 9/SC9_TC074/p_combineInvoice', [('combinedOutbound') : combinedString]))

CustomKeywords.'copyToExcel.exel'(combineInvoiceNo, 4, 1, 'Excel Files\\Scenario 9', 'S9_TestCases_Data.xlsx', 'TC74-Supplier Seller GI Invoice')

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC045/p_verifyStatusNew', [('invoiceNoSys') : combineInvoiceNo]), 
    'Combined')

WebUI.delay(2)

WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : findTestData('Scenario 9/S9_TC074-Supplier Seller GI Invoice').getValue(
                'InvoiceNo', 2)]))

WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : findTestData('Scenario 9/S9_TC074-Supplier Seller GI Invoice').getValue(
                'InvoiceNo', 4)]))

WebUI.click(findTestObject('Scenario 9/SC9_TC074/button_approve'))

WebUI.click(findTestObject('Scenario 9/SC9_TC074/button_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC056/div_Do Approve.The operation was successful'), 0)

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC056/p_verifyStatus', [('invoiceNo') : findTestData('Scenario 9/S9_TC074-Supplier Seller GI Invoice').getValue(
                'InvoiceNo', 2)]), 'Approved', FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC056/p_verifyStatus', [('invoiceNo') : findTestData('Scenario 9/S9_TC074-Supplier Seller GI Invoice').getValue(
                'InvoiceNo', 4)]), 'Approved', FailureHandling.CONTINUE_ON_FAILURE)

WebUI.closeBrowser()

