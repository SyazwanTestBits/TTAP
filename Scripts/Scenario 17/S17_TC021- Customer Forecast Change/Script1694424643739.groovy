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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_Place ChangeCancel'))

WebUI.waitForElementPresent(findTestObject('Page_OrderChangeCancel/h3_Order ChangeCancel'), 0)

WebUI.click(findTestObject('Scenario 12/SC12_TC018/button_Forecast Change'))

WebUI.verifyElementPresent(findTestObject('Page_OrderChangeCancel/div_Dt_ContractNo_Create', [('contractNo') : ContractNo]), 
    0)

WebUI.click(findTestObject('Page_OrderChangeCancel/div_Dt_ContractNo_Create', [('contractNo') : ContractNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Scenario 12/SC12_TC018/h3_Create Forecast Change'), 0)

WebUI.click(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/button_Download'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC018/div_Download Forecast Change By Customer.The operation was successful'), 
    0)

WebUI.delay(2)

downloadedExcel = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

partsNoRowIndices = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(downloadedExcel, 2, 15)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 17/S17_Cmn1-Write Info into Form Excel - mapDataIndices'), [('datafile') : dataFile
        , ('fileColumns') : mapKeyandColIndex, ('mapDataIndices') : partsNoRowIndices, ('downloadedFormPath') : downloadedExcel
        , ('downloadedFormSheetname') : customerOrderNo], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 12/SC12_TC018/button_Upload'), downloadedExcel)

WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC018/div_Upload Change Forecast By Customer.The operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 12/SC12_TC018/button_Issue'))

WebUI.waitForElementPresent(findTestObject('Scenario 12/SC12_TC018/div_IssueAre you sure to do Issue(c0001)CANCELCONFIRM'), 
    0)

WebUI.click(findTestObject('Scenario 12/SC12_TC018/span_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC018/div_Save And Issue Revised Customer Order.The operation was successful'), 
    0)

'\r\n'
WebUI.waitForElementPresent(findTestObject('Scenario 12/SC12_TC018/h3_Forecast Change'), 0)

WebUI.closeBrowser()

