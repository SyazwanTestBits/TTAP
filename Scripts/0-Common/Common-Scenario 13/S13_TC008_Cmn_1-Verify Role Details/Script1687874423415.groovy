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

WebUI.waitForElementPresent(findTestObject('Page_RoleList/Page_ViewRoleDetail/h3_View Role Detail'), 0, FailureHandling.CONTINUE_ON_FAILURE)

roleName = WebUI.getAttribute(findTestObject('Page_RoleList/Page_ViewRoleDetail/input_RoleNameDetail'), 'value', FailureHandling.STOP_ON_FAILURE)

roleNote = WebUI.getAttribute(findTestObject('Page_RoleList/Page_ViewRoleDetail/input_RoleNoteDetail'), 'value', FailureHandling.STOP_ON_FAILURE)

WebUI.verifyMatch(roleName, RoleName, false)

WebUI.verifyMatch(roleNote, RoleNote, false)

WebUI.click(findTestObject('Navbar_Brivge/button_Privilege'))

WebUI.click(findTestObject('Navbar_Brivge/PrivilegeMenu_Brivge/li_Role List'))

WebUI.waitForElementPresent(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/h3_Role List'), 0, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/input_SearchRole'), RoleName)

WebUI.waitForElementPresent(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/div_Dt_RoleName', [('roleName') : RoleName]), 0, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/div_Dt_RoleName', [('roleName') : RoleName]), RoleName, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/div_Dt_RoleNote', [('roleNote') : RoleNote]), RoleNote, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/div_Dt_RoleStatus', [('roleStatus') : RoleStatus]), RoleStatus, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.takeFullPageScreenshot(FailureHandling.CONTINUE_ON_FAILURE)

WebUI.closeBrowser()

