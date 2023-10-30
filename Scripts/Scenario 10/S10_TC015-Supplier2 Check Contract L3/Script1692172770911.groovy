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
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : 'JP-YAZ-SUP'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/button_Master'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/li_Contract List'))

WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/download_CanEdit/button_Download_contract list - Supplier'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/li_Download Contract'))

'Verify Download Contract'
WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/h6_Download Contract Master By filters'), 
    'Download Contract Master By filters.')

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/p_The operation was successful'), 
    'The operation was successful.')

WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/download_CanEdit/button_Download_contract list - Supplier'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/li_Download Contract Parts'))

'Verify Download Contract by Part'
WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/h6_Download Contract Parts Master By filters'), 
    'Download Contract Parts Master By filters.')

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/p_The operation was successful'), 
    'The operation was successful.')

WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/svg_close notification download contract'))

WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/svg_close notification download contract part'))

WebUI.setText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/input_Search Contract List'), contractNo)

contractNo = WebUI.getText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/p_first row contract list'), FailureHandling.STOP_ON_FAILURE)

not_run: CustomKeywords.'copyToExcel.exel'(contractNo, 1, 0, 'Excel Files\\Scenario 13', 'S13_TestCases_Data.xlsx', 'AutoGen')

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

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC008/View Contract/input__paymentTermsId'), 'value', 
    paymentTerm, 0)

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

WebUI.click(findTestObject('Scenario 10/S10_TC008/Page_View Contract - Brivge/p_Unit Parts No'))

for (int row = 1; row <= testDataDT.getRowNumbers(); row++) {
    int col = 1

    for (String colname : headerlist) {
        WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC008/View Contract/table/p_Dt_contents', [('row') : row
                    , ('col') : col]), testDataDT.getValue(colname, row))

        col = (col + 1)
    }
}

WebUI.back()

//---------------------------------------------------------------------Edit----------------------------------------------------------------------------------------------------------
'EDIT THROUGH MODIFY PAGE'
not_run: WebUI.setText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/input_Search Contract List'), contractNo)

not_run: WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/button_modify contract list'))

not_run: WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/h3_header page view and modify'), 
    'Modify Contract')

not_run: WebUI.click(findTestObject('Scenario 10/S10_TC008/View Contract/input__paymentTermsId'))

not_run: WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/View Contract/li_paymentTerm', [('paymentTermCode') : paymentTermEdit1]))

not_run: CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/View Contract/button_Save modify'), 
    0)

not_run: WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/View Contract/p_The operation was successful'), 
    0)

not_run: WebUI.back()

not_run: WebUI.setText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/input_Search Contract List'), contractNo)

not_run: WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/div_Dt_paymentTermCode'), paymentTermEdit1_Dt)

not_run: WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/input_Dt_tickAll'))

not_run: WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/download_CanEdit/button_Download_contract list - Supplier'))

not_run: WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/li_Download Contract'))

not_run: WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/h6_Download Contract Master By filters'), 
    'Download Contract Master By filters.')

not_run: WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/p_The operation was successful'), 
    'The operation was successful.')

'EDIT THROUGH EXCEL FILE'
not_run: latestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

not_run: def lastrowstring = CustomKeywords.'getLastRow.lastRow'(latestPath, 'Contract Master')

not_run: int lastrow = lastrowstring.toInteger()

//println(lastrow)
not_run: CustomKeywords.'copyToExcel.exel2'('MOD', lastrow, 0, latestPath, 'Contract Master')

not_run: CustomKeywords.'copyToExcel.exel2'(paymentTermEdit2, lastrow, 10, latestPath, 'Contract Master')

not_run: AbsolutePath = CustomKeywords.'ManageFiles.getFileAbsolutePath'(latestPath)

not_run: WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/download_CanEdit/button_Upload_contract list'))

not_run: CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/download_CanEdit/li_Download Contract'), 
    latestPath)

not_run: WebUI.refresh()

not_run: WebUI.setText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/input_Search Contract List'), contractNo)

not_run: WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/div_Dt_paymentTermCode'), paymentTermEdit2)

WebUI.closeBrowser()

