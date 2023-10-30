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

WebUI.click(findTestObject('Scenario 12/SC12_TC014/p_detailsButton', [('SOnumber') : spotSalesOrderNo]))

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringDetail/h3_SO Monitoring Detail'), 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_MonitoringDetail/input_SalesOrderNo'), 'value', spotSalesOrderNo, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderType'), 
    'value', 'Spot', 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_contractNo'), 
    'value', contractNo, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderFrequency'), 
    'value', 'Weekly', 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring Detail - Brivge/textarea_TT REMITTANCE AT 60 DAYS FROM THE END OF BL MONTH'), 
    'value', paymentTermsDesc, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_status'), 
    'value', 'Received', 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_buyer'), 
    'value', GlobalVariable.BAF_COMPANY_BU, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_receiver'), 
    'value', GlobalVariable.BAF_COMPANY_DC, 0)

WebUI.click(findTestObject('Scenario 12/SC12_TC016/button_Parts Monitoring detail_step_2'))

WebUI.click(findTestObject('Scenario 12/SC12_TC016/button_download Parts Monitoring detail'))

WebUI.click(findTestObject('Scenario 12/SC12_TC016/li_Download DR'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC016/div_Download DR For Supplier.The operation was successful'), 
    0)

CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.takeFullPageScreenshot()

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Page_SO_MonitoringDetail/p_breadcrumb_SO Monitoring List'), 
    0)

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringList/h3_SO Monitoring List'), 0)

