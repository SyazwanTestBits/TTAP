import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_SUP2], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Shipping Detail List'))

WebUI.waitForElementPresent(findTestObject('Page_ShippingDetailList/h3_Shipping Detail List'), 0)

def containerNo1 = testData.getValue('ContainerNo', 1)

def containerNo2 = testData.getValue('ContainerNo', 2)

def bookingNo1 = testData.getValue('BookingNo', 3)

WebUI.setText(findTestObject('Scenario 1/S1_TC090/Page_Shipping Detail List - Brivge/input_Shipping Detail List'), (((bookingNo1 + 
    ' ') + containerNo1) + ' ') + containerNo2)

WebUI.click(findTestObject('Scenario 1/S1_TC041/button_AND'))

WebUI.click(findTestObject('Scenario 1/S1_TC090/Page_Shipping Detail List - Brivge/div_checkAll'))

WebUI.click(findTestObject('Scenario 1/S1_TC090/Page_Shipping Detail List - Brivge/button_download'))

WebUI.click(findTestObject('Scenario 1/S1_TC090/Page_Shipping Detail List - Brivge/li_Download'))

WebUI.verifyElementPresent(findTestObject('Scenario 1/S1_TC090/Page_Shipping Detail List - Brivge/div_Download Shipping Details.The operation was successful'), 
    0)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 1/S1_Cmn1-Write Info into Form Excel'), [('datafile') : testData
        , ('fileColumns') : fileColumns, ('startRowFormMinusOne') : 3, ('downloadedFormPath') : latestFilePath, ('downloadedFormSheetname') : 'Sheet1'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 1/S1_TC090/Page_Shipping Detail List - Brivge/button_upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 1/S1_TC090/Page_Shipping Detail List - Brivge/li_Upload Shipping Detail'), 
    latestFilePath)

WebUI.verifyElementPresent(findTestObject('Scenario 1/S1_TC090/Page_Shipping Detail List - Brivge/div_Upload Shipping Details.The operation was successful'), 
    0)

for (def rowNum = 1; rowNum <= 4; rowNum++) {
    def bookingNo = testData.getValue('BookingNo', rowNum)

    def containerNo = testData.getValue('ContainerNo', rowNum)

    if (bookingNo || containerNo) {
        // Check if either bookingNo or containerNo is not empty or null
        TestObject cargoStatusObject = findTestObject('Object Repository/Scenario 12/SC12_TC053/p_verifyCargoStatus', [('bookingNo') : bookingNo
                , ('containerNo') : containerNo])

        // Get the text content of the cargo status element
        String cargoStatusText = WebUI.getText(cargoStatusObject)

        WebUI.verifyMatch(cargoStatusText, 'Cargo Outbound', false, FailureHandling.STOP_ON_FAILURE)

        KeywordUtil.logInfo("VERIFIED - Booking No: $bookingNo, Container No: $containerNo, Cargo status: Cargo Outbound")
    }
}

for (def rowNum2 = 1; rowNum2 <= 4; rowNum2++) {
    WebUI.click(findTestObject('Scenario 1/S1_TC090/Page_Edit Shipping Detail - Brivge/button_edit', [('row') : rowNum2]))

    WebUI.click(findTestObject('Scenario 1/S1_TC090/Page_Edit Shipping Detail - Brivge/button_Confirm'))

    WebUI.click(findTestObject('Scenario 1/S1_TC090/Page_Edit Shipping Detail - Brivge/button_CONFIRM (1)'))

    WebUI.verifyElementPresent(findTestObject('Scenario 1/S1_TC090/Page_Edit Shipping Detail - Brivge/div_Confirm Shipping Detail.The operation was successful'), 
        0)

    WebUI.click(findTestObject('Scenario 1/S1_TC090/Page_Edit Shipping Detail - Brivge/p_Shipping Detail List'))

    if (((rowNum2 == 1) || (rowNum2 == 2)) || (rowNum2 == 3)) {
        WebUI.setText(findTestObject('Scenario 1/S1_TC090/Page_Shipping Detail List - Brivge/input_Shipping Detail List'), 
            (((bookingNo1 + ' ') + containerNo1) + ' ') + containerNo2)

        WebUI.click(findTestObject('Scenario 1/S1_TC041/button_AND'))
    }
}

WebUI.closeBrowser()

