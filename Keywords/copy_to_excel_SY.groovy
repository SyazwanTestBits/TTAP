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
import org.apache.poi.openxml4j.util.ZipSecureFile as ZipSecureFile
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellReference as CellReference
import org.apache.poi.xssf.usermodel.XSSFSheet as XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import com.kms.katalon.core.annotation.Keyword as Keyword

public class copy_to_excel_SY {

	@Keyword
	public void exel_SY(String name, int rowNum, int colNum, String file_input, String sheetName) {

		FileInputStream fis = new FileInputStream(file_input)

		XSSFWorkbook workbook = new XSSFWorkbook(fis)

		XSSFSheet sheet = workbook.getSheet('Global Parts')

		Row row = sheet.getRow(rowNum)

		if (row == null) {
			row = sheet.createRow(rowNum // Create a new row if it doesn't exist
					)
		}

		Cell cell = row.createCell(colNum)

		if (name.matches('\\d+')) {
			//Convert 'name' String to Integer if it has numbers only
			int intValue = Integer.parseInt(name)

			cell.setCellValue(intValue //'name' String that has Chinese letters will be converted to English
					//'name' String that has letters will remain as String
					)
		} else {
			def chinesePattern = '\\p{IsHan}'

			if (name =~ chinesePattern) {
				def convertedName = super.convertChineseToEnglishDate(name)

				cell.setCellValue(convertedName)
			} else {
				cell.setCellValue(name)
			}
		}

		FileOutputStream fos = new FileOutputStream(file_input)

		workbook.write(fos)

		fos.close()

		fis.close()
	}
}
