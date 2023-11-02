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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/span_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/li_SO Monitoring List'))

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), orderNo)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC039_TC040/p_verifyContractNo_firstRow'), contractNo)

WebUI.click(findTestObject('Scenario 10/S10_TC024/input_tick all'))

WebUI.click(findTestObject('Scenario 1/S1_TC067/button_SO Monitoring list_download'))

WebUI.click(findTestObject('Scenario 1/S1_TC067/li_Download by Excel'))

WebUI.takeFullPageScreenshot()

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadCustOrderbyExcel_Success'), 0)

WebUI.click(findTestObject('Page_SO_MonitoringList/button_Dt_Detail', [('contractNo') : contractNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 1/S1_TC067/button_Parts Monitoring detail_step_2'))

WebUI.scrollToElement(findTestObject('Scenario 1/S1_TC067/div_Order Qty'), 0)

KeywordUtil.logInfo("Verified New Order Quantity: $expectedOrderQTY")

KeywordUtil.logInfo("Verified New Order Quantity (Expected): $expectedOrderQTY")

println(expectedOrderQTY)

actualOrderQTY = WebUI.getText(findTestObject('Scenario 1/S1_TC067/div_Order Qty (Amount)'))

KeywordUtil.logInfo("Verified New Order Quantity (Actual): $actualOrderQTY")

println(actualOrderQTY)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC067/div_Order Qty (Amount)'), expectedOrderQTY)

WebUI.closeBrowser()

