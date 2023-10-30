import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Get the WebDriver instance
WebDriver driver = DriverFactory.getWebDriver()

try {
	// Locate the table using a complex XPath based on bookingNo and containerNo
	WebElement table = driver.findElement(By.xpath('//table[2]'))

	// Find all the tr elements within the table (skipping the first tr)
	List<WebElement> trElements = table.findElements(By.xpath('.//tr[position() > 0]'))

	//To iterate index for loop below
	int i = 1

	// Iterate through the list of tr elements
	for (WebElement tr : trElements) {
		// Find the first p element within each tr
		WebElement firstP = tr.findElement(By.tagName('p'))

		// Get the text content of the first p element
		String text = firstP.getText()

		def partsNo = datafile_suporderchange.getValue('PartsNo', i)

		if (text == partsNo) {
			def newFirmValueQty = datafile_suporderchange.getValue('newFirmQty', i)

			def formattedDeliveredQty = newFirmValueQty.replaceAll('(\\d)(?=(\\d{3})+(?!\\d))', '$1,')

			WebUI.verifyElementText(findTestObject('Page_ChangeCancelReqList/div_td_partsDetailnfo', [('row') : i, ('col') : col]),
				formattedDeliveredQty)
		}
		
		i++
	}
}
finally {
	// You can add cleanup or resource release logic here if needed
}