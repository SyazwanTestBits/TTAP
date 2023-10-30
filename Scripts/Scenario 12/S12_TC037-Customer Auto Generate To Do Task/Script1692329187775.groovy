import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.BAF_COMPANY_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('HomePage_Brivge/button_ToDoList_More'))

WebUI.waitForElementPresent(findTestObject('Page_TodoList/h3_Todo List'), 0)

WebUI.setText(findTestObject('Scenario 1/S1_TC032/input_Text'), requestNo)

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC032/div_Task'), 'You have pending change or cancel request to approve')

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC032/div_TaskCategory'), 'Order')

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC037/div_Description', [('requestNo') : requestNo]), 0)

KeywordUtil.logInfo("Customer Code: and Request No: $requestNo")


WebUI.closeBrowser()

