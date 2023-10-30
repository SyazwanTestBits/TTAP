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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.CUST_USERNAME_USERF
        , ('password') : GlobalVariable.CUST_PWD_USERF, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.COMPANY_SUPPLIER_1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Received Request List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/input_Filter_Received Request List'), description)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Edit'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Next'))

RequestNo = WebUI.getText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/p_RequestNo'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Download'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Download Part Form'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_The operation was successful'), 0)

CustomKeywords.'copyToExcel.exel'(RequestNo, 12, 2, filePath, fileName, sheetName)

CustomKeywords.'copyToExcel.exel'(RequestNo, 13, 2, filePath, fileName, sheetName)

CustomKeywords.'copyToExcel.exel'(RequestNo, 14, 2, filePath, fileName, sheetName)

CustomKeywords.'copyToExcel.exel'(RequestNo, 1, 1, 'Excel Files\\Scenario 13', 'S13_TestCases_Data.xlsx', 'TC35')

CustomKeywords.'copyToExcel.exel'(contractNo, 12, 7, 'Excel Files\\Scenario 13\\TC035', '13.0 - TC35 - Contract Parts Info.xlsx', 
    'Request Contract Detail')

CustomKeywords.'copyToExcel.exel'(contractNo, 13, 7, 'Excel Files\\Scenario 13\\TC035', '13.0 - TC35 - Contract Parts Info.xlsx', 
    'Request Contract Detail')

CustomKeywords.'copyToExcel.exel'(contractNo, 14, 7, 'Excel Files\\Scenario 13\\TC035', '13.0 - TC35 - Contract Parts Info.xlsx', 
    'Request Contract Detail')

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Upload'))

AbsolutePath = CustomKeywords.'ManageFiles.getFileAbsolutePath'('Excel Files/Scenario 13/TC035/13.0 - TC35 - Contract Parts Info.xlsx')

'Upload the file that has been edited.'
CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Upload Master'), AbsolutePath)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_Upload_The operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Next'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__businessType'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_BusinessType', [('businessType') : businessType]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__orderFrequency'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Order Frequency', [('orderFrequency') : orderFrequency]))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__forecastNumber'), forecastNumber)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__targetLeadTime'), targetLeadtime)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__confirmOrderLeadtime'), confirmOrderLeadTime)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__leadtime'), leadTime)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__deliveryPlanStartDate'), deliveryPlanStartDate)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__contractShortCode'), contractShortCode)

not_run: CustomKeywords.'copyToExcel.exel'(contractShortCode, 1, 10, 'Excel Files\\Scenario 13', td_fileName, td_sheetName)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__Contract Description'), contractDescription)

not_run: CustomKeywords.'copyToExcel.exel'(contractDescription, 1, 11, 'Excel Files\\Scenario 13', td_fileName, td_sheetName)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__paymentTermsId'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Payment Terms Code', [('paymentTermCode') : paymentTermCode]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__currencyCode'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Currency', [('currency') : currency]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__priceBasis'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Price Basis', [('priceBasis') : priceBasis]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input_Shipping Route Code'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Shipping Route Code', [('shippingRouteCode') : shippingRouteCode]))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input_routeDescription'), routeDescription)

not_run: CustomKeywords.'copyToExcel.exel'(routeDescription, 1, 16, 'Excel Files\\Scenario 13', td_fileName, td_sheetName)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__customsFlag'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Customs Flag', [('customsFlag') : customsFlag]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__expAgencyUid'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_expAgencyUidOption', [('expCustomParty') : exportCustomsParty]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__impAgencyUid'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_impAgencyUidOption', [('impAgencyUid') : importCustomsParty]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__printHSCodeFlag'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_printHSCodeFlagOption', [('printHSCodeFlag') : printHSCodeFlag]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input_INCOTERMS INFO_incotermsCode'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Incoterms Code', [('incotermsCode') : incotermsCode]))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input_INCOTERMS INFO_incotermsPlace'), incotermsPlace)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Complete'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Confirm'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_Confirm Working.The operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Contract Route List/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Contract Route List/li_Contract Route List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Contract Route List/input_Contract Route List'), routeDescription)

WebUI.delay(1)

ContractRouteCode = WebUI.getText(findTestObject('Scenario 13/S13_TC035/Contract Route List/p_Contract Route Code'))

CustomKeywords.'copyToExcel.exel'(ContractRouteCode, 1, 23, 'Excel Files\\Scenario 13', 'S13_TestCases_Data.xlsx', 'TC35')

WebUI.closeBrowser()

