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

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Shipping Detail List'))

WebUI.waitForElementPresent(findTestObject('Page_ShippingDetailList/h3_Shipping Detail List'), 0)

WebUI.setText(findTestObject('Page_ShippingDetailList/input_ShippingDetailList_Search'), bookingNo)

for (def rowNum = 1; rowNum <= testData_UploadOutboundForm.getRowNumbers(); rowNum++) {
def containerNo = testData_UploadOutboundForm.getValue('ContainerNo', rowNum)

WebUI.verifyElementText(findTestObject('Page_ShippingDetailList/div_Dt_BookingNo', [('rowNum') : rowNum]), bookingNo)

KeywordUtil.logInfo("Row: $rowNum - Booking No. is $bookingNo")

WebUI.verifyElementText(findTestObject('Page_ShippingDetailList/div_Dt_ContainerNo', [('rowNum') : rowNum]), containerNo)

KeywordUtil.logInfo("Row: $rowNum - Container No. is $containerNo")

WebUI.verifyElementText(findTestObject('Page_ShippingDetailList/div_Dt_CargoStatus', [('rowNum') : rowNum]), cargoStatus)

KeywordUtil.logInfo("Row: $rowNum - Cargo Status is EMPTY")
}

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()