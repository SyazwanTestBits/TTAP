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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.ADMIN_USERNAME
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.ADMIN_COMPANY], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Privilege'))

WebUI.click(findTestObject('Navbar_Brivge/PrivilegeMenu_Brivge/li_Role List'))

WebUI.waitForElementPresent(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/h3_Role List'), 0)

WebUI.click(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/button_CreateNewRole'))

WebUI.waitForElementPresent(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/Page_CreateNewRole/h3_Create New Role'), 0)

WebUI.setText(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/Page_CreateNewRole/input_RoleName'), RoleName)

WebUI.setText(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/Page_CreateNewRole/input_RoleNote'), RoleNote)

WebUI.click(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/Page_CreateNewRole/button_save'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SuccessfullyCreateNewRole'), 0)

WebUI.takeElementScreenshot(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SuccessfullyCreateNewRole'))

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 13/S13_TC008_Cmn_1-Verify Role Details'), [('RoleName') : RoleName
        , ('RoleNote') : RoleNote, ('RoleStatus') : RoleStatus], FailureHandling.STOP_ON_FAILURE)

