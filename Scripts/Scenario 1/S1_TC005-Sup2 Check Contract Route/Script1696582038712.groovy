import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
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
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_SUP2], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/button_Master'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/li_Contract Route List'))

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/input_search contract route link'), 
    contractRouteCode)

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/p_first row contract route list'), 
    contractRouteCode)

WebUI.verifyElementText(findTestObject('Scenario 9/SC9_TC003/p_verifyStatus', [('contractRouteCode') : contractRouteCode]), 
    'Building')

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/input_tick first row contract route list'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/button-click view contract detail'))

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/h3_header view or edit Contract Route Detail'), 
    'Contract Route Detail')

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/header_2Parts List InformationParts List Information'), 
    0)

//-------------looping for verify part detail------------------------------------
WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/div_Parts No header in part detail list'))

//-----------------------------------------------------------------------------------------------------
WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/button_download in view page'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/li_Download Route Detail'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/h6_Download Contract Route Detail in view page'), 
    'Download Contract Route Detail By filters.')

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/p_The operation was successful'), 
    'The operation was successful.')

WebUI.takeFullPageScreenshot()

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedContractRouteParts, downloadedFile, 2, [5, 6, 7], [0, 2, 3, 5
        , 6, 7, 8, 9, 10, 12])

WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/button_download in view page'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/li_Download Route Detail By Overview'))

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/h6_Download Contract Route Overview By selected'), 
    'Download Contract Route Overview By selected.')

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/p_The operation was successful'), 
    'The operation was successful.')

WebUI.takeFullPageScreenshot()

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedContractRoutePartsOverview, downloadedFile, 4, [6, 7, 8], [3, 4
        , 5, 6, 7, 9, 10, 11, 13, 15, 16, 17, 19, 21, 22, 23, 25])

WebUI.closeBrowser()

