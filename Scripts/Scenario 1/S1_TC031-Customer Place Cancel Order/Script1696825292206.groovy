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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_Place ChangeCancel'))

WebUI.setText(findTestObject('Object Repository/Scenario 1/S1_TC031/input_textField'), custOrderNo)

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 1/S1_TC031/p_verifyCO'), custOrderNo)

WebUI.click(findTestObject('Object Repository/Scenario 1/S1_TC031/button_Cancel _Button'))

WebUI.click(findTestObject('Object Repository/Scenario 1/S1_TC031/button_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Scenario 1/S1_TC031/div_Cancel Order.The operation was successful'), 
    0)

WebUI.callTestCase(findTestCase('Scenario 1/S1_TC031-Get Request No'), [('contractRouteCode') : findTestData('Scenario 1/S1_TC002-BU1 to Customer Contract').getValue('ContractRouteCode', 1)], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

