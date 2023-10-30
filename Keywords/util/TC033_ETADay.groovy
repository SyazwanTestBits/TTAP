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


public class TC033_ETADay {

	@Keyword
	public void handleETDETA(String transday, String etadays) {

		List<String> ListETDDay = new ArrayList()
		String[] daysArray = etadays.split(",");
		List<String> daysList = new ArrayList<>(Arrays.asList(daysArray));

		for (String ETDday : daysList) {

			WebUI.click(findTestObject('Scenario 13/S13_TC033_Shipping_Route/li_etdWeek_day', [('EtdWeekDay') : ETDday]))

			ListETDDay.add(ETDday)
		}

		// ADD Trasport Days
		WebUI.setText(findTestObject('Object Repository/Scenario 13/S13_TC033_Shipping_Route/input__transportDays'), transday)


		// VERIFY ETA Days
		List<String> listdays = ["SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"];
		List<String> newetaday = new ArrayList<>();
		int addDay =0;

		StringBuilder resultETADay = new StringBuilder();

		for (String dayetd : ListETDDay) {

			addDay = Integer.parseInt(etadays);


			while (addDay >= 7) {
				addDay = addDay - 7;
			}

			for (String listday : listdays) {

				//start if 1
				if (dayetd == listday) {

					int indexday = listdays.indexOf(listday)
					indexday = indexday + addDay

					if (indexday >= 7) {
						indexday = indexday -7
					}

					String nextetd = listdays.get(indexday)
					newetaday.add(nextetd)

				}

			}

		}

		for (String outputETA : newetaday) {
			WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC033_Shipping_Route/ETADAy', [('ETADAY') : outputETA]), 1)

		}

	}



}
