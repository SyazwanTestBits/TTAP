import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
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
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_LUQMAN
        , ('password') : GlobalVariable.PNA_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_DC], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 10/S10_TC043/button_Logistics'))

WebUI.click(findTestObject('Scenario 10/S10_TC043/li_Outbound To WIP'))

WebUI.click(findTestObject('Scenario 10/S10_TC043/button_Upload'))

dataFilePath = CustomKeywords.'ManageFiles.getFileAbsolutePath'(dataFile)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_Cmn2-Write Info into Form Excel'), [('datafile') : testDatafile
        , ('fileColumns') : fileColumns, ('startRowFormMinusOne') : startRowFormMinusOne, ('downloadedFormPath') : dataFilePath
        , ('downloadedFormSheetname') : downloadedFormSheetname], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC043/li_Upload Wip Adjust'), dataFilePath)

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC043/div_Upload WIP Adjust.The operation was successful'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC043/button_downloadIcon'))

WebUI.click(findTestObject('Scenario 10/S10_TC043/li_Download Wip Adjust History'))

WebUI.click(findTestObject('Scenario 10/S10_TC043/input_Select Download Condition_customerCodes'))

WebUI.click(findTestObject('Scenario 10/S10_TC043/li_MY-PNA-CUS', [('customerCode') : customerCode]))

WebUI.setText(findTestObject('Scenario 10/S10_TC043/input_Select Download Condition_contractNo'), contractNo)

WebUI.setText(findTestObject('Scenario 10/S10_TC043/input_Select Download Condition_partsNo'), partsNo)

WebUI.click(findTestObject('Scenario 10/S10_TC043/button_Download'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC043/div_Download WIP Adjust.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_Cmn1_Compare Test Data'), [('LatestPath') : downloadedFile
        , ('expectationExcelPath') : dataFilePath, ('startRows') : 4, ('endRows') : 4, ('startCols') : 1, ('endCols') : 7
        , ('NumberOfNoMatch') : 0], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_Cmn1_Compare Test Data'), [('LatestPath') : downloadedFile
        , ('expectationExcelPath') : dataFilePath, ('startRows') : 4, ('endRows') : 4, ('startCols') : 8, ('endCols') : 8
        , ('NumberOfNoMatch') : 0], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

