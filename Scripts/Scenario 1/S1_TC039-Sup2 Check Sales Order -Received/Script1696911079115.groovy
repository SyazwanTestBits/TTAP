import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_SUP2], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/span_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/li_SO Monitoring List'))

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), orderNo)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC039_TC040/p_verifyContractNo_firstRow'), contractNo)

KeywordUtil.logInfo("Verified Contract No: $contractNo")

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC039_TC040/p_verifyStatus', [('contractNo') : contractNo]), 'Received')

KeywordUtil.logInfo("Verified Status: Received")

WebUI.click(findTestObject('Page_SO_MonitoringList/button_Dt_Detail', [('contractNo') : contractNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderNo'), 
    'value', orderNo, 0)

KeywordUtil.logInfo("Verified Order No: $orderNo")

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderType'), 
    'value', 'Regular', 0)

KeywordUtil.logInfo("Verified Order Type: Regular")

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_status'), 
    'value', 'Received', 0)

KeywordUtil.logInfo("Verified Status: Received")

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_contractNo'), 
    'value', contractNo, 0)

KeywordUtil.logInfo("Verified Contract No: $contractNo")

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderFrequency'), 
    'value', 'Weekly', 0)

KeywordUtil.logInfo("Verified Order Frequency: Weekly")

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_buyer'), 
    'value', GlobalVariable.S1_BAF_BU2, 0)

KeywordUtil.logInfo("Verified Buyer: $GlobalVariable.S1_BAF_BU2")

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_receiver'), 
    'value', GlobalVariable.S1_BAF_DC2, 0)

KeywordUtil.logInfo("Verified Receiver: $GlobalVariable.S1_BAF_DC2")

WebUI.closeBrowser()

