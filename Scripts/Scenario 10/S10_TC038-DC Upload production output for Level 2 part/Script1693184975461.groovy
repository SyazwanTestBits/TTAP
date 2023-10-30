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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_SYAZWAN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_DC], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Inbound Monitoring List'))

WebUI.click(findTestObject('Scenario 10/S10_TC038/button_download'))

WebUI.click(findTestObject('Scenario 10/S10_TC038/li_Download Inbound Form (Manual)'))

WebUI.click(findTestObject('Scenario 10/S10_TC038/button_AND'))

String searchL2 = ''

for (int row = 1; row <= testData.getRowNumbers(); row++) {
    searchL2 = ((searchL2 + testData.getValue('Parts No.', row)) + ' ')
}

WebUI.setText(findTestObject('Scenario 10/S10_TC038/input_search l2 inbound form manual'), searchL2)

WebUI.click(findTestObject('Scenario 10/S10_TC038/input_tick all'))

WebUI.click(findTestObject('Scenario 10/S10_TC038/span_Download'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

proplanpath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

int startrow = 3

for (int row = 1; row <= testData.getRowNumbers(); row++) {
    for (def pair : column) {
        def columnName = pair.key

        def columnIndex = pair.value

        def dataValue = testData.getValue(columnName, row)

        int rowinexcel = startrow + row

        CustomKeywords.'copyToExcel.exel2'(dataValue, rowinexcel, columnIndex, proplanpath, 'Inbound Result(Manual)')
    }
}

WebUI.click(findTestObject('Scenario 10/S10_TC038/button_upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC038/li_Upload Inbound Result(Manual)'), proplanpath)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.closeBrowser()

