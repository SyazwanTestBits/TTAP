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
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/span_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/li_CO Monitoring List'))

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC029/p_verifyStatus', [('orderNo') : RegularOrderNo]), 'Completed')

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC049/p_verifyDelayStatus', [('COid') : RegularOrderNo]), 'Normal')

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC029/p_verifyStatus', [('orderNo') : SpotOrderNo]), 'Completed')

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC049/p_verifyDelayStatus', [('COid') : SpotOrderNo]), 'Normal')

WebUI.callTestCase(findTestCase('Scenario 12/S12_TC071.1-Customer Check Customer Order -Regular'), [('RegularOrderNo') : findTestData('Scenario 12/SC12_TC011-CO Number').getValue('RegularCustomerOrderNo', 1)
        , ('expectedCOregular') : 'Excel Files\\Scenario 12\\Expected Data\\TC71\\Expected Customer Order Regular.xlsx'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Scenario 12/S12_TC071.2-Customer Check Customer Order -Spot'), [('SpotOrderNo') : findTestData('Scenario 12/SC12_TC011-CO Number').getValue('SpotCustomerOrderNo', 1)
        , ('expectedCOspot') : 'Excel Files\\Scenario 12\\Expected Data\\TC71\\Expected Customer Order Spot.xlsx'], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()
