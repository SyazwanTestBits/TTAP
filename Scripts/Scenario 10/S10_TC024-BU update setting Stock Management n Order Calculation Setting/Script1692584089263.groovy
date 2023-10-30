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

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC024/button_Stock Management Tool'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC024/li_Stock Management  Order Calculation Setting'))

'combine contract no from TC17 TC21'
String contractSearch = (contractNoBUL2 + ' ') + contractNoBUL3

WebUI.click(findTestObject('Scenario 10/S10_TC024/button_search AND'))

WebUI.setText(findTestObject('Scenario 10/S10_TC024/input_order Stock Management  Order'), contractSearch)

WebUI.click(findTestObject('Scenario 10/S10_TC024/input_tick all'))

WebUI.click(findTestObject('Scenario 10/S10_TC024/svg_download'))

WebUI.click(findTestObject('Scenario 10/S10_TC024/li_Download Alarm Usage Pattern'))

WebUI.verifyElementText(findTestObject('Page_PaymentTerm/p_The operation was successful'), 'The operation was successful.')

latestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

int lastrow = 6

partsNoRowIndices = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(latestPath, 2, 7)

println(partsNoRowIndices)

for (int row = 1; row <= testData.getRowNumbers(); row++) {
    def datafilePartNo = testData.getValue('PartNo', row)

    def fileRowIndex = partsNoRowIndices[datafilePartNo]

    for (def pair : stockControlColumnName) {
        def columnName = pair.key

        def columnIndex = pair.value

        def dataValue = testData.getValue(columnName, row)

        CustomKeywords.'copyToExcel.exel3'(dataValue, fileRowIndex, columnIndex, latestPath, 'base')
    }
}

WebUI.click(findTestObject('Scenario 10/S10_TC024/button_upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC024/li_Upload Alarm Usage Pattern'), latestPath)

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

not_run: WebUI.delay(5)

not_run: WebUI.waitForElementPresent(findTestObject('Scenario 13/S13_TC002/Page_Brivge/div_Upload Portmaster.The operation was successful - Copy'), 
    0)

WebUI.closeBrowser()

