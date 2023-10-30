import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject


import java.util.regex.Matcher
import java.util.regex.Pattern

import javax.mail.BodyPart
import javax.mail.Folder
import javax.mail.Message
import javax.mail.Multipart
import javax.mail.Session
import javax.mail.Store
import javax.mail.search.SubjectTerm
import org.json.JSONArray
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
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

import internal.GlobalVariable

public class test {

	private static String verificationCodes;

	public static void main(String[] args) {
		//startExecution()
		extract()
	}


	@Keyword
	static String extract() {
		// Email account credentials
		String username ="musyaruspentium@outlook.com"
		String password = "rivcdhbslhhmiauo"

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
		println(inbox.getMessageCount())

		// Search for messages with the subject "BriVge Verification Code"
		Message[] messages = inbox.search(new SubjectTerm("BriVge Verification Code"))


		if (messages.length > 0) {
			// Get the latest message
			Message latestMessage = messages[messages.length - 1];

			//Convert the email to JSON (important!)
			JSONObject latestMessageObj = new JSONObject()
			try {
				latestMessageObj.put("subject", latestMessage.subject)
				latestMessageObj.put("sender", latestMessage.from[0].toString())
				latestMessageObj.put("sentDate", latestMessage.sentDate.toString())
				latestMessageObj.put("body", latestMessage.getContent().toString())

				println("Latest email: " + latestMessageObj.toString())

				// Extract the "body" from the latestMessageObj
				String bodyText = latestMessageObj.getString("body")

				// Parse the HTML content using Jsoup
				Document doc = Jsoup.parse(bodyText)

				// Extract text content from the document and preprocess it
				String extractedText = doc.text().replaceAll("\\s+", " ").trim()

				println(extractedText)

				//Find verification numbers:

				String regex = "\\b\\d{6}\\b"
				java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex)
				java.util.regex.Matcher matcher = pattern.matcher(extractedText)

				// Extract and print the six-digit numbers
				/*
				 while (matcher.find()) {
				 String sixDigitNumber = matcher.group()
				 println("First occurrence: $sixDigitNumber")
				 break // Once the first six-digit number is found, the loop will exit
				 }
				 */

				while (matcher.find()) {
					String sixDigitNumber = matcher.group()
					verificationCodes = sixDigitNumber
					println("Found six-digit number: $sixDigitNumber")
					break
				}
			} catch (IOException e) {
				e.printStackTrace()



			} catch (Exception e) {
				e.printStackTrace()
			}
		}


		return verificationCodes
	}


}






