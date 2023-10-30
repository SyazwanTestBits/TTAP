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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge - First Time Login'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : findTestData(
            'Scenario 13/S13_TC009-Create New User').getValue('Username', 1), ('password') : findTestData('Scenario 13/S13_TC009-Create New User').getValue(
            'BriVgeUserGenCode', 1), ('verificationCode') : findTestData('Scenario 13/S13_TC009-Create New User').getValue(
            'BriVgeVerificationCode', 1), ('company') : findTestData('Scenario 13/S13_TC009-Create New User').getValue('UserCompanyCode', 
            1), ('loginId') : findTestData('Scenario 13/S13_TC009-Create New User').getValue('LoginID', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.BRIVGE_URL)

WebUI.setText(findTestObject('LoginPage_Brivge/input_Sign In_username'), loginId)

WebUI.setText(findTestObject('LoginPage_Brivge/input_Sign In_password'), password)

WebUI.click(findTestObject('LoginPage_Brivge/button_Sign In'))

WebUI.click(findTestObject('Scenario 13/S13_TC011/Page_Brivge/button_Re-send'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC011/Page_Brivge/p_The operation was successful'), 0)

WebUI.delay(10)

userVerifCode = CustomKeywords.'GetBrivgeUserVerifCode.extractUserVerifCode'()

CustomKeywords.'copyToExcel.exel'(userVerifCode, 1, 8, 'Excel Files\\Scenario 13', 'S13_TestCases_Data.xlsx', 'TC09-Create New User')

WebUI.setText(findTestObject('LoginPage_Brivge/input_Verify_verificationCode'), userVerifCode)

WebUI.click(findTestObject('LoginPage_Brivge/button_Verify'))

WebUI.verifyElementPresent(findTestObject('Navbar_Brivge/p_UserLogin_Username2', [('username') : username]), 0)

WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/button_UserProfile'))

WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/button_CompanyOptions'))

WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/li_Company', [('company') : company]))

WebUI.waitForPageLoad(0)

WebUI.closeBrowser()

