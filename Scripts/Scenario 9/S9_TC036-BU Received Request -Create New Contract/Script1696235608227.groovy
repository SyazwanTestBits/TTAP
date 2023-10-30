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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Received Request List'))

WebUI.waitForElementPresent(findTestObject('Page_ReceivedReqList/h3_Received Request List'), 0)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/input_Filter_Received Request List'), description)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Edit'))

WebUI.delay(5)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Next'))

RequestNo = WebUI.getText(findTestObject('Scenario 12/SC12_TC004/p_RequestNo_firstRow'))

CustomKeywords.'copyToExcel.exel'(RequestNo, 1, 1, 'Excel Files\\Scenario 9', 'S9_TestCases_Data.xlsx', 'TC36-BU to Customer Contract')

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Download'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Download Part Form'))

WebUI.verifyElementPresent(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/div_Download Request Parts Detail.The operation was successful'), 
    0)

downloadedExcel = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 9/S9_Cmn1-Write Info into Form Excel'), [('datafile') : datafile_contractPartsInfo
        , ('fileColumns') : fileColumns, ('startRowFormMinusOne') : startRowFormMinusOne, ('downloadedFormPath') : downloadedExcel
        , ('downloadedFormSheetname') : downloadedFormSheetname], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Upload'))

'Upload the file that has been edited.'
CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Upload Master'), downloadedExcel)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_Upload_The operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Next'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SaveWorkingParts_Success'), 0)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__businessType'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_BusinessType', [('businessType') : businessType]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__orderFrequency'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Order Frequency', [('orderFrequency') : orderFrequency]))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__forecastNumber'), forecastNumber)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__targetLeadTime'), targetLeadtime)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__contractShortCode'), contractShortCode)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__Contract Description'), contractDescription)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__paymentTermsId'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Payment Terms Code', [('paymentTermCode') : paymentTermCode]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__currencyCode'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Currency', [('currency') : currency]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__priceBasis'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Price Basis', [('priceBasis') : priceBasis]))

WebUI.click(findTestObject('Scenario 9/SC9_TC002/input_shipperDC'))

WebUI.click(findTestObject('Scenario 9/SC9_TC002/li_shipperDC', [('shipperDC') : shipperDC]))

WebUI.click(findTestObject('Scenario 9/SC9_TC002/input_shippingRouteCode'))

WebUI.click(findTestObject('Scenario 9/SC9_TC002/li_shippingRouteCode', [('shippingRouteCode') : shippingRouteCode]))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input_routeDescription'), routeDescription)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__customsFlag'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Customs Flag', [('customsFlag') : customsFlag]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Next'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SaveWorking_Success'), 0)

WebUI.setText(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/input_submitReqTo_Seller1_description'), 
    routeDescription)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Complete'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_PromptMsg_AreYouSureToDoComplete'), 0)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Confirm'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_Confirm Working.The operation was successful'), 
    0)

WebUI.waitForElementPresent(findTestObject('Page_ReceivedReqList/h3_Received Request List'), 0)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/input_Filter_Received Request List'), description)

WebUI.verifyElementText(findTestObject('Page_ReceivedReqList/td_ReceivedReqList_ReqStatus'), 'Completed')

WebUI.callTestCase(findTestCase('Scenario 9/S9_TC036.1-Get Contract Route Code'), [('contractNo') : findTestData('Scenario 9/S9_TC036-BU to Customer Contract').getValue(
            'ContractNo', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

