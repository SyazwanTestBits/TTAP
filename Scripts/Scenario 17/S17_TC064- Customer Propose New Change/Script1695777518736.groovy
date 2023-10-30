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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Scenario 17/S17_TC057/Page_Home Page - Brivge/li_ChangeCancel Request List'))

'Verify Order Change with Status = New\r\n'
WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC034/p_Order Change', [('requestNo') : requestNo]), 'New')

WebUI.waitForElementPresent(findTestObject('Scenario 17/S17_TC057/Page_Home Page - Brivge/h3_ChangeCancel Request List'), 
    0)

WebUI.click(findTestObject('Scenario 12/SC12_TC019/p_detailButton', [('requestNo') : requestNo]))

WebUI.click(findTestObject('Scenario 10/S10_TC088/button_Propose New'))

WebUI.setText(findTestObject('Scenario 10/S10_TC088/textarea_Input Reject Reason_lcbm-lcbm2348'), 'Test Propose New')

WebUI.click(findTestObject('Scenario 17/S17_TC057/Page_ChangeCancel Request Detail - Brivge/button_Confirm_ProposeNew'))

WebUI.click(findTestObject('Scenario 17/S17_TC064/button_Confirm'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC088/div_Propose New Confirm.The operation was successful'), 
    0)

WebUI.waitForElementPresent(findTestObject('Scenario 10/S10_TC088/h3_ChangeCancel Request Detail'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC088/p_ChangeCancel Request List'))

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC088/p_verifyStatus', [('requestNo') : requestNo]), 'Propose New')

//
WebUI.closeBrowser()

