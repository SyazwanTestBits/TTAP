import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Customs Invoice List(Import)'))

WebUI.waitForElementPresent(findTestObject('Page_CustomsInvoice_Import/h3_Customs Invoice(Import)'), 0)

for (int row=1;row<=testData.getRowNumbers();row++) {
	
	String invoiceNo=testData.getValue("InvoiceNo",row)
	
    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC063/p_verifyStatus', [('invoiceNo') : invoiceNo]), 'Pending Customs Clearance')

    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC063/p_verifyCargoStatus', [('invoiceNo') : invoiceNo]), '')
	
	KeywordUtil.logInfo("Verified Invoice No: $invoiceNo with status: Pending Customs Clearance")
}

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

