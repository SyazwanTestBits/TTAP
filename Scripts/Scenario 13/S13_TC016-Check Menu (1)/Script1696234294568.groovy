import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.util.CryptoUtil as CryptoUtil
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

def encryptedPassword = CryptoUtil.encode(CryptoUtil.getDefault(password))

def encryptedVerifCode = CryptoUtil.encode(CryptoUtil.getDefault(verificationcode))

'Login using user that just created.'
WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : encryptedPassword, ('verificationCode') : encryptedVerifCode, ('company') : company], FailureHandling.STOP_ON_FAILURE)

'Click on the user profile.'
WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/button_UserProfile'))

'Click on Company dropdown list.'
WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/button_CompanyOptions'))

'Switch company to BU.'
WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/li_Company', [('company') : companyBU]))

'Click on the user profile to verify that the company has successfully switched.'
WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/button_UserProfile'))

'Click on company dropdown list to verify that the company has successfully switched.'
WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/button_CompanyOptions'))

WebUI.verifyElementPresent(findTestObject('Navbar_Brivge/button_Order'), 0)

WebUI.verifyElementPresent(findTestObject('Navbar_Brivge/button_Master'), 0)

WebUI.verifyElementPresent(findTestObject('Navbar_Brivge/button_Dashboard'), 0)

WebUI.verifyElementPresent(findTestObject('Navbar_Brivge/button_Privilege'), 0)

WebUI.verifyElementPresent(findTestObject('Navbar_Brivge/button_Logistics'), 0)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC055/button_Accounting'), 0)

'Verify that the company has successfully switched to BU'
WebUI.verifyTextPresent(company, false)

WebUI.closeBrowser()

