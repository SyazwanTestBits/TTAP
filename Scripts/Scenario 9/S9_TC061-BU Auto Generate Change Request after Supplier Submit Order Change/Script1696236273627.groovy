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

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_ChangeCancel Request List'))

WebUI.waitForElementPresent(findTestObject('Page_ChangeCancelReqList/h3_ChangeCancel Request List'), 0)

WebUI.setText(findTestObject('Page_ChangeCancelReqList/input_ChangeCancelReqList_Search'), supChangeRequestNo)

WebUI.verifyElementText(findTestObject('Page_ChangeCancelReqList/div_Dt_ChgReqNo_RequestFrom', [('changeReqNo') : supChangeRequestNo]), 
    GlobalVariable.S9_BAF_SUP)

WebUI.verifyElementText(findTestObject('Page_ChangeCancelReqList/div_Dt_ChgReqNo_RequestType', [('changeReqNo') : supChangeRequestNo]), 
    'Order Change')

WebUI.verifyElementText(findTestObject('Page_ChangeCancelReqList/div_Dt_ChgReqNo_Status', [('changeReqNo') : supChangeRequestNo]), 
    'New')

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Page_ChangeCancelReqList/button_Dt_ChgReqNo_Detail', [('changeReqNo') : supChangeRequestNo]))

WebUI.waitForElementPresent(findTestObject('Page_ChangeCancelReqList/Page_ChgCancelReqDetail/h3_ChangeCancel Request Detail'), 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Page_ChangeCancelReqList/Page_ChgCancelReqDetail/input_RequestNo'), 'value', 
    supChangeRequestNo, 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_ChangeCancelReqList/Page_ChgCancelReqDetail/input_RequestStatus'), 
    'value', 'New', 0)

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

