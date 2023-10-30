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

WebUI.callTestCase(findTestCase('Scenario 12/S12_TC041.1-BU Check Purchase Order -Regular'), [('contractNo') : findTestData(
            'Scenario 12/SC12_TC007-Supplier to BU Contract').getValue('ContractNo', 1), ('regularPurchaseOrderNo') : findTestData(
            'Scenario 12/SC12_TC015-BU PO').getValue('RegularPurchaseOrderNo', 1), ('orderTypeRegular') : 'Regular', ('expectedPOregular') : 'Excel Files\\Scenario 12\\Expected Data\\TC41\\Expected Purchase Order Regular.xlsx'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.callTestCase(findTestCase('Scenario 12/S12_TC041.2-BU Check Sales Order -Regular'), [('contractNo') : findTestData('Scenario 12/SC12_TC004-BU to Customer Contract').getValue('ContractNo', 1)
        , ('regularSalesOrderNo') : findTestData('Scenario 12/SC12_TC014-BU SO').getValue('RegularSalesOrderNo', 1), ('orderTypeRegular') : 'Regular'
        , ('expectedSOregular') : 'Excel Files\\Scenario 12\\Expected Data\\TC41\\Expected Sales Order Regular.xlsx'], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

