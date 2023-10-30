import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_CO Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_CO_MonitoringList/h3_CO Monitoring List'), 0)

WebUI.setText(findTestObject('Scenario 12/SC12_TC012/input_CO Monitoring List'), ContractNo)

orderNo = WebUI.getText(findTestObject('Scenario 12/SC12_TC012/div_customerOrderNo', [('contractNo') : ContractNo, ('orderType') : OrderType_Regular]), 
    FailureHandling.STOP_ON_FAILURE)

absPath = CustomKeywords.'ManageFiles.getFileAbsolutePath'('Excel Files/Scenario 9/S9_TestCases_Data.xlsx')

CustomKeywords.'copyToExcel.exel2'(orderNo, 1, 0, absPath, 'TC42-CUS Download CO')

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC012/input_Checkbox2', [('contractNo') : ContractNo, ('orderType') : OrderType_Regular]), 
    0)

WebUI.verifyElementText(findTestObject('Scenario 9/SC9_TC042/p_verifyOrderNo'), orderNo)

WebUI.check(findTestObject('Scenario 12/SC12_TC012/input_Checkbox2', [('contractNo') : ContractNo, ('orderType') : OrderType_Regular]), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_CO_MonitoringList/button_Dt_DownloadOptions'))

WebUI.click(findTestObject('Page_CO_MonitoringList/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadCustOrderbyExcel_Success'), 0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'('Excel Files\\Scenario 9\\Expected Data\\TC42\\S9_TC42_CustomerOrder.xlsx', 
    downloadedFile, 1, [23, 24, 25], [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17])

WebUI.takeFullPageScreenshot()

WebUI.verifyElementText(findTestObject('Page_SO_MonitoringList/td_Status_SOMonitoringList', [('orderNo') : orderNo]), 'Submitted')

WebUI.click(findTestObject('Page_SO_MonitoringList/button_Dt_Detail', [('contractNo') : orderNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page_CO_MonitoringList/Page_CO Monitoring Detail - Brivge/h3_CO Monitoring Detail'), 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_MonitoringDetail/input_Status'), 'value', 'Submitted', 0)

WebUI.scrollToElement(findTestObject('HomePage_Brivge/div_Copyright  2021 BriVge. All rights reserved'), 0)

for (def index : (1..datafile_outbound.getRowNumbers())) {
    WebUI.verifyElementText(findTestObject('Page_CO_MonitoringList/Page_CO Monitoring Detail - Brivge/div_CO Monitoring Detail_PartsDetail_Status', 
            [('row') : index]), mapverify_partsdetail[index])
}

WebUI.scrollToElement(findTestObject('Page_SO_MonitoringDetail/td_last'), 0)

WebUI.closeBrowser()

