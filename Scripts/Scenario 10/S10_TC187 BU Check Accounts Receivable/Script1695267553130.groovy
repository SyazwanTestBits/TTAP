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

String numrowString = testData.getRowNumbers()

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : usernameLogin
        , ('password') : passwordLogin, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : companyLogin], 
    FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC187 BU Check Accounts Receivable/div_Accounts Receivable'), 
    0)

not_run: WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC187 BU Check Accounts Receivable/div_Outstanding Invoices (Not Cleared) - Copy'), 
    numrowString)

not_run: WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC187 BU Check Accounts Receivable/div_Overdue Invoices (Not Cleared)'), 
    numrowString)

WebUI.scrollToElement(findTestObject('Scenario 10/S10_TC187 BU Check Accounts Receivable/div_Invoices(Not Cleared)'), 0)

for (int i = 1; i <= testData.getRowNumbers(); i++) {
    invoiceNo = testData.getValue('Invoice No', i)

    WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC187 BU Check Accounts Receivable/div_Td-InvoiceNumber', 
            [('invoiceNo') : invoiceNo]), 0)

    for (int col = 2; col <= 6; col++) {
        datavalue = testData.getValue(col, i)
		
		println(datavalue)

        not_run: WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC187 BU Check Accounts Receivable/div_Td-InvoiceNumber -following td', 
                [('col') : col, ('invoiceNo') : invoiceNo]), datavalue)

        actual=WebUI.getText(findTestObject('Scenario 10/S10_TC187 BU Check Accounts Receivable/div_Td-InvoiceNumber -following td', 
                [('col') : col, ('invoiceNo') : invoiceNo]), FailureHandling.STOP_ON_FAILURE)
		
		println(actual)
    }
}

WebUI.closeBrowser()

