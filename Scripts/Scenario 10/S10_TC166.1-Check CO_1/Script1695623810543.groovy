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

def orderNo = CO_numberList[0]

WebUI.click(findTestObject('Scenario 10/S10_TC177/p_detailButton', [('orderNo') : orderNo]))

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC177/input_Basic Info Detail_orderNo'), 'value', orderNo, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC177/input_Basic Info Detail_status'), 'value', 'Processing', 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 10/S10_TC177/input_Basic Info Detail_contractNo'), 'value', contractNoL2, 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC177/p_Parts No'))

firstRow = WebUI.getText(findTestObject('Scenario 10/S10_TC177/p_partCheck', [('lrow') : 1, ('lcol') : 1]), FailureHandling.STOP_ON_FAILURE)

def testData=findTestData('Scenario 10/S10_TC166-Customer Check CO_1')

if(firstRow != findTestData('Scenario 10/S10_TC166-Customer Check CO_1').getValue('PartsNo', 1)) {
	
	testData=findTestData('Scenario 10/S10_TC166-Customer Check CO_2')
}

int numberrowtd = testData.getRowNumbers()

for (int rowl = 1; rowl <= numberrowtd; rowl++) {
    int coll = 1

    for (String colname : columnname) {
        String valuecol =testData.getValue(colname, rowl)

        WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC177/p_partCheck', [('lrow') : rowl, ('lcol') : coll]), 
            valuecol)

        coll = (coll + 1)
    }
}

WebUI.delay(0)

