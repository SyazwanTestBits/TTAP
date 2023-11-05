import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testdata.TestData as TestData
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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : password, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Outbound Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_OutboundMonitoringList/h3_Outbound Monitoring List'), 0)

not_run: WebUI.callTestCase(findTestCase('Scenario 12/S12_TC044.1-Supplier Outbound -Regular'), [('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('company') : GlobalVariable.BAF_COMPANY_SUP, ('salesOrderNo') : findTestData(
            'Scenario 12/SC12_TC016-Supplier SO').getValue('RegularSalesOrderNo', 1), ('testdata_upload_outbounddata') : findTestData(
            'Scenario 12/SC12_TC044.1-Supplier Outbound -Regular'), ('testdata_contractpartsinfo') : findTestData('Scenario 12/SC12_TC004-Contract Parts Info')
        , ('buyer') : 'VN-TTVN', ('receiver') : 'VN-AKIRA', ('outboundType') : 'Outbound', ('shippingMode') : 'Sea', ('columnNames') : [
            'OutboundNo', 'OutboundDate', 'BookingNo', 'OutboundQty', 'ETD', 'ETA', 'ContainerNo', 'ContainerType', 'CommodityType'
            , 'SealNo', 'C_M3', 'C_NetWeight', 'C_GrossWeight', 'OuterPackageNo', 'OuterPackageType', 'OP_M3', 'OP_NetWeight'
            , 'OP_GrossWeight', 'InnerPackageNo', 'InnerPackageType', 'IP_M3', 'IP_NetWeight', 'IP_GrossWeight'], ('outboundDate') : '11/8/2023'], 
    FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Scenario 12/S12_TC044.2-Supplier Outbound -Spot'), [('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('company') : GlobalVariable.BAF_COMPANY_SUP, ('salesOrderNo') : findTestData(
            'Scenario 12/SC12_TC016-Supplier SO').getValue('SpotSalesOrderNo', 1), ('testdata_upload_outbounddata') : findTestData(
            'Scenario 12/SC12_TC044.2-Supplier Outbound -Spot'), ('testdata_contractpartsinfo') : findTestData('Scenario 12/SC12_TC004-Contract Parts Info')
        , ('buyer') : 'VN-TTVN', ('receiver') : 'VN-TTVN', ('outboundType') : 'Outbound', ('shippingMode') : 'Sea', ('columnNames') : [
            'No', 'Shipper', 'OutboundNo', 'OutboundDate', 'OutboundTime', 'BookingNo', 'PartsNo', 'UOM', 'OutboundQty', 'ShippingMode'
            , 'OutboundType', 'Receiver', 'Buyer', 'ETD', 'ETA', 'ContainerNo', 'ContainerType', 'CommodityType', 'SealNo'
            , 'C_M3', 'C_NetWeight', 'C_GrossWeight', 'OuterPackageNo', 'OuterPackageType', 'OP_M3', 'OP_NetWeight', 'OP_GrossWeight'
            , 'InnerPackageNo', 'InnerPackageType', 'IP_M3', 'IP_NetWeight', 'IP_GrossWeight', 'SalesOrderNo', 'Seller', 'SellerPartsNo'
            , 'SellerPartsDescription', 'SellerBackNo', 'ColorCode', 'SRBQ', 'RemainingQtyAvailable'], ('outboundDate') : '11/8/2023'], 
    FailureHandling.STOP_ON_FAILURE)

List<String> outboundRefList = []

for (int rowIndex = 1; rowIndex <= 4; rowIndex++) {
    String outboundRefNo = testData.getValue('OutboundRefNo', rowIndex)

    outboundRefList.add(outboundRefNo)

    TestObject outboundRefTestObject = findTestObject('Scenario 12/SC12_TC044/p_verifyOutboundCompleted', [('outboundRefSys') : outboundRefNo])

    // Verify the status is "Completed"
    WebUI.verifyElementText(outboundRefTestObject, 'Completed')

    KeywordUtil.logInfo("Verified Outbound Reference: $outboundRefNo with status: Completed")

    // Get the outboundNo using WebUI.getText
    TestObject outboundNoTestObject = findTestObject('Scenario 12/SC12_TC044/p_getOutboundNo', [('outboundRefSys') : outboundRefNo])

    String outboundNo = WebUI.getText(outboundNoTestObject)

    KeywordUtil.logInfo("Retrieved Outbound No: $outboundNo")

    // Copy the outboundNo to Excel using the same loop
    CustomKeywords.'copyToExcel.exel'(outboundNo, rowIndex, 2, 'Excel Files\\Scenario 12', 'S12_TestCases_Data.xlsx', 'TC44-Outbound No')
}

WebUI.closeBrowser()

