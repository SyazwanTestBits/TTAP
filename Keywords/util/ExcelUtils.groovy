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
}
