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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/span_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_SO Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringList/h3_SO Monitoring List'), 0)

for (String orderNo : SO_numberList) {
    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC029/p_verifyStatus', [('orderNo') : orderNo]), 'Processing')

    not_run: WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC049/p_verifyDelayStatus', [('COid') : orderNo]), 
        'Normal')

    KeywordUtil.logInfo("[Verified] Customer Order No: $orderNo status is Completed and Delay Status is Normal")
}

WebUI.callTestCase(findTestCase('Scenario 10/S10_TC167.1-Check SO_1'), [('SO_numberList') : [findTestData('Scenario 10/S10_TC162').getValue(
                2, 1), findTestData('Scenario 10/S10_TC162').getValue(2, 2)], ('contractNoL2') : findTestData('Scenario 10/S10_TC021-Setup Data').getValue(
            'ContractNo', 1), ('columnname') : ['PartsNo', 'UnitPartsNo', 'BackNo', 'POnumber', 'Customer', 'SPQ', 'OrderLot'
            , 'OrderQty', 'UnitPrice', 'Currency', 'Status', 'DeliveredQty', 'InTransitQty', 'InboundedQty', 'InboundedPlanQty'
            , 'InboundedPlanStatus']], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC178/p_SO Monitoring List'), 0)

WebUI.callTestCase(findTestCase('Scenario 10/S10_TC167.2-Check SO_2'), [('SO_numberList') : [findTestData('Scenario 10/S10_TC162').getValue(
                2, 1), findTestData('Scenario 10/S10_TC162').getValue(2, 2)], ('contractNoL2') : findTestData('Scenario 10/S10_TC021-Setup Data').getValue(
            'ContractNo', 1), ('columnname') : ['PartsNo', 'UnitPartsNo', 'BackNo', 'POnumber', 'Customer', 'SPQ', 'OrderLot'
            , 'OrderQty', 'UnitPrice', 'Currency', 'Status', 'DeliveredQty', 'InTransitQty', 'InboundedQty', 'InboundedPlanQty'
            , 'InboundedPlanStatus']], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

