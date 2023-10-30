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

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/li_Announcements List'))

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/button_create announcement'))

WebUI.setText(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/input__announcement header'), announceHeader)

WebUI.setText(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/input__announcement description'), announceDesc)

WebUI.setText(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/input__announcement content'), announceContent)

newstartDate = CustomKeywords.'util.changeFormatString.changeDateFormat2'(testData.getValue('StartDate', 1))

(daySD, monthSD, yearSD) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt'(newstartDate)

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/input__start DATE'), FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'util.handlePicker2.handleCalendar_CannotPrevious'(findTestObject('Calendar_Object/div_calendar header'), 
    daySD, monthSD, yearSD)

String currentime = LocalTime.now()

(hour_notImportant, currentMinute) = CustomKeywords.'GetCurrentTime.parseHourAndMinute'(currentime)

String classminutePointer = 'lcbm-MuiPickersClockPointer-pointer lcbm-MuiPickersClockPointer-animateTransform'

not_run: if ((currentMinute % 5) == 0) {
    not_run: classminutePointer = 'lcbm-MuiPickersClockPointer-thumb lcbm-MuiPickersClockPointer-noPoint'
} else {
}

not_run: classminutePointer = 'lcbm-MuiPickersClockPointer-thumb'

not_run: classminutePointer = 'lcbm-MuiPickersClockPointer-thumb'

startcurrentTime = CustomKeywords.'GetCurrentTime.currentTimeNext_5Minute'()

(starthour, startminute) = CustomKeywords.'GetCurrentTime.parseHourAndMinute'(startcurrentTime)

starthour2 = (starthour + 1)

startminute2 = ((startminute / 5) + 1)

WebUI.dragAndDropToObject(findTestObject('clock/clock pointer hour'), findTestObject('clock/div_Confirm_lcbm-MuiPickersClock-Hour', 
        [('hour') : starthour2]))

WebUI.dragAndDropToObject(findTestObject('clock/clock pointer minute', [('class_minute_pointer') : classminutePointer]), 
    findTestObject('clock/div_Confirm_lcbm-MuiPickersClock-minute', [('minute') : startminute2]))

//-------------------------------------end date------------------------------------------------------------------------------------------
newendDate = CustomKeywords.'util.changeFormatString.changeDateFormat2'(testData.getValue('EndDate', 1))

(dayED, monthED, yearED) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt'(newendDate)

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/input__expire DATE'), FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'util.handlePicker2.handleCalendar_CannotPrevious'(findTestObject('Calendar_Object/div_calendar header'), 
    dayED, monthED, yearED)

//endTime = CustomKeywords.'GetCurrentTime.currentTimeNext_5Minute'()
(endhour, endminute) = CustomKeywords.'GetCurrentTime.parseHourAndMinute'(startcurrentTime)

endhour2 = (endhour + 1)

endminute2 = ((endminute / 5) + 1)

WebUI.dragAndDropToObject(findTestObject('clock/clock pointer hour'), findTestObject('clock/div_Confirm_lcbm-MuiPickersClock-Hour', 
        [('hour') : endhour2]))

WebUI.dragAndDropToObject(findTestObject('clock/clock pointer minute', [('class_minute_pointer') : classminutePointer]), 
    findTestObject('clock/div_Confirm_lcbm-MuiPickersClock-minute', [('minute') : endminute2]))

//--------------------------------------------------------------------------------------------------------------------------------------------------
WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/input_Customs'))

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/svg_CUSTOM-add user'), 0)

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/svg_CUSTOM-add user'))

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/button_CUSTOM_AND'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/input_CUSTOM-user-search user'), 
    0)

'userf syazwanrusdi-pna'
WebUI.setText(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/input_CUSTOM-user-search user'), user[1])

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/test/input_CUSTOM-user-tick all TEST'))

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/button_CUSTOM-user-Confirm'))

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/button_CUSTOM-add company'))

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/button_CUSTOM_AND'))

WebUI.setText(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/input_CUSTOM-Company-search company'), 
    companySUP)

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/test/input_CUSTOM-user-tick all TEST'))

WebUI.click(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/button_CUSTOM-Company-confirm'))

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/button_save creation announcement'), 
    0)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/p_The operation was successful'), 
    0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC022 and TC23/Create announcement/p_back to Announcements List'), 
    0)

WebUI.setText(findTestObject('Scenario 13/S13_TC022 and TC23/input_Announcement List search'), announceHeader)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC022 and TC23/div_status'), 'DRAFT')

WebUI.closeBrowser()

