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

WebUI.click(findTestObject('Scenario 12/SC12_TC047/p_detailButton', [('POid') : regularPurchaseOrderNo]))

WebUI.waitForElementPresent(findTestObject('Page_PO_MonitoringDetail/h3_PO Monitoring Detail'), 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_PO_MonitoringDetail/input_PurchaseOrderNo'), 'value', regularPurchaseOrderNo, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC046/input_detailStatus'), 'value', 'Completed', 0)

WebUI.click(findTestObject('Scenario 12/SC12_TC047/p_PO Monitoring List'))

WebUI.click(findTestObject('Scenario 12/SC12_TC046/p_checkbox', [('SOid') : regularPurchaseOrderNo]))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_download'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC015/div_Download Purchase Order by Excel.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedPOregular, downloadedFile, 1, [23, 24, 25, 26, 27, 28], [1, 2
        , 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15])

WebUI.click(findTestObject('Scenario 12/SC12_TC046/p_checkbox', [('SOid') : regularPurchaseOrderNo]))

