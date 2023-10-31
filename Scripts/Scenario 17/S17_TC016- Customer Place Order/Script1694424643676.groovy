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
import java.util.Locale as Locale

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_Place Order'))

WebUI.waitForElementPresent(findTestObject('Page_RegularOrder/h3_Place Regular Order'), 0)

WebUI.click(findTestObject('Scenario 17/S17_TC016/Page_Place Regular Order - Brivge/button_filter'))

WebUI.click(findTestObject('Scenario 17/S17_TC002/input__orderFrequency'))

WebUI.click(findTestObject('Scenario 17/S17_TC002/li_orderFrequency', [('orderFrequency') : orderFrequency]))

Locale englishLocale = new Locale('en', 'US')

def dateFormat = new SimpleDateFormat('MMM d, yyyy', englishLocale)

def currentDate = new Date()

String formattedDate = dateFormat.format(currentDate)

weeklyPeriod = CustomKeywords.'util.WeeklyPeriod.getWeeklyDateRange'(formattedDate)

println(weeklyPeriod)

WebUI.click(findTestObject('Scenario 17/S17_TC016/Page_Place Regular Order - Brivge/Page_Place Regular Order - Brivge (1)/input_orderPeriodIndex'))

WebUI.click(findTestObject('Scenario 17/S17_TC016/Page_Place Regular Order - Brivge/Page_Place Regular Order - Brivge/li_orderPeriodIndex', 
        [('orderPeriodIndex') : orderPeriodIndex]))

WebUI.click(findTestObject('remote filter/button_Search'))

WebUI.setText(findTestObject('Scenario 17/S17_TC006/input_Search'), contractRouteNo)

WebUI.verifyElementText(findTestObject('Page_RegularOrder/div_Dt_ContractRouteNo', [('contractRouteNo') : contractRouteNo]), 
    contractRouteNo)

WebUI.click(findTestObject('Page_RegularOrder/button_Dt_AddOrder', [('contractRouteNo') : contractRouteNo]))

WebUI.waitForElementPresent(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/h3_Place Order Detail(Regular)'), 
    0)

WebUI.click(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/button_Download'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadRegOrderFormCust_Success'), 0)

WebUI.delay(2)

downloadedExcel = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

partsNoRowIndices = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(downloadedExcel, 2, 17)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 17/S17_Cmn1-Write Info into Form Excel - mapDataIndices'), [('datafile') : datafile
        , ('fileColumns') : mapKeyandColIndex, ('mapDataIndices') : partsNoRowIndices, ('downloadedFormPath') : downloadedExcel
        , ('downloadedFormSheetname') : downloadedFormSheetName], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 17/S17_Cmn1-Write Info into Form Excel'), [('datafile') : testdataInboundDateChange
        , ('fileColumns') : fileColumns, ('startRowFormMinusOne') : startRowFormMinusOne, ('downloadedFormPath') : downloadedExcel
        , ('downloadedFormSheetname') : downloadedFormSheetName], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.uploadFile(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/input_UploadFile'), downloadedExcel)

WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadRegOrderFormCust_Success'), 0)

WebUI.click(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/button_Issue'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSureToDo_Issue'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_Issue_Success'), 0)

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

