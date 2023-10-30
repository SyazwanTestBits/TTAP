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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import internal.GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

public class handlePicker2 {


	public findIndex(String[] arr, String t) {

		// if array is Null
		if (arr == null) {
			return -1;
		}

		// find length of array
		int len = arr.length;
		int i = 0;

		// traverse in the array
		while (i < len) {

			// if the i-th element is t
			// then return the index
			if (arr[i] == t) {
				i++;
				return i;
			}
			else {
				i = i + 1;
			}
		}
		return -1;
	}


	@Keyword
	public void handleCalendar(TestObject currentMonthYear, TestObject nextMonthButton, TestObject preMonthButton, int inputDay, int inputMonth, int inputYear) {

		WebElement next = WebUiBuiltInKeywords.findWebElement(nextMonthButton);
		WebElement pre = WebUiBuiltInKeywords.findWebElement(preMonthButton);
		WebElement getCurrentMonthYear = WebUiBuiltInKeywords.findWebElement(currentMonthYear);


		int inputMonthIndex = inputMonth - 1;
		// in List, the position of first value is 0. For example list of monthInYear, first value is January and its position is 0, not 1

		String[] monthInYear = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October" , "November", "December"];

		// get current year
		String currenttext = getCurrentMonthYear.getText();
		currenttext = getCurrentMonthYear.getText();
		currenttext = currenttext.replaceAll("\\s","");
		String currenttextyear = currenttext.replaceAll("[a-zA-Z]", "");
		int currentyear = Integer.parseInt(currenttextyear);

		// MAIN LOOPING: continue looping until function BREAK
		while(true) {

			// get month and year from header text for example we want to extract the MONTH and YEAR from "November 2022"
			String text = getCurrentMonthYear.getText();
			text = getCurrentMonthYear.getText();
			text = text.replaceAll("\\s","");
			String textday = text.replaceAll("\\d","");
			String textyear = text.replaceAll("[a-zA-Z]", "");
			int headeryear = Integer.parseInt(textyear);

			// find header month index from monthInYear[]
			int headermonth = findIndex(monthInYear,textday);

			// MAIN IF ELSE: If header month n year is not equal to input month and year, click next or previous. Else, BREAK
			if ( headermonth != inputMonth || headeryear != inputYear) {

				// if input year is greater than current year, click next
				if (inputYear > currentyear) {

					next.click(); //click next month button
					Thread.sleep(500); //slowing the execution
				}

				// else if input year is equal to current year AND header month more or equal input month, click next.
				else if (inputYear == currentyear && inputMonth > headermonth) {
					next.click(); //click next month button
					Thread.sleep(500); //slowing the execution

				}

				else {
					pre.click(); //click previous month button
					Thread.sleep(500); //slowing the execution
				}
			}

			else {
				break; //stop clicking when we got the desired month
			}
		}

		WebDriver driver = DriverFactory.getWebDriver();
		WebElement day = driver.findElement(By.xpath("//button[@type = 'button' and @tabindex='0' and (text() = '" + inputDay+ "' or . = '" + inputDay + "')]"));
		//WebElement day = driver.findElement(By.xpath("//*[@class = 'lcbm-MuiButtonBase-root lcbm-MuiIconButton-root lcbm-MuiPickersDay-day' and @type = 'button' and (text() = '" + inputDay + "' or . = '"+inputDay+ "')]"));

		day.click();
	}






	@Keyword
	public void handleCalendar_CannotPrevious(TestObject currentMonthYear, int inputDay, int inputMonth, int inputYear) {

		WebElement next = WebUiBuiltInKeywords.findWebElement(findTestObject('Scenario 13/S13_TC033_Shipping_Route/button previous calendar'));
		WebElement getCurrentMonthYear = WebUiBuiltInKeywords.findWebElement(currentMonthYear);


		int inputMonthIndex = inputMonth - 1;
		// in List, the position of first value is 0. For example list of monthInYear, first value is January and its position is 0, not 1

		String[] monthInYear = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October" , "November", "December"];

		// get current year
		String currenttext = getCurrentMonthYear.getText();
		String currenttextyear = currenttext.replaceAll("\\s","");
		currenttextyear = currenttextyear.replaceAll("[a-zA-Z]", "");
		int currentyear = Integer.parseInt(currenttextyear);

		//get current month
		String currentmonth = currenttext.replaceAll("\\s","");
		currentmonth = currentmonth.replaceAll("\\d","");
		int newcurrentmonth = findIndex(monthInYear,currentmonth);



		// MAIN LOOPING: continue looping until function BREAK
		while(true) {


			String text = getCurrentMonthYear.getText();
			text = getCurrentMonthYear.getText();
			text = text.replaceAll("\\s","");
			String textday = text.replaceAll("\\d","");
			String textyear = text.replaceAll("[a-zA-Z]", "");
			int headeryear = Integer.parseInt(textyear);

			// find header month index from monthInYear[]
			int headermonth = findIndex(monthInYear,textday);

			// MAIN IF ELSE: If header month n year is not equal to input month and year, click next or previous. Else, BREAK
			if ( headermonth != inputMonth || headeryear != inputYear) {


				if (headermonth == newcurrentmonth && headeryear == currentyear) {
					next.click();
					Thread.sleep(500);
				}

				// if input year is greater than current year, click next
				else if (inputYear > currentyear) {

					WebElement next2 = WebUiBuiltInKeywords.findWebElement(findTestObject('Scenario 13/S13_TC033_Shipping_Route/button next calendar'));
					next2.click(); //click next month button
					Thread.sleep(500); //slowing the execution
				}

				// else if input year is equal to current year AND header month more or equal input month, click next.
				else if (inputYear == currentyear && inputMonth > headermonth) {
					WebElement next2 = WebUiBuiltInKeywords.findWebElement(findTestObject('Scenario 13/S13_TC033_Shipping_Route/button next calendar'));
					next2.click(); //click next month button
					Thread.sleep(500); //slowing the execution

				}
			}

			else {
				break; //stop clicking when we got the desired month
			}
		}

		WebDriver driver = DriverFactory.getWebDriver();
		WebElement day = driver.findElement(By.xpath("//button[@type = 'button' and @tabindex='0' and (text() = '" + inputDay+ "' or . = '" + inputDay + "')]"));
		//WebElement day = driver.findElement(By.xpath("//*[@class = 'lcbm-MuiButtonBase-root lcbm-MuiIconButton-root lcbm-MuiPickersDay-day' and @type = 'button' and (text() = '" + inputDay + "' or . = '"+inputDay+ "')]"));

		day.click();
	}
}
