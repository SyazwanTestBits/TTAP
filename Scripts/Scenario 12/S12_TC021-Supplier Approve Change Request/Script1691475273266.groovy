import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Scenario 12/SC12_TC021/li_ChangeCancel Request List'))

'Verify Forecast Change with Status = New\r\n'
WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC019/p_verifyForecastChange', [('requestNo') : requestNo]), 'New')

WebUI.click(findTestObject('Scenario 12/SC12_TC019/p_detailButton', [('requestNo') : requestNo]))

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 12/SC12_TC021/Page_ChangeCancel Request Detail - Brivge/p_Parts No'), 
    0)

int numberrowtd = findTestData('Scenario 12/SC12_TC021-Supplier Forecast Change Info').getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    int coll = 1

    for (String col : columnname) {
        String valuecol = findTestData('Scenario 12/SC12_TC021-Supplier Forecast Change Info').getValue(col, rowl)

        def actualValue = WebUI.getText(findTestObject('Scenario 12/SC12_TC019/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]))

        KeywordUtil.logInfo("Expected value: $valuecol ; Actual value: $actualValue")

        WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC019/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]), 
            valuecol)

        coll = (coll + 1)
    }
}

//
WebUI.click(findTestObject('Scenario 12/SC12_TC021/span_Approve'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC021/div_Approve.The operation was successful'), 0)

WebUI.click(findTestObject('Scenario 12/SC12_TC021/p_ChangeCancel Request List'))

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC021/p_verifyApprove', [('requestNo') : requestNo]), 'Approved')

WebUI.closeBrowser()

