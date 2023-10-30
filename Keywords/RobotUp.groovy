import java.awt.*
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor


public class RobotUpload {

	@Keyword
	def uploadFile(TestObject to, String filePath) {

		WebUI.click(to)

		Thread.sleep(1000)

		StringSelection ss = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();

		robot.delay(1000)
		robot.keyPress(KeyEvent.VK_CONTROL);    //press Control
		robot.keyPress(KeyEvent.VK_V);           //press  V       = means kita paste excel file path

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		robot.delay(1000)

		robot.keyPress(KeyEvent.VK_ENTER);     //press  Enter
		robot.keyRelease(KeyEvent.VK_ENTER);    //release Enter
	}


	@Keyword
	def uploadFileUsingJS(TestObject to, String filePath) {

		WebDriver driver = DriverFactory.getWebDriver()

		WebElement element = WebUiCommonHelper.findWebElement(to, 3)

		JavascriptExecutor executor = ((driver) as JavascriptExecutor)

		executor.executeScript('arguments[0].click()', element)

		Thread.sleep(500)

		StringSelection ss = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();

		robot.delay(1000)
		robot.keyPress(KeyEvent.VK_CONTROL);    //press Control
		robot.keyPress(KeyEvent.VK_V);           //press  V       = means kita paste excel file path

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		robot.delay(1000)

		robot.keyPress(KeyEvent.VK_ENTER);     //press  Enter
		robot.keyRelease(KeyEvent.VK_ENTER);    //release Enter
	}
}
















