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

WebUI.waitForElementPresent(findTestObject('Page_StockMngmntOrderCalcSetting/Page_ViewEditStockManagement_OrderCalcSettingDetail/h3_View Stock Management  Order Calculation Setting Detail'), 
    0)

absolutePath = CustomKeywords.'ManageFiles.getFileAbsolutePath'(relativeFilePath)

def partsNo = datafile.getValue('PartsNo', index)

def invBoxFlag = datafile.getValue('InventoryControlledBox', index)

def outboundFluctuation = datafile.getValue('Fluctuation', index)

def alertType = datafile.getValue('AlertMethod', index)

def minDays3 = datafile.getValue('MinStockDays', index)

def maxDays = datafile.getValue('MaxStockDays', index)

def deliveryCustPat = datafile.getValue('DailyAllocationPattern', index)

def custDelayPat = datafile.getValue('AdjustmentPattern1', index)

def custAdvancePat = datafile.getValue('AdjustmentPattern2', index)

def ocgn = datafile.getValue('OrderCalculationGroupingNo', index)

def safetyStockUom = datafile.getValue('SafetyStockUnit', index)

not_run: safetyStock = CustomKeywords.'readFromExcel.getCellValue'(absolutePath, sheetname, index, 17)

def safetyStock = datafile.getValue('IsByPercentageSSS', index)

//maxStock = CustomKeywords.'readFromExcel.getCellValue'(absolutePath, sheetname, index, 18)
def calculationPattern = datafile.getValue('CalculationPatternforN3', index)

WebUI.verifyElementAttributeValue(findTestObject('Page_StockMngmntOrderCalcSetting/Page_ViewEditStockManagement_OrderCalcSettingDetail/input__partsNo'), 
    'value', partsNo, 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_StockMngmntOrderCalcSetting/Page_ViewEditStockManagement_OrderCalcSettingDetail/input__inventoryBoxFlag'), 
    'value', invBoxFlag, 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_StockMngmntOrderCalcSetting/Page_ViewEditStockManagement_OrderCalcSettingDetail/input__outboundFluctuation'), 
    'value', outboundFluctuation, 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_StockMngmntOrderCalcSetting/Page_ViewEditStockManagement_OrderCalcSettingDetail/input__alertType'), 
    'value', alertType, 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_StockMngmntOrderCalcSetting/Page_ViewEditStockManagement_OrderCalcSettingDetail/input__minDays3'), 
    'value', minDays3, 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_StockMngmntOrderCalcSetting/Page_ViewEditStockManagement_OrderCalcSettingDetail/input__maxDays'), 
    'value', maxDays, 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_StockMngmntOrderCalcSetting/Page_ViewEditStockManagement_OrderCalcSettingDetail/input__deliveryCustPat'), 
    'value', deliveryCustPat, 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_StockMngmntOrderCalcSetting/Page_ViewEditStockManagement_OrderCalcSettingDetail/input__custDelayPat'), 
    'value', custDelayPat, 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_StockMngmntOrderCalcSetting/Page_ViewEditStockManagement_OrderCalcSettingDetail/input__custAdvancePat'), 
    'value', custAdvancePat, 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_StockMngmntOrderCalcSetting/Page_ViewEditStockManagement_OrderCalcSettingDetail/input_Order Calculation information_orderGroupId'), 
    'value', ocgn, 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_StockMngmntOrderCalcSetting/Page_ViewEditStockManagement_OrderCalcSettingDetail/input_Order Calculation information_safetyStockUom'), 
    'value', safetyStockUom, 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_StockMngmntOrderCalcSetting/Page_ViewEditStockManagement_OrderCalcSettingDetail/input_Order Calculation information_safetyStock'), 
    'value', safetyStock, 0)

not_run: WebUI.verifyElementAttributeValue(findTestObject('Page_StockMngmntOrderCalcSetting/Page_ViewEditStockManagement_OrderCalcSettingDetail/input_Order Calculation information_maxStock'), 
    'value', maxStock, 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_StockMngmntOrderCalcSetting/Page_ViewEditStockManagement_OrderCalcSettingDetail/input_Order Calculation information_calculationPattern'), 
    'value', calculationPattern, 0)

WebUI.takeFullPageScreenshot()

WebUI.back()

