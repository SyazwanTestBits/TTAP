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

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC075/button_Logistics'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC075/li_Customs Invoice List(Import)'))

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC075/input_Customs Invoice(Import)_lcbm-MuiInput_97ca9d'), 
    invoice_no)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC075/div_first row invoice'), invoice_no)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC075/div_cargo status'), '')

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC075/div_first row status'), 'Pending Customs Clearance')

WebUI.click(findTestObject('Scenario 13/S13_TC075/button_edit cargo status'))

WebUI.click(findTestObject('Scenario 13/S13_TC075/input_List Cargo Status_cargoStatus'))

WebUI.click(findTestObject('Scenario 13/S13_TC075/li_Imp clearance completed'))

WebUI.click(findTestObject('Scenario 13/S13_TC075/button_cargo status update'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC075/p22_The operation was successful'), 'The operation was successful.')

WebUI.click(findTestObject('Scenario 13/S13_TC075/button_close noti update'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC075/div_cargo status'), 'Imp clearance completed')

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC075/input_tick first row'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC075/button_completed'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC075/p_Are you sure to do Complete(c0001)'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC075/button_CONFIRM'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC075/p22_The operation was successful'), 'The operation was successful.')

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC075/div_first row status'), 'Customs Clearance Completed')

WebUI.closeBrowser()

