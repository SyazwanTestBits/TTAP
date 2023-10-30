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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import internal.GlobalVariable

public class convertFile {

	@Keyword
	public static void main(String xlsmFilePath, String xlsxFilePath) throws IOException {
		
				// Get the paths to the XLSM and XLSX files.
				//String xlsmFilePath = "path/to/xlsm/file.xlsm";
				//String xlsxFilePath = "path/to/xlsx/file.xlsx";
		
				// Create a FileInputStream object for the XLSM file.
				FileInputStream fis = new FileInputStream(xlsmFilePath);
		
				// Create a FileOutputStream object for the XLSX file.
				FileOutputStream fos = new FileOutputStream(xlsxFilePath);
		
				// Create a byte array to store the data from the XLSM file.
				byte[] bytes = new byte[1024];
		
				// Read the data from the XLSM file and write it to the XLSX file.
				int read;
				while ((read = fis.read(bytes)) != -1) {
					fos.write(bytes, 0, read);
				}
		
				// Close the FileInputStream and FileOutputStream objects.
				fis.close();
				fos.close();
		
				System.out.println("Successfully converted XLSM file to XLSX file.");
			}
}
