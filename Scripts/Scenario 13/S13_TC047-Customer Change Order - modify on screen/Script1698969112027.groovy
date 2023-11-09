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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : password, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_Place ChangeCancel'))

WebUI.waitForElementPresent(findTestObject('Page_OrderChangeCancel/h3_Order ChangeCancel'), 0)

WebUI.verifyElementPresent(findTestObject('Page_OrderChangeCancel/div_Dt_ContractNo_Create', [('contractNo') : custOrderNo]), 
    0)

WebUI.click(findTestObject('Page_OrderChangeCancel/div_Dt_ContractNo_Create', [('contractNo') : custOrderNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/h3_Create Order Change'), 0)

WebUI.click(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/button_Download'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadOrderChangeCustomer_Success'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

partIndex = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(latestFilePath, 2, 17)

'ADJUST FIRM QTY'
for (int row = 1; row <= testdataOrderChange.getRowNumbers(); row++) {
    part_No = testdataOrderChange.getValue('Part No', row)

    float newFirm = 0.0

    if (testdataOrderChange.getValue('NewFirm', row) != '') {
        newFirm = Float.parseFloat(testdataOrderChange.getValue('NewFirm', row))
    }
    
    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/h6_Input Order QTY for each PN'), 
        0)

    CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/input_INPUT ORDER QTY FOR EACH PN_newFirmQty', 
            [('part_No') : part_No]), 0)

    CustomKeywords.'util.clearTextJS.clearElementText2'(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/input_INPUT ORDER QTY FOR EACH PN_newFirmQty', 
            [('part_No') : part_No]))

    WebUI.setText(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/input_INPUT ORDER QTY FOR EACH PN_newFirmQty', 
            [('part_No') : part_No]), newFirm.toString())
}

'ADJUST SHIPPING PLAN'
for (int row = 1; row <= testdataOrderChange.getRowNumbers(); row++) {
    part_No = testdataOrderChange.getValue('Part No', row)

    float inboundDateQty1 = 0.0

    float inboundDateQty2 = 0.0

    if (testdataOrderChange.getValue('InboundNewDate_Qty1', row) != '') {
        inboundDateQty1 = Float.parseFloat(testdataOrderChange.getValue('InboundNewDate_Qty1', row))
    }
    
    if (testdataOrderChange.getValue('InboundNewDate_Qty2', row) != '') {
        inboundDateQty2 = Float.parseFloat(testdataOrderChange.getValue('InboundNewDate_Qty2', row))
    }
    
    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/h6_Input Shipping Plan'), 
        0)

    CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/input_input shipping_qtyPlanDate1', 
            [('part_No') : part_No]), 0)

    CustomKeywords.'util.clearTextJS.clearElementText2'(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/input_input shipping_qtyPlanDate1', 
            [('part_No') : part_No]))

    WebUI.setText(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/input_input shipping_qtyPlanDate1', [('part_No') : part_No]), 
        inboundDateQty1.toString())

    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/h6_Input Shipping Plan'), 
        0)

    CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/input_input shipping_qtyPlanDate2', 
            [('part_No') : part_No]), 0)

    CustomKeywords.'util.clearTextJS.clearElementText2'(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/input_input shipping_qtyPlanDate2', 
            [('part_No') : part_No]))

    WebUI.setText(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/input_input shipping_qtyPlanDate2', [('part_No') : part_No]), 
        inboundDateQty2.toString())
}

WebUI.setText(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/input_Basic info (order summary)_customerRefNo'), 
    testdataInboundDateChange.getValue('OrderReference', 1))

WebUI.setText(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/input_Basic info (order summary)_remark'), testdataInboundDateChange.getValue(
        'OrderReference', 1))

CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/div_Create Order ChangeHome PageOrder ChangeCancelCreate Order ChangeSaveIssue'), 
    0)

WebUI.click(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/button_Issue'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSureToDo_Issue'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_SaveAndIssueRevisedCustOrder_Success'), 0)

WebUI.closeBrowser()

