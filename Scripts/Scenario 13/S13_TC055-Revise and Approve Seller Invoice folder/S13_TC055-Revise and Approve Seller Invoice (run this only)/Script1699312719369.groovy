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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : 'admin'
        , ('password') : password, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Accounting'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/li_Seller(GI) Invoice List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC055/input_Seller(GI) Invoice'), outboundNo)

invoiceNo = WebUI.getText(findTestObject('Scenario 13/S13_TC055/Page_Brivge/p_InvoiceNo - based on outbound', [('outboundNo') : outboundNo]))

CustomKeywords.'copyToExcel.exel'(invoiceNo, 1, 1, filePath, fileName, sheetName)

WebUI.click(findTestObject('Scenario 13/S13_TC055/input_Checkbox'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/Page_Brivge/button_Edit'))

WebUI.waitForElementVisible(findTestObject('Scenario 13/S13_TC055/edit/h3_Seller(GI) Invoice Detail'), 0)

WebUI.callTestCase(findTestCase('Scenario 13/S13_TC055-Revise and Approve Seller Invoice folder/Zcmm-seller invoice Detail-01-placenDate, Consignee, shipping mark'), 
    [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Scenario 13/S13_TC055-Revise and Approve Seller Invoice folder/Zcmm-seller invoice Detail-02-INCOTERM BREAKDOWN'), 
    [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Scenario 13/S13_TC055-Revise and Approve Seller Invoice folder/Zcmm-seller invoice Detail-03- vessel, ETD_ETA, PAYMENT TERMS'), 
    [('testDataETD_ETA') : findTestData('Scenario 13/S13_TC052-Autogen Outbound Data')], FailureHandling.STOP_ON_FAILURE)

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC055/Page_Brivge/h6_Invoice Parts Detail Information'), 0)

for (int row = 1; row <= testDataPrice.getRowNumbers(); row++) {
    part_No = testDataPrice.getValue('Parts No.', row)

    String unitPrice = testDataPrice.getValue('Unit Price', row)

    CustomKeywords.'util.clearTextJS.clearElementText2'(findTestObject('Scenario 13/S13_TC055/Page_Brivge/input_Unit Price', 
            [('part_No') : part_No]))

    WebUI.setText(findTestObject('Scenario 13/S13_TC055/Page_Brivge/input_Unit Price', [('part_No') : part_No]), unitPrice)

    WebUI.click(findTestObject('Scenario 13/S13_TC055/Page_Brivge/h6_Invoice Parts Detail Information'), FailureHandling.STOP_ON_FAILURE)
}

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC055/Page_Brivge/button_Save'), 0)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC055/Page_Brivge/div_Save Invoice Detail.The operation was successful'), 
    0)

WebUI.back()

WebUI.setText(findTestObject('Scenario 13/S13_TC055/input_Seller(GI) Invoice'), outboundNo)

WebUI.click(findTestObject('Scenario 13/S13_TC055/input_Checkbox'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Approve'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Confirm'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC055/Page_Brivge/div_Status'), 'Approved')

WebUI.click(findTestObject('Scenario 13/S13_TC055/Page_Brivge/button_Release'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Confirm'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC055/Page_Brivge/div_Status'), 'Released')

WebUI.closeBrowser()

