import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.NumberFormat
import java.text.SimpleDateFormat

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.click(findTestObject('Scenario 12/SC12_TC014/p_detailsButton', [('SOnumber') : regularSalesOrderNo]))

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC050 TC053/input_SO status'), 'value', 'Received', 0)

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC050 TC053/p_total amaount currecy'), 'CNY')

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header-Parts Monitoring detailDisplay Monitor_d3694d'), 
    0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/button_Parts Monitoring detail_pop down'))

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header_Parts No header list'), 0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/header_Parts No header list'))

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/button_Download_SO Monitoring Detail'))

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/li_Download DR'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC050 TC053/div_Download DR For Supplier.The operation was successful'), 
    0)

supDR = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'excelUtils.unprotectExcelSheet'(supDR)

CustomKeywords.'excelUtils.clearOutValueExcel'(supDR, 10, 10)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 9/S9_Cmn1-Write Info into Form Excel (Delivery Plan)'), [('datafile') : datafile_dr
        , ('fileColumns') : filecolumns_dr, ('startRowFormMinusOne') : 9, ('downloadedFormPath') : supDR, ('downloadedFormSheetname') : 'Delivery Plan'
        , ('columnHeaderQty') : 'newInboundQty', ('columnHeaderListIndex') : 'columnListIndex', ('dateExcelRelPath') : 'Excel Files/Scenario 12/S12_TestCases_Data.xlsx'
        , ('dateSheetName') : 'TC17.1-Sup SODeliveryPlan(Date)'], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/button_Upload BU SO Detail'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 13/S13_TC050 TC053/li_Upload DR'), supDR)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC050 TC053/div_Upload Delivery RequestThe operation was successful'), 
    0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.delay(2)

for (def index : (1..datafile_date.getRowNumbers())) {
    def excelColumnSize = 2

    for (def index2 : (1..excelColumnSize)) {
        def dateValue = findTestData('Scenario 12/SC12_TC017-Supplier SO Delivery Plan (Date) -Regular').getValue('newPlanDate' + index2, index)

        if (dateValue != 'NULL') {
            def dateFormat = new SimpleDateFormat('MMM dd, yyyy')

            def parsedDate = dateFormat.parse(dateValue)

            if (parsedDate.date < 10) {
                def newDateFormat = new SimpleDateFormat('MMM d, yyyy')

                dateValue = newDateFormat.format(parsedDate)
            }
            
            WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC050 TC053/datePlanOutInDate', [('date') : dateValue]), 
                0)
        }
    }
}

for (def index : (1..datafile_dr.getRowNumbers())) {
    def excelColumnSize = 2

    def colNumStart = 15

    for (def index2 : (1..excelColumnSize)) {
        def planQtyValue = findTestData('Scenario 12/SC12_TC017-Supplier SO Delivery Plan -Regular').getValue('newInboundQty' + index2, index)

        def planQtyValueParse = Integer.parseInt(planQtyValue)

        // Create a NumberFormat instance for formatting as "1,000"
        def numberFormat = NumberFormat.getIntegerInstance()

        // Format the planQtyValue
        String formattedValue = numberFormat.format(planQtyValueParse)

        WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC050 TC053/newPlanQty', [('row') : index, ('col') : colNumStart]), 
            formattedValue)

        colNumStart = (colNumStart + 2)
    }
}

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 12/SC12_TC017/button_Confirm'), 0)

WebUI.click(findTestObject('Scenario 12/SC12_TC017/button_CONFIRMnoti'))

'\r\n'
WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC017/div_Confirm Supplier SO.The operation was successful'), 
    0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 12/SC12_TC017/p_SO Monitoring List'), 0)

