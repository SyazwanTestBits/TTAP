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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Received Request List'))

WebUI.click(findTestObject('Scenario 17/S17_TC002/p_edit', [('description') : description]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Next'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Download'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Download Part Form'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_The operation was successful'), 0)

downloadedExcel = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 17/S17_Cmn1-Write Info into Form Excel'), [('datafile') : datafile_contractPartsInfo
        , ('fileColumns') : fileColumns, ('startRowFormMinusOne') : startRowFormMinusOne, ('downloadedFormPath') : downloadedExcel
        , ('downloadedFormSheetname') : downloadedFormSheetname], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Upload Master'), downloadedExcel)

WebUI.delay(3)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_Upload_The operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Next'))

WebUI.click(findTestObject('Scenario 17/S17_TC002/input__businessType'))

WebUI.click(findTestObject('Scenario 17/S17_TC002/li_businessType', [('businessType') : businessType]))

WebUI.click(findTestObject('Scenario 17/S17_TC002/input__orderFrequency'))

WebUI.click(findTestObject('Scenario 17/S17_TC002/li_orderFrequency', [('orderFrequency') : orderFrequency]))

WebUI.click(findTestObject('Scenario 9/SC9_TC004/input__deliveryToUid'))

WebUI.click(findTestObject('Scenario 9/SC9_TC004/li_deliveryTo', [('deliveryTo') : deliveryTo]))

WebUI.setText(findTestObject('Scenario 17/S17_TC002/input__forecastNumber'), forecastNumber)

WebUI.setText(findTestObject('Scenario 17/S17_TC002/input__targetLeadTime'), targetLeadTime)

WebUI.setText(findTestObject('Scenario 9/SC9_TC004/input__confirmOrderLeadtime'), confirmOrderLeadTime)

WebUI.setText(findTestObject('Scenario 9/SC9_TC004/input__leadtime'), leadtime)

WebUI.setText(findTestObject('Scenario 9/SC9_TC004/input__deliveryPlanStartDate'), deliveryPlanStartDate)

WebUI.setText(findTestObject('Scenario 9/SC9_TC004/input__contractShortCode'), contractShortCode)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__paymentTermsId'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Payment Terms Code', [('paymentTermCode') : paymentTermCode]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__currencyCode'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Currency', [('currency') : currency]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__priceBasis'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Price Basis', [('priceBasis') : priceBasis]))

WebUI.click(findTestObject('Scenario 9/SC9_TC002/input_shippingRouteCode'))

WebUI.click(findTestObject('Scenario 9/SC9_TC002/li_shippingRouteCode', [('shippingRouteCode') : shippingRouteCode]))

WebUI.click(findTestObject('Scenario 17/S17_TC002/input_autoDiFlag'))

WebUI.click(findTestObject('Scenario 17/S17_TC002/li_autoDiFlag', [('autoDIFlag') : autoDIFlag]))

WebUI.setText(findTestObject('Scenario 17/S17_TC002/input_routeDescription'), routeDescription)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__customsFlag'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Customs Flag', [('customsFlag') : customsFlag]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Complete'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Confirm'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_Confirm Working.The operation was successful'), 
    0)

WebUI.closeBrowser()

