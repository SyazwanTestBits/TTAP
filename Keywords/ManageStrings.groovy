import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil













public class ManageStrings {

	/**
	 * Converts the first letter of each word to uppercase and the remaining letters to lowercase.
	 * @param input The input string containing one or more words.
	 * @return The modified string with the first letter of each word capitalized and the rest of the letters lowercase.
	 */
	@Keyword(keywordObject='Manage Strings')
	def capitalizeFirstLetterEachWordToLowercaseRest(String input) {
		// Split the input string into an array of words
		String[] words = input.split(" ")

		// Initialize an empty string to store the output
		String output = ""

		// Process each word
		for (word in words) {
			// Capitalize the first letter and convert the remaining letters to lowercase
			output += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " "
		}

		// Trim any leading or trailing spaces from the output string
		output = output.trim()

		// Log the modified string
		KeywordUtil.logInfo('Returned output: ' + output)

		// Return the modified string
		return output

	}
}
