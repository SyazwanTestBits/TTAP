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
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 10/S10_TC024/button_Stock Management Tool'))

WebUI.click(findTestObject('Navbar_Brivge/StockMgmtToolMenu_Brivge/li_Order Calculation List'))

WebUI.waitForElementPresent(findTestObject('Page_OrderCalcList/h3_Order Calculation List'), 0)

WebUI.setText(findTestObject('Page_OrderCalcList/input_Search_OrderCalculationList'), ocgn)

WebUI.click(findTestObject('Page_OrderCalcList/tr_td_OrderCalcGroupNo_CreateCalc', [('ocgn') : ocgn]))

WebUI.waitForElementPresent(findTestObject('Page_OrderCalcList/Page_CreateOrderCalculation/h3_Create Order Calculation'), 
    0)

WebUI.click(findTestObject('Page_OrderCalcList/Page_CreateOrderCalculation/input__cutoffDate'), FailureHandling.STOP_ON_FAILURE)

(day, month, year) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt_withFormat_ChineseLocale'(cutOffDateFormatted, 'MMM d, yyyy')

CustomKeywords.'util.handlePicker2.handleCalendar_CannotPrevious'(findTestObject('Scenario 13/S13_TC033_Shipping_Route/div_calendar header'), 
    day, month, year)

not_run: WebUI.verifyElementAttributeValue(findTestObject('Page_OrderCalcList/Page_CreateOrderCalculation/input__cutoffDate'), 
    'value', cutOffDateFormatted, 0)

WebUI.click(findTestObject('Page_OrderCalcList/Page_CreateOrderCalculation/input_tr1_Checkbox_UsageHistory', [('customerUsage') : customerUsage]))

WebUI.click(findTestObject('Page_OrderCalcList/Page_CreateOrderCalculation/button_calculate'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_RunOrderCalculation_Success'), 0)

WebUI.setText(findTestObject('Page_OrderCalcList/input_Search_OrderCalculationList'), ocgn)

WebUI.verifyElementText(findTestObject('Page_OrderCalcList/tr_td_OrderCalcGroupNo_Status', [('ocgn') : ocgn]), 'PENDING ORDER')

ocRefNo = WebUI.getText(findTestObject('Page_OrderCalcList/tr_td_OrderCalcGroupNo_OCRefNo', [('ocgn') : ocgn]), FailureHandling.STOP_ON_FAILURE)

s10excel_abspath = CustomKeywords.'ManageFiles.getFileAbsolutePath'('Excel Files/Scenario 10/S10_TestCases_Data.xlsx')

CustomKeywords.'copyToExcel.exel4'(ocRefNo, 1, 8, s10excel_abspath, 'TC034-Create Order Calculation')

WebUI.verifyElementText(findTestObject('Page_OrderCalcList/tr_td_OrderCalcGroupNo_CustomerUsage', [('ocgn') : ocgn]), customerUsage)

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

