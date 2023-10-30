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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.CUST_USERNAME_USERF
        , ('password') : GlobalVariable.CUST_PWD_USERF, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.COMPANY_SUPPLIER_1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Accounting'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/li_Seller(GI) Invoice List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC055/input_Seller(GI) Invoice'), contractNo)

invoiceNo = WebUI.getText(findTestObject('Scenario 13/S13_TC055/Page_Brivge/p_InvoiceNo'))

CustomKeywords.'copyToExcel.exel'(invoiceNo, 1, 1, filePath, fileName, sheetName)

WebUI.click(findTestObject('Scenario 13/S13_TC055/input_Checkbox'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/Page_Brivge/button_Edit'))

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC055/Page_Brivge/h6_Invoice Parts Detail Information'), 0)

CustomKeywords.'commonUtils.clearElementText'(findTestObject('Scenario 13/S13_TC055/Page_Brivge/input_Unit Price'))

WebUI.setText(findTestObject('Scenario 13/S13_TC055/Page_Brivge/input_Unit Price'), '0.01')

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC055/Page_Brivge/button_Save'), 0)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC055/Page_Brivge/div_Save Invoice Detail.The operation was successful'), 
    0)

WebUI.back()

WebUI.setText(findTestObject('Scenario 13/S13_TC055/input_Seller(GI) Invoice'), contractNo)

WebUI.click(findTestObject('Scenario 13/S13_TC055/input_Checkbox'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Approve'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Confirm'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC055/Page_Brivge/div_Status'), 'Approved')

not_run: WebUI.click(findTestObject('Scenario 13/S13_TC055/Page_Brivge/button_Release'))

not_run: WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Confirm'))

not_run: WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC055/Page_Brivge/div_Status'), 'Released')

WebUI.closeBrowser()

