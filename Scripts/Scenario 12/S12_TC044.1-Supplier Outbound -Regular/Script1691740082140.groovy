import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testdata.TestData as TestData
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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.click(findTestObject('Page_OutboundMonitoringList/button_CreateOutboundResults'))

WebUI.waitForElementPresent(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/h3_Create Outbound'), 0)

'check this button'
WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/button_Download Outbound Form'))

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/li_Select Parts  Download Outbound Form'))

WebUI.waitForElementPresent(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/h5_Download Outbound Form'), 
    0)

WebUI.setText(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/input_Buyer'), buyer)

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/li_BuyerOption', [
            ('buyer') : buyer]))

WebUI.setText(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/input_Receiver'), 
    receiver)

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/li_ReceiverOption', 
        [('receiver') : receiver]))

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/input_outboundType'), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/li_outboundTypeOption', 
        [('outboundType') : outboundType]))

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/input_outboundDate'))

(day, month, year) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt'(outboundDate)

CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Scenario 13/S13_TC033_Shipping_Route/div_calendar header'), 
    findTestObject('Scenario 13/S13_TC033_Shipping_Route/button next calendar'), findTestObject('Scenario 13/S13_TC033_Shipping_Route/button previous calendar'), 
    day, month, year)

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/input_shippingMode'), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/li_shippingModeOption', 
        [('shippingMode') : shippingMode]))

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/button_Step 2. Select Parts'))

WebUI.setText(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/input_DwnloadOutboundForm_SearchOutboundList'), 
    salesOrderNo)

for (def rowNum = 1; rowNum <= testdata_contractpartsinfo.getRowNumbers(); rowNum++) {
    partsNo = testdata_contractpartsinfo.getValue('PartsNo', rowNum)

    WebUI.verifyElementPresent(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/div_Dt_DwnloadOutboundForm_SalesOrderNo', 
            [('salesOrderNo') : salesOrderNo, ('partsNo') : partsNo]), 0, FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo("Sales Order: ' $salesOrderNo ' and Parts No: ' $partsNo ' are present on the Download Outbound form list")
}

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/input_Dt_checkbox_CheckAllOutboundList'))

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/button_Download'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadOutboundFile_Success'), 0)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'ExcelActions.writeOutboundDataIntoDownloadOutboundFormSC12'(testdata_upload_outbounddata, latestFilePath)

WebUI.uploadFile(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/input_UploadFile_OutboundForm'), latestFilePath)

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC044/div_Upload Outbound By CreateThe operation was successful'), 
    0)

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/button_Submit'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSure_ToDoSubmit'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_CreateOutbound_Success'), 0)

