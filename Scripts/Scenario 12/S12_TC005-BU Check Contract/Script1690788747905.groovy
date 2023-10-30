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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/button_Master'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/li_Contract List'))

WebUI.setText(findTestObject('Scenario 12/SC12_TC005/input_Contract List'), contractNo)

WebUI.click(findTestObject('Scenario 12/SC12_TC005/p_checkbox'))

WebUI.click(findTestObject('Scenario 12/SC12_TC005/button_downloadContract'))

WebUI.click(findTestObject('Scenario 12/SC12_TC005/li_Download Contract'))

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/p_The operation was successful'), 
    'The operation was successful.')

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 17/S17_Cmn3_Compare Test Data'), [('LatestPath') : downloadedFile
        , ('expectationExcelPath') : expectedContract, ('startRows') : 8, ('endRows') : 8, ('startCols') : 6, ('endCols') : 28
        , ('NumberOfNoMatch') : 0], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/button_view contract list'))

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC036 n 37-Check Contract/h3_header page view and modify'), 'View Contract')

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__contractNo'), 
    'value', contractNo, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__contractType'), 
    'value', contractType, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__businessType'), 
    'value', businessType, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__sellerCode'), 
    'value', sellerCode, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__buyerCode'), 
    'value', buyerCode, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__orderFrequency'), 
    'value', orderFrequency, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__deliveryTo'), 
    'value', deliveryTo, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__forecastNum'), 
    'value', forecastNum, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__targetLeadtime'), 
    'value', targetLeadTime, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__paymentTermsId'), 
    'value', paymentTermsCode, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__currency'), 
    'value', currency, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__priceBasis'), 
    'value', priceBasis, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__receiveDcId'), 
    'value', receiveDC, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__customsFlag'), 
    'value', customFlag, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__exportCCAgency'), 
    'value', exportCustomParty, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__importCCAgency'), 
    'value', importCustomParty, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__printHscodeFlag'), 
    'value', printHSCode, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__incotermsId'), 
    'value', incotermsCode, 0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/input__incotermsPlace'), 
    'value', incotermsPlace, 0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 12/SC12_TC005/Page_View Contract - Brivge/button_downloadContractParts'), 
    0)

WebUI.click(findTestObject('Scenario 12/SC12_TC005/li_Download Contract Parts'))

WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC036 n 37-Check Contract/p_The operation was successful'), 
    'The operation was successful.')

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedContractParts, downloadedFile, 2, [7, 8, 9, 10, 11, 12], [2, 4
        , 5, 6, 7, 8, 9, 10, 11, 12, 13, 19, 20, 22, 24, 25, 26, 27, 28, 29, 30, 31])

WebUI.closeBrowser()

