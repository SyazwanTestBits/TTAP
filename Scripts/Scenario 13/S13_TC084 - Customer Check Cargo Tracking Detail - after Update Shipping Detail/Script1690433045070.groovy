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

//-----------------------------Initial------------------------------------------------
String buttonClickBookNo = 'Booking Number: ' + bookingNumber

//---------Start Testing--------------------------------
WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : password, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC064,065/button_Logistics'))

WebUI.click(findTestObject('Scenario 13/S13_TC064,065/li_Cargo Tracking Detail'))

WebUI.setText(findTestObject('Scenario 13/S13_TC064,065/input_Search booking no'), bookingNumber)

WebUI.click(findTestObject('Scenario 13/S13_TC064,065/button_Search booking no'))

WebUI.click(findTestObject('Scenario 13/S13_TC064,065/h6_Booking Number', [('buttonbookno') : buttonClickBookNo]))

WebUI.click(findTestObject('Scenario 13/S13_TC064,065/button_arrow first'))

int rownum = findTestData('Data Files/Scenario 13/S13_TC084n085_Forecast').getRowNumbers()

'For Forecast Container'
for (int r = 1; r <= rownum; r++) {
    String contnum = findTestData('Data Files/Scenario 13/S13_TC084n085_Forecast').getValue('ContainerNo', r)

    String PlanETADate = findTestData('Data Files/Scenario 13/S13_TC084n085_Forecast').getValue('plan ETA', r)

    newPlanETADate = CustomKeywords.'DateConversionLocal.convertLocalChineseIntoLocalEnglishWithOutput'(PlanETADate,
		'd MMM yyyy', 'MMM d, yyyy')

    String fullPlanETADate = 'Planned ETA @ Final Destination: ' + newPlanETADate

    String PredictETADate = findTestData('Data Files/Scenario 13/S13_TC084n085_Forecast').getValue('predict ETA', r)

	newPredictETADate = CustomKeywords.'DateConversionLocal.convertLocalChineseIntoLocalEnglishWithOutput'(PredictETADate,
		'd MMM yyyy', 'MMM d, yyyy')

    String fullPredictETADate = 'Predictive ETA @ Final Destination: ' + newPredictETADate

    String trackType = findTestData('Data Files/Scenario 13/S13_TC084n085_Forecast').getValue('TrackingType', r)

    String titlebook = (('BOOKING NUMBER: ' + bookingNumber) + '    CONTAINER NUMBER: ') + contnum

    String mainforpath = (('Booking Number: ' + bookingNumber) + '    Container Number: ') + contnum

    WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC064,065/div_titleContainerNum v1_1', [('maintitle') : mainforpath]), 
        0)

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC064,065/div_titleContainerNum v1_1', [('maintitle') : mainforpath]), 
        titlebook)

    'NEED TO CHECK AFTER TC063'
    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC064,065/div_Planned ETA date', [('maintitle') : mainforpath
                , ('planEtaDate') : fullPlanETADate]), fullPlanETADate)

    'NEED TO CHECK AFTER TC063'
    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC064,065/div_Predictive ETA date', [('maintitle') : mainforpath
                , ('predictEtaDate') : fullPredictETADate]), fullPredictETADate)

    WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC064,065/div_trackingType v1_1', [('maintitle') : mainforpath
                , ('tracktype') : trackType]), 0)

    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC064,065/button_arrow_container more detail', 
            [('maintitle') : mainforpath]), 1)

    for (String milestoneforce : milestoneREALTIME) {
        String sytle = findTestData('Data Files/Scenario 13/S13_TC084n085_Forecast').getValue(milestoneforce, r)

        WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC064,065/p_milestonelist v1_1', [('maintitle') : mainforpath
                    , ('milestonecol') : milestoneforce]), 0)

        WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC064,065/p_milestonelist v1_1', [('maintitle') : mainforpath
                    , ('milestonecol') : milestoneforce]), 'style', sytle, 0)
    }
}

int numrowForecastManual = findTestData('Data Files/Scenario 13/S13_TC084n085_Forecast-Manual').getRowNumbers()

'For Forecast: MANUAL'
for (int row11 = 1; row11 <= numrowForecastManual; row11++) {
    String contnum11 = findTestData('Data Files/Scenario 13/S13_TC084n085_Forecast-Manual').getValue('ContainerNo', row11)

    String titlecontnum11 = ''

    if (contnum11 != '') {
        titlecontnum11 = (' ' + contnum11)
    } else {
        titlecontnum11 = contnum11
    }
    
    String trackType11 = findTestData('Data Files/Scenario 13/S13_TC084n085_Forecast-Manual').getValue('TrackingType', row11)

    String mainforpath11 = (('Booking Number: ' + bookingNumber) + '    Container Number: ') + contnum11

    String titlebook11 = (('BOOKING NUMBER: ' + bookingNumber) + '    CONTAINER NUMBER:') + titlecontnum11

    WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC064,065/div_titleContainerNum v1_1', [('maintitle') : mainforpath11]), 
        0)

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC064,065/div_titleContainerNum v1_1', [('maintitle') : mainforpath11]), 
        titlebook11)

    WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC064,065/div_trackingType v1_1', [('maintitle') : mainforpath11
                , ('tracktype') : trackType11]), 0)

    if (trackType11 == 'REAL-TIME') {
        WebUI.click(findTestObject('Scenario 13/S13_TC064,065/div_trackingType v1_1', [('maintitle') : mainforpath11, ('tracktype') : trackType11]))

        WebUI.click(findTestObject('Scenario 13/S13_TC064,065/li_tracktype (1)'))
    }
    
    for (String milestoneforce11 : milestoneREALTIME) {
        String sytle11 = findTestData('Data Files/Scenario 13/S13_TC084n085_Forecast-Manual').getValue(milestoneforce11, 
            row11)

        WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC064,065/p_milestonelist v1_1', [('maintitle') : mainforpath11
                    , ('milestonecol') : milestoneforce11]), 0)

        WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC064,065/p_milestonelist v1_1', [('maintitle') : mainforpath11
                    , ('milestonecol') : milestoneforce11]), 'style', sytle11, 0)
    }
    
    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC064,065/button_arrow_container more detail', 
            [('maintitle') : mainforpath11]), 1)
}

int numrowNonForecast = findTestData('Data Files/Scenario 13/S13_TC084n085_NonForecast').getRowNumbers()

for (int row2 = 1; row2 <= numrowNonForecast; row2++) {
    String contnum2 = findTestData('Data Files/Scenario 13/S13_TC076n077_NonForecast').getValue('ContainerNo', row2)

    String titlecontnum2 = ''

    if (contnum2 != '') {
        titlecontnum2 = (' ' + contnum2)
    } else {
        titlecontnum2 = contnum2
    }
    
    String trackType = findTestData('Data Files/Scenario 13/S13_TC084n085_NonForecast').getValue('TrackingType', row2)

    String mainforpath2 = (('Booking Number: ' + bookingNumber) + '    Container Number: ') + contnum2

    String titlebook2 = (('BOOKING NUMBER: ' + bookingNumber) + '    CONTAINER NUMBER:') + titlecontnum2

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
        String sytle2 = findTestData('Data Files/Scenario 13/S13_TC084n085_NonForecast').getValue(milestonecol, row2)

        WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC064,065/p_milestonelist v1_1', [('maintitle') : mainforpath2
                    , ('milestonecol') : milestonecol]), 0)

        WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC064,065/p_milestonelist v1_1', [('maintitle') : mainforpath2
                    , ('milestonecol') : milestonecol]), 'style', sytle2, 0)
    }
    
    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC064,065/button_arrow_container more detail', 
            [('maintitle') : mainforpath2]), 1)
}

WebUI.closeBrowser()

