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

not_run: WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_DC], 
    FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

not_run: WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Inbound Monitoring List'))

not_run: WebUI.click(findTestObject('Page_InboundMonitorList/button_Create_InboundMonitorList'))

not_run: WebUI.click(findTestObject('Scenario 12/SC12_TC068/button_Download_step_1'))

not_run: def outboundNoText = ''

not_run: for (String outboundNo : outboundNoList) {
    outboundNoText += (outboundNo + ' ')
}

not_run: outboundNoText = outboundNoText.trim()

not_run: println(outboundNoText)

not_run: WebUI.click(findTestObject('Scenario 12/SC12_TC068/button_AND'))

not_run: WebUI.setText(findTestObject('Scenario 12/SC12_TC068/input_outboundNo'), outboundNoText)

not_run: WebUI.click(findTestObject('Scenario 12/SC12_TC068/input_checkAll'))

not_run: WebUI.click(findTestObject('Scenario 12/SC12_TC068/button_Download'))

not_run: WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/p_The operation was successful'), 
    'The operation was successful.')

downloadedForm = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('Scenario 12/S12_TC068.1-Write Inbound Details'), [('datafile') : datafile, ('fileColumns') : fileColumns
        , ('startRowFormMinusOne') : startRowFormMinusOne, ('downloadedFormPath') : downloadedForm, ('downloadedFormSheetname') : downloadedFormSheetName], 
    FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.scrollToElement(findTestObject('Scenario 12/SC12_TC068/button_Upload'), 0)

not_run: CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 12/SC12_TC068/button_Upload'), downloadedForm)

not_run: CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 12/SC12_TC068/button_Confirm'), 0)

not_run: WebUI.click(findTestObject('Scenario 12/SC12_TC068/button_CONFIRMpop'))

not_run: def outboundRefNoText = ''

not_run: for (String outboundRefNo : outboundRefNoList) {
    outboundRefNoText += (outboundRefNo + ' ')
}

not_run: outboundRefNoText = outboundRefNoText.trim()

not_run: println(outboundRefNoText)

not_run: WebUI.setText(findTestObject('Scenario 12/SC12_TC068/input_outboundNo'), outboundRefNoText)

not_run: WebUI.click(findTestObject('Scenario 12/SC12_TC068/button_AND'))

not_run: for (int i = 1; i <= 9; i++) {
    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC068/p_Completed', [('rowNo') : i]), 'Completed')
}

not_run: WebUI.closeBrowser()

