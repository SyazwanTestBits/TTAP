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
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 10/S10_TC058/li_Request Modify Packing'))

WebUI.waitForElementVisible(findTestObject('Scenario 10/S10_TC058/h3_Request Modify Packing'), 0)

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

WebUI.click(findTestObject('Scenario 10/S10_TC058/button_part list-OK'))

WebUI.scrollToElement(findTestObject('Scenario 10/S10_TC058/h6_UPLOAD PART'), 0)

not_run: WebUI.click(findTestObject('Scenario 10/S10_TC058/svg_UPLOAD PART_lcbm-MuiSvgIcon-root'))

WebUI.click(findTestObject('Scenario 10/S10_TC058/button_UPLOAD PART_download'))

WebUI.click(findTestObject('Scenario 10/S10_TC058/li_DOWNLOAD PART FORM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

latestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

int startrow = 6

for (int index = 1; index <= testData.getRowNumbers(); index++) {
    for (def pair : columnname) {
        def columnName = pair.key

        def columnIndex = pair.value

        def dataString = testData.getValue(columnName, index)

        int rowExcel = startrow + index

        CustomKeywords.'copyToExcel.exel3'(dataString, rowExcel, columnIndex, latestPath, 'Unit Parts')
    }
}

WebUI.click(findTestObject('Scenario 10/S10_TC058/button_UPLOAD PART_upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC058/li_UPLOAD PART FORM'), latestPath)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC058/button_Submit'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC058/button_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.setText(findTestObject('Scenario 10/S10_TC058/input_Request List_search'), description)

requestList = WebUI.getText(findTestObject('Scenario 10/S10_TC058/RequestList_DR-first'))

CustomKeywords.'copyToExcel.exel4'(requestList, 1, 0, 'Excel Files/Scenario 10/S10_TestCases_Data.xlsx', 'TC59 AutoGen')

WebUI.closeBrowser()

