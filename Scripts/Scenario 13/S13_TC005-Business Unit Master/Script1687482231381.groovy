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

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/button_Master'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/li_Business Unit List'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/button_add_BU'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/list_companyId'))

WebUI.click(findTestObject('Scenario 13/S13_TC005_BU_List_Page/input_CompanyCode', [('ListCompanyCode') : ListCompanyCode]))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/tick__CopyCompToBU'))

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/input__buCode'), BUCode)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC005_BU_List_Page/check_input__regionCode'), 'value', 
    RegionCode, 1)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/input__activeFlag'), 
    'value', ActiveFlag, 1)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/input__buName'), 
    'value', CompBUName, 1)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/input__buShortCode'), 
    'value', CompBUShortCode, 1)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC005_BU_List_Page/input__timeZone'), 'value', Timezone, 
    1)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/input__address1'), 
    'value', CompanyAddress, 1)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/input__postalCode'), 
    'value', CompanyPostal, 1)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/input__contact1'), 
    'value', CompContactName, 1)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/input__email1'), 
    'value', CompContactEmail, 1)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/input__telephone1'), 
    'value', CompContactTel, 1)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/input__fax1'), 'value', 
    CompContactFax, 1)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/button_Submit'), 
    1)

not_run: WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/button_Submit'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/button_CONFIRM'))

'The operation was successful.'
WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC005_BU_List_Page/p_The operation was successful'), 
    'The operation was successful.')

WebUI.closeBrowser()

