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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : password, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/span_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/li_CO Monitoring List'))

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/input_CO id'), COid)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC049/p_DT-delayStatus'), 'Normal')

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/input_tick first row CO list'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/button_CO detail'))

'NEED TO CHECK'
WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC049 TC054/input_status CO_verify'), 'value', 'Confirmed', 
    0)

WebUI.scrollToElement(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/header_Shipping part list'), 0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/header_Parts No in shipping list'))

int numberrowtd = findTestData('Scenario 13/S13_TC049').getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    int coll = 1

    for (String colname : columnname) {
        String valuecol = findTestData('Scenario 13/S13_TC049').getValue(colname, rowl)

        actualValue = WebUI.getText(findTestObject('Scenario 13/S13_TC049 TC054/p_part detail list-tc49', [('lrow') : rowl
                    , ('lcol') : coll]))

        KeywordUtil.logInfo((((((('In row:' + rowl) + ' column:') + colname) + ', Actual data:') + actualValue) + ' Expectation data:') + 
            valuecol)

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC049 TC054/p_part detail list-tc49', [('lrow') : rowl, ('lcol') : coll]), 
            valuecol)

        coll = (coll + 1)
    }
}

WebUI.closeBrowser()

