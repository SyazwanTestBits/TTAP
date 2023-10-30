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
import java.text.SimpleDateFormat
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Scenario 12/SC12_TC035/li_ChangeCancel Request List'))

WebUI.waitForElementPresent(findTestObject('Scenario 12/SC12_TC035/h3_ChangeCancel Request List'), 0)

'Verify Order Change with Status = New\r\n'
WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC034/p_Order Change', [('requestNo') : requestNo]), 'New')

WebUI.click(findTestObject('Scenario 12/SC12_TC019/p_detailButton', [('requestNo') : requestNo]))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC035/h3_ChangeCancel Request Detail'), 0)

WebUI.scrollToElement(findTestObject('Scenario 12/SC12_TC034/p_Parts No sorting'), 0)

WebUI.click(findTestObject('Scenario 12/SC12_TC034/p_Parts No sorting'))

inputDate = findTestData('Scenario 10/S10_TC092-Supplier Date Change').getValue('Outbound_Date3', 1)

// Parse the input date using SimpleDateFormat
SimpleDateFormat inputFormat = new SimpleDateFormat('dd MMM yyyy')

Date date = inputFormat.parse(inputDate)

// Create a new SimpleDateFormat for the desired output format
SimpleDateFormat outputFormat = new SimpleDateFormat('MMM d, yyyy')

// Format the date to "Dec 21, 2023"
String formattedDate = outputFormat.format(date)

// Print the formatted date
println('Formatted Date: ' + formattedDate)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC094/p_Outbound_Date3_Supplier'), formattedDate)

int numberrowtd = findTestData('Scenario 10/S10_TC094-BU Check Change').getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    int coll = 1

    for (String col : columnname) {
        String valuecol = findTestData('Scenario 10/S10_TC094-BU Check Change').getValue(col, rowl)

        WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC029/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]), 
            valuecol)

        String actualValue = WebUI.getText(findTestObject('Scenario 12/SC12_TC029/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]))

        //println("Row: $rowl, Column: $coll")
        //println("Expected Value: $valuecol")
        //println("Actual Value: $actualValue")
        println("$actualValue same as expected -->  $valuecol")

        coll = (coll + 1)
    }
    
    not_run: WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC029/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]), 
        valuecol)
}

//
WebUI.closeBrowser()

