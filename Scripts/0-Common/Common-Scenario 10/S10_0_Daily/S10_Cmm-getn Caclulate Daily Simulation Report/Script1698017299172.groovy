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

//---------------FOR L3 PART---------------------------------------------------------
for (int index = 1; index <= testData.getRowNumbers(); index++) {
    partNo = testData.getValue('PartNo', index)

    rowIndex = (partIndex[partNo])

    int DateColumn = 34

    String dateUsage = CustomKeywords.'util.compareTestData.getCellValue2'(actualPath, rowIndex - 3, DateColumn, 0)

    def dateMapIn = [:]

    def dateMapOut = [:]

    def dateMapBalance = [:]

    def dateMapStockDay = [:]

    while (dateUsage != 'No Usage') {
        nextInboundDate = CustomKeywords.'util.compareTestData.getExcelDateCellValue'(actualPath, 6, DateColumn, 0, 'd MMM yyyy')

        excelUsageIn = CustomKeywords.'util.compareTestData.getCellValue3Nullto0'(actualPath, rowIndex - 7, DateColumn, 
            0)

        excelUsageOut = CustomKeywords.'util.compareTestData.getCellValue2'(actualPath, rowIndex - 3, DateColumn, 0)

        excelUsageBalance = CustomKeywords.'util.compareTestData.getCellValue2'(actualPath, rowIndex - 1, DateColumn, 0)
		
		not_run: excelUsageBalance= excelUsageOut-excelUsageIn

        excelUsageStockDay = CustomKeywords.'util.compareTestData.getCellValue3Nullto0'(actualPath, rowIndex, DateColumn, 
            0)

        dateMapIn.put(nextInboundDate, excelUsageIn)

        dateMapOut.put(nextInboundDate, excelUsageOut)

        dateMapBalance.put(nextInboundDate, excelUsageBalance)

        dateMapStockDay.put(nextInboundDate, excelUsageStockDay)

        DateColumn = (DateColumn + 1)

        excelUsageOut = CustomKeywords.'util.compareTestData.getCellValue2'(actualPath, rowIndex - 3, DateColumn, 0)

        dateUsage = excelUsageOut.toString()
    }
    
    println(dateMapOut)

    //-------------------------In -------------------------------------
    def weeklyInDayMap = CustomKeywords.'util.getValuePerWeek.lastValueWeeklyTotals'(dateMapIn)

    rowWeeklySimu = 8

    for (def mapValue4 : weeklyInDayMap) {
        def sumPerWeek4 = mapValue4.value

        CustomKeywords.'copyToExcel.exel3'(sumPerWeek4, index, rowWeeklySimu, 'Excel Files/Scenario 10/S10_TestCases_Data.xlsx', 
            sheetWriteUsagePerWeek)

        //CustomKeywords.'copyToExcel.exel3'(sumPerWeek4, index, rowWeeklySimu, 'Excel Files/Scenario 10/S10_TestCases_Data.xlsx',sheetForWeekReport)
        rowWeeklySimu = (rowWeeklySimu + 4)
    }
    
    //-----------------------Out Calculation-------------------------
    def weeklyOutSumMap = CustomKeywords.'util.getValuePerWeek.calculateWeeklyTotals'(dateMapOut)

    int rowWeeklySimu = 9

    for (def mapValue : weeklyOutSumMap) {
        def sumPerWeek = mapValue.value

        CustomKeywords.'copyToExcel.exel3'(sumPerWeek, index, rowWeeklySimu, 'Excel Files/Scenario 10/S10_TestCases_Data.xlsx', 
            sheetWriteUsagePerWeek)

        //CustomKeywords.'copyToExcel.exel3'(sumPerWeek, index, rowWeeklySimu, 'Excel Files/Scenario 10/S10_TestCases_Data.xlsx',sheetForWeekReport)
        rowWeeklySimu = (rowWeeklySimu + 4)
    }
    
    println((('Part: ' + partNo) + ' ') + weeklyOutSumMap)

    //-------------------Balanvce-----------------------
    'Balance'
    def weeklyBalanceSumMap = CustomKeywords.'util.getValuePerWeek.lastValueWeeklyTotals'(dateMapBalance)

    rowWeeklySimu = 10

    for (def mapValue2 : weeklyBalanceSumMap) {
        def sumPerWeek2 = mapValue2.value

        CustomKeywords.'copyToExcel.exel3'(sumPerWeek2, index, rowWeeklySimu, 'Excel Files/Scenario 10/S10_TestCases_Data.xlsx', 
            sheetWriteUsagePerWeek)

        //CustomKeywords.'copyToExcel.exel3'(sumPerWeek2, index, rowWeeklySimu, 'Excel Files/Scenario 10/S10_TestCases_Data.xlsx',sheetForWeekReport)
        rowWeeklySimu = (rowWeeklySimu + 4)
    }
    
    //----------------StockDay-----------------
    'StockDay'
    def weeklyStockDayMap = CustomKeywords.'util.getValuePerWeek.lastValueWeeklyTotals'(dateMapStockDay)

    rowWeeklySimu = 11

    for (def mapValue3 : weeklyStockDayMap) {
        def sumPerWeek3 = mapValue3.value

        CustomKeywords.'copyToExcel.exel3'(sumPerWeek3, index, rowWeeklySimu, 'Excel Files/Scenario 10/S10_TestCases_Data.xlsx', 
            sheetWriteUsagePerWeek)

        //CustomKeywords.'copyToExcel.exel3'(sumPerWeek3, index, rowWeeklySimu, 'Excel Files/Scenario 10/S10_TestCases_Data.xlsx',sheetForWeekReport)
        rowWeeklySimu = (rowWeeklySimu + 4)
    }
}

