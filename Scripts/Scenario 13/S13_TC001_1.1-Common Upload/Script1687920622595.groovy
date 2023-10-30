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

if (isUpload == 'YES') {
    WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.ADMIN_USERNAME
            , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE], FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Navbar_Brivge/button_Master'))

    WebUI.click(findTestObject('Navbar_Brivge/MasterMenu_Brivge/li_Global_MasterDwnloadUpload'))

    WebUI.waitForElementPresent(findTestObject('Page_CommonDwnloadUpload/h3_CommonDwnloadUpload'), 0)

    WebUI.click(findTestObject('Page_CommonDwnloadUpload/button_common'))

    WebUI.click(findTestObject('Page_CommonDwnloadUpload/Page_CommonOptions/span_CommonOptions', [('commonOptions') : UploadOptions]))

    absoluteFilePath = CustomKeywords.'ManageFiles.getFileAbsolutePath'(relativeFolderPath + UploadFileName)

    WebUI.uploadFile(findTestObject('Page_CommonDwnloadUpload/input_UploadFile_Master'), absoluteFilePath)

    uploadOption = CustomKeywords.'ManageStrings.capitalizeFirstLetterEachWordToLowercaseRest'(UploadOptions)

    WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadCommonMaster_Successful', [('uploadOption') : uploadOption]), 
        0, FailureHandling.CONTINUE_ON_FAILURE)

    WebUI.takeElementScreenshot(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadCommonMaster_Successful', [('uploadOption') : uploadOption]), 
        FailureHandling.OPTIONAL)

    WebUI.closeBrowser()
}

