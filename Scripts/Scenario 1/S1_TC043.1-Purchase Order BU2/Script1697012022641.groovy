import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.click(findTestObject('Page_SO_MonitoringList/button_Dt_Detail', [('contractNo') : contractNoBU2]), FailureHandling.STOP_ON_FAILURE)

orderNo = (orderNoList[0])

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderNo'), 
    'value', orderNo, 0)

KeywordUtil.logInfo("Verified Order No: $orderNo")

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderType'), 
    'value', 'Regular', 0)

KeywordUtil.logInfo('Verified Order Type: Regular')

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_status'), 
    'value', 'Received', 0)

KeywordUtil.logInfo('Verified Status: Received')

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_contractNo'), 
    'value', contractNoBU2, 0)

KeywordUtil.logInfo("Verified Contract No: $contractNoBU2")

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderFrequency'), 
    'value', 'Weekly', 0)

KeywordUtil.logInfo('Verified Order Frequency: Weekly')

CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Scenario 1/S1_TC034/p_Copyright  2021 BriVge. All rights reserved'), 
    0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC178/p_Parts No'), 0)

for (int rowl = 1; rowl <= 3; rowl++) {
    int coll = 1

    for (String col : columnname) {
        String valuecol = testDataCheck.getValue(col, rowl)

        def actualValue = WebUI.getText(findTestObject('Scenario 10/S10_TC177/p_partCheck', [('lrow') : rowl, ('lcol') : coll]))

        KeywordUtil.logInfo("Expected value: $valuecol ; Actual value: $actualValue")

        WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC177/p_partCheck', [('lrow') : rowl, ('lcol') : coll]), 
            valuecol)

        coll = (coll + 1)
    }
}

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 12/SC12_TC043/Page_PO Monitoring Detail - Brivge/p_PO Monitoring List'), 
    0)

