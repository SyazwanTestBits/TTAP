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

for (int row2 = 1; row2 <= testData.getRowNumbers(); row2++) {
    String contnum = testData.getValue('ContainerNo', row2)

    String bookingNumber = testData.getValue('BookingId', row2)

    String SOid = testData.getValue('SOid', row2)

    String buttonClickPONo = 'Order Number: ' + SOid

    WebUI.setText(findTestObject('Scenario 13/S13_TC064,065/input_Search booking no'), SOid)

    WebUI.click(findTestObject('Scenario 13/S13_TC064,065/button_Search booking no'))

    WebUI.click(findTestObject('Scenario 13/S13_TC064,065/h6_Booking Number', [('buttonbookno') : buttonClickPONo]))

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

        WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC064,065/p_milestonelist v1_1', [('maintitle') : mainforpath2
                    , ('milestonecol') : milestonecol]), 'style', sytle2, 0)
    }
    
    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC064,065/button_arrow_container more detail', 
            [('maintitle') : mainforpath2]), 1)

    CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Scenario 13/S13_TC064,065/input_Search booking no'), 
        0)

    CustomKeywords.'util.clearTextJS.clearElementText'(findTestObject('Scenario 13/S13_TC064,065/input_Search booking no'))
}

