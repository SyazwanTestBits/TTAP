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

Windows.startApplicationWithTitle('C:\\Program Files\\Microsoft Office\\root\\Office16\\EXCEL.EXE', '')

Windows.click(findWindowsObject('Object Repository/Windows/excel/ListItem'))

Windows.click(findWindowsObject('Object Repository/Windows/excel/Button'))

Windows.setText(findWindowsObject('Object Repository/Windows/excel/Edit'), xlsm)

Windows.click(findWindowsObject('Object Repository/Windows/excel/SplitButton'))

Windows.click(findWindowsObject('Windows/excel/Button_enable editing'))

Windows.delay(3)

Windows.click(findWindowsObject('Windows/excel/Button_xlsm_file'))

Windows.click(findWindowsObject('Windows/excel/list_xlsm_files_save as'))

Windows.click(findWindowsObject('Windows/excel/list_xlsm_files_save as_Browse'))

Windows.click(findWindowsObject('Windows/excel/ComboBox_xlsm_files_save as_Browse_type'))

Windows.sendKeys(findWindowsObject('Windows/excel/ComboBox_xlsm_files_save as_Browse_type'), Keys.chord('E', 'E', 'E', 'E', 
        'E', 'E', 'E', 'E', Keys.ENTER))

Windows.click(findWindowsObject('Windows/excel/Button__xlsm_files_save as_Browse_save'))

Windows.closeApplication()

