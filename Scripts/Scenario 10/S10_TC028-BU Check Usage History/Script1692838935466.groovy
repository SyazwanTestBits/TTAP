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

String searchName = (((partL3no1 + ' ') + partL3no2) + ' ') + contractNoL3

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_SYAZWAN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC024/button_Stock Management Tool'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC028/li_Usage History'))

WebUI.waitForElementPresent(findTestObject('Scenario 10/S10_TC028/h3_Usage History'), 0)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC028/h3_Usage History'), 'Usage History')

WebUI.click(findTestObject('Scenario 10/S10_TC028/button_AND'), FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Scenario 10/S10_TC028/input_Search Usage History'), bomNo)

WebUI.click(findTestObject('Scenario 10/S10_TC028/input_Usage History_usageStartDate'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/div_Production plan-yearMonth-year', [('year') : '2023']))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/div_Production plan-yearMonth-month', [('month') : 'Jun']))

WebUI.click(findTestObject('Scenario 10/S10_TC028/input_Usage History_usageEndDate'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/div_Production plan-yearMonth-year', [('year') : '2023']))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/div_Production plan-yearMonth-month', [('month') : 'Dec']))

WebUI.click(findTestObject('Scenario 10/S10_TC028/span_Apply Date'))

for (int row = 1; row <= noRowPart; row++) {
    WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC028/td_BuyerUsage', [('row') : row]), bomNo)
}

WebUI.click(findTestObject('Scenario 10/S10_TC028/input_tick all'))

WebUI.click(findTestObject('Scenario 10/S10_TC028/button_download'))

WebUI.click(findTestObject('Scenario 10/S10_TC028/li_Download'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC027/0_create project page/p_The operation was successful'), 
    0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

latestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

int startrow = 2

for (int row = 1; row <= testData.getRowNumbers(); row++) {
    for (def pair : headerlist) {
        def columnName = pair.key

        def columnIndex = pair.value

        def dataValue = testData.getValue(columnName, row)

        int rowinexcel = startrow + row

        String excelValue = CustomKeywords.'util.compareTestData.getValueExcelFiles'(latestPath, rowinexcel, columnIndex, 
            0)

        WebUI.verifyMatch(excelValue, dataValue, false)
    }
}

WebUI.closeBrowser()

