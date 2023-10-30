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
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_SUPPLIER1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/button_Master'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/li_Contract Route List'))

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/input_search contract route link'), 
    contract_route_id)

not_run: WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/div_Dt_Updated Date'))

not_run: WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/div_Dt_Updated Date'))

not_run: contractRouteID = WebUI.getText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/p_first row contract route list'), 
    FailureHandling.STOP_ON_FAILURE)

not_run: CustomKeywords.'copyToExcel.exel'(contractRouteID, 1, 0, 'Excel Files\\Scenario 10', 'S10_TestCases_Data.xlsx', 
    'TC016')

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/input_tick first row contract route list'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/button_download'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/li_Download Route Detail By Overview'))

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/h6_Download Contract Route Overview By selected'), 
    'Download Contract Route Overview By selected.')

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/p_The operation was successful'), 
    'The operation was successful.')

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/button_download'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/li_Download Route Detail'))

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/h6_Download Contract Route Detail By selected'), 
    'Download Contract Route Detail By selected.')

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/p_The operation was successful'), 
    'The operation was successful.')

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/svg_close Download Contract Route Overview'))

WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/svg_close Download Contract Route Detail'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/button-click view contract detail'))

//----------------------------------Go to view contract route list page 
WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/h3_header view or edit Contract Route Detail'), 
    'Contract Route Detail')

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/input_Contract Header Detail Information_customer'), 
    'value', testData.getValue('Customer', 1), 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/input_Contract Header Detail Information_status'), 
    'value', testData.getValue('Status', 1), 0)

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/header_2Parts List InformationParts List Information'), 
    0)

//-------------looping for verify part detail------------------------------------
WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/div_Parts No header in part detail list'))

int colindex = 0

//int numberrowtd = findTestData('Scenario 13/S13_TC038').getRowNumbers()
for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    colindex = 1

    for (String colname : columnname) {
        String valuecol = testData.getValue(colname, rowl)

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/p list/p_part detail list', 
                [('lcol') : colindex, ('lrow') : rowl]), valuecol)

        colindex = (colindex + 1)
    }
}

//-----------------------------------------------------------------------------------------------------
WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/button_download in view page'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/li_Download Route Detail'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/h6_Download Contract Route Detail in view page'), 
    'Download Contract Route Detail By filters.')

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/p_The operation was successful'), 
    'The operation was successful.')

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/button_download in view page'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/li_Download Route Detail By Overview'))

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/h6_Download Contract Route Overview By selected'), 
    'Download Contract Route Overview By selected.')

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/p_The operation was successful'), 
    'The operation was successful.')

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.closeBrowser()

