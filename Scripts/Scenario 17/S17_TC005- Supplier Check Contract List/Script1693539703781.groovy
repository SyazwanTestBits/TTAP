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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC032/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 17/S17_TC005/li_Contract List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/input_Filter_Received Request List'), contractNo)

not_run: WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Edit'))

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/header_2Parts List InformationParts List Information'), 
    0)

//-------------looping for verify part detail------------------------------------
WebUI.click(findTestObject('Scenario 13/S13_TC038 n 39-Check Contract Route/view detail page/div_Parts No header in part detail list'))

int colindex = 0

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    colindex = 1

    for (String colname : columnname) {
        String valuecol = findTestData('Scenario 17/S17_TC005- Contract Parts').getValue(colname, rowl)

        WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC029/p_partsDetail', [('lcol') : colindex, ('lrow') : rowl]), 
            valuecol)

        //println("Row: $rowl, Column: $coll")
        //println("Expected Value: $valuecol")
        //println("Actual Value: $actualValue")
        println("$actualValue same as expected -->  $valuecol")

        //Log actual and expected results
        KeywordUtil.logInfo("Actual: $actualValue and Expected: $valuecol")

        colindex = (colindex + 1)
    }
}

WebUI.scrollToElement(findTestObject('Scenario 17/S17_TC005/svg_Download Icon'), 0)

WebUI.click(findTestObject('Scenario 17/S17_TC005/svg_Download Icon'))

WebUI.click(findTestObject('Scenario 17/S17_TC005/li_Download Contract Parts'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC005/div_Download Contract Parts Master.The operation was successful'), 
    0)

WebUI.delay(2)

'Check part no. removed= inactive\r\n'
WebUI.verifyElementText(findTestObject('Scenario 17/S17_TC002/p_Verify Active Flag', [('partNo') : partNo]), activeFlag)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'util.compareTestData.compareExcelFiles_ExcelFormulaCompatible'(latestFilePath, expectedContractParts, 8, 
    12, 5, 34)

WebUI.closeBrowser()

