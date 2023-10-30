import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_LUQMAN
        , ('password') : GlobalVariable.PNA_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_CUST], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Master'))

WebUI.click(findTestObject('Navbar_Brivge/MasterMenu_Brivge/li_Unit Parts List'))

WebUI.waitForElementPresent(findTestObject('Page_UnitPartsList/h3_Unit Parts List'), 0)

WebUI.click(findTestObject('Page_UnitPartsList/button_Dt_CreateModify_UnitParts'))

WebUI.waitForElementPresent(findTestObject('Page_UnitPartsList/Page_CreateModify_UnitParts/h3_Create OR Modify Unit Parts'), 
    0)

WebUI.check(findTestObject('Page_UnitPartsList/Page_CreateModify_UnitParts/RadioButton_CreateModifyUnitParts_UpDownload'))

WebUI.click(findTestObject('Page_UnitPartsList/Page_CreateModify_UnitParts/button_Download'))

downloadedExcel = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

for (def index : (1..dataFile.getRowNumbers())) {
    'Write - NEW/MOD'
    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC002').getValue(2, index), index + 6, 0, downloadedExcel, 
        'Unit Parts')

    'Write - Unit Parts No.'
    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC002').getValue(3, index), index + 6, 2, downloadedExcel, 
        'Unit Parts')

    'Write - Parts No.'
    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC002').getValue(4, index), index + 6, 3, downloadedExcel, 
        'Unit Parts')

    'Write - Unit Parts Description'
    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC002').getValue(5, index), index + 6, 4, downloadedExcel, 
        'Unit Parts')

    'Write - Parts Ref No.'
    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC002').getValue(6, index), index + 6, 5, downloadedExcel, 
        'Unit Parts')

    'Write - Back No.'
    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC002').getValue(7, index), index + 6, 6, downloadedExcel, 
        'Unit Parts')

    'Write - HS Code'
    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC002').getValue(8, index), index + 6, 7, downloadedExcel, 
        'Unit Parts')

    'Write - UOM Code'
    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC002').getValue(9, index), index + 6, 8, downloadedExcel, 
        'Unit Parts')

    'Write - Paired Parts'
    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC002').getValue(10, index), index + 6, 9, downloadedExcel, 
        'Unit Parts')

    'Write - Active Flag'
    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC002').getValue(11, index), index + 6, 12, downloadedExcel, 
        'Unit Parts')
}

absolutePath = CustomKeywords.'ManageFiles.getFileAbsolutePath'(downloadedExcel)

WebUI.uploadFile(findTestObject('Page_UnitPartsList/Page_CreateModify_UnitParts/input_UploadFile_UnitParts'), absolutePath)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadUnitPartsData_Success'), 0)

WebUI.sendKeys(findTestObject(null), Keys.chord(Keys.HOME))

WebUI.click(findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/button_Submit'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSure_ToDoSubmit'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Page_UnitPartsList/div_NotiMsg_SaveUnitParts_Success'), 0)

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

