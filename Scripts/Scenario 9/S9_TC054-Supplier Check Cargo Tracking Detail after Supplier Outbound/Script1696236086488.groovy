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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_SUP], 
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

for (int row = 1; row <= 3; row++) {
	
	String bookingNo = findTestData('Data Files/Scenario 9/S9_TC054-Supplier Check Cargo Tracking Detail').getValue('BookingNo',
		row)

	String contnum2 = findTestData('Data Files/Scenario 9/S9_TC054-Supplier Check Cargo Tracking Detail').getValue('ContainerNo',
		row)

	String titlecontnum2 = ''

	if (contnum2 != '') {
		titlecontnum2 = (' ' + contnum2)
	} else {
		titlecontnum2 = contnum2
	}
	
	String trackType = findTestData('Data Files/Scenario 9/S9_TC054-Supplier Check Cargo Tracking Detail').getValue('TrackingType',
		row)

	String mainforpath2 = (('Booking Number: ' + bookingNo) + '    Container Number: ') + contnum2

	String titlebook2 = (('BOOKING NUMBER: ' + bookingNo) + '    CONTAINER NUMBER:') + titlecontnum2

	println('CURRENT MAIN: ' + mainforpath2)

	println('CURRENT TITLE: ' + titlebook2)

	WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC064,065/div_titleContainerNum v1_1', [('maintitle') : mainforpath2]),
		0)

	WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC064,065/div_titleContainerNum v1_1', [('maintitle') : mainforpath2]),
		titlebook2.toUpperCase())

	WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC064,065/div_trackingType v1_1', [('maintitle') : mainforpath2
				, ('tracktype') : trackType]), 0)

	if (trackType == 'REAL-TIME') {
		WebUI.click(findTestObject('Scenario 13/S13_TC064,065/div_trackingType v1_1', [('maintitle') : mainforpath2, ('tracktype') : trackType]))

		WebUI.click(findTestObject('Scenario 13/S13_TC064,065/li_tracktype (1)'))
	}
	
	CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC064,065/button_arrow_container more detail',
			[('maintitle') : mainforpath2]), 1)

	for (String milestonecol : milestone) {
		String sytle2 = findTestData('Data Files/Scenario 9/S9_TC054-Supplier Check Cargo Tracking Detail').getValue(milestonecol,
			row)

		WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC064,065/p_milestonelist v1_1', [('maintitle') : mainforpath2
					, ('milestonecol') : milestonecol]), 0)

		WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC064,065/p_milestonelist v1_1', [('maintitle') : mainforpath2
					, ('milestonecol') : milestonecol]), 'style', sytle2, 0)
	}
	
	CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC064,065/button_arrow_container more detail',
			[('maintitle') : mainforpath2]), 1)

}

WebUI.closeBrowser()

