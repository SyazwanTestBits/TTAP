import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.util.CryptoUtil as CryptoUtil
import internal.GlobalVariable as GlobalVariable

def encryptedVerifCode = CryptoUtil.encode(CryptoUtil.getDefault(verificationcode))

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : password, ('verificationCode') : encryptedVerifCode, ('company') : GlobalVariable.COMPANY_SUPPLIER_1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC025n29/button_Notification'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC025n29/span_Whats New'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC025n29/h4_Announcement header', [('announcementHeader') : announcementHeader]), 
    0)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC025n29/p_Announcement description', [('announcementDescp') : announcementDescription]), 
    0)

WebUI.click(findTestObject('Scenario 13/S13_TC025n29/div_LEARN MORE', [('announcementHeader') : announcementHeader]))

WebUI.verifyElementPresent(findTestObject('Object Repository/Scenario 13/S13_TC025n29/span_learnmore-announcement type-NEW'), 
    0)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC025n29/h4_Announcement header', [('announcementHeader') : announcementHeader]), 
    0)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC025n29/p_Announcement content', [('announcementContent') : annnouncementContent]), 
    0)

WebUI.verifyElementPresent(findTestObject('Object Repository/Scenario 13/S13_TC025n29/span_Aug 7, 2023'), 0)

WebUI.closeBrowser()

