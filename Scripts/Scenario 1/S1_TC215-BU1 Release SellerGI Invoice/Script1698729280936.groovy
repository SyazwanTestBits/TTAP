import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_BU1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Accounting'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/li_Seller(GI) Invoice List'))

for (String invoiceNo : invoiceNoList) {
    WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : invoiceNo]))

    WebUI.click(findTestObject('Scenario 12/SC12_TC056/button_release'))

    WebUI.click(findTestObject('Scenario 12/SC12_TC056/button_CONFIRMrelease'))

    WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC056/div_Do Release.The operation was successful'), 0)

    WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC056/p_verifyStatus', [('invoiceNo') : invoiceNo]), 'Released')

    KeywordUtil.logInfo("Verified Invoice No: $invoiceNo with status: Released")

    WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : invoiceNo]))
}

WebUI.closeBrowser()

