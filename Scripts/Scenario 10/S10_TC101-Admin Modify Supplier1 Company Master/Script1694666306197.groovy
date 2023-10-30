import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.ADMIN_USERNAME
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.ADMIN_COMPANY], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/span_Master'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC003_Company_List_Page/li_Company List'))

WebUI.setText(findTestObject('Scenario 10/S10_TC101/input_Company'), supplierShortCode)

WebUI.click(findTestObject('Scenario 10/S10_TC101/p_editButton'))

// Verify the element attribute value
def elementAttributeValue = WebUI.getAttribute(findTestObject('Scenario 10/S10_TC101/input__verifyYES'), 'value')

if (elementAttributeValue == 'NO') {
    KeywordUtil.logInfo('Element attribute value is \'NO\', continuing with the script.')

    WebUI.mouseOver(findTestObject('Scenario 10/S10_TC101/input__portcastFlag'))

    WebUI.click(findTestObject('Scenario 10/S10_TC101/button__x'))

    WebUI.click(findTestObject('Scenario 10/S10_TC101/input__portcastFlag'))

    WebUI.click(findTestObject('Scenario 10/S10_TC101/li_YES'))

    WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC101/input__verifyYES'), 'value', 'YES', 0)

    WebUI.click(findTestObject('Scenario 10/S10_TC101/button_Submit'))

    WebUI.click(findTestObject('Scenario 10/S10_TC101/button_CONFIRM'))

    WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC101/div_Save And Issue Company Info.The operation was successful'), 
        0)
} else {
    KeywordUtil.logInfo('Element attribute value is \'YES\', skipping the remaining script.')
}

WebUI.closeBrowser()

