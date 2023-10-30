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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

'Login to Brivge as Admin'
WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.ADMIN_USERNAME
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE], FailureHandling.STOP_ON_FAILURE)

'Click navbar button Master'
WebUI.click(findTestObject('Navbar_Brivge/button_Master'))

'Click option List Master Download/Upload [Global]'
WebUI.click(findTestObject('Navbar_Brivge/MasterMenu_Brivge/li_Global_MasterDwnloadUpload'))

'Wait for page title Common Download and Upload to be visible'
WebUI.waitForElementPresent(findTestObject('Page_CommonDwnloadUpload/h3_CommonDwnloadUpload'), 0)

'Click button Common'
WebUI.click(findTestObject('Page_CommonDwnloadUpload/button_common'))

'Loop Data File Common Master Download Upload [Download Options] to download Common Master Excels'
for (def rowNum = 1; rowNum <= findTestData('Data Files/Scenario 13/Common Master Download').getRowNumbers(); rowNum++) {
    def commonOptions = findTestData('Data Files/Scenario 13/Common Master Download').getValue('DownloadOptions', rowNum)

    KeywordUtil.logInfo(('Click Option: ' + commonOptions) + 'to Download')

    WebUI.click(findTestObject('Page_CommonDwnloadUpload/Page_CommonOptions/span_CommonOptions', [('commonOptions') : commonOptions]))
}

'Click button Download'
WebUI.click(findTestObject('Page_CommonDwnloadUpload/button_Download'))

'Verify Files Successfully Download'
WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SuccessDownloadMasterbyFilters'), 0, FailureHandling.CONTINUE_ON_FAILURE)

'Verify Files Successfully Download'
WebUI.takeElementScreenshot(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SuccessDownloadMasterbyFilters'), FailureHandling.OPTIONAL)

'Put a delay to give some time for files to be downloaded'
WebUI.delay(1)

'get Latest Modified File from default katalon download directory'
latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('zip')

'Unzip zip file '
CustomKeywords.'ManageFiles.unzipFileIntoParentDirectory'(latestFilePath)

latestFolderPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('folder')

'Verify all Common Master Excels has been downloaded'
CustomKeywords.'ManageFiles.checkDownloadedCommonMasterExcelFiles'(latestFolderPath)

WebUI.closeBrowser()

