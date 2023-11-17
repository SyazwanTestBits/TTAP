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
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Scenario 12/SC12_TC033/li_Place Supplier Change'))

WebUI.setText(findTestObject('Scenario 10/S10_TC083/input_Supplier Order Change_search'), salesOrderNo)

WebUI.click(findTestObject('Page_OrderChangeCancel/div_Dt_ContractNo_Create', [('contractNo') : contractNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Scenario 12/SC12_TC033/h3_Create Supplier Order Change'), 0)

WebUI.click(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/button_Download'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.delay(2)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

downloadedExcel = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

partsNoRowIndices = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(downloadedExcel, 2, 16)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 17/S17_Cmn1-Write Info into Form Excel - mapDataIndices'), [('datafile') : datafile
        , ('fileColumns') : mapKeyandColIndex, ('mapDataIndices') : partsNoRowIndices, ('downloadedFormPath') : downloadedExcel
        , ('downloadedFormSheetname') : salesOrderNo], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 17/S17_Cmn1-Write Info into Form Excel'), [('datafile') : testdataInboundDateChange
        , ('fileColumns') : fileColumns, ('startRowFormMinusOne') : startRowFormMinusOne, ('downloadedFormPath') : downloadedExcel
        , ('downloadedFormSheetname') : salesOrderNo], FailureHandling.STOP_ON_FAILURE)

WebUI.uploadFile(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/input_UploadFile'), downloadedExcel)

WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC033/div_Upload Supplier Order Change. The operation was successful'), 
    0)

WebUI.click(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/button_Issue'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSureToDo_Issue'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC055/Page_Supplier Order ChangeCancel - Brivge/div_Save And Issue Change Supplier Order. The operation was successful'), 
    0)

WebUI.closeBrowser()

