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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_SYAZWAN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_SUPPLIER1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/span_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/li_SO Monitoring List'))

WebUI.setText(findTestObject('Page_ChangeCancelReqList/Page_ChgCancelReqDetail/input_SO Monitoring List_lcbm'), ContractID)

WebUI.click(findTestObject('Scenario 10/S10_TC075/button_filter setting'))

WebUI.click(findTestObject('Scenario 10/S10_TC075/input_filter-order type'))

WebUI.click(findTestObject('Scenario 10/S10_TC075/li_filter-order typeSpot'))

WebUI.click(findTestObject('Scenario 10/S10_TC075/button_filter Search'))

WebUI.click(findTestObject('Page_PO_MonitoringList/div_DtHeader_Order No'))

WebUI.click(findTestObject('Page_PO_MonitoringList/div_DtHeader_Order No'))

orderNo1 = WebUI.getText(findTestObject('Scenario 10/S10_TC050/OrderNo1'))

CustomKeywords.'copyToExcel.exel'(orderNo1, 1, 0, filepath, filename, sheetname)

WebUI.click(findTestObject('Scenario 10/S10_TC074/input_Tick All'))

not_run: WebUI.click(findTestObject('Scenario 10/S10_TC074/Page_SO Monitoring List - Brivge/button_Detail'))

not_run: int row = 1

not_run: boolean yr = WebUI.verifyElementVisible(findTestObject('Scenario 10/S10_TC052/div_dt-Status with row', [('row') : row]))

not_run: println(yr)

not_run: while (yr == true) {
    not_run: WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC052/div_dt-Status with row', [('row') : row]), 'Confirmed')

    not_run: row = (row + 1)

    not_run: yr = WebUI.verifyElementVisible(findTestObject('Scenario 10/S10_TC052/div_dt-Status with row', [('row') : row]))

    not_run: println(row)

    not_run: println(yr)
}

//WebUI.verifyElementVisible(findTestObject('Scenario 10/S10_TC052/div_dt-Sale Order', [('row') : row]),FailureHandling.OPTIONAL)
WebUI.closeBrowser()

