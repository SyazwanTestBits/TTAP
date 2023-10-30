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

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/button_Master'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/li_Contract List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/input_Search Contract List'), contractNo)

contractNo = WebUI.getText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/p_first row contract list'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/button_view contract list'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/h3_header page view and modify'), 'View Contract')

WebUI.scrollToElement(findTestObject('Scenario 10/S10_TC008/View Contract/table/h_part list information'), 0)

for (int i = 1; i <= testData.getRowNumbers(); i++) {
    removePart = testData.getValue('Part No', i)

    'VERIFY INACTIVE IN ACTIVE FLAG'
    WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC008/View Contract/table/p_Dt_contents inactive', [('partNo') : removePart]), 
        'Inactive')
}

WebUI.closeBrowser()

