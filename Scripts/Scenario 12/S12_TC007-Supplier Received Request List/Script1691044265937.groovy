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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Received Request List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/input_Filter_Received Request List'), description)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Edit'))

'turn on back button for debug'
not_run: WebUI.click(findTestObject('Scenario 12/SC12_TC004/button_Back'))

'turn on back button for debug'
not_run: WebUI.click(findTestObject('Scenario 12/SC12_TC004/button_Back'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Next'))

RequestNo = WebUI.getText(findTestObject('Scenario 12/SC12_TC004/p_RequestNo'))

CustomKeywords.'copyToExcel.exel'(RequestNo, 1, 1, 'Excel Files\\Scenario 12', 'S12_TestCases_Data.xlsx', 'TC7')

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Download'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Download Part Form'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_The operation was successful'), 0)

downloadedExcel = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 12/S12_Cmn2-Write Info into Form Excel_Latest'), [('datafile') : partsInfoData
        , ('fileColumns') : filecolumns, ('startRowFormMinusOne') : 11, ('downloadedFormPath') : downloadedExcel, ('downloadedFormSheetname') : 'Request Contract Detail'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Upload'))

'Upload the file that has been edited.'
CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Upload Master'), downloadedExcel)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_Upload_The operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Next'))

WebUI.click(findTestObject('Scenario 12/SC12_TC007/svg__deliveryTo'))

WebUI.click(findTestObject('Scenario 12/SC12_TC007/li_deliveryToCompany', [('deliveryTo') : deliveryTo]))

WebUI.setText(findTestObject('Scenario 12/SC12_TC007/input__confirmOrderLeadtime'), confirmOrderLeadTime)

WebUI.setText(findTestObject('Scenario 12/SC12_TC007/input__leadtime'), leadTime)

WebUI.setText(findTestObject('Scenario 12/SC12_TC007/input__deliveryPlanStartDate'), deliveryPlanStartDate)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__contractShortCode'), contractShortCode)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__Contract Description'), contractDescription)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__paymentTermsId'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Payment Terms Code', [('paymentTermCode') : paymentTermCode]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__currencyCode'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Currency', [('currency') : currency]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__priceBasis'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Price Basis', [('priceBasis') : priceBasis]))

WebUI.click(findTestObject('Scenario 12/SC12_TC007/svg_shippingRouteCode'))

WebUI.click(findTestObject('Scenario 12/SC12_TC007/li_shippingRouteCode', [('shippingRouteCode') : shippingRouteCode]))

WebUI.click(findTestObject('Scenario 12/SC12_TC007/svg_spotShippingRouteCode', [('spotShippingRouteCode') : spotShippingRouteCode]))

WebUI.click(findTestObject('Scenario 12/SC12_TC007/li_spotShippingRouteCode', [('spotShippingRouteCode') : spotShippingRouteCode]))

WebUI.click(findTestObject('Scenario 12/SC12_TC007/input__contractShortCode', [('spotShippingRouteCode') : spotShippingRouteCode]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__customsFlag'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Customs Flag', [('customsFlag') : customsFlag]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__expAgencyUid'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_expAgencyUidOption', [('expCustomParty') : exportCustomsParty]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__impAgencyUid'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_impAgencyUidOption', [('impAgencyUid') : importCustomsParty]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__printHSCodeFlag'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_printHSCodeFlagOption', [('printHSCodeFlag') : printHSCodeFlag]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Complete'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Confirm'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_Confirm Working.The operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Contract Route List/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Contract Route List/li_Contract Route List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Contract Route List/input_Contract Route List'), routeDescription)

WebUI.delay(1)

ContractRouteCode = WebUI.getText(findTestObject('Scenario 13/S13_TC035/Contract Route List/p_Contract Route Code', [('routeDesc') : routeDescription]))

CustomKeywords.'copyToExcel.exel'(ContractRouteCode, 1, 19, 'Excel Files\\Scenario 12', 'S12_TestCases_Data.xlsx', 'TC7')

WebUI.closeBrowser()

