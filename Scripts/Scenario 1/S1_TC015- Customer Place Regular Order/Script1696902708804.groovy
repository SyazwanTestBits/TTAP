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
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_Place Order'))

WebUI.waitForElementPresent(findTestObject('Page_RegularOrder/h3_Place Regular Order'), 0)

WebUI.click(findTestObject('Page_RegularOrder/button_remote filter'))

WebUI.click(findTestObject('Page_RegularOrder/remotefilter/input_orderFrequency'))

WebUI.click(findTestObject('Page_RegularOrder/remotefilter/li__orderFrequency_Weekly'))

println("$weeklyPeriod")

def convertedWeeklyPeriod = CustomKeywords.'DateConversion.convertChineseToEnglishPeriodRange'("$weeklyPeriod")

WebUI.click(findTestObject('Page_RegularOrder/remotefilter/input_orderPeriod'))

WebUI.click(findTestObject('Page_RegularOrder/remotefilter/li_orderPeriod', [('WeeklyPeriod') : convertedWeeklyPeriod]))

WebUI.click(findTestObject('Page_RegularOrder/remotefilter/button_remotefilter_Search'))

WebUI.verifyElementText(findTestObject('Page_RegularOrder/div_Dt_ContractRouteNo', [('contractRouteNo') : contractRouteNo]), 
    contractRouteNo)

WebUI.verifyElementText(findTestObject('Page_RegularOrder/div_Dt_Status - Copy', [('contractRouteNo') : contractRouteNo]), 
    '')

WebUI.click(findTestObject('Page_RegularOrder/button_Dt_AddOrder', [('contractRouteNo') : contractRouteNo]))

WebUI.waitForElementPresent(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/h3_Place Order Detail(Regular)'), 
    0)

WebUI.click(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/button_Download'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadRegOrderFormCust_Success'), 0)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

partIndex = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(latestFilePath, 2, 17)

println(partIndex)

for (int row = 1; row <= testData.getRowNumbers(); row++) {
    def datafilePartNo = testData.getValue('PartNo', row)

    def fileRowIndex = partIndex[datafilePartNo]

    println(fileRowIndex)

    for (def pair : columnName) {
        def columnName = pair.key

        def columnIndex = pair.value

        def dataString = testData.getValue(columnName, row)

        CustomKeywords.'copyToExcel.exel3'(dataString, fileRowIndex, columnIndex, latestFilePath, contractRouteNo)
    }
}

for (int row1 = 1; row1 <= testDataDate.getRowNumbers(); row1++) {
    def dataString = testDataDate.getValue('Date', row1)

    CustomKeywords.'copyToExcel.exel3'(dataString, 16, 23 + row1, latestFilePath, contractRouteNo)
}

WebUI.uploadFile(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/input_UploadFile'), latestFilePath)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadRegOrderFormCust_Success'), 0)

WebUI.click(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/button_Issue'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSureToDo_Issue'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_Issue_Success'), 0)

WebUI.waitForElementPresent(findTestObject('Page_RegularOrder/h3_Place Regular Order'), 0)

WebUI.click(findTestObject('Page_RegularOrder/button_remote filter'))

WebUI.click(findTestObject('Page_RegularOrder/remotefilter/input_orderFrequency'))

WebUI.click(findTestObject('Page_RegularOrder/remotefilter/li__orderFrequency_Weekly'))

WebUI.click(findTestObject('Page_RegularOrder/remotefilter/input_orderPeriod'))

WebUI.click(findTestObject('Page_RegularOrder/remotefilter/li_orderPeriod', [('WeeklyPeriod') : weeklyPeriod]))

WebUI.click(findTestObject('Page_RegularOrder/remotefilter/button_remotefilter_Search'))

WebUI.verifyElementText(findTestObject('Page_RegularOrder/div_Dt_Status - Copy', [('contractRouteNo') : contractRouteNo]), 
    'Submitted')

WebUI.closeBrowser()

