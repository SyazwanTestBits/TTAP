import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Accounting'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/li_Seller(GI) Invoice List'))

def invoiceNoList = []

def outboundNoList = []

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

    WebUI.verifyElementText(verifyStatusTestObject, 'New', FailureHandling.CONTINUE_ON_FAILURE)

    KeywordUtil.logInfo("Verified Invoice No: $invoiceNo with status: New")

    // Copy the invoice number to Excel
    CustomKeywords.'copyToExcel.exel'(invoiceNo, rowIndex, 0, 'Excel Files\\Scenario 10', 'S10_TestCases_Data.xlsx', 'TC172-Seller GI Invoice')
}

WebUI.closeBrowser()

