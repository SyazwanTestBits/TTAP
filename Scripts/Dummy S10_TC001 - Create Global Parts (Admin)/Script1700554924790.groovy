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

not_run: WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : 'https://test.brivge.com/', ('username') : 'admin'
        , ('password') : 'HeCM15nHKBI=', ('verificationCode') : '9811222', ('company') : 'SG-TTAP'], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.click(findTestObject('Navbar_Brivge/button_Master'))

not_run: WebUI.click(findTestObject('Navbar_Brivge/MasterMenu_Brivge/li_Global Parts List'))

not_run: WebUI.waitForElementPresent(findTestObject('Page_GlobalPartsList/h3_Global Parts List'), 0)

not_run: WebUI.click(findTestObject('Page_GlobalPartsList/button_CreateModify_GlobalParts'))

not_run: WebUI.waitForElementPresent(findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/h3_Create OR Modify Global Parts'), 
    0)

not_run: WebUI.check(findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/input_CreateModifyGlobalParts_DwnloadUpload'))

not_run: WebUI.waitForElementPresent(findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/h6_Select Download Or Upload'), 
    0)

not_run: WebUI.click(findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/button_Download'))

not_run: WebUI.delay(3)

downloadedExcel = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

println(downloadedExcel)

not_run: CustomKeywords.'python.disableProtect_excel'(downloadedExcel)

for (def index; index <= dataFile.getRowNumbers(); index++) {
 
	CustomKeywords.'copyToExcel.exel2'("Muahahaha9", 7, 0, downloadedExcel,'Global Parts')
   
}

