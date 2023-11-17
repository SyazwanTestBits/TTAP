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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : password, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC055/button_Accounting'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/li_Seller(GI) Invoice List'))

WebUI.waitForElementPresent(findTestObject('Page_Seller(GI)_InvoiceList/h3_Seller(GI) Invoice List'), 0)

WebUI.setText(findTestObject('Scenario 13/S13_TC055/input_Seller(GI) Invoice'), invoiceNo)

WebUI.check(findTestObject('Scenario 13/S13_TC055/input_Checkbox'))

'Approved'
WebUI.verifyElementText(findTestObject('Page_Seller(GI)_InvoiceList/div_Dt_Seller(GI)_InvList_Status', [('invoiceNo') : invoiceNo]), 
    'Approved')

WebUI.click(findTestObject('Scenario 13/S13_TC055/Page_Brivge/button_Release'))

WebUI.waitForElementVisible(findTestObject('NotificationMsg_Brivge/div_NotiMsg_AreYouSureToDo_Release'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DoRelease_Success'), 0)

WebUI.verifyElementText(findTestObject('Page_Seller(GI)_InvoiceList/div_Dt_Seller(GI)_InvList_Status', [('invoiceNo') : invoiceNo]), 
    'Released')

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

