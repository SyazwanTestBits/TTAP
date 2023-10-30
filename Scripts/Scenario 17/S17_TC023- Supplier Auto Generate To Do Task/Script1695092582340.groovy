import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('HomePage_Brivge/button_ToDoList_More'))

WebUI.waitForElementPresent(findTestObject('Page_TodoList/h3_Todo List'), 0)

WebUI.setText(findTestObject('Scenario 17/S17_TC023/Page_Todo List - Brivge/input_search'), requestNo)

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC023/Page_Todo List - Brivge/div_Description_Supplier', [('requestNo') : requestNo]), 
    0)

containsText = requestNo

elementText = WebUI.getText(findTestObject('Scenario 17/S17_TC023/Page_Todo List - Brivge/div_Description_Supplier', [('requestNo') : requestNo]))

println(elementText)

if (elementText.contains(containsText)) {
    println('Element Text: ' + elementText)

    println('Expected Partial Text Found: ' + containsText)
} else {
    println('Element Text: ' + elementText)

    throw new AssertionError('Expected Partial Text NOT Found: ' + containsText)
}

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

