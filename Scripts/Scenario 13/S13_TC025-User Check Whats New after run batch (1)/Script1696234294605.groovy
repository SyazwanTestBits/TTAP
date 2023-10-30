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

def encryptedVerifCode = CryptoUtil.encode(CryptoUtil.getDefault(verificationcode))

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : password, ('verificationCode') : encryptedVerifCode, ('company') : 'PK-CUS-POC-S13-5'], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC025n29/button_Notification'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC025n29/span_Whats New'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC025n29/h4_Announcement header', [('announcementHeader') : announcementHeader]), 
    0)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC025n29/p_Announcement description', [('announcementDescp') : announcementDescription]), 
    0)

WebUI.click(findTestObject('Scenario 13/S13_TC025n29/div_LEARN MORE', [('announcementheader') : announcementHeader]))

WebUI.verifyElementPresent(findTestObject('Object Repository/Scenario 13/S13_TC025n29/span_learnmore-announcement type-NEW'), 
    0)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC025n29/h4_Announcement header', [('announcementHeader') : announcementHeader]), 
    0)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC025n29/p_Announcement content', [('announcementContent') : annnouncementContent]), 
    0)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC025n29/span_date', [('date') : date]), 0)

WebUI.takeScreenshot()

WebUI.closeBrowser()

