import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Accounting'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/li_Seller(GI) Invoice List'))

invoice1 = WebUI.getText(findTestObject('Scenario 17/S17_TC078/p_getInvoiceNo', [('outboundNo') : outboundNo1]))

invoice2 = WebUI.getText(findTestObject('Scenario 17/S17_TC078/p_getInvoiceNo', [('outboundNo') : outboundNo2]))

invoice3 = WebUI.getText(findTestObject('Scenario 17/S17_TC078/p_getInvoiceNo', [('outboundNo') : outboundNo3]))

println(invoice1)

println(invoice2)

println(invoice3)

CustomKeywords.'copyToExcel.exel'(invoice1, 1, 2, filePath, fileName, sheetName)

CustomKeywords.'copyToExcel.exel'(invoice2, 2, 2, filePath, fileName, sheetName)

CustomKeywords.'copyToExcel.exel'(invoice3, 3, 2, filePath, fileName, sheetName)

WebUI.delay(2)

'Approve'
for (String invoiceNo : invoiceNoList) {
	
    WebUI.click(findTestObject('Scenario 17/S17_TC078/p_EditInvoice', [('invoiceNo') : invoiceNo]))

    WebUI.click(findTestObject('Scenario 17/S17_TC078/span_Save'))

    WebUI.back()

	WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : invoiceNo])) 
	
	WebUI.click(findTestObject('Scenario 12/SC12_TC056/button_approve'))
	
	WebUI.click(findTestObject('Scenario 12/SC12_TC056/button_CONFIRMapprove'))
	
    WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC056/div_Do Approve.The operation was successful'), 0)
	
    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC056/p_verifyStatus', [('invoiceNo') : invoiceNo]), 'Approved')
	
    WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : invoiceNo]))
}

'Release'
for (String invoiceNo : invoiceNoList) {
	WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : invoiceNo]))

    WebUI.click(findTestObject('Scenario 12/SC12_TC056/button_release'))
	
	WebUI.click(findTestObject('Scenario 12/SC12_TC056/button_CONFIRMrelease'))
		
	WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC056/div_Do Release.The operation was successful'), 0)
	
    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC056/p_verifyStatus', [('invoiceNo') : invoiceNo]), 'Released')
	
    WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : invoiceNo]))
}

'\r\n'
WebUI.closeBrowser()

