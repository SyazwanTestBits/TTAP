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

'Login and change  company to PK-CUS-POC'
WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : GlobalVariable.PNA_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/li_Request Add New Part'))

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/input__requestTo'))

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/li_Request To', [('company') : companyReq]))

WebUI.setText(findTestObject('Scenario 13/S13_TC034/Page_Brivge/input__description'), description)

WebUI.click(findTestObject('Scenario 10/S10_TC005/button_Download'))

WebUI.click(findTestObject('Scenario 10/S10_TC005/li_Download'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC005/p_The operation was successful'), 0)

downloadedExcel = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

for (def index : (1..dataFile.getRowNumbers())) {
    'Write - Unit Parts No'
    CustomKeywords.'copyToExcel.exel3'(dataFile.getValue(2, index), index + 6, 0, downloadedExcel, 
        'INPUT PART INFO')

    'Write - Parts No.'
    CustomKeywords.'copyToExcel.exel3'(dataFile.getValue(3, index), index + 6, 1, downloadedExcel, 
        'INPUT PART INFO')

    'Write - Unit Parts Description'
    CustomKeywords.'copyToExcel.exel3'(dataFile.getValue(4, index), index + 6, 2, downloadedExcel, 
        'INPUT PART INFO')

    'Write - Parts Ref No.'
    CustomKeywords.'copyToExcel.exel3'(dataFile.getValue(5, index), index + 6, 3, downloadedExcel, 
        'INPUT PART INFO')

    'Write - Back No.'
    CustomKeywords.'copyToExcel.exel3'(dataFile.getValue(6, index), index + 6, 4, downloadedExcel, 
        'INPUT PART INFO')

    'Write - HS Code'
    CustomKeywords.'copyToExcel.exel3'(dataFile.getValue(7, index), index + 6, 5, downloadedExcel, 
        'INPUT PART INFO')

    'Write - UOM Code'
    CustomKeywords.'copyToExcel.exel3'(dataFile.getValue(8, index), index + 6, 6, downloadedExcel, 
        'INPUT PART INFO')

    'Write - Paired Parts'
    CustomKeywords.'copyToExcel.exel3'(dataFile.getValue(9, index), index + 6, 7, downloadedExcel, 
        'INPUT PART INFO')

    'Write - Unit Parts No. of Paired Parts'
    CustomKeywords.'copyToExcel.exel3'(dataFile.getValue(10, index), index + 6, 8, downloadedExcel, 
        'INPUT PART INFO')

    'Write - Paired Order Flag'
    CustomKeywords.'copyToExcel.exel3'(dataFile.getValue(11, index), index + 6, 9, downloadedExcel, 
        'INPUT PART INFO')

    'Write - Order Lot'
    CustomKeywords.'copyToExcel.exel3'(dataFile.getValue(12, index), index + 6, 10, downloadedExcel, 
        'INPUT PART INFO')

    'Write - SPQ'
    CustomKeywords.'copyToExcel.exel3'(dataFile.getValue(13, index), index + 6, 11, downloadedExcel, 
        'INPUT PART INFO')

    'Write - M3'
    CustomKeywords.'copyToExcel.exel3'(dataFile.getValue(14, index), index + 6, 12, downloadedExcel, 
        'INPUT PART INFO')

    'Write - Net Weight'
    CustomKeywords.'copyToExcel.exel3'(dataFile.getValue(15, index), index + 6, 13, downloadedExcel, 
        'INPUT PART INFO')

    'Write - Gross Weight'
    CustomKeywords.'copyToExcel.exel3'(dataFile.getValue(16, index), index + 6, 14, downloadedExcel, 
        'INPUT PART INFO')
}

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/button_Upload Part'))

absolutePath = CustomKeywords.'ManageFiles.getFileAbsolutePath'(downloadedExcel)

'Upload the file that has been edited.'
CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Object Repository/Scenario 13/S13_TC034/Page_Brivge/li_Upload Part From'), 
    absolutePath)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC034/Page_Brivge/div_The Operation was Successful'), 0)

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/button_Save'))

WebUI.delay(1)

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/button_Submit'))

WebUI.delay(1)

WebUI.click(findTestObject('Scenario 13/S13_TC034/Page_Brivge/button_Confirm'))

WebUI.delay(1)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC034/Page_Brivge/div_Submit_The Operation was Succesful'), 0)

WebUI.callTestCase(findTestCase('Scenario 1/S1_TC001.01-Get Request No'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

