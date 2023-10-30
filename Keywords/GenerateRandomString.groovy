import java.text.SimpleDateFormat

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

public class GenerateRandomString {

	@Keyword(keywordObject='Generator')
	def generateRandomMasterCode(int codeLength) {

		// Generate a random code
		String regionCode = UUID.randomUUID().toString().replaceAll("[^A-Za-z0-9]", "").substring(0, codeLength);

		// Print the generated code
		KeywordUtil.logInfo("Generated Region Code: " + regionCode);

		return regionCode

	}

	@Keyword(keywordObject='Generator')
	def generateTimestamp() {
		// Create a SimpleDateFormat object with the desired timestamp format
		def timestampFormat = new SimpleDateFormat("yyyyMMddHHmmss")

		// Get the current date and time
		def currentTime = new Date()

		// Format the current date and time as a timestamp
		def timestamp = timestampFormat.format(currentTime)

		// Print the updated regionName
		KeywordUtil.logInfo('Timestamp generated: ' + timestamp)

		return timestamp
	}
}
