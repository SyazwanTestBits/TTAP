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
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Master'))

WebUI.click(findTestObject('Navbar_Brivge/MasterMenu_Brivge/li_Received Request List'))

WebUI.waitForElementPresent(findTestObject('Page_ReceivedReqList/h3_Received Request List'), 0)

WebUI.setText(findTestObject('Page_ReceivedReqList/input_Search_ReceivedReqList'), requestNo)

WebUI.click(findTestObject('Page_ReceivedReqList/td_ReceivedReqList_ReqEdit', [('requestNo') : requestNo]))

WebUI.waitForElementVisible(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/h3_Received Request Add New Part'), 
    0)

WebUI.click(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/button_Next'))

WebUI.mouseOver(findTestObject('Page_RequestList/Page_ReqAddNewPart/button__Refresh_ChgtoFinalSeller'))

WebUI.verifyElementPresent(findTestObject('Page_RequestList/Page_ReqAddNewPart/tooltip_ChangetoFinalSeller'), 0)

WebUI.click(findTestObject('Page_RequestList/Page_ReqAddNewPart/button__Refresh_ChgtoFinalSeller'))

WebUI.verifyElementNotPresent(findTestObject('Page_RequestList/Page_ReqAddNewPart/datafield_nextSellerCode'), 0)

WebUI.verifyElementNotPresent(findTestObject('Page_RequestList/Page_ReqAddNewPart/datafield_NextSellerPartsNo'), 0)

WebUI.click(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/button_Dwnload_ContractPartInfo'))

WebUI.click(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/li_Download Part Form'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_DwnloadReqPartsDetail_Success'), 0)

contractPartsForm = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_TC020_Cmn_1-Write Request Add New Parts Data (L2 Parts)'), 
    [('datafile') : datafile_contractpartinfo, ('fileColumns') : contractFileColumns, ('startRowFormMinusOne') : startRowFormMinusOne
        , ('downloadedFormPath') : contractPartsForm, ('downloadedFormSheetname') : downloadedFormSheetname], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/button_Upload_ContractPartInfo'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/li_Upload Master'), 
    contractPartsForm)

not_run: WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadWorkingMaster_Success'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.click(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/button_Next'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SaveWorkingParts_Success'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_TC021_Cmn_1-Input Received Req Add New Part L2 (BU)'), 
    [('businessType') : businessType, ('orderFrequency') : orderFrequency, ('forecastNumber') : forecastNumber, ('targetLeadtime') : targetLeadtime
        , ('contractShortCode') : contractShortCode, ('paymentTermCode') : paymentTermCode, ('currency') : currency, ('priceBasis') : priceBasis
        , ('shipperDc') : shipperDc, ('shippingRouteCode') : shippingRouteCode, ('customsFlag') : customsFlag, ('contractDescription') : contractDescription
        , ('routeDescription') : routeDescription], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_ConfirmWorking_Success'), 0)

WebUI.setText(findTestObject('Page_ReceivedReqList/input_Search_ReceivedReqList'), requestNo)

WebUI.verifyElementText(findTestObject('Page_ReceivedReqList/td_ReceivedReqList_ReqStatus'), 'Completed')

WebUI.takeFullPageScreenshot()

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_TC007_Cmn_2-Get Contract Route Code'), [('routeDescription') : routeDescription
        , ('contractRouteCodeIndex') : 14, ('filepath') : filepath, ('filename') : filename, ('sheetname') : sheetname], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

