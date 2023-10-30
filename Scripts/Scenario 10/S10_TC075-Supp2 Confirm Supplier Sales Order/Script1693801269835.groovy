import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_SYAZWAN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_SUPPLIER2], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/span_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/li_SO Monitoring List'))

WebUI.setText(findTestObject('Page_ChangeCancelReqList/Page_ChgCancelReqDetail/input_SO Monitoring List_lcbm'), contractSupp2)

WebUI.click(findTestObject('Scenario 10/S10_TC075/button_filter setting'))

WebUI.click(findTestObject('Scenario 10/S10_TC075/input_filter-order type'))

WebUI.click(findTestObject('Scenario 10/S10_TC075/li_filter-order typeSpot'))

WebUI.click(findTestObject('Scenario 10/S10_TC075/button_filter Search'))

orderNo1 = WebUI.getText(findTestObject('Scenario 10/S10_TC050/OrderNo1'))

CustomKeywords.'copyToExcel.exel'(orderNo1, 1, 2, filepath, filename, sheetname)

int row = 1

WebUI.click(findTestObject('Scenario 10/S10_TC052/button_detail SO with row', [('row') : row]))

'NEED TO CHECK'
WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC050 TC053/input_SO status'), 'value', 'Received', 0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC051/button_SO-Confirm'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC051/button_CONFIRM'))

WebUI.verifyElementVisible(findTestObject('NotificationMsg_Brivge/p_The operation was successful'))

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.back()

WebUI.setText(findTestObject('Page_ChangeCancelReqList/Page_ChgCancelReqDetail/input_SO Monitoring List_lcbm'), contractSupp2)

not_run: WebUI.click(findTestObject('Scenario 10/S10_TC075/button_filter setting'))

not_run: WebUI.click(findTestObject('Scenario 10/S10_TC075/input_filter-order type'))

not_run: WebUI.click(findTestObject('Scenario 10/S10_TC075/li_filter-order typeSpot'))

not_run: WebUI.click(findTestObject('Scenario 10/S10_TC075/button_filter Search'))

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC052/div_dt-Status with row', [('row') : row]), 'Confirmed')

WebUI.closeBrowser()

