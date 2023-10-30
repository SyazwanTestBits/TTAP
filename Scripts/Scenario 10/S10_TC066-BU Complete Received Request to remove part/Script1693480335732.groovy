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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_SYAZWAN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Master'))

WebUI.click(findTestObject('Navbar_Brivge/MasterMenu_Brivge/li_Received Request List'))

WebUI.waitForElementPresent(findTestObject('Page_ReceivedReqList/h3_Received Request List'), 0)

WebUI.setText(findTestObject('Page_ReceivedReqList/input_Search_ReceivedReqList'), requestDesc)

WebUI.click(findTestObject('Page_ReceivedReqList/td_ReceivedReqList_ReqEdit', [('requestNo') : requestDesc]))

WebUI.waitForElementPresent(findTestObject('Scenario 10/S10_TC066/h3_Received Request Remove Part'), 0)

not_run: reqID = WebUI.getAttribute(findTestObject('Scenario 10/S10_TC060/input_Reject_requestNo'), 'value')

WebUI.scrollToElement(findTestObject('Scenario 10/S10_TC060/h6_REQUEST PARTS DETAIL'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC060/div_Parts No'))

for (int i = 1; i <= testData.getRowNumbers(); i++) {
    String part = testData.getValue('Part No', i)

    for (def pair : columnname) {
        def columnName = pair.key

        def columnIndex = pair.value

        WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC060/div_part detail-dt', [('row') : i, ('col') : columnIndex]), 
            part)
    }
}

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC060/button_Request-Complete'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC060/button_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.waitForElementPresent(findTestObject('Page_ReceivedReqList/h3_Received Request List'), 0)

WebUI.setText(findTestObject('Page_ReceivedReqList/input_Search_ReceivedReqList'), requestDesc)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC060/div_recceived-dt-status'), 'Completed')

WebUI.closeBrowser()

