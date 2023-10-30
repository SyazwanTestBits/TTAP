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

WebUI.click(findTestObject('Page_SO_MonitoringList/button_Dt_Detail', [('contractNo') : contractNo]))

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringDetail/h3_SO Monitoring Detail'), 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_MonitoringDetail/input_SalesOrderNo'), 'value', salesOrderNo, 
    0)

WebUI.click(findTestObject('Page_SO_MonitoringDetail/button_Parts Monitoring detail_step_2'))

WebUI.click(findTestObject('Page_SO_MonitoringDetail/button_DownloadOptions'))

WebUI.click(findTestObject('Page_SO_MonitoringDetail/li_Download DR'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadDRSupplier_Success'), 0)

CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.takeFullPageScreenshot()

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Page_SO_MonitoringDetail/p_breadcrumb_SO Monitoring List'), 
    0)

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringList/h3_SO Monitoring List'), 0)

