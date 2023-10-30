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

import internal.GlobalVariable

public class getValuePerWeek {



	@Keyword
	def calculateWeeklyTotals(map) {
		// Initialize variables to store weekly values
		def currentWeek = -1
		def weeklyTotal = 0.0
		def weeklyTotals = [:]

		// Iterate through the map and calculate weekly totals
		map.each { dateStr, value ->
			// Parse the date
			def date = Date.parse("dd MMM yyyy", dateStr)

			// Get the week number of the year for the current date
			def calendar = Calendar.instance
			calendar.time = date
			def weekNumber = calendar.get(Calendar.WEEK_OF_YEAR)

			// Check if the week has changed
			if (weekNumber != currentWeek) {
				if (currentWeek != -1) {
					weeklyTotals["Week $currentWeek"] = weeklyTotal
				}
				currentWeek = weekNumber
				weeklyTotal = 0.0
			}

			// Add the value for the current date to the weekly total
			weeklyTotal += value
		}

		// Add the total for the last week
		if (currentWeek != -1) {
			weeklyTotals["Week $currentWeek"] = weeklyTotal
		}

		return weeklyTotals
	}
	
	@Keyword
	def lastValueWeeklyTotals(map) {
		// Initialize variables to store weekly values
		def currentWeek = -1
		def weeklyTotal = 0.0
		def weeklyTotals = [:]

		// Iterate through the map and calculate weekly totals
		map.each { dateStr, value ->
			// Parse the date
			def date = Date.parse("dd MMM yyyy", dateStr)

			// Get the week number of the year for the current date
			def calendar = Calendar.instance
			calendar.time = date
			def weekNumber = calendar.get(Calendar.WEEK_OF_YEAR)

			// Check if the week has changed
			if (weekNumber != currentWeek) {
				if (currentWeek != -1) {
					weeklyTotals["Week $currentWeek"] = weeklyTotal
				}
				currentWeek = weekNumber
				weeklyTotal = 0.0
			}

			
			weeklyTotal = value
		}

		// Add the total for the last week
		if (currentWeek != -1) {
			weeklyTotals["Week $currentWeek"] = weeklyTotal
		}

		return weeklyTotals
	}

}
