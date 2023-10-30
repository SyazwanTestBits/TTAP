import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_FATIN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC024/button_Stock Management Tool'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC155/li_Stock Adjustment'))

def contractNoText = ''

for (String contractNo : contractNoList) {
    contractNoText += (contractNo + ' ')
}

contractNoText = contractNoText.trim()

println(contractNoText)

WebUI.setText(findTestObject('Scenario 10/S10_TC155/input_Stock Adjustment List'), contractNoText)

WebUI.click(findTestObject('Scenario 10/S10_TC154/button_AND'))

WebUI.click(findTestObject('Scenario 10/S10_TC155/div_tickall'))

WebUI.click(findTestObject('Scenario 10/S10_TC155/button_download'))

WebUI.click(findTestObject('Scenario 10/S10_TC155/li_Download'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC155/div_Imp Stock Adjustment DownloadThe operation was successful'), 
    0)

WebUI.delay(3)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

partsNoRowIndices = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(latestFilePath, 1, 4)

println(partsNoRowIndices)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_Cmn3-Write Info into Form Excel with Sorting'), [('datafile') : datafile
        , ('fileColumns') : fileColumns, ('mapDataIndices') : partsNoRowIndices, ('downloadedFormPath') : latestFilePath
        , ('downloadedFormSheetname') : downloadedFormSheetname], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'copyToExcel.exel'(adjustmentDate, 1, 2, latestFilePath, '', 'SMGTF411')

WebUI.click(findTestObject('Scenario 10/S10_TC155/button_upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC154/li_Upload Master'), latestFilePath)

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC155/div_Upload ImpStock AdjustThe operation was successful'), 
    0)

TestData testData = findTestData('Scenario 10/S10_TC154-BU Upload Stock Adjustment')

int rowCount = testData.getRowNumbers()

for (int row = 1; row <= rowCount; row++) {
    String partNumber = testData.getValue('PartsNo', row)

    WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC155/p_verifyAdjustmentToStock', [('partsNo') : partNumber]), 
        '50')

    WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC155/p_verifyAdjustmentToOnHold', [('partsNo') : partNumber]), 
        '10')

    KeywordUtil.logInfo("PartsNo $partNumber Adjustment to stock qty is verified: 50")

    KeywordUtil.logInfo("PartsNo $partNumber Adjustment to On Hold qty is verified: 10")
}

WebUI.closeBrowser()

