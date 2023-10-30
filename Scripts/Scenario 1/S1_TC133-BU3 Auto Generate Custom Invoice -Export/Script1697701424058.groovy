import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_BU3], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Customs Invoice List(Export)'))

WebUI.waitForElementPresent(findTestObject('Page_CustomsInvoice_Export/h3_Customs Invoice(Export)'), 0)

WebUI.setText(findTestObject('Scenario 1/S1_TC032/input_Text'), ((invoiceNoList[0]) + ' ') + (invoiceNoList[1]))

WebUI.click(findTestObject('Scenario 12/SC12_TC068/button_AND'))

for (String invoiceNo : invoiceNoList) {
    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC063/p_verifyStatus', [('invoiceNo') : invoiceNo]), 'Pending Customs Clearance')
	
	KeywordUtil.logInfo("Verified Invoice No: $invoiceNo with Status: Pending Customs Clearance")

    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC063/p_verifyCargoStatus', [('invoiceNo') : invoiceNo]), '')
	
	KeywordUtil.logInfo("Verified Invoice No: $invoiceNo with Cargo Status: Empty")
}

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

