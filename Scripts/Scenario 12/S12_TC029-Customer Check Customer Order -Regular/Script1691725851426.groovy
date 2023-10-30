import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/span_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/li_CO Monitoring List'))

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC029/p_verifyStatus', [('orderNo') : COid]), 'Confirmed')

WebUI.check(findTestObject('Scenario 12/SC12_TC012/input_Checkbox', [('orderType') : orderType, ('contractNo') : contractNo]))

WebUI.click(findTestObject('Page_CO_MonitoringList/button_Dt_DownloadOptions'))

WebUI.click(findTestObject('Page_CO_MonitoringList/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadCustOrderbyExcel_Success'), 0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedCOregular, downloadedFile, 1, [23, 24, 25, 26, 27, 28], [1, 2
        , 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15])

WebUI.click(findTestObject('Scenario 12/SC12_TC029/p_detail', [('orderNo') : COid]))

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_ForecastDetail/input_SalesOrderNo'), 'value', COid, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderType'), 
    'value', 'Regular', 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC029/Page_CO Monitoring Detail - Brivge/input_Basic Info Detail_status'), 
    'value', 'Confirmed', 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_contractNo'), 
    'value', contractNo, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderFrequency'), 
    'value', 'Weekly', 0)

WebUI.scrollToElement(findTestObject('Scenario 12/SC12_TC029/h6_Shipping Plan List'), 0)

WebUI.click(findTestObject('Scenario 12/SC12_TC029/p_Parts No sort'))

int numberrowtd = findTestData('Scenario 12/SC12_TC029-Customer Check CO').getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    int coll = 1

    for (String colname : columnname) {
        String valuecol = findTestData('Scenario 12/SC12_TC029-Customer Check CO').getValue(colname, rowl)

        def actualValue = WebUI.getText(findTestObject('Scenario 12/SC12_TC029/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]))

        KeywordUtil.logInfo("Expected value: $valuecol ; Actual value: $actualValue")

        WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC029/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]), 
            valuecol)

        coll = (coll + 1)
    }
}

WebUI.closeBrowser()

