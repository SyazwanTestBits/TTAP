import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 17/S17_TC020/Page_PO Management - Brivge/span_Dashboard'))

WebUI.click(findTestObject('Scenario 17/S17_TC020/Page_Home Page - Brivge/li_Dashboard Overview'))

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC020/Page_PO Management - Brivge/div_Order Statistics'), 0)

'Verify CO no. present in Overview PO List'
WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC020/p_PO_no', [('customerOrderNo') : CustomerOrderNo]), 0)

WebUI.click(findTestObject('Scenario 17/S17_TC020/Page_PO Management - Brivge/span_Cost Monitoring'))

'Verify CO no. present in Purchase Record'
WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC020/p_PO_no_COMonitoring', [('customerOrderNo') : CustomerOrderNo]), 
    0)

totalNoPO = WebUI.getText(findTestObject('Scenario 17/S17_TC020/div_totalNoPO'))

WebDriver driver = DriverFactory.getWebDriver()

println(totalNoPO)

//xpath locator that selects all rows of the first table
String xpath = '(//tbody[@class=\'lcbm-MuiTableBody-root\'][1])[1]//tr'

// Find all rows that match the provided XPath
List<WebElement> rows = driver.findElements(By.xpath(xpath))

// Get the count of rows
int rowCountPOlist = rows.size()

println('Row count: ' + rowCountPOlist)

'Verify POMonitoringList= totalNoPO '
WebUI.verifyEqual(rowCountPOlist, totalNoPO)

KeywordUtil.logInfo("totalNoPO : $totalNoPO , rowCountPOlist: $rowCountPOlist")

WebUI.closeBrowser()

