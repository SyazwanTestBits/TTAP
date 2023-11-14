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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/span_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/li_SO Monitoring List'))

WebUI.callTestCase(findTestCase('Scenario 12/S12_TC017.1-Supplier Check and Confirm Order -Regular'), [('columnnameRegular') : [
            'Part no', 'Customer unit part no', 'Back no', 'Purchase order no', 'Customer code', 'SPQ', 'Order lot', 'Order Qty'
            , 'Unit Price', 'Currency', 'Status', 'Delivered qty', 'InTransit qty', 'Receiver inbounded qty', 'Planoutin plan 1'
            , 'Planoutin status 1', 'Planoutin plan 2', 'Planoutin status 2', 'Estimate date value 1', 'Estimate date value 2']
        , ('regularSalesOrderNo') : findTestData('Scenario 12/SC12_TC016-Supplier SO').getValue('RegularSalesOrderNo', 1)
        , ('spotSalesOrderNo') : findTestData('Scenario 12/SC12_TC016-Supplier SO').getValue('SpotSalesOrderNo', 1), ('filecolumns_dr') : [
            ('newInboundQty1') : 20, ('newInboundQty2') : 22], ('datafile_dr') : findTestData('Scenario 12/SC12_TC017-Supplier SO Delivery Plan -Regular')
        , ('datafile_date') : findTestData('Scenario 12/SC12_TC017-Supplier SO Delivery Plan (Date) -Regular')], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Scenario 12/S12_TC017.2-Supplier Check and Confirm Order -Spot'), [('columnnameSpot') : [
            'Part no', 'Customer unit part no', 'Back no', 'Purchase order no', 'Customer code', 'SPQ', 'Order lot', 'Order Qty'
            , 'Unit Price', 'Currency', 'Status', 'Delivered qty', 'InTransit qty', 'Receiver inbounded qty', 'Planoutin plan 1'
            , 'Planoutin status 1', 'Estimate date value 1'], ('regularSalesOrderNo') : findTestData('Scenario 12/SC12_TC016-Supplier SO').getValue(
            'RegularSalesOrderNo', 1), ('spotSalesOrderNo') : findTestData('Scenario 12/SC12_TC016-Supplier SO').getValue(
            'SpotSalesOrderNo', 1), ('filecolumns_dr') : [('newInboundQty1') : 20, ('newInboundQty2') : 22], ('datafile_dr') : findTestData(
            'Scenario 12/SC12_TC017-Supplier SO Delivery Plan -Spot'), ('datafile_date') : findTestData('Scenario 12/SC12_TC017-Supplier SO Delivery Plan (Date) -Spot')], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC017/p_verifyStatusCofirmed', [('SOnumber') : regularSalesOrderNo]), 
    'Confirmed')

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC017/p_verifyStatusCofirmed', [('SOnumber') : spotSalesOrderNo]), 
    'Confirmed')

WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)



