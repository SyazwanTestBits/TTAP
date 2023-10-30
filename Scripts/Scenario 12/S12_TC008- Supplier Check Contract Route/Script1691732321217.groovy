import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_MARIA
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/li_Contract Route List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/input_search contract route link'), contractRouteCode)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/p_first row contract route list'), 
    contractRouteCode)

WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/button-click view contract detail'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/h3_header view or edit Contract Route Detail'), 
    'Contract Route Detail')

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC006/Page_Contract Route Detail - Brivge/input_Contract Header Detail Information_contractRouteNo'), 
    'value', contractRouteCode, 0)

KeywordUtil.logInfo("Verified Contract Route No: $contractRouteCode")

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC006/Page_Contract Route Detail - Brivge/input_Contract Header Detail Information_customer'), 
    'value', GlobalVariable.BAF_COMPANY_CUS, 0)

KeywordUtil.logInfo("Verified Customer: $GlobalVariable.BAF_COMPANY_CUS")

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC006/Page_Contract Route Detail - Brivge/input_Contract Header Detail Information_status'), 
    'value', 'Build Completed', 0)

KeywordUtil.logInfo('Verified Status: Build Completed')

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC006/Page_Contract Route Detail - Brivge/input_Contract Header Detail Information_description'), 
    'value', contractRouteDescription, 0)

KeywordUtil.logInfo("Verified Contract Route Description: $contractRouteDescription")

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/header_2Parts List InformationParts List Information'), 
    0)

//-----------------------------------------------looping for verify part details
WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/div_Parts No header in part detail list'))

int stringindex = 0

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    for (int coll = 1; coll <= 7; coll++) {
        stringindex = (coll - 1)

        String colname2 = columnname[stringindex]

        String valuecol = findTestData('Scenario 12/SC12_TC008- Supplier Check Contract Route').getValue(colname2, rowl)

        def actualValue = WebUI.getText(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/p list/p_part detail list', 
                [('lcol') : coll, ('lrow') : rowl]))

        KeywordUtil.logInfo("Expected value: $valuecol ; Actual value: $actualValue")

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/p list/p_part detail list', 
                [('lcol') : coll, ('lrow') : rowl]), valuecol)
    }
}

WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/button_download in view page'))

WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/li_Download Route Detail'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/h6_Download Contract Route Detail in view page'), 
    'Download Contract Route Detail By filters.')

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/p_The operation was successful'), 
    'The operation was successful.')

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedContractRouteParts, downloadedFile, 2, [5, 6, 7, 8, 9, 10], [
        0, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12])

WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/button_download in view page'))

WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/li_Download Route Detail By Overview'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/h6_Download Contract Route Overview By selected'), 
    'Download Contract Route Overview By selected.')

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/p_The operation was successful'), 
    'The operation was successful.')

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedContractRoutePartsOverview, downloadedFile, 4, [6, 7, 8, 9, 10
        , 11], [3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19])

WebUI.closeBrowser()

