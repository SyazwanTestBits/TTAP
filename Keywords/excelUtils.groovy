import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil













public class excelUtils {

	@Keyword
	def unprotectExcelSheet(String path) {
		// Open the workbook and sheet
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);

		// Unprotect the sheet if it's protected with a password
		sheet.protectSheet(null)

		// Create an evaluator to recalculate formulas in the workbook
		def evaluator = workbook.getCreationHelper().createFormulaEvaluator()

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

		KeywordUtil.logInfo('Finish Unprotect Excel: ' + path)
	}

	@Keyword
	def clearOutValueExcel(String filepath, int startRow, int startCol) {

		// Load the Excel file
		def file = new FileInputStream(filepath)
		def workbook = new XSSFWorkbook(file)
		def sheet = workbook.getSheetAt(0) // Assuming it's the first sheet (index 0)

		// Iterate through rows and columns starting from (10,10) to the end
		for (int rowIdx = startRow; rowIdx <= sheet.lastRowNum; rowIdx++) {
			def row = sheet.getRow(rowIdx)
			if (row != null) {
				for (int colIdx = startCol; colIdx < row.getLastCellNum(); colIdx++) {
					def cell = row.getCell(colIdx, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
					cell.setCellValue('')
				}
			}
		}

		// Save the changes to the Excel file
		file.close()
		def outFile = new FileOutputStream(filepath)
		workbook.write(outFile)
		outFile.close()

		// Close the workbook
		workbook.close()

	}
	
	@Keyword
	def clearOutValueExcel2(String filepath, int startRow, int startCol, int endRow, int endCol) {
	
		// Load the Excel file
		def file = new FileInputStream(filepath)
		def workbook = new XSSFWorkbook(file)
		def sheet = workbook.getSheetAt(0) // Assuming it's the first sheet (index 0)
	
		// Iterate through rows and columns from startRow to endRow and startCol to endCol
		for (int rowIdx = startRow - 1; rowIdx < endRow; rowIdx++) { // Subtract 1 because Excel rows and columns are 0-based
			def row = sheet.getRow(rowIdx)
			if (row != null) {
				for (int colIdx = startCol - 1; colIdx < endCol; colIdx++) { // Subtract 1 because Excel rows and columns are 0-based
					def cell = row.getCell(colIdx, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
					cell.setCellValue('')
				}
			}
		}
	
		// Save the changes to the Excel file
		file.close()
		def outFile = new FileOutputStream(filepath)
		workbook.write(outFile)
		outFile.close()
	
		// Close the workbook
		workbook.close()
	}
	
	
	
	
	
}
