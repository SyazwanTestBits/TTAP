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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_DC3], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Scenario 1/S1_TC110/Page_Download Outbound Prioritization - Brivge/li_Download Outbound Instruction'))

WebUI.setText(findTestObject('Scenario 1/S1_TC110/Page_Download Outbound Prioritization - Brivge/input_Download_salesOrderNo'), 
    orderNo)

WebUI.click(findTestObject('Scenario 1/S1_TC110/Page_Download Outbound Prioritization - Brivge/button_Download'))

WebUI.click(findTestObject('Scenario 1/S1_TC110/Page_Download Outbound Prioritization - Brivge/li_Download By Parts'))

WebUI.verifyElementPresent(findTestObject('Scenario 1/S1_TC110/Page_Download Outbound Prioritization - Brivge/div_Download Outbound Priority By Parts.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDataSpecific'(OutboundInstruction_byParts, downloadedFile, [4, 5, 6, 7], [2, 3, 4, 5
        , 9, 10, 11, 15, 16, 17, 18, 19, 20, 22, 23, 24, 25, 26])

WebUI.click(findTestObject('Scenario 1/S1_TC110/Page_Download Outbound Prioritization - Brivge/button_Download'))

WebUI.click(findTestObject('Scenario 1/S1_TC110/Page_Download Outbound Prioritization - Brivge/li_Download By Module'))

WebUI.verifyElementPresent(findTestObject('Scenario 1/S1_TC110/Page_Download Outbound Prioritization - Brivge/div_Download Outbound Priority By Module.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDataSpecific'(OutboundInstruction_byModule, downloadedFile, [4, 5, 6], [2, 6, 7, 8
        , 9, 11, 15, 16, 17, 18, 19, 20, 22, 23, 24, 25, 26])

WebUI.closeBrowser()

