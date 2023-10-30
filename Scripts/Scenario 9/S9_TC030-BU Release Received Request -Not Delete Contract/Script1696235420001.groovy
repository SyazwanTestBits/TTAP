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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/li_Received Request List'))

WebUI.waitForElementPresent(findTestObject('Page_ReceivedReqList/h3_Received Request List'), 0)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/input_Filter_Received Request List'), requestNo)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/p_RequestNo_View'), requestNo)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_RequestFrom'), GlobalVariable.S9_BAF_CUS)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_Status'), 'Completed')

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_View'))

WebUI.waitForElementPresent(findTestObject('Page_ReceivedReqList/Page_View Received Request Add New Part - Brivge/h3_View Received Request Add New Part'), 
    0)

WebUI.click(findTestObject('Page_ReceivedReqList/Page_View Received Request Add New Part - Brivge/button_Release'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page_ReceivedReqList/Page_View Received Request Add New Part - Brivge/h5_Select whether to delete the corresponding contract'), 
    0)

WebUI.click(findTestObject('Page_ReceivedReqList/Page_View Received Request Add New Part - Brivge/button_Confirm'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_AreYouSureToDo_Release'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Scenario 9/SC9_TC007/div_Release Working.The operation was successful'), 0)

WebUI.waitForElementPresent(findTestObject('Page_ReceivedReqList/h3_Received Request List'), 0)

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/input_Filter_Received Request List'), requestNo)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/p_RequestNo_View'), requestNo)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC035/Page_Brivge/div_Status'), 'Submitted')

WebUI.closeBrowser()

