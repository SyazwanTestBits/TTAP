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

WebUI.verifyElementText(findTestObject('Scenario 17/S17_TC017/div_CustomerOrderNo', [('contractNo') : ContractNo, ('orderType') : OrderType_Regular]), 
    RegularCustomerOrderNo)

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC017/input_Checkbox', [('contractNo') : ContractNo, ('orderType') : OrderType_Regular]), 
    0)

WebUI.check(findTestObject('Scenario 17/S17_TC017/input_Checkbox', [('contractNo') : ContractNo, ('orderType') : OrderType_Regular]))

WebUI.click(findTestObject('Page_CO_MonitoringList/button_Dt_DownloadOptions'))

WebUI.click(findTestObject('Page_CO_MonitoringList/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadCustOrderbyExcel_Success'), 0)

LatestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

absoluteExpectPath = CustomKeywords.'ManageFiles.getFileAbsolutePath'(expectationExcelPath)

nomatch = CustomKeywords.'util.compareTestData.compareExcelFiles'(LatestPath, absoluteExpectPath, 24, 28, 2, 20)

println('Number of error: ' + nomatch)

WebUI.verifyEqual(nomatch, NumberOfNoMatch, FailureHandling.STOP_ON_FAILURE)

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

