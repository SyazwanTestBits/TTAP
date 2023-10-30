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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.CUST_USERNAME_USERF
        , ('password') : GlobalVariable.CUST_PWD_USERF, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.COMPANY_CUSTOMER], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/span_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/li_CO Monitoring List'))

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/input_CO id'), COid)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/input_tick first row CO list'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/button_CO detail'))

'NEED TO CHECK'
WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC049 TC054/input_status CO_verify'), 'value', 'Processing', 
    0)

'Delay Status- NEED TO CHECK\r\n'
not_run: WebUI.verifyElementAttributeValue(findTestObject(null), '', '', 0)

WebUI.scrollToElement(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/header_Shipping part list'), 0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/header_Parts No in shipping list'))

int numberrowtd = findTestData('Scenario 13/S13_TC054').getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    int coll = 1

    for (String colname : columnname) {
        String valuecol = findTestData('Scenario 13/S13_TC054').getValue(colname, rowl)

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC049 TC054/p_part detail list-tc49', [('lrow') : rowl, ('lcol') : coll]), 
            valuecol)

        coll = (coll + 1)
    }
}

WebUI.closeBrowser()

