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
import java.text.ParseException as ParseException
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

//---------Start Testing--------------------------------
WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_SYAZWAN
        , ('password') : GlobalVariable.PNA_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC064,065/button_Logistics'))

WebUI.click(findTestObject('Scenario 13/S13_TC064,065/li_Cargo Tracking Detail'))

for (int row2 = 1; row2 <= testData.getRowNumbers(); row2++) {
    String contnum = testData.getValue('ContainerNo', row2)

    String bookingNumber = testData.getValue('BookingId', row2)

    if (bookingNumber != '') {
        String buttonClickBookNo = 'Booking Number: ' + bookingNumber

        WebUI.setText(findTestObject('Scenario 13/S13_TC064,065/input_Search booking no'), bookingNumber)

        WebUI.click(findTestObject('Scenario 13/S13_TC064,065/button_Search booking no'))

        WebUI.click(findTestObject('Scenario 13/S13_TC064,065/h6_Booking Number', [('buttonbookno') : buttonClickBookNo]))
    } else {
        String buttonClickConNo = 'Container Number: ' + contnum

        WebUI.setText(findTestObject('Scenario 13/S13_TC064,065/input_Search booking no'), contnum)

        WebUI.click(findTestObject('Scenario 13/S13_TC064,065/button_Search booking no'))

        WebUI.click(findTestObject('Scenario 13/S13_TC064,065/h6_Booking Number', [('buttonbookno') : buttonClickConNo]))
    }
    
    String titlecontnum = ''

    String titlebooknum = ''

    String upperbooknum = ''

    if (bookingNumber != '') {
        upperbooknum = bookingNumber.toUpperCase()

        titlebooknum = (' ' + upperbooknum)
    } else {
        titlebooknum = (' ' + bookingNumber)
    }
    
    if (contnum != '') {
        titlecontnum = (' ' + contnum)
    } else {
        titlecontnum = contnum
    }
    
    String trackType = testData.getValue('TrackingType', row2)

    String mainforpath2 = (('Booking Number: ' + bookingNumber) + '    Container Number: ') + contnum

    String titlebook2 = (('BOOKING NUMBER:' + titlebooknum) + '    CONTAINER NUMBER:') + titlecontnum
	
	titlebook2=titlebook2.toUpperCase()
	

    WebUI.click(findTestObject('Scenario 13/S13_TC064,065/button_arrow first'))

    WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC064,065/div_titleContainerNum v1_1', [('maintitle') : mainforpath2]), 
        0)

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC064,065/div_titleContainerNum v1_1', [('maintitle') : mainforpath2]), 
        titlebook2)

    WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC064,065/div_trackingType v1_1', [('maintitle') : mainforpath2
                , ('tracktype') : trackType]), 0)

    if (trackType == 'REAL-TIME') {
        WebUI.click(findTestObject('Scenario 13/S13_TC064,065/div_trackingType v1_1', [('maintitle') : mainforpath2, ('tracktype') : trackType]))

        WebUI.click(findTestObject('Scenario 13/S13_TC064,065/li_tracktype (1)'))
    }
    
    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC064,065/button_arrow_container more detail', 
            [('maintitle') : mainforpath2]), 1)

    for (String milestonecol : milestone) {
        String sytle2 = testData.getValue(milestonecol, row2)

        WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC064,065/p_milestonelist v1_1', [('maintitle') : mainforpath2
                    , ('milestonecol') : milestonecol]), 0)

        actualColour = WebUI.getAttribute(findTestObject('Scenario 13/S13_TC064,065/p_milestonelist v1_1', [('maintitle') : mainforpath2
                    , ('milestonecol') : milestonecol]), 'style')

        KeywordUtil.logInfo((((((('Path: ' + mainforpath2) + ' Milestone: ') + milestonecol) + ' Actual colour: ') + actualColour) + 
            ' expectation colour: ') + sytle2)

        WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC064,065/p_milestonelist v1_1', [('maintitle') : mainforpath2
                    , ('milestonecol') : milestonecol]), 'style', sytle2, 0)
    }
    
    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC064,065/button_arrow_container more detail', 
            [('maintitle') : mainforpath2]), 1)

    CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Scenario 13/S13_TC064,065/input_Search booking no'), 
        0)

    CustomKeywords.'util.clearTextJS.clearElementText'(findTestObject('Scenario 13/S13_TC064,065/input_Search booking no'))
}

WebUI.callTestCase(findTestCase('Scenario 10/S10_TC105-BU Check Cargo Tracking Detail/S10_TC105.1 - BU Check Cargo Tracking Detail PO'), 
    [('containerNumber') : '', ('milestone') : ['Booking Submitted', 'Booking Confirmed', 'Empty Container Release to Shipper'
            , 'Cargo Outbound', 'Gate in (arrival at first POL)', 'Container loaded at first POL', 'Vessel departure from first POL'
            , 'Vessel arrival at T/S port', 'Container discharge at T/S port', 'Container loaded at T/S port', 'Vessel departure from T/S'
            , 'Vessel arrived at final POD (ATA)', 'Container unload at final POD', 'Container Gate out from final POD', 'Vessel arrived at final POD (ATA)'
            , 'Container unload at final POD', 'Container Gate out from final POD'], ('milestoneREALTIME') : ['Empty container pick-up (Empty Container Release to Shipper)'
            , 'Gate in (Gate In to Outbound Terminal)', 'Loaded (Loaded on vessel at Port of Loading)', 'Departure (vessel Departure from Port of Loading)'
            , 'Arrival (vessel Arrival at Transhipment Port)', 'Unload (Unloaded from vessel at Transhipment Port)', 'Loaded (Loaded on vessel at Transhipment Port)'
            , 'Departure (Departure from Transhipment Port)', 'Arrival (vessel Arrival at Port of Discharging)', 'Unload (Unloaded from vessel at Port of Discharging)'
            , 'Gate out (Gate Out from Inbound Terminal for Delivery to Consignee (or Port Shuttle))', 'Empty container returned (Empty Container Returned from Customer)']
        , ('testDataForecast') : '', ('testData') : findTestData('Scenario 10/S10_TC105_PO')], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

