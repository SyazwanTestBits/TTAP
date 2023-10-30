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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : usernameLogin
        , ('password') : passwordLogin, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : companyLogin], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 10/S10_TC185/div_Vendor Analysis'))

WebUI.waitForElementVisible(findTestObject('Scenario 10/S10_TC185/div_Supplier Statistics2'), 0)

WebUI.click(findTestObject('remote filter/button_Supplier Statistics_remote filter'))

WebUI.click(findTestObject('Scenario 10/S10_TC185/input__startMonth'))

WebUI.click(findTestObject('remote filter/calendar/div_filter-year', [('year') : fromYear]))

WebUI.click(findTestObject('remote filter/calendar/div_filter-month', [('month') : fromMonth]))

WebUI.click(findTestObject('remote filter/calendar/input_endMonth'))

WebUI.click(findTestObject('remote filter/calendar/div_filter-year', [('year') : toYear]))

WebUI.click(findTestObject('remote filter/calendar/div_filter-month', [('month') : toMonth]))

WebUI.click(findTestObject('remote filter/calendar/button_remote filter_Search'))

for (int i = 1; i <= testData.getRowNumbers(); i++) {
    WebUI.click(findTestObject('Scenario 10/S10_TC185/input_Purchase Amount, by Supplier, by Country'))

    WebUI.click(findTestObject('Scenario 10/S10_TC185/li_input_Purchase Amount, by Supplier, by Country', [('currency') : testData.getValue(
                    'currency', i)]))

    WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC185/div_table1-companyJP-YAZ-amount'), testData.getValue('JP-YAZ', 
            i))

    WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC185/div_table1-companyMY-ELA-amount'), testData.getValue('MY-ELA', 
            i))

    WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC185/div_Purchase Amount-CNTW-SUP'), testData.getValue('CNTW-SUP', 
            i))
}

not_run: WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC185/path_piechart-JP'), 0)

not_run: WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC185/path_piechart-MY'), 0)

not_run: WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC185/path_piechart-CN'), 0)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('Scenario 10/S10_TC185/h6_Completed Deliveries'), 0)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC185/div_COMPLETED DELIVERIES_JP-YAZ'), '100%')

backColorJP = WebUI.getCSSValue(findTestObject('Scenario 10/S10_TC185/div_COMPLETED DELIVERIES_JP-YAZ - background'), 'background-color')

WebUI.verifyMatch(backColorJP, 'rgba(26, 175, 93, 1)', false)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC185/div_COMPLETED DELIVERIES_ELA-MY'), '100%')

backColorMY = WebUI.getCSSValue(findTestObject('Scenario 10/S10_TC185/div_COMPLETED DELIVERIES_ELA-MY - background'), 'background-color')

WebUI.verifyMatch(backColorMY, 'rgba(26, 175, 93, 1)', false)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC185/div_COMPLETED DELIVERIES_CNTW'), '')

backColorCN = WebUI.getCSSValue(findTestObject('Scenario 10/S10_TC185/div_COMPLETED DELIVERIES_CNTW - Copy'), 'background-color')

WebUI.verifyMatch(backColorCN, 'rgba(129, 133, 131, 1)', false)

WebUI.takeScreenshot()

WebUI.closeBrowser()

