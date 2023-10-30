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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/li_Request Add New Part'))

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/input__requestTo'))

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/li_Request To', [('company') : company]))

WebUI.setText(findTestObject('Scenario 13/S13_TC034/Page_Brivge/input__description'), description)

WebUI.click(findTestObject('Scenario 10/S10_TC005/button_Download'))

WebUI.click(findTestObject('Scenario 10/S10_TC005/li_Download'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC005/p_The operation was successful'), 0)

downloadedExcel = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 9/S9_Cmn1-Write Info into Form Excel'), [('datafile') : datafile
        , ('fileColumns') : fileColumns, ('startRowFormMinusOne') : startRowFormMinusOne, ('downloadedFormPath') : downloadedExcel
        , ('downloadedFormSheetname') : downloadedFormSheetName], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/button_Upload Part'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Object Repository/Scenario 13/S13_TC034/Page_Brivge/li_Upload Part From'), 
    downloadedExcel)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC034/Page_Brivge/div_The Operation was Successful'), 0)

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/button_Save'))

WebUI.delay(1)

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/button_Submit'))

WebUI.delay(1)

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/button_Confirm'))

WebUI.delay(1)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC034/Page_Brivge/div_Submit_The Operation was Succesful'), 0)

WebUI.closeBrowser()

