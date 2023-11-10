import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

absPath = CustomKeywords.'ManageFiles.getFileAbsolutePath'(dateExcelRelPath)

for (def index : (1..datafile.getRowNumbers())) {
	
    def commaSeparatedString = datafile.getValue(columnHeaderListIndex, index)

    def indexListNumbers = commaSeparatedString.split(',').collect({ 
                ((it) as Integer)
        })

    int j = 1

    for (int i = 0; i < indexListNumbers.size(); i++) {
        def columnName = columnHeaderQty + j

        def listNum = indexListNumbers[i]

        def dataValueQty = datafile.getValue(columnName, index)

        CustomKeywords.'copyToExcel.excelSetCellStyleDoubleDR'(dataValueQty, index + startRowFormMinusOne, listNum, downloadedFormPath, 
            downloadedFormSheetname)

        if (listNum != 0) {
            dateValue = CustomKeywords.'readFromExcel.getCellValue'(downloadedFormPath, downloadedFormSheetname, 9, listNum)
			
            formattedDate = CustomKeywords.'commonUtils.parseDateInfoDesiredDateFormat2'(dateValue, 'EEE MMM dd HH:mm:ss zzz yyyy', 
                'MMM dd, yyyy')

            CustomKeywords.'copyToExcel.exel2'(formattedDate, index, i, absPath, dateSheetName)
        } else {
            CustomKeywords.'copyToExcel.exel2'('NULL', index, i, absPath, dateSheetName)
        }
        
        j++
    }
}

println('Wait for writing to finish')

