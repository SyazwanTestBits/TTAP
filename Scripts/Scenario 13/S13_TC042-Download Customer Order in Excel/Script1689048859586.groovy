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

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_CO Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_CO_MonitoringList/h3_CO Monitoring List'), 0)

WebUI.verifyElementText(findTestObject('Page_CO_MonitoringList/div_Dt_CustomerOrderNo', [('contractNo') : contractNo]), 
    customerOrderNo)

WebUI.verifyElementPresent(findTestObject('Page_CO_MonitoringList/input_checkbox_Dt_ContractNo', [('contractNo') : contractNo]), 
    0)

WebUI.check(findTestObject('Page_CO_MonitoringList/input_checkbox_Dt_ContractNo', [('contractNo') : contractNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_CO_MonitoringList/button_Dt_DownloadOptions'))

WebUI.click(findTestObject('Page_CO_MonitoringList/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadCustOrderbyExcel_Success'), 0)

CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

