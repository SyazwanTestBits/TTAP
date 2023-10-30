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
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Outbound To WIP'))

WebUI.waitForElementPresent(findTestObject('Page_OutboundToWIP/h3_Outbound To WIP'), 0)

WebUI.click(findTestObject('Page_OutboundToWIP/button_Download_OutboundToWIP'))

WebUI.click(findTestObject('Page_OutboundToWIP/li_Download Outbound History'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DownloadOutboundToWIPListByFilters_Success'), 
    0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

outboundHistForm = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

int lastrow = CustomKeywords.'util.ExcelUtils.getLastRowNumber'(outboundHistForm)

for (int index = 1; index <= testData.getRowNumbers(); index++) {
    for (def pair : columnname) {
        def columnName = pair.key

        def columnIndex = pair.value

        def dataValue = testData.getValue(columnName, index)

        int fileRowIndex = index + lastrow

        CustomKeywords.'copyToExcel.exel3'(dataValue, fileRowIndex, columnIndex, outboundHistForm, 'Outbound')
    }
}

WebUI.click(findTestObject('Page_OutboundToWIP/button_upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Page_OutboundToWIP/li_Upload Outbound'), outboundHistForm)

if (WebUI.verifyElementNotPresent(findTestObject('Object Repository/Page_OutboundToWIP/h6_Confirm notification'),
	1, FailureHandling.OPTIONAL) == true) {
	WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)
} else {
	WebUI.click(findTestObject('Object Repository/Page_OutboundToWIP/button_CONFIRM'))

	WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)
}


WebUI.closeBrowser()

