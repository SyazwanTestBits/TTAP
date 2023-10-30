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

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/span_Master'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/li_Company List'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/button-add_Company'))

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/input__companyCode'), CompanyCode)

WebUI.click(findTestObject('Scenario 13/S13_TC003_Company_List_Page/list_regionCode'))

'input RegionCode in list'
WebUI.click(findTestObject('Scenario 13/S13_TC003_Company_List_Page/input_regioncode', [('RegionCode') : RegionCode]))

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/input__companyName'), CompanyName)

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/input__companyShortCode'), CompanyShortCode)

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/input__address1'), CompanyAddress)

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/input__postalCode'), CompanyPostal)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/list-TimeZone'))

'input Timezone in list'
WebUI.click(findTestObject('Scenario 13/S13_TC003_Company_List_Page/input_TimeZone', [('Timezone') : Timezone]))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/list-ActiveFlag'))

'input Active Flag in list'
WebUI.click(findTestObject('Scenario 13/S13_TC003_Company_List_Page/input_ActiveFlag', [('ActiveFlag') : ActiveFlag]))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/list-TrackDetail'))

'input Track detail in list'
WebUI.click(findTestObject('Scenario 13/S13_TC003_Company_List_Page/input_TrackDetail', [('TrackDetail') : TrackDetail]))

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/input__contact1'), CompContactName)

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/input__fax1'), CompContactFax)

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/input__telephone1'), CompContactTel)

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/input__email1'), CompContactEmail)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC003_Company_List_Page/span_Submit'), 
    1)

not_run: WebUI.click(findTestObject('Scenario 13/S13_TC003_Company_List_Page/span_Submit'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/button_CONFIRM'))

'The operation was successful.'
WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/div_The operation was successful'), 
    'The operation was successful.')

WebUI.closeBrowser()

