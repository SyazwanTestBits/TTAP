import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_ChangeCancel Request List'))

WebUI.waitForElementPresent(findTestObject('Page_ChangeCancelReqList/h3_ChangeCancel Request List'), 0)

WebUI.setText(findTestObject('Page_ChangeCancelReqList/input_ChangeCancelReqList_Search'), reqSalesOrderNo)

WebUI.click(findTestObject('Page_ChangeCancelReqList/button_Dt_ChgReqNo_Detail', [('changeReqNo') : reqSalesOrderNo]), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page_ChangeCancelReqList/Page_ChgCancelReqDetail/h3_ChangeCancel Request Detail'), 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Page_ChangeCancelReqList/Page_ChgCancelReqDetail/input_RequestNo'), 'value', 
    reqSalesOrderNo, 0)

not_run: WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 9/S9_TC062_Cmn1-Verify New Firm'), [('datafile_suporderchange') : datafile_suporderchange], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 12/SC12_TC026/p_Parts No sort'))

int numberrowtd = findTestData('Scenario 9/S9_TC062-Customer Check Change Details').getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    int coll = 1

    for (String col : columnname) {
        String valuecol = findTestData('Scenario 9/S9_TC062-Customer Check Change Details').getValue(col, rowl)

        WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC029/p_partsDetail', [('lrow') : rowl, ('lcol') : coll]), 
            valuecol)

        coll = (coll + 1)
    }
    
    if (((coll == 14) || (coll == 15)) || (coll == 16)) {
        WebUI.verifyElementText(findTestObject('Scenario 9/SC9_TC062/p_verify New Outbound Date Header'), 'New Outbound Date(Supplier)')

        if (coll == 14) {
            expectedDate = (newOutboundDate[0])
        } else if (coll == 15) {
            expectedDate = (newOutboundDate[1])
        } else if (coll == 16) {
            expectedDate = (newOutboundDate[2])
        }
        
        def dateFormat = new SimpleDateFormat('MMM dd, yyyy')

        def parsedDate = dateFormat.parse(expectedDate)

        if (parsedDate.date < 10) {
            def newDateFormat = new SimpleDateFormat('MMM d, yyyy')

            expectedDate = newDateFormat.format(parsedDate)
        }
        
        WebUI.verifyElementText(findTestObject('Scenario 9/SC9_TC062/p_verify New Outbound Date Supplier', [('column') : coll]), 
            expectedDate)
    }
}

WebUI.scrollToElement(findTestObject('Page_ChangeCancelReqList/Page_ChgCancelReqDetail/th_lastOutboundDate'), 0)

WebUI.takeFullPageScreenshot()

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Page_ChangeCancelReqList/Page_ChgCancelReqDetail/button_Approve'), 
    0)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_ApproveChgCancelReq_Success'), 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_ChangeCancelReqList/Page_ChgCancelReqDetail/input_RequestStatus'), 
    'value', 'Approved', 0)

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

