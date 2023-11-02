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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_SO Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringList/h3_SO Monitoring List'), 0)

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), contractNo)

WebUI.verifyElementPresent(findTestObject('Page_SO_MonitoringList/div_Dt_ContractNo', [('contractNo') : contractNo]), 0)

not_run: regularSalesOrderNo = WebUI.getText(findTestObject('Page_SO_MonitoringList/td_SalesOrder_firstRow'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/input_checkall'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_download'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC014/div_Download Sales Order by Excel.The operation was successful'), 
    0)

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Scenario 12/SC12_TC014/p_detailsButton', [('SOnumber') : regularSalesOrderNo]))

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringDetail/h3_SO Monitoring Detail'), 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_MonitoringDetail/input_SalesOrderNo'), 'value', regularSalesOrderNo, 
    0)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_Parts Monitoring detail_step_2'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_downloadPartsMonitoringDetail'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download Price'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC014/div_Download Price.The operation was successful'), 0)

CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.takeFullPageScreenshot()

not_run: CustomKeywords.'copyToExcel.exel'(regularSalesOrderNo, 1, 2, 'Excel Files\\Scenario 1', 'S1_TestCases_Data.xlsx', 
    sheetname)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Page_SO_MonitoringDetail/p_breadcrumb_SO Monitoring List'), 
    0)

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringList/h3_SO Monitoring List'), 0)

WebUI.closeBrowser()

