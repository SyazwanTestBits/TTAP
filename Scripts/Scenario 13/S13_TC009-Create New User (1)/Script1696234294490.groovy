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
	, ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company],
FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Privilege'))

WebUI.click(findTestObject('Navbar_Brivge/PrivilegeMenu_Brivge/li_User List'))

WebUI.waitForElementPresent(findTestObject('Page_UserList/h3_User List'), 0)

WebUI.click(findTestObject('Page_UserList/button_CreateNewUser'))

WebUI.waitForElementPresent(findTestObject('Page_UserList/Page_CreateNewUser/h3_Create New User'), 0)

WebUI.setText(findTestObject('Page_UserList/Page_CreateNewUser/input__loginId'), loginId)

WebUI.setText(findTestObject('Page_UserList/Page_CreateNewUser/input__name'), Username)

WebUI.setText(findTestObject('Page_UserList/Page_CreateNewUser/input__email'), email)

WebUI.click(findTestObject('Page_UserList/Page_CreateNewUser/button_AssignCompany'))

WebUI.waitForElementPresent(findTestObject('Page_UserList/Page_CreateNewUser/Page_AssignCompany_Modal/h5_Company List'),
0)

WebUI.setText(findTestObject('Page_UserList/Page_CreateNewUser/Page_AssignCompany_Modal/input_Search_CompanyList'), userCompany)

WebUI.waitForElementPresent(findTestObject('Page_UserList/Page_CreateNewUser/Page_AssignCompany_Modal/checkbox_CompanyCode',
	[('userCompany') : userCompany]), 0)

WebUI.check(findTestObject('Page_UserList/Page_CreateNewUser/Page_AssignCompany_Modal/checkbox_CompanyCode', [('userCompany') : userCompany]),
FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_UserList/Page_CreateNewUser/Page_AssignCompany_Modal/button_Confirm_CompanyList'))

WebUI.verifyElementAttributeValue(findTestObject('Page_UserList/Page_CreateNewUser/input__defaultCompanyUid'), 'value',
defaultCompany, 0)

WebUI.verifyElementText(findTestObject('Page_UserList/Page_CreateNewUser/div_Dt_CompanyInfo_CompanyCode', [('userCompany') : userCompany]),
userCompany)

WebUI.click(findTestObject('Page_UserList/Page_CreateNewUser/button_Dt_CompanyInfo_CompanyCode_AssignRole'))

WebUI.waitForElementPresent(findTestObject('Page_UserList/Page_CreateNewUser/Page_AssignRole_Modal/h5_Role List'), 0)

WebUI.setText(findTestObject('Page_UserList/Page_CreateNewUser/Page_AssignRole_Modal/input_Search_RoleList'), userRole)

WebUI.waitForElementPresent(findTestObject('Page_UserList/Page_CreateNewUser/Page_AssignRole_Modal/checkbox_RoleList_RoleName',
	[('userRole') : userRole]), 0)

WebUI.check(findTestObject('Page_UserList/Page_CreateNewUser/Page_AssignRole_Modal/checkbox_RoleList_RoleName', [('userRole') : userRole]),
FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_UserList/Page_CreateNewUser/Page_AssignRole_Modal/button_Confirm_RoleList'))

WebUI.verifyElementText(findTestObject('Page_UserList/Page_CreateNewUser/div_Dt_CompanyInfo_RoleName', [('userRole') : userRole]),
userRole)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Page_UserList/Page_CreateNewUser/button_Save_CreateNewUser'),
0)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_CreateUser_Success'), 0)

WebUI.takeFullPageScreenshot()

WebUI.delay(10)

GlobalVariable.TEMP_USERNAME = Username

userVerifCode = CustomKeywords.'GetBrivgeUserVerifCode.extractUserVerifCode'()

userGenCode = CustomKeywords.'GetBrivgeUserGenerateCode.extractUserGenCode'()

CustomKeywords.'copyToExcel.exel'(userVerifCode, 1, 7, 'Excel Files\\Scenario 13', 'S13_TestCases_Data.xlsx', 'TC09-Create New User')

CustomKeywords.'copyToExcel.exel'(userGenCode, 1, 8, 'Excel Files\\Scenario 13', 'S13_TestCases_Data.xlsx', 'TC09-Create New User')

WebUI.closeBrowser()

