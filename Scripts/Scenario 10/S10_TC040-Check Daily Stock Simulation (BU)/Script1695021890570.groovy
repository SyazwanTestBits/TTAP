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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_LUQMAN
        , ('password') : GlobalVariable.PNA_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 10/S10_TC025/button_Stock Management Tool'))

WebUI.click(findTestObject('Scenario 10/S10_TC029/li_Daily Rundown Simulation'))

'Combine contract number from TC017 and TC021'
String contractNo = (contractNoL2 + ' ') + contractNoL3

WebUI.click(findTestObject('Scenario 10/S10_TC026/button_search_AND'))

WebUI.setText(findTestObject('Scenario 10/S10_TC025/input_Daily Rundown List'), contractNo)

WebUI.click(findTestObject('Scenario 10/S10_TC024/input_tick all'))

WebUI.click(findTestObject('Scenario 10/S10_TC025/button_Download'))

WebUI.click(findTestObject('Scenario 10/S10_TC025/li_Daily Rundown Simulation'))

not_run: WebUI.click(findTestObject('Scenario 10/S10_TC025/input_Rundown Start Date'))

not_run: CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Scenario 13/S13_TC033_Shipping_Route/div_calendar header'), 
    findTestObject('Scenario 13/S13_TC033_Shipping_Route/button next calendar'), findTestObject('Scenario 13/S13_TC033_Shipping_Route/button previous calendar'), 
    lastdayetd, lastmonthetd, lastyearetd)

WebUI.click(findTestObject('Scenario 10/S10_TC025/input_Input Download Date Range (PopUp)_includePlanFlag'))

WebUI.click(findTestObject('Scenario 10/S10_TC025/input_Include shipping plan not yet invoiced_simulateByParts'))

WebUI.click(findTestObject('Scenario 10/S10_TC025/button_Download_Input Download Date Range'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC025/p_The operation was successful'), 0)

latestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('macroexcel')

not_run: println(latestPath)

WebUI.callTestCase(findTestCase('0-Common/convertXlsmIntoXlsx/ConvertXLSMtoXLSX'), [('latestpath') : latestPath], FailureHandling.STOP_ON_FAILURE)

not_run: CustomKeywords.'Verification.verifyDynamicExcelCellValue'('Excel Files/Scenario 10/Convert from XLSM to XLSX/S10-TC025.xlsx', 
    'basic', '11112023', '25122023', 'No Usage')

excelFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_0_Daily/S10_Cmm-getn Caclulate Daily Simulation Report'), 
    [('testData') : testDataWeek, ('sheetWriteUsagePerWeek') : sheet, ('sheetForWeekReport') : sheet], FailureHandling.STOP_ON_FAILURE)


WebUI.closeBrowser()

