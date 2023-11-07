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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC051/button_Logistics'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC051/li_Download Delivery Plan'))

WebUI.click(findTestObject('Scenario 12/SC12_TC043/button_contractNo'))

WebUI.click(findTestObject('Scenario 12/SC12_TC043/li_supplierContract', [('contractNo') : contractNo]))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC051/button_Download'))

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC051/h6_Download Outbound Delivery Plan'), 'Download Outbound Delivery Plan.')

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC051/p_The operation was successful'), 'The operation was successful.')

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDataSpecific'(expectedDeliveryPlan, downloadedFile, [5, 6, 7, 8, 9, 10, 11, 12, 13
        , 14, 15, 16, 17, 18, 19, 20, 21, 22], [5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22])

WebUI.closeBrowser()

