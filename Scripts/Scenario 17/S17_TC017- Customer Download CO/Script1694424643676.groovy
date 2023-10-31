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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_CO Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_CO_MonitoringList/h3_CO Monitoring List'), 0)

WebUI.setText(findTestObject('Scenario 17/S17_TC006/input_Search'), ContractNo)

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC017/div_CustomerOrderNo', [('contractNo') : ContractNo, ('orderType') : orderType]), 
    0)

'Grab the customer order no'
CO_no = WebUI.getText(findTestObject('Scenario 17/S17_TC017/div_CustomerOrderNo', [('contractNo') : ContractNo, ('orderType') : orderType]))

'copy customer order no to excel\r\n'
CustomKeywords.'copyToExcel.exel'(CO_no, 1, 0, filePath, fileName, sheetName)

WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC017/input_Checkbox', [('contractNo') : ContractNo, ('orderType') : OrderType_Regular]), 
    0)

WebUI.check(findTestObject('Scenario 17/S17_TC017/input_Checkbox', [('contractNo') : ContractNo, ('orderType') : OrderType_Regular]))

WebUI.click(findTestObject('Scenario 17/S17_TC017/Page_CO Monitoring List - Brivge/button_download'))

WebUI.click(findTestObject('Page_CO_MonitoringList/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadCustOrderbyExcel_Success'), 0)

WebUI.delay(2)

LatestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSortMap'(LatestPath, expectationExcelPath, 1, [23, 24, 25, 26, 27], [1, 2, 3
        , 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15])

WebUI.closeBrowser()

