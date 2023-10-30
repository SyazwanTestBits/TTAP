import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_BU1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Customs Invoice List(Import)'))

WebUI.waitForElementPresent(findTestObject('Page_CustomsInvoice_Import/h3_Customs Invoice(Import)'), 0)

for (String invoiceNo : invoiceNoList) {
    WebUI.click(findTestObject('Scenario 12/SC12_TC065/p_editButton', [('invoiceNo') : invoiceNo]))

    WebUI.click(findTestObject('Scenario 12/SC12_TC065/svg_Input Cargo Status_lcbm-MuiSvgIcon-root'))

    WebUI.click(findTestObject('Scenario 12/SC12_TC065/li_Imp clearance completed'))

    WebUI.click(findTestObject('Scenario 12/SC12_TC065/button_update', [('invoiceNo') : invoiceNo]))

    WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC065/div_Update Cargo Status.The operation was successful'), 
        0)

    WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

    WebUI.delay(1)

    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC063/p_verifyCargoStatus', [('invoiceNo') : invoiceNo]), 'Imp clearance completed')

    KeywordUtil.logInfo("Verified Invoice No: $invoiceNo with Cargo Status: Imp clearance completed")

    WebUI.click(findTestObject('Scenario 12/SC12_TC065/p_completeButton', [('invoiceNo') : invoiceNo]))

    WebUI.click(findTestObject('Scenario 12/SC12_TC065/button_CONFIRM'))

    WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC065/div_Completed Customs Clearance.The operation was successful'), 
        0)

    WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

    WebUI.delay(1)

    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC063/p_verifyStatus', [('invoiceNo') : invoiceNo]), 'Customs Clearance Completed')

    KeywordUtil.logInfo("Verified Invoice No: $invoiceNo with Status: Customs Clearance Completed")
}

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

