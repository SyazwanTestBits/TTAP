import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.click(findTestObject('Scenario 12/SC12_TC049/p_detailButton', [('COid') : RegularOrderNo]))

WebUI.waitForElementPresent(findTestObject('Scenario 12/SC12_TC049/h3_CO Monitoring Detail'), 0)

WebUI.verifyElementAttributeValue(findTestObject('Page_SO_MonitoringDetail/input_SalesOrderNo'), 'value', RegularOrderNo, 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 12/SC12_TC046/input_detailStatus'), 'value', 'Processing', 0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 12/SC12_TC049/p_CO Monitoring List'), 0)

WebUI.click(findTestObject('Scenario 12/SC12_TC049/p_checkbox', [('COid') : RegularOrderNo]))

WebUI.click(findTestObject('Scenario 12/SC12_TC049/button_download'))

WebUI.click(findTestObject('Scenario 12/SC12_TC049/li_Download by Excel'))

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC049/div_Download Customer Order by Excel.The operation was successful'), 
    0)

downloadedFile = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'verifyExcelData.verifyDynamicSort'(expectedCOregular, downloadedFile, 1, [23, 24, 25, 26, 27, 28], [1, 2, 3
        , 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15])

WebUI.takeFullPageScreenshot()

WebUI.click(findTestObject('Scenario 12/SC12_TC049/p_checkbox', [('COid') : RegularOrderNo]))

