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

WebUI.click(findTestObject('Scenario 10/S10_TC074/button_Order'))

WebUI.click(findTestObject('Scenario 10/S10_TC074/li_SO Monitoring List'))

WebUI.setText(findTestObject('Scenario 10/S10_TC074/input_SO Monitoring List_Search'), orderNo)

WebUI.click(findTestObject('Scenario 10/S10_TC073/S10_73/button_remote filter'))

WebUI.click(findTestObject('Scenario 10/S10_TC073/S10_73/input_remote_orderTypeList'))

WebUI.click(findTestObject('Scenario 10/S10_TC073/S10_73/li_remote_ordertype_Spot'))

WebUI.click(findTestObject('Scenario 10/S10_TC073/S10_73/button_Search'))

orderNo1 = WebUI.getText(findTestObject('Scenario 10/S10_TC050/OrderNo1'))

CustomKeywords.'copyToExcel.exel'(orderNo1, 1, 2, filepath, filename, sheetname)

WebUI.click(findTestObject('Scenario 10/S10_TC074/input_Tick All'))

WebUI.click(findTestObject('Scenario 10/S10_TC074/Page_SO Monitoring List - Brivge/button_Detail'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC050 TC053/p_total amaount currecy'), 'MYR')

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header-Parts Monitoring detailDisplay Monitor_d3694d'), 
    0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/button_Parts Monitoring detail_pop down'))

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header_Parts No header list'), 0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/header_Parts No header list'))

WebUI.click(findTestObject('Scenario 10/S10_TC051/button_download part monitor'))

WebUI.click(findTestObject('HomePage_Brivge/li_Download DR'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.delay(2)

println('HW')

WebUI.click(findTestObject('Scenario 10/S10_TC051/button_download part monitor'))

WebUI.click(findTestObject('Scenario 10/S10_TC051/li_Download Price'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

latestPrice = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

int rowstart = 9

for (int row = 1; row <= testDataPrice.getRowNumbers(); row++) {
    def value = testDataPrice.getValue('Unit Price', row)

    def valueCurrency = testDataPrice.getValue('Currency', row)

    int rowExcel = rowstart + row

    CustomKeywords.'copyToExcel.exel3'(value, rowExcel, 11, latestPrice, 'base')

    CustomKeywords.'copyToExcel.exel3'(valueCurrency, rowExcel, 12, latestPrice, 'base')
}

println('HW')

WebUI.click(findTestObject('Scenario 10/S10_TC051/button_upload part monitor'))

//WebUI.click(findTestObject('Scenario 10/S10_TC051/li_Upload Price'))
CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC051/li_Upload Price'), latestPrice)

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

//int numberrowtd = testData.getRowNumbers()
//for (int rowl = 1; rowl <= numberrowtd; rowl++) {
//int coll = 1
//for (String col : columnname) {
//String valuecol = testData.getValue(col, rowl)
// WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC050 TC053/p_part detail list-tc50 - Copy', [('lrow') : rowl
//, ('lcol') : coll]), valuecol)
//coll = (coll + 1)
//}
//}
CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC051/button_SO-Confirm'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC051/button_CONFIRM'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.back()

WebUI.setText(findTestObject('Page_ChangeCancelReqList/Page_ChgCancelReqDetail/input_SO Monitoring List_lcbm'), orderNo1)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC051/div_dt-Status'), 'Confirmed')

WebUI.closeBrowser()

