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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.CUST_USERNAME_USERF
        , ('password') : GlobalVariable.CUST_PWD_USERF, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.COMPANY_SUPPLIER_1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/button_Logistics'))

WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/li_Shipping Detail List'))

for (int rowNum = 1; rowNum <= noRows; rowNum++) {
    String containerNo = testData.getValue('ContainerNo', rowNum)

    String cargoStatus = testData.getValue('CargoStatus', rowNum)

    String containerTrackJourney = testData.getValue('ContainerTrackJourney', rowNum)

    ATD = CustomKeywords.'util.changeFormatString.changeDateFormat2'(testData.getValue('ATD', rowNum))

    (dayATD, monthATD, yearATD) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt'(ATD)

    ETA = CustomKeywords.'util.changeFormatString.changeDateFormat2'(testData.getValue('ETA', rowNum))

    (dayETA, monthETA, yearETA) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt'(ETA)

    String BLNo = testData.getValue('BLNo', rowNum)

    BLDate = CustomKeywords.'util.changeFormatString.changeDateFormat2'(testData.getValue('BLDate', rowNum))

    (dayBLDate, monthBLDate, yearBLDate) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt'(BLDate)

    String VesselName = testData.getValue('VesselName', rowNum)

    String VoyageNo = testData.getValue('VoyageNo', rowNum)

    def M3 = testData.getValue('M3', rowNum)

    def NetWeight = testData.getValue('NetWeight', rowNum)

    def GrossWeight = testData.getValue('GrossWeight', rowNum)

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/input_Search Shipping List'), bookingID)

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_BookingNo', [('rowNum') : rowNum]), bookingID)

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_ContainerNo', [('rowNum') : rowNum]), containerNo)

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_CargoStatus', [('rowNum') : rowNum]), '')

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_Container Journey', [('rowNum') : rowNum]), 
        'NO')

    if (testData.getValue('ATD', rowNum) == 'YES') {
        WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/button_Dt_Container Journey Tracking Detail', [('rowNum') : rowNum]))

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC075/p22_The operation was successful'), 'The operation was successful.')

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_Container Journey', [('rowNum') : rowNum]), 
            'YES')
    }
    
    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/button_Dt_edit shipping', [('rowNum') : rowNum]))

    WebUI.waitForElementVisible(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/h3_Edit Shipping Detail'), 0)

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__atd date'))

    CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Calendar_Object/div_calendar header'), findTestObject(
            'Calendar_Object/button next calendar'), findTestObject('Calendar_Object/button previous calendar'), dayATD, 
        monthATD, yearATD)

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__eta date'))

    CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Calendar_Object/div_calendar header'), findTestObject(
            'Calendar_Object/button next calendar'), findTestObject('Calendar_Object/button previous calendar'), dayETA, 
        monthETA, yearETA)

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__BL_No'), BLNo)

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__BL_Date'))

    CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Calendar_Object/div_calendar header'), findTestObject(
            'Calendar_Object/button next calendar'), findTestObject('Calendar_Object/button previous calendar'), dayBLDate, 
        monthBLDate, yearBLDate)

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__vesselName'), VesselName)

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__voyageNo'), VoyageNo)

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__cargoStatus'))

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/li_CargoStatus', [('cargoStatus') : cargoStatus]))

    CustomKeywords.'commonUtils.clearElementText'(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__m3'))

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__m3'), M3)

    CustomKeywords.'commonUtils.clearElementText'(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__netWeight (Clear)'))

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__netWeight (Clear)'), NetWeight)

    CustomKeywords.'commonUtils.clearElementText'(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__grossWeight'))

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__grossWeight'), GrossWeight)

    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/button_Confirm 1'), 
        0)

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/button_CONFIRM 2'))

    'The operation was successful.'
    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC075/p22_The operation was successful'), 'The operation was successful.')

    not_run: WebUI.back(FailureHandling.STOP_ON_FAILURE)

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/input_Search Shipping List'), bookingID)

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_CargoStatus', [('rowNum') : rowNum]), cargoStatus)

    CustomKeywords.'commonUtils.clearElementText'(findTestObject('Scenario 13/S13_TC062-Syazwan/input_Search Shipping List'))
}

