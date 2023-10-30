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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

actualPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

println(actualPath)

partIndex = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(actualPath, 1, 7)


for (int row = 1; row <= expectationPath.getRowNumbers(); row++) {
    partNo = expectationPath.getValue('PartNo', row)

    rowIndex = (partIndex[partNo])

    for (def pair : columnNameStock) {
        def columnName = pair.key

        def columnIndex = pair.value

        def dataValue = expectationPath.getValue(columnName, row)

        String excelValue = CustomKeywords.'util.compareTestData.getCellValue2'(actualPath, rowIndex, columnIndex, 0)

        KeywordUtil.logInfo((((((('In row: ' + rowIndex) + ' column: ') + columnName) + ', actual: ') + excelValue) + ' expectation: ') + 
            dataValue)

        WebUI.verifyMatch(excelValue, dataValue, false)
    }
    //------------------------------------------------------------------------------------------------------------
	int columnActualExcel= 29
	
	println(expectationPath.getColumnCount())
	
    for (int col =9; col < expectationPath.getColumnCount();col++) {

        def dataValue = expectationPath.getValue(col, row)
		
		println(dataValue)
		
		println dataValue.getClass()
		
		decisize0=0
		
		if (col%4 == 0) {
			
			BigDecimal dataValueDouble = new BigDecimal(dataValue)
			
			int intValue = (int) dataValueDouble
			
			dataValue=intValue.toString()
		}else {
			
			def deciSize0=dataValue.split("\\.")
			
			decisize0= deciSize0[1].size()
		}
		
		if (decisize0 > 3) {
			
			BigDecimal dataValueDouble = new BigDecimal(dataValue)
			
			println(dataValueDouble)
			
			dataValueDouble=dataValueDouble.setScale(3, BigDecimal.ROUND_HALF_UP)
			
			dataValue=dataValueDouble.toString()
		}
		
		
		
		//----------------------------------------------------------------------------------------------------------------------
		
        String excelValue = CustomKeywords.'util.compareTestData.getCellValue2'(actualPath, rowIndex, columnActualExcel, 0)
		
		println(excelValue)
		
		decisize=0
		
		if(col%4 != 0) {
			
			def deciSize=excelValue.split("\\.")
			
			decisize= deciSize[1].size() 
		
		}
		
		if (decisize > 3) {
			
			BigDecimal excelValueDouble = new BigDecimal(excelValue)
			
			println(excelValueDouble)
			
			excelValueDouble=excelValueDouble.setScale(3, BigDecimal.ROUND_HALF_UP)
			
			excelValue=excelValueDouble.toString()
		}
		

		KeywordUtil.logInfo("For part: "+partNo+" in column excel:"+columnActualExcel+" column in tesData: "+col+" Actual: "+ excelValue+" Expectation: "+dataValue)

	    WebUI.verifyMatch(excelValue, dataValue, false)
			
		columnActualExcel=columnActualExcel+1
		
    }
}


//---------------------------------L2 Part--------------------------------------------

for (int row=1;row<= testDataL2.getRowNumbers();row++) {
	
	partNo= testDataL2.getValue('Part No',row)
	
	indexL1= partIndex[partNo]
	
	for (def pair : columnNameStock) {
		def columnName = pair.key

		def columnIndex = pair.value

		def dataValue = testDataL2.getValue(columnName, row)

		String excelValue = CustomKeywords.'util.compareTestData.getCellValue2'(actualPath, indexL1, columnIndex, 0)

		KeywordUtil.logInfo((((((('In row: ' + rowIndex) + ' column: ') + columnName) + ', actual: ') + excelValue) + ' expectation: ') +
			dataValue)

		WebUI.verifyMatch(excelValue, dataValue, false)
	}
	
	int columnActualExcel= 29
	
	int col =9
	
	while( col < expectationPath.getColumnCount()) {
		
		String excelValue = CustomKeywords.'util.compareTestData.getCellValue2'(actualPath, indexL1, columnActualExcel, 0)
		
		WebUI.verifyMatch(excelValue,valueInL2, false)
		
		columnActualExcel= columnActualExcel +1
		
		col = col+1
		
		excelValue = CustomKeywords.'util.compareTestData.getCellValue2'(actualPath, indexL1, columnActualExcel, 0)
		
		WebUI.verifyMatch(excelValue,'No Usage', false)
		
		columnActualExcel= columnActualExcel +1
		
		col = col+1
		
		excelValue = CustomKeywords.'util.compareTestData.getCellValue2'(actualPath, indexL1, columnActualExcel, 0)
		
		WebUI.verifyMatch(excelValue,'-', false)
		
		columnActualExcel= columnActualExcel +1
		
		col = col+1
		
		excelValue = CustomKeywords.'util.compareTestData.getCellValue2'(actualPath, indexL1, columnActualExcel, 0)
		
		WebUI.verifyMatch(excelValue,'-', false)
		
		columnActualExcel= columnActualExcel +1
		
		col = col+1
		
		
	}
	
	
}


