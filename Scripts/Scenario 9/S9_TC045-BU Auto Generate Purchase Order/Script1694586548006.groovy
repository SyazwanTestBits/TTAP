import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Page_PO_MonitoringList/li_PO Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_PO_MonitoringList/h3_PO Monitoring List'), 0)

WebUI.setText(findTestObject('Page_PO_MonitoringList/input_PO Monitoring List'), contractNo)

WebUI.verifyElementPresent(findTestObject('Page_SO_MonitoringList/div_Dt_ContractNo', [('contractNo') : contractNo]), 0)

purchaseOrderNo = WebUI.getText(findTestObject('Page_PO_MonitoringList/div_Dt_PurchaseOrderNo', [('contractNo') : contractNo]), 
    FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'copyToExcel.exel2'(purchaseOrderNo, 1, 0, 'Excel Files\\Scenario 9\\S9_TestCases_Data.xlsx', 'TC45-Autogen BU PO')

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC015/p_verifyPO2', [('contractNo') : contractNo, ('orderType') : orderTypeRegular]), 
    purchaseOrderNo)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/input_checkall'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_download'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC015/div_Download Purchase Order by Excel.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'('Excel Files\\Scenario 9\\Expected Data\\TC45\\S9_TC45_BUPurchaseOrder.xlsx', 
    downloadedFile, 1, [23, 24, 25], [1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19])

WebUI.verifyElementText(findTestObject('Page_SO_MonitoringList/td_Status_SOMonitoringList', [('orderNo') : purchaseOrderNo]), 
    'Received')

WebUI.click(findTestObject('Page_SO_MonitoringList/button_Dt_Detail', [('contractNo') : purchaseOrderNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page_PO_MonitoringDetail/h3_PO Monitoring Detail'), 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_MonitoringDetail/input_Status'), 'value', 'Received', 0)

WebUI.scrollToElement(findTestObject('HomePage_Brivge/div_Copyright  2021 BriVge. All rights reserved'), 0)

WebUI.click(findTestObject('Page_PO_MonitoringDetail/p_Unit Parts No'))

for (def index : (1..datafile_outbound.getRowNumbers())) {
    def newFirmQty = datafile_outbound.getValue('Firm', index)

    def formattedNewFirmQty = newFirmQty.replaceAll('(\\d)(?=(\\d{3})+(?!\\d))', '$1,')

    WebUI.verifyElementText(findTestObject('Page_PO_MonitoringDetail/div_partsDetail_PO_Status', [('row') : index]), mapverify_partsdetail[
        index])

    WebUI.verifyElementText(findTestObject('Page_PO_MonitoringDetail/p_OrderQty', [('row') : index]), formattedNewFirmQty)
}

WebUI.scrollToElement(findTestObject('Page_SO_MonitoringDetail/td_last'), 0)

WebUI.closeBrowser()

