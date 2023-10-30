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
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 12/SC12_TC073/button_Management Tool'))

WebUI.click(findTestObject('Scenario 12/SC12_TC073/li_Download Overall Visualisation'))

WebUI.waitForElementPresent(findTestObject('Scenario 12/SC12_TC073/h3_Download Overall Visualisation'), 0)

WebUI.click(findTestObject('Scenario 12/SC12_TC073/input__customerId'))

WebUI.click(findTestObject('Scenario 12/SC12_TC073/li_customerCode', [('customerCode') : customerCode]))

WebUI.click(findTestObject('Scenario 12/SC12_TC073/input_customerCode'))

WebUI.click(findTestObject('Scenario 12/SC12_TC073/li_contractRouteCode', [('contractRouteCode') : contactRouteCode]))

WebUI.click(findTestObject('Scenario 12/SC12_TC073/Page_Download Overall Visualisation - Brivge/input__withPoNoFlag'))

WebUI.click(findTestObject('Scenario 12/SC12_TC073/Page_Download Overall Visualisation - Brivge/input_With PO No_withInvoiceNoFlag'))

WebUI.click(findTestObject('Scenario 12/SC12_TC073/Page_Download Overall Visualisation - Brivge/Page_Download Overall Visualisation - Brivge/button_Download'))

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC038 n 39-Check Contract Route/p_The operation was successful'), 
    'The operation was successful.')

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedOV, downloadedFile, 1, [6, 7, 8, 9, 10, 11], [1, 2, 3, 4, 5, 6
        , 7, 8, 9])

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

