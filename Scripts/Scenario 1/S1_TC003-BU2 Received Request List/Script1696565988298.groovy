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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_BU2], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Received Request List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/input_Filter_Received Request List'), description)

WebUI.click(findTestObject('Scenario 1/S1_TC003/button_Edit'))

'turn on back button for debug'
not_run: WebUI.click(findTestObject('Scenario 12/SC12_TC004/button_Back'))

'turn on back button for debug'
not_run: WebUI.click(findTestObject('Scenario 12/SC12_TC004/button_Back'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Next'))

RequestNo = WebUI.getText(findTestObject('Scenario 12/SC12_TC004/p_RequestNo'))

CustomKeywords.'copyToExcel.exel'(RequestNo, 1, 1, 'Excel Files\\Scenario 1', 'S1_TestCases_Data.xlsx', 'TC3-BU2 to BU1 Contract')

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Download'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Download Part Form'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_The operation was successful'), 0)

WebUI.takeFullPageScreenshot()

downloadedExcel = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 1/S1_Cmn1-Write Info into Form Excel'), [('datafile') : testdata_contractPartsInfo
        , ('fileColumns') : fileColumn, ('startRowFormMinusOne') : 11, ('downloadedFormPath') : downloadedExcel, ('downloadedFormSheetname') : sheet], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Upload'))

'Upload the file that has been edited.'
CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Upload Master'), downloadedExcel)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_Upload_The operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Next'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__deliveryToUid'))

WebUI.click(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/li_deliveryToUidOption', [('deliveryToUidOption') : deliveryTo]))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__contractShortCode'), contractShortCode)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__Contract Description'), contractDescription)

WebUI.click(findTestObject('Page_ReceivedReqList/Page_Create Payment Terms - Brivge/button_ADD PAYMENT TERMS'))

WebUI.setText(findTestObject('Page_ReceivedReqList/Page_Create Payment Terms - Brivge/input__paymentTermsCode'), inputPaymentTerm)

WebUI.setText(findTestObject('Page_ReceivedReqList/Page_Create Payment Terms - Brivge/input__description'), paymentTermDescription)

WebUI.click(findTestObject('Page_ReceivedReqList/Page_Create Payment Terms - Brivge/input__termsType'))

WebUI.click(findTestObject('Page_ReceivedReqList/Page_Create Payment Terms - Brivge/li_TermsType', [('paymentTermsType') : termsType]))

WebUI.setText(findTestObject('Page_ReceivedReqList/Page_Create Payment Terms - Brivge/input__fromMonth'), fromMonth)

WebUI.setText(findTestObject('Page_ReceivedReqList/Page_Create Payment Terms - Brivge/input__days'), days)

WebUI.setText(findTestObject('Page_ReceivedReqList/Page_Create Payment Terms - Brivge/input__fromDay'), fromDay)

WebUI.click(findTestObject('Page_ReceivedReqList/Page_Create Payment Terms - Brivge/button_Save'))

WebUI.verifyElementPresent(findTestObject('Page_ReceivedReqList/Page_Create Payment Terms - Brivge/div_Save Payment Terms Detail.The operation was successful'), 
    0)

WebUI.takeFullPageScreenshot()

WebUI.delay(2)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__paymentTermsId'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Payment Terms Code', [('paymentTermCode') : paymentTermCode]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__currencyCode'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Currency', [('currency') : currency]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__priceBasis'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Price Basis', [('priceBasis') : priceBasis]))

WebUI.click(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/input_ADDDISTRIBUTIONCENTER_shipperUid'))

WebUI.click(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/li_shipperUid_Option', [('shipperDc') : shipperDC]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input_Shipping Route Code'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Shipping Route Code', [('shippingRouteCode') : shippingRouteCode]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__customsFlag'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Customs Flag', [('customsFlag') : customsFlag]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__expAgencyUid'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_expAgencyUidOption', [('expCustomParty') : exportCustomParty]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__impAgencyUid'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_impAgencyUidOption', [('impAgencyUid') : importCustomParty]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__printHSCodeFlag'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_printHSCodeFlagOption', [('printHSCodeFlag') : HSCodeFlag]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Next'))

WebUI.setText(findTestObject('Scenario 1/S1_TC003/Page_Received Request Add New Part - Brivge/input_SUBMIT REQUEST TO CNTW-SUP-POC_description'), 
    SP2_Description)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Complete'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Confirm'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_Confirm Working.The operation was successful'), 
    0)

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

