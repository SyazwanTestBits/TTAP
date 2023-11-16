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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.text.ParseException as ParseException
import java.util.Locale
import java.util.Date as Date

import internal.GlobalVariable

public class DateConversionLocal {

	@Keyword
	public static String convertLocalChineseIntoLocalEnglishWithOutput(String inputDate, String inputFormat, String outputFormat) {

		//Standard input = 'd MMM yyyy'

		def SimpleDateFormat sdf = new SimpleDateFormat(inputFormat, Locale.ENGLISH)
		def parsedDate = sdf.parse(inputDate)


		def outputLocalFormat = new SimpleDateFormat(outputFormat, Locale.ENGLISH)
		String formattedDate = outputLocalFormat.format(parsedDate)

		return formattedDate

	}
	
	@Keyword
	public static String convert_DateChinese_Into_DateEnglish_WithOutput_V2(String inputDate, String inputFormat, String outputFormat) {

		//Standard input = 'd MMM yyyy'

		def SimpleDateFormat sdf = new SimpleDateFormat(inputFormat)
		def parsedDate = sdf.parse(inputDate)


		def outputLocalFormat = new SimpleDateFormat(outputFormat, Locale.ENGLISH)
		String formattedDate = outputLocalFormat.format(parsedDate)

		return formattedDate

	}


	@Keyword
	public static String convertLocalChineseIntoLocalEnglish(String inputDate) {

		//Standard input = 'd MMM yyyy'

		def SimpleDateFormat sdf = new SimpleDateFormat('d MMM yyyy', Locale.ENGLISH)
		def parsedDate = sdf.parse(inputDate)

		String formattedDate = sdf.format(parsedDate)

		return formattedDate

	}

	
	
	@Keyword
	def changeDateFormat_Chinese_Into_Chinese (String inputDate, String inputFormat, String outputFormat) {

		//String outputFormat = "d/M/yyyy"
		//inputfor s13 tc62="MMM d, yyyy"

		SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat)
		SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat)

		Date date = inputDateFormat.parse(inputDate)
		String formattedDate = outputDateFormat.format(date)

		return formattedDate
	}
	
	
	
}
