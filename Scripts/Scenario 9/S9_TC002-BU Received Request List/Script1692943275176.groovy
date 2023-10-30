import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Received Request List'))

'Search by Description from TC1'
WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/input_Filter_Received Request List'), description)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Edit'))

'turn on back button for debug'
not_run: WebUI.click(findTestObject('Scenario 12/SC12_TC004/button_Back'))

'turn on back button for debug'
not_run: WebUI.click(findTestObject('Scenario 12/SC12_TC004/button_Back'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Next'))

RequestNo = WebUI.getText(findTestObject('Scenario 12/SC12_TC004/p_RequestNo'))

KeywordUtil.logInfo("Request No is $RequestNo")

CustomKeywords.'copyToExcel.exel'(RequestNo, 1, 1, 'Excel Files\\Scenario 9', 'S9_TestCases_Data.xlsx', 'TC2-BU to Customer Contract')

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Download'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Download Part Form'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_The operation was successful'), 0)

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

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Next'), 0)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Complete'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Confirm'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_Confirm Working.The operation was successful'), 
    0)

WebUI.callTestCase(findTestCase('Scenario 9/S9_TC002.1-Get Request No'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Scenario 9/S9_TC002.2-Get Contract Route Code'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

