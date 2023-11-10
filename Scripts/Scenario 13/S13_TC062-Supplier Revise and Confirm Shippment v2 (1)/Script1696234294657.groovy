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

WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/button_Logistics'))

WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/li_Shipping Detail List'))

for (int row = 1; row <= numberCargo; row++) {
    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/input_Search Shipping List'), bookingID)

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_ContainerNo', [('rowNum') : row]), testData.getValue(
            'ContainerNo', row))

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_CargoStatus', [('rowNum') : row]), '')

    not_run: WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_Container Journey', [('rowNum') : row]), 
        'NO')

    if (testData.getValue('ContainerTrackJourney', row) == 'YES') {
        WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/button_Dt_Container Journey Tracking Detail', [('rowNum') : row]))

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC075/p22_The operation was successful'), 'The operation was successful.')

        WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_Dismiss'))

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_Container Journey', [('rowNum') : row]), 
            'YES')
    }
    
    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/button_Dt_edit shipping', [('rowNum') : row]))

    //--------------------------------------------Edit Page-----------------------------------------------------
    WebUI.waitForElementVisible(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/h3_Edit Shipping Detail'), 0)

    ATDdate = CustomKeywords.'util.changeFormatString.changeDateFormat2'(testData.getValue('ATD', row))

    (ATDday, ATDmonth, ATDyear) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt'(ATDdate)

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__atd date'))

    CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Calendar_Object/div_calendar header'), findTestObject(
            'Calendar_Object/button next calendar'), findTestObject('Calendar_Object/button previous calendar'), ATDday, 
        ATDmonth, ATDyear)

    ETAdate = CustomKeywords.'util.changeFormatString.changeDateFormat2'(testData.getValue('ETA', row))

    (ETAday, ETAmonth, ETAyear) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt'(ETAdate)

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__eta date'))

    CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Calendar_Object/div_calendar header'), findTestObject(
            'Calendar_Object/button next calendar'), findTestObject('Calendar_Object/button previous calendar'), ETAday, 
        ETAmonth, ETAyear)

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__BL_No'), testData.getValue('BLNo', row))

    BLdate = CustomKeywords.'util.changeFormatString.changeDateFormat2'(testData.getValue('BLDate', row))

    (BLday, BLmonth, BLyear) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt'(BLdate)

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__BL_Date'))

    CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Calendar_Object/div_calendar header'), findTestObject(
            'Calendar_Object/button next calendar'), findTestObject('Calendar_Object/button previous calendar'), BLday, 
        BLmonth, BLyear)

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__vesselName'), testData.getValue('VesselName', 
            row))

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__voyageNo'), testData.getValue('VoyageNo', 
            row))

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__cargoStatus'))

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/li_CargoStatus', [('cargoStatus') : testData.getValue(
                    'CargoStatus', row)]))

    CustomKeywords.'commonUtils.clearElementText'(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__m3'))

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__m3'), testData.getValue('M3', row))

    CustomKeywords.'commonUtils.clearElementText'(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__netWeight (Clear)'))

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__netWeight (Clear)'), testData.getValue(
            'NetWeight', row))

    CustomKeywords.'commonUtils.clearElementText'(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__grossWeight'))

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__grossWeight'), testData.getValue('GrossWeight', 
            row))

    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/button_Confirm 1'), 
        0)

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/button_CONFIRM 2'))

    'The operation was successful.'
    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC075/p22_The operation was successful'), 'The operation was successful.')

    WebUI.back(FailureHandling.STOP_ON_FAILURE)

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/input_Search Shipping List'), bookingID)

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_CargoStatus', [('rowNum') : row]), testData.getValue(
            'CargoStatus', row))

    CustomKeywords.'commonUtils.clearElementText'(findTestObject('Scenario 13/S13_TC062-Syazwan/input_Search Shipping List'))
}

WebUI.closeBrowser()

