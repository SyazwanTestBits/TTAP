import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_SUP2], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Scenario 13/S13_TC064,065/li_Cargo Tracking Detail'))

WebUI.waitForElementPresent(findTestObject('Page_Cargo Tracking Detail - Brivge/h3_Cargo Tracking Detail'), 0)

WebUI.setText(findTestObject('Page_Cargo Tracking Detail - Brivge/input_Search For Cargo'), orderNo)

WebUI.click(findTestObject('Page_Cargo Tracking Detail - Brivge/button_Search'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Page_Cargo Tracking Detail - Brivge/h6_Order Number No', [('orderNo') : orderNo]), 
    0)

WebUI.click(findTestObject('Page_Cargo Tracking Detail - Brivge/h6_Order Number No', [('orderNo') : orderNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_Cargo Tracking Detail - Brivge/button_BookingNumber_First'))

WebUI.takeFullPageScreenshot()

for (int row = 1; row <= 4; row++) {
    String bookingNo = findTestData('Data Files/Scenario 1/S1_TC198-Customer Cargo Tracking Details').getValue('BookingNo', 
        row)

    String contnum2 = findTestData('Data Files/Scenario 1/S1_TC198-Customer Cargo Tracking Details').getValue('ContainerNo', 
        row)

    String titlecontnum2 = ''

    if (contnum2 != '') {
        titlecontnum2 = (' ' + contnum2)
    } else {
        titlecontnum2 = contnum2
    }
    
    String trackType = findTestData('Data Files/Scenario 1/S1_TC198-Customer Cargo Tracking Details').getValue('TrackingType', 
        row)

    String mainforpath2 = (('Booking Number: ' + bookingNo) + '    Container Number: ') + contnum2

    String titlebook2 = (('BOOKING NUMBER: ' + bookingNo) + '    CONTAINER NUMBER:') + titlecontnum2

    KeywordUtil.logInfo("CURRENT MAIN: $mainforpath2")

    KeywordUtil.logInfo("CURRENT TITLE: $titlebook2")

    WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC064,065/div_titleContainerNum v1_1', [('maintitle') : mainforpath2]), 
        0)

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC064,065/div_titleContainerNum v1_1', [('maintitle') : mainforpath2]), 
        titlebook2.toUpperCase())

    KeywordUtil.logInfo("VERIFIED MAIN TITLE: $mainforpath2")

    WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC064,065/div_trackingType v1_1', [('maintitle') : mainforpath2
                , ('tracktype') : trackType]), 0)

    KeywordUtil.logInfo("VERIFIED TRACKING TYPE: $trackType")

    if (trackType == 'REAL-TIME') {
        WebUI.click(findTestObject('Scenario 13/S13_TC064,065/div_trackingType v1_1', [('maintitle') : mainforpath2, ('tracktype') : trackType]))

        WebUI.click(findTestObject('Scenario 13/S13_TC064,065/li_tracktype (1)'))
    }
    
    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC064,065/button_arrow_container more detail', 
            [('maintitle') : mainforpath2]), 1)

    for (String milestonecol : milestone) {
        String sytle2 = findTestData('Data Files/Scenario 1/S1_TC198-Customer Cargo Tracking Details').getValue(milestonecol, 
            row)

        WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC064,065/p_milestonelist v1_1', [('maintitle') : mainforpath2
                    , ('milestonecol') : milestonecol]), 0)

        WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC064,065/p_milestonelist v1_1', [('maintitle') : mainforpath2
                    , ('milestonecol') : milestonecol]), 'style', sytle2, 0)

        KeywordUtil.logInfo("VERIFIED MILESTONE: $milestonecol")
    }
    
    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC064,065/button_arrow_container more detail', 
            [('maintitle') : mainforpath2]), 1)
}

WebUI.closeBrowser()

