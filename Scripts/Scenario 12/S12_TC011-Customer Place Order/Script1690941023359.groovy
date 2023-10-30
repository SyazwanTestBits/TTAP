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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectBuilder as TestObjectBuilder

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_Place Order'))

WebUI.waitForElementPresent(findTestObject('Page_RegularOrder/h3_Place Regular Order'), 0)

WebUI.callTestCase(findTestCase('Scenario 12/S12_TC011.1-Customer Place Order -Regular'), [('username') : GlobalVariable.CUST_USERNAME_USERF
        , ('password') : GlobalVariable.CUST_PWD_USERF, ('company') : GlobalVariable.CUST_COMPANY_USERF, ('contractRouteNo') : findTestData('Scenario 12/SC12_TC007-Supplier to BU Contract').getValue('ContractRouteCode', 1)
        , ('contractNo') : findTestData('Scenario 12/SC12_TC004-BU to Customer Contract').getValue('ContractNo', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Scenario 12/S12_TC011.2-Customer Place Order -Spot'), [('contractRouteNo') : findTestData('Scenario 12/SC12_TC007-Supplier to BU Contract').getValue('ContractRouteCode', 1)
        , ('contractNo') : findTestData('Scenario 12/SC12_TC004-BU to Customer Contract').getValue('ContractNo', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Page_CO_MonitoringList/li_CO Monitoring List'))

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC011/p_verifyCOstatus', [('SOnumber') : RegularOrderNo]), 'Submitted')

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC011/p_verifyCOstatus', [('SOnumber') : SpotOrderNo]), 'Submitted')

WebUI.closeBrowser()

