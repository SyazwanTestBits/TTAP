import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/span_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/li_CO Monitoring List'))

WebUI.click(findTestObject('Scenario 12/SC12_TC022/p_forecastDetail', [('orderNo') : COid]))

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_ForecastDetail/input_SalesOrderNo'), 'value', COid, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderType'), 
    'value', 'Regular', 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_contractNo'), 
    'value', contractNo, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderFrequency'), 
    'value', 'Weekly', 0)

WebUI.scrollToElement(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/header_Shipping part list'), 0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/header_Parts No in shipping list'))

int numberrowtd = findTestData('Scenario 12/SC12_TC022-Customer Check Forecast CO').getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    int coll = 1

    for (String colname : columnname) {
        String valuecol = findTestData('Scenario 12/SC12_TC022-Customer Check Forecast CO').getValue(colname, rowl)

        def actualValue = WebUI.getText(findTestObject('Scenario 12/SC12_TC019/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]))
		
		KeywordUtil.logInfo("Expected value: $valuecol ; Actual value: $actualValue")

        WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC022/p_partForecast', [('lrow') : rowl, ('lcol') : coll]), 
            valuecol)

        coll = (coll + 1)
    }
}

WebUI.closeBrowser()

