import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_CO Monitoring List'))

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), orderNo)

WebUI.click(findTestObject('Scenario 10/S10_TC024/input_tick all'))

WebUI.click(findTestObject('Scenario 1/S1_TC072/Page_CO Monitoring List - Brivge/button_Download'))

WebUI.click(findTestObject('Scenario 1/S1_TC072/li_Download by Excel'))

WebUI.takeFullPageScreenshot()

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadCustOrderbyExcel_Success'), 0)

not_run: WebUI.click(findTestObject('Scenario 1/S1_TC068/svg_Download Purchase Order by Excel Cancel'))

WebUI.click(findTestObject('Scenario 1/S1_TC072/button_Detail'), FailureHandling.STOP_ON_FAILURE)

WebUI.doubleClick(findTestObject('Scenario 1/S1_TC068/button_Shipping Plan Info_step_2'))

WebUI.scrollToElement(findTestObject('Scenario 1/S1_TC072/div_Order Qty'), 0)

KeywordUtil.logInfo("Verified New Order Quantity: $expectedOrderQTY")

KeywordUtil.logInfo("Verified New Order Quantity (Expected): $expectedOrderQTY")

println(expectedOrderQTY)

actualOrderQTY = WebUI.getText(findTestObject('Scenario 1/S1_TC072/div_OrderQTY (Amount)'))

KeywordUtil.logInfo("Verified New Order Quantity (Actual): $actualOrderQTY")

println(actualOrderQTY)

WebUI.click(findTestObject('Scenario 1/S1_TC072/Page_CO Monitoring Detail - Brivge/div_Customer Parts No'))

for (int rowl = 1; rowl <= 6; rowl++) {
    int coll = 1

    for (String col : columnname) {
        String valuecol = testDataCheck.getValue(col, rowl)

        def actualValue = WebUI.getText(findTestObject('Scenario 10/S10_TC177/p_partCheck', [('lrow') : rowl, ('lcol') : coll]))

        KeywordUtil.logInfo("Expected value: $valuecol ; Actual value: $actualValue")

        WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC177/p_partCheck', [('lrow') : rowl, ('lcol') : coll]), 
            valuecol)

        coll = (coll + 1)
    }
}

WebUI.closeBrowser()

