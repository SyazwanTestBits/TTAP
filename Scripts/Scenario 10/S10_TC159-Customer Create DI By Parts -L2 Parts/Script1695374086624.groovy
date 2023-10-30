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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_SYAZWAN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_CUST], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 10/S10_TC159/button_Logistics'))

WebUI.click(findTestObject('Scenario 10/S10_TC159/li_Customer DI List'))

WebUI.waitForElementPresent(findTestObject('Scenario 10/S10_TC159/h3_Customer Delivery Instruction List'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC159/button_Download'))

WebUI.click(findTestObject('Scenario 10/S10_TC159/li_Download By Parts'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC159/div_Download DI By Parts.The operation was successful'), 
    0)

latestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

partIndex = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(latestPath, 2, 9)

not_run: WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_Cmn4-Write Info into Form Excel with Integer Conversion'), 
    [('datafile') : datafile, ('fileColumns') : fileColumns, ('startRowFormMinusOne') : 8, ('downloadedFormPath') : excelForm
        , ('downloadedFormSheetname') : sheetName], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_Cmn4-Write Info into Form Excel with Integer Conversion - Copy'), 
    [('datafile') : datafile, ('fileColumns') : fileColumns2, ('downloadedFormPath') : latestPath, ('downloadedFormSheetname') : sheetName
        , ('partIndexMap') : partIndex], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'copyToExcel.exel5withoutRecalculate'(findTestData('Scenario 10/S10_TC159-DI Parts Date').getValue('Date1', 
        1), 8, 12, latestPath, sheetName)

CustomKeywords.'copyToExcel.exel5withoutRecalculate'(findTestData('Scenario 10/S10_TC159-DI Parts Date').getValue('Date2', 
        1), 8, 13, latestPath, sheetName)

CustomKeywords.'copyToExcel.exel4'(contractNo, 4, 3, latestPath, sheetName)

CustomKeywords.'copyToExcel.exel4'(contractNo, 5, 3, latestPath, sheetName)

WebUI.click(findTestObject('Scenario 10/S10_TC159/button_Upload'))

not_run: filepath = CustomKeywords.'ManageFiles.getFileAbsolutePath'(excelForm)

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC159/li_Upload DI Form'), latestPath)

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC159/div_Upload DI.The operation was successful'), 0)

WebUI.setText(findTestObject('Scenario 10/S10_TC159/input_Customer Delivery Instruction List'), contractNo)

for (int row = 1; row <= 2; row++) {
    WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC159/p_verifyStatus', [('contractNo') : contractNo, ('row') : row]), 
        'New')
}

WebUI.closeBrowser()

