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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_SUPPLIER2], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Accounting'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/li_Seller(GI) Invoice List'))

WebUI.setText(findTestObject('Page_InvoiceList/input_Seller(GI) Invoice List_search'), contractID)

for (String invoiceNo : invoiceNoList_copyFromExcel) {
    WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : invoiceNo]), FailureHandling.CONTINUE_ON_FAILURE)

    WebUI.click(findTestObject('Scenario 12/SC12_TC056/button_approve'), FailureHandling.CONTINUE_ON_FAILURE)

    WebUI.click(findTestObject('Scenario 12/SC12_TC056/button_CONFIRMapprove'), FailureHandling.CONTINUE_ON_FAILURE)

    WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC056/div_Do Approve.The operation was successful'), 0, 
        FailureHandling.CONTINUE_ON_FAILURE)

    WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC056/p_verifyStatus', [('invoiceNo') : invoiceNo]), 'Approved', 
        FailureHandling.CONTINUE_ON_FAILURE)

    WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : invoiceNo]), FailureHandling.CONTINUE_ON_FAILURE)
}

WebUI.closeBrowser()

