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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : password, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC037/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC037/Page_Brivge/li_Contract List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC037/Page_Brivge/input_Contract List'), contractNo)

WebUI.click(findTestObject('Scenario 13/S13_TC037/Page_Brivge/input_Check Box'))

WebUI.click(findTestObject('Scenario 13/S13_TC037/Page_Brivge/button_Download'))

WebUI.click(findTestObject('Scenario 13/S13_TC037/Page_Brivge/li_Download Contract'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC037/Page_Brivge/div_Download Contract Master By filters.The operation was successful'), 
    0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

latestContract = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

contractIndex = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(latestContract, 2, 7)

int contractRow = contractIndex[contractNo]

CustomKeywords.'copyToExcel.exel2'('MOD', contractRow, 0, latestContract, 'Contract Master')

CustomKeywords.'copyToExcel.exel2'('S13_TC037', contractRow, 1, latestContract, 'Contract Master')

CustomKeywords.'copyToExcel.exel2'('S13_TC037', contractRow, 1, latestContract, 'Contract Master')

String oldDescription = CustomKeywords.'util.compareTestData.getCellValue2'(latestContract, contractRow, 4, 0)

String newDescription = "$oldDescription TC37"

CustomKeywords.'copyToExcel.exel2'(newDescription, contractRow, 4, latestContract, 'Contract Master')

WebUI.click(findTestObject('Scenario 13/S13_TC037/button_upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 13/S13_TC037/li_Upload Contract'), latestContract)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('Scenario 13/S13_TC037/Page_Brivge/button_Download'))

WebUI.click(findTestObject('Scenario 13/S13_TC037/Page_Brivge/li_Download Contract Parts'))

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC037/Page_Brivge/div_Download Contract Parts Master By filters.The operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 13/S13_TC037/Page_Brivge/button_Download'))

WebUI.click(findTestObject('Scenario 13/S13_TC037/Page_Brivge/li_Download Contract With Type'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC037/Page_Brivge/div_Download Contract With Type.The operation was successful'), 
    0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.click(findTestObject('Scenario 13/S13_TC037/Page_Brivge/button_Edit'))

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC037/Page_Brivge/input__Contract No'), 'value', contractNo, 
    0)

WebUI.click(findTestObject('Scenario 13/S13_TC037/Page_Brivge/button_Save'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC037/Page_Brivge/div_Save Contract.The operation was successful'), 
    0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.closeBrowser()

