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

WebUI.click(findTestObject('Scenario 10/S10_TC024/li_Stock Management  Order Calculation Setting'))

WebUI.waitForElementPresent(findTestObject('Page_StockMngmntOrderCalcSetting/h3_Stock Management  Order Calculation Setting'), 
    0)

WebUI.setText(findTestObject('Page_StockMngmntOrderCalcSetting/input_Search_StockMngmtOrderCalcSetting'), contractNo)

WebUI.check(findTestObject('Page_StockMngmntOrderCalcSetting/input_Checkall_StockMngmtOrderCalcSettingList'))

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Page_StockMngmntOrderCalcSetting/button_Download_StockMngmtOrderCalcSetting'))

WebUI.click(findTestObject('Page_StockMngmntOrderCalcSetting/li_Download Alarm Usage Pattern'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_AlarmUsagePatternDwnloadBySelection_Success'), 
    0)

baseFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

partsNoRowIndices = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(baseFile, 2, 7)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_TC033_Cmn1-Write Info into Form Excel (Stock Mngmt and Order Calc Setting)'), 
    [('datafile') : datafile, ('fileColumns') : mapKeyandColIndex, ('mapDataIndices') : partsNoRowIndices, ('downloadedFormPath') : baseFile
        , ('downloadedFormSheetname') : downloadedSheetname], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 10/S10_TC024/button_upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC024/li_Upload Alarm Usage Pattern'), baseFile)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadAlarmUsagePattern_Success'), 0)

WebUI.refresh()

for (def index : (1..datafile.getRowNumbers())) {
    def partsNo = datafile.getValue('PartsNo', index)

    println(partsNo)

    def orderCalcGroupNo = datafile.getValue('OrderCalculationGroupingNo', index)

    WebUI.setText(findTestObject('Page_StockMngmntOrderCalcSetting/input_Search_StockMngmtOrderCalcSetting'), partsNo)

    WebUI.verifyElementText(findTestObject('Page_StockMngmntOrderCalcSetting/tr_td_StockMngmtOrderCalcSetting_OCGN', [('partsNo') : partsNo]), 
        orderCalcGroupNo)

    WebUI.click(findTestObject('Page_StockMngmntOrderCalcSetting/tr_button_StockMngmtOrderCalcSetting_View', [('partsNo') : partsNo]))

    WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_TC033_Cmn2-Verify Uploaded Stock Mngmnt and Order Calc Setting'), 
        [('datafile') : datafile, ('index') : index, ('sheetname') : 'TC033-Up Stock Mngmt Calc Set', ('relativeFilePath') : 'Excel Files/Scenario 10/S10_TestCases_Data.xlsx'], 
        FailureHandling.STOP_ON_FAILURE)
}

WebUI.closeBrowser()

