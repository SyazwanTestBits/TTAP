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

WebUI.click(findTestObject('LoginPage_Brivge/p_Forget Password'))

WebUI.waitForElementPresent(findTestObject('LoginPage_Brivge/ForgetPwdPage_Brivge/h5_Forget Password'), 0)

WebUI.setText(findTestObject('LoginPage_Brivge/ForgetPwdPage_Brivge/input_Forget Password_username'), loginId)

WebUI.click(findTestObject('LoginPage_Brivge/ForgetPwdPage_Brivge/button_Send Mail'))

WebUI.verifyElementPresent(findTestObject('LoginPage_Brivge/p_PasswordResetMsg'), 0)

WebUI.verifyElementPresent(findTestObject('LoginPage_Brivge/div_NotiMsg_SendPwdResetMail_Success'), 0)

WebUI.closeBrowser()

WebUI.delay(15)

pwdResetLink = CustomKeywords.'GetBrivgePasswordResetLink.extractUserPasswordResetLink'()

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(pwdResetLink)

WebUI.waitForElementPresent(findTestObject('LoginPage_Brivge/ResetPasswordPage_Brivge/h5_Reset Password'), 0)

WebUI.setEncryptedText(findTestObject('LoginPage_Brivge/ResetPasswordPage_Brivge/input_Reset Password_new-password'), newpassword)

WebUI.setEncryptedText(findTestObject('LoginPage_Brivge/ResetPasswordPage_Brivge/input_Reset Password_confirm-new-password'), 
    newpassword)

WebUI.click(findTestObject('LoginPage_Brivge/ResetPasswordPage_Brivge/button_Reset Password'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_ResetPassword_Success'), 0)

WebUI.delay(30)

CustomKeywords.'GetBrivgePasswordConfirmChgEmail.getPasswordConfirmChangeEmail'()

newVerifCode = CustomKeywords.'GetBrivgeUserVerifCode.extractUserVerifCode'()

CustomKeywords.'copyToExcel.exel'(newVerifCode, 1, 8, 'Excel Files\\Scenario 13', 'S13_TestCases_Data.xlsx', 'TC021-Forget Password')

WebUI.setText(findTestObject('LoginPage_Brivge/input_Sign In_username'), loginId)

WebUI.setEncryptedText(findTestObject('LoginPage_Brivge/input_Sign In_password'), newpassword)

WebUI.click(findTestObject('LoginPage_Brivge/button_Sign In'))

WebUI.setText(findTestObject('LoginPage_Brivge/input_Verify_verificationCode'), newVerifCode)

WebUI.click(findTestObject('LoginPage_Brivge/button_Verify'))

WebUI.verifyElementPresent(findTestObject('Navbar_Brivge/p_UserLogin_Username', [('username') : loginId]), 0)

WebUI.closeBrowser()

