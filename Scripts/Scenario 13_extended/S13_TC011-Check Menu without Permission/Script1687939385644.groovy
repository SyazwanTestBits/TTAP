import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.util.CryptoUtil as CryptoUtil
import internal.GlobalVariable as GlobalVariable

def encryptedPassword = CryptoUtil.encode(CryptoUtil.getDefault(password))

def encryptedVerifCode = CryptoUtil.encode(CryptoUtil.getDefault(verificationcode))

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : encryptedPassword, ('verificationCode') : encryptedVerifCode, ('company') : GlobalVariable.CUST_COMPANY_USERF], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementNotPresent(findTestObject('Scenario 13/S13_TC011/button_Order'), 0)

WebUI.verifyElementNotPresent(findTestObject('Scenario 13/S13_TC011/button_Logistics'), 0)

WebUI.verifyElementNotPresent(findTestObject('Scenario 13/S13_TC011/button_Accounting'), 0)

WebUI.verifyElementNotPresent(findTestObject('Scenario 13/S13_TC011/button_Management Tool'), 0)

WebUI.verifyElementNotPresent(findTestObject('Scenario 13/S13_TC011/button_Dashboard'), 0)

WebUI.verifyElementNotPresent(findTestObject('Scenario 13/S13_TC011/button_Master'), 0)

WebUI.verifyElementNotPresent(findTestObject('Scenario 13/S13_TC011/button_Privilege'), 0)

WebUI.verifyElementNotPresent(findTestObject('Scenario 13/S13_TC011/button_Stock Management Tool'), 0)

WebUI.closeBrowser()

