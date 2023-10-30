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

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Outbound Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_OutboundMonitoringList/h3_Outbound Monitoring List'), 0)

WebUI.click(findTestObject('Page_OutboundMonitoringList/button_Dt_Row_ViewOutboundDetail'))

WebUI.waitForElementPresent(findTestObject('Page_OutboundMonitoringList/Page_OutboundMonitoringDetail_View/h3_Outbound Monitoring Detail'), 
    0)

outboundNo = WebUI.getAttribute(findTestObject('Page_OutboundMonitoringList/Page_OutboundMonitoringDetail_View/input_Outbound Basic Info_outboundNo'), 
    'value')

etd = WebUI.getAttribute(findTestObject('Page_OutboundMonitoringList/Page_OutboundMonitoringDetail_View/input_Outbound Basic Info_etd'), 
    'value')

eta = WebUI.getAttribute(findTestObject('Page_OutboundMonitoringList/Page_OutboundMonitoringDetail_View/input_Outbound Basic Info_eta'), 
    'value')

CustomKeywords.'copyToExcel.exel'(outboundNo, 1, 1, 'Excel Files\\Scenario 13', 'S13_TestCases_Data.xlsx', 'TC52-Autogen Outbound Data')

CustomKeywords.'copyToExcel.exel'(etd, 1, 2, 'Excel Files\\Scenario 13', 'S13_TestCases_Data.xlsx', 'TC52-Autogen Outbound Data')

CustomKeywords.'copyToExcel.exel'(eta, 1, 3, 'Excel Files\\Scenario 13', 'S13_TestCases_Data.xlsx', 'TC52-Autogen Outbound Data')

WebUI.closeBrowser()

