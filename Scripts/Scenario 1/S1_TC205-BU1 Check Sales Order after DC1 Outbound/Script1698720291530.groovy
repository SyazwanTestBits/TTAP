import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_BU1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/span_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/li_SO Monitoring List'))

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), regularOrderNo)

WebUI.check(findTestObject('Scenario 12/SC12_TC014/input_checkall'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_download'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download by Excel'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC014/div_Download Sales Order by Excel.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'('Excel Files\\Scenario 1\\Expected Data\\TC205\\Expected Sales Order Regular.xlsx', 
    downloadedFile, 1, [23, 24, 25, 26, 27, 28], [1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17])

WebUI.verifyElementText(findTestObject('Page_SO_MonitoringList/td_Status_SOMonitoringList', [('orderNo') : regularOrderNo]), 
    'Processing')

WebUI.click(findTestObject('Page_SO_MonitoringList/button_Dt_Detail', [('contractNo') : regularOrderNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringDetail/h3_SO Monitoring Detail'), 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_MonitoringDetail/input_Status'), 'value', 'Processing', 0)

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header-Parts Monitoring detailDisplay Monitor_d3694d'), 
    0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/button_Parts Monitoring detail_pop down'))

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header_Parts No header list'), 0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/header_Parts No header list'))

int numberrowtd = findTestData('Scenario 1/S1_TC205.1-BU1 SO -Regular').getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    int coll = 1

    for (String colname : columnname_regular) {
        String valuecol = findTestData('Scenario 1/S1_TC205.1-BU1 SO -Regular').getValue(colname, rowl)

        def actualValue = WebUI.getText(findTestObject('Scenario 12/SC12_TC029/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]))

        KeywordUtil.logInfo("Expected value: $valuecol ; Actual value: $actualValue")

        WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC029/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]), 
            valuecol)

        coll = (coll + 1)
    }
}

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 1/S1_TC205/Page_SO Monitoring Detail - Brivge/p_SO Monitoring List'), 
    0)

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), spotOrderNo)

WebUI.check(findTestObject('Scenario 12/SC12_TC014/input_checkall'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_download'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download by Excel'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC014/div_Download Sales Order by Excel.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'('Excel Files\\Scenario 1\\Expected Data\\TC205\\Expected Sales Order Spot.xlsx', 
    downloadedFile, 1, [23, 24, 25], [1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19])

WebUI.verifyElementText(findTestObject('Page_SO_MonitoringList/td_Status_SOMonitoringList', [('orderNo') : spotOrderNo]), 
    'Processing')

WebUI.click(findTestObject('Page_SO_MonitoringList/button_Dt_Detail', [('contractNo') : spotOrderNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringDetail/h3_SO Monitoring Detail'), 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_MonitoringDetail/input_Status'), 'value', 'Completed', 0)

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header-Parts Monitoring detailDisplay Monitor_d3694d'), 
    0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/button_Parts Monitoring detail_pop down'))

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header_Parts No header list'), 0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/header_Parts No header list'))

int numberrowtd2 = findTestData('Scenario 1/S1_TC205.2-BU1 SO -Spot').getRowNumbers()

for (int rowl2 = 1; rowl2 <= numberrowtd2; rowl2++) {
    int coll = 1

    for (String colname : columnname_spot) {
        String valuecol = findTestData('Scenario 1/S1_TC205.2-BU1 SO -Spot').getValue(colname, rowl2)

        def actualValue = WebUI.getText(findTestObject('Scenario 12/SC12_TC029/p_partsDetail', [('lrow') : rowl2, ('lcol') : coll]))

        KeywordUtil.logInfo("Expected value: $valuecol ; Actual value: $actualValue")

        WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC029/p_partsDetail', [('lrow') : rowl2, ('lcol') : coll]), 
            valuecol)

        coll = (coll + 1)
    }
}

WebUI.closeBrowser()

