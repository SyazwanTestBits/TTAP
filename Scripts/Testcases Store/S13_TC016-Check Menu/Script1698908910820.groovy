import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.util.CryptoUtil as CryptoUtil
import internal.GlobalVariable as GlobalVariable

def encryptedPassword = CryptoUtil.encode(CryptoUtil.getDefault(password))

def encryptedVerifCode = CryptoUtil.encode(CryptoUtil.getDefault(verificationcode))

'Login using user that just created.'
WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : encryptedPassword, ('verificationCode') : encryptedVerifCode, ('company') : GlobalVariable.ADMIN_COMPANY], 
    FailureHandling.STOP_ON_FAILURE)

'Click on the user profile.'
WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/button_UserProfile'))

'Click on Company dropdown list.'
WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/button_CompanyOptions'))

'Switch company to BU.'
WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/li_Company', [('company') : company]))

'Click on the user profile to verify that the company has successfully switched.'
WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/button_UserProfile'))

'Click on company dropdown list to verify that the company has successfully switched.'
WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/button_CompanyOptions'))

'Verify that the company has successfully switched to BU'
WebUI.verifyTextPresent(company, false)

WebUI.closeBrowser()

