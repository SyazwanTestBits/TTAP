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
import com.kms.katalon.core.annotation.Keyword as Keyword
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : GlobalVariable.PNA_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC032/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC032/Page_Brivge/li_Payment Terms List'))

WebUI.click(findTestObject('Scenario 13/S13_TC032/Page_Brivge/button_Download'))

WebUI.click(findTestObject('Scenario 13/S13_TC032/Page_Brivge/li_Download'))

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

excelFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

lastRow = CustomKeywords.'util.ExcelUtils.getLastRowNumber'(excelFilePath)

KeywordUtil.logInfo("Last row number: $lastRow")

not_run: for (def index : (1..dataFile.getRowNumbers())) {
    CustomKeywords.'copyToExcel.exel3'(findTestData('Scenario 10/S10_TC003').getValue(2, index), lastRow + 1, 0, excelFilePath, 
        'MLF110')

    CustomKeywords.'copyToExcel.exel3'(findTestData('Scenario 10/S10_TC003').getValue(3, index), lastRow + 1, 2, excelFilePath, 
        'MLF110')

    CustomKeywords.'copyToExcel.exel3'(findTestData('Scenario 10/S10_TC003').getValue(4, index), lastRow + 1, 3, excelFilePath, 
        'MLF110')

    CustomKeywords.'copyToExcel.exel3'(findTestData('Scenario 10/S10_TC003').getValue(5, index), lastRow + 1, 4, excelFilePath, 
        'MLF110')

    CustomKeywords.'copyToExcel.exel3'(findTestData('Scenario 10/S10_TC003').getValue(6, index), lastRow + 1, 5, excelFilePath, 
        'MLF110')

    CustomKeywords.'copyToExcel.exel3'(findTestData('Scenario 10/S10_TC003').getValue(7, index), lastRow + 1, 6, excelFilePath, 
        'MLF110')

    CustomKeywords.'copyToExcel.exel3'(findTestData('Scenario 10/S10_TC003').getValue(8, index), lastRow + 1, 7, excelFilePath, 
        'MLF110')
}

not_run: WebUI.click(findTestObject('Scenario 13/S13_TC032/Page_Brivge/button_Upload'))

not_run: AbsolutePath = CustomKeywords.'ManageFiles.getFileAbsolutePath'(excelFilePath)

'Upload the file that has been edited.'
not_run: CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 13/S13_TC002/Page_Brivge/li_Upload'), AbsolutePath)

'Verify the success message.'
not_run: WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC032/Page_Brivge/div_The operation was successful'), 
    0)

not_run: WebUI.closeBrowser()

