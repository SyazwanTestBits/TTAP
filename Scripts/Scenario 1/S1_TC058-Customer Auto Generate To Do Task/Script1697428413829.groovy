import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('HomePage_Brivge/button_ToDoList_More'))

WebUI.waitForElementPresent(findTestObject('Page_TodoList/h3_Todo List'), 0)

WebUI.setText(findTestObject('Scenario 1/S1_TC032/input_Text'), (Supplier1_RequestNo + ' ') + Supplier2_RequestNo)

WebUI.click(findTestObject('Scenario 12/SC12_TC068/button_AND'))

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC058/div_Task', [('row') : 1]), 'You have pending change or cancel request to approve')

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC058/div_TaskCategory', [('row') : 1]), 'Order')

WebUI.verifyElementPresent(findTestObject('Scenario 1/S1_TC058/div_Description', [('row') : 1, ('orderNo') : Supplier1_RequestNo]), 
    0)

KeywordUtil.logInfo("Customer Code:PK-CUS-POC and Request No: $Supplier1_RequestNo")

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC058/div_Task', [('row') : 2]), 'You have pending change or cancel request to approve')

WebUI.verifyElementText(findTestObject('Scenario 1/S1_TC058/div_TaskCategory', [('row') : 2]), 'Order')

WebUI.verifyElementPresent(findTestObject('Scenario 1/S1_TC058/div_Description', [('row') : 1, ('orderNo') : Supplier2_RequestNo]), 
    0)

KeywordUtil.logInfo("Customer Code:PK-CUS-POC and Request No: $Supplier2_RequestNo")

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

