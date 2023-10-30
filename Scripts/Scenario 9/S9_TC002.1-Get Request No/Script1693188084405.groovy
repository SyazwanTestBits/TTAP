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

WebUI.click(findTestObject('Scenario 9/SC9_TC002/li_Request List'))

RequestNo = WebUI.getText(findTestObject('Scenario 9/SC9_TC002/p_requestNo'))

KeywordUtil.logInfo("Request No is $RequestNo")

CustomKeywords.'copyToExcel.exel'(RequestNo, 1, 1, 'Excel Files\\Scenario 9', 'S9_TestCases_Data.xlsx', 'TC4-Supp to BU Contract')

not_run: WebUI.closeBrowser()

