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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_Place Supplier Change'))

WebUI.waitForElementPresent(findTestObject('Page_Supplier Order ChangeCancel - Brivge/h3_Supplier Order ChangeCancel'), 
    0)

WebUI.setText(findTestObject('Page_Supplier Order ChangeCancel - Brivge/input_SupplierOrderChange_Order_Search'), salesOrderNo)

WebUI.click(findTestObject('Page_Supplier Order ChangeCancel - Brivge/button_Create_SupplierOrderChange', [('salesOrderNo') : salesOrderNo]), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page_Supplier Order ChangeCancel - Brivge/Page_Create Supplier Order Change - Brivge/h3_Create Supplier Order Change'), 
    0)

WebUI.click(findTestObject('Page_Supplier Order ChangeCancel - Brivge/Page_Create Supplier Order Change - Brivge/button_Download'), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Page_Supplier Order ChangeCancel - Brivge/Page_Create Supplier Order Change - Brivge/div_Download Supplier Order Change. The operation was successful'), 
    0)

supOrderChangeForm = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'excelUtils.unprotectExcelSheet'(supOrderChangeForm)

supOrderPartsNoMap = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(supOrderChangeForm, 2, 16)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 9/S9_Cmn1-Write Info into Form Excel with Sorting (Supplier Order Change)'), 
    [('datafile') : datafile_suporderchange, ('fileColumns') : suporder_filecolidx, ('mapDataIndices') : supOrderPartsNoMap
        , ('downloadedFormPath') : supOrderChangeForm, ('downloadedFormSheetname') : salesOrderNo], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 9/S9_Cmn2-Write Info into Form Excel with Sorting (Supplier Order Change New Outbound Qty)'), 
    [('datafile') : datafile_suporderchange, ('mapDataIndices') : supOrderPartsNoMap, ('downloadedFormPath') : supOrderChangeForm
        , ('downloadedFormSheetname') : salesOrderNo, ('daterange') : 5, ('columnNameHeader') : 'newOutboundQty', ('columnIndexMinusOne') : 27], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 9/S9_Cmn3-Write Info into Form Excel (Supplier Order Change Date)'), 
    [('datafile') : datafile_suporderchange_date, ('dateRange') : 5, ('startRowForm') : 15, ('downloadedFormPath') : supOrderChangeForm
        , ('downloadedFormSheetname') : salesOrderNo, ('columnName') : 'newOutboundDate', ('columnIndexStartMinusOne') : 27
        , ('columnHeaderListIndex') : 'adjustmentDays'], FailureHandling.STOP_ON_FAILURE)

WebUI.uploadFile(findTestObject('Page_Supplier Order ChangeCancel - Brivge/Page_Create Supplier Order Change - Brivge/input_fileUpload_SupplierOrderChange'), 
    supOrderChangeForm)

WebUI.verifyElementPresent(findTestObject('Page_Supplier Order ChangeCancel - Brivge/Page_Create Supplier Order Change - Brivge/div_Upload Supplier Order Change. The operation was successful'), 
    0)

WebUI.click(findTestObject('Page_Supplier Order ChangeCancel - Brivge/Page_Create Supplier Order Change - Brivge/button_Issue'), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSureToDo_Issue'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Page_Supplier Order ChangeCancel - Brivge/div_Save And Issue Change Supplier Order. The operation was successful'), 
    0)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_SO Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringList/h3_SO Monitoring List'), 0)

WebUI.setText(findTestObject('Page_SO_MonitoringList/input_SO Monitoring List'), salesOrderNo)

WebUI.click(findTestObject('Page_SO_MonitoringList/button_Dt_Detail', [('contractNo') : salesOrderNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringDetail/h3_SO Monitoring Detail'), 0)

WebUI.verifyElementNotPresent(findTestObject('Page_SO_MonitoringDetail/button_CHANGE ORDER'), 0)

WebUI.closeBrowser()

