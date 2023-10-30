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
import java.time.LocalDate as LocalDate
import java.text.SimpleDateFormat as SimpleDateFormat

actualPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

println(actualPath)

partIndex = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndicesSkipNull'(actualPath, 1, 7)

println(partIndex)

for (int index = 1; index <= testData.getRowNumbers(); index++) {
    partNo = testData.getValue('PartNo', index)

    rowIndex = (partIndex[partNo])

    String dateUsage = ''

    int DateColumn = 34

    def dateMap = [:]

    while (dateUsage != 'No Usage') {
        nextInboundDate = CustomKeywords.'util.compareTestData.getExcelDateCellValue'(actualPath, 6, DateColumn, 0, 'd MMM yyyy')

        excelUsage = CustomKeywords.'util.compareTestData.getCellValue2'(actualPath, rowIndex - 3, DateColumn, 0)

        dateMap.put(nextInboundDate, excelUsage)

        DateColumn = (DateColumn + 1)

        excelUsage = CustomKeywords.'util.compareTestData.getCellValue2'(actualPath, 12, DateColumn, 0)

        dateUsage = excelUsage.toString()
    }
    
    def weeklySumMap = CustomKeywords.'util.getValuePerWeek.calculateWeeklyTotals'(dateMap)

    int rowWeeklySimu = 1

    for (def mapValue : weeklySumMap) {
        def sumPerWeek = mapValue.value

        CustomKeywords.'copyToExcel.exel3'(sumPerWeek, index, rowWeeklySimu, 'Excel Files/Scenario 10/S10_TestCases_Data.xlsx', 
            sheetWriteUsagePerWeek)

        rowWeeklySimu = (rowWeeklySimu + 1)
    }
    
    println((('Part: ' + partNo) + ' ') + weeklySumMap)
}

