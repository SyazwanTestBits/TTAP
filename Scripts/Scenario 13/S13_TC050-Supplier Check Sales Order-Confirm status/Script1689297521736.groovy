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

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/span_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/li_SO Monitoring List'))

WebUI.setText(findTestObject('Page_ChangeCancelReqList/Page_ChgCancelReqDetail/input_SO Monitoring List_lcbm'), SOid)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/input-first row in SO listing'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC050 TC053/button_SO delay status'), 'Normal')

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/button_SO detail'))

'NEED TO CHECK'
WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC050 TC053/input_SO status'), 'value', 'Confirmed', 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC050 TC053/input_Total amount'), 'value', '717.5', 0)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC050 TC053/p_total amaount currecy'), 'CNY')

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header-Parts Monitoring detailDisplay Monitor_d3694d'), 
    0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/button_Parts Monitoring detail_pop down'))

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header_Parts No header list'), 0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/header_Parts No header list'))

int numberrowtd = findTestData('Scenario 13/S13_TC050').getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    int coll = 1

    for (String col : columnname) {
        String valuecol = findTestData('Scenario 13/S13_TC050').getValue(col, rowl)
		
		actualValue = WebUI.getText(findTestObject('Scenario 13/S13_TC050 TC053/p_part detail list-tc50 - Copy', [('lrow') : rowl, ('lcol') : coll]))

		KeywordUtil.logInfo((((((('In row:' + rowl) + ' column:') + col) + ', Actual data:') + actualValue) + ' Expectation data:') +valuecol)

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC050 TC053/p_part detail list-tc50 - Copy', [('lrow') : rowl
                    , ('lcol') : coll]), valuecol)

        coll = (coll + 1)
    }
    
}

