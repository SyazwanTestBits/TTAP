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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : url, ('username') : username, ('password') : password
        , ('verificationCode') : verificationCode, ('company') : company], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 10/S10_TC183/div_PO Management'))

for (def rowNum = 1; rowNum <= testData.getRowNumbers(); rowNum++) {
    def PurchaseOrderNo = testData.getValue('PurchaseOrderNo', rowNum)

    def Status = testData.getValue('Status', rowNum)

    WebUI.setText(findTestObject('Scenario 10/S10_TC183/input_Search PO No'), PurchaseOrderNo)

    WebUI.click(findTestObject('Scenario 10/S10_TC183/p_Purchase Order No'))

    println(Status)

    WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC183/input_Basic Info_status'), 'value', Status, 
        0)

    //CustomKeywords.'util.clearTextJS.clearElementText'(findTestObject('Scenario 10/S10_TC183/input_Search PO No'))
    WebUI.back()
}

WebUI.closeBrowser()
