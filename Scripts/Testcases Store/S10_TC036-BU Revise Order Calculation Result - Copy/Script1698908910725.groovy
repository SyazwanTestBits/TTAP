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

WebUI.click(findTestObject('Scenario 10/S10_TC035/li_Order Calculation List'))

WebUI.waitForElementVisible(findTestObject('Scenario 10/S10_TC035/h3_Order Calculation List'), 0)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC035/h3_Order Calculation List'), 'Order Calculation List')

WebUI.setText(findTestObject('Scenario 10/S10_TC035/input_Search Order Calculation'), orderCalcNo)

WebUI.click(findTestObject('Scenario 10/S10_TC035/input_tick all'))

WebUI.click(findTestObject('Scenario 10/S10_TC035/button_download'))

WebUI.click(findTestObject('Scenario 10/S10_TC035/li_Download'))

WebUI.delay(2)

String latestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

println(latestPath)

absolutePath = CustomKeywords.'ManageFiles.getFileAbsolutePath'(path)

not_run: for (int row = 1; row <= testData.getRowNumbers(); row++) {
    def value = testData.getValue('Final Adjust', row)

    int intvalue = value.toLong()

    def rowExcel = testData.getValue('Row', row)

    int introwExcel = rowExcel.toInteger()

    def columnExcel = testData.getValue('Column', row)

    int intcolumnExcel = columnExcel.toInteger()

    def sheetExcel = testData.getValue('SheetNumber', row)

    int intsheetExcel = sheetExcel.toInteger()

    CustomKeywords.'copyToExcel.exelSheetNumber2'(value, introwExcel, intcolumnExcel, latestPath, intsheetExcel, 'int')

    not_run: CustomKeywords.'copyToExcel.exel3'('2323232', 14, 42, latestPath, '202312')
}

WebUI.click(findTestObject('Scenario 10/S10_TC035/button_upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC035/li_Upload'), absolutePath)

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC027/0_create project page/p_The operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/svg_close notification'))

WebUI.closeBrowser()

