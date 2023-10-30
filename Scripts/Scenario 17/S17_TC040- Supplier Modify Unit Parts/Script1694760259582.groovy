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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Master'))

WebUI.click(findTestObject('Navbar_Brivge/MasterMenu_Brivge/li_Unit Parts List'))

WebUI.waitForElementPresent(findTestObject('Page_UnitPartsList/h3_Unit Parts List'), 0)

WebUI.check(findTestObject('Scenario 17/S17_TC040/input_checkboxPartsNo', [('partNo') : partNo1]))

WebUI.check(findTestObject('Scenario 17/S17_TC040/input_checkboxPartsNo', [('partNo') : partNo2]))

WebUI.check(findTestObject('Scenario 17/S17_TC040/input_checkboxPartsNo', [('partNo') : partNo3]))

WebUI.check(findTestObject('Scenario 17/S17_TC040/input_checkboxPartsNo', [('partNo') : partNo4]))

WebUI.click(findTestObject('Page_UnitPartsList/button_Dt_CreateModify_UnitParts'))

WebUI.waitForElementPresent(findTestObject('Page_UnitPartsList/Page_CreateModify_UnitParts/h3_Create OR Modify Unit Parts'), 
    0)

WebUI.check(findTestObject('Page_UnitPartsList/Page_CreateModify_UnitParts/RadioButton_CreateModifyUnitParts_UpDownload'))

WebUI.click(findTestObject('Page_UnitPartsList/Page_CreateModify_UnitParts/button_Download'))

WebUI.delay(3)

downloadedExcel = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 17/S17_Cmn1-Write Info into Form Excel'), [('datafile') : datafile
        , ('fileColumns') : fileColumns, ('startRowFormMinusOne') : startRowFormMinusOne, ('downloadedFormPath') : downloadedExcel
        , ('downloadedFormSheetname') : downloadedFormSheetName], FailureHandling.STOP_ON_FAILURE)

absolutePath = CustomKeywords.'ManageFiles.getFileAbsolutePath'(downloadedExcel)

WebUI.uploadFile(findTestObject('Page_UnitPartsList/Page_CreateModify_UnitParts/input_UploadFile_UnitParts'), absolutePath)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadUnitPartsData_Success'), 0)

WebUI.click(findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/button_Submit'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSure_ToDoSubmit'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Page_UnitPartsList/div_NotiMsg_SaveUnitParts_Success'), 0)

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

