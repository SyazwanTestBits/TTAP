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

WebUI.click(findTestObject('Page_OutboundMonitoringList/Page_CreateOutbound/button_Download Outbound Form'))

WebUI.click(findTestObject('Scenario 12/SC12_TC044/li_Download Blank Outbound Form'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadOutboundFile_Success'), 0)

WebUI.click(findTestObject('Scenario 12/SC12_TC044/p_Outbound Monitoring List'))

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 1/S1_Cmn1-Write Info into Form Excel'), [('datafile') : outboundDetails
        , ('fileColumns') : columnNames, ('startRowFormMinusOne') : 7, ('downloadedFormPath') : latestFilePath, ('downloadedFormSheetname') : 'Outbound'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 12/SC12_TC044/button_Upload'))

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 12/SC12_TC044/li_Upload'), latestFilePath)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadOutboundByCreate_Success'), 0)

