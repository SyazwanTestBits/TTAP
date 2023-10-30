import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

not_run: WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Contract Route List/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Contract Route List/li_Contract Route List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Contract Route List/input_Contract Route List'), routeDescription)

WebUI.delay(1)

ContractRouteCode = WebUI.getText(findTestObject('Scenario 13/S13_TC035/Contract Route List/p_Contract Route Code', [('routeDesc') : routeDescription]))

KeywordUtil.logInfo("Contract Route No is $ContractRouteCode")

CustomKeywords.'copyToExcel.exel'(ContractRouteCode, 1, 16, 'Excel Files\\Scenario 9', 'S9_TestCases_Data.xlsx', 'TC2-BU to Customer Contract')

not_run: WebUI.closeBrowser()

