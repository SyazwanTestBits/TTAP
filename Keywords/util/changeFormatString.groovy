package util

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar
import java.time.LocalTime

import internal.GlobalVariable

public class changeFormatString {

	@Keyword
	def changeDateFormat (String inputDate) {

		String outputFormat = "MMM dd, yyyy"

		SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd MMM yyyy")
		SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat)

		Date date = inputDateFormat.parse(inputDate)
		String formattedDate = outputDateFormat.format(date)

		return formattedDate
	}

	@Keyword
	def changeDateFormat2 (String inputDate) {

		String outputFormat = "d/M/yyyy"

		SimpleDateFormat inputDateFormat = new SimpleDateFormat("MMM d, yyyy")
		SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat)

		Date date = inputDateFormat.parse(inputDate)
		String formattedDate = outputDateFormat.format(date)

		return formattedDate
	}
}
