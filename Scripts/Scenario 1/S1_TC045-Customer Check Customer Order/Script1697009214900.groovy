import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_CO Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_CO_MonitoringList/h3_CO Monitoring List'), 0)

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), orderNo)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC039_TC040/p_verifyContractNo_firstRow'), contractNo)

KeywordUtil.logInfo("Verified Contract No: $contractNo")

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC039_TC040/p_verifyStatus', [('contractNo') : contractNo]), 'Received')

KeywordUtil.logInfo('Verified Status: Received')

WebUI.click(findTestObject('Scenario 12/SC12_TC014/input_checkall'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_Download_CO'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC049/div_Download Customer Order by Excel.The operation was successful'), 
    0)

LatestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedCO, LatestPath, 1, [23, 24, 25, 26, 27, 28], [1, 2, 3, 5, 6, 7
        , 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18])

WebUI.click(findTestObject('Page_SO_MonitoringList/button_Dt_Detail', [('contractNo') : contractNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_ForecastDetail/input_SalesOrderNo'), 'value', orderNo, 0)

KeywordUtil.logInfo("Verified Order No: $orderNo")

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderType'), 
    'value', 'Regular', 0)

KeywordUtil.logInfo('Verified Order Type: Regular')

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC029/Page_CO Monitoring Detail - Brivge/input_Basic Info Detail_status'), 
    'value', 'Received', 0)

KeywordUtil.logInfo('Verified Status: Received')

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderFrequency'), 
    'value', 'Weekly', 0)

KeywordUtil.logInfo('Verified Order Frequency: Weekly')

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC045/input_Basic Info Detail_customerCode'), 'value', GlobalVariable.S1_BAF_CUS, 
    0)

KeywordUtil.logInfo('Verified Customer: $GlobalVariable.S1_BAF_CUS')

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC045/input_Basic Info Detail_contractNo'), 'value', contractNo, 
    0)

KeywordUtil.logInfo("Verified Contract No: $contractNo")

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC178/p_Parts No'), 0)

for (int rowl = 1; rowl <= 6; rowl++) {
    int coll = 1

    for (String col : columnname) {
        String valuecol = testDataCheck.getValue(col, rowl)

        def actualValue = WebUI.getText(findTestObject('Scenario 10/S10_TC177/p_partCheck', [('lrow') : rowl, ('lcol') : coll]))

        KeywordUtil.logInfo("Expected value: $valuecol ; Actual value: $actualValue")

        WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC177/p_partCheck', [('lrow') : rowl, ('lcol') : coll]), 
            valuecol)

        coll = (coll + 1)
    }
}

WebUI.closeBrowser()

