import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/span_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_CO Monitoring List'))

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), cusOrderNo)

WebUI.check(findTestObject('Scenario 12/SC12_TC014/input_checkall'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_Download_CO'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download by Excel'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC014/div_Download Sales Order by Excel.The operation was successful'), 
    0)

CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.verifyElementText(findTestObject('Page_SO_MonitoringList/td_Status_SOMonitoringList', [('orderNo') : cusOrderNo]), 
    'Processing')

WebUI.click(findTestObject('Page_SO_MonitoringList/button_Dt_Detail', [('contractNo') : cusOrderNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page_CO_MonitoringList/Page_CO Monitoring Detail - Brivge/h3_CO Monitoring Detail'), 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_MonitoringDetail/input_Status'), 'value', 'Processing', 0)

WebUI.scrollToElement(findTestObject('HomePage_Brivge/div_Copyright  2021 BriVge. All rights reserved'), 0)

WebUI.click(findTestObject('Page_CO_MonitoringList/Page_CO Monitoring Detail - Brivge/p_Customer Parts No'))

for (def index : (1..datafile_outbound.getRowNumbers())) {
    WebUI.verifyElementText(findTestObject('Page_CO_MonitoringList/Page_CO Monitoring Detail - Brivge/div_CO Monitoring Detail_PartsDetail_Status', 
            [('row') : index]), mapverify_partsdetail[index])

    def newFirmQty = datafile_outbound.getValue('newFirmQty', index)

    //	if qty more than 3 digits, it will put symbol (,) for example 1000 becomes 1,000 to make it same as the system
    def formattedNewFirmQty = newFirmQty.replaceAll('(\\d)(?=(\\d{3})+(?!\\d))', '$1,')

    WebUI.verifyElementText(findTestObject('Page_CO_MonitoringList/Page_CO Monitoring Detail - Brivge/div_CO Monitoring Detail_OrderQty', 
            [('row') : index]), formattedNewFirmQty)
}

WebUI.scrollToElement(findTestObject('Page_SO_MonitoringDetail/td_last'), 0)

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

