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

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_CO Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_CO_MonitoringList/h3_CO Monitoring List'), 0)

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC017/div_CustomerOrderNo', [('contractNo') : contractNo, ('orderType') : OrderType_Regular]), 
    0)

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), customerOrderNo)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/input_checkall'))

WebUI.click(findTestObject('Scenario 17/S17_TC017/Page_CO Monitoring List - Brivge/button_download'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC049/div_Download Customer Order by Excel.The operation was successful'), 
    0)

LatestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(LatestPath, expectationExcelPath, 1, [23, 24, 25, 26, 27], [1, 2, 3, 5
        , 6, 7, 8, 9, 10, 11, 12, 13, 14, 15])

WebUI.closeBrowser()

