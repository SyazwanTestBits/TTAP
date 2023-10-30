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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : password, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Shipping Detail List'))

WebUI.waitForElementPresent(findTestObject('Page_ShippingDetailList/h3_Shipping Detail List'), 0)

for (def rowNum = 1; rowNum <= testData.getRowNumbers(); rowNum++) {
    WebUI.setText(findTestObject('Page_ShippingDetailList/input_ShippingDetailList_Search'), bookingNo)

    def containerNo = testData.getValue('ContainerNo', rowNum)

    def ATD = testData.getValue('ATD', rowNum)

    def ETA = testData.getValue('ETA', rowNum)

    def BLNo = testData.getValue('BLNo', rowNum)

    def BLDate = testData.getValue('BLDate', rowNum)

    def VesselName = testData.getValue('VesselName', rowNum)

    def VoyageNo = testData.getValue('VoyageNo', rowNum)

    def M3 = testData.getValue('M3', rowNum)

    def NetWeight = testData.getValue('NetWeight', rowNum)

    def GrossWeight = testData.getValue('GrossWeight', rowNum)

    def containerTrackJourney = testData.getValue('ContainerTrackJourney', rowNum)

    WebUI.verifyElementText(findTestObject('Page_ShippingDetailList/div_Dt_BookingNo', [('rowNum') : rowNum]), bookingNo)

    KeywordUtil.logInfo("Row: $rowNum - Booking No. is $bookingNo")

    WebUI.verifyElementText(findTestObject('Page_ShippingDetailList/div_Dt_ContainerNo'), containerNo)

    KeywordUtil.logInfo("Row: $rowNum - Container No. is $containerNo")

    WebUI.verifyElementText(findTestObject('Page_ShippingDetailList/div_Dt_ATD', [('rowNum') : rowNum]), ATD)

    KeywordUtil.logInfo("Row: $rowNum - ATD is $ATD")

    WebUI.verifyElementText(findTestObject('Page_ShippingDetailList/div_Dt_ETD', [('rowNum') : rowNum]), ETA)

    KeywordUtil.logInfo("Row: $rowNum - ETD. is $ETA")

    WebUI.verifyElementText(findTestObject('Page_ShippingDetailList/div_Dt_ContainerTrack', [('rowNum') : rowNum]), containerTrackJourney)

    KeywordUtil.logInfo("Row: $rowNum - Container Track Journey Detail. is $containerTrackJourney")

    WebUI.click(findTestObject('Page_ShippingDetailList/button_Dt_Row_View', [('rowNum') : rowNum]))

    WebUI.waitForElementPresent(findTestObject('Page_ShippingDetailList/Page_ViewShippingDetail/h3_View Shipping Detail'), 
        0)

    parseATD = CustomKeywords.'commonUtils.parseDateInfoDesiredDateFormat'(ATD)

    WebUI.verifyElementAttributeValue(findTestObject('Page_ShippingDetailList/Page_ViewShippingDetail/input__atd'), 'value', 
        parseATD, 0)

    parseETA = CustomKeywords.'commonUtils.parseDateInfoDesiredDateFormat'(ETA)

    WebUI.verifyElementAttributeValue(findTestObject('Page_ShippingDetailList/Page_ViewShippingDetail/input__eta'), 'value', 
        parseETA, 0)

    WebUI.verifyElementAttributeValue(findTestObject('Page_ShippingDetailList/Page_ViewShippingDetail/input__blNo'), 'value', 
        BLNo, 0)

    parseBLDate = CustomKeywords.'commonUtils.parseDateInfoDesiredDateFormat'(BLDate)

    WebUI.verifyElementAttributeValue(findTestObject('Page_ShippingDetailList/Page_ViewShippingDetail/input__blDate'), 'value', 
        parseBLDate, 0)

    WebUI.verifyElementAttributeValue(findTestObject('Page_ShippingDetailList/Page_ViewShippingDetail/input__vesselName'), 
        'value', VesselName, 0)

    WebUI.verifyElementAttributeValue(findTestObject('Page_ShippingDetailList/Page_ViewShippingDetail/input__voyageNo'), 
        'value', VoyageNo, 0)

    WebUI.verifyElementAttributeValue(findTestObject('Page_ShippingDetailList/Page_ViewShippingDetail/input__m3'), 'value', 
        M3, 0)

    WebUI.verifyElementAttributeValue(findTestObject('Page_ShippingDetailList/Page_ViewShippingDetail/input__netWeight'), 
        'value', NetWeight, 0)

    WebUI.verifyElementAttributeValue(findTestObject('Page_ShippingDetailList/Page_ViewShippingDetail/input__grossWeight'), 
        'value', GrossWeight, 0)

    WebUI.verifyElementAttributeValue(findTestObject('Page_ShippingDetailList/Page_ViewShippingDetail/input__viaPortcastFlag'), 
        'value', containerTrackJourney, 0)

    WebUI.back()
}

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

