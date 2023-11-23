import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_CO Monitoring List'))

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), CONumber)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/input_checkall'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_Download_CO'))

WebUI.click(findTestObject('Scenario 12/SC12_TC014/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC049/div_Download Customer Order by Excel.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

'Need to change expected file'
CustomKeywords.'verifyExcelData.verifyDynamicSort'('Excel Files\\Scenario 1\\Expected Data\\TC196\\Expected Customer Order.xlsx', 
    downloadedFile, 2, [23, 24, 25], [1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16])

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC143/Page_SO Monitoring List - Brivge/div_Processing'), 'Processing')

WebUI.click(findTestObject('Scenario 1/S1_TC143/Page_SO Monitoring List - Brivge/button_Detail'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Scenario 1/S1_TC143/Page_SO Monitoring Detail - Brivge/h3_SO Monitoring Detail'), 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 1/S1_TC143/Page_SO Monitoring Detail - Brivge/input_Please input related basic information_status'), 
    'value', 'Processing', 0)

WebUI.scrollToElement(findTestObject('Scenario 1/S1_TC143/Page_SO Monitoring Detail - Brivge/p_Copyright  2021 BriVge. All rights reserved'), 
    0)

WebUI.click(findTestObject('Scenario 1/S1_TC148/Page_CO Monitoring Detail - Brivge/p_Customer Parts No'))

not_run: CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC178/p_Parts No'), 0)

int numberrowtd = findTestData('Scenario 1/S1_TC196-Shipping Plan List').getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    int coll = 1

    for (String colname : columnname) {
        String valuecol = findTestData('Scenario 1/S1_TC196-Shipping Plan List').getValue(colname, rowl)

        def actualValue = WebUI.getText(findTestObject('Scenario 1/S1_TC148/Page_CO Monitoring Detail - Brivge/div_s1001', 
                [('lrow') : rowl, ('lcol') : coll]))

        KeywordUtil.logInfo("Expected value: $valuecol ; Actual value: $actualValue")

        WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC148/Page_CO Monitoring Detail - Brivge/div_s1001', [('lrow') : rowl
                    , ('lcol') : coll]), valuecol)

        coll = (coll + 1)
    }
}

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

