import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_DC3], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Outbound Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_OutboundMonitoringList/h3_Outbound Monitoring List'), 0)

WebUI.click(findTestObject('Page_OutboundMonitoringList/button_CreateOutboundResults'))

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/button_Download Outbound Form'))

WebUI.click(findTestObject('Scenario 12/SC12_TC044/li_Download Blank Outbound Form'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadOutboundFile_Success'), 0)

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Scenario 12/SC12_TC044/p_Outbound Monitoring List'))

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 1/S1_Cmn1-Write Info into Form Excel'), [('datafile') : outboundDetails
        , ('fileColumns') : columnNames, ('startRowFormMinusOne') : 7, ('downloadedFormPath') : latestFilePath, ('downloadedFormSheetname') : 'Outbound'], 
    FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.click(findTestObject('Scenario 12/SC12_TC044/button_Upload'))

not_run: CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 12/SC12_TC044/li_Upload'), latestFilePath)

not_run: WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadOutboundByCreate_Success'), 
    0)

WebUI.takeFullPageScreenshot()

List<String> outboundRefList = []

for (int rowIndex = 1; rowIndex <= 2; rowIndex++) {
    String outboundRefNo = outboundNoData.getValue('OutboundRefNo', rowIndex)

    outboundRefList.add(outboundRefNo)

    // Verify the status is "Completed"
    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC044/p_verifyOutboundCompleted', [('outboundRefSys') : outboundRefNo]), 
        'Completed')

    KeywordUtil.logInfo("Verified Outbound Reference: $outboundRefNo with status: Completed")

    // Get the outboundNo using WebUI.getText
    outboundNo = WebUI.getText(findTestObject('Scenario 12/SC12_TC044/p_getOutboundNo', [('outboundRefSys') : outboundRefNo]))

    KeywordUtil.logInfo("Retrieved Outbound No: $outboundNo")

    // Copy the outboundNo to Excel using the same loop
    CustomKeywords.'copyToExcel.exel'(outboundNo, rowIndex, 1, 'Excel Files\\Scenario 1', 'S1_TestCases_Data.xlsx', 'TC111-OutboundNo')
}

WebUI.closeBrowser()

