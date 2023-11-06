import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.DataFormat
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.io.FileInputStream

public class ExcelActions extends DateConversion {

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

		// Define a regular expression to match Chinese characters
		def chinesePattern = /\p{IsHan}/

		if (name =~ chinesePattern) {

			def convertedName=super.convertChineseToEnglishDate(name)
			cell.setCellValue(convertedName);
		}
		else {
			cell.setCellValue(name);}

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

		for(def rowNum=1; rowNum<=testdata.getRowNumbers(); rowNum++) {

			def inboundNewDate = testdata.getValue('InboundNewDate1', rowNum)
			def inboundNewDate2 = testdata.getValue('InboundNewDate2', rowNum)

			// Define a regular expression to match Chinese characters
			def chinesePattern = /\p{IsHan}/

			if (inboundNewDate =~ chinesePattern || inboundNewDate2 =~ chinesePattern) {

				def conInboundDate1=super.convertChineseToEnglishDate(inboundNewDate)
				def conInboundDate2=super.convertChineseToEnglishDate(inboundNewDate2)


				// Get the row for Inbound Date 1
				Row row = sheet.getRow(16);

				Cell inboundNewDate_Cell = row.createCell(rowNum+23)
				inboundNewDate_Cell.setCellValue(conInboundDate1);
				KeywordUtil.logInfo('Writing: ' + conInboundDate1 + ' in Inbound Date 1');


				Cell inboundNewDate2_Cell = row.createCell(rowNum+24)
				inboundNewDate2_Cell.setCellValue(conInboundDate2);
				KeywordUtil.logInfo('Writing: ' + conInboundDate2 + ' in Inbound Date 2');}

			else {


				// Get the row for Inbound Date 1
				Row row = sheet.getRow(16);

				Cell inboundNewDate_Cell = row.createCell(rowNum+23)
				inboundNewDate_Cell.setCellValue(inboundNewDate);
				KeywordUtil.logInfo('Writing: ' + inboundNewDate + ' in Inbound Date 1');

				Cell inboundNewDate2_Cell = row.createCell(rowNum+24)
				inboundNewDate2_Cell.setCellValue(inboundNewDate2);
				KeywordUtil.logInfo('Writing: ' + inboundNewDate2 + ' in Inbound Date 2');}
		}

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

		KeywordUtil.logInfo('Write Finished at Change Order file: ' + path);
	}

	@Keyword(keywordObject='Excel Actions')
	def writeOutboundDataIntoDownloadOutboundForm(TestData testdata, String path) {

		// Open the workbook and sheet
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);

		println(sheet.getLastRowNum().toString())

		// Create an evaluator to recalculate formulas in the workbook
		def evaluator = workbook.getCreationHelper().createFormulaEvaluator()

		// Iterate over rows and set cell values
		for (def rowNum = 8; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);

			// Write Outbound No value into Column Index 3
			def outboundNo = testdata.getValue('OutboundNo', rowNum - 7)

			if(!outboundNo.isEmpty()) {
				Cell outboundNoCell = row.createCell(3);
				outboundNoCell.setCellValue(outboundNo);
				KeywordUtil.logInfo('Writing: ' + outboundNo + ' in outbound No of Download Outbound Form');
			}

			// Write New Booking No value into Column Index 6
			def bookingNo = testdata.getValue('BookingNo', rowNum - 7)

			if(!bookingNo.isEmpty()) {
				Cell bookingNoCell = row.createCell(6);
				bookingNoCell.setCellValue(bookingNo);
				KeywordUtil.logInfo('Writing: ' + bookingNo + ' in Booking No of Download Outbound Form');
			}

			// Write Container No value into Column Index 16
			def containerNo = testdata.getValue('ContainerNo', rowNum - 7)

			if(!containerNo.isEmpty()) {
				Cell containerNoCell = row.createCell(16);
				containerNoCell.setCellValue(containerNo);
				KeywordUtil.logInfo('Writing: ' + containerNo + ' in Container No. of Download Outbound Form');
			}


			// Write Outer Package No value into Column Index 23
			def outerPackageNo = testdata.getValue('OuterPackageNo', rowNum - 7)

			if(!outerPackageNo.isEmpty()) {
				Cell outerPackageNoCell = row.createCell(23);
				outerPackageNoCell.setCellValue(outerPackageNo);
				KeywordUtil.logInfo('Writing: ' + outerPackageNo + ' in Outer Package No. of Download Outbound Form');
			}

			// Write Outer Package Type value into Column Index 24
			def outerPackageType = testdata.getValue('OuterPackageType', rowNum - 7)

			if(!outerPackageType.isEmpty()) {
				Cell outerPackageTypeCell = row.createCell(24);
				outerPackageTypeCell.setCellValue(outerPackageType);
				KeywordUtil.logInfo('Writing: ' + outerPackageType + ' in Outer Package Type of Download Outbound Form');
			}
		}

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

		KeywordUtil.logInfo('Finish Writing Download Outbound Form Excel: ' + path)
	}

	@Keyword(keywordObject='Excel Actions')
	def writeOutboundQtyIntoDownloadOutboundForm(TestData testdata, String path) {

		// Open the workbook and sheet
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);

		// Create an evaluator to recalculate formulas in the workbook
		def evaluator = workbook.getCreationHelper().createFormulaEvaluator()

		// Create number format style
		DataFormat dataFormat = workbook.createDataFormat()
		CellStyle numberStyle = workbook.createCellStyle()
		numberStyle.setDataFormat(dataFormat.getFormat("0"))

		// Iterate over rows and set cell values
		for (def rowNum = 8; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);

			// Write New Firm value into Column Index 14
			def newFirmValue = testdata.getValue('NewFirm', rowNum - 7)

			if(!newFirmValue.isEmpty()) {
				Cell firmCell = row.createCell(9);
				firmCell.setCellValue(Double.parseDouble(newFirmValue));
				firmCell.setCellStyle(numberStyle);
				KeywordUtil.logInfo('Writing: ' + Double.parseDouble(newFirmValue).toString() + ' in New Firm');
			}

		}

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

		KeywordUtil.logInfo('Finish Writing Download Outbound Form Excel: ' + path)
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
		def testData = findTestData('Data Files/Scenario 13/S13_TC041-Inbound Dates')

		// Get the value of Inbound Date 1 from the test data
		def inboundDate1 = testData.getValue('Inbound_Date1', 1)


		// Define a regular expression to match Chinese characters
		def chinesePattern = /\p{IsHan}/

		if (inboundDate1  =~ chinesePattern) {

			def convertedDate=super.convertChineseToEnglishDate(inboundDate1)

			// Get the row for Inbound Date 1
			Row row = sheet.getRow(16);

			// Write Inbound Date 1 value into Column Index 24
			Cell inboudDate1_Cell = row.createCell(24);
			inboudDate1_Cell.setCellValue(convertedDate);

			KeywordUtil.logInfo('Writing: ' + convertedDate + ' in Place Order'); }

		else {

			// Get the row for Inbound Date 1
			Row row = sheet.getRow(16);

			// Write Inbound Date 1 value into Column Index 24
			Cell inboudDate1_Cell = row.createCell(24);
			inboudDate1_Cell.setCellValue(inboundDate1);

			KeywordUtil.logInfo('Writing: ' + inboundDate1 + ' in Place Order'); }

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
	}

	//SCENARIO 12 - STARTS FROM HERE

	// PLACE REGULAR ORDER
	@Keyword(keywordObject='Excel Actions')
	def writeIntoExcelPlaceOrderRegularSC12(String path, String contract_no) {
		// Find test data
		def testData = findTestData('Data Files/Scenario 12/SC12_TC011-Place Regular Order')

		// Write inbound date
		writeIntoExcelPlaceOrderRegularInboundDateSC12(path, contract_no)

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
			Cell inboundQty1 = row.createCell(24);
			inboundQty1.setCellValue(Double.parseDouble(testData.getValue('Inbound_Date1_Qty', rowNum - 16)));
			inboundQty1.setCellStyle(numberStyle);
			KeywordUtil.logInfo('Writing: ' + Double.parseDouble(testData.getValue('Inbound_Date1_Qty', rowNum - 16)).toString() + ' in Place Order Form');

			// Write Inbound Qty value into Column Index 25
			Cell inboundQty2 = row.createCell(25);
			inboundQty2.setCellValue(Double.parseDouble(testData.getValue('Inbound_Date2_Qty', rowNum - 16)));
			inboundQty2.setCellStyle(numberStyle);
			KeywordUtil.logInfo('Writing: ' + Double.parseDouble(testData.getValue('Inbound_Date2_Qty', rowNum - 16)).toString() + ' in Place Order Form');
		}

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

		KeywordUtil.logInfo('Finish Writing Place Order Form Excel: ' + path)
	}


	@Keyword(keywordObject='Excel Actions 2')
	def writeIntoExcelPlaceOrderRegularSC122(String path, String contract_no, def testData) {
		// Find test data
		//def testData = findTestData('Data Files/Scenario 12/SC12_TC011-Place Regular Order')
		// Write inbound date
		writeIntoExcelPlaceOrderRegularInboundDateSC12(path, contract_no)

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
			Cell inboundQty1 = row.createCell(24);
			inboundQty1.setCellValue(Double.parseDouble(testData.getValue('Inbound_Date1_Qty', rowNum - 16)));
			inboundQty1.setCellStyle(numberStyle);
			KeywordUtil.logInfo('Writing: ' + Double.parseDouble(testData.getValue('Inbound_Date1_Qty', rowNum - 16)).toString() + ' in Place Order Form');

			// Write Inbound Qty value into Column Index 25
			Cell inboundQty2 = row.createCell(25);
			inboundQty2.setCellValue(Double.parseDouble(testData.getValue('Inbound_Date2_Qty', rowNum - 16)));
			inboundQty2.setCellStyle(numberStyle);
			KeywordUtil.logInfo('Writing: ' + Double.parseDouble(testData.getValue('Inbound_Date2_Qty', rowNum - 16)).toString() + ' in Place Order Form');
		}

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

		KeywordUtil.logInfo('Finish Writing Place Order Form Excel: ' + path)
	}

	// PLACE SPOT ORDER
	@Keyword(keywordObject='Excel Actions')
	def writeIntoExcelPlaceOrderSpotSC12(String path, String contract_no) {
		// Find test data
		def testData = findTestData('Data Files/Scenario 12/SC12_TC011-Place Spot Order')
		// Write inbound date
		writeIntoExcelPlaceOrderSpotInboundDateSC12(path, contract_no)

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

		// Write Spot Reason
		Row row0 = sheet.getRow(11);
		Cell spotReason_Cell = row0.createCell(3);
		spotReason_Cell.setCellValue('Spot order for contract: ' + contract_no);

		// Iterate over rows and set cell values
		for (def rowNum = 17; rowNum <= sheet.getLastRowNum(); rowNum++) {

			Row row = sheet.getRow(rowNum);

			// Write Firm value into Column Index 14
			Cell firmCell = row.createCell(14);
			firmCell.setCellValue(Double.parseDouble(testData.getValue('Firm', rowNum - 16)));
			firmCell.setCellStyle(numberStyle);
			KeywordUtil.logInfo('Writing: ' + Double.parseDouble(testData.getValue('Firm', rowNum - 16)).toString() + ' in Place Order Form');

			// Write Inbound Qty value into Column Index 19
			Cell inboundQty = row.createCell(19);
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

	// PLACE FORECAST CHANGE
	@Keyword(keywordObject='Excel Actions')
	def writeIntoExcelForecastChange(TestData testdata, String path, String custorder_no) {

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
		for (def rowNum = 15; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);

			// Write New Firm value into Column Index 17
			def newForecastValue = testdata.getValue('New Forecast N+1', rowNum - 14)

			if(!newForecastValue.isEmpty()) {
				Cell firmCell = row.createCell(17);
				firmCell.setCellValue(Double.parseDouble(newForecastValue));
				firmCell.setCellStyle(numberStyle);
				KeywordUtil.logInfo('Writing: ' + Double.parseDouble(newForecastValue).toString() + ' in New Forecast');
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

	// PLACE REGULAR ORDER CHANGE
	@Keyword(keywordObject='Excel Actions')
	def writeIntoExcelOrderChangeRegularSC12(TestData testdata, String path, String custorder_no) {

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

			// Write New Inbound Qty 1 value into Column Index 25
			def newInboundQty1Value = testdata.getValue('InboundNewDate_Qty1', rowNum - 16)

			if(!newInboundQty1Value.isEmpty()) {
				Cell inboundQty1 = row.createCell(25);
				inboundQty1.setCellValue(Double.parseDouble(newInboundQty1Value));
				inboundQty1.setCellStyle(numberStyle);
				KeywordUtil.logInfo('Writing: ' + Double.parseDouble(newInboundQty1Value).toString() + ' in Inbound Date 1 Qty');
			}

			// Write New Inbound Qty 2 value into Column Index 26
			def newInboundQty2Value = testdata.getValue('InboundNewDate_Qty2', rowNum - 16)

			if(!newInboundQty2Value.isEmpty()) {
				Cell inboundQty2 = row.createCell(26);
				inboundQty2.setCellValue(Double.parseDouble(newInboundQty2Value));
				inboundQty2.setCellStyle(numberStyle);
				KeywordUtil.logInfo('Writing: ' + Double.parseDouble(newInboundQty2Value).toString() + ' in Inbound Date 2 Qty');
			}
			// Write New Inbound Qty 3 value into Column Index 27
			def newInboundQty3Value = testdata.getValue('InboundNewDate_Qty3', rowNum - 16)

			if(!newInboundQty3Value.isEmpty()) {
				Cell inboundQty3 = row.createCell(27);
				inboundQty3.setCellValue(Double.parseDouble(newInboundQty3Value));
				inboundQty3.setCellStyle(numberStyle);
				KeywordUtil.logInfo('Writing: ' + Double.parseDouble(newInboundQty3Value).toString() + ' in Inbound Date 3 Qty');
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
	def writeIntoExcelOrderChangeRegularInboundDateSC12(TestData testdata, String path, String custorder_no) {
		// Open the workbook and sheet
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(custorder_no);

		// Create an evaluator to recalculate formulas in the workbook
		def evaluator = workbook.getCreationHelper().createFormulaEvaluator()


		for(def rowNum=1; rowNum<=testdata.getRowNumbers(); rowNum++) {

			def inboundNewDate1 = testdata.getValue('InboundNewDate1', rowNum)
			def inboundNewDate2 = testdata.getValue('InboundNewDate2', rowNum)
			def inboundNewDate3 = testdata.getValue('InboundNewDate3', rowNum)

			// Define a regular expression to match Chinese characters
			def chinesePattern = /\p{IsHan}/

			if (inboundNewDate1 =~ chinesePattern || inboundNewDate2 =~ chinesePattern || inboundNewDate3 =~ chinesePattern) {

				def conInboundDate1=super.convertChineseToEnglishDate(inboundNewDate1)
				def conInboundDate2=super.convertChineseToEnglishDate(inboundNewDate2)
				def conInboundDate3=super.convertChineseToEnglishDate(inboundNewDate3)

				// Get the row for Inbound Date 1
				Row row = sheet.getRow(16);


				//def inboundNewDate1 = testdata.getValue('InboundNewDate1', rowNum)
				Cell inboundNewDate1_Cell = row.createCell(rowNum+24)
				inboundNewDate1_Cell.setCellValue(conInboundDate1);
				KeywordUtil.logInfo('Writing: ' + conInboundDate1 + ' in Inbound Date 1');

				//def inboundNewDate2 = testdata.getValue('InboundNewDate2', rowNum)
				Cell inboundNewDate2_Cell = row.createCell(rowNum+25)
				inboundNewDate2_Cell.setCellValue(conInboundDate2);
				KeywordUtil.logInfo('Writing: ' + conInboundDate2 + ' in Inbound Date 2');

				//def inboundNewDate3 = testdata.getValue('InboundNewDate3', rowNum)
				Cell inboundNewDate3_Cell = row.createCell(rowNum+26)
				inboundNewDate3_Cell.setCellValue(conInboundDate3);
				KeywordUtil.logInfo('Writing: ' + conInboundDate3 + ' in Inbound Date 3'); }

			else {

				// Get the row for Inbound Date 1
				Row row = sheet.getRow(16);


				Cell inboundNewDate1_Cell = row.createCell(rowNum+24)
				inboundNewDate1_Cell.setCellValue(inboundNewDate1);
				KeywordUtil.logInfo('Writing: ' + inboundNewDate1 + ' in Inbound Date 1');

				Cell inboundNewDate2_Cell = row.createCell(rowNum+25)
				inboundNewDate2_Cell.setCellValue(inboundNewDate2);
				KeywordUtil.logInfo('Writing: ' + inboundNewDate2 + ' in Inbound Date 2');

				Cell inboundNewDate3_Cell = row.createCell(rowNum+26)
				inboundNewDate3_Cell.setCellValue(inboundNewDate3);
				KeywordUtil.logInfo('Writing: ' + inboundNewDate3 + ' in Inbound Date 3');}

		}

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

		KeywordUtil.logInfo('Write Finished at Change Order file: ' + path);
	}

	//SUPPLIER OUTBOUND
	@Keyword(keywordObject='Excel Actions')
	def writeOutboundDataIntoDownloadOutboundFormSC12(TestData testdata, String path) {

		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);

		println(sheet.getLastRowNum().toString())

		def evaluator = workbook.getCreationHelper().createFormulaEvaluator()

		// Get the variable names from the test data object
		List<String> variableNames = testdata.columnNames

		int numberrowtd = testdata.getRowNumbers() - 2 + 8
		println("numberrowtd: "+numberrowtd)

		for (def rowNum = 8; rowNum <= numberrowtd; rowNum++) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
			}

			for (def varIndex = 0; varIndex < variableNames.size(); varIndex++) {
				def variableName = variableNames[varIndex]
				def variableValue = testdata.getValue(variableName, rowNum - 6)

				if (!variableValue.isEmpty()) {

					def columnIndex = testdata.getValue(variableNames[varIndex], 1).toInteger()

					Cell variableCell = row.createCell(columnIndex);
					if (variableCell == null) {
						variableCell = row.createCell(columnIndex);
					}
					// Define a regular expression to match Chinese characters
					def chinesePattern = /\p{IsHan}/

					if(variableValue =~ chinesePattern) {
						def conVariableValue=super.convertChineseToEnglishDate(variableValue)
						variableCell.setCellValue(conVariableValue);

						KeywordUtil.logInfo('Writing: ' + conVariableValue + ' in ' + variableName + ' of Download Outbound Form');
						println("CURRENT ROW IS: "+rowNum)
					}
					else {
						variableCell.setCellValue(variableValue);

						KeywordUtil.logInfo('Writing: ' + variableValue + ' in ' + variableName + ' of Download Outbound Form');
						println("CURRENT ROW IS: "+rowNum)
					}

				}
			}
		}

		evaluator.evaluateAll()

		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

		KeywordUtil.logInfo('Finish Writing Download Outbound Form Excel: ' + path)
	}

	// NON-KEYWORDS LINE SEPARATORS
	/**
	 * Writes the inbound date into an Excel file for place order regular.
	 *
	 * @param path        The file path of the Excel file.
	 * @param contract_no The name of the sheet within the Excel file.
	 */
	def writeIntoExcelPlaceOrderRegularInboundDateSC12(String path, String contract_no) {
		// Open the workbook and sheet
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(contract_no);

		// Create an evaluator to recalculate formulas in the workbook
		def evaluator = workbook.getCreationHelper().createFormulaEvaluator()

		// Find test data
		def testData = findTestData('Data Files/Scenario 12/SC12_TC011-Inbound Dates Regular')

		// Get the value of Inbound Date 1 from the test data
		def inboundDate1 = testData.getValue('Inbound_Date1', 1)
		def inboundDate2 = testData.getValue('Inbound_Date2', 1)


		// Define a regular expression to match Chinese characters
		def chinesePattern = /\p{IsHan}/

		if (inboundDate1 =~ chinesePattern || inboundDate2 =~ chinesePattern) {
			// Enter this block if either inboundDate1 or inboundDate2 contains Chinese characters
			// Add your code here to handle the case when Chinese characters are found
			println("Chinese characters found in inboundDate1 or inboundDate2")


			def conInboundDate1=super.convertChineseToEnglishDate(inboundDate1)

			def conInboundDate2=super.convertChineseToEnglishDate(inboundDate2)


			// Get the row for Inbound Date 1
			Row row = sheet.getRow(16);

			// Write Inbound Date 1 value into Column Index 24
			Cell inboudDate1_Cell = row.createCell(24);
			inboudDate1_Cell.setCellValue(conInboundDate1);

			// Write Inbound Date 1 value into Column Index 25
			Cell inboudDate2_Cell = row.createCell(25);
			inboudDate2_Cell.setCellValue(conInboundDate2);


			KeywordUtil.logInfo('Writing: ' + conInboundDate1 + ' in Place Order');
			KeywordUtil.logInfo('Writing: ' + conInboundDate2 + ' in Place Order');}

		else{

			// Get the row for Inbound Date 1
			Row row = sheet.getRow(16);

			// Write Inbound Date 1 value into Column Index 24
			Cell inboudDate1_Cell = row.createCell(24);
			inboudDate1_Cell.setCellValue(inboundDate1);

			// Write Inbound Date 1 value into Column Index 25
			Cell inboudDate2_Cell = row.createCell(25);
			inboudDate2_Cell.setCellValue(inboundDate2);

			KeywordUtil.logInfo('Writing: ' + inboundDate1 + ' in Place Order');
			KeywordUtil.logInfo('Writing: ' + inboundDate2 + ' in Place Order');

		}

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close(); }


	def writeIntoExcelPlaceOrderSpotInboundDateSC12(String path, String contract_no) {
		// Open the workbook and sheet
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(contract_no);

		// Create an evaluator to recalculate formulas in the workbook
		def evaluator = workbook.getCreationHelper().createFormulaEvaluator()

		// Find test data
		def testData = findTestData('Data Files/Scenario 12/SC12_TC011-Inbound Dates Spot')

		// Get the value of Inbound Date 1 from the test data
		def inboundDate1 = testData.getValue('Inbound_Date1', 1)

		// Define a regular expression to match Chinese characters
		def chinesePattern = /\p{IsHan}/

		if (inboundDate1 =~ chinesePattern) {
			// Enter this block if either inboundDate1 or inboundDate2 contains Chinese characters
			// Add your code here to handle the case when Chinese characters are found
			println("Chinese characters found in inboundDate1")

			def conInboundDate1=super.convertChineseToEnglishDate(inboundDate1)


			// Get the row for Inbound Date 1
			Row row = sheet.getRow(16);

			// Write Inbound Date 1 value into Column Index 19
			Cell inboudDate1_Cell = row.createCell(19);
			inboudDate1_Cell.setCellValue(conInboundDate1);


			KeywordUtil.logInfo('Writing: ' + conInboundDate1 + ' in Place Order');}
		else {

			// Get the row for Inbound Date 1
			Row row = sheet.getRow(16);

			// Write Inbound Date 1 value into Column Index 19
			Cell inboudDate1_Cell = row.createCell(19);
			inboudDate1_Cell.setCellValue(inboundDate1);


			KeywordUtil.logInfo('Writing: ' + inboundDate1 + ' in Place Order');}


		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
	}

	//SCENARIO 17 - STARTS FROM HERE

	@Keyword
	(keywordObject='Excel Actions')
	def writeIntoExcelPlaceOrderRegularInboundDateS17(String path, String contract_no) {
		// Open the workbook and sheet
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(contract_no);

		// Create an evaluator to recalculate formulas in the workbook
		def evaluator = workbook.getCreationHelper().createFormulaEvaluator()

		// Find test data
		def testData = findTestData('Data Files/Scenario 17/S17_TC016.1- Inbound Date')

		// Get the value of Inbound Date 1 from the test data
		def inboundDate1 = testData.getValue('InboundDate1', 1)

		// Get the value of Inbound Date 2 from the test data
		def inboundDate2 = testData.getValue('InboundDate2', 1)


		// Define a regular expression to match Chinese characters
		def chinesePattern = /\p{IsHan}/

		if (inboundDate1 =~ chinesePattern || inboundDate2 =~ chinesePattern) {

			def conInboundDate1=super.convertChineseToEnglishDate(inboundDate1)
			def conInboundDate2=super.convertChineseToEnglishDate(inboundDate2)

			// Get the row for Inbound Date 1
			Row row = sheet.getRow(16);

			// Write Inbound Date 1 value into Column Index 24
			Cell inboudDate1_Cell = row.createCell(24);
			inboudDate1_Cell.setCellValue(conInboundDate1);

			KeywordUtil.logInfo('Writing: ' + conInboundDate1 + ' in Place Order');

			// Write Inbound Date 1 value into Column Index 24
			Cell inboudDate2Cell = row.createCell(25);
			inboudDate2Cell.setCellValue(conInboundDate2);

			KeywordUtil.logInfo('Writing: ' + conInboundDate2 + ' in Place Order');}

		else {

			// Get the row for Inbound Date 1
			Row row = sheet.getRow(16);

			// Write Inbound Date 1 value into Column Index 24
			Cell inboudDate1_Cell = row.createCell(24);
			inboudDate1_Cell.setCellValue(inboundDate1);

			KeywordUtil.logInfo('Writing: ' + inboundDate1 + ' in Place Order');

			// Write Inbound Date 1 value into Column Index 25
			Cell inboudDate2_Cell = row.createCell(25);
			inboudDate2_Cell.setCellValue(inboundDate2);

			KeywordUtil.logInfo('Writing: ' + inboundDate2 + ' in Place Order');}

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
	}

	//SCENARIO 9 - STARTS FROM HERE

	/**
	 * Writes data into an Excel file for regular place order.
	 *
	 * @param path        The file path of the Excel file.
	 * @param contract_no The name of the sheet within the Excel file.
	 */
	@Keyword(keywordObject='Excel Actions')
	def writeIntoExcelPlaceOrderRegularS9_TC41(String path, String contract_no) {
		// Find test data
		def testData = findTestData('Data Files/Scenario 9/S9_TC041-CUS Reg Order (Firm)')

		// Write inbound date
		writeIntoExcelPlaceOrderRegularInboundDateS9_TC41(path, contract_no)

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
			forecastn1Cell.setCellValue(Double.parseDouble(testData.getValue('ForecastN1', rowNum - 16)));
			forecastn1Cell.setCellStyle(numberStyle);
			KeywordUtil.logInfo('Writing: ' + Double.parseDouble(testData.getValue('ForecastN1', rowNum - 16)).toString() + ' in Place Order Form');

			// Write Inbound Qty value into Column Index 24
			Cell inboundQty1 = row.createCell(24);
			inboundQty1.setCellValue(Double.parseDouble(testData.getValue('InboundDate1Qty', rowNum - 16)));
			inboundQty1.setCellStyle(numberStyle);
			KeywordUtil.logInfo('Writing: ' + Double.parseDouble(testData.getValue('InboundDate1Qty', rowNum - 16)).toString() + ' in Place Order Form');

			// Write Inbound Qty value into Column Index 25
			Cell inboundQty2 = row.createCell(25);
			inboundQty2.setCellValue(Double.parseDouble(testData.getValue('InboundDate2Qty', rowNum - 16)));
			inboundQty2.setCellStyle(numberStyle);
			KeywordUtil.logInfo('Writing: ' + Double.parseDouble(testData.getValue('InboundDate2Qty', rowNum - 16)).toString() + ' in Place Order Form');
		}

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

		KeywordUtil.logInfo('Finish Writing Place Order Form Excel: ' + path)
	}

	def writeIntoExcelPlaceOrderRegularInboundDateS9_TC41(String path, String contract_no) {
		// Open the workbook and sheet
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(contract_no);

		// Create an evaluator to recalculate formulas in the workbook
		def evaluator = workbook.getCreationHelper().createFormulaEvaluator()

		// Find test data
		def testData = findTestData('Data Files/Scenario 9/S9_TC041-CUS Reg Order (Date)')

		// Get the value of Inbound Date 1 from the test data
		def inboundDate1 = testData.getValue('InboundDate1', 1)

		// Get the value of Inbound Date 2 from the test data
		def inboundDate2 = testData.getValue('InboundDate2', 1)


		// Define a regular expression to match Chinese characters
		def chinesePattern = /\p{IsHan}/

		if (inboundDate1 =~ chinesePattern || inboundDate2 =~ chinesePattern) {


			def conInboundDate1=super.convertChineseToEnglishDate(inboundDate1)
			def conInboundDate2=super.convertChineseToEnglishDate(inboundDate2)

			// Get the row for Inbound Date 1
			Row row = sheet.getRow(16);

			// Write Inbound Date 1 value into Column Index 24
			Cell inboudDate1_Cell = row.createCell(24);
			inboudDate1_Cell.setCellValue(conInboundDate1);

			KeywordUtil.logInfo('Writing: ' + conInboundDate1 + ' in Place Order');

			// Write Inbound Date 1 value into Column Index 25
			Cell inboudDate2Cell = row.createCell(25);
			inboudDate2Cell.setCellValue(conInboundDate2);

			KeywordUtil.logInfo('Writing: ' + conInboundDate2 + ' in Place Order');}

		else {

			// Get the row for Inbound Date 1
			Row row = sheet.getRow(16);

			// Write Inbound Date 1 value into Column Index 24
			Cell inboudDate1_Cell = row.createCell(24);
			inboudDate1_Cell.setCellValue(inboundDate1);

			KeywordUtil.logInfo('Writing: ' + inboundDate1 + ' in Place Order');

			// Write Inbound Date 1 value into Column Index 25
			Cell inboudDate2_Cell = row.createCell(25);
			inboudDate2_Cell.setCellValue(inboundDate2);

			KeywordUtil.logInfo('Writing: ' + inboundDate2 + ' in Place Order'); }

		// Evaluate all formulas in the workbook
		evaluator.evaluateAll()

		// Save changes to the Excel file
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();
	}


}