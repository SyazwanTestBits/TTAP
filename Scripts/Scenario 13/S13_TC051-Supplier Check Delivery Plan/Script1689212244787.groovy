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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : password, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC051/button_Logistics'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC051/li_Download Delivery Plan'))

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC051/input_Download_salesOrderNo'), SOid)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC051/button_Download'))

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC051/h6_Download Outbound Delivery Plan'), 'Download Outbound Delivery Plan.')

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC051/p_The operation was successful'), 'The operation was successful.')

LatestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('Scenario 13/S13_TC051.1-Supplier Check Delivery Plan- compare two test data - v2'), [('expectationExcelPath') : 'Excel Files/Scenario 13/TC051/DeliveryPlanDownload-expectation.xlsx'
        , ('NumberOfNoMatch') : 0, ('testData') : findTestData('Scenario 13/S13_TC051'), ('actualPath') : ''], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

