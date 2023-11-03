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

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC024/button_Stock Management Tool'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC035/li_Order Calculation List'))

WebUI.waitForElementVisible(findTestObject('Scenario 10/S10_TC035/h3_Order Calculation List'), 0)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC035/h3_Order Calculation List'), 'Order Calculation List')

WebUI.setText(findTestObject('Scenario 10/S10_TC035/input_Search Order Calculation'), orderCalcNo)

WebUI.click(findTestObject('Scenario 10/S10_TC035/input_tick all'))

WebUI.click(findTestObject('Scenario 10/S10_TC048/button_placeOrder'))

WebUI.click(findTestObject('Scenario 10/S10_TC048/button_placeOrder Confirm'))

WebUI.waitForElementPresent(findTestObject('Scenario 10/S10_TC048/h3_Place Order Screen(Regular)'), 0)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC048/h3_Place Order Screen(Regular)'), 'Place Order Screen(Regular)')

for (int row = 1; row <= testData1.getRowNumbers(); row++) {
    partNo = WebUI.getText(findTestObject('Scenario 10/S10_TC048/div_Dt input order-Part No', [('row') : row]), FailureHandling.STOP_ON_FAILURE)

    def qty = 0

    for (int rowTD = 1; rowTD <= testData1.getRowNumbers(); rowTD++) {
        if (partNo == testData1.getValue('Part No', rowTD)) {
            qty = testData1.getValue('Firm Qty', rowTD)
        }
    }
    
    'KIV'
    WebUI.click(findTestObject('Scenario 10/S10_TC048/input_Dt input order-Firm qty', [('row') : row]), FailureHandling.STOP_ON_FAILURE)

    'KIV'
    CustomKeywords.'util.clearTextJS.clearElementText'(findTestObject('Scenario 10/S10_TC048/input_Dt input order-Firm qty', 
            [('row') : row]))

    'KIV'
    WebUI.sendKeys(findTestObject('Scenario 10/S10_TC048/input_Dt input order-Firm qty', [('row') : row]), qty)
}

WebUI.setText(findTestObject('Scenario 10/S10_TC048/input__basic info-orderRefNo'), orderReference)

WebUI.setText(findTestObject('Scenario 10/S10_TC048/input__basic info-remark'), orderReferenceRemark)

not_run: CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC048/button_Download step 1'), 
    0)

not_run: WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

not_run: WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

not_run: latestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

//--------------------------TableDate-------------------------------------------------------------------------
not_run: int col = 1

not_run: for (String dateExcel : dateList) {
    colIndex = (col + 12)

    CustomKeywords.'copyToExcel.exel2'(dateExcel, 12, colIndex, latestPath, contractNum)

    col = (col + 1)
}

not_run: col = 1

not_run: for (String dateExcel : inboundDate) {
    colIndex = (col + 12)

    CustomKeywords.'copyToExcel.exel2'(dateExcel, 14, colIndex, latestPath, contractNum)

    col = (col + 1)
}

//------------------------------------------INBOUND-------------------------
not_run: partIndex = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(latestPath, 2, 15)

not_run: println(partIndex)

not_run: for (int index = 1; index <= testData1.getRowNumbers(); index++) {
    def datafilePartNo = testData1.getValue('Part No', index)

    def fileRowIndex = partIndex[datafilePartNo]

    for (def pair : columnName) {
        def columnName = pair.key

        def columnIndex = pair.value

        def dataString = testData1.getValue(columnName, index)

        CustomKeywords.'copyToExcel.exel3'(dataString, fileRowIndex, columnIndex, latestPath, contractNum)
    }
}

not_run: CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Scenario 10/S10_TC048/button_Download step 1'), 
    0)

not_run: CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC048/button_Upload'), latestPath)

not_run: CustomKeywords.'RobotUpload.uploadFileUsingJS'(findTestObject('Scenario 10/S10_TC048/button_Upload'), latestPath)

not_run: WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

not_run: WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

not_run: CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC048/button_Download step 3'), 
    0)

not_run: WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

not_run: WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

not_run: CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC048/button_Save'), 0)

not_run: WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

not_run: WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

not_run: CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC048/button_Issue'), 0)

not_run: WebUI.click(findTestObject('Scenario 10/S10_TC048/button_CONFIRM'))

not_run: WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

not_run: WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

not_run: WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

not_run: WebUI.closeBrowser()

