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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectBuilder as TestObjectBuilder

WebUI.click(findTestObject('Scenario 12/SC12_TC011/button_Spot Order'))

WebUI.waitForElementPresent(findTestObject('Scenario 12/SC12_TC011/h3_Place Spot Order'), 0)

WebUI.setText(findTestObject('Scenario 12/SC12_TC011/Page_Place Regular Order - Brivge/input_Search'), contractNo)

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC011/div_ContractRouteNo', [('contractRouteNo') : contractRouteNo]), 
    contractRouteNo)

WebUI.click(findTestObject('Scenario 12/SC12_TC011/div_addSpotOrder', [('contractRouteNo') : contractRouteNo]))

WebUI.waitForElementPresent(findTestObject('Scenario 12/SC12_TC011/h3_Place Order Detail(Spot)'), 0)

WebUI.click(findTestObject('Scenario 12/SC12_TC011/button_Download'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC011/div_Download Spot Order Form For Customer.The operation was successful'), 
    0)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'ExcelActions.writeIntoExcelPlaceOrderSpotSC12'(latestFilePath, contractNo)

WebUI.uploadFile(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/input_UploadFile'), latestFilePath)

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC011/div_Upload Spot Order Form For Customer.The operation was successful'), 
    0)

WebUI.click(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/button_Issue'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSureToDo_Issue'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_Issue_Success'), 0)

WebUI.takeFullPageScreenshot()

