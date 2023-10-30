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
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC024/button_Stock Management Tool'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC026/li_Weekly Stock Simulation'))

WebUI.click(findTestObject('Scenario 10/S10_TC026/button_search_AND'))

'combine contract no from TC17 TC21'
String contractSearch = (contractNoBUL2 + ' ') + contractNoBUL3

WebUI.setText(findTestObject('Scenario 10/S10_TC026/input_search'), contractSearch)

WebUI.click(findTestObject('Scenario 10/S10_TC026/input_tick_all'))

WebUI.click(findTestObject('Scenario 10/S10_TC026/button_download'))

WebUI.click(findTestObject('Scenario 10/S10_TC026/li_download_Weekly Rundown'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC026/p_The operation was successful'), 0)

latestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('macroexcel')

println(latestPath)

WebUI.callTestCase(findTestCase('0-Common/ConvertXLSMtoXLSX'), [('latestpath') : latestPath], FailureHandling.STOP_ON_FAILURE)

latestX = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_0_Weekly/S10_Cmm-Compare Weekly Simulation Report - Copy'), 
    [('actualPath') : latestX, ('expectationPath') : testDataL3, ('columnNameStock') : [('Stock Qty') : 16, ('Stock Qty (On-Hold)') : 17
            , ('Adjustment (Stock)') : 18, ('Adjustment (On-Hold)') : 19, ('Total (Excludes On-Hold)') : 20, ('Stock Days') : 21
            , ('Low Stock Alert') : 23], ('columnName1') : [('Week 1 In') : 29, ('Week 1 Out') : 30, ('Week 1 Balance') : 31
            , ('Week 1 Stock Days') : 32, ('Week 2 In') : 33, ('Week 2 Out') : 34, ('Week 2 Balance') : 35, ('Week 2 Stock Days') : 36
            , ('Week 3 In') : 37, ('Week 3 Out') : 38, ('Week 3 Balance') : 39, ('Week 3 Stock Days') : 40, ('Week 4 In') : 41
            , ('Week 4 Out') : 42, ('Week 4 Balance') : 43, ('Week 4 Stock Days') : 44, ('Week 5 In') : 45, ('Week 5 Out') : 46
            , ('Week 5 Balance') : 47, ('Week 5 Stock Days') : 48, ('Week 6 In') : 49, ('Week 6 Out') : 50, ('Week 6 Balance') : 51
            , ('Week 6 Stock Days') : 52, ('Week 7 In') : 53, ('Week 7 Out') : 54, ('Week 7 Balance') : 55, ('Week 7 Stock Days') : 56]
        , ('columnName2') : [('Week 1 In') : 33, ('Week 1 Out') : 34, ('Week 1 Balance') : 35, ('Week 1 Stock Days') : 36
            , ('Week 2 In') : 37, ('Week 2 Out') : 38, ('Week 2 Balance') : 39, ('Week 2 Stock Days') : 40, ('Week 3 In') : 41
            , ('Week 3 Out') : 42, ('Week 3 Balance') : 43, ('Week 3 Stock Days') : 44, ('Week 4 In') : 45, ('Week 4 Out') : 46
            , ('Week 4 Balance') : 47, ('Week 4 Stock Days') : 48, ('Week 5 In') : 49, ('Week 5 Out') : 50, ('Week 5 Balance') : 51
            , ('Week 5 Stock Days') : 52, ('Week 6 In') : 53, ('Week 6 Out') : 54, ('Week 6 Balance') : 55, ('Week 6 Stock Days') : 56
            , ('Week 7 In') : 57, ('Week 7 Out') : 58, ('Week 7 Balance') : 59, ('Week 7 Stock Days') : 60], ('columNameWeek') : [:]
        , ('testDataL2') : testDataL2, ('valueInL2') : valueInL2], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

