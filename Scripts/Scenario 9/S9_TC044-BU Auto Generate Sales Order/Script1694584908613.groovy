import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_SO Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringList/h3_SO Monitoring List'), 0)

WebUI.setText(findTestObject('Page_SO_MonitoringList/input_SO Monitoring List'), contractNo)

WebUI.verifyElementPresent(findTestObject('Page_SO_MonitoringList/div_Dt_ContractNo', [('contractNo') : contractNo]), 0)

salesOrderNo = WebUI.getText(findTestObject('Page_SO_MonitoringList/div_Dt_SalesOrderNo', [('contractNo') : contractNo]), 
    FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'copyToExcel.exel2'(salesOrderNo, 1, 0, 'Excel Files\\Scenario 9\\S9_TestCases_Data.xlsx', 'TC44-Autogen BU Sales Order')

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC014/p_verifySO', [('contractNo') : contractNo, ('orderType') : orderTypeRegular]), 
    salesOrderNo)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/input_checkall'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_download'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC014/div_Download Sales Order by Excel.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'('Excel Files\\Scenario 9\\Expected Data\\TC44\\S9_TC44_BUSalesOrder.xlsx', 
    downloadedFile, 1, [23, 24, 25], [1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20])

WebUI.verifyElementText(findTestObject('Page_SO_MonitoringList/td_Status_SOMonitoringList', [('orderNo') : salesOrderNo]), 
    'Received')

WebUI.click(findTestObject('Page_SO_MonitoringList/button_Dt_Detail', [('contractNo') : salesOrderNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringDetail/h3_SO Monitoring Detail'), 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_MonitoringDetail/input_Status'), 'value', 'Received', 0)

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header-Parts Monitoring detailDisplay Monitor_d3694d'), 
    0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/button_Parts Monitoring detail_pop down'))

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header_Parts No header list'), 0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/header_Parts No header list'))

for (def index : (1..datafile_outbound.getRowNumbers())) {
    def newFirmQty = datafile_outbound.getValue('Firm', index)

    //	if qty more than 3 digits, it will put symbol (,) for example 1000 becomes 1,000 to make it same as the system
    def formattedNewFirmQty = newFirmQty.replaceAll('(\\d)(?=(\\d{3})+(?!\\d))', '$1,')

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC050 TC053/partsDetail_Status', [('row') : index]), mapverify_partsdetail[
        index])

    WebUI.verifyElementText(findTestObject('Page_SO_MonitoringDetail/div_PartsMonitorDetail_OrderQty', [('row') : index]), 
        formattedNewFirmQty)
}

WebUI.scrollToElement(findTestObject('Page_SO_MonitoringDetail/td_last'), 0)

WebUI.closeBrowser()

