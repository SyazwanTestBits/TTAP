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

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/div_VESSEL VOYAGE'))

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Vessel  Voyage_vesselName'), 'vessel-1')

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Vessel  Voyage_voyageNo'), 'voyage-1')

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/button_Vessel  Voyage_OK'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/div_ETD ETA'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/input_ETD  ETA_etd'))

(etd_day, etd_month, etd_year) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt_withFormat'(testDataETD_ETA.getValue(
        'outboundETD', 1), 'MMM d, yyyy')

CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Calendar_Object/div_calendar header'), findTestObject(
        'Calendar_Object/button next calendar'), findTestObject('Calendar_Object/button previous calendar'), etd_day, etd_month, 
    etd_year)

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/input_ETD  ETA_eta'))

(eta_day, eta_month, eta_year) = CustomKeywords.'commonUtils.parseDateDayMonthYearEachIntoInt_withFormat'(testDataETD_ETA.getValue(
        'outboundETA', 1), 'MMM d, yyyy')

CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Calendar_Object/div_calendar header'), findTestObject(
        'Calendar_Object/button next calendar'), findTestObject('Calendar_Object/button previous calendar'), eta_day, eta_month, 
    eta_year)

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/button_ETD  ETA_OK'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/div_PAYMENT TERMS'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/input_Payment Terms_code'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/li_Payment Terms_code', [('code') : 'TB60BL10']))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/button_payment term-OK'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/div_REMARK'))

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/textarea_Remark_remark'), 'S13_TC055')

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/button_Remark_OK'))

