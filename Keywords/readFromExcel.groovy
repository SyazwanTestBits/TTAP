import java.text.DecimalFormat

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import com.kms.katalon.core.annotation.Keyword



















public class readFromExcel {

	@Keyword(keywordObject='Excel Actions')
	def getCellValue(String filePath, String sheetName, int rowNum, int colNum) {

		// Load the Excel file
		def fis = new FileInputStream(filePath)
		def workbook = new XSSFWorkbook(fis)
		def sheet = workbook.getSheet(sheetName)

		// Get the specified row
		def row = sheet.getRow(rowNum)

		// Get the specified cell
		def cell = row.getCell(colNum)

		// Get the value from the cell
		def cellValue

		switch (cell.getCellTypeEnum()) {
			case CellType.NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					cellValue = cell.getDateCellValue().toString()
				} else {
					cellValue = formatNumericValue(cell.getNumericCellValue())
				}
				break
			case CellType.STRING:
				cellValue = cell.getStringCellValue()
				break
			case CellType.FORMULA:
				CellType formulaType = cell.getCachedFormulaResultTypeEnum()
				if (formulaType == CellType.NUMERIC) {
					cellValue = formatNumericValue(cell.getNumericCellValue())
				} else if (formulaType == CellType.STRING) {
					cellValue = cell.getStringCellValue()
				}
				break
			default:
				cellValue = ""
		}

		workbook.close()
		fis.close()

		println("Value at row ${rowNum + 1}, column ${colNum + 1}: $cellValue")

		return cellValue
	}

	def formatNumericValue(doubleValue) {
		def decimalFormat = new DecimalFormat("#.##")
		return decimalFormat.format(doubleValue)
	}

}
