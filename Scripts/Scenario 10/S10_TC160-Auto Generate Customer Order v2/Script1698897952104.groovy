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

WebUI.click(findTestObject('Scenario 10/S10_TC160/button_Order'))

WebUI.click(findTestObject('Scenario 10/S10_TC160/li_CO Monitoring List'))

WebUI.setText(findTestObject('Scenario 10/S10_TC160/input_CO Monitoring List_Search'), contractNo)


for (int row = 1; row <= 2; row++) {
	orderNo1 = WebUI.getText(findTestObject('Page_SO_MonitoringList/td_SalesOrder', [('row') : row]))
	
	WebUI.click(findTestObject('Scenario 10/S10_TC144/button_SOid_detail', [('row') : row]))
	
	WebUI.click(findTestObject('Scenario 10/S10_TC177/p_Parts No'))

	firstRow = WebUI.getText(findTestObject('Scenario 10/S10_TC177/p_partCheck', [('lrow') : 1, ('lcol') : 1]), FailureHandling.STOP_ON_FAILURE)
	
	if (firstRow == testData.getValue('PartsNo', 2)) {
		CustomKeywords.'copyToExcel.exel'(orderNo1, 1, 1, filePath, fileName, Sheetname)
	} else {
		CustomKeywords.'copyToExcel.exel'(orderNo1, 2, 1, filePath, fileName, Sheetname)
	}
	
	WebUI.back()
	
	WebUI.setText(findTestObject('Scenario 10/S10_TC074/input_SO Monitoring List_Search'), contractNo)


}

//orderNo1 = WebUI.getText(findTestObject('Scenario 10/S10_TC160/orderNo1'))

//println(orderNo1)

//CustomKeywords.'copyToExcel.exel'(orderNo1, 1, 1, filePath, fileName, Sheetname)

//orderNo2 = WebUI.getText(findTestObject('Scenario 10/S10_TC160/orderNo2'))

//CustomKeywords.'copyToExcel.exel'(orderNo2, 2, 1, filePath, fileName, Sheetname)

//println(orderNo2)

WebUI.closeBrowser()

