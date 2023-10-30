import java.util.regex.Pattern

import javax.mail.Folder
import javax.mail.Message
import javax.mail.Session
import javax.mail.Store
import javax.mail.search.SubjectTerm

import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.util.CryptoUtil

import internal.GlobalVariable

public class GetBrivgePasswordResetLink {

	// Stores the extracted verification codes
	private static String pwdResetLink;

	// Entry point of the script
	public static void main(String[] args) {
		extractUserPasswordResetLink()
	}

	// Keyword to extract user verification codes
	@Keyword
	static String extractUserPasswordResetLink() {
		// Fetch email account credentials from global variables
		String username = GlobalVariable.NOTIFICATION_TEST_EMAIL;
		String password = CryptoUtil.decode(CryptoUtil.getDefault(GlobalVariable.NOTIFICATION_TEST_EMAIL_PWD));

		// IMAP server properties
		String host = "imap-mail.outlook.com"
		String port = "993"

		// Set properties for the IMAP server connection
		Properties properties = new Properties()
		properties.setProperty("mail.store.protocol", "imaps")
		properties.setProperty("mail.imaps.host", host)
		properties.setProperty("mail.imaps.port", port)
		properties.setProperty("mail.imaps.ssl.enable", "true")

		// Create a session for the email account
		Session session = Session.getInstance(properties)

		// Connect to the IMAP server
		Store store = session.getStore("imaps")
		store.connect(host, username, password)

		// Open the inbox folder
		Folder inbox = store.getFolder("INBOX")
		inbox.open(Folder.READ_ONLY)

		// Refresh the folder to potentially resolve synchronization issues
		KeywordUtil.logInfo("Messages Count: " + inbox.getMessageCount())

		// Search for messages with the subject "BriVge Verification Code"
		Message[] messages = inbox.search(new SubjectTerm("BriVge Password Reset"))

		// Process messages if found
		if (messages.length > 0) {
			// Get the latest message
			Message latestMessage = messages[messages.length - 1];

			// Convert the email to JSON (important!)
			JSONObject latestMessageObj = new JSONObject()
			try {
				latestMessageObj.put("subject", latestMessage.subject)
				latestMessageObj.put("sender", latestMessage.from[0].toString())
				latestMessageObj.put("sentDate", latestMessage.sentDate.toString())
				latestMessageObj.put("body", latestMessage.getContent().toString())

				KeywordUtil.logInfo("Latest email: \n" + latestMessageObj.toString())

				// Extract the "body" from the latestMessageObj
				String bodyText = latestMessageObj.getString("body")

				// Parse the HTML content using Jsoup
				Document doc = Jsoup.parse(bodyText)

				// Select the <a> element with the specific href attribute
				Element linkElement = doc.select("a[href^='https://click.pstmrk.it']").first()

				// Get the value of the href attribute (the link)
				pwdResetLink = linkElement.attr("href")

				// Log the extracted reset link
				KeywordUtil.logInfo("Extracted Reset Link: " + pwdResetLink);

				// Extract text content from the document and preprocess it
				String extractedText = doc.text().replaceAll("\\s+", " ").trim()

				KeywordUtil.logInfo("Extracted Text from Email: \n" + extractedText)

			} catch (IOException e) {
				e.printStackTrace()
			} catch (Exception e) {
				e.printStackTrace()
			}
		}

		return pwdResetLink
	}
}
