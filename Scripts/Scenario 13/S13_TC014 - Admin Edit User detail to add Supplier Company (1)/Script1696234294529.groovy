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

String listTargetCompany = "$targetCompanyCode ( $targetCompanyName )"

//String listTargetCompany = 'CNTW-SUP-POC ( CNTW-SUP by Upload )'
String currentcompany = ''

int rowtarget = 0

int row = 1

String boolenCompanyJustAdd = 'NO'

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/button_Privilege'))

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/li_User List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC014n015/input_User List_search'), userID)

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/button_edit_user'))

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/button_Company Info_Assign Company'))

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/button_AND to OR'))

WebUI.setText(findTestObject('Scenario 13/S13_TC014n015/input_Company List_Assign-search'), targetCompanyCode)

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/input_Company List_Assign_tick'))

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/button_Confirm'))

WebUI.verifyElementText(findTestObject('Page_UserList/Page_CreateNewUser/div_Dt_CompanyInfo_CompanyCode', [('userCompany') : targetCompanyCode]), 
    targetCompanyCode)

WebUI.click(findTestObject('Page_UserList/Page_CreateNewUser/div_Dt_CompanyInfo_CompanyCode - edit Role', [('userCompany') : targetCompanyCode]))

WebUI.waitForElementPresent(findTestObject('Page_UserList/Page_CreateNewUser/Page_AssignRole_Modal/h5_Role List'), 0)

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/button_AND to OR'))

WebUI.setText(findTestObject('Scenario 13/S13_TC014n015/input_Role List_search'), targetRole)

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/input_Role_DT_tick', [('row') : 1]))

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/button_Confirm'))

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/input__defaultCompanyList'))

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/li_defaultCompanyTarget', [('defaultComp') : listTargetCompany]))

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC014n015/button_save'), 0)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC075/p22_The operation was successful'), 'The operation was successful.')

WebUI.back()

WebUI.waitForElementVisible(findTestObject('Scenario 13/S13_TC014n015/h3_User List'), 0)

WebUI.setText(findTestObject('Scenario 13/S13_TC014n015/input_User List_search'), userID)

companyListString = WebUI.getText(findTestObject('Scenario 13/S13_TC014n015/div_Dt_list companyCode'))

String[] companyList = companyListString.split(', ')

String boolenCompVerify = 'No'

for (String compantcodeinList : companyList) {
    if (compantcodeinList == targetCompanyCode) {
        boolenCompVerify = 'Yes'
    }
}

WebUI.verifyMatch(boolenCompVerify, 'Yes', false)

WebUI.closeBrowser()

