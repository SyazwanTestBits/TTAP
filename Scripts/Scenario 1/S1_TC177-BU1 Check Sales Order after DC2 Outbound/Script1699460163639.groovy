import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_LUQMAN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_BU1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/span_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_SO Monitoring List'))

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), SONumber)

WebUI.check(findTestObject('Scenario 12/SC12_TC014/input_checkall'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_download'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download by Excel'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC014/div_Download Sales Order by Excel.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

'Need to change expected file'
CustomKeywords.'verifyExcelData.verifyDynamicSort'('Excel Files\\Scenario 1\\Expected Data\\TC177\\Expected Sales Order.xlsx', 
    downloadedFile, 1, [23, 24, 25, 26, 27, 28], [1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17])

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC143/Page_SO Monitoring List - Brivge/div_Processing'), 'Processing')

WebUI.click(findTestObject('Scenario 1/S1_TC143/Page_SO Monitoring List - Brivge/button_Detail'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Scenario 1/S1_TC143/Page_SO Monitoring Detail - Brivge/h3_SO Monitoring Detail'), 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC143/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_status'), 
    'value', 'Processing', 0)

WebUI.click(findTestObject('Scenario 1/S1_TC143/Page_SO Monitoring Detail - Brivge/button_Parts Monitoring detail_step_2'))

WebUI.scrollToElement(findTestObject('Scenario 1/S1_TC143/Page_SO Monitoring Detail - Brivge/p_Copyright  2021 BriVge. All rights reserved'), 
    0)

WebUI.click(findTestObject('Page_PO_MonitoringDetail/p_Unit Parts No'))

int numberrowtd = findTestData('Scenario 1/S1_TC177-Parts Detail').getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    int coll = 1

    for (String colname : columnname) {
        String valuecol = findTestData('Scenario 1/S1_TC177-Parts Detail').getValue(colname, rowl)

        def actualValue = WebUI.getText(findTestObject('Scenario 1/S1_TC143/Page_SO Monitoring Detail - Brivge/div_s1001', 
                [('lrow') : rowl, ('lcol') : coll]))

        KeywordUtil.logInfo("Expected value: $valuecol ; Actual value: $actualValue")

        WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC143/Page_SO Monitoring Detail - Brivge/div_s1001', [('lrow') : rowl
                    , ('lcol') : coll]), valuecol)

        coll = (coll + 1)
    }
}

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

