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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_SO Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_SO_MonitoringList/h3_SO Monitoring List'), 0)

WebUI.verifyElementPresent(findTestObject('Page_SO_MonitoringList/div_Dt_ContractNo', [('contractNo') : contractNo]), 0)

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC014/p_verifySO', [('contractNo') : contractNo, ('orderType') : orderTypeRegular]), 
    regularSalesOrderNo)

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC014/p_verifySO', [('contractNo') : contractNo, ('orderType') : orderTypeSpot]), 
    spotSalesOrderNo)

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), contractNo)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring List - Brivge/p_checkbox', [('orderNo') : regularSalesOrderNo]))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_download'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC014/div_Download Sales Order by Excel.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedSOregular, downloadedFile, 1, [23, 24, 25, 26, 27, 28], [1, 2
        , 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21])

WebUI.click(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring List - Brivge/p_checkbox', [('orderNo') : regularSalesOrderNo]))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/Page_SO Monitoring List - Brivge/p_checkbox', [('orderNo') : spotSalesOrderNo]))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_download'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC014/div_Download Sales Order by Excel.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedSOspot, downloadedFile, 1, [23, 24, 25, 26, 27, 28], [1, 2, 3
        , 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18])

WebUI.callTestCase(findTestCase('Scenario 12/S12_TC014.1-Regular SO Details'), [('contractNo') : findTestData('Scenario 12/SC12_TC004-BU to Customer Contract').getValue('ContractNo', 1)
        , ('regularSalesOrderNo') : findTestData('Scenario 12/SC12_TC014-BU SO').getValue('RegularSalesOrderNo', 1), ('spotSalesOrderNo') : findTestData('Scenario 12/SC12_TC014-BU SO').getValue('SpotSalesOrderNo', 1)
        , ('paymentTermsDesc') : findTestData('Scenario 12/SC12_TC003.1-BU Create Payment Terms').getValue('Description', 1)], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Scenario 12/S12_TC014.2-Spot SO Details'), [('contractNo') : findTestData('Scenario 12/SC12_TC004-BU to Customer Contract').getValue('ContractNo', 1)
        , ('regularSalesOrderNo') : findTestData('Scenario 12/SC12_TC014-BU SO').getValue('RegularSalesOrderNo', 1), ('spotSalesOrderNo') : findTestData('Scenario 12/SC12_TC014-BU SO').getValue('SpotSalesOrderNo', 1)
        , ('paymentTermsDesc') : findTestData('Scenario 12/SC12_TC003.1-BU Create Payment Terms').getValue('Description', 1)], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Scenario 12/S12_TC014.3-Forecast Details'), [('contractNo') : findTestData('Scenario 12/SC12_TC004-BU to Customer Contract').getValue('ContractNo', 1)
        , ('regularSalesOrderNo') : findTestData('Scenario 12/SC12_TC014-BU SO').getValue('RegularSalesOrderNo', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

