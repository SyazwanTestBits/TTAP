
import java.text.SimpleDateFormat
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI




public class commonUtils {

	@Keyword(keywordObject='Common Utils')
	def clearElementText(TestObject to) {
		WebElement element = WebUiCommonHelper.findWebElement(to,30)
		WebUI.executeJavaScript("arguments[0].value=''", Arrays.asList(element))
	}

	@Keyword(keywordObject='Common Utils')
	def parseDateDayMonthYearEachIntoInt(String date) {
		def (day, month, year) = date.split('/')
		def outboundDateDay = day.toInteger()
		def outboundDateMonth = month.toInteger()
		def outboundDateYear = year.toInteger()
		return [outboundDateDay, outboundDateMonth, outboundDateYear]
	}

	@Keyword(keywordObject='Common Utils')
	def parseDateDayMonthYearEachIntoInt_withFormat(String inputDate, String inputFormatDate) {

		String outputFormat = "d/M/yyyy"

		//Typical input format: MMM d, yyyy
		SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormatDate)
		SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat)
		Date date = inputDateFormat.parse(inputDate)
		String formattedDate = outputDateFormat.format(date)


		def (day, month, year) = formattedDate.split('/')
		def outboundDateDay = day.toInteger()
		def outboundDateMonth = month.toInteger()
		def outboundDateYear = year.toInteger()
		return [outboundDateDay, outboundDateMonth, outboundDateYear]
	}

	@Keyword(keywordObject='Common Utils')
	String parseDateInfoDesiredDateFormat(String dateString) {
		// Define the input and output date formats
		def inputFormat = new SimpleDateFormat("dd MMM yyyy")
		def outputFormat = new SimpleDateFormat("MMM dd, yyyy")

		// Parse the input date string into a Date object
		Date date
		try {
			date = inputFormat.parse(dateString)
		} catch (java.text.ParseException e) {
			throw new IllegalArgumentException("Invalid date format. Please use 'dd MMM yyyy' format, e.g., '29 Jul 2023'")
		}

		// Format the date as 'MMM dd, yyyy' using the output format
		String formattedDate = outputFormat.format(date)

		return formattedDate
	}

	@Keyword(keywordObject='Common Utils')
	String parseDateInfoDesiredDateFormat2(String dateString, String inputDateFormat, String outputDateFormat) {
		// Define the input and output date formats
		def inputFormat = new SimpleDateFormat(inputDateFormat)
		def outputFormat = new SimpleDateFormat(outputDateFormat)

		// Parse the input date string into a Date object
		Date date
		try {
			date = inputFormat.parse(dateString)
		} catch (java.text.ParseException e) {
			throw new IllegalArgumentException("Invalid date format. Please use 'dd MMM yyyy' format, e.g., '29 Jul 2023'")
		}

		// Format the date as 'MMM dd, yyyy' using the output format
		String formattedDate = outputFormat.format(date)

		return formattedDate
	}
}