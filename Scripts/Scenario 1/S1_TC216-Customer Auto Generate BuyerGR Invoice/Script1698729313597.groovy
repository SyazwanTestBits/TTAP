import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Accounting'))

WebUI.click(findTestObject('Navbar_Brivge/AccountingMenu_Brivge/li_Buyer(GR) Invoice List'))

WebUI.waitForElementVisible(findTestObject('Page_Buyer(GR)InvoiceList/h3_Buyer(GR) Invoice List'), 0)

for (String invoiceNo : invoiceNoList) {
    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC060/p_verifyStatus', [('invoiceNo') : invoiceNo]), 'Released')

    KeywordUtil.logInfo("Verified Invoice No: $invoiceNo with status: Released")
}

WebUI.closeBrowser()

