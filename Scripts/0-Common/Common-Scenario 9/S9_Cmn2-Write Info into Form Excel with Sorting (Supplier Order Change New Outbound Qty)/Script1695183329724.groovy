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

for (int index : (1..datafile.getRowNumbers())) {
    def datafilePartNo = datafile.getValue('PartsNo', index)

    def fileRowIndex = mapDataIndices[datafilePartNo]

    for (int index2 : (1..daterange)) {
        def dataValue = datafile.getValue(columnNameHeader + index2, index)

        if (dataValue != '0') {
            CustomKeywords.'copyToExcel.excelSetCellStyleDoubleDR'(dataValue, fileRowIndex, columnIndexMinusOne + index2, 
                downloadedFormPath, downloadedFormSheetname)
        }else {
			CustomKeywords.'copyToExcel.exel2'('', fileRowIndex, columnIndexMinusOne + index2, 
                downloadedFormPath, downloadedFormSheetname)
		}
    }
}

println('Wait for writing to finish')