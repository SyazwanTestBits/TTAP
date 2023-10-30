import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_BU1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Scenario 12/SC12_TC020/li_ChangeCancel Request List'))

WebUI.setText(findTestObject('Object Repository/Scenario 1/S1_TC031/input_textField'), requestNo)

WebUI.click(findTestObject('Scenario 1/S1_TC034/p_Supplier Code_sorting'))

for (int row = 1; row <= 2; row++) {
    WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_requestNo', [('row') : row]), requestNo)

    WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_requestFrom', [('row') : row]), 'PK-CUS-POC')

    WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_requestType', [('row') : row]), 'Cancel')

    WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_requestStatus', [('row') : row]), 'New')

    def supplierCode = WebUI.getText(findTestObject('Scenario 1/S1_TC034/p_supplierCode', [('row') : row]))

    // Use conditional statements to specify supplier codes based on row
    if (row == row_supplier1) {
        WebUI.verifyMatch(supplierCode, 'MY-ELA-SUP', true)
    } else if (row == row_supplier2) {
        WebUI.verifyMatch(supplierCode, 'CNTW-SUP-POC', true)
    }
}

WebUI.click(findTestObject('Scenario 1/S1_TC034/p_detailButton', [('row') : row_supplier1]))

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC034/input_Seller'), 'value', GlobalVariable.S1_BAF_BU3, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC034/input_Buyer'), 'value', GlobalVariable.S1_BAF_BU1, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC034/input_ContractNo'), 'value', BU3toBU1_contract, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC034/input_PO'), 'value', BU3toBU1_PO, 0)

formattedDate1 = CustomKeywords.'DateFormatter.formatDate'(oldInboundDate_1)

formattedDate2 = CustomKeywords.'DateFormatter.formatDate'(oldInboundDate_2)

CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Scenario 1/S1_TC034/p_Copyright  2021 BriVge. All rights reserved'), 
    0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 1/S1_TC034/p_PO Parts No_sorting'), 0)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_OldInboundDate_1'), formattedDate1)

KeywordUtil.logInfo("Old Inbound Date 1 is verified: $formattedDate1")

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_OldInboundDate_2'), formattedDate2)

KeywordUtil.logInfo("Old Inbound Date 2 is verified: $formattedDate2")

for (int rowl = 1; rowl <= 3; rowl++) {
    int coll = 1

    for (String col : columnname1) {
        String expectedValue = changeData1.getValue(col, rowl)

        def actualValue = WebUI.getText(findTestObject('Scenario 1/S1_TC034/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]))

        KeywordUtil.logInfo("Expected value: $expectedValue ; Actual value: $actualValue")

        WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]), 
            expectedValue)

        coll = (coll + 1)
    }
}

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 1/S1_TC034/p_ChangeCancel Request List'), 0)

WebUI.setText(findTestObject('Object Repository/Scenario 1/S1_TC031/input_textField'), requestNo)

WebUI.click(findTestObject('Scenario 1/S1_TC034/p_Supplier Code_sorting'))

WebUI.click(findTestObject('Scenario 1/S1_TC034/p_detailButton', [('row') : row_supplier2]))

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC034/input_Seller'), 'value', GlobalVariable.S1_BAF_BU2, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC034/input_Buyer'), 'value', GlobalVariable.S1_BAF_BU1, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC034/input_ContractNo'), 'value', BU2toBU1_contract, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC034/input_PO'), 'value', BU2toBU1_PO, 0)

CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Scenario 1/S1_TC034/p_Copyright  2021 BriVge. All rights reserved'), 
    0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 1/S1_TC034/p_PO Parts No_sorting'), 0)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_OldInboundDate_1'), formattedDate1)

KeywordUtil.logInfo("Old Inbound Date 1 is verified: $formattedDate1")

for (int rowl = 1; rowl <= 3; rowl++) {
    int coll = 1

    for (String col : columnname2) {
        String expectedValue = changeData2.getValue(col, rowl)

        def actualValue = WebUI.getText(findTestObject('Scenario 1/S1_TC034/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]))

        KeywordUtil.logInfo("Expected value: $expectedValue ; Actual value: $actualValue")

        WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]), 
            expectedValue)

        coll = (coll + 1)
    }
}

WebUI.closeBrowser()

