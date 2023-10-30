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
		, ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : admin_company],
	FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Privilege'))

WebUI.click(findTestObject('Navbar_Brivge/PrivilegeMenu_Brivge/li_User List'))

WebUI.waitForElementPresent(findTestObject('Page_UserList/h3_User List'), 0, FailureHandling.OPTIONAL)

WebUI.setText(findTestObject('Page_UserList/input_SearchUserList'), username)

WebUI.verifyElementPresent(findTestObject('Page_UserList/p_Dt_UserList_Username', [('username') : username]), 0, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Page_UserList/p_Dt_UserList_Status', [('username') : username]), 'Active', FailureHandling.CONTINUE_ON_FAILURE)

WebUI.check(findTestObject('Page_UserList/checkbox_Dt_UserList_Checkbox', [('username') : username]))

WebUI.click(findTestObject('Page_UserList/button_Dt_UserList_Edit', [('username') : username]))

WebUI.waitForElementPresent(findTestObject('Page_UserList/Page_EditUserDetail/h3_Edit User Detail'), 0, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Page_UserList/Page_EditUserDetail/input_Status'))

WebUI.click(findTestObject('Page_UserList/Page_EditUserDetail/li_StatusOption', [('status') : status]))

WebUI.click(findTestObject('Page_UserList/Page_EditUserDetail/button_Save'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SuccessfullyUpdateUser'), 0, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.takeFullPageScreenshot()

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 13/S13_TC017_Cmn_1-Verify User Status Inactive'), [('username') : username],
	FailureHandling.STOP_ON_FAILURE)

