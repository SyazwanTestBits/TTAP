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
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor

import internal.GlobalVariable

public class ScrollToElement {

	@Keyword
	public void clickUsingJS(TestObject to, int timeout) {
		WebDriver driver = DriverFactory.getWebDriver()

		WebElement element = WebUiCommonHelper.findWebElement(to, timeout)

		JavascriptExecutor executor = ((driver) as JavascriptExecutor)

		executor.executeScript('arguments[0].click()', element)
	}


	@Keyword
	public void verifyElementUsingJS(TestObject to, int timeout) {
		WebDriver driver = DriverFactory.getWebDriver()

		WebElement myElement = WebUiCommonHelper.findWebElement(to, timeout)

		JavascriptExecutor executor = ((driver) as JavascriptExecutor)

		executor.executeScript('arguments[0].click()', myElement)
	}

	@Keyword
	public void scrollElementUsingJS(TestObject to, int timeout) {
		WebDriver driver = DriverFactory.getWebDriver()

		WebElement myElement = WebUiCommonHelper.findWebElement(to, timeout)

		JavascriptExecutor executor = ((driver) as JavascriptExecutor)

		executor.executeScript('arguments[0].scrollIntoView({ behavior: "smooth", block: "center", inline: "center" })', myElement)
	}

	@Keyword
	static void scrollUsingJS(String direction, int distance) {
		WebDriver driver = DriverFactory.getWebDriver()
		JavascriptExecutor js = ((JavascriptExecutor) driver)

		def jsScript = ""

		switch (direction.toLowerCase()) {
			case "up":
				jsScript = "window.scrollBy(0, -" + distance + ");"
				break
			case "down":
				jsScript = "window.scrollBy(0, " + distance + ");"
				break
			case "left":
				jsScript = "window.scrollBy(-" + distance + ", 0);"
				break
			case "right":
				jsScript = "window.scrollBy(" + distance + ", 0);"
				break
			default:
				println("Invalid direction: " + direction)
				return
		}

		js.executeScript(jsScript)
	}
}
