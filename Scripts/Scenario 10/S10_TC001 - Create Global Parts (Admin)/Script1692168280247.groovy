import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.testdata.reader.SheetPOI as SheetPOI
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.ADMIN_USERNAME
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : 'SG-TTAP'], 
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

WebUI.delay(3)

downloadedExcel = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

println(downloadedExcel)

not_run: CustomKeywords.'python.disableProtect_excel'(downloadedExcel)

for (def index : (1..dataFile.getRowNumbers())) {
    //CustomKeywords.'copyToExcel.exel_SY2'('3333', 7, 0, downloadedExcel,'Global Parts')
    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC001').getValue(2, index), index + 6, 0, downloadedExcel, 
        'Global Parts')

    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC001').getValue(3, index), index + 6, 2, downloadedExcel, 
        'Global Parts')

    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC001').getValue(4, index), index + 6, 3, downloadedExcel, 
        'Global Parts')

    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC001').getValue(5, index), index + 6, 4, downloadedExcel, 
        'Global Parts')

    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC001').getValue(6, index), index + 6, 5, downloadedExcel, 
        'Global Parts')

    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC001').getValue(7, index), index + 6, 6, downloadedExcel, 
        'Global Parts')

    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC001').getValue(8, index), index + 6, 7, downloadedExcel, 
        'Global Parts')

    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC001').getValue(9, index), index + 6, 8, downloadedExcel, 
        'Global Parts')

    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC001').getValue(10, index), index + 6, 9, downloadedExcel, 
        'Global Parts')

    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC001').getValue(11, index), index + 6, 10, downloadedExcel, 
        'Global Parts')

    CustomKeywords.'copyToExcel.exel2'(findTestData('Scenario 10/S10_TC001').getValue(12, index), index + 6, 16, downloadedExcel, 
        'Global Parts' //KeywordUtil.logInfo("Index: $index")
        )
}

absolutePath = CustomKeywords.'ManageFiles.getFileAbsolutePath'(downloadedExcel)

WebUI.uploadFile(findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/input_UploadFile_GlobalParts'), absolutePath)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SuccessUploadGlobalPartsMaster'), 0)

WebUI.sendKeys(findTestObject(null), Keys.chord(Keys.HOME))

WebUI.click(findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/button_Submit'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSure_ToDoSubmit'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SuccessSaveGlobalParts'), 0)

WebUI.closeBrowser()

