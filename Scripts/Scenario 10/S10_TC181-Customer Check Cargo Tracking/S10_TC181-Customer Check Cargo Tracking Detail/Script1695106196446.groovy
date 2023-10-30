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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.text.ParseException as ParseException
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date

//---------Start Testing--------------------------------
WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_CUST], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC064,065/button_Logistics'))

WebUI.click(findTestObject('Scenario 13/S13_TC064,065/li_Cargo Tracking Detail'))

WebUI.callTestCase(findTestCase('Scenario 10/S10_TC181-Customer Check Cargo Tracking/S10_TC181.1-Customer Cargo Tracking (B-NT-01-001)'), 
    [('milestone') : ['Booking Submitted', 'Booking Confirmed', 'Empty Container Release to Shipper', 'Cargo Outbound', 'Gate in (arrival at first POL)'
            , 'Container loaded at first POL', 'Vessel departure from first POL', 'Vessel arrival at T/S port', 'Container discharge at T/S port'
            , 'Container loaded at T/S port', 'Vessel departure from T/S', 'Vessel arrived at final POD (ATA)', 'Container unload at final POD'
            , 'Container Gate out from final POD', 'Cargo Inbound', 'Vessel arrived at final POD (ATA)', 'Container unload at final POD'
            , 'Container Gate out from final POD', 'Container Gate out from final POD'], ('milestoneREALTIME') : ['Empty container pick-up (Empty Container Release to Shipper)'
            , 'Gate in (Gate In to Outbound Terminal)', 'Loaded (Loaded on vessel at Port of Loading)', 'Departure (vessel Departure from Port of Loading)'
            , 'Arrival (vessel Arrival at Transhipment Port)', 'Unload (Unloaded from vessel at Transhipment Port)', 'Loaded (Loaded on vessel at Transhipment Port)'
            , 'Departure (Departure from Transhipment Port)', 'Arrival (vessel Arrival at Port of Discharging)', 'Unload (Unloaded from vessel at Port of Discharging)'
            , 'Gate out (Gate Out from Inbound Terminal for Delivery to Consignee (or Port Shuttle))', 'Empty container returned (Empty Container Returned from Customer)']
        , ('bookingNo') : findTestData('Scenario 10/S10_TC181-Customer Check Cargo Tracking').getValue('BookingNo', 1)], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 12/SC12_TC050/img'))

WebUI.click(findTestObject('Scenario 13/S13_TC064,065/button_Logistics'))

WebUI.click(findTestObject('Scenario 13/S13_TC064,065/li_Cargo Tracking Detail'))

WebUI.callTestCase(findTestCase('Scenario 10/S10_TC181-Customer Check Cargo Tracking/S10_TC181.2-Customer Cargo Tracking (TLLU6124978)'), 
    [('milestone') : ['Booking Submitted', 'Booking Confirmed', 'Empty Container Release to Shipper', 'Cargo Outbound', 'Gate in (arrival at first POL)'
            , 'Container loaded at first POL', 'Vessel departure from first POL', 'Vessel arrival at T/S port', 'Container discharge at T/S port'
            , 'Container loaded at T/S port', 'Vessel departure from T/S', 'Vessel arrived at final POD (ATA)', 'Container unload at final POD'
            , 'Container Gate out from final POD', 'Cargo Inbound', 'Vessel arrived at final POD (ATA)', 'Container unload at final POD'
            , 'Container Gate out from final POD', 'Container Gate out from final POD'], ('milestoneREALTIME') : ['Empty container pick-up (Empty Container Release to Shipper)'
            , 'Gate in (Gate In to Outbound Terminal)', 'Loaded (Loaded on vessel at Port of Loading)', 'Departure (vessel Departure from Port of Loading)'
            , 'Arrival (vessel Arrival at Transhipment Port)', 'Unload (Unloaded from vessel at Transhipment Port)', 'Loaded (Loaded on vessel at Transhipment Port)'
            , 'Departure (Departure from Transhipment Port)', 'Arrival (vessel Arrival at Port of Discharging)', 'Unload (Unloaded from vessel at Port of Discharging)'
            , 'Gate out (Gate Out from Inbound Terminal for Delivery to Consignee (or Port Shuttle))', 'Empty container returned (Empty Container Returned from Customer)']
        , ('containerNo') : findTestData('Scenario 10/S10_TC181-Customer Check Cargo Tracking').getValue('ContainerNo', 
            3)], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 12/SC12_TC050/img'))

WebUI.click(findTestObject('Scenario 13/S13_TC064,065/button_Logistics'))

WebUI.click(findTestObject('Scenario 13/S13_TC064,065/li_Cargo Tracking Detail'))

WebUI.callTestCase(findTestCase('Scenario 10/S10_TC181-Customer Check Cargo Tracking/S10_TC181.3-Customer Cargo Tracking (C-20130614001)'), 
    [('milestone') : ['Booking Submitted', 'Booking Confirmed', 'Empty Container Release to Shipper', 'Cargo Outbound', 'Gate in (arrival at first POL)'
            , 'Container loaded at first POL', 'Vessel departure from first POL', 'Vessel arrival at T/S port', 'Container discharge at T/S port'
            , 'Container loaded at T/S port', 'Vessel departure from T/S', 'Vessel arrived at final POD (ATA)', 'Container unload at final POD'
            , 'Container Gate out from final POD', 'Cargo Inbound', 'Vessel arrived at final POD (ATA)', 'Container unload at final POD'
            , 'Container Gate out from final POD', 'Container Gate out from final POD'], ('milestoneREALTIME') : ['Empty container pick-up (Empty Container Release to Shipper)'
            , 'Gate in (Gate In to Outbound Terminal)', 'Loaded (Loaded on vessel at Port of Loading)', 'Departure (vessel Departure from Port of Loading)'
            , 'Arrival (vessel Arrival at Transhipment Port)', 'Unload (Unloaded from vessel at Transhipment Port)', 'Loaded (Loaded on vessel at Transhipment Port)'
            , 'Departure (Departure from Transhipment Port)', 'Arrival (vessel Arrival at Port of Discharging)', 'Unload (Unloaded from vessel at Port of Discharging)'
            , 'Gate out (Gate Out from Inbound Terminal for Delivery to Consignee (or Port Shuttle))', 'Empty container returned (Empty Container Returned from Customer)']
        , ('containerNo') : findTestData('Scenario 10/S10_TC181-Customer Check Cargo Tracking').getValue('ContainerNo', 
            4)], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

WebUI.closeBrowser()

