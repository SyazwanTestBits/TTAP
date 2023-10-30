import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.util.CryptoUtil as CryptoUtil
import internal.GlobalVariable as GlobalVariable

def encryptedPassword = CryptoUtil.encode(CryptoUtil.getDefault(password))

def encryptedVerifCode = CryptoUtil.encode(CryptoUtil.getDefault(verificationcode))

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : encryptedPassword, ('verificationCode') : encryptedVerifCode, ('company') : GlobalVariable.COMPANY_SUPPLIER_1], 
    FailureHandling.STOP_ON_FAILURE)

for (String menu_title : cntw_sup_poc_list) {
    WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC013/button_menu', [('role_menu_button') : menu_title]), 
        0)
}

WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/button_UserProfile'))

WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/button_CompanyOptions'))

WebUI.click(findTestObject('Navbar_Brivge/UserProfile_Brivge/li_Company', [('company') : 'PK-CUS-POC']))

WebUI.waitForPageLoad(0)

for (String menu_title : pk_cus_poc_list) {
    WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC013/button_menu', [('role_menu_button') : menu_title]), 
        0)
}

