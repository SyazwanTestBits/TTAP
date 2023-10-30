import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Outbound Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_OutboundMonitoringList/h3_Outbound Monitoring List'), 0)

WebUI.click(findTestObject('Page_OutboundMonitoringList/button_CreateOutboundResults'))

WebUI.waitForElementPresent(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/h3_Create Outbound'), 0)

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/button_Download Outbound Form'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/li_Select Parts  Download Outbound Form'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/h5_Download Outbound Form'), 
    0)

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/input_Buyer'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/li_BuyerOption', [
            ('buyer') : buyer]), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/input_Receiver'), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/li_ReceiverOption', 
        [('receiver') : receiver]), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/button_Step 2. Select Parts'))

WebUI.setText(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/input_DwnloadOutboundForm_SearchOutboundList'), 
    salesOrderNo)

WebUI.check(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/input_Dt_checkbox_CheckAllOutboundList'), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/Modal_DwnloadOutboundForm/button_Download'), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadOutboundFile_Success'), 0)

WebUI.back()

WebUI.waitForElementPresent(findTestObject('Page_OutboundMonitoringList/h3_Outbound Monitoring List'), 0)

outboundForm = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 9/S9_Cmn1-Write Info into Form Excel'), [('datafile') : datafile_outbound
        , ('fileColumns') : filecolumns_index, ('startRowFormMinusOne') : 7, ('downloadedFormPath') : outboundForm, ('downloadedFormSheetname') : 'Outbound'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_OutboundMonitoringList/button_Upload_OutboundMonitoringList'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Page_OutboundMonitoringList/li_Upload'), outboundForm)

WebUI.verifyElementPresent(findTestObject('Page_OutboundMonitoringList/div_Upload Outbound.The operation was successful'), 
    0)

WebUI.takeFullPageScreenshot()

List<String> outboundRefList = []

for (int rowIndex = 1; rowIndex <= 2; rowIndex++) {
	String outboundRefNo = outboundData.getValue('OutboundRefNo', rowIndex)

	outboundRefList.add(outboundRefNo)

	TestObject outboundRefTestObject = findTestObject('Scenario 12/SC12_TC044/p_verifyOutboundCompleted', [('outboundRefSys') : outboundRefNo])

	// Verify the status is "Completed"
	WebUI.verifyElementText(outboundRefTestObject, 'Completed')

	KeywordUtil.logInfo("Verified Outbound Reference: $outboundRefNo with status: Completed")

	// Get the outboundNo using WebUI.getText
	TestObject outboundNoTestObject = findTestObject('Scenario 12/SC12_TC044/p_getOutboundNo', [('outboundRefSys') : outboundRefNo])

	String outboundNo = WebUI.getText(outboundNoTestObject)

	KeywordUtil.logInfo("Retrieved Outbound No: $outboundNo")

	// Copy the outboundNo to Excel using the same loop
	CustomKeywords.'copyToExcel.exel'(outboundNo, rowIndex, 1, 'Excel Files\\Scenario 9', 'S9_TestCases_Data.xlsx', 'TC47-OutboundNo')
}

WebUI.closeBrowser()

