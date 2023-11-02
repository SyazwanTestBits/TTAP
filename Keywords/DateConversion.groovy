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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import internal.GlobalVariable

public class DateConversion {

	@Keyword
	public static String convertChineseToEnglishPeriodRange(String chineseDateRange) {
		try {
			String[] dateParts = chineseDateRange.split(" ~ ");

			SimpleDateFormat chineseFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.CHINA);
			SimpleDateFormat englishFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);

			Date startDate = chineseFormat.parse(dateParts[0]);
			Date endDate = chineseFormat.parse(dateParts[1]);

			String englishStartDate = englishFormat.format(startDate);
			String englishEndDate = englishFormat.format(endDate);

			return englishStartDate + " ~ " + englishEndDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String convertChineseToEnglishDate(String chineseDate) {
		try {

			SimpleDateFormat chineseFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.CHINA);
			SimpleDateFormat englishFormat = new SimpleDateFormat("dd MMM yyyy", Locale.US);


			Date date = chineseFormat.parse(chineseDate);

			return englishFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
