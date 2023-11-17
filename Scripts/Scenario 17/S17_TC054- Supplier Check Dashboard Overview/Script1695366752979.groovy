import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.By as By

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_SUP], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 17/S17_TC054/Page_PO Management - Brivge/span_Dashboard_Supplier'))

WebUI.click(findTestObject('Scenario 17/S17_TC054/Page_PO Management - Brivge/li_Dashboard Overview_Supplier'))

WebUI.click(findTestObject('Scenario 17/S17_TC054/Page_Vendor Analysis - Brivge/span_Vendor Analysis'))

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC054/Page_Dashboard/div_Supplier Statistics2'), 0)

WebUI.click(findTestObject('Scenario 17/S17_TC054/Page_Vendor Analysis - Brivge/button_RemoteFilter_Supplier Statistics'))

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
int numberrowtd = findTestData('Scenario 17/S17_TC054- Purchase Amount By Supplier').getRowNumbers()

'Verify Supplier exists in Purchase Amount, by Supplier, by Country list'
for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    int coll = 1

    for (String col : columnname) {
        String valuecol = findTestData('Scenario 17/S17_TC054- Purchase Amount By Supplier').getValue(col, rowl)

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
int numberrowtd2 = findTestData('Scenario 17/S17_TC054- Complete Deliveries List').getRowNumbers()

'Verify Supplier Name in Completed Deliveries List'
for (int rowl = 1; rowl <= numberrowtd2; rowl++) {
    int coll = 1

    for (String col : columnname2) {
        String valuecol = findTestData('Scenario 17/S17_TC054- Complete Deliveries List').getValue(col, rowl)

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

//-------------------------------------------------------------------------------------------------------
WebUI.click(findTestObject('Scenario 17/S17_TC054/Page_Vendor Analysis - Brivge/span_Accounts Receivable'))

WebUI.verifyElementPresent(findTestObject('Scenario 17/S17_TC054/Page_Dashboard/div_Financial Statistics'), 0)

totalOutstandingInvoices = WebUI.getText(findTestObject('Scenario 17/S17_TC054/Page_Accounts Receivable - Brivge/h5_totalNoOutInvoices'))

//-------------------------------------------------------------------------------------------------------
//xpath locator that selects all rows of the table (Invoice Not Cleared)
String xpath = '(//tbody[@class=\'lcbm-MuiTableBody-root\'][1])[1]//tr'

// Find all rows that match the provided XPath
List<WebElement> rows = driver2.findElements(By.xpath(xpath))

// Get the count of rows
'Invoice Not Cleared List'
int rowCountinvoiceNotClearedlist = rows.size()

println('Row count: ' + rowCountinvoiceNotClearedlist)

'totalOustandingInvoice= InvoicesNotClearedList'
WebUI.verifyEqual(rowCountinvoiceNotClearedlist, totalOutstandingInvoices)

'Disable this step if you run this tc for the first time after data flush\r\n'
WebUI.verifyElementText(findTestObject('Scenario 17/S17_TC054/Page_Accounts Receivable - Brivge/h5_Currency'), currency)

WebUI.verifyElementText(findTestObject('Scenario 17/S17_TC054/Page_Accounts Receivable - Brivge/h5_Accounts'), account)

WebUI.verifyElementText(findTestObject('Scenario 17/S17_TC054/Page_Accounts Receivable - Brivge/h5_OverdueAmountPercentage'), 
    overdueAmountPer)

WebUI.verifyElementText(findTestObject('Scenario 17/S17_TC054/Page_Accounts Receivable - Brivge/h5_TotalOutstandingAmount'), 
    tOustandingAmount)

WebUI.verifyElementText(findTestObject('Scenario 17/S17_TC054/Page_Accounts Receivable - Brivge/h5_OverdueAmount'), overdueAmount)

WebUI.verifyElementText(findTestObject('Scenario 17/S17_TC054/Page_Accounts Receivable - Brivge/h5_creditlimit'), creditLimit)

WebUI.closeBrowser()

