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

WebUI.click(findTestObject('Scenario 10/S10_TC024/button_Stock Management Tool'))

WebUI.click(findTestObject('Navbar_Brivge/StockMgmtToolMenu_Brivge/li_Order Calculation Grouping'))

WebUI.waitForElementPresent(findTestObject('Page_OrderCalcGrouping/h3_Order Calculation Grouping'), 0)

WebUI.click(findTestObject('Page_OrderCalcGrouping/button_Dwnload_OrderCalcGrouping'))

WebUI.click(findTestObject('Page_OrderCalcGrouping/li_Download Order Calc Grouping'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadOrderCalcGrouping_Success'), 0)

ordercalcgroupform = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

lastRowNumString = CustomKeywords.'getLastRow.lastRow'(ordercalcgroupform, downloadedSheetname)

int lastrow = lastRowNumString.toInteger()

WebUI.callTestCase(findTestCase('0-Common/Write Info into Downloaded Form Excel NEWMOD (Master)'), [('datafile') : datafile_createordcalcgroup
        , ('fileColumns') : downloadfilecolumns, ('lastRowIndex') : lastrow, ('downloadedFormPath') : ordercalcgroupform
        , ('downloadedFormSheetname') : downloadedSheetname], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_OrderCalcGrouping/button_Upload_OrderCalcGrouping'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Page_OrderCalcGrouping/li_Upload Order Calc Grouping'), ordercalcgroupform)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadOrderCalcGrouping_Success'), 0)

WebUI.waitForElementPresent(findTestObject('Page_OrderCalcGrouping/h3_Order Calculation Grouping'), 0)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_TC031_Cmn_1-Verify Uploaded Order Calc Grouping'), [('datafile') : datafile_createordcalcgroup
        , ('datafileColsVerification') : datafileColsToVerify], FailureHandling.STOP_ON_FAILURE)

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

