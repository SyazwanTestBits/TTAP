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

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 17/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Master'))

WebUI.click(findTestObject('Navbar_Brivge/MasterMenu_Brivge/li_Request Remove Part'))

WebUI.click(findTestObject('Scenario 17/S17_TC003/Page_Request Remove Part - Brivge/input__requestTo'))

WebUI.click(findTestObject('Scenario 17/S17_TC003/Page_Request Remove Part - Brivge/li_requestTo', [('requestTo') : requestTo]))

WebUI.setText(findTestObject('Scenario 17/S17_TC003/Page_Request Remove Part - Brivge/input_description'), description)

WebUI.click(findTestObject('Scenario 17/S17_TC003/Page_Request Remove Part - Brivge/button_SELECT PART'))

WebUI.click(findTestObject('Scenario 17/S17_TC003/Page_Request Remove Part - Brivge/input_Checkbox', [('contractNo') : contractNo
            , ('partsNo') : partsNo]))

WebUI.click(findTestObject('Scenario 17/S17_TC003/Page_Request Remove Part - Brivge/span_OK'))

WebUI.delay(2)

WebUI.click(findTestObject('Scenario 17/S17_TC003/Page_Request Remove Part - Brivge/button_Submit'))

WebUI.click(findTestObject('Scenario 17/S17_TC003/Page_Request Remove Part - Brivge/span_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC003/Page_Request Remove Part - Brivge/div_Submit Request DetailThe operation was successful'), 
    0)

RequestNo = WebUI.getText(findTestObject('Scenario 17/S17_TC003/Page_Request Remove Part - Brivge/p_GrabRequestNoViaDescription', 
        [('description') : description]))

println(RequestNo)

CustomKeywords.'copyToExcel.exel'(RequestNo, 1, 1, filePath, fileName, sheetName)

WebUI.closeBrowser()

