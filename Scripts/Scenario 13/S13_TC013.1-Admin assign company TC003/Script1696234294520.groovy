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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.ADMIN_USERNAME
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Privilege'))

WebUI.click(findTestObject('Navbar_Brivge/PrivilegeMenu_Brivge/li_User List'))

WebUI.waitForElementPresent(findTestObject('Page_UserList/h3_User List'), 0)

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/button_edit_user'))

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/button_Company Info_Assign Company'))

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/button_AND to OR'))

targetCompanyCode = ((((newCUSCompany + ' ') + newSUPCompany) + ' ') + newBUCompany)

WebUI.setText(findTestObject('Scenario 13/S13_TC014n015/input_Company List_Assign-search'), targetCompanyCode)

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/input_Company List_Assign_tick all'))

WebUI.click(findTestObject('Scenario 13/S13_TC014n015/button_Confirm'))

'For Customer Company'
WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 13/S13_Tc013.1-Cmm1-edit role'), [('company') : newCUSCompany, ('userRole') : 'Customer'], 
    FailureHandling.STOP_ON_FAILURE)

'For Supply Company'
WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 13/S13_Tc013.1-Cmm1-edit role'), [('company') : newSUPCompany, ('userRole') : 'Supplier'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 13/S13_Tc013.1-Cmm1-edit role'), [('company') : newBUCompany, ('userRole') : 'BU'], 
    FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC014n015/button_save'), 0)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC075/p22_The operation was successful'), 'The operation was successful.')

WebUI.closeBrowser()

