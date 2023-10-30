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

WebUI.setText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/input_Search Contract List'), contractNo)

not_run: WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/button_modify contract list'))

WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/button_view contract list'))

'CHECK IF THIS PAGE CANNOT BE MODIFY'
WebUI.verifyElementNotPresent(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/h3_header modify page'), 0)

not_run: WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/svg_Terminate_close'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/h3_header page view and modify'), 'View Contract')

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__contractNo'), 'value', contractNo, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__sellerCode'), 'value', sellerid, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__consignee'), 'value', consignee, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__contractType'), 'value', contractType, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__buyerCode'), 'value', buyerid, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__accountee'), 'value', accounteeid, 
    0)

not_run: WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__businessType'), 'value', 
    businessType, 0)

not_run: WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__orderFrequency'), 
    'value', orderFrequency, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__deliveryTo'), 'value', deliveryTo, 
    0)

not_run: WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__forecastNum'), 'value', 
    forecastNum, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__confirmOrderLeadtime'), 'value', 
    confirmOrderLead, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__leadtime'), 'value', targetLeadTime, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/View Contract/input__deliveryPlanStartDate'), 
    'value', deliveryPlanDate, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__contractShortCode'), 'value', 
    contractShortCode, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__description'), 'value', view_description, 
    0)

not_run: WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__paymentTermsId'), 
    'value', '', 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__currency'), 'value', currency, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__priceBasis'), 'value', priceBasis, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__receiveDcId'), 'value', receiveDC, 
    0)

not_run: WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__shipperDcId'), 'value', 
    shipperDC, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__shippingRouteId'), 'value', 
    shippingRouteCode, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__customsFlag'), 'value', customFlaq, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__exportCParty'), 'value', exportCParty, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__importCParty'), 'value', importCParty, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__printHscodeFlag'), 'value', 
    hsCode, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__incotermsCode'), 'value', incotermsCode, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__incotermsPlace'), 'value', 
    incotermsPlace, 0)

WebUI.scrollToElement(findTestObject('Scenario 10/S10_TC008/View Contract/table/h_part list information'), 0)

for (int row = 1; row <= testDataDT.getRowNumbers(); row++) {
    int col = 1

    for (String colname : headerlist) {
        WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC008/View Contract/table/p_Dt_contents', [('row') : row
                    , ('col') : col]), testDataDT.getValue(colname, row))

        col = (col + 1)
    }
}

WebUI.back()

