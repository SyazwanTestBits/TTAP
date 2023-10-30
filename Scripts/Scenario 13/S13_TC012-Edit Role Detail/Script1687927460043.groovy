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

WebUI.waitForElementPresent(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/h3_Role List'), 0, FailureHandling.OPTIONAL)

WebUI.setText(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/input_SearchRole'), RoleName)

WebUI.waitForElementPresent(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/div_Dt_RoleName', [('roleName') : RoleName]), 0, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/div_Dt_RoleName', [('roleName') : RoleName]), RoleName, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/div_Dt_RoleNote', [('roleNote') : RoleNote]), RoleNote, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/div_Dt_RoleStatus', [('roleStatus') : RoleStatus]), RoleStatus, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/button_Dt_EditRoleDetails', [('roleName') : RoleName]))

WebUI.waitForElementPresent(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/Page_EditRoleDetail/h3_Edit Role Detail'), 0, FailureHandling.OPTIONAL)

if (IsCopyRole == 'YES') {
    WebUI.click(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/Page_EditRoleDetail/button_CopyRole'))

    WebUI.waitForElementPresent(findTestObject('Page_RoleList/Page_EditRoleDetail/Page_CopyRole/h5_Copy Role'), 0, FailureHandling.OPTIONAL)

    WebUI.setText(findTestObject('Page_RoleList/Page_EditRoleDetail/Page_CopyRole/input_SearchCopyRoleName'), CopyRoleAs)

    WebUI.waitForElementPresent(findTestObject('Page_RoleList/Page_EditRoleDetail/Page_CopyRole/div_Dt_CopyRole_Customer', 
            [('copyRoleName') : CopyRoleAs]), 0, FailureHandling.CONTINUE_ON_FAILURE)

    WebUI.click(findTestObject('Page_RoleList/Page_EditRoleDetail/Page_CopyRole/button_CopyRole', [('copyRoleName') : CopyRoleAs]))

    WebUI.click(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/Page_EditRoleDetail/Page_CopyRole/button_Cancel'))
}

WebUI.click(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/Page_EditRoleDetail/button_save'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SuccessUpdateRole'), 0, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.takeFullPageScreenshot()

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 13/S13_TC012_Cmn_1-Verify Role Permissions Detail'), [('RoleName') : RoleName
        , ('RoleNote') : RoleNote, ('RoleStatus') : RoleStatus, ('CopyRoleAs') : CopyRoleAs], FailureHandling.STOP_ON_FAILURE)

