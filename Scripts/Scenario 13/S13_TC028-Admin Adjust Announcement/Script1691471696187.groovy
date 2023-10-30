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
import java.time.LocalTime as LocalTime

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.ADMIN_USERNAME
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.ADMIN_COMPANY], 
    FailureHandling.STOP_ON_FAILURE)

//announcement_header ='syazwanthunder2'
WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/li_Announcements List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC022 and TC23/input_Announcement List search'), announcement_header)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC022 and TC23/div_status'), 'PUBLISHED')

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/button_edit_announcement'))

newEndDate = CustomKeywords.'util.changeFormatString.changeDateFormat2'(testData.getValue('StartDate', 1))

(dayED, monthED, yearED) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt'(newEndDate)

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/input__expire DATE'))

CustomKeywords.'util.handlePicker2.handleCalendar_CannotPrevious'(findTestObject('Calendar_Object/div_calendar header'), 
    dayED, monthED, yearED)

String currentime = LocalTime.now()

(hour_notImportant, currentMinute) = CustomKeywords.'GetCurrentTime.parseHourAndMinute'(currentime)

//String classminutePointer = 'lcbm-MuiPickersClockPointer-thumb lcbm-MuiPickersClockPointer-noPoint'
startcurrentTime = CustomKeywords.'GetCurrentTime.currentTimeNext_5Minute'()

(starthour, startminute) = CustomKeywords.'GetCurrentTime.parseHourAndMinute'(startcurrentTime)

starthour2 = (starthour + 1)

startminute2 = ((startminute / 5) + 1)

WebUI.dragAndDropToObject(findTestObject('clock/clock pointer hour'), findTestObject('clock/div_Confirm_lcbm-MuiPickersClock-Hour', 
        [('hour') : starthour2]))

WebUI.dragAndDropToObject(findTestObject('clock/clock pointer minute', [('class_minute_pointer') : 'lcbm-MuiPickersClockPointer-thumb lcbm-MuiPickersClockPointer-noPoint']), 
    findTestObject('clock/div_Confirm_lcbm-MuiPickersClock-minute', [('minute') : startminute2]))

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/button_ADJUST'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/p_The operation was successful'), 
    'The operation was successful.')

WebUI.back()

WebUI.setText(findTestObject('Scenario 13/S13_TC022 and TC23/input_Announcement List search'), announcement_header)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC022 and TC23/div_Display End Time'), testData.getValue('StartDate', 
        1))

