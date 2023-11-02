import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Shipping Detail List'))

WebUI.waitForElementPresent(findTestObject('Page_ShippingDetailList/h3_Shipping Detail List'), 0)


int row1 = 0

int row2 = 0

int row3 = 0

int rowDT = 0

for (def rowNum = 1; rowNum <= testData.getRowNumbers(); rowNum++) {
	WebUI.setText(findTestObject('Scenario 13/S13_TC086/input_Shipping Detail List_Search'), testData.getValue(
			'OutbondNo', rowNum))

	noDT = testData.getValue('No', rowNum)

	switch (noDT) {
		case '1':
			row1++

			rowDT = row1

			break
		case '2':
			row2++

			rowDT = row2

			break
		case '3':
			row3++

			rowDT = row3

			break
	}
	
	def bookingNo = testData.getValue('BookingId', rowNum)

	def containerNo = testData.getValue('ContainerNo', rowNum)

	def cargoStatus = ''

	println(rowDT)

	WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC086/div_Booking No', [('rowNum') : rowDT]), bookingNo)

	KeywordUtil.logInfo("Row: $rowDT - Booking No. is $bookingNo")

	WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC086/div_Container No', [('rowNum') : rowDT]), containerNo)

	KeywordUtil.logInfo("Row: $rowDT - Container No. is $containerNo")

	WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC086/div_Cargo Status', [('rowNum') : rowDT]), cargoStatus)

	KeywordUtil.logInfo("Row: $rowDT - Container No. is $cargoStatus")

	WebUI.click(findTestObject('Page_ShippingDetailList/button_Dt_Row_View', [('rowNum') : rowDT]))

	WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC086/input__cargoStatus'), 'value', cargoStatus,
		0)

	WebUI.back()
}

WebUI.closeBrowser()