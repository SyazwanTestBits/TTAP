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

WebUI.click(findTestObject('Navbar_Brivge/MasterMenu_Brivge/li_Request List'))

WebUI.waitForElementPresent(findTestObject('Page_RequestList/h3_Request List'), 0)

WebUI.click(findTestObject('Page_RequestList/button_CreateNewRequest'))

WebUI.click(findTestObject('Page_RequestList/li_ADD NEW PART'))

WebUI.waitForElementPresent(findTestObject('Page_RequestList/Page_ReqAddNewPart/h3_Request Add New Part'), 0)

WebUI.click(findTestObject('Page_RequestList/Page_ReqAddNewPart/input__requestTo'))

WebUI.click(findTestObject('Page_RequestList/Page_ReqAddNewPart/li_RequestToOption', [('requestTo') : requestTo]))

WebUI.setText(findTestObject('Page_RequestList/Page_ReqAddNewPart/input__description'), description)

WebUI.click(findTestObject('Page_RequestList/Page_ReqAddNewPart/button_DownloadPartForm'))

WebUI.click(findTestObject('Page_RequestList/Page_ReqAddNewPart/li_DOWNLOAD PART FORM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadRequestParts_Success'), 0)

downloadedForm = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 10/S10_TC020_Cmn_1-Write Request Add New Parts Data (L2 Parts)'), 
    [('datafile') : datafile_ReqAddNewPart, ('fileColumns') : reqAddNewPartFormColumns, ('startRowFormMinusOne') : startRowFormMinusOne
        , ('downloadedFormPath') : downloadedForm, ('downloadedFormSheetname') : downloadedFormSheetName], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_RequestList/Page_ReqAddNewPart/button_UploadPartForm'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Page_RequestList/Page_ReqAddNewPart/li_UPLOAD PART FORM'), downloadedForm)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadParts_Success'), 0)

WebUI.click(findTestObject('Page_RequestList/Page_ReqAddNewPart/button_Save'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SaveRequest_Success'), 0)

WebUI.click(findTestObject('Page_RequestList/Page_ReqAddNewPart/button_Submit'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSure_ToDoSubmit'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SubmitRequestDetail_Success'), 0)

WebUI.waitForElementPresent(findTestObject('Page_RequestList/h3_Request List'), 0)

requestNo = WebUI.getText(findTestObject('Page_RequestList/Page_ReqAddNewPart/tr1_ReqList_RequestNo'))

CustomKeywords.'copyToExcel.exel'(requestNo, 1, 1, 'Excel Files\\Scenario 10', 'S10_TestCases_Data.xlsx', 'TC20-Autogen Data')

WebUI.verifyElementText(findTestObject('Page_RequestList/Page_ReqAddNewPart/tr1_ReqList_Description'), description)

WebUI.verifyElementText(findTestObject('Page_RequestList/Page_ReqAddNewPart/tr1_ReqList_ReqType'), 'Add New Part')

WebUI.verifyElementText(findTestObject('Page_RequestList/Page_ReqAddNewPart/tr1_ReqList_Status'), 'Submitted')

WebUI.closeBrowser()

