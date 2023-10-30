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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_SUP2], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Scenario 12/SC12_TC020/li_ChangeCancel Request List'))

WebUI.setText(findTestObject('Object Repository/Scenario 1/S1_TC031/input_textField'), requestNo)

WebUI.click(findTestObject('Scenario 1/S1_TC034/p_Supplier Code_sorting'))

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_requestNo', [('row') : row_supplier1]), requestNo)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_requestFrom', [('row') : row_supplier1]), GlobalVariable.S1_BAF_CUS)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_requestType', [('row') : row_supplier1]), 'Cancel')

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_requestStatus', [('row') : row_supplier1]), 'New')

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_supplierCode', [('row') : row_supplier1]), GlobalVariable.S1_BAF_SUP2)

WebUI.click(findTestObject('Scenario 1/S1_TC034/p_detailButton', [('row') : row_supplier1]))

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC034/input_Seller'), 'value', GlobalVariable.S1_BAF_SUP2, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC034/input_Buyer'), 'value', GlobalVariable.S1_BAF_BU2, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC034/input_ContractNo'), 'value', Sup2toBU2_contract, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC034/input_PO'), 'value', SUP2_SO, 0)

formattedDate1 = CustomKeywords.'DateFormatter.formatDate'(oldInboundDate_1)

CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Scenario 1/S1_TC034/p_Copyright  2021 BriVge. All rights reserved'), 
    0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 1/S1_TC034/p_Parts No_sorting'), 0)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC034/p_OldInboundDate_1'), formattedDate1)

KeywordUtil.logInfo("Old Inbound Date 1 is verified: $formattedDate1")

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

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 1/S1_TC037_TC038/button_Reject'), 0)

WebUI.setText(findTestObject('Scenario 1/S1_TC037_TC038/input_Input Reject Reason_remark'), 'S1_TC038-Supplier2 Reject Change')

WebUI.click(findTestObject('Scenario 1/S1_TC037_TC038/button_RejectReason'))

WebUI.click(findTestObject('Scenario 1/S1_TC037_TC038/button_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Scenario 1/S1_TC037_TC038/div_Reject.The operation was successful'), 0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 12/SC12_TC021/p_ChangeCancel Request List'), 
    0)

WebUI.setText(findTestObject('Object Repository/Scenario 1/S1_TC031/input_textField'), requestNo)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC037_TC038/p_status', [('requestNo') : requestNo]), 'Rejected')

WebUI.closeBrowser()

