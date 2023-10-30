import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/span_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC049 TC054/li_CO Monitoring List'))

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC029/p_verifyStatus', [('orderNo') : RegularOrderNo]), 'Confirmed')

WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC049/p_verifyDelayStatus', [('COid') : RegularOrderNo]), 'Normal')

WebUI.click(findTestObject('Scenario 12/SC12_TC049/p_checkbox', [('COid') : RegularOrderNo]))

WebUI.click(findTestObject('Scenario 12/SC12_TC049/button_download'))

WebUI.click(findTestObject('Scenario 12/SC12_TC049/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC049/div_Download Customer Order by Excel.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedCO, downloadedFile, 1, [23, 24, 25, 26, 27, 28], [1, 2, 3, 5, 6
        , 7, 8, 9, 10, 11, 12, 13, 14, 15])

WebUI.click(findTestObject('Scenario 12/SC12_TC049/p_checkbox', [('COid') : RegularOrderNo]))

WebUI.click(findTestObject('Scenario 12/SC12_TC049/p_detailButton', [('COid') : RegularOrderNo]))

WebUI.waitForElementPresent(findTestObject('Scenario 12/SC12_TC049/h3_CO Monitoring Detail'), 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_ForecastDetail/input_SalesOrderNo'), 'value', RegularOrderNo, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC046/input_detailStatus'), 'value', 'Confirmed', 0)

WebUI.closeBrowser()

