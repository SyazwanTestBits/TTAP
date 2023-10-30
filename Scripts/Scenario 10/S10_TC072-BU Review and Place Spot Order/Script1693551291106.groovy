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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_SYAZWAN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC024/button_Stock Management Tool'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC035/li_Order Calculation List'))

WebUI.waitForElementVisible(findTestObject('Scenario 10/S10_TC035/h3_Order Calculation List'), 0)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC035/h3_Order Calculation List'), 'Order Calculation List')

WebUI.setText(findTestObject('Scenario 10/S10_TC035/input_Search Order Calculation'), orderCalcRefNo)

WebUI.click(findTestObject('Scenario 10/S10_TC035/input_tick all'))

WebUI.click(findTestObject('Scenario 10/S10_TC072/button_ReviewBtn'))

WebUI.click(findTestObject('Scenario 10/S10_TC072/div_spot tick all'))

WebUI.click(findTestObject('Scenario 10/S10_TC072/button_Review'))

WebUI.setText(findTestObject('Scenario 10/S10_TC072/input__adjustmentReason'), 'Place Spot Order')

WebUI.click(findTestObject('Scenario 10/S10_TC072/button_Confirm'))

WebUI.waitForElementPresent(findTestObject('Scenario 10/S10_TC072/div_ReviewAre you sure to do Review(c0001)CANCELCONFIRM'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC072/button_CONFIRMpopup'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC072/div_Create Spot OrderThe operation was successful'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC072/button_Download'))

not_run: WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC072/div_Download Spot Order.The operation was successful'), 
    0)

WebUI.delay(2)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

partsNoRowIndices = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(latestFilePath, 2, 16)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_Cmn3-Write Info into Form Excel with Sorting'), [('datafile') : datafile
        , ('fileColumns') : fileColumns, ('mapDataIndices') : partsNoRowIndices, ('downloadedFormPath') : latestFilePath
        , ('downloadedFormSheetname') : contractNo], FailureHandling.STOP_ON_FAILURE)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'copyToExcel.exel'(findTestData('Scenario 10/S10_TC072-Inbound Plan Date').getValue('InPlan_1', 1), 13, 17, 
    latestFilePath, '', contractNo)

CustomKeywords.'copyToExcel.exel'(findTestData('Scenario 10/S10_TC072-Inbound Plan Date').getValue('InPlan_1', 2), 15, 17, 
    latestFilePath, '', contractNo)

CustomKeywords.'copyToExcel.exel'(findTestData('Scenario 10/S10_TC072-Inbound Plan Date').getValue('InPlan_2', 1), 13, 18, 
    latestFilePath, '', contractNo)

CustomKeywords.'copyToExcel.exel'(findTestData('Scenario 10/S10_TC072-Inbound Plan Date').getValue('InPlan_2', 2), 15, 18, 
    latestFilePath, '', contractNo)

CustomKeywords.'copyToExcel.exel'(findTestData('Scenario 10/S10_TC072-Inbound Plan Date').getValue('InPlan_3', 1), 13, 19, 
    latestFilePath, '', contractNo)

CustomKeywords.'copyToExcel.exel'(findTestData('Scenario 10/S10_TC072-Inbound Plan Date').getValue('InPlan_3', 2), 15, 19, 
    latestFilePath, '', contractNo)

CustomKeywords.'copyToExcel.exel'(contractNo, 3, 3, latestFilePath, '', contractNo)

CustomKeywords.'copyToExcel.exel'('Place Spot Order', 9, 3, latestFilePath, '', contractNo)

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC072/button_Upload'), latestFilePath)

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC072/div_Upload Placed OrderThe operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC072/button_Issue'))

WebUI.click(findTestObject('Scenario 10/S10_TC072/button_CONFIRMissue'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC072/p_Order Calculation List'))

WebUI.setText(findTestObject('Scenario 10/S10_TC035/input_Search Order Calculation'), orderCalcRefNo)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC072/p_verifyOrderIssued', [('OrderCalRefNo') : orderCalcRefNo + 
            '-#1']), 'ORDER ISSUED')

customerUsageNo = WebUI.getText(findTestObject('Scenario 10/S10_TC072/p_getCustomerUsage', [('ocrf_spot') : orderCalcRefNo_spot]))

CustomKeywords.'copyToExcel.exel'(customerUsageNo, 1, 0, 'Excel Files\\Scenario 10', 'S10_TestCases_Data.xlsx', 'TC072-Spot Customer Usage')

WebUI.closeBrowser()

