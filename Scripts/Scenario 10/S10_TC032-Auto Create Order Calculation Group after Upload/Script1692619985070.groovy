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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 10/S10_TC024/button_Stock Management Tool'))

WebUI.click(findTestObject('Navbar_Brivge/StockMgmtToolMenu_Brivge/li_Order Calculation List'))

WebUI.waitForElementPresent(findTestObject('Page_OrderCalcList/h3_Order Calculation List'), 0)

for (def index : (1..datafile.getRowNumbers())) {
    def orderCalcGroupNo = datafile.getValue('OrderCalculationGroupingNo', index)

    def buyerCode = datafile.getValue('BuyerCode', index)

    WebUI.setText(findTestObject('Page_OrderCalcList/input_Search_OrderCalculationList'), orderCalcGroupNo)

    WebUI.verifyElementText(findTestObject('Page_OrderCalcList/tr1_td_OrderCalcGroupNo'), orderCalcGroupNo)

    WebUI.verifyElementText(findTestObject('Page_OrderCalcList/tr1_td_OrderCalcGroupCustCode'), buyerCode)

    WebUI.verifyElementText(findTestObject('Page_OrderCalcList/tr1_td_OrderCalcGroupStatus'), 'PENDING CALCULATION')

    WebUI.takeFullPageScreenshot()

    CustomKeywords.'commonUtils.clearElementText'(findTestObject('Page_OrderCalcList/input_Search_OrderCalculationList'))
}

WebUI.closeBrowser()

