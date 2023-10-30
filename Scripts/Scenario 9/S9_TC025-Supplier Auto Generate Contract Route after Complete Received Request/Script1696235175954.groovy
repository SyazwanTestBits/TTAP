import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Master'))

WebUI.click(findTestObject('Scenario 13/S13_TC035/Contract Route List/li_Contract Route List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC035/Contract Route List/input_Contract Route List'), contractRouteCode)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC035/Contract Route List/p_Contract Route Code'), contractRouteCode)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC035/Contract Route List/p_Contract Route Code_Status'), 'Build Completed')

WebUI.check(findTestObject('Scenario 13/S13_TC035/Contract Route List/checkbox_ContractRouteCode', [('contractRouteCode') : contractRouteCode]), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Contract Route List/button_Download'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Contract Route List/li_Download Route Detail By Overview'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Contract Route List/div_Download Contract Route Overview By selected.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'('Excel Files\\Scenario 9\\Expected Data\\TC25\\Expected Contract Route Parts Overview.xlsx', 
    downloadedFile, 4, [6, 7, 8], [3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19])

WebUI.click(findTestObject('Scenario 13/S13_TC035/Contract Route List/button_Download'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC035/Contract Route List/li_Download Route Detail'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC035/Contract Route List/div_Download Contract Route Detail By selected.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'('Excel Files\\Scenario 9\\Expected Data\\TC25\\Expected Contract Route Parts.xlsx', 
    downloadedFile, 2, [5, 6, 7], [0, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12])

WebUI.closeBrowser()

