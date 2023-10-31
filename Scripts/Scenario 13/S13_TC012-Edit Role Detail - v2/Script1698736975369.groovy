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

WebUI.waitForElementPresent(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/div_Dt_RoleName', [('roleName') : RoleName]), 
    0, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/div_Dt_RoleName', [('roleName') : RoleName]), 
    RoleName, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/div_Dt_RoleNote', [('roleNote') : RoleNote]), 
    RoleNote, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/div_Dt_RoleStatus', [('roleStatus') : RoleStatus]), 
    RoleStatus, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/button_Dt_EditRoleDetails', [('roleName') : RoleName]))

WebUI.waitForElementPresent(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/Page_EditRoleDetail/h3_Edit Role Detail'), 
    0, FailureHandling.OPTIONAL)

for (int rowAdd = 1; rowAdd <= testDataAdd.getRowNumbers(); rowAdd++) {
    String permission = testDataAdd.getValue(1, rowAdd)

    WebUI.setText(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/input_SearchPermision'), permission)

    WebUI.verifyElementPresent(findTestObject('Page_RoleList/permission-Dt_RoleDescription', [('permission') : permission]), 
        0)

    backgroundColour = WebUI.getCSSValue(findTestObject('Page_RoleList/permission-Dt_RoleDescription', [('permission') : permission]), 
        'background-color')

    println(backgroundColour)

    if (backgroundColour != 'rgba(209, 210, 211, 1)') {
        WebUI.click(findTestObject('Page_RoleList/Permission-Dt-Tick', [('permission') : permission]))
    }
    
    CustomKeywords.'util.clearTextJS.clearElementText2'(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/input_SearchPermision'))
}

for (int rowRemove = 1; rowRemove <= testDataRemove.getRowNumbers(); rowRemove++) {
    String permission2 = testDataRemove.getValue(1, rowRemove)

    WebUI.setText(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/input_SearchPermision'), permission2)

    WebUI.verifyElementPresent(findTestObject('Page_RoleList/permission-Dt_RoleDescription', [('permission') : permission2]), 
        0)

    backgroundColour = WebUI.getCSSValue(findTestObject('Page_RoleList/permission-Dt_RoleDescription', [('permission') : permission2]), 
        'background-color')

    println(backgroundColour)

    if (backgroundColour == 'rgba(209, 210, 211, 1)') {
        WebUI.click(findTestObject('Page_RoleList/Permission-Dt-Tick', [('permission') : permission2]))
    }
    
    CustomKeywords.'util.clearTextJS.clearElementText2'(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/input_SearchPermision'))
}

not_run: WebUI.click(findTestObject('Page_Request Add New Part - Brivge/Page_RoleList/Page_EditRoleDetail/button_save'))

not_run: WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SuccessUpdateRole'), 0, FailureHandling.CONTINUE_ON_FAILURE)

