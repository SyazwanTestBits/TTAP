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

WebUI.click(findTestObject('Scenario 10/S10_TC075/button_filter setting'))

WebUI.click(findTestObject('Scenario 10/S10_TC075/input_filter-order type'))

WebUI.click(findTestObject('Scenario 10/S10_TC075/li_filter-order typeSpot'))

WebUI.click(findTestObject('Scenario 10/S10_TC075/button_filter Search'))

WebUI.setText(findTestObject('Page_ChangeCancelReqList/Page_ChgCancelReqDetail/input_SO Monitoring List_lcbm'), SOid)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/button_SO detail'))

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header-Parts Monitoring detailDisplay Monitor_d3694d'), 
    0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/button_Parts Monitoring detail_pop down'))

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header_Parts No header list'), 0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/header_Parts No header list'))

for (int row = 1; row <= testData.getRowNumbers(); row++) {
    //for (def part : columnQty) {
        //def columnKey = part.key

        //def columnIndex = part.value

       // def dataValue = testData.getValue(columnKey, row)

        //WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC050 TC053/p_part detail list-tc50 - Copy', [('lrow') : row, ('lcol') : columnIndex]), dataValue)
    //}
    
    List<String> headerRow = testData.getColumnNames()

    int numberOfColumns = headerRow.size()
	//int coll = 9
    
	for (int col1 = 1; col1 <= numberOfColumns; col1++) {
	//for (String col1 : columnname) {
        def dataValue = testData.getValue(col1, row)

        //int colTable = col1 + 10

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC050 TC053/p_part detail list-tc50 - Copy', [('lrow') : row
                    , ('lcol') : col1]), dataValue)
		
		//coll = (coll + 1)
    }
}

WebUI.closeBrowser()

