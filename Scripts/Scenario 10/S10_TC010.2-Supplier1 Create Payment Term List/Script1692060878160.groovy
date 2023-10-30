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
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : 'MY-ELA-SUP'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC032/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC032/Page_Brivge/li_Payment Terms List'))

WebUI.click(findTestObject('Page_PaymentTerm/button_download1'))

WebUI.click(findTestObject('Page_PaymentTerm/li_Download2'))

WebUI.verifyElementText(findTestObject('Page_PaymentTerm/p_The operation was successful'), 'The operation was successful.')

latestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

def lastrowstring = CustomKeywords.'getLastRow.lastRow'(latestPath, 'MLF110')

int lastrow = lastrowstring.toInteger()

for (int row = 1; row <= testData.getRowNumbers(); row++) {
    for (def pair : paymentTermFileColumns) {
        def columnName = pair.key

        def columnIndex = pair.value

        String dataValue = testData.getValue(columnName, row)

        CustomKeywords.'copyToExcel.exel2'(dataValue, row + lastrow, columnIndex, latestPath, 'MLF110')
    }
}

WebUI.click(findTestObject('Scenario 13/S13_TC032/Page_Brivge/button_Upload'))

not_run: AbsolutePath = CustomKeywords.'ManageFiles.getFileAbsolutePath'(latestPath)

'Upload the file that has been edited.'
CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 13/S13_TC002/Page_Brivge/li_Upload'), latestPath)

'Verify the success message.'
WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC002/Page_Brivge/div_Upload Portmaster.The operation was successful - Copy'), 
    0)

WebUI.refresh()

WebUI.waitForElementPresent(findTestObject('Page_PaymentTerm/h3_Payment Terms List'), 0)

not_run: for (int row = 1; row <= testData.getRowNumbers(); row++) {
    def paymentTerm = testData.getValue('PaymentTerm', row)

    WebUI.verifyElementPresent(findTestObject('Page_PaymentTerm/div_payementTermCode', [('paymentTermCode') : paymentTerm]), 
        0)
}

WebUI.closeBrowser()

