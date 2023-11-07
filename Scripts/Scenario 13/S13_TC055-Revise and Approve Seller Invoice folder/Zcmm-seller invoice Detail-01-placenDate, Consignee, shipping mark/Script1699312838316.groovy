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
import java.time.LocalDate as LocalDate
import java.time.format.DateTimeFormatter as DateTimeFormatter

LocalDate currentDate = LocalDate.now()

String formattedDate = currentDate.format(DateTimeFormatter.ofPattern('d/M/yyyy'))

(placeNDate_day, placeNDate_month, placeNDate_year) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt_withFormat'(
    formattedDate, 'd/M/yyyy')

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/div_Place and Date'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/input_Place and Date_issueDate'))

CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Calendar_Object/div_calendar header'), findTestObject(
        'Calendar_Object/button next calendar'), findTestObject('Calendar_Object/button previous calendar'), placeNDate_day, 
    placeNDate_month, placeNDate_year)

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/button_Place and Date_OK'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/div_CONSIGNEE INFORMATION'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/input_Consignee Information_codeUid'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/li_Consignee Information_codeUid', [('codeCompany') : findTestData(
                'Scenario 13/S13_TC009-Create New User').getValue('UserCompanyCode', 1)]))

WebUI.clearText(findTestObject('Scenario 13/S13_TC055/edit/input_Consignee Information_codeName'))

CustomKeywords.'util.clearTextJS.clearElementText2'(findTestObject('Scenario 13/S13_TC055/edit/input_Consignee Information_codeName'))

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Consignee Information_codeName'), findTestData('Scenario 13/S13_TC009-Create New User').getValue(
        'UserCompanyName', 1))

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Consignee Information_address2'), 'address 2')

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Consignee Information_address3'), 'address 3')

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Consignee Information_address4'), 'address 4')

CustomKeywords.'util.clearTextJS.clearElementText2'(findTestObject('Scenario 13/S13_TC055/edit/input_Consignee Information_telephone1'))

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Consignee Information_telephone1'), '8765')

CustomKeywords.'util.clearTextJS.clearElementText2'(findTestObject('Scenario 13/S13_TC055/edit/input_Consignee Information_fax1'))

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Consignee Information_fax1'), '4321')

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/button_Consignee Information_OK'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/div_Shipping Mark'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/input_Shipping Mark_countryOfOrigins'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/li_Shipping Mark_countryOfOrigins', [('origin') : 'CHINA, SHANG HAI']))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/button_Shipping Mark_countryOfOrigins_OK'))

