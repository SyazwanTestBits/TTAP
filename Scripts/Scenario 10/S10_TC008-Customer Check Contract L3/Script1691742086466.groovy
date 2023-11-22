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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_SYAZWAN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : 'MY-PNA-CUS'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/button_Master'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/li_Contract List'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/button_Download_contract list - Customer'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/li_Download Contract'))

'Verify Download Contract'
WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/h6_Download Contract Master By filters'), 
    'Download Contract Master By filters.')

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/p_The operation was successful'), 
    'The operation was successful.')

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/button_Download_contract list - Customer'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/li_Download Contract Parts'))

'Verify Download Contract by Part'
WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/h6_Download Contract Parts Master By filters'), 
    'Download Contract Parts Master By filters.')

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/p_The operation was successful'), 
    'The operation was successful.')

WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/svg_close notification download contract'))

WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/svg_close notification download contract part'))

WebUI.setText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/input_Search Contract List'), contractNo)

WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/button_modify contract list'))

'CHECK IF THIS PAGE CANNOT BE MODIFY'
WebUI.verifyElementNotPresent(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/h3_header modify page'), 0)

WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/svg_Terminate_close'))

WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/button_view contract list'))

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

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__businessType'), 'value', businessType, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__orderFrequency'), 'value', 
    orderFrequency, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__deliveryTo'), 'value', deliveryTo, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__forecastNum'), 'value', forecastNum, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__targetLeadtime'), 'value', 
    targetLeadTime, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__contractShortCode'), 'value', 
    contractShortCode, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__paymentTermsId'), 'value', 
    paymentTerm, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__currency'), 'value', currency, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__priceBasis'), 'value', priceBasis, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__shipperDcId'), 'value', shipperDC, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__shippingRouteId'), 'value', 
    shippingRouteCode, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__customsFlag'), 'value', customFlaq, 
    0)

WebUI.scrollToElement(findTestObject('Scenario 10/S10_TC008/View Contract/table/h_part list information'), 0)

for (int row = 1; row <= testDataDT.getRowNumbers(); row++) {
    int col = 1

    for (String colname : headerlist) {
        not_run: CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Scenario 10/S10_TC008/View Contract/table/p_Dt_contents', 
                [('row') : row, ('col') : col]), 0)

        expectdata = testDataDT.getValue(colname, row)

        'PLEASE ENABLE IF YOUR COMPUTER IS CHINESE LOCAL. IF NOT, DISABLE IT'
        not_run: if (colname == 'CurrentApplyDate') {
            expectdata = CustomKeywords.'DateConversionLocal.changeDateFormat_Chinese_Into_Chinese'(testDataDT.getValue(
                    colname, row), 'MMM d, yyyy', 'yyyy年MM月dd日')
        }
        
        WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC008/View Contract/table/p_Dt_contents', [('row') : row
                    , ('col') : col]), expectdata)

        col = (col + 1)
    }
}

WebUI.closeBrowser()

