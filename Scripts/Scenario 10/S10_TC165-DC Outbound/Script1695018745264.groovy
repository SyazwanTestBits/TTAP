import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as TestObject
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
import internal.GlobalVariable as GlobalVariable

import java.text.SimpleDateFormat
import java.util.Date

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_FATIN
        , ('password') : GlobalVariable.PNA_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_DC], 
    FailureHandling.STOP_ON_FAILURE)


Date currentDate = new Date()

SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyyy")

String outboundDate = sdf.format(currentDate)


WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Outbound Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_OutboundMonitoringList/h3_Outbound Monitoring List'), 0)

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

salesOrderNo = ((testDataSO.getValue(2, 1) + ' ') + testDataSO.getValue(2, 2))

WebUI.setText(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/input_DwnloadOutboundForm_SearchOutboundList'), 
    salesOrderNo)

WebUI.click(findTestObject('Scenario 10/S10_TC165/button_AND'))

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/input_Dt_checkbox_CheckAllOutboundList'))

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/button_Download'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadOutboundFile_Success'), 0)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_Cmn2-Write Info into Form Excel'), [('datafile') : outboundData
        , ('fileColumns') : columnNames, ('startRowFormMinusOne') : startRowFormMinusOne, ('downloadedFormPath') : latestFilePath
        , ('downloadedFormSheetname') : 'Outbound'], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 10/S10_TC165/p_Outbound Monitoring List'))

WebUI.click(findTestObject('Scenario 10/S10_TC165/button_Upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC165/li_Upload'), latestFilePath)

'need to check notification msg object'
WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadOutboundByCreate_Success'), 0)

List<String> outboundRefList = []

for (int rowIndex = 1; rowIndex <= 2; rowIndex++) {
    String outboundRefNo = outboundListData.getValue('OutboundRefNo', rowIndex)

    outboundRefList.add(outboundRefNo)

    outboundRefTestObject = findTestObject('Scenario 12/SC12_TC044/p_verifyOutboundCompleted', [('outboundRefSys') : outboundRefNo])

    // Verify the status is "Completed"
    WebUI.verifyElementText(outboundRefTestObject, 'Completed')

    KeywordUtil.logInfo("Verified Outbound Reference: $outboundRefNo with status: Completed")

    // Get the outboundNo using WebUI.getText
    outboundNoTestObject = findTestObject('Scenario 12/SC12_TC044/p_getOutboundNo', [('outboundRefSys') : outboundRefNo])

    String outboundNo = WebUI.getText(outboundNoTestObject)

    KeywordUtil.logInfo("Retrieved Outbound No: $outboundNo")

    // Copy the outboundNo to Excel using the same loop
    CustomKeywords.'copyToExcel.exel'(outboundNo, rowIndex, 1, 'Excel Files\\Scenario 10', 'S10_TestCases_Data.xlsx', 'TC165-Outbound List')
}

WebUI.closeBrowser()

