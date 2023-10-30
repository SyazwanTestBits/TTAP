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

for (def index : (1..datafile.getRowNumbers())) {
	
	def orderCalcGroupNo = datafile.getValue('OrderCalculationGroupingNo', index)
	
    WebUI.setText(findTestObject('Page_OrderCalcGrouping/input_Search_OrderCalcGrouping'), orderCalcGroupNo)

    for (def pair : datafileColsVerification) {
        def columnName = pair.key

        def columnIndex = pair.value

        def dataValue = datafile.getValue(columnName, index)

        WebUI.verifyElementText(findTestObject('Page_OrderCalcGrouping/tr1_td_OrderCalcGroupingInfo', [('td_index_ordercalcgroup') : columnIndex]), 
            dataValue)
    }
    
    CustomKeywords.'commonUtils.clearElementText'(findTestObject('Page_OrderCalcGrouping/input_Search_OrderCalcGrouping'))
}

