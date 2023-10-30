import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
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
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.PNA_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC032/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC032/Page_Brivge/li_Payment Terms List'))

WebUI.click(findTestObject('Scenario 13/S13_TC032/Page_Brivge/button_Download'))

WebUI.click(findTestObject('Scenario 13/S13_TC032/Page_Brivge/li_Download'))

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

excelFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

lastRow = CustomKeywords.'util.ExcelUtils.getLastRowNumber'(excelFilePath)

KeywordUtil.logInfo("Last row number: $lastRow")

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 12/S12_Cmn2-Write Info into Form Excel_Latest'), [('datafile') : paymentTermsData
        , ('fileColumns') : filecolumns, ('startRowFormMinusOne') : lastRow, ('downloadedFormPath') : excelFilePath, ('downloadedFormSheetname') : 'MLF110'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC032/Page_Brivge/button_Upload'))

'Upload the file that has been edited.'
CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 13/S13_TC002/Page_Brivge/li_Upload'), excelFilePath)

'Verify the success message.'
WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC032/Page_Brivge/div_The operation was successful'), 0, FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

