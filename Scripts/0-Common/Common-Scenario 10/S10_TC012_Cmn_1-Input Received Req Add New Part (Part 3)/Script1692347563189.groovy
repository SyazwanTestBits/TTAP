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

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__deliveryToUid'))

WebUI.click(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/li_deliveryToUidOption', [('deliveryToUidOption') : deliveryTo]))

WebUI.setText(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/input__confirmOrderLeadtime'), confirmOrderLeadtime)

WebUI.setText(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/input__leadtime'), leadTime)

WebUI.setText(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/input__deliveryPlanStartDate'), deliveryPlanStartDate)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__contractShortCode'), contractShortCode)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__Contract Description'), contractDescription)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__paymentTermsId'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Payment Terms Code', [('paymentTermCode') : paymentTermCode]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__currencyCode'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Currency', [('currency') : currency]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__priceBasis'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Price Basis', [('priceBasis') : priceBasis]))

WebUI.verifyElementAttributeValue(findTestObject('Page_ReceivedReqList/Page_ReceivedReq_AddNewPart/input_ADD DISTRIBUTION CENTER_receiverUid'), 
    'value', receiveDc, 0)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input_Shipping Route Code'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Shipping Route Code', [('shippingRouteCode') : shippingRouteCode]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__customsFlag'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Customs Flag', [('customsFlag') : customsFlag]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__expAgencyUid'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_expAgencyUidOption', [('expCustomParty') : exportCustomsParty]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__impAgencyUid'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_impAgencyUidOption', [('impAgencyUid') : importCustomsParty]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input__printHSCodeFlag'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_printHSCodeFlagOption', [('printHSCodeFlag') : HSCodeFlag]))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input_INCOTERMS INFO_incotermsCode'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/li_Incoterms Code', [('incotermsCode') : IncotermsCode]))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Received_Request_List/input_INCOTERMS INFO_incotermsPlace'), IncotermsPlace)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Complete'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_PromptMsg_AreYouSureToDoComplete'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

