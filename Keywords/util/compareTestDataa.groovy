package util

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows


import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Workbook

import com.kms.katalon.core.util.KeywordUtil
import java.text.SimpleDateFormat

import internal.GlobalVariable

public class compareTestData {

	@Keyword
	def compareExcelFiles(String file11Path, String file22Path, int startRows, int endRows,int startCols,int endCols) {

		int nomatch=0
		try {
			FileInputStream fis1 = new FileInputStream(file11Path)
			FileInputStream fis2 = new FileInputStream(file22Path)
			Workbook workbook1 = new XSSFWorkbook(fis1)
			Workbook workbook2 = new XSSFWorkbook(fis2)

			// Assuming both Excel files have only one sheet, change accordingly if you have multiple sheets
			Sheet sheet1 = workbook1.getSheetAt(0)
			Sheet sheet2 = workbook2.getSheetAt(0)

			// Assuming both files have the same number of rows and columns, change accordingly if they differ
			//int numRows = sheet1.getLastRowNum() + 1
			//int numCols = sheet1.getRow(0).getLastCellNum()
			//int numRows = 8
			//int numCols = 24

			for (int i = startRows - 1; i < endRows; i++) {
				Row row1 = sheet1.getRow(i)
				Row row2 = sheet2.getRow(i)

				for (int j = startCols - 1; j < endCols; j++) {

					//it skip column Delivery Request Number because it auto generate
					if (j != 3) {

						Cell cell1 = row1.getCell(j)
						Cell cell2 = row2.getCell(j)

						if (cell1 != null && cell2 != null) {

							//Print the values of Cell 1 and 2
							println "Actual: ${cell1.toString()} and Expected: ${cell2.toString()} at Row: ${i + 1}, Col: ${j + 1}"

							//Log the Actual(Cell 1) and Expected values(Cell2):
							KeywordUtil.logInfo("Actual: ${cell1.toString()} and Expected: ${cell2.toString()} at Row: ${i + 1}, Col: ${j + 1}")


							// Compare cell values, you can implement your custom comparison logic here
							if (!cell1.toString().equals(cell2.toString())) {
								println "Difference found at Row: ${i + 1}, Col: ${j + 1}"
								nomatch=nomatch+1

							}


						}
					}


				}
			}

			println "Comparison completed."

			fis1.close()
			fis2.close()

		} catch (Exception e) {
			e.printStackTrace()
		}
		//WebUI.verifyMatch(nomatch, '0', false)
		return nomatch
	}

	@Keyword
	def getValueExcelFiles(String file11Path, int row, int col, int sheetnum) {

		int nomatch=0

		FileInputStream fis1 = new FileInputStream(file11Path)
		Workbook workbook1 = new XSSFWorkbook(fis1)

		// Assuming both Excel files have only one sheet, change accordingly if you have multiple sheets
		Sheet sheet1 = workbook1.getSheetAt(sheetnum)

		Row row1 = sheet1.getRow(row)
		Cell cell = row1.getCell(col)

		def cell1 = cell.toString()

		fis1.close()

		//WebUI.verifyMatch(nomatch, '0', false)
		return cell1
	}

	@Keyword
	public static Object getCellValue2(String filePath, int row, int column, int sheetNum) throws Exception {
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(fis);

		// Assuming both Excel files have only one sheet, change accordingly if you have multiple sheets
		Sheet sheet = workbook.getSheetAt(sheetNum);

		Row row1 = sheet.getRow(row);
		Cell cell = row1.getCell(column);

		// Get the cell type
		int cellType = cell.getCellType();

		// Get the cell value based on the cell type
		Object cellValue;
		switch (cellType) {
			case Cell.CELL_TYPE_NUMERIC:
				cellValue = cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue = cell.getNumericCellValue();
				break;
			default:
				cellValue = null;
		}

		if(cellValue==null) {
			cellValue=''
		}

		fis.close()

		return cellValue
	}

	@Keyword
	public static Object getCellValue3Nullto0(String filePath, int row, int column, int sheetNum) throws Exception {
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(fis);

		// Assuming both Excel files have only one sheet, change accordingly if you have multiple sheets
		Sheet sheet = workbook.getSheetAt(sheetNum);

		Row row1 = sheet.getRow(row);
		Cell cell = row1.getCell(column);

		// Get the cell type
		int cellType = cell.getCellType();

		// Get the cell value based on the cell type
		Object cellValue;
		switch (cellType) {
			case Cell.CELL_TYPE_NUMERIC:
				cellValue = cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue = cell.getNumericCellValue();
				break;
			default:
				cellValue = null;
		}

		if(cellValue==null) {
			cellValue=0
		}

		fis.close()

		return cellValue
	}

	@Keyword
	public static Object getCellValue3ToString(String filePath, int row, int column, int sheetNum) throws Exception {
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(fis);

		// Assuming both Excel files have only one sheet, change accordingly if you have multiple sheets
		Sheet sheet = workbook.getSheetAt(sheetNum);

		Row row1 = sheet.getRow(row);
		Cell cell = row1.getCell(column);

		// Get the cell type
		int cellType = cell.getCellType();

		// Get the cell value based on the cell type
		Object cellValue;
		switch (cellType) {
			case Cell.CELL_TYPE_NUMERIC:
				cellValue = cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue = cell.getNumericCellValue();
				break;
			default:
				cellValue = null;
		}

		if(cellValue==null) {
			cellValue=''
		}

		fis.close()



		return cellValue
	}

	@Keyword
	def getExcelDateCellValue(String filePath, int row, int column, int sheetNum, String formatDate) {

		Workbook workbook = WorkbookFactory.create(new File(filePath))

		Sheet sheet = workbook.getSheetAt(sheetNum)

		// Get the Row object for the desired row.
		Row rowex = sheet.getRow(row)

		// Get the Cell object for the desired cell.
		Cell cell = rowex.getCell(column)

		// Check the cell type to see if it is a date type.
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(cell)) {
			// Get the date value using the getDateCellValue() method.
			Date dateValue= cell.getDateCellValue()
			SimpleDateFormat sdf = new SimpleDateFormat(formatDate)
			String dateString = sdf.format(dateValue)
			return dateString

		} else {
			// Return null if the cell does not contain a date value.
			return null
		}

		// Close the workbook.
		workbook.close()

	}

	@Keyword
	def compareExcelFilesColumnList(String filePathActual, String filePathExpectation, int startRows,List<Integer> listColumn) {

		int nomatch=0
		try {
			FileInputStream fis1 = new FileInputStream(filePathActual)
			FileInputStream fis2 = new FileInputStream(filePathExpectation)
			Workbook workbook1 = new XSSFWorkbook(fis1)
			Workbook workbook2 = new XSSFWorkbook(fis2)

			// Assuming both Excel files have only one sheet, change accordingly if you have multiple sheets
			Sheet sheet1 = workbook1.getSheetAt(0)
			Sheet sheet2 = workbook2.getSheetAt(0)

			// Assuming both files have the same number of rows and columns, change accordingly if they differ
			//int numRows = sheet1.getLastRowNum() + 1
			//int numCols = sheet1.getRow(0).getLastCellNum()
			//int numRows = 8
			//int numCols = 24

			for (int i = startRows - 1; i < sheet1.lastRowNum; i++) {
				Row row1 = sheet1.getRow(i)
				Row row2 = sheet2.getRow(i)

				for (int colunum : listColumn) {

					//it skip column Delivery Request Number because it auto generate


					Cell cell1 = row1.getCell(j)
					Cell cell2 = row2.getCell(j)

					if (cell1 != null && cell2 != null) {
						// Compare cell values, you can implement your custom comparison logic here
						if (!cell1.toString().equals(cell2.toString())) {
							println "Difference found at Row: ${i + 1}, Col: ${j + 1}"
							nomatch=nomatch+1

						}


					}



				}
			}

			println "Comparison completed."

			fis1.close()
			fis2.close()

		} catch (Exception e) {
			e.printStackTrace()
		}
		//WebUI.verifyMatch(nomatch, '0', false)
		return nomatch
	}


	@Keyword
	def compareExcelFiles_ExcelFormulaCompatible(String file11Path, String file22Path, int startRows, int endRows,int startCols,int endCols) {

		int nomatch=0
		try {
			FileInputStream fis1 = new FileInputStream(file11Path)
			FileInputStream fis2 = new FileInputStream(file22Path)
			Workbook workbook1 = new XSSFWorkbook(fis1)
			Workbook workbook2 = new XSSFWorkbook(fis2)

			// Assuming both Excel files have only one sheet, change accordingly if you have multiple sheets
			Sheet sheet1 = workbook1.getSheetAt(0)
			Sheet sheet2 = workbook2.getSheetAt(0)

			// Assuming both files have the same number of rows and columns, change accordingly if they differ
			//int numRows = sheet1.getLastRowNum() + 1
			//int numCols = sheet1.getRow(0).getLastCellNum()
			//int numRows = 8
			//int numCols = 24

			for (int i = startRows - 1; i < endRows; i++) {
				Row row1 = sheet1.getRow(i)
				Row row2 = sheet2.getRow(i)

				for (int j = startCols - 1; j < endCols; j++) {

					//it skip column Delivery Request Number because it auto generate
					if (j != 3) {

						Cell cell1 = row1.getCell(j)
						Cell cell2 = row2.getCell(j)


						if (cell1 != null && cell2 != null) {


							// Evaluate cell1 and cell2 if they contain formulas
							if (cell1.getCellTypeEnum()  == CellType.FORMULA) {
								FormulaEvaluator evaluator = workbook1.getCreationHelper().createFormulaEvaluator();
								cell1 = evaluator.evaluateInCell(cell1);
							}
							if (cell2.getCellTypeEnum()  == CellType.FORMULA) {
								FormulaEvaluator evaluator = workbook2.getCreationHelper().createFormulaEvaluator();
								cell2 = evaluator.evaluateInCell(cell2);
							}


							//Print the values of Cell 1 and 2
							println "Actual: ${cell1.toString()} and Expected: ${cell2.toString()} at Row: ${i + 1}, Col: ${j + 1}"

							//Log the Actual(Cell 1) and Expected values(Cell2):
							KeywordUtil.logInfo("Actual: ${cell1.toString()} and Expected: ${cell2.toString()} at Row: ${i + 1}, Col: ${j + 1}")


							// Compare cell values, you can implement your custom comparison logic here
							if (!cell1.toString().equals(cell2.toString())) {
								println "Difference found at Row: ${i + 1}, Col: ${j + 1}"
								nomatch=nomatch+1

							}


						}
					}


				}
			}

			println "Comparison completed."

			fis1.close()
			fis2.close()

		} catch (Exception e) {
			e.printStackTrace()
		}
		//WebUI.verifyMatch(nomatch, '0', false)
		return nomatch
	}


}
