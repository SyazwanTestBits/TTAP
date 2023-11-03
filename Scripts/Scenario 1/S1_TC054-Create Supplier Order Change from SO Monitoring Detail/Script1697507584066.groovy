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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 1/S1_TC054/button_Order'))

WebUI.click(findTestObject('Scenario 1/S1_TC054/li_SO Monitoring List'))

WebUI.setText(findTestObject('Scenario 1/S1_TC054/input_SO Monitoring List_Search'), SalesOrderNo)

WebUI.click(findTestObject('Scenario 1/S1_TC054/button_detail'))

WebUI.click(findTestObject('Scenario 1/S1_TC054/button_Change Order'))

WebUI.click(findTestObject('Scenario 1/S1_TC054/button_Download'))

WebUI.delay(2)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

println(latestFilePath)

CustomKeywords.'copyToExcel.exel2'(firmQTY, 18, 15, latestFilePath, SalesOrderNo)

CustomKeywords.'copyToExcel.exel2'(Firm1, 18, 24, latestFilePath, SalesOrderNo)

CustomKeywords.'copyToExcel.exel2'(Firm2, 18, 25, latestFilePath, SalesOrderNo)

CustomKeywords.'copyToExcel.exel2'(Date2, 15, 25, latestFilePath, SalesOrderNo)

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 1/S1_TC054/button_Upload'), latestFilePath)

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC033/div_Upload Supplier Order Change. The operation was successful'), 
    0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.click(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/button_Issue'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSureToDo_Issue'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.callTestCase(findTestCase('Scenario 1/S1_TC054.1-Get Request No'), [('contractRouteCode') : findTestData('Scenario 1/S1_TC002-BU1 to Customer Contract').getValue('ContractRouteCode', 1)], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

