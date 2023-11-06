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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeeklyPeriod {


	@Keyword
	public static String getWeeklyDateRange(String date1String) {

		//String date1String = "Oct 10, 2023";

		// Define a DateTimeFormatter for parsing the input date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");

		// Parse the input date string into a LocalDate
		LocalDate date1 = LocalDate.parse(date1String, formatter);

		// Calculate the start of the week (Sunday) for the given date
		LocalDate startOfWeek = date1.with(DayOfWeek.MONDAY);

		// Calculate the end of the week (Saturday) for the given date
		LocalDate endOfWeek = startOfWeek.plusDays(6);

		// Create a string representation of the weekly date range
		String weeklyRange = startOfWeek.format(formatter) + " ~ " + endOfWeek.format(formatter);

		// Print the result
		System.out.println("date1 = " + date1.format(formatter));
		System.out.println("weekly1 = " + weeklyRange);

		return weeklyRange
	}
}
