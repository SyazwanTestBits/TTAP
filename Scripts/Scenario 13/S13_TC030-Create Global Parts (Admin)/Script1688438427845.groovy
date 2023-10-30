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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.ADMIN_USERNAME
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.ADMIN_COMPANY], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Master'))

WebUI.click(findTestObject('Navbar_Brivge/MasterMenu_Brivge/li_Global Parts List'))

WebUI.waitForElementPresent(findTestObject('Page_GlobalPartsList/h3_Global Parts List'), 0)

WebUI.click(findTestObject('Page_GlobalPartsList/button_CreateModify_GlobalParts'))

WebUI.waitForElementPresent(findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/h3_Create OR Modify Global Parts'), 
    0)

WebUI.check(findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/input_CreateModifyGlobalParts_DwnloadUpload'))

WebUI.waitForElementPresent(findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/h6_Select Download Or Upload'), 
    0)

WebUI.click(findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/button_Download'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SuccessDwnloadGlobalPartsMaster'), 0)

CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

absolute_globalpart_path = CustomKeywords.'ManageFiles.getFileAbsolutePath'(relative_globalpart_filepath)

WebUI.uploadFile(findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/input_UploadFile_GlobalParts'), absolute_globalpart_path)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SuccessUploadGlobalPartsMaster'), 0)

not_run: CustomKeywords.'Verification.verifyDisplayUploadedGlobalPartsData'()

not_run: CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/button_Submit'), 
    0)

WebUI.sendKeys(findTestObject(null), Keys.chord(Keys.HOME))

WebUI.click(findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/button_Submit'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSure_ToDoSubmit'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SuccessSaveGlobalParts'), 0)

not_run: WebUI.waitForElementPresent(findTestObject('Page_GlobalPartsList/h3_Global Parts List'), 0)

not_run: CustomKeywords.'Verification.verifySubmittedGlobalParts'()

WebUI.closeBrowser()

