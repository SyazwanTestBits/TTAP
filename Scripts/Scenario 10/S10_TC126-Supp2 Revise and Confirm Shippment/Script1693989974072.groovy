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
        , ('password') : GlobalVariable.PNA_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_SUPPLIER2], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/button_Logistics'))

WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/li_Shipping Detail List'))

not_run: WebUI.click(findTestObject('Page_ShippingDetailList/dt_header_ETA'))

not_run: WebUI.click(findTestObject('Page_ShippingDetailList/dt_header_ETA'))

not_run: WebUI.click(findTestObject('Page_ShippingDetailList/dt_header_ETA_desc'))

for (int rowNum = 1; rowNum <= noRows; rowNum++) {
    String OutbondNo = testData.getValue('OutbondNo', rowNum)

    String bookingID = testData.getValue('BookingId', rowNum)

    String containerNo = testData.getValue('ContainerNo', rowNum)

    String search = (((OutbondNo + ' ') + bookingID) + ' ') + containerNo

    String cargoStatus = testData.getValue('CargoStatus', rowNum)

    String containerTrackJourney = testData.getValue('ContainerTrackJourney', rowNum)

    ATD = CustomKeywords.'util.changeFormatString.changeDateFormat2'(testData.getValue('ATD', rowNum))

    (dayATD, monthATD, yearATD) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt'(ATD)

    String BLNo = testData.getValue('BLNo', rowNum)

    BLDate = CustomKeywords.'util.changeFormatString.changeDateFormat2'(testData.getValue('BLDate', rowNum))

    (dayBLDate, monthBLDate, yearBLDate) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt'(BLDate)

    String VesselName = testData.getValue('VesselName', rowNum)

    String VoyageNo = testData.getValue('VoyageNo', rowNum)

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/input_Search Shipping List'), search)

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_BookingNo', [('rowNum') : 1]), bookingID)

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_ContainerNo', [('rowNum') : 1]), containerNo)

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_CargoStatus', [('rowNum') : 1]), '')

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_Container Journey', [('rowNum') : 1]), 
        'NO')

    if (containerTrackJourney == 'YES') {
        WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/button_Dt_Container Journey Tracking Detail', [('rowNum') : 1]))

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC075/p22_The operation was successful'), 'The operation was successful.')

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_Container Journey', [('rowNum') : 1]), 
            'YES')
    }
    
    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/button_Dt_edit shipping', [('rowNum') : 1]))

    WebUI.waitForElementVisible(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/h3_Edit Shipping Detail'), 0)

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__atd date'))

    CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Calendar_Object/div_calendar header'), findTestObject(
            'Calendar_Object/button next calendar'), findTestObject('Calendar_Object/button previous calendar'), dayATD, 
        monthATD, yearATD)

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__BL_No'), BLNo)

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__BL_Date'))

    CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Calendar_Object/div_calendar header'), findTestObject(
            'Calendar_Object/button next calendar'), findTestObject('Calendar_Object/button previous calendar'), dayBLDate, 
        monthBLDate, yearBLDate)

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__vesselName'), VesselName)

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__voyageNo'), VoyageNo)

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/input__cargoStatus'))

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/li_CargoStatus', [('cargoStatus') : cargoStatus]))

    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/button_Confirm 1'), 
        0)

    WebUI.click(findTestObject('Scenario 13/S13_TC062-Syazwan/0_edit page/button_CONFIRM 2'))

    'The operation was successful.'
    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC075/p22_The operation was successful'), 'The operation was successful.')

    WebUI.back(FailureHandling.STOP_ON_FAILURE)

    WebUI.setText(findTestObject('Scenario 13/S13_TC062-Syazwan/input_Search Shipping List'), search)

    not_run: WebUI.click(findTestObject('Page_ShippingDetailList/dt_header_ETA'))

    not_run: WebUI.click(findTestObject('Page_ShippingDetailList/dt_header_ETA'))

    not_run: WebUI.click(findTestObject('Page_ShippingDetailList/dt_header_ETA_desc'))

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC062-Syazwan/div_Dt_CargoStatus', [('rowNum') : 1]), cargoStatus)

    CustomKeywords.'util.clearTextJS.clearElementText'(findTestObject('Scenario 13/S13_TC062-Syazwan/input_Search Shipping List'))
}

WebUI.closeBrowser()

