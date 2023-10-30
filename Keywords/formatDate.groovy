import java.text.SimpleDateFormat

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil




public class DateFormatter {

	@Keyword
	static String formatDate(String inputDateStr) {
		def dateFormat = new SimpleDateFormat('dd MMM yyyy')
		def parsedDate = dateFormat.parse(inputDateStr)

		def newDateFormat = new SimpleDateFormat('MMM d, yyyy')
		String formattedDate = newDateFormat.format(parsedDate)

		// Log the formatted date using KeywordUtil.logInfo
		KeywordUtil.logInfo("Formatted date: $formattedDate")

		return formattedDate
	}
}



