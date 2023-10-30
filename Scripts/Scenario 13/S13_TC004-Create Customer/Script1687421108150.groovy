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
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.COMPANY_SGTTAP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC004_Customer_List_page/span_Master'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC004_Customer_List_page/li_Customer List'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC004_Customer_List_page/button-add_customer'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC004_Customer_List_page/list__CompanyCode'))

WebUI.click(findTestObject('Scenario 13/S13_TC004_Customer_List_page/input_CompanyCode', [('ListCompanyCode') : ListCompanyCode]))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC004_Customer_List_page/tick__CompanyCopytoCustomer'))

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC004_Customer_List_page/input__customerCode'), CustomerCode)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC004_Customer_List_page/check_input__regionCode'), 'value', 
    RegionCode, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC004_Customer_List_page/check_input__activeFlag'), 'value', 
    ActiveFlag, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC004_Customer_List_page/check_input_customerName'), 'value', 
    CompCustomerName, 1)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC004_Customer_List_page/check_input_customerShortCode'), 
    'value', CompCustomerShortCode, 1)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC004_Customer_List_page/check_input__timeZone'), 'value', 
    Timezone, 2)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC004_Customer_List_page/check_input_address1'), 'value', 
    CompanyAddress, 1)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC004_Customer_List_page/check_input_postalCode'), 'value', 
    CompanyPostal, 1)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC004_Customer_List_page/check_input_contact1'), 'value', 
    CompContactName, 1)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC004_Customer_List_page/chechk_input_fax1'), 'value', 
    CompContactFax, 1)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC004_Customer_List_page/check_input_telephone1'), 'value', 
    CompContactTel, 1)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC004_Customer_List_page/chechk_input_email1'), 'value', 
    CompContactEmail, 1)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Object Repository/Scenario 13/S13_TC004_Customer_List_page/button_Submit'), 
    1)

not_run: WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC004_Customer_List_page/button_Submit'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC004_Customer_List_page/span_CONFIRM'))

'The operation was successful.'
WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC004_Customer_List_page/p_The operation was successful'), 
    'The operation was successful.')

WebUI.closeBrowser()

