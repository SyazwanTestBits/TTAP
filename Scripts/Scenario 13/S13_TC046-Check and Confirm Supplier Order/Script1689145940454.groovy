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

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_SO Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringList/h3_SO Monitoring List'), 0)

WebUI.verifyElementText(findTestObject('Page_SO_MonitoringList/div_Dt_SalesOrderNo', [('contractNo') : contractNo]), salesOrderNo)

WebUI.verifyElementText(findTestObject('Page_SO_MonitoringList/div_Dt_ContractNo', [('contractNo') : contractNo]), contractNo)

WebUI.click(findTestObject('Page_SO_MonitoringList/button_Dt_Detail', [('contractNo') : contractNo]))

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringDetail/h3_SO Monitoring Detail'), 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_MonitoringDetail/input_SalesOrderNo'), 'value', salesOrderNo, 
    0)

WebUI.click(findTestObject('Page_SO_MonitoringDetail/button_Parts Monitoring detail_step_2'))

WebUI.scrollToElement(findTestObject('Page_SO_MonitoringDetail/div_PartsMonitorDetail_PartsNo - Copy'), 0)

//--------------------------------------------------Edit DR----------------------------------------------------------------------------
WebUI.click(findTestObject('Page_SO_MonitoringDetail/button_DownloadOptions'))

WebUI.click(findTestObject('Page_SO_MonitoringDetail/li_Download DR'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

drPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

drIndex = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(drPath, 2, 10)

drLastColumn = CustomKeywords.'util.ExcelUtils.getLastColumnNumber'(drPath, 0)

println(drLastColumn)

partNo1 = partTestData.getValue('PartsNo', 1)

excelPart1Row = (drIndex[partNo1])

CustomKeywords.'copyToExcel.exel2'('', excelPart1Row, drLastColumn, drPath, 'Delivery Plan')

CustomKeywords.'copyToExcel.exel2'('100', excelPart1Row, drLastColumn - 7, drPath, 'Delivery Plan')

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/button_Upload BU SO Detail'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC051/li_Upload DR'), drPath)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

//--------------------------------------------------Edit DR----------------------------------------------------------------------------
//--------------------------------------------------Edit Price--------------------------------------------------------------------------
WebUI.click(findTestObject('Page_SO_MonitoringDetail/button_DownloadOptions'))

WebUI.click(findTestObject('Page_SO_MonitoringDetail/li_Download Price'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

pricePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

int rowstart = 9

for (int row = 1; row <= testDataPrice.getRowNumbers(); row++) {
    def value = testDataPrice.getValue('Unit Price', row)

    int rowExcel = rowstart + row

    CustomKeywords.'copyToExcel.exel3'(value, rowExcel, 11, pricePath, 'base')
}

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/button_Upload BU SO Detail'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC051/li_Upload Price'), pricePath)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

//---------------------------------------------------------------------------------------------------------------------------------------
CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Page_SO_MonitoringDetail/h3_SO Monitoring Detail'), 
    0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Page_SO_MonitoringDetail/button_Confirm'), 0)

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSureToDo_Confirm'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_ConfirmSupplierSO_Success'), 0)

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringDetail/h3_SO Monitoring Detail'), 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_MonitoringDetail/input_Status'), 'value', status, 0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Page_SO_MonitoringDetail/button_Parts Monitoring detail_step_2'), 
    0)

not_run: CustomKeywords.'Verification.verifyPartsMonitoringDetailStatus'(testdata, status)

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

