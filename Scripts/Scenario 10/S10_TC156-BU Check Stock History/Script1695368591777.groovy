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

WebUI.click(findTestObject('Scenario 10/S10_TC156/li_Stock History'))

def contractNoText = ''

for (String contractNo : contractNoList) {
    contractNoText += (contractNo + ' ')
}

contractNoText = contractNoText.trim()

println(contractNoText)

WebUI.setText(findTestObject('Scenario 10/S10_TC156/input_Stock History'), contractNoText)

WebUI.click(findTestObject('Scenario 10/S10_TC154/button_AND'))

WebUI.click(findTestObject('Scenario 10/S10_TC156/button_filterDate'))

WebUI.click(findTestObject('Scenario 10/S10_TC156/div_22', [('dateNo') : dateNo]))

WebUI.click(findTestObject('Scenario 10/S10_TC156/button_OK'))

WebUI.click(findTestObject('Scenario 10/S10_TC156/div_tickAll'))

WebUI.click(findTestObject('Scenario 10/S10_TC156/button_download'))

WebUI.click(findTestObject('Scenario 10/S10_TC156/li_Download'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC156/div_Imp Inventory DownloadThe operation was successful'), 
    0)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_Cmn1_Compare Test Data'), [('LatestPath') : latestFilePath
        , ('expectationExcelPath') : 'Excel Files\\Scenario 10\\S10-TC156-Stock History List Expect.xlsx', ('startRows') : 5
        , ('endRows') : 11, ('startCols') : 9, ('endCols') : 12, ('NumberOfNoMatch') : 0], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

