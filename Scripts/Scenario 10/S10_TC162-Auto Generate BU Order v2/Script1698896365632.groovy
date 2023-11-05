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

WebUI.click(findTestObject('Scenario 10/S10_TC162/li_SO Monitoring List'))

WebUI.setText(findTestObject('Scenario 10/S10_TC162/input_SO Monitoring List_Search'), contractNo)

for (int row = 1; row <= 2; row++) {
	saleOrder = WebUI.getText(findTestObject('Page_SO_MonitoringList/td_SalesOrder', [('row') : row]))
	
	WebUI.click(findTestObject('Scenario 10/S10_TC144/button_SOid_detail', [('row') : row]))
	
	WebUI.click(findTestObject('Scenario 10/S10_TC144/button_Parts Monitoring detail_step_2'))
	
	WebUI.scrollToElement(findTestObject('Scenario 10/S10_TC144/div_Parts No'), 0)
	
	WebUI.click(findTestObject('Scenario 10/S10_TC144/div_Parts No'))
	
	partFirstRow = WebUI.getText(findTestObject('Scenario 10/S10_TC144/div_PartNo_firstRow'))
	
	if (partFirstRow == testData.getValue('PartsNo', 2)) {
		CustomKeywords.'copyToExcel.exel'(saleOrder, 1, 1, filePath, fileName, Sheetname)
	} else {
		CustomKeywords.'copyToExcel.exel'(saleOrder, 2, 1, filePath, fileName, Sheetname)
	}
	
	WebUI.back()
	
	WebUI.setText(findTestObject('Scenario 10/S10_TC074/input_SO Monitoring List_Search'), contractNo)


}

//WebUI.closeBrowser()

