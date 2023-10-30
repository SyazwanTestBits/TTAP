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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Outbound Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_OutboundMonitoringList/h3_Outbound Monitoring List'), 0)

WebUI.callTestCase(findTestCase('Scenario 9/S9_TC069.1-Supplier Outbound Details'), [('salesOrderNo') : findTestData('Scenario 9/S9_TC046-Autogen SUP Sales Order').getValue('OrderNo', 1)
        , ('testdata_upload_outbounddata') : findTestData('Scenario 9/S9_TC069-Supplier Outbound'), ('buyer') : 'PK-CUS-TTAP'
        , ('receiver') : 'PK-CUS-DC', ('outboundType') : 'Outbound', ('shippingMode') : 'Sea', ('columnNames') : [('OutboundNo') : 3, ('OutboundDate') : 4, ('BookingNo') : 6, ('OutboundQty') : 9, ('ShippingMode') : 10, ('ETD') : 14, ('ETA') : 15, ('ContainerNo') : 16, ('ContainerType') : 17, ('CommodityType') : 18, ('SealNo') : 19, ('ContainerM3') : 20, ('ContainerNetWeight') : 21, ('ContainerGrossWeight') : 22, ('OuterPackageNo') : 23, ('OuterPackageType') : 24, ('OPM3') : 25, ('OPNetWeight') : 26, ('OPGrossWeight') : 27, ('InnerPackageNo') : 28, ('InnerPackageType') : 29, ('IPM3') : 30, ('IPNetWeight') : 31, ('IPGrossWeight') : 32]
        , ('outboundDate') : '11/8/2023'], FailureHandling.STOP_ON_FAILURE)

List<String> outboundRefList = []

for (int rowIndex = 1; rowIndex <= 1; rowIndex++) {
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
    CustomKeywords.'copyToExcel.exel'(outboundNo, rowIndex, 1, 'Excel Files\\Scenario 9', 'S9_TestCases_Data.xlsx', 'TC69-OutboundNo')
}

WebUI.closeBrowser()

