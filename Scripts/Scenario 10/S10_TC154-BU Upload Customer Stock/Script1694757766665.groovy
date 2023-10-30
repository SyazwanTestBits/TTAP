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

WebUI.click(findTestObject('Scenario 10/S10_TC154/li_Customer Stock'))

not_run: def contractNoText = ''

not_run: for (String contractNo : contractNoList) {
    contractNoText += (contractNo + ' ')
}

not_run: contractNoText = contractNoText.trim()

not_run: println(contractNoText)

WebUI.setText(findTestObject('Scenario 10/S10_TC154/input_Customer Stock List'), contractNo)

WebUI.click(findTestObject('Scenario 10/S10_TC154/button_AND'))

WebUI.click(findTestObject('Scenario 10/S10_TC154/div_tickall'))

WebUI.click(findTestObject('Scenario 10/S10_TC154/button_download'))

WebUI.click(findTestObject('Scenario 10/S10_TC154/li_Download'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC154/div_Customer Stock DownloadThe operation was successful'), 
    0)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

partsNoRowIndices = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(latestFilePath, 0, 7)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_Cmn3-Write Info into Form Excel with Sorting'), [('datafile') : datafile
        , ('fileColumns') : fileColumns, ('mapDataIndices') : partsNoRowIndices, ('downloadedFormPath') : latestFilePath
        , ('downloadedFormSheetname') : downloadedFormSheetname], FailureHandling.STOP_ON_FAILURE)

for (int row = 7; row <= 13; row++) {
    CustomKeywords.'copyToExcel.writeDateToExcel'(date, row, 6, latestFilePath, 'SMGTF310')
}

WebUI.delay(2)

WebUI.click(findTestObject('Scenario 10/S10_TC154/button_upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC154/li_Upload Master'), latestFilePath)

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC154/div_Upload Customer StockNo records in the upload file is to be upload.(w0010)'), 
    0)

TestData testData = findTestData('Scenario 10/S10_TC154-BU Upload Customer Stock')

int rowCount = testData.getRowNumbers()

for (int row = 1; row <= rowCount; row++) {
    String partNumber = testData.getValue('PartsNo', row)

    WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC154/p_verifyStock', [('partsNo') : partNumber]), '250')

    KeywordUtil.logInfo("PartsNo $partNumber stock is verified: 250")
}

'Check buyer stock date successfully updated into system\r\n'
WebUI.closeBrowser()

