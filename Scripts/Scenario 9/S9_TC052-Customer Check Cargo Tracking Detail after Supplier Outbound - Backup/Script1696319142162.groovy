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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_CUS], 
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

for (def index : (1..datafile_outbound.getRowNumbers())) {
    def bookingNo = datafile_outbound.getValue('BookingNo', index)

    def containerNo = datafile_outbound.getValue('ContainerNo', index)

    s9_excel = CustomKeywords.'ManageFiles.getFileAbsolutePath'('Excel Files/Scenario 9/S9_TestCases_Data.xlsx')

    eta = CustomKeywords.'readFromExcel.getCellValue'(s9_excel, 'TC47-SUP Upload Outbound Result', index, 25)

    WebUI.scrollToElement(findTestObject('Page_Cargo Tracking Detail - Brivge/h6_Booking Number No_Container Number No', 
            [('bookingNo') : bookingNo, ('containerNo') : containerNo]), 0)

    WebUI.verifyElementPresent(findTestObject('Page_Cargo Tracking Detail - Brivge/h6_Booking Number No_Container Number No', 
            [('bookingNo') : bookingNo, ('containerNo') : containerNo]), 0)

    not_run: WebUI.verifyElementPresent(findTestObject('Page_Cargo Tracking Detail - Brivge/p_PlannedETA_FinalDestination', 
            [('bookingNo') : bookingNo, ('containerNo') : containerNo, ('eta') : eta]), 0)

    not_run: WebUI.verifyElementPresent(findTestObject('Page_Cargo Tracking Detail - Brivge/p_PredictiveETA_FinalDestination', 
            [('bookingNo') : bookingNo, ('containerNo') : containerNo, ('eta') : eta]), 0)

    WebUI.verifyElementText(findTestObject('Page_Cargo Tracking Detail - Brivge/div_Status ONTIME', [('bookingNo') : bookingNo
                , ('containerNo') : containerNo]), 'Status: ONTIME')

    WebUI.verifyElementText(findTestObject('Page_Cargo Tracking Detail - Brivge/div_MANUAL', [('bookingNo') : bookingNo, ('containerNo') : containerNo]), 
        'MANUAL')

    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Page_Cargo Tracking Detail - Brivge/button_BookingNumber_ExpandDetail', 
            [('bookingNo') : bookingNo, ('containerNo') : containerNo]), 0)

    WebUI.verifyElementText(findTestObject('Page_Cargo Tracking Detail - Brivge/div_Booking Submitted', [('bookingNo') : bookingNo
                , ('containerNo') : containerNo]), 'Booking Submitted')

    CustomKeywords.'CargoTrackingVerifications.verifyCargoTrackingMilestonesColor'(bookingNo, containerNo)
}

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

