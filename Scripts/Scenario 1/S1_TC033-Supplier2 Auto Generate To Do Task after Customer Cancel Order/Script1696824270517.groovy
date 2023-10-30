import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_SUP2], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('HomePage_Brivge/button_ToDoList_More'))

WebUI.waitForElementPresent(findTestObject('Page_TodoList/h3_Todo List'), 0)

WebUI.setText(findTestObject('Scenario 1/S1_TC032/input_Text'), orderNo)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC032/div_Task'), 'You have pending order to confirm')

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC032/div_TaskCategory'), 'Order')

WebUI.verifyElementPresent(findTestObject('Scenario 1/S1_TC032/div_Description', [('orderNo') : orderNo]), 0)

KeywordUtil.logInfo("Customer Code: PK-CUS-POC and Order No: $orderNo")

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

