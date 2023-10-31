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
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/span_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/li_SO Monitoring List'))

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), contractNo)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_detailButton', [('contractNo') : contractNo]))

'Grab the customer order no'
SO_no = WebUI.getAttribute(findTestObject('Scenario 17/S17_TC019/input_OrderNo'), 'value')

println(SO_no)

'copy customer order no to excel\r\n'
CustomKeywords.'copyToExcel.exel'(SO_no, 1, 0, filePath, fileName, sheetName)

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header-Parts Monitoring detailDisplay Monitor_d3694d'), 
    0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/button_Parts Monitoring detail_pop down'))

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header_Parts No header list'), 0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/header_Parts No header list'))

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/button_Download_SO Monitoring Detail'))

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/li_Download DR'))

WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC050 TC053/div_Download DR For Supplier.The operation was successful'), 
    0)

supDR = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'excelUtils.unprotectExcelSheet'(supDR)

'Clear existing value in the downloaded excel sheet\r\n'
CustomKeywords.'excelUtils.clearOutValueExcel2'(supDR, 11, 11, 15, 47)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 17/S17_Cmn1-Write Info into Form Excel'), [('datafile') : dataFile1
        , ('fileColumns') : fileColumns, ('startRowFormMinusOne') : startRowFormMinusOne, ('downloadedFormPath') : supDR
        , ('downloadedFormSheetname') : downloadedFormSheetname], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 17/S17_TC019/Page_SO Monitoring Detail - Brivge/button_uploadSOMonitoring_Detail'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 13/S13_TC050 TC053/li_Upload DR'), supDR)

WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC050 TC053/div_Upload Delivery RequestThe operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/button_Download_SO Monitoring Detail'))

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/li_Download Price'))

WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC050 TC053/div_Download Price.The operation was successful'), 
    0)

supPrice = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'excelUtils.unprotectExcelSheet'(supPrice)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 9/S9_Cmn1-Write Info into Form Excel (Price)'), [('datafile') : dataFile2
        , ('fileColumns') : fileColumns_Price, ('startRowFormMinusOne') : startRowFormMinusOne2, ('downloadedFormPath') : supPrice
        , ('downloadedFormSheetname') : downloadedFormSheetname2], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 17/S17_TC019/Page_SO Monitoring Detail - Brivge/button_uploadSOMonitoring_Detail'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 13/S13_TC050 TC053/li_Upload Price'), supPrice)

WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC050 TC053/div_Upload Price.The operation was successful'), 
    0)

int numberrowtd = findTestData('Scenario 12/SC12_TC017-Supplier Check and Confirm SO -Regular').getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    not_run: int coll = 1

    not_run: for (String col : columnnameRegular) {
        String valuecol = findTestData('Scenario 12/SC12_TC017-Supplier Check and Confirm SO -Regular').getValue(col, rowl)

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC050 TC053/p_part detail list-tc50 - Copy', [('lrow') : rowl
                    , ('lcol') : coll]), valuecol)

        coll = (coll + 1)
    }
}

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC050 TC053/button_Confirm'), 0)

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSureToDo_Confirm'), 0)

WebUI.click(findTestObject('Scenario 17/S17_TC019/span_CONFIRM'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC050 TC053/div_Confirm Supplier SO.The operation was successful'), 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC050 TC053/input_Status_Monitoring Detail'), 'value', 
    'Confirmed', 0)

WebUI.closeBrowser(FailureHandling.STOP_ON_FAILURE)

