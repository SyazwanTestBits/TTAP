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
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.interactions.Actions as Actions

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

WebUI.click(findTestObject('Scenario 17/S17_TC054/Page_Vendor Analysis - Brivge/span_Vendor Analysis'))

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC054/Page_Dashboard/div_Supplier Statistics2'), 0)

WebUI.click(findTestObject('Scenario 17/S17_TC076/Page_Vendor Analysis - Brivge/button_RemoteFilter_Supplier Statistics_Customer'))

WebUI.click(findTestObject('Scenario 17/S17_TC054/Page_Vendor Analysis - Brivge/input__startMonth'))

WebUI.click(findTestObject('Scenario 17/S17_TC054/Page_Vendor Analysis - Brivge/div_startYear', [('startYear') : startYear]))

WebUI.click(findTestObject('Scenario 17/S17_TC054/Page_Vendor Analysis - Brivge/div_startMonth', [('startMonth') : startMonth]))

WebUI.click(findTestObject('Scenario 17/S17_TC054/Page_Vendor Analysis - Brivge/Page_Vendor Analysis - Brivge/input_endMonth'))

WebUI.click(findTestObject('Scenario 17/S17_TC054/Page_Vendor Analysis - Brivge/Page_Vendor Analysis - Brivge/div_endYear', 
        [('endYear') : endYear]))

WebUI.click(findTestObject('Scenario 17/S17_TC054/Page_Vendor Analysis - Brivge/Page_Vendor Analysis - Brivge/div_endMonth', 
        [('endMonth') : endMonth]))

WebUI.delay(1)

WebUI.click(findTestObject('Scenario 17/S17_TC054/Page_Vendor Analysis - Brivge/Page_Vendor Analysis - Brivge/button_Search_SupplierStatistics'))

WebDriver driver2 = DriverFactory.getWebDriver()

List<WebElement> supplierCode = WebUI.findWebElements(findTestObject('Scenario 17/S17_TC054/svg_SupplierName'), 30)

'Click Supplier Country on Chart'
for (int i = 0; i < supplierCode.size(); i++) {
    countryCode = (supplierCode[i]).getText()

    println(countryCode)

    if (countryCode.equalsIgnoreCase('PK')) {
        Actions builder = new Actions(driver2)

        builder.click(supplierCode[i]).build().perform()

        break
    }
}

//-------------------------------------------------------------------
int numberrowtd = findTestData('Scenario 17/S17_TC076- Purchase Amount By Supplier').getRowNumbers()

'Verify Supplier exists in Purchase Amount, by Supplier, by Country list'
for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    int coll = 1

    for (String col : columnname) {
        String valuecol = findTestData('Scenario 17/S17_TC076- Purchase Amount By Supplier').getValue(col, rowl)

        String actualValue = WebUI.getText(findTestObject('Scenario 17/S17_TC054/table_PurchaseBySupplier', [('lrow') : rowl
                    , ('lcol') : coll]))

        //println("Row: $rowl, Column: $coll")
        //println("Expected Value: $valuecol")
        //println("Actual Value: $actualValue")
        println("$actualValue same as expected -->  $valuecol")

        //Log actual and expected results
        KeywordUtil.logInfo("Actual: $actualValue and Expected: $valuecol")

        coll = (coll + 1)
    }
}

//-------------------------------------------------------------------------------------------------------
int numberrowtd2 = findTestData('Scenario 17/S17_TC076- Complete Deliveries List').getRowNumbers()

'Verify Supplier Name in Completed Deliveries List'
for (int rowl = 1; rowl <= numberrowtd2; rowl++) {
    int coll = 1

    for (String col : columnname2) {
        String valuecol = findTestData('Scenario 17/S17_TC076- Complete Deliveries List').getValue(col, rowl)

        String actualValue = WebUI.getText(findTestObject('Scenario 17/S17_TC054/table_CompleteDeliveries', [('lrow') : rowl
                    , ('lcol') : coll]))

        //println("Row: $rowl, Column: $coll")
        //println("Expected Value: $valuecol")
        //println("Actual Value: $actualValue")
        println("$actualValue same as expected -->  $valuecol")

        //Log actual and expected results
        KeywordUtil.logInfo("Actual: $actualValue and Expected: $valuecol")

        coll = (coll + 1)
    }
}

WebUI.closeBrowser()

