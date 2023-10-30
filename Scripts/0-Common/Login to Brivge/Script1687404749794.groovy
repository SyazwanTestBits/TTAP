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

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(url)

WebUI.setText(findTestObject('LoginPage_Brivge/input_Sign In_username'), username)

WebUI.setEncryptedText(findTestObject('LoginPage_Brivge/input_Sign In_password'), password)

WebUI.click(findTestObject('LoginPage_Brivge/button_Sign In'))

WebUI.setEncryptedText(findTestObject('LoginPage_Brivge/input_Verify_verificationCode'), verificationCode)

WebUI.click(findTestObject('LoginPage_Brivge/button_Verify'))

WebUI.verifyElementPresent(findTestObject('Navbar_Brivge/p_UserLogin_Username', [('username') : username]), 0)

WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/button_UserProfile'))

WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/button_CompanyOptions'))

WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/li_Company', [('company') : company]))

WebUI.waitForPageLoad(0)

