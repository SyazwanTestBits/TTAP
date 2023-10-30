import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_SYAZWAN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_DC], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 10/S10_TC159/button_Logistics'))

WebUI.click(findTestObject('Scenario 10/S10_TC164/li_Download Outbound Instruction'))

WebUI.waitForElementPresent(findTestObject('Scenario 10/S10_TC164/h3_Download Outbound Prioritization'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC164/input_Download_onbPlanDateStartFormat'))

(firstdayetd, firstmonthetd, firstyearetd) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt_withFormat'(dateETD, 
    'd MMM yyyy')

not_run: int firstdayetd = Integer.parseInt(DayFirstETD)

not_run: int firstmonthetd = Integer.parseInt(MonthFirstETD)

not_run: int firstyearetd = Integer.parseInt(YearFirstETD)

CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Scenario 13/S13_TC033_Shipping_Route/div_calendar header'), 
    findTestObject('Scenario 10/S10_TC164/button_nextMonth'), findTestObject('Scenario 10/S10_TC164/button_prevMonth'), 
    firstdayetd, firstmonthetd, firstyearetd)

WebUI.click(findTestObject('Scenario 10/S10_TC164/input_Download_customerId'))

WebUI.click(findTestObject('Scenario 10/S10_TC164/li_MY-PNA-CUS'))

WebUI.click(findTestObject('Scenario 10/S10_TC164/input_Download_receiverUid'))

WebUI.click(findTestObject('Scenario 10/S10_TC164/li_MY-PNA-CUS'))

WebUI.click(findTestObject('Scenario 10/S10_TC164/button_Download'))

WebUI.click(findTestObject('Scenario 10/S10_TC164/li_Download By Parts'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC164/div_Download Outbound Priority By Parts.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

KeywordUtil.logInfo("Downloaded File Path: $downloadedFile")

replacedFilePath = CustomKeywords.'ManageFiles.replaceExpectedFileWithDownloadedFile'(downloadedFile, 'Excel Files\\Scenario 10\\S10-TC164-OutboundInstruction.xlsx')

if (replacedFilePath != null) {
    KeywordUtil.logInfo("Replaced File Path: $replacedFilePath")
} else {
    KeywordUtil.markFailedAndStop('Downloaded file does not exist.')
}

WebUI.delay(5)

WebUI.verifyCheckpoint(findCheckpoint('Checkpoints/Scenario 10/S10_TC164'), true)

WebUI.closeBrowser()

