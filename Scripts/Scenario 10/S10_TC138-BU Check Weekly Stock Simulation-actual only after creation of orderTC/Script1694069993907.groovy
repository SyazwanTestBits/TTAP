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

String searchPart = ''

searchPart = (contractNoBUL2 + ' ') + contractNoBUL3

not_run: for (int i = 1; i <= testData.getRowNumbers(); i++) {
    searchPart = ((searchPart + testData.getValue('Part no', i)) + ' ')
}

WebUI.setText(findTestObject('Scenario 10/S10_TC026/input_search'), searchPart)

WebUI.click(findTestObject('Scenario 10/S10_TC026/input_tick_all'))

WebUI.click(findTestObject('Scenario 10/S10_TC026/button_download'))

WebUI.click(findTestObject('Scenario 10/S10_TC026/li_download_Weekly Rundown'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC026/p_The operation was successful'), 0)

latestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('macroexcel')

println(latestPath)

WebUI.callTestCase(findTestCase('0-Common/ConvertXLSMtoXLSX'), [('latestpath') : latestPath], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

