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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_DC], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 9/SC9_TC083/button_Management Tool'))

WebUI.click(findTestObject('Scenario 9/SC9_TC084/Page_Parts Inventory List(By Package) - Brivge/li_Parts Inventory List(By Package)'))

WebUI.waitForElementPresent(findTestObject('Scenario 9/SC9_TC084/Page_Parts Inventory List(By Package) - Brivge/h3_Parts Inventory List(By Package)'), 
    0)

WebUI.setText(findTestObject('Scenario 9/SC9_TC083/input_Parts Inventory List(By Parts)'), 'scenario9002')

WebUI.click(findTestObject('Scenario 9/SC9_TC083/button_download'))

WebUI.click(findTestObject('Scenario 9/SC9_TC083/li_Download'))

WebUI.verifyElementPresent(findTestObject('Scenario 9/SC9_TC084/Page_Parts Inventory List(By Package) - Brivge/div_Download Inventory By Package.The operation was successful'), 
    0)

latestFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

int colindex = 0

int numberrowtd = findTestData('Scenario 9/S9_TC084-DC Parts Inventory By Package').getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    colindex = 1

    for (String colname : columnname) {
        String valuecol = findTestData('Scenario 9/S9_TC084-DC Parts Inventory By Package').getValue(colname, rowl)

        WebUI.verifyElementText(findTestObject('Scenario 9/SC9_TC083/p_verifyDetails', [('lcol') : colindex, ('lrow') : rowl]), 
            valuecol)

        colindex = (colindex + 1)
    }
}

'need data flushing to run'
WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 9/S9_Cmn3_Compare Test Data'), [('LatestPath') : latestFile, ('expectationExcelPath') : 'Excel Files\\Scenario 9\\Expected Data\\TC84\\InventoryByPackage.xlsx'
        , ('startRows') : 5, ('endRows') : 5, ('startCols') : 2, ('endCols') : 13, ('NumberOfNoMatch') : 0], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

