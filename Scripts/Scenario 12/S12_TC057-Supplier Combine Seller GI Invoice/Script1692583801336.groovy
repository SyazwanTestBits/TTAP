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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Accounting'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/li_Seller(GI) Invoice List'))

WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : invoiceCombine1]))

WebUI.click(findTestObject('Scenario 12/SC12_TC056/p_checkboxSelect', [('invoiceNo') : invoiceCombine2]))

WebUI.click(findTestObject('Scenario 12/SC12_TC056/button_combine'))

WebUI.click(findTestObject('Scenario 12/SC12_TC056/button_CONFIRMcombine'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC056/div_Do Combine.The operation was successful'), 0)

combineInvoiceNo = WebUI.getText(findTestObject('Scenario 12/SC12_TC056/p_combineInvoice'))

CustomKeywords.'copyToExcel.exel'(combineInvoiceNo, 5, 2, 'Excel Files\\Scenario 12', 'S12_TestCases_Data.xlsx', 'TC45-Supplier SellerGI Invoice')

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC056/p_combineInvoiceStatus'), 'Combined')

WebUI.closeBrowser()

