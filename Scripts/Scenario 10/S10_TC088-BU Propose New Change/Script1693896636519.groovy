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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_SYAZWAN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Scenario 12/SC12_TC035/li_ChangeCancel Request List'))

WebUI.waitForElementPresent(findTestObject('Scenario 12/SC12_TC035/h3_ChangeCancel Request List'), 0)

'Verify Order Change with Status = New\r\n'
WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC034/p_Order Change', [('requestNo') : requestNo]), 'New')

WebUI.click(findTestObject('Scenario 12/SC12_TC019/p_detailButton', [('requestNo') : requestNo]))

WebUI.click(findTestObject('Scenario 10/S10_TC088/button_Propose New'))

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC088/button_edit3rdDate'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC088/input__newSuppOutboundPlanDateV2'))

(firstday, firstmonth, firstyear) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt_withFormat'(dateChange, 
    'MMM d, yyyy')

println(firstday)

println(firstmonth)

println(firstyear)

not_run: int firstday = Integer.parseInt(DayFirst)

not_run: int firstmonth = Integer.parseInt(MonthFirst)

not_run: int firstyear = Integer.parseInt(YearFirst)

CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Scenario 13/S13_TC033_Shipping_Route/div_calendar header'), 
    findTestObject('Calendar_Object/button next calendar'), findTestObject('Calendar_Object/button previous calendar'), 
    firstday, firstmonth, firstyear)

WebUI.click(findTestObject('Scenario 10/S10_TC088/button_ConfirmDate'))

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC088/button_refresh'), 0)

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC088/div_Refresh Inbound Info.The operation was successful'), 
    0)

WebUI.setText(findTestObject('Scenario 10/S10_TC088/textarea_Input Reject Reason_lcbm-lcbm2348'), 'Change Date ')

WebUI.click(findTestObject('Scenario 10/S10_TC088/button_Confirm'))

WebUI.delay(1)

WebUI.click(findTestObject('Scenario 10/S10_TC088/button_doubleCONFIRM'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC088/div_Propose New Confirm.The operation was successful'), 
    0)

WebUI.waitForElementPresent(findTestObject('Scenario 10/S10_TC088/h3_ChangeCancel Request Detail'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC088/p_ChangeCancel Request List'))

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC088/p_verifyStatus', [('requestNo') : requestNo]), 'Propose New')

//
WebUI.closeBrowser()

