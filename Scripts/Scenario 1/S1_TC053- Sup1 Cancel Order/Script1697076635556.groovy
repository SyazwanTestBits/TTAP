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
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_SUP1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Scenario 12/SC12_TC033/li_Place Supplier Change'))

WebUI.setText(findTestObject('Scenario 10/S10_TC083/input_Supplier Order Change_search'), salesOrderNo)

WebUI.click(findTestObject('Scenario 17/S17_TC034/div_Cancel_SupplierOrderChange', [('salesOrderNo') : salesOrderNo]))

WebUI.takeFullPageScreenshot()

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC034/Page_Supplier Order ChangeCancel - Brivge/div_CancelAre you sure to do Cancel(c0001)CANCELCONFIRM'), 
    0)

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Scenario 17/S17_TC034/Page_Supplier Order ChangeCancel - Brivge/span_CONFIRM'))

WebUI.takeFullPageScreenshot()

'change from Confirmed to Received'
WebUI.verifyElementText(findTestObject('Scenario 17/S17_TC034/p_verifyOrderStatus', [('salesOrderNo') : salesOrderNo]), 
    'Received')

WebUI.takeFullPageScreenshot()

WebUI.callTestCase(findTestCase('Scenario 1/S1_TC053.1-Get Request No'), [('contractRouteCode') : findTestData('Scenario 1/S1_TC002-BU1 to Customer Contract').getValue('ContractRouteCode', 1)], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

