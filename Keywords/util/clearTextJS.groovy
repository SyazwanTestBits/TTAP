
package util

import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By
import org.openqa.selenium.firefox.FirefoxDriver


public class clearTextJS {

	@Keyword
	def static clearElementText(TestObject to) {
		WebElement element = WebUiCommonHelper.findWebElement(to,100)
		WebUI.executeJavaScript("arguments[0].value=''", Arrays.asList(element))
	}

	@Keyword
	def static clearElementText2(TestObject to) {
		WebUI.click(to)
		WebUI.sendKeys(to, Keys.chord(Keys.CONTROL + 'a'))
		WebUI.sendKeys(to, Keys.chord(Keys.DELETE))
	}

	@Keyword
	def static clearElementTextDoubleClick(TestObject to) {

		WebElement element = WebUiCommonHelper.findWebElement(to,100)
		element.click()
		element.sendKeys(Keys.chord(Keys.CONTROL, 'A',Keys.DELETE))
	}
}
