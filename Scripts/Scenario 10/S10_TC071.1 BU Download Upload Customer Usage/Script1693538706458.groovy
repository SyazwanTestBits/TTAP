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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_SYAZWAN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Navbar_Brivge/button_Stock Management Tool'), 0)

WebUI.click(findTestObject('Navbar_Brivge/StockMgmtToolMenu_Brivge/li_DownloadUpload Customer Usage'))

WebUI.waitForElementPresent(findTestObject('Page_Donwload upload customer usage/h3_Customer Usage'), 0)

WebUI.click(findTestObject('Page_Donwload upload customer usage/input_Upload_customerList'))

WebUI.click(findTestObject('Page_Donwload upload customer usage/li_Upload_customerList', [('buyerID') : buyerID]))

WebUI.click(findTestObject('Page_Donwload upload customer usage/input_Upload_yearMonthFrom'))

WebUI.click(findTestObject('Calendar_Object/div_list_year', [('year') : 2023]))

WebUI.click(findTestObject('Calendar_Object/div_list_month', [('month') : 'Dec']))

WebUI.click(findTestObject('Page_Donwload upload customer usage/input_Upload_yearMonthTo'))

WebUI.click(findTestObject('Calendar_Object/div_list_year', [('year') : 2023]))

WebUI.click(findTestObject('Calendar_Object/div_list_month', [('month') : 'Dec']))

WebUI.click(findTestObject('Page_Donwload upload customer usage/input_Weekly_usageTypes_1'))

WebUI.click(findTestObject('Page_Donwload upload customer usage/button_Download'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

latestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

partsNoRowIndices = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(latestPath, 1, 5)

for (int row = 1; row <= testData.getRowNumbers(); row++) {
    def datafilePartNo = testData.getValue('Parts No.', row)

    def fileRowIndex = partsNoRowIndices[datafilePartNo]

    for (def pair : columnname) {
        def columnName = pair.key

        def columnIndex = pair.value

        def dataString = testData.getValue(columnName, row)

        CustomKeywords.'copyToExcel.exel3'(dataString, fileRowIndex, columnIndex, latestPath, 'CSUGF121')
    }
}

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Page_Donwload upload customer usage/button_Upload'), latestPath)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.closeBrowser()

