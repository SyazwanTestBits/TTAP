import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_SYAZWAN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/button_Master'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/li_Shipping Route List'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/button_add_shipping'))

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/input__shippingRouteCode'), ShippingRouteCode)

WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/input__displayShippingRoute'), DisplayShippingRoute)

'Click on List of ActiveFlag'
WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/button__activeFlag'))

'Choose type ActiveFlag in List'
WebUI.click(findTestObject('Scenario 13/S13_TC033_Shipping_Route/li_activeFlag', [('ActiveFlag') : ActiveFlag]))

'Click on List of Shipping Mode'
WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/button__shippingMode'))

'Choose type shipping mode in List'
WebUI.click(findTestObject('Scenario 13/S13_TC033_Shipping_Route/li_shippingMode', [('ShippingMode') : ShippingMode]))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/button__fromRegion'))

WebUI.click(findTestObject('Scenario 13/S13_TC033_Shipping_Route/li_fromRegion', [('FromRegion') : FromRegion]))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/button__toRegion'))

WebUI.click(findTestObject('Scenario 13/S13_TC033_Shipping_Route/li_toRegion', [('ToRegion') : ToRegion]))

'If have value in From State list'
if (FromState != '') {
    WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/button__fromState'))

    WebUI.click(findTestObject('Scenario 13/S13_TC033_Shipping_Route/li_fromState', [('FromState') : FromState]))
}

'If have value in To State list'
println(('value of toState is:' + ToState) + '1')

if (ToState != '') {
    WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/button__toState'))

    WebUI.click(findTestObject('Scenario 13/S13_TC033_Shipping_Route/li_toState', [('ToState') : ToState]))
}

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/button__shipperUid'))

WebUI.click(findTestObject('Scenario 13/S13_TC033_Shipping_Route/li_shipperUid', [('ShipperUid') : ShipperUid]))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/button__receiverUid'))

WebUI.click(findTestObject('Scenario 13/S13_TC033_Shipping_Route/li_receiverUid', [('ReceiverUid') : ReceiverUid]))

'If shippier region is not same with receiver region '
if (FromRegion != ToRegion) {
    'If have value in From Port list'
    if (FromPortId != 'no') {
        WebUI.click(findTestObject('Scenario 13/S13_TC033_Shipping_Route/button__fromPortId'))

        WebUI.click(findTestObject('Scenario 13/S13_TC033_Shipping_Route/li_fromPortId', [('FromPortId') : FromPortId]))
    }
    
    'If have value in To Port list'
    if (ToPortId != 'no') {
        WebUI.click(findTestObject('Scenario 13/S13_TC033_Shipping_Route/button__toPortId'))

        WebUI.click(findTestObject('Scenario 13/S13_TC033_Shipping_Route/li_toPortId', [('ToPortId') : ToPortId]))
    }
    
    WebUI.setText(findTestObject('Scenario 13/S13_TC033_Shipping_Route/input__OriginCcLeadtime'), OriginLeadTime)

    WebUI.setText(findTestObject('Scenario 13/S13_TC033_Shipping_Route/input__DestinationLeadtime'), DestinationLeadTime)
}

'Scroll to element Add Shipping Information button'
WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC033_Shipping_Route/svg_shippingInformation'), 0)

'Click add Shipping Information'
WebUI.click(findTestObject('Scenario 13/S13_TC033_Shipping_Route/svg_shippingInformation'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/button_shippInformation_etdWeek'))

WebUI.callTestCase(findTestCase('Scenario 1/S1_TC001.1-BU Create Shipping Route/S1_TC001.1-Create Shipping Route ETD TransportDay ETA'), 
    [('TransportDays') : TransportDays, ('EtdWeekDay') : EtdWeekDay], FailureHandling.STOP_ON_FAILURE)

//For ETD day looping
WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/input__cyCloseDays'), CloseDays)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/button__firstEtd'))

int firstdayetd = Integer.parseInt(DayFirstETD)

int firstmonthetd = Integer.parseInt(MonthFirstETD)

int firstyearetd = Integer.parseInt(YearFirstETD)

CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Scenario 13/S13_TC033_Shipping_Route/div_calendar header'), 
    findTestObject('Scenario 13/S13_TC033_Shipping_Route/button next calendar'), findTestObject('Scenario 13/S13_TC033_Shipping_Route/button previous calendar'), 
    firstdayetd, firstmonthetd, firstyearetd)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/button__lastEtd'))

int lastdayetd = Integer.parseInt(DayLastETD)

int lastmonthetd = Integer.parseInt(MonthLastETD)

int lastyearetd = Integer.parseInt(YearLastETD)

CustomKeywords.'util.handlePicker2.handleCalendar'(findTestObject('Scenario 13/S13_TC033_Shipping_Route/div_calendar header'), 
    findTestObject('Scenario 13/S13_TC033_Shipping_Route/button next calendar'), findTestObject('Scenario 13/S13_TC033_Shipping_Route/button previous calendar'), 
    lastdayetd, lastmonthetd, lastyearetd)

WebUI.callTestCase(findTestCase('Scenario 1/S1_TC001.1-BU Create Shipping Route/S1_TC001.1-Create Shipping Route SF Week'), 
    [('ShippingFrequency') : ShippingFrequency], FailureHandling.STOP_ON_FAILURE)

//For Shipping Frelooping
WebUI.click(findTestObject('Scenario 13/S13_TC033_Shipping_Route/span_Confirm'))

WebUI.delay(1)

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/button_Save'), 
    1)

'The operation was successful.'
WebUI.verifyElementText(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/p_The operation was successful'), 
    'The operation was successful.')

WebUI.closeBrowser()

