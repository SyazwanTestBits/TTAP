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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_DC1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Inbound Monitoring List'))

WebUI.click(findTestObject('Page_InboundMonitorList/button_Create_InboundMonitorList'))

WebUI.click(findTestObject('Scenario 12/SC12_TC068/button_Download_step_1'))

def outboundNoText = ''

for (String outboundNo : outboundNoList) {
    outboundNoText += (outboundNo + ' ')
}

outboundNoText = outboundNoText.trim()

println(outboundNoText)

WebUI.setText(findTestObject('Scenario 12/SC12_TC068/input_outboundNo'), outboundNoText)

WebUI.click(findTestObject('Scenario 12/SC12_TC068/button_AND'))

WebUI.click(findTestObject('Scenario 12/SC12_TC068/input_checkAll'))

WebUI.click(findTestObject('Scenario 12/SC12_TC068/button_Download'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC176/div_Download Inbound Plan For Upload.The operation was successful'), 
    0)

WebUI.takeFullPageScreenshot()

downloadedForm = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_Cmn2-Write Info into Form Excel'), [('datafile') : datafile
        , ('fileColumns') : fileColumns, ('startRowFormMinusOne') : startRowFormMinusOne, ('downloadedFormPath') : downloadedForm
        , ('downloadedFormSheetname') : downloadedFormSheetName], FailureHandling.STOP_ON_FAILURE)

WebUI.scrollToElement(findTestObject('Scenario 12/SC12_TC068/button_Upload'), 0)

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 12/SC12_TC068/button_Upload'), downloadedForm)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 12/SC12_TC068/button_Confirm'), 0)

WebUI.click(findTestObject('Scenario 12/SC12_TC068/button_CONFIRMpop'))

WebUI.takeFullPageScreenshot()

def outboundRefNoText = ''

for (String outboundRefNo : outboundRefNoList) {
    outboundRefNoText += (outboundRefNo + ' ')
}

outboundRefNoText = outboundRefNoText.trim()

println(outboundRefNoText)

WebUI.setText(findTestObject('Scenario 12/SC12_TC068/input_outboundNo'), outboundRefNoText)

WebUI.click(findTestObject('Scenario 12/SC12_TC068/button_AND'))

for (int i = 1; i <= 7; i++) {
    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC068/p_Completed', [('rowNo') : i]), 'Completed')
}

WebUI.closeBrowser()

