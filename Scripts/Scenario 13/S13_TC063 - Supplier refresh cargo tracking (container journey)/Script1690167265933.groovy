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

//String rownum = findTestData('Data Files/Scenario 13/S13_TC063').getRowNumbers()
WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : password, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC063/button_Logistics'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC063/li_Cargo Tracking(Container Journey)'))

for (int row = 1; row <= testData62.getRowNumbers(); row++) {
    if (testData62.getValue('Portcast', row) == 'Yes') {
        String containerID = testData62.getValue('ContainerNo', row)

        WebUI.setText(findTestObject('Scenario 13/S13_TC063/input_Cargo Tracking(Container Journey)'), containerID)

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC063/div_Container_Id'), containerID)

        WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC063/button_refresh'), 0)

        WebUI.click(findTestObject('Scenario 13/S13_TC063/button_refresh'))

        WebUI.delay(1)

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC063/h6_Refresh All Uncompleted Bookmarks'), 'Refresh All Uncompleted Bookmarks')

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC063/p_The operation was successful'), 'The operation was successful.')

        WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC063/div_ETA_POD'), 0)

        eta_pod_date = WebUI.getText(findTestObject('Scenario 13/S13_TC063/div_ETA_POD'))

        plan_eta_date = WebUI.getText(findTestObject('Scenario 13/S13_TC063/div_Plan_ETA_POD'))

        discharge_date = WebUI.getText(findTestObject('Scenario 13/S13_TC063/div_Discharge_Date'))

        String lastEvent = WebUI.getText(findTestObject('Scenario 13/S13_TC063/div_last Event'))

        String lastEventDetail = ''

        String lastEventDate = ''

        def datePattern = '\\d{2} \\w{3} \\d{4} : '

        def matcher = lastEvent =~ datePattern

        if (matcher.find()) {
            lastEventDetail = lastEvent.substring(matcher.end())

            println(lastEventDetail)
        }
        
        def datePattern2 = '(\\d{2} \\w{3} \\d{4}) : '

        def matcher2 = lastEvent =~ datePattern2

        if (matcher2.find()) {
            lastEventDate = ((matcher2[0])[1])

            println(lastEventDate)
        }
        
        CustomKeywords.'copyToExcel.exel'(containerID, row, 0, excelpath, excelname, excelsheet)

        CustomKeywords.'copyToExcel.exel'(eta_pod_date, row, 1, excelpath, excelname, excelsheet)

        CustomKeywords.'copyToExcel.exel'(plan_eta_date, row, 2, excelpath, excelname, excelsheet)

        CustomKeywords.'copyToExcel.exel'(discharge_date, row, 3, excelpath, excelname, excelsheet)

        CustomKeywords.'copyToExcel.exel'(lastEventDetail, row, 4, excelpath, excelname, excelsheet)

        CustomKeywords.'copyToExcel.exel'(lastEventDate, row, 5, excelpath, excelname, excelsheet)

        CustomKeywords.'util.clearTextJS.clearElementText'(findTestObject('Scenario 13/S13_TC063/input_Cargo Tracking(Container Journey)'))
    }
}

WebUI.closeBrowser()

