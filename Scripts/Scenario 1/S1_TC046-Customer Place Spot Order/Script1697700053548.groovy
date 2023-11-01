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

WebUI.click(findTestObject('Object Repository/Scenario 1/S1_TC046/Page_Home Page - Brivge/button_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 1/S1_TC046/Page_Home Page - Brivge/li_Place Order'))

WebUI.click(findTestObject('Object Repository/Scenario 1/S1_TC046/Page_Place Regular Order - Brivge/span_Spot Order'))

WebUI.setText(findTestObject('Object Repository/Scenario 1/S1_TC046/Page_Place Spot Order - Brivge/input_Spot Order_Search'), 
    ContractRouteNo)

WebUI.click(findTestObject('Object Repository/Scenario 1/S1_TC046/Page_Place Spot Order - Brivge/button_Create'))

WebUI.click(findTestObject('Object Repository/Scenario 1/S1_TC046/Page_Place Order Detail(Spot) - Brivge/button_Download'))

WebUI.takeFullPageScreenshot()

WebUI.verifyElementPresent(findTestObject('Object Repository/Scenario 1/S1_TC046/Page_Place Order Detail(Spot) - Brivge/div_Download Spot Order Form For Customer'), 
    0)

WebUI.delay(3)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

println(latestFilePath)

CustomKeywords.'copyToExcel.exel3'(spotOrderReason, 11, 3, latestFilePath, custContractNo)

CustomKeywords.'copyToExcel.exel3'(FirmQty1, 17, 14, latestFilePath, custContractNo)

CustomKeywords.'copyToExcel.exel3'(FirmQty2, 18, 14, latestFilePath, custContractNo)

CustomKeywords.'copyToExcel.exel3'(FirmQty3, 19, 14, latestFilePath, custContractNo)

CustomKeywords.'copyToExcel.exel3'(FirmQty4, 20, 14, latestFilePath, custContractNo)

CustomKeywords.'copyToExcel.exel3'(FirmQty5, 21, 14, latestFilePath, custContractNo)

CustomKeywords.'copyToExcel.exel3'(FirmQty6, 22, 14, latestFilePath, custContractNo)

CustomKeywords.'copyToExcel.exel3'(inboundDate1, 16, 19, latestFilePath, custContractNo)

CustomKeywords.'copyToExcel.exel3'(inboundDate2, 16, 20, latestFilePath, custContractNo)

CustomKeywords.'copyToExcel.exel3'(inboundQty1, 19, 20, latestFilePath, custContractNo)

CustomKeywords.'copyToExcel.exel3'(inboundQty2, 20, 19, latestFilePath, custContractNo)

CustomKeywords.'copyToExcel.exel3'(inboundQty3, 22, 19, latestFilePath, custContractNo)

CustomKeywords.'copyToExcel.exel3'(inboundQty4, 22, 20, latestFilePath, custContractNo)

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 1/S1_TC046/Page_Place Order Detail(Spot) - Brivge/button_Upload'), 
    latestFilePath)

WebUI.takeFullPageScreenshot()

WebUI.verifyElementPresent(findTestObject('Scenario 1/S1_TC046/div_Upload Spot Order Form For Customer.The operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 1/S1_TC046/Page_Place Order Detail(Spot) - Brivge/button_Issue'))

WebUI.click(findTestObject('Scenario 1/S1_TC046/Page_Place Order Detail(Spot) - Brivge/button_Confirm'))

WebUI.click(findTestObject('Scenario 1/S1_TC046/button_Confirm (Firm Qty is Zero)'))

WebUI.verifyElementPresent(findTestObject('Scenario 1/S1_TC046/div_Upload Spot Order Form For Customer.The operation was successful'), 
    0)

WebUI.closeBrowser()

