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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_SYAZWAN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC024/button_Stock Management Tool'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC027/li_Project'))

WebUI.waitForElementPresent(findTestObject('Scenario 10/S10_TC027/h3_Project'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC027/button_add new project'))

WebUI.waitForElementPresent(findTestObject('Scenario 10/S10_TC027/0_create project page/h3_Create Project'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/input__BuyerId'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/li_MY-PNA-CUS'))

WebUI.setText(findTestObject('Scenario 10/S10_TC027/0_create project page/input__projectCode'), projectCode)

WebUI.setText(findTestObject('Scenario 10/S10_TC027/0_create project page/input__projectName'), projectCode)

WebUI.setText(findTestObject('Scenario 10/S10_TC027/0_create project page/input__remarks'), projectCode)

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/button_Project Info_save'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/svg_close notification'))

WebUI.setText(findTestObject('Scenario 10/S10_TC027/0_create project page/input__mainDescription'), 'Main Description')

not_run: WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/li_Main Description'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/button_FIELDS TO DESCRIBE FINAL PRODUCT_save'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/svg_close notification'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/button_FIELDS TO DESCRIBE FINAL PRODUCT_confirm'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/button_CONFIRM_FIELDS TO DESCRIBE FINAL PRODUCT'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/svg_close notification'))

absolutePath = CustomKeywords.'ManageFiles.getFileAbsolutePath'('Excel Files/Scenario 10/S10_TC27/10.0 - TC27 - BU - Upload Product Field.xlsx')

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/button_FIELDS TO DESCRIBE FINAL PRODUCT_upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC027/0_create project page/li_FIELDS TO DESCRIBE FINAL PRODUCT_upload_Upload Field Info'), 
    absolutePath)

println(absolutePath)

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC027/0_create project page/h6_Upload Final Product'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/svg_close Upload Final Product notification'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/input__noOfLevel'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/li_noOfLevel 2'))

WebUI.setText(findTestObject('Scenario 10/S10_TC027/0_create project page/input__level1MainMaterial'), 'MODEL')

not_run: WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/li_level1MainMaterial_MODEL'))

WebUI.setText(findTestObject('Scenario 10/S10_TC027/0_create project page/input__level2MainMaterial_descp'), 'L2 PART NO')

'L2 PART NO'
not_run: WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/li_level2MainMaterial_L2_input', [('level2materialinput') : 'L2 PART NO']))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/button_level2MainMaterial_Add Description1'))

WebUI.setText(findTestObject('Scenario 10/S10_TC027/0_create project page/input__level2OtherMaterials0_descp1'), 'L2 PART NAME')

'L2 PART NAME'
not_run: WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/li_level2MainMaterial_L2_input', [('level2materialinput') : 'L2 PART NAME']))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/button_level2MainMaterial_Add Description2'))

WebUI.setText(findTestObject('Scenario 10/S10_TC027/0_create project page/input__level2OtherMaterials0_descp2'), 'L2 Status')

'L2 Status'
not_run: WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/li_level2MainMaterial_L2_input', [('level2materialinput') : 'L2 Status']))

CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Scenario 10/S10_TC027/0_create project page/button_FIELDS TO DESCRIBE MATERIALS USED IN FINAL PRODUCT_save'), 
    0)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC027/0_create project page/button_FIELDS TO DESCRIBE MATERIALS USED IN FINAL PRODUCT_save'), 
    0)

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC027/0_create project page/p_The operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/svg_close notification'))

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC027/0_create project page/button_FIELDS TO DESCRIBE MATERIALS USED IN FINAL PRODUCT_confirm'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/button_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC027/0_create project page/div_Save Project MaterialThe operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/svg_close notification'))

CustomKeywords.'util.ScrollToElement.scrollElementUsingJS'(findTestObject('Scenario 10/S10_TC027/0_create project page/button_FIELDS TO DESCRIBE MATERIALS USED IN FINAL PRODUCT-download'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/button_FIELDS TO DESCRIBE MATERIALS USED IN FINAL PRODUCT-download'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/li_Download Material Info'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'), FailureHandling.STOP_ON_FAILURE)

excelMaterial = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

'Looping for material field level 1'
for (int i = 1; i <= testDataMaterialFieldLevel1.getRowNumbers(); i++) {
    CustomKeywords.'copyToExcel.exel3'(testDataMaterialFieldLevel1.getValue('MODEL', i), 3 + i, 0, excelMaterial, 'Level 1')
}

'Looping for material field level 2'
for (int i = 1; i <= testDataMaterialFieldLevel2.getRowNumbers(); i++) {
    CustomKeywords.'copyToExcel.exel3'(testDataMaterialFieldLevel2.getValue('L2 Part No', i), 3 + i, 0, excelMaterial, 'Level 2')

    CustomKeywords.'copyToExcel.exel3'(testDataMaterialFieldLevel2.getValue('L2 Part Name', i), 3 + i, 1, excelMaterial, 
        'Level 2')

    CustomKeywords.'copyToExcel.exel3'(testDataMaterialFieldLevel2.getValue('L2 Part Status', i), 3 + i, 2, excelMaterial, 
        'Level 2')
}

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 10/S10_TC027/0_create project page/button_FIELDS TO DESCRIBE MATERIALS USED IN FINAL PRODUCT_upload'), 
    0)

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC027/0_create project page/li_FIELDS TO DESCRIBE MATERIALS USED IN FINAL PRODUCT_upload_Upload Material Info'), 
    excelMaterial)

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC027/0_create project page/div_Upload Final ProductThe operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/svg_close notification'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/button__BOM_download'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/li_Download Bom Usage'))

WebUI.delay(5)

excelPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

println(excelPath)

mapLevelField = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndicesWithSheetList'(excelPath, columnField, 4, 
    0)

println(mapLevelField)

for (int i = 1; i <= testDataField.getRowNumbers(); i++) {
    def searchPart = ''

    for (int col : columnField) {
        int colpilih = col + 1

        String part = testDataField.getValue(colpilih, i)

        searchPart = ((searchPart + part) + ' ')
    }
    
    println(searchPart)

    int fileRowIndex = mapLevelField[searchPart]

    println(fileRowIndex)

    def usageValue = testDataField.getValue('Usage', i)

    CustomKeywords.'copyToExcel.exel3'(testDataField.getValue('Usage', i), 3 + i, 2, excelPath, 'Field')
}

mapLevel1 = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndicesWithSheetList'(excelPath, columnLevel1, 4, 1)

for (int i = 1; i <= testDataLevel1.getRowNumbers(); i++) {
    def searchPart = ''

    for (int col : columnLevel1) {
        int colpilih = col + 1

        String part = testDataLevel1.getValue(colpilih, i)

        searchPart = ((searchPart + part) + ' ')
    }
    
    println(mapLevel1)

    println(searchPart)

    def fileRowIndex = mapLevel1[searchPart]

    println(fileRowIndex)

    def usageValue = testDataLevel1.getValue('Usage', i)

    CustomKeywords.'copyToExcel.exel3'(usageValue, fileRowIndex, 4, excelPath, 'Level1')
}

mapLevel2 = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndicesWithSheetList'(excelPath, columnLevel2, 4, 2)

for (int i = 1; i <= testDataLevel2.getRowNumbers(); i++) {
    def searchPart = ''

    for (int col : columnLevel2) {
        int colpilih = col + 1

        String part = testDataLevel2.getValue(colpilih, i)

        searchPart = ((searchPart + part) + ' ')
    }
    
    def fileRowIndex = mapLevel2[searchPart]

    println(fileRowIndex)

    def usageValue = testDataLevel2.getValue('Usage', i)

    CustomKeywords.'copyToExcel.exel3'(usageValue, fileRowIndex, 7, excelPath, 'Level2')
}

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/button__BOM_upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC027/0_create project page/li_Upload Bom Usage'), 
    excelPath)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.scrollToElement(findTestObject('Scenario 10/S10_TC027/0_create project page/div_PRODUCTION PLAN HISTORY LIST'), 0)

WebUI.waitForElementVisible(findTestObject('Scenario 10/S10_TC027/0_create project page/div_PRODUCTION PLAN HISTORY LIST'), 
    0)

bom_No = WebUI.getText(findTestObject('Scenario 10/S10_TC027/0_create project page/div_DT Bom-ver'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/button__plan history-download'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/li_download_plan history-Download Production Plan'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/input__Production plan-productionType'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/li_Production plan-productionType-MONTHLY'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/input__Production plan-usageHistoryId'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/li_Production plan-usageHistoryId-Bom-ver3', [('bom_No') : bom_No]))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/input__noOfLevel (1)'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/li_Production plan-noOfLevel-Level 2'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/input__Production plan-yearMonthFrom'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/div_Production plan-yearMonth-year', [('year') : '2023']))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/div_Production plan-yearMonth-month', [('month') : 'Jun']))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/input__Production plan-yearMonthTo'))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/div_Production plan-yearMonth-year', [('year') : '2023']))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/div_Production plan-yearMonth-month', [('month') : 'Nov']))

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/button_Production plan-Download'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC027/0_create project page/p_The operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/svg_close notification'))

WebUI.delay(3)

proplanpath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

int startrow4 = 9

for (int row = 1; row <= testDataProductPlan.getRowNumbers(); row++) {
    for (def pair : columnProductPlan) {
        def columnName = pair.key

        def columnIndex = pair.value

        def dataValue = testDataProductPlan.getValue(columnName, row)

        int rowinexcel = startrow4 + row

        CustomKeywords.'copyToExcel.exel2'(dataValue, rowinexcel, columnIndex, proplanpath, 'CSUGF130')
    }
}

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/button__plan history-upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 10/S10_TC027/0_create project page/li_Upload Production Plan'), 
    proplanpath)

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC027/0_create project page/p_The operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 10/S10_TC027/0_create project page/svg_close notification'))

productPlanBomNo = WebUI.getText(findTestObject('Scenario 10/S10_TC027/0_create project page/div_Dt_ProductPlan-BomNo'))

CustomKeywords.'copyToExcel.exel4'(productPlanBomNo, 1, 0, 'Excel Files/Scenario 10/S10_TestCases_Data.xlsx', 'TC027-AutoGen')

WebUI.closeBrowser()

