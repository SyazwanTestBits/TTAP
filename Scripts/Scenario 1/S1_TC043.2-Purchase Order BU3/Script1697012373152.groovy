import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.click(findTestObject('Page_SO_MonitoringList/button_Dt_Detail', [('contractNo') : contractNoBU3]), FailureHandling.STOP_ON_FAILURE)

orderNo = (orderNoList[1])

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderNo'), 
    'value', orderNo, 0)

KeywordUtil.logInfo("Verified Order No: $orderNo")

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_orderType'), 
    'value', 'Regular', 0)

KeywordUtil.logInfo('Verified Order Type: Regular')

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_status'), 
    'value', 'Cancelled', 0)

KeywordUtil.logInfo('Verified Status: Cancelled')

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC039_TC040/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_contractNo'), 
    'value', contractNoBU3, 0)

KeywordUtil.logInfo("Verified Contract No: $contractNoBU3")

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

