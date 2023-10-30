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

WebUI.click(findTestObject('Scenario 13/S13_TC069/li_Inbound Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_InboundMonitorList/h3_Inbound Monitor List'), 0)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 13/S13_TC078-Cmn_1-Verify Inbounds From Supplier Type'), [('outboundNo') : outboundNoRef
        , ('testdata') : testdata_updateInbound], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_InboundMonitorList/button_Create_InboundMonitorList'))

WebUI.waitForElementPresent(findTestObject('Page_InboundMonitorList/Page_UpdateInboundResults/h3_Update Inbound Results'), 
    0)

WebUI.click(findTestObject('Page_InboundMonitorList/Page_UpdateInboundResults/button_Download_step_1'))

WebUI.setText(findTestObject('Page_InboundMonitorList/Page_UpdateInboundResults/input_Search_Dwnload_InboundResultsForm'), 
    outboundNo)

WebUI.click(findTestObject('Page_InboundMonitorList/Page_UpdateInboundResults/input_Checkbox_CheckAll_InboundResults'))

WebUI.click(findTestObject('Page_InboundMonitorList/Page_UpdateInboundResults/button_Download'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_DwnloadInboundPlanForUpload_Success'), 0)

inboundPlanForm = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

partsNoRowIndexes = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(inboundPlanForm, 7, 4)

println(partsNoRowIndexes)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 9/S9_Cmn1-Write Info into Form Excel with Sorting'), [('datafile') : testdata_updateInbound
        , ('fileColumns') : updateInboundColumIndexes, ('mapDataIndices') : partsNoRowIndexes, ('downloadedFormPath') : inboundPlanForm
        , ('downloadedFormSheetname') : 'Inbound'], FailureHandling.STOP_ON_FAILURE)

not_run: for (def index : (1..testdata_updateInbound.getRowNumbers())) {
    def inboundNoRefValue = testdata_updateInbound.getValue('InboundNoRef', index)

    def inboundDateValue = testdata_updateInbound.getValue('InboundDate', index)

    CustomKeywords.'copyToExcel.exel2'(inboundNoRefValue, index + 3, 3, inboundPlanForm, 'Inbound')

    CustomKeywords.'copyToExcel.exel2'(inboundDateValue, index + 3, 4, inboundPlanForm, 'Inbound')
}

WebUI.uploadFile(findTestObject('Page_InboundMonitorList/Page_UpdateInboundResults/input_Upload_UpdateInboundResultsForm'), 
    inboundPlanForm)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadInboundResult_Success'), 0)

WebUI.scrollToElement(findTestObject('Page_InboundMonitorList/Page_UpdateInboundResults/button_Step 3. CHECK UPLOADED INBOUND RESULTS_step_3'), 
    0)

WebUI.verifyTextPresent(inboundNoRef, false)

formattedInboundDate = CustomKeywords.'commonUtils.parseDateInfoDesiredDateFormat2'(inboundDate, 'dd MMM yyyy', 'MMM dd, yyyy')

WebUI.verifyTextPresent(formattedInboundDate, false)

WebUI.takeFullPageScreenshot()

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Page_InboundMonitorList/Page_UpdateInboundResults/button_Confirm'), 
    0)

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSureToDo_Confirm'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_ConfirmInboundResult_Success'), 0)

WebUI.waitForElementPresent(findTestObject('Page_InboundMonitorList/h3_Inbound Monitor List'), 0)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 13/S13_TC078-Cmn_2-Verify Inbounds Status Completed'), [('outboundNo') : outboundNoRef
        , ('testdata') : testdata_updateInbound], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

