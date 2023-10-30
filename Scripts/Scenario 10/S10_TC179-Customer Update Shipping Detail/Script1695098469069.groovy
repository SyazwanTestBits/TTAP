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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_CUST], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Shipping Detail List'))

WebUI.waitForElementPresent(findTestObject('Page_ShippingDetailList/h3_Shipping Detail List'), 0)

for (def rowNum = 1; rowNum <= testData.getRowNumbers(); rowNum++) {
    def bookingNo = testData.getValue('BookingNo', rowNum)

    def containerNo = testData.getValue('ContainerNo', rowNum)

    if (bookingNo || containerNo) {
        // Check if either bookingNo or containerNo is not empty or null
        TestObject cargoStatusObject = findTestObject('Object Repository/Scenario 12/SC12_TC053/p_verifyCargoStatus', [('bookingNo') : bookingNo
                , ('containerNo') : containerNo])

        // Get the text content of the cargo status element
        String cargoStatusText = WebUI.getText(cargoStatusObject)

        // Verify that the cargo status text is empty using verifyMatch
        WebUI.verifyMatch(cargoStatusText, '', true, FailureHandling.CONTINUE_ON_FAILURE)

        // If the verifyMatch fails, it will cause the script to fail
        KeywordUtil.logInfo("Cargo status is empty for BookingNo: $bookingNo, ContainerNo: $containerNo")

        WebUI.click(findTestObject('Scenario 10/S10_TC179/p_inputCargoStatus', [('bookingNo') : bookingNo, ('containerNo') : containerNo]))

        WebUI.click(findTestObject('Scenario 10/S10_TC179/input_Input Cargo Status_cargoStatus'))

        WebUI.click(findTestObject('Scenario 10/S10_TC179/li_Cargo Inbound'))

        WebUI.click(findTestObject('Scenario 10/S10_TC179/button_update'))

        WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC179/div_Update Cargo StatusThe operation was successful'), 
            0)

        WebUI.delay(1)

        String cargoStatusTextUpdated = WebUI.getText(cargoStatusObject)

        WebUI.verifyMatch(cargoStatusTextUpdated, 'Cargo Inbound', true, FailureHandling.CONTINUE_ON_FAILURE)
    }
}

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

