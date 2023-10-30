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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : password, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S9_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Navbar_Brivge/OrderMenu_Brivge/li_Place Order'))

WebUI.waitForElementPresent(findTestObject('Page_RegularOrder/h3_Place Regular Order'), 0)

WebUI.verifyElementText(findTestObject('Page_RegularOrder/div_Dt_ContractRouteNo', [('contractRouteNo') : contractRouteNo]), 
    contractRouteNo)

WebUI.verifyElementText(findTestObject('Page_RegularOrder/div_Dt_Status', [('contractRouteNo') : contractRouteNo]), '')

WebUI.click(findTestObject('Page_RegularOrder/button_Dt_AddOrder', [('contractRouteNo') : contractRouteNo]))

WebUI.waitForElementPresent(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/h3_Place Order Detail(Regular)'), 
    0)

WebUI.click(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/button_Download'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_DwnloadRegOrderFormCust_Success'), 0)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'ExcelActions.writeIntoExcelPlaceOrderRegularS9_TC41'(latestFilePath, contractNo)

WebUI.uploadFile(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/input_UploadFile'), latestFilePath)

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_UploadRegOrderFormCust_Success'), 0)

WebUI.click(findTestObject('Page_RegularOrder/Page_PlaceOrderDetail_Reg/button_Issue'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSureToDo_Issue'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/div_NotiMsg_Issue_Success'), 0)

WebUI.verifyElementText(findTestObject('Page_RegularOrder/div_Dt_Status', [('contractRouteNo') : contractRouteNo]), 'Submitted')

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

