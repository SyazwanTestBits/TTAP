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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.ADMIN_USERNAME
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.ADMIN_COMPANY], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.navigateToUrl('https://st.brivge.com/tools')

WebUI.waitForElementPresent(findTestObject('Scenario 10/S10_TC077/h3_PNA Data Replication Tools'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC077/input_Upload_supplierIds'))

WebUI.click(findTestObject('Scenario 10/S10_TC077/li_Upload_supplierIds', [('supplierID') : supplierID]))

WebUI.click(findTestObject('Scenario 10/S10_TC077/input_Upload_suppContractIds'))

WebUI.setText(findTestObject('Scenario 10/S10_TC077/input_Upload_suppContractIds'), contractID)

WebUI.click(findTestObject('Scenario 10/S10_TC077/li_Upload_suppContractIds', [('contractID') : contractID]))

WebUI.click(findTestObject('Scenario 10/S10_TC077/button_Download'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

latestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

lastrow = CustomKeywords.'util.ExcelUtils.getLastRowNumber'(latestPath)

for (int i = 1; i <= testData.getRowNumbers(); i++) {
    int fileRowIndex = lastrow + i

    for (def pair : columnname) {
        def columnName = pair.key

        def columnIndex = pair.value

        def dataString = testData.getValue(columnName, i)

        CustomKeywords.'copyToExcel.exel3'(dataString, fileRowIndex, columnIndex, latestPath, 'PO Balance ')
    }
}

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC077/button_Upload'), latestPath)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.closeBrowser()

