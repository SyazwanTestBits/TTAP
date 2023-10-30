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

public class GetCurrentTime {


	@Keyword
	def currentTimeNext_5Minute () {
		String inputTime = LocalTime.now();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		Date date = sdf.parse(inputTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int minutes = calendar.get(Calendar.MINUTE);

		int difference = 5 - (minutes % 5);
		calendar.add(Calendar.MINUTE, difference);
		//if (minutes % 5 != 0) {}

		Date roundedTime = calendar.getTime();
		String newround = sdf.format(roundedTime)

		return newround
	}


	@Keyword
	def currentTimeNext_5MinuteWithInput (String timenow) {
		//String inputTime = LocalTime.now();
		String inputTime = timenow
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		Date date = sdf.parse(inputTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int minutes = calendar.get(Calendar.MINUTE);

		int difference = 5 - (minutes % 5);
		calendar.add(Calendar.MINUTE, difference);



		Date roundedTime = calendar.getTime();
		String newround = sdf.format(roundedTime)

		return newround

	}

	@Keyword
	def parseHourAndMinute(String clock) {
		def (hour,minute) = clock.split(':')
		def newhour = hour.toInteger()
		def newminute = minute.toInteger()
		return [newhour, newminute]
	}
}
