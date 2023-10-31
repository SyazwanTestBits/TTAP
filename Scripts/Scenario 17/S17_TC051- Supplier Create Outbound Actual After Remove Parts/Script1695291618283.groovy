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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Outbound Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_OutboundMonitoringList/h3_Outbound Monitoring List'), 0)

WebUI.click(findTestObject('Scenario 17/S17_TC051/Page_Outbound Monitoring List - Brivge/Page_Outbound Monitoring List - Brivge/button_Download_OutboundMonitoringList'))

WebUI.click(findTestObject('Scenario 17/S17_TC051/Page_Outbound Monitoring List - Brivge/Page_Outbound Monitoring List - Brivge/li_Download Blank Outbound Form'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadOutboundFile_Success'), 0)

WebUI.delay(2)

downloadedExcel = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 17/S17_Cmn1-Write Info into Form Excel'), [('datafile') : dataFile
        , ('fileColumns') : fileColumns, ('startRowFormMinusOne') : startRowFormMinusOne, ('downloadedFormPath') : downloadedExcel
        , ('downloadedFormSheetname') : downloadedFormSheetName], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(55)

WebUI.click(findTestObject('Scenario 17/S17_TC051/Page_Outbound Monitoring List - Brivge/button_Upload_OutboundMonitoringList'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 17/S17_TC051/Page_Outbound Monitoring List - Brivge/li_Upload__OutboundMonitoringList'), 
    downloadedExcel)

WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('Page_OutboundMonitoringList/div_Upload Outbound.The operation was successful'), 
    0)

WebUI.setText(findTestObject('Scenario 17/S17_TC006/input_Search'), outboundRef1)

outbound1 = WebUI.getText(findTestObject('Scenario 17/S17_TC051/p_grabOutboundNo', [('outboundRef') : outboundRef1]))

CustomKeywords.'util.clearTextJS.clearElementText'(findTestObject('Scenario 17/S17_TC006/input_Search'))

WebUI.setText(findTestObject('Scenario 17/S17_TC006/input_Search'), outboundRef2)

outbound2 = WebUI.getText(findTestObject('Scenario 17/S17_TC051/p_grabOutboundNo', [('outboundRef') : outboundRef2]))

println(outbound1)

println(outbound2)

CustomKeywords.'copyToExcel.exel'(outbound1, 1, 1, filePath, fileName, sheetName)

CustomKeywords.'copyToExcel.exel'(outbound2, 2, 1, filePath, fileName, sheetName)

WebUI.closeBrowser()

