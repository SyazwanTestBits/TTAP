package util

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import com.kms.katalon.core.annotation.Keyword

public class ExcelUtils {

	@Keyword
	def static int getLastRowNumber(String excelFilePath) {
		FileInputStream fis = new FileInputStream(excelFilePath)
		Workbook workbook = new XSSFWorkbook(fis)
		Sheet sheet = workbook.getSheetAt(0)

		int lastRowNumber = sheet.getLastRowNum()

		workbook.close()
		fis.close()

		return lastRowNumber
	}
	
	@Keyword
	def static int getLastColumnNumber(String excelFilePath, int sheetNum) {
		FileInputStream fis = new FileInputStream(excelFilePath)
		Workbook workbook = new XSSFWorkbook(fis)
		Sheet sheet = workbook.getSheetAt(sheetNum)

		def lastRow = sheet.getLastRowNum()

		// Find the last column in the last row
		def lastCell = sheet.getRow(lastRow).getLastCellNum()

		// Excel columns are 0-based, so add 1 to get the actual column number
		def lastColumnNumber = lastCell -1

		workbook.close()
		fis.close()

		return lastColumnNumber
	}
}
