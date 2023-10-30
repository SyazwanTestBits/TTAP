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
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_BU1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC040/h6_Master'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC040/li_Cargo Status Setting'))

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC040/input_Supplier Code verify'), 'value', supplier_code, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Object Repository/Scenario 13/S13_TC040/input_Supplier Name verify'), 
    'value', supplier_name, 0)

int totalrow = testData.getRowNumbers()

for (row = 1; row <= totalrow; row++) {
    String status_name = testData.getValue('status_name', row)

    String customer_code = testData.getValue('customer_code', row)

    String status_role = testData.getValue('status_role', row)

    String contract_id = testData.getValue('contract_id', row)

    WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC040/button_add cargo status'))

    WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC040/input_Status Setting_cargoStatusSettingName'), 
        status_name)

    WebUI.click(findTestObject('Scenario 13/S13_TC040/button_Status Setting_customerId list'))

    WebUI.click(findTestObject('Scenario 13/S13_TC040/li_status_setting_customercode', [('customer_code') : customer_code]))

    WebUI.click(findTestObject('Scenario 13/S13_TC040/button_Status Setting_role list'))

    WebUI.click(findTestObject('Scenario 13/S13_TC040/li_Status Setting_role', [('status_role') : status_role]))

    //-----------------------Looping for add contract id(s)-----------------------------
    'Looping for clicking many contract numbers'
    if (contract_id != '') {
        WebUI.click(findTestObject('Scenario 13/S13_TC040/button_Status Setting_contractIds list'))

        String[] contractIdList = contract_id.split(',')

        for (String contid : contractIdList) {
            WebUI.click(findTestObject('Scenario 13/S13_TC040/li_Status Setting_contractIds', [('cont_id') : contid]))
        }
        
        WebUI.click(findTestObject('Scenario 13/S13_TC040/button_Status Setting_contractIds list'))
    }
    
    //-----------------------Looping for check status alert default based on role-----------------------------
    String[] statusAlertListDefault

    int i_alert = 1

    switch (status_role) {
        case 'EXP Shipping':
            statusAlertListDefault = status_alert_EXPShip_default.split(',')

            break
        case 'EXP Customs':
            statusAlertListDefault = status_alert_EXPCus_default.split(',')

            break
        case 'IMP Shipping':
            statusAlertListDefault = status_alert_IMPShip_default.split(',')

            break
        case 'IMP Customs':
            statusAlertListDefault = status_alert_IMPCus_default.split(',')

            break
    }
    
    'Looping for check default statuses'
    for (String salert : statusAlertListDefault) {
        WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC040/input_Status Alert_default', [('status_alert_default') : salert]), 
            1)

        i_alert++
    }
    
    //-----------------------end of looping----------------------------------------------------------------
    //WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC040/button_Add Unit status alert'), 0)
    //WebUI.click(findTestObject('Scenario 13/S13_TC040/button_Add Unit status alert'))
    //WebUI.setText(findTestObject('Scenario 13/S13_TC040/input_Status Alert_add', [('alert_i') : i_alert]), 'test 123 ver1')
    //WebUI.click(findTestObject('Scenario 13/S13_TC040/input_Status Alert_delete', [('alert_i') : i_alert]))
    //WebUI.verifyElementNotPresent(findTestObject('Scenario 13/S13_TC040/input_Status Alert_add', [('alert_i') : i_alert]), 1)
    //WebUI.click(findTestObject('Scenario 13/S13_TC040/button_Add Unit status alert'))
    //WebUI.setText(findTestObject('Scenario 13/S13_TC040/input_Status Alert_add', [('alert_i') : i_alert]), 'test 123 ver2')
    CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC040/button_Save And Exit'), 1)

    WebUI.click(findTestObject('Scenario 13/S13_TC040/button_CONFIRM'))

    if (WebUI.verifyElementNotPresent(findTestObject('Scenario 13/S13_TC040/p_Contracts already exists in other settings, continue to move them to this setting(c0014)'), 
        1, FailureHandling.OPTIONAL) == true) {
        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC040/p_The operation was successful'), 'The operation was successful.')
    } else {
        WebUI.click(findTestObject('Scenario 13/S13_TC040/span_CONFIRM_contract already exit ver'))

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC040/p_The operation was successful'), 'The operation was successful.')
    }
    
    WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))
}

WebUI.closeBrowser()

