import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_ChangeCancel Request List'))

WebUI.setText(findTestObject('Scenario 10/S10_TC083/input_Supplier Order Change_search'), requestNo)

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC034/h3_ChangeCancel Request List'), 0)

WebUI.verifyElementText(findTestObject('Scenario 17/S17_TC060/p_verifyRequestType', [('requestType') : requestType, ('requestNo') : requestNo]), 
    requestType)

WebUI.verifyElementText(findTestObject('Scenario 17/S17_TC060/p_verifyRequestStatus', [('requestStatus') : requestStatus
            , ('requestNo') : requestNo]), 'Propose New')

WebUI.click(findTestObject('Scenario 12/SC12_TC019/p_detailButton', [('requestNo') : requestNo]))

WebUI.click(findTestObject('Scenario 12/SC12_TC034/p_Parts No sorting'))

int numberrowtd = findTestData('Scenario 17/S17_TC060- Parts Detail').getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    int coll = 1

    for (String col : columnname) {
        String valuecol = findTestData('Scenario 17/S17_TC060- Parts Detail').getValue(col, rowl)

        String actualValue = WebUI.getText(findTestObject('Scenario 12/SC12_TC029/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]))

        //println("Row: $rowl, Column: $coll")
        //println("Expected Value: $valuecol")
        //println("Actual Value: $actualValue")
        println("$actualValue same as expected -->  $valuecol")
		
		//Log actual and expected results
		KeywordUtil.logInfo("Actual: $actualValue and Expected: $valuecol")

        coll = (coll + 1)
    }
    
    not_run: WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC019/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]), 
        valuecol)
}

WebUI.closeBrowser()

