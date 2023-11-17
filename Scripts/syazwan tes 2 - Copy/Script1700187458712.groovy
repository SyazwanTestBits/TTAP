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

try (InputStream fileStream = getClass().getResourceAsStream("/Content.zip");
	InputStream bufferedStream = new BufferedInputStream(fileStream);
	ZipInputStream zipStream = new ZipInputStream(bufferedStream)) {
 
 ZipEntry entry = zipStream.getNextEntry();
 System.out.println("Entry name: " + entry.getName());
 System.out.println("Entry compressed size: " + entry.getCompressedSize());
 System.out.println("Entry uncompressed size: " + entry.getSize());
 
 // Consume all bytes
 StringBuilder content = new StringBuilder();
 try {
   for (int b; (b = zipStream.read()) >= 0;) content.append((char) b);
 } catch (Exception e) {
   System.err.println("Stream failed at byte " + content.length());
   e.printStackTrace();
 }
}