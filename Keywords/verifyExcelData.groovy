import java.text.SimpleDateFormat

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.util.KeywordUtil


public class verifyExcelData {
	@Keyword
	def verifyDynamicExcelCellValue(String excelFilePath, String sheetName, String startDate, String endDate, String expectedValue) {
		try {
			// Specify the Excel file path
			def excelData = TestDataFactory.findTestData("YourTestDataSheetName")
			excelData.setExcelFile(excelFilePath, sheetName)

			// Find the header row to locate the date columns
			def headerRow = excelData.getAllData().get(0)

			// Find the column numbers for the start and end dates
			int startColumn = -1
			int endColumn = -1

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy")

			for (int colNum = 0; colNum < headerRow.size(); colNum++) {
				def cellValue = headerRow.get(colNum).toString().trim()

				if (cellValue.equals(startDate)) {
					startColumn = colNum
				} else if (cellValue.equals(endDate)) {
					endColumn = colNum
				}
			}

			if (startColumn != -1 && endColumn != -1) {
				// Loop through rows to find the expected value within the date range
				def rows = excelData.getAllData().subList(1, excelData.getAllData().size())
				for (def row : rows) {
					def cellValue = row.get(startColumn).toString().trim()

					if (cellValue.equals(startDate)) {
						// Verify the expected value in the corresponding row
						def valueCellValue = row.get(endColumn).toString().trim()

						if (valueCellValue.equals(expectedValue)) {
							println("Found the expected value '$expectedValue' in the date range: $startDate to $endDate")
							return
						}
					}
				}

				// If the expected value is not found, mark the verification as failed
				throw new Exception("Expected value '$expectedValue' not found in the date range: $startDate to $endDate")
			} else {
				throw new Exception("Start date '$startDate' or end date '$endDate' not found in the header row.")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Verification failed: " + e.getMessage())
		}
	}

	@Keyword(keywordObject='Excel Verification')
	static void verifyDynamicSort(String expectedFilePath, String downloadedFilePath, int partsNoColumnIndex, List<Integer> partsNoRowIndices, List<Integer> columnsToVerify) {
		try {
			FileInputStream expectedFis = new FileInputStream(new File(expectedFilePath));
			FileInputStream downloadedFis = new FileInputStream(new File(downloadedFilePath));

			Workbook expectedWorkbook = new XSSFWorkbook(expectedFis);
			Workbook downloadedWorkbook = new XSSFWorkbook(downloadedFis);

			Sheet expectedSheet = expectedWorkbook.getSheetAt(0);
			Sheet downloadedSheet = downloadedWorkbook.getSheetAt(0);

			List<String> mismatchDetails = new ArrayList<>();

			// Sort the downloaded sheet based on the specified column
			sortSheet(downloadedSheet, partsNoColumnIndex);

			for (int rowIndex : partsNoRowIndices) {
				Row downloadedRow = downloadedSheet.getRow(rowIndex);

				if (downloadedRow == null) {
					mismatchDetails.add("Row not found in the downloaded Excel file for index " + rowIndex);
					continue;
				}

				String downloadedPartsNo = downloadedRow.getCell(partsNoColumnIndex).toString().trim();

				// Find the corresponding row in the expected sheet
				Row expectedRow = findRowByPartsNo(expectedSheet, partsNoColumnIndex, downloadedPartsNo);

				if (expectedRow == null) {
					mismatchDetails.add("Parts No. not found in the expected Excel file for partsNo: " + downloadedPartsNo);
					continue;
				}

				for (int colIndex : columnsToVerify) {
					Cell expectedCell = expectedRow.getCell(colIndex);
					Cell downloadedCell = downloadedRow.getCell(colIndex);

					if (expectedCell != null && downloadedCell != null) {
						String expectedValue = expectedCell.toString().trim();
						String downloadedValue = downloadedCell.toString().trim();

						if (!expectedValue.equals(downloadedValue)) {
							mismatchDetails.add("Mismatch in cell values for row " + rowIndex + ", column " + colIndex + ". Expected: " + expectedValue + ", Downloaded: " + downloadedValue);
						} else {
							KeywordUtil.logInfo("Cell values match for row " + rowIndex + ", column " + colIndex + ". Expected: " + expectedValue + ", Downloaded: " + downloadedValue);
						}
					} else {
						mismatchDetails.add("Cell is null in row " + rowIndex + " and column " + colIndex);
					}
				}

			}

			expectedFis.close();
			downloadedFis.close();

			for (String mismatchDetail : mismatchDetails) {
				KeywordUtil.logInfo(mismatchDetail);
			}

			if (mismatchDetails.isEmpty()) {
				KeywordUtil.logInfo("All data checks passed. Downloaded data matches expected data.");
			} else {
				// If there are mismatch details, throw an exception to fail the test case
				throw new AssertionError("Excel data verification failed.");
			}

		} catch (Exception e) {
			KeywordUtil.logInfo("Error verifying dynamic sort: " + e.getMessage());
		}
	}

	// The rest of the code remains the same


	static void sortSheet(Sheet sheet, int columnToSortBy) {
		// Implement sorting logic here based on the specified column
		// You can use a custom sorting algorithm or Apache POI's sorting features
		// For simplicity, you can use Collections.sort() on a list of rows
		// Example: List<Row> rows = getRowsAsList(sheet);
		//          Collections.sort(rows, new YourComparator(columnToSortBy));
		//          clearSheet(sheet);
		//          addSortedRowsToSheet(sheet, rows);
	}

	// Implement the methods getRowsAsList, clearSheet, and addSortedRowsToSheet as needed

	// Helper method to find a row in the sheet by "Parts No."
	static Row findRowByPartsNo(Sheet sheet, int partsNoColumnIndex, String partsNo) {
		for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				String currentPartsNo = row.getCell(partsNoColumnIndex).toString().trim();
				if (partsNo.equals(currentPartsNo)) {
					return row;
				}
			}
		}
		return null; // Return null if not found
	}


}


