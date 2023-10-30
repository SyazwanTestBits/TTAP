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

not_run: WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Page_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Contract Route List/li_Contract Route List'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Contract Route List/svg_ContractRouteList_ContractRouteCode_CaretDown'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Contract Route List/li_ZADesc'))

WebUI.delay(1)

ContractRouteCode = WebUI.getText(findTestObject('Scenario 13/S13_TC035/Contract Route List/p_Contract Route Code'))

WebUI.click(findTestObject('Scenario 9/SC9_TC021/p_viewButton', [('contractRouteNo') : ContractRouteCode]))

CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Scenario 9/SC9_TC021/p_verifyContractNo'), 0)

WebUI.verifyElementText(findTestObject('Scenario 9/SC9_TC021/p_verifyContractNo'), contractNo)

CustomKeywords.'copyToExcel.exel'(ContractRouteCode, 1, 14, 'Excel Files\\Scenario 9', 'S9_TestCases_Data.xlsx', 'TC23-Supplier to BU Contract')

not_run: WebUI.closeBrowser()

