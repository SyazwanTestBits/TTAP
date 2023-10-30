import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
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
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_BU1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/span_Order'))

WebUI.click(findTestObject('Page_PO_MonitoringList/li_PO Monitoring List'))

def orderNoText = ''

for (String orderNo : orderNoList) {
    orderNoText += (orderNo + ' ')
}

orderNoText = orderNoText.trim()

println(orderNoText)

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), orderNoText)

WebUI.click(findTestObject('Scenario 1/S1_TC041/button_AND'))

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC041/p_verifyContractNo', [('row') : 1]), contractNoBU2)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC041/p_verifyStatus_PO', [('row') : 1]), 'Received')

KeywordUtil.logInfo("Verified Contract No: $contractNoBU2 ; Status: Received ")

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC041/p_verifyContractNo', [('row') : 2]), contractNoBU3)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC041/p_verifyStatus_PO', [('row') : 2]), 'Cancelled')

KeywordUtil.logInfo("Verified Contract No: $contractNoBU3 ; Status: Cancelled ")

BU2toBU1_PO = (orderNoList[0])

WebUI.click(findTestObject('Scenario 12/SC12_TC046/p_checkbox', [('SOid') : BU2toBU1_PO]))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_download'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC015/div_Download Purchase Order by Excel.The operation was successful'), 
    0)

LatestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedPO_BU2, LatestPath, 1, [23, 24, 25], [1, 2, 3, 5, 6, 7, 8, 9, 10
        , 11, 12, 13, 14, 15, 16, 17])

WebUI.click(findTestObject('Scenario 12/SC12_TC046/p_checkbox', [('SOid') : BU2toBU1_PO]))

BU3toBU1_PO = (orderNoList[1])

WebUI.click(findTestObject('Scenario 12/SC12_TC046/p_checkbox', [('SOid') : BU3toBU1_PO]))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_download'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC015/div_Download Purchase Order by Excel.The operation was successful'), 
    0)

LatestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedPO_BU3, LatestPath, 1, [23, 24, 25], [1, 2, 3, 5, 6, 7, 8, 9, 10
        , 11, 12, 13, 14, 15, 16, 17, 18])

WebUI.click(findTestObject('Scenario 12/SC12_TC046/p_checkbox', [('SOid') : BU3toBU1_PO]))

WebUI.callTestCase(findTestCase('Scenario 1/S1_TC043.1-Purchase Order BU2'), [('contractNoBU2') : findTestData('Scenario 1/S1_TC003-BU2 to BU1 Contract').getValue(
            'ContractNo', 1), ('contractNoBU3') : findTestData('Scenario 1/S1_TC006-BU3 to BU1 Contract').getValue('ContractNo', 
            1), ('orderNoList') : [findTestData('Scenario 1/S1_TC020-AutoGen SO PO number').getValue('BU1_PO', 1), findTestData(
                'Scenario 1/S1_TC020-AutoGen SO PO number').getValue('BU1_PO', 2)], ('planOut_date1') : findTestData('Scenario 1/S1_TC017_Inbound Date Change').getValue(
            'InboundDate_1', 1), ('planOut_date2') : findTestData('Scenario 1/S1_TC017_Inbound Date Change').getValue('InboundDate_2', 
            1), ('columnname') : ['PartsNo', 'UnitPartsNo', 'BackNo', 'SalesOrderNo', 'Supplier', 'Shipper', 'SPQ', 'OrderLot'
            , 'OrderQty', 'UnitPrice', 'Currency', 'Status', 'InboundedQty', 'Inbound Plan Qty', 'Inbound Plan Status', 'EstimatedQty']
        , ('testDataCheck') : findTestData('Scenario 1/S1_TC043-BU1 Check Purchase order BU2')], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Scenario 1/S1_TC043.2-Purchase Order BU3'), [('contractNoBU2') : findTestData('Scenario 1/S1_TC003-BU2 to BU1 Contract').getValue(
            'ContractNo', 1), ('contractNoBU3') : findTestData('Scenario 1/S1_TC006-BU3 to BU1 Contract').getValue('ContractNo', 
            1), ('orderNoList') : [findTestData('Scenario 1/S1_TC020-AutoGen SO PO number').getValue('BU1_PO', 1), findTestData(
                'Scenario 1/S1_TC020-AutoGen SO PO number').getValue('BU1_PO', 2)], ('planOut_date1') : findTestData('Scenario 1/S1_TC017_Inbound Date Change').getValue(
            'InboundDate_1', 1), ('planOut_date2') : findTestData('Scenario 1/S1_TC017_Inbound Date Change').getValue('InboundDate_2', 
            1), ('columnname') : ['PartsNo', 'UnitPartsNo', 'BackNo', 'SalesOrderNo', 'Supplier', 'Shipper', 'SPQ', 'OrderLot'
            , 'OrderQty', 'UnitPrice', 'Currency', 'Status', 'InboundedQty', 'Inbound Plan Qty_1', 'Inbound Plan Status_1'
            , 'Inbound Plan Qty_2', 'Inbound Plan Status_2'], ('testDataCheck') : findTestData('Scenario 1/S1_TC043-BU1 Check Purchase order BU3')], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

