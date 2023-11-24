

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

import internal.GlobalVariable

import org.openqa.selenium.support.events.EventFiringWebDriver

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.exception.BrowserNotOpenedException

public class python {

	@Keyword
	def helloWorld(int a, int b) {
		runPython("keywords.hello_world", a, b)
	}

	@Keyword
	def disableProtect_excel(String filename) {
		runPython("keywords.disableProtect_excel", filename)
	}

	@Keyword
	def disableProtect_excel_v2(String filename) {
		runPython("keywords.disableProtect_excel_v2", filename)
	}

	@Keyword
	def convert_xlsm_xlsx(String filename) {
		runPython("convert_xlsm_xlsx", filename)
	}

	private runPython(String script, Object[] keywordArgs) {
		try {
			KeywordUtil.logInfo(Arrays.deepToString(keywordArgs))
			def outputFile = File.createTempFile("ks_py_output_", ".tmp")
			KeywordUtil.logInfo(outputFile.absolutePath)

			def allArgs = [
				keyword: script,
				keywordArgs: keywordArgs,
				outputPath: outputFile.absolutePath
			]

			try {
				def webdriver = DriverFactory.getWebDriver()
				if (webdriver instanceof EventFiringWebDriver) {
					webdriver = ((EventFiringWebDriver) webdriver).getWrappedDriver()
				}
				allArgs["webDriver"] = [
					sessionId: webdriver.getSessionId().toString(),
					serverUrl: DriverFactory.getWebDriverServerUrl(webdriver)
				]
			} catch (BrowserNotOpenedException e) {
			}

			def inputFile = File.createTempFile("ks_py_input_", ".tmp")
			def allArgsJson = groovy.json.JsonOutput.toJson(allArgs)
			KeywordUtil.logInfo(allArgsJson)
			inputFile.write(allArgsJson)

			KeywordUtil.logInfo("Project directory " + RunConfiguration.getProjectDir());
			def pb = new ProcessBuilder(
					"python",
					RunConfiguration.getProjectDir() + "/python/execution.py",
					inputFile.absolutePath)
			def process = pb.start()
			def errCode = process.waitFor()

			def line
			def input = new BufferedReader(new InputStreamReader(process.getInputStream()))
			while ((line = input.readLine()) != null) {
				KeywordUtil.logInfo(line)
			}
			input.close()
		} catch (Exception err) {
			err.printStackTrace()
		}
	}
}
