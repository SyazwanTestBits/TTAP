import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.openxml4j.util.ZipSecureFile
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellReference
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.annotation.Keyword

//file='excel.xlsx'
sheetName="Global Parts"
rowNum=10
colNum=1
name="hello world"

println(file)

FileInputStream fis = new FileInputStream(file);
XSSFWorkbook workbook = new XSSFWorkbook(fis);

XSSFSheet sheet = workbook.getSheet(sheetName);


Row row = sheet.getRow(rowNum);
if (row == null) {
	row = sheet.createRow(rowNum); // Create a new row if it doesn't exist
}

Cell cell = row.createCell(colNum);
cell.setCellValue(name);

FileOutputStream fos = new FileOutputStream(file);
workbook.write(fos);

fos.close();
fis.close();

//recalculateExcelFormulas(file)