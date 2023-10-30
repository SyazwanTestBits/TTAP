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

'Login to Brivge as Admin'
WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.ADMIN_USERNAME
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE], FailureHandling.STOP_ON_FAILURE)

'Click \'Master\' on the Dashboard'
WebUI.click(findTestObject('Scenario 13/S13_TC002/Page_Brivge/button_Master'))

'Select \'Company List\''
WebUI.click(findTestObject('Scenario 13/S13_TC002/Page_Brivge/li_Port List'))

'Click Upload Button'
WebUI.click(findTestObject('Scenario 13/S13_TC002/Page_Brivge/button_Upload'))

AbsolutePath = CustomKeywords.'ManageFiles.getFileAbsolutePath'('Excel Files/Scenario 13/TC002/13.0 - TC2.1 - Create Port Master.xlsx')

'Upload the file that has been edited.'
CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Object Repository/Scenario 13/S13_TC002/Page_Brivge/li_Upload Port Master'), 
    AbsolutePath)

'Verify the success message.'
WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC002/Page_Brivge/div_Upload Portmaster.The operation was successful'), 
    0)

WebUI.closeBrowser()

