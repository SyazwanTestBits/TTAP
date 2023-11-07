import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S17_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Scenario 17/S17_TC057/Page_Home Page - Brivge/li_ChangeCancel Request List'))

'Verify Order Change with Status = New\r\n'
WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC034/p_Order Change', [('requestNo') : requestNo]), 'New')

WebUI.waitForElementPresent(findTestObject('Scenario 17/S17_TC057/Page_Home Page - Brivge/h3_ChangeCancel Request List'), 
    0)

WebUI.click(findTestObject('Scenario 12/SC12_TC019/p_detailButton', [('requestNo') : requestNo]))

WebUI.click(findTestObject('Scenario 10/S10_TC088/button_Propose New'))

WebDriver driver = DriverFactory.getWebDriver()

//Find dates to change in the table 
List<WebElement> elements = WebUI.findWebElements(findTestObject('Scenario 17/S17_TC057/p_getDates'), 30)

for (WebElement element : elements) {
    println(element.text)

    // Simulate pressing the "Tab" key
    driver.switchTo().activeElement().sendKeys(Keys.TAB)

    if (element.text == "$date") {
        break
    }
}

'Edit the date to change'
CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 17/S17_TC057/p_editDate', [('date') : date]), 
    0)

WebUI.click(findTestObject('Scenario 17/S17_TC057/input_newSuppOutboundPlanDate'))

//Find day elements 
List<WebElement> elementsDates_Calendar = WebUI.findWebElements(findTestObject('Scenario 17/S17_TC057/p_dayElements'), 30)

'Choose a new date'
for (WebElement element : elementsDates_Calendar) {
    println(element.text)

    //Choose a new day
    if (element.text == "$day") {
        element.click()

        break
    }
}

WebUI.delay(1)

WebUI.click(findTestObject('Scenario 17/S17_TC057/p_Confirm'))

WebUI.setText(findTestObject('Scenario 10/S10_TC088/textarea_Input Reject Reason_lcbm-lcbm2348'), 'Test Propose New')

WebUI.click(findTestObject('Scenario 17/S17_TC057/Page_ChangeCancel Request Detail - Brivge/button_Confirm_ProposeNew'))

WebUI.click(findTestObject('Scenario 17/S17_TC064/button_Confirm'))

WebUI.verifyElementPresent(findTestObject('Scenario 10/S10_TC088/div_Propose New Confirm.The operation was successful'), 
    0)

WebUI.waitForElementPresent(findTestObject('Scenario 10/S10_TC088/h3_ChangeCancel Request Detail'), 0)

WebUI.click(findTestObject('Scenario 10/S10_TC088/p_ChangeCancel Request List'))

WebUI.verifyElementText(findTestObject('Scenario 10/S10_TC088/p_verifyStatus', [('requestNo') : requestNo]), 'Propose New')

//
WebUI.closeBrowser()

