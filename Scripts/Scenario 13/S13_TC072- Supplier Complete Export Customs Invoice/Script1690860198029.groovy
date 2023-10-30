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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.CUST_USERNAME_USERF
        , ('password') : GlobalVariable.CUST_PWD_USERF, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.COMPANY_SUPPLIER_1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC072_1_1/button_Logistics'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC072_1_1/li_Customs Invoice List(Export)'))

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC072_1_1/input_search Customs Invoice(Export)'), invoice_no)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC072_1_1/div_TW12308002'), invoice_no)

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC072_1_1/div_Custom status'), 'Pending Customs Clearance')

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC072/div_cargos_status'), '')

WebUI.click(findTestObject('Scenario 13/S13_TC072_1_1/button_click edit'))

WebUI.click(findTestObject('Scenario 13/S13_TC072_1_1/input_Input Cargo Status_cargoStatus new'))

WebUI.click(findTestObject('Scenario 13/S13_TC072_1_1/li_cargo status'))

WebUI.click(findTestObject('Scenario 13/S13_TC072_1_1/button_cargo status update'))

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC072_1_1/p_The operation was successful'), 'The operation was successful.')

WebUI.click(findTestObject('Scenario 13/S13_TC072_1_1/button_close noti update'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC072/div_cargos_status'), 'Exp clearance completed')

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC072_1_1/input_tick all'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC072_1_1/button_Completed'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC072_1_1/button_CONFIRM'))

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC072_1_1/p_The operation was successful'), 'The operation was successful.')

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC072_1_1/div_Custom status'), 'Customs Clearance Completed')

