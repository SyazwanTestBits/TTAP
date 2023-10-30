package util
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.DataFormat
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.jsoup.select.Evaluator.IsEmpty

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class ExcelActions2 {

	@Keyword(keywordObject='Excel Actions')
	def verifyCopyRolePermissionList(String role) {
		// Remove leading and trailing whitespace from the role string
		String copyRole = role.trim()

		// Find the test data file based on the role
		def testData = findTestData('Data Files/Scenario 13/Role Permissions-' + copyRole)

		// Find the input search role object
		def input_searchRole = findTestObject('Object Repository/Page_RoleList/Page_ViewRoleDetail/input_SearchRolePermission')

		// Iterate over each row in the test data
		for (def rowNum = 1; rowNum <= testData.getRowNumbers(); rowNum++) {
			// Get the role description from the test data
			def role_description = testData.getValue('RoleDescription', rowNum)

			// Set the role description in the input search role field
			WebUI.setText(input_searchRole, role_description, FailureHandling.CONTINUE_ON_FAILURE)

			// Wait for the role description element to be present
			WebUI.waitForElementPresent(findTestObject('Object Repository/Page_RoleList/Page_ViewRoleDetail/div_Dt_RolePermission_Description' , [('roleDescription'):role_description]), 0, FailureHandling.CONTINUE_ON_FAILURE)

			// Verify the presence of the role description element
			WebUI.verifyElementPresent(findTestObject('Object Repository/Page_RoleList/Page_ViewRoleDetail/div_Dt_RolePermission_Description' , [('roleDescription'):role_description]), 0, FailureHandling.CONTINUE_ON_FAILURE)

			// Verify the presence of the role checked element
			WebUI.verifyElementPresent(findTestObject('Object Repository/Page_RoleList/Page_ViewRoleDetail/div_Dt_RolePermission_Checked', [('roleDescription'):role_description]), 0, FailureHandling.CONTINUE_ON_FAILURE)

			// Log information about the checked role description
			KeywordUtil.logInfo('Role Description: ' + role_description + ' is Checked')

			// Clear the input search role field for the next iteration
			CustomKeywords.'commonUtils.clearElementText'(input_searchRole)
		}
	}

	/**
	 * Writes data into an Excel file for regular place order.
	 * 
	 * @param path        The file path of the Excel file.
	 * @param contract_no The name of the sheet within the Excel file.
	 */
	@Keyword(keywordObject='Excel Actions')
	def writeIntoExcelPlaceOrderRegular(String path, String contract_no) {
		// Find test data
		def testData = findTestData('Data Files/Scenario 13/S13_TC041-Customer Place Order (Regular)')

		// Write inbound date
		writeIntoExcelPlaceOrderRegularInboundDate(path, contract_no)

		// Open the workbook and sheet
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(contract_no);

		// Unprotect the sheet if it's protected with a password
		sheet.protectSheet(null)

		// Create an evaluator to recalculate formulas in the workbook
		def evaluator = workbook.getCreationHelper().createFormulaEvaluator()

		// Create number format style
		DataFormat dataFormat = workbook.createDataFormat()
		CellStyle numberStyle = workbook.createCellStyle()
		numberStyle.setDataFormat(dataFormat.getFormat("0"))

		// Iterate over rows and set cell values
		for (def rowNum = 17; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);

			// Write Firm value into Column Index 14
			Cell firmCell = row.createCell(14);
			firmCell.setCellValue(Double.parseDouble(testData.getValue('Firm', rowNum - 16)));
			firmCell.setCellStyle(numberStyle);
			KeywordUtil.logInfo('Writing: ' + Double.parseDouble(testData.getValue('Firm', rowNum - 16)).toString() + ' in Place Order Form');

			// Write Forecast N+1 value into Column Index 20
			Cell forecastn1Cell = row.createCell(20);
			forecastn1Cell.setCellValue(Double.parseDouble(testData.getValue('Forecast_N+1', rowNum - 16)));
			forecastn1Cell.setCellStyle(numberStyle);
			KeywordUtil.logInfo('Writing: ' + Double.parseDouble(testData.getValue('Forecast_N+1', rowNum - 16)).toString() + ' in Place Order Form');

			// Write Inbound Qty value into Column Index 24
			Cell inboundQty = row.createCell(24);
			inboundQty.setCellValue(Double.parseDouble(testData.getValue('Inbound_Date1_Qty', rowNum - 16)));
			inboundQty.setCellStyle(numberStyle);
			KeywordUtil.logInfo('Writing: ' + Double.parseDouble(testData.getValue('Inbound_Date1_Qty', rowNum - 16)).toString() + ' in Place Order Form');
		}

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

		KeywordUtil.logInfo('Finish Writing Place Order Form Excel: ' + path)
	}

	@Keyword
	public void writeIntoExcel(String path, String name , int rowNum, int colNum) throws IOException{
		FileInputStream fis = new FileInputStream("C:\\Users\\azwan\\Desktop\\toyotsudata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row row = sheet.getRow(rowNum);
		Cell cell = row.createCell(colNum);
		cell.setCellValue(name);
		FileOutputStream fos = new FileOutputStream("C:\\Users\\azwan\\Desktop\\toyotsudata.xlsx");
		workbook.write(fos);
		fos.close();
	}

	@Keyword(keywordObject='Excel Actions')
	def writeIntoExcelOrderChangeRegular(TestData testdata, String path, String custorder_no) {

		// Open the workbook and sheet
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(custorder_no);

		// Unprotect the sheet if it's protected with a password
		sheet.protectSheet(null)

		// Create an evaluator to recalculate formulas in the workbook
		def evaluator = workbook.getCreationHelper().createFormulaEvaluator()

		// Create number format style
		DataFormat dataFormat = workbook.createDataFormat()
		CellStyle numberStyle = workbook.createCellStyle()
		numberStyle.setDataFormat(dataFormat.getFormat("0"))

		// Iterate over rows and set cell values
		for (def rowNum = 17; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);

			// Write New Firm value into Column Index 14
			def newFirmValue = testdata.getValue('NewFirm', rowNum - 16)

			if(!newFirmValue.isEmpty()) {
				Cell firmCell = row.createCell(15);
				firmCell.setCellValue(Double.parseDouble(newFirmValue));
				firmCell.setCellStyle(numberStyle);
				KeywordUtil.logInfo('Writing: ' + Double.parseDouble(newFirmValue).toString() + ' in New Firm');
			}

			// Write New Inbound Qty 1 value into Column Index 24
			def newInboundQtyValue = testdata.getValue('InboundNewDate_Qty1', rowNum - 16)

			if(!newInboundQtyValue.isEmpty()) {
				Cell inboundQty = row.createCell(24);
				inboundQty.setCellValue(Double.parseDouble(newInboundQtyValue));
				inboundQty.setCellStyle(numberStyle);
				KeywordUtil.logInfo('Writing: ' + Double.parseDouble(newInboundQtyValue).toString() + ' in Inbound Date 1 Qty');
			}

			// Write New Inbound Qty 2 value into Column Index 25
			def newInboundQty2Value = testdata.getValue('InboundNewDate_Qty2', rowNum - 16)

			if(!newInboundQty2Value.isEmpty()) {
				Cell inboundQty2 = row.createCell(25);
				inboundQty2.setCellValue(Double.parseDouble(newInboundQty2Value));
				inboundQty2.setCellStyle(numberStyle);
				KeywordUtil.logInfo('Writing: ' + Double.parseDouble(newInboundQty2Value).toString() + ' in Inbound Date 2 Qty');
			}
		}

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

		KeywordUtil.logInfo('Finish Writing Change Order Form Excel: ' + path)
	}

	@Keyword(keywordObject='Excel Actions')
	def writeIntoExcelOrderChangeRegularInboundDate(TestData testdata, String path, String custorder_no) {
		// Open the workbook and sheet
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(custorder_no);

		// Create an evaluator to recalculate formulas in the workbook
		def evaluator = workbook.getCreationHelper().createFormulaEvaluator()

		// Get the row for Inbound Date 1
		Row row = sheet.getRow(16);

		for(def rowNum=1; rowNum<=testdata.getRowNumbers(); rowNum++) {
			def inboundNewDate = testdata.getValue('InboundNewDate1', rowNum)
			Cell inboundNewDate_Cell = row.createCell(rowNum+23)
			inboundNewDate_Cell.setCellValue(inboundNewDate);
			KeywordUtil.logInfo('Writing: ' + inboundNewDate + ' in Inbound Date 1');

			def inboundNewDate2 = testdata.getValue('InboundNewDate2', rowNum)
			Cell inboundNewDate2_Cell = row.createCell(rowNum+24)
			inboundNewDate2_Cell.setCellValue(inboundNewDate2);
			KeywordUtil.logInfo('Writing: ' + inboundNewDate2 + ' in Inbound Date 2');
		}

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

		KeywordUtil.logInfo('Write Finished at Change Order file: ' + path);
	}


	// NON-KEYWORDS LINE SEPARATORS


	/**
	 * Writes the inbound date into an Excel file for place order regular.
	 *
	 * @param path        The file path of the Excel file.
	 * @param contract_no The name of the sheet within the Excel file.
	 */
	def writeIntoExcelPlaceOrderRegularInboundDate(String path, String contract_no) {
		// Open the workbook and sheet
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(contract_no);

		// Create an evaluator to recalculate formulas in the workbook
		def evaluator = workbook.getCreationHelper().createFormulaEvaluator()

		// Find test data
		def testData = findTestData('Data Files/Scenario 13/Autogen')

		// Get the value of Inbound Date 1 from the test data
		def inboundDate1 = testData.getValue('Inbound_Date1', 1)

		// Get the row for Inbound Date 1
		Row row = sheet.getRow(16);

		// Write Inbound Date 1 value into Column Index 24
		Cell inboudDate1_Cell = row.createCell(24);
		inboudDate1_Cell.setCellValue(inboundDate1);

		KeywordUtil.logInfo('Writing: ' + inboundDate1 + ' in Place Order');

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
	}

	//SUPPLIER CHANGE
	@Keyword(keywordObject='Excel Actions')
	def writeSupplierChangeSC12(TestData testdata, String path) {


		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);

		//To unprotect sheet
		sheet.protectSheet("");


		println(sheet.getLastRowNum().toString())


		def evaluator = workbook.getCreationHelper().createFormulaEvaluator()


		// Get the variable names from the test data object
		List<String> variableNames = testdata.columnNames


		int numberrowtd = testdata.getRowNumbers() - 2 + 15
		println("numberrowtd: "+numberrowtd)



		for (def rowNum = 15; rowNum <= numberrowtd; rowNum++) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
			}

			for (def varIndex = 0; varIndex < variableNames.size(); varIndex++) {
				def variableName = variableNames[varIndex]
				def variableValue = testdata.getValue(variableName, rowNum - 13)


				if (!variableValue.isEmpty()) {


					def columnIndex = testdata.getValue(variableNames[varIndex], 1).toInteger()


					Cell variableCell = row.createCell(columnIndex);
					if (variableCell == null) {
						variableCell = row.createCell(columnIndex);
					}

					variableCell.setCellValue(variableValue);
					KeywordUtil.logInfo('Writing: ' + variableValue + ' in ' + variableName + ' of Download Outbound Form');
					println("CURRENT ROW IS: "+rowNum)

					evaluator.evaluateAll()

				}
			}
		}


		evaluator.evaluateAll()


		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();



		KeywordUtil.logInfo('Finish Writing Supplier Change Form Excel: ' + path)
	}


}