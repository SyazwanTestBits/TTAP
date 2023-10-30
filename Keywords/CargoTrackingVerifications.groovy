import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI













public class CargoTrackingVerifications {

	@Keyword
	def verifyCargoTrackingMilestonesColor(String bookingNo, String containerNo) {
		// Get the WebDriver instance
		WebDriver driver = DriverFactory.getWebDriver()

		try {
			// Locate the table using a complex XPath based on bookingNo and containerNo
			WebElement table = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Booking Number: "+bookingNo+"    Container Number: "+containerNo+"'])[1]/following::table[2]"))

			// Find all the tr elements within the table (skipping the first tr)
			List<WebElement> trElements = table.findElements(By.xpath(".//tr[position() > 1]"))

			// Iterate through the list of tr elements
			for (WebElement tr : trElements) {
				// Find the first p element within each tr
				WebElement firstP = tr.findElement(By.tagName("p"))

				// Get the text content of the first p element
				String text = firstP.getText()

				// Get the style attribute value of the first p element
				String styleAttr = firstP.getAttribute('style')

				// Check if the style attribute contains "color: blue"
				if (styleAttr.contains("color: blue")) {
					KeywordUtil.logInfo("Element: $text  - has style attribute 'color: blue'")
				} else {
					KeywordUtil.markFailed("Element: $text  - does not have style attribute 'color: blue'")
				}
			}
		} finally {
			// You can add cleanup or resource release logic here if needed
		}
	}


}
