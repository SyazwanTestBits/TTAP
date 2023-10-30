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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : url, ('username') : username, ('password') : password
        , ('verificationCode') : verificationCode, ('company') : company], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 10/S10_TC050/button_Order'))

WebUI.click(findTestObject('Scenario 10/S10_TC050/li_SO Monitoring List'))

WebUI.setText(findTestObject('Scenario 10/S10_TC050/input_SO Monitoring List_Search'), contractNo)

orderNo1 = WebUI.getText(findTestObject('Scenario 10/S10_TC050/OrderNo1'))

not_run: println(orderNo1)

not_run: orderNo2 = WebUI.getText(findTestObject('Scenario 10/S10_TC050/OrderNo2'))

not_run: println(orderNo2)

not_run: orderNo3 = WebUI.getText(findTestObject('Scenario 10/S10_TC050/OrderNo3'))

not_run: println(orderNo2)

CustomKeywords.'copyToExcel.exel'(orderNo1, 1, 0, filepath, filename, sheetname)

not_run: CustomKeywords.'copyToExcel.exel'(orderNo1, 1, 2, filepath, filename, sheetname)

not_run: CustomKeywords.'copyToExcel.exel'(orderNo1, 1, 2, filepath, filename, sheetname)

not_run: WebUI.setText(findTestObject('Scenario 10/S10_TC050/input_SO Monitoring List_Search'), orderNo1)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC050/Status1'), expectedStatus)

not_run: WebUI.setText(findTestObject('Scenario 10/S10_TC050/input_SO Monitoring List_Search'), orderNo2)

not_run: WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC050/Status1'), expectedStatus)

not_run: WebUI.setText(findTestObject('Scenario 10/S10_TC050/input_SO Monitoring List_Search'), orderNo3)

not_run: WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC050/Status1'), expectedStatus)

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/button_SO detail'))

WebUI.closeBrowser()

