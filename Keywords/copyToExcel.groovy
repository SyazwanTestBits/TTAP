import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellReference
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import com.kms.katalon.core.annotation.Keyword


public class copyToExcel extends DateConversion {

	@Keyword
	public void exel(String name, int rowNum, int colNum, String filePath, String fileName, String sheetName) throws IOException {
		String fullFilePath = filePath + File.separator + fileName; // Full file path

		FileInputStream fis = new FileInputStream(fullFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet(sheetName);

		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum); // Create a new row if it doesn't exist
		}

		Cell cell = row.createCell(colNum);

		// Define a regular expression to match Chinese characters
		def chinesePattern = /\p{IsHan}/

		if (name =~ chinesePattern) {

			def convertedName=super.convertChineseToEnglishDate(name)
			cell.setCellValue(convertedName);
		}
		else {

			cell.setCellValue(name);}


		FileOutputStream fos = new FileOutputStream(fullFilePath);
		workbook.write(fos);


		// Refresh the specified cell
		CellReference cellRef = new CellReference(rowNum, colNum);
		Row sheetRow = sheet.getRow(cellRef.getRow());
		Cell sheetCell = sheetRow.getCell(cellRef.getCol());
		sheetCell.setCellValue(sheetCell.getStringCellValue());
		//


		fos.close();
		fis.close();

		recalculateExcelFormulas(fullFilePath)
	}

	@Keyword
	public void exel2(String name, int rowNum, int colNum, String file, String sheetNumber) throws IOException {

		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet(sheetNumber);

		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum); // Create a new row if it doesn't exist
		}

		Cell cell = row.createCell(colNum);

		// Define a regular expression to match Chinese characters
		def chinesePattern = /\p{IsHan}/

		if (name =~ chinesePattern) {

			def convertedName=super.convertChineseToEnglishDate(name)
			cell.setCellValue(convertedName);
		}
		else {

			cell.setCellValue(name);}

		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);

		fos.close();
		fis.close();

		recalculateExcelFormulas(file)
	}

	@Keyword
	public void exel4(String name, int rowNum, int colNum, String file, String sheetNumber) throws IOException {

		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet(sheetNumber);

		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum); // Create a new row if it doesn't exist
		}

		Cell cell = row.createCell(colNum);

		if (name.matches("\\d+")) {  //Convert 'name' String to Integer if it has numbers only
			int intValue = Integer.parseInt(name);
			cell.setCellValue(intValue);
		}

		else {

			def chinesePattern = /\p{IsHan}/

			if (name =~ chinesePattern) {

				def convertedName=super.convertChineseToEnglishDate(name)
				cell.setCellValue(convertedName); //'name' String that has Chinese letters will be converted to English

			}

			else {

				//'name' String that has letters will remain as String
				cell.setCellValue(name);}

		}


		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);

		fos.close();
		fis.close();

		recalculateExcelFormulas(file)
	}

	@Keyword
	public void exel3(def name, int rowNum, int colNum, String file, String sheetNumber) throws IOException {

		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet(sheetNumber);

		//sheet.protectSheet(null)

		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum); // Create a new row if it doesn't exist
		}

		Cell cell = row.createCell(colNum);

		// Define a regular expression to match Chinese characters
		def chinesePattern = /\p{IsHan}/

		if (name =~ chinesePattern) {

			def convertedName=super.convertChineseToEnglishDate(name)
			cell.setCellValue(convertedName);}

		else {

			cell.setCellValue(name);}

		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);

		fos.close();
		fis.close();

		recalculateExcelFormulas(file)
	}

	@Keyword
	public void exel5withoutRecalculate(String name, int rowNum, int colNum, String file, String sheetNumber) throws IOException {

		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet(sheetNumber);

		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum); // Create a new row if it doesn't exist
		}

		Cell cell = row.createCell(colNum);

		if (name.matches("\\d+")) {  //Convert 'name' String to Integer if it has numbers only
			int intValue = Integer.parseInt(name);
			cell.setCellValue(intValue);
		}

		else {

			def chinesePattern = /\p{IsHan}/

			if (name =~ chinesePattern) {

				def convertedName=super.convertChineseToEnglishDate(name)
				cell.setCellValue(convertedName); //'name' String that has Chinese letters will be converted to English

			}

			else {

				//'name' String that has letters will remain as String
				cell.setCellValue(name);}
		}


		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);

		fos.close();
		fis.close();

	}

	@Keyword
	public void excelSetCellStyleDoubleDR(String name, int rowNum, int colNum, String file, String sheetNumber) throws IOException {
		// Convert name to a double value
		double doubleValue = Double.parseDouble(name);

		println doubleValue

		// Skip if the value is 0
		if (doubleValue == 0.0) {
			return;
		}

		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet(sheetNumber);

		// Create number format style
		DataFormat dataFormat = workbook.createDataFormat();
		CellStyle numberStyle = workbook.createCellStyle();
		numberStyle.setDataFormat(dataFormat.getFormat("0"));

		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum); // Create a new row if it doesn't exist
		}

		Cell cell = row.createCell(colNum);
		cell.setCellValue(doubleValue);
		cell.setCellStyle(numberStyle);

		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);

		fos.close();
		fis.close();

		recalculateExcelFormulas(file);
	}

	@Keyword
	public void exelSheetNumber(def name, int rowNum, int colNum, String file) throws IOException {

		FileInputStream fis = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fis);

		Sheet sheet = workbook.getSheetAt(0);

		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum); // Create a new row if it doesn't exist
		}

		Cell cell = row.createCell(colNum);


		// Define a regular expression to match Chinese characters
		def chinesePattern = /\p{IsHan}/

		if (name =~ chinesePattern) {

			def convertedName=super.convertChineseToEnglishDate(name)
			cell.setCellValue(convertedName);
		}
		else {
			cell.setCellValue(name);}

		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);

		fos.close();
		fis.close();

		recalculateExcelFormulas(file)
	}


	@Keyword
	public void exelSheetNumber2(String name, int rowNum, int colNum, String file, int sheetNumber) throws IOException {

		if (name == null || name.isEmpty()) {
			name = ""; // Assign a default value to the name parameter
		}

		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum); // Create a new row if it doesn't exist
		}

		// Use the IFERROR() function to handle missing arguments
		//int intValue = Integer.parseInt(name)

		Cell cell = row.createCell(colNum);


		// Define a regular expression to match Chinese characters
		def chinesePattern = /\p{IsHan}/

		if (name =~ chinesePattern) {

			def convertedName=super.convertChineseToEnglishDate(name)
			cell.setCellValue(convertedName);
		}
		else {
			cell.setCellValue(name);}

		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);

		fos.close();
		fis.close();

		//recalculateExcelFormulas(file);

	}

	@Keyword
	public String exelInboundPlan(File file, String name, int rowNum, int colNum) throws IOException{

		//convert downloaded File to Path
		String path = file.getAbsolutePath();
		path = path.replace("\\","\\\\");

		System.out.println(path);


		//open excel file
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		//edit
		XSSFSheet sheet = workbook.getSheet("Inbound");
		Row row = sheet.getRow(rowNum);
		Cell cell = row.createCell(colNum);

		// Define a regular expression to match Chinese characters
		def chinesePattern = /\p{IsHan}/

		if (name =~ chinesePattern) {
			def convertedName=super.convertChineseToEnglishDate(name)
			cell.setCellValue(convertedName);}
		else {
			cell.setCellValue(name);}

		//close
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

		//return the file path for upload
		System.out.println("File path is: " + path);
		return path;

	}

	@Keyword
	public String exelInboundPlan2(File file, String name, int rowNum, int colNum) throws IOException{

		//convert downloaded File to Path
		String path = file.getAbsolutePath();
		path = path.replace("\\","\\\\");

		System.out.println(path);

		//open excel file
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		//edit
		XSSFSheet sheet = workbook.getSheet("Inbound");
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(colNum);


		// Define a regular expression to match Chinese characters
		def chinesePattern = /\p{IsHan}/

		if (name =~ chinesePattern) {

			def convertedName=super.convertChineseToEnglishDate(name)
			cell.setCellValue(convertedName);}

		else {

			cell.setCellValue(name);}

		//close
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		fos.close();

		//return the file path for upload
		System.out.println("File path is: " + path);
		return path;

	}

	public void recalculateExcelFormulas(path) {
		// Load an existing Excel workbook
		def inputFile = new File(path)
		def fileInputStream = new FileInputStream(inputFile)
		def workbook = new XSSFWorkbook(fileInputStream)
		def evaluator = workbook.getCreationHelper().createFormulaEvaluator()

		// Recalculate formulas
		evaluator.evaluateAll()

		// Save the recalculated workbook back to the same file
		def fileOutputStream = new FileOutputStream(inputFile)
		workbook.write(fileOutputStream)
		fileOutputStream.close()

		workbook.close()
		fileInputStream.close()
	}

	@Keyword
	public void writeDateToExcel(String date, int rowNum, int colNum, String file, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet(sheetName);

		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum); // Create a new row if it doesn't exist
		}

		Cell cell = row.createCell(colNum);

		// Format the date as "dd MMM yyyy"
		CellStyle dateStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd MMM yyyy"));


		// Define a regular expression to match Chinese characters
		def chinesePattern = /\p{IsHan}/

		if (date =~ chinesePattern) {

			def convertedDate=super.convertChineseToEnglishDate(name)
			cell.setCellValue(new Date(convertedDate));
			cell.setCellStyle(dateStyle);
		}
		else {

			cell.setCellValue(new Date(date));
			cell.setCellStyle(dateStyle);}

		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);

		fos.close();
		fis.close();
	}

}