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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/button_Master'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/li_Contract Route List'))

WebUI.setText(findTestObject('Scenario 17/S17_TC006/input_Search'), description)

WebUI.delay(2)

CRouteCode = WebUI.getText(findTestObject('Scenario 17/S17_TC006/p_getContractRouteCode', [('description') : description]))

println(CRouteCode)

'copy customer order no to excel\r\n'
CustomKeywords.'copyToExcel.exel'(CRouteCode, 1, 0, filePath, fileName, sheetName)

WebUI.delay(2)

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/p_first row contract route list'), 
    CRouteCode)

WebUI.verifyElementText(findTestObject('Scenario 9/SC9_TC003/p_verifyStatus', [('contractRouteCode') : CRouteCode]), 'Build Completed')

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/input_tick first row contract route list'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/button-click view contract detail'))

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/h3_header view or edit Contract Route Detail'), 
    'Contract Route Detail')

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/header_2Parts List InformationParts List Information'), 
    0)

//-------------looping for verify part detail------------------------------------
WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/div_Parts No header in part detail list'))

int colindex = 0

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    colindex = 1

    for (String colname : columnname) {
        String valuecol = findTestData('Scenario 17/S17_TC006 - Part List').getValue(colname, rowl)

        String actualValue = WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/p list/p_part detail list', 
                [('lcol') : colindex, ('lrow') : rowl]), valuecol)

        //Log actual and expected results
        KeywordUtil.logInfo("Actual: $actualValue and Expected: $valuecol")

        colindex = (colindex + 1)

        println("$actualValue same as expected -->  $valuecol")
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

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 17/S17_Cmn3_Compare Test Data'), [('LatestPath') : latestFilePath
        , ('expectationExcelPath') : expectedContractRouteParts, ('startRows') : 6, ('endRows') : 9, ('startCols') : 6, ('endCols') : 13
        , ('NumberOfNoMatch') : NumberOfNoMatch], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/button_download in view page'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/li_Download Route Detail By Overview'))

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/h6_Download Contract Route Overview By selected'), 
    'Download Contract Route Overview By selected.')

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/p_The operation was successful'), 
    'The operation was successful.')

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedContractRoutePartsOverview, latestFilePath, 4, [6, 7, 8, 9], 
    [3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14])

WebUI.closeBrowser()

