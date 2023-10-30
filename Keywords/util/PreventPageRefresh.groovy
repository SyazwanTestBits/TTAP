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
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions as Actions
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import internal.GlobalVariable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

public class PreventRejectInputRefresh {

	@Keyword  //Do not receive any variable from test case for this method ( e.g text,locators,etc) so that it will not interfere with the process in this method
	public void stopPageRefresh() {
		// Get the WebDriver instance
		WebDriver driver=DriverFactory.getWebDriver()

		// Find a web element, for example, an input field
		WebElement inputElement = driver.findElement(By.xpath("//div[@role='dialog']//input[@name='rejectReason']"))

		// Create an ExecutorService with multiple threads
		ExecutorService executorService = Executors.newFixedThreadPool(2) // Adjust the number of threads as needed

		// Submit tasks to the ExecutorService
		executorService.submit({
			inputElement.sendKeys("Reject")
		} as Runnable)

		executorService.submit({
			10.times {
				inputElement.sendKeys(Keys.ESCAPE)  //To prevent auo=to refresh after inputting texts, Escape button is pressed for 10 times
			}
		} as Runnable)

		// Shutdown the ExecutorService when done
		executorService.shutdown()
	}

}


