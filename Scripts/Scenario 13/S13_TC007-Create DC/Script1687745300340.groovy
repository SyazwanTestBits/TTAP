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

WebUI.click(findTestObject('Scenario 13/S13_TC005_BU_List_Page/button_Master'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/li_Distribution Center List'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/button-add_dc'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/list_ListCompanyCode'))

WebUI.click(findTestObject('Scenario 13/S13_TC007_DC_List_Page/input_ListCompanyCode', [('ListCompanyCode') : ListCompanyCode]))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/tick-CopyCompToDC'))

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/input__dcCode'), DCCode)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/input__regionCode'), 
    'value', RegionCode, 0)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/input__activeFlag'), 
    'value', ActiveFlag, 0)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/input__dcName'), 
    'value', CompDCName, 0)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/input__dcShortCode'), 
    'value', CompDCShortCode, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC007_DC_List_Page/check_input__timeZone'), 'value', Timezone, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/input__address1'), 
    'value', CompanyAddress, 0)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/input__postalCode'), 
    'value', CompanyPostal, 0)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/input__contact1'), 
    'value', CompContactName, 0)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/input__fax1'), 'value', 
    CompContactFax, 0)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/input__telephone1'), 
    'value', CompContactTel, 0)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/input__email1'), 
    'value', CompContactEmail, 0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/button_Submit'), 
    1)

not_run: WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/button_Submit'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/button_CONFIRM'))

'The operation was successful.'
WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC007_DC_List_Page/p_The operation was successful'), 
    'The operation was successful.')

WebUI.closeBrowser()

