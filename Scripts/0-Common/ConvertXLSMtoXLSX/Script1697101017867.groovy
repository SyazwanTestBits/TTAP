import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('https://www.freeconvert.com/xlsm-to-xlsx')

WebUI.maximizeWindow()

WebUI.delay(5)

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Convert/div_File   Choose Files'), latestpath)

WebUI.waitForElementVisible(findTestObject('Object Repository/Convert/button_Convert'), 0)

WebUI.click(findTestObject('Object Repository/Convert/button_Convert'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Convert/div_Done'), 0)

WebUI.click(findTestObject('Object Repository/Convert/a_Download'))

WebUI.delay(2)

latestXLSX = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.closeBrowser()

