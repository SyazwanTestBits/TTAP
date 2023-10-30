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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Page_PO_MonitoringList/li_PO Monitoring List'))

WebUI.waitForElementPresent(findTestObject('Page_PO_MonitoringList/h3_PO Monitoring List'), 0)

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), regularPurchaseOrderNo)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC078/p_verifyContractNo', [('PO') : regularPurchaseOrderNo]), contractNo)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC078/p_verifyOrderType', [('PO') : regularPurchaseOrderNo]), orderTypeRegular)

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC078/p_verifyStatus', [('PO') : regularPurchaseOrderNo]), 'Confirmed')

WebUI.click(findTestObject('Scenario 12/SC12_TC014/input_checkall'))

WebUI.click(findTestObject('Scenario 10/S10_TC078/p_detailButton', [('PO') : regularPurchaseOrderNo]))

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC078/div_Parts No'), 0)

//-------------looping for verify part detail------------------------------------
int colindex = 0

int numberrowtd = findTestData('Scenario 10/S10_TC099-BU Check Purchase Order').getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    colindex = 1

    for (String colname : columnname) {
        String valuecol = findTestData('Scenario 10/S10_TC099-BU Check Purchase Order').getValue(colname, rowl)

        WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC078/p_checkDetail', [('lcol') : colindex, ('lrow') : rowl]), 
            valuecol)

        colindex = (colindex + 1)
    }
}

WebUI.closeBrowser()

