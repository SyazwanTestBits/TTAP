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

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/div_Incoterm Breakdown'))

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Invoice Amount_invoiceAdjustmentAmount'), 
    '')

String intialAmount = WebUI.getAttribute(findTestObject('Scenario 13/S13_TC055/edit/input_Invoice Amount_invoiceAmount'), 
    'value')

float newAmount = Float.parseFloat(intialAmount)

float amountAdjust = 0.5

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Invoice Amount_invoiceAdjustmentAmount'), 
    amountAdjust.toString())

newAmount = (newAmount + amountAdjust)

String newAmountString = ''

if ((newAmount % 1) == 0.0) {
    int newAmountInteger = newAmount.toInteger()

    newAmountString = newAmountInteger.toString()
} else {
    newAmountString = newAmount.toString()
}

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC055/edit/input_Invoice Amount_invoiceAmountByAdjust'), 
    'value', newAmountString, 0)

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Invoice Amount_invoiceAdjustmentReason'), 
    'S13_TC055')

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Cost Breakdown for Invoice Amount_initialIncotermsId'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/li_Incoterm Breakdown_Cost Breakdown for Invoice Amount_initialIncotermsId', 
        [('code') : 'EXW']))

CustomKeywords.'util.clearTextJS.clearElementText2'(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Cost Breakdown for Invoice Amount_initialIncotermsPlace'))

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Cost Breakdown for Invoice Amount_initialIncotermsPlace'), 
    'TW PORT')

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC055/edit/input_Invoice Amount_Cost Breakdown for Invoice Amount_initialIncotermsAmount'), 
    'value', newAmountString, 0)

'COST BREAKDOWN FOR INVOICE AMOUNT'
float itemAmount0 = 1

float itemAmount1 = 0

float itemAmount2 = 0

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Cost Breakdown for Invoice Amount_itemName0'), 
    'itemAmount0')

WebUI.sendKeys(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Cost Breakdown for Invoice Amount_itemAmount0'), 
    itemAmount0.toString())

not_run: WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Cost Breakdown for Invoice Amount_itemName1'), 
    'itemAmount1')

not_run: WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Cost Breakdown for Invoice Amount_itemAmount1'), 
    itemAmount1.toString())

not_run: WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Cost Breakdown for Invoice Amount_itemName2'), 
    'itemAmount2')

not_run: WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Cost Breakdown for Invoice Amount_itemAmount2'), 
    itemAmount2.toString())

newAmount = (((newAmount - itemAmount0) - itemAmount1) - itemAmount2)

if ((newAmount % 1) == 0.0) {
    int newAmountInteger = newAmount.toInteger()

    newAmountString = newAmountInteger.toString()
} else {
    newAmountString = newAmount.toString()
}

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC055/edit/input_Invoice Amount_Cost Breakdown for Invoice Amount_initialIncotermsAmount'), 
    'value', newAmountString, 0)

'ADDTIONAL COST ITEMS FOR INVOICE AMOUNT'
float additemAmount0 = 1

float additemAmount1 = 0

float additemAmount2 = 0

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Addtional Cost Items for Invoice Amount_additionalItemName0'), 
    'additemAmount0')

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Addtional Cost Items for Invoice Amount_additionalItemAmount0'), 
    additemAmount0.toString())

not_run: WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Addtional Cost Items for Invoice Amount_additionalItemName1'), 
    'additemAmount1')

not_run: WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Addtional Cost Items for Invoice Amount_additionalItemAmount1'), 
    additemAmount1.toString())

not_run: WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Addtional Cost Items for Invoice Amount_additionalItemName2'), 
    'additemAmount2')

not_run: WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Addtional Cost Items for Invoice Amount_additionalItemAmount2'), 
    additemAmount2.toString())

'INVOICE AMOUNT GRAND TOTAL'
WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Invoice Amount Grand Total_finalIncotermsId'))

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/li_Incoterm Breakdown_Invoice Amount Grand Total_finalIncotermsId', 
        [('code') : 'EXW']))

CustomKeywords.'util.clearTextJS.clearElementText2'(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Invoice Amount Grand Total_finalIncotermsPlace'))

WebUI.setText(findTestObject('Scenario 13/S13_TC055/edit/input_Incoterm Breakdown_Invoice Amount Grand Total_finalIncotermsPlace'), 
    'TW PORT')

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC055/edit/input_Invoice Amount_Invoice Amount Grand Total_initialIncotermsAmount'), 
    'value', newAmountString, 0)

String twPortTotal = String.format('%.2f', newAmount)

newAmount = ((((((newAmount + additemAmount0) + additemAmount1) + additemAmount2) + itemAmount0) + itemAmount1) + itemAmount2)

if ((newAmount % 1) == 0.0) {
    int newAmountInteger = newAmount.toInteger()

    newAmountString = newAmountInteger.toString()
} else {
    newAmountString = newAmount.toString()
}

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC055/edit/input_Invoice Amount_Invoice Amount Grand Total_finalIncotermsAmount'), 
    'value', newAmountString, 0)

formattTWPort = ('CNY ' + twPortTotal)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC055/edit/p_Invoice Amount_preview-twPort'), formattTWPort)

WebUI.click(findTestObject('Scenario 13/S13_TC055/edit/button_Incoterm Breakdown_OK'))

