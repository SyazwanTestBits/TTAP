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
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_CUST], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Navbar_Brivge/MasterMenu_Brivge/li_Request Remove Part'))

WebUI.waitForElementVisible(findTestObject('Scenario 10/S10_TC065/h3_Request Remove Part'), 0)

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/input__requestTo'))

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/li_Request To', [('company') : company]))

WebUI.setText(findTestObject('Scenario 13/S13_TC034/Page_Brivge/input__description'), description)

String searchPart = ''

for (int i = 1; i <= testData.getRowNumbers(); i++) {
    searchPart = ((searchPart + testData.getValue('Part No', i)) + ' ')
}

WebUI.click(findTestObject('Scenario 10/S10_TC058/button_SELECT PART'))

WebUI.click(findTestObject('Scenario 10/S10_TC058/button_AND'))

WebUI.setText(findTestObject('Scenario 10/S10_TC058/input_Parts List'), searchPart)

WebUI.click(findTestObject('Scenario 10/S10_TC058/input_part List-tick all'))

WebUI.click(findTestObject('Scenario 10/S10_TC061/button_part list OK new'))

for (int i = 1; i <= testData.getRowNumbers(); i++) {
    partNo = testData.getValue('Part No', i)

    WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC065/div_SelectPart-DT-partNo', [('row') : i]), partNo)
}

WebUI.click(findTestObject('Scenario 10/S10_TC065/button_Submit'))

WebUI.click(findTestObject('Scenario 10/S10_TC058/button_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.waitForElementVisible(findTestObject('Scenario 10/S10_TC065/h3_Request List'), 0)

WebUI.setText(findTestObject('Scenario 10/S10_TC065/input_Request List_search'), description)

reqID = WebUI.getText(findTestObject('Scenario 10/S10_TC065/div_request list-DT-partNo'))

CustomKeywords.'copyToExcel.exel4'(reqID, 1, 2, 'Excel Files/Scenario 10/S10_TestCases_Data.xlsx', 'TC065')

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC065/div_request list-DT-status'), 'Submitted')

WebUI.closeBrowser()

