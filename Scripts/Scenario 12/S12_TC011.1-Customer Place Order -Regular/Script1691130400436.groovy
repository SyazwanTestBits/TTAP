import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

WebUI.click(findTestObject('Page_RegularOrder/button_remote filter'))

WebUI.click(findTestObject('Page_RegularOrder/remotefilter/input_orderFrequency'))

WebUI.click(findTestObject('Page_RegularOrder/remotefilter/li__orderFrequency_Weekly'))

//def dateFormat = new SimpleDateFormat('MMM d, yyyy')
//def currentDate = new Date()
//String formattedDate = dateFormat.format(currentDate)
//weeklyPeriod = CustomKeywords.'util.WeeklyPeriod.getWeeklyDateRange'(formattedDate)
println("$weeklyPeriod")

def convertedWeeklyPeriod = CustomKeywords.'DateConversion.convertChineseToEnglishPeriodRange'("$weeklyPeriod")

println(convertedWeeklyPeriod)

WebUI.click(findTestObject('Page_RegularOrder/remotefilter/input_orderPeriod'))

WebUI.click(findTestObject('Page_RegularOrder/remotefilter/li_orderPeriod', [('WeeklyPeriod') : convertedWeeklyPeriod]))

WebUI.click(findTestObject('Page_RegularOrder/remotefilter/button_remotefilter_Search'))

WebUI.setText(findTestObject('Scenario 12/SC12_TC011/Page_Place Regular Order - Brivge/input_Search'), contractNo)

WebUI.verifyElementText(findTestObject('Page_RegularOrder/div_Dt_ContractRouteNo', [('contractRouteNo') : contractRouteNo]), 
    contractRouteNo)

WebUI.verifyElementText(findTestObject('Page_RegularOrder/div_Dt_Status', [('contractRouteNo') : contractRouteNo]), '')

WebUI.click(findTestObject('Page_RegularOrder/button_Dt_AddOrder', [('contractRouteNo') : contractRouteNo]))

WebUI.waitForElementPresent(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/h3_Place Order Detail(Regular)'), 
    0)

WebUI.click(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/button_Download'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadRegOrderFormCust_Success'), 0)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'ExcelActions.writeIntoExcelPlaceOrderRegularSC12'(latestFilePath, contractNo)

WebUI.uploadFile(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/input_UploadFile'), latestFilePath)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadRegOrderFormCust_Success'), 0)

WebUI.click(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/button_Issue'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSureToDo_Issue'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_Issue_Success'), 0)

WebUI.verifyElementText(findTestObject('Page_RegularOrder/div_Dt_Status', [('contractRouteNo') : contractRouteNo]), 'Submitted')

WebUI.takeFullPageScreenshot()

