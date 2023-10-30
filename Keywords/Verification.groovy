import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testdata.ExcelData
import com.kms.katalon.core.testdata.TestDataFactory
import java.text.SimpleDateFormat
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class Verification {

	@Keyword(keywordObject='Verify Processes')
	def verifyDisplayUploadedGlobalPartsData() {
		// Retrieve test data
		def testData = findTestData('Data Files/Scenario 13/S13_TC030-Create Global Parts')

		// Get test data row numbers
		def testDataRowNumbers = testData.getRowNumbers()

		// Find the 'Next Page' button object
		def goToNextPageObj = findTestObject('Page_GlobalPartsList/button_Dt_GlobalPartsList_NextPage')

		def isNextPage = false

		// Initialize a list to track parts numbers not found
		List<String> partsNoNotFound = []

		// Loop through each row of the test data
		for (int rowNum = 8; rowNum <= testDataRowNumbers; rowNum++) {
			// Get the parts number from the test data
			def partsNo = testData.getValue(3, rowNum)

			// Find the parts number input object
			def partsNoObj = findTestObject('Page_GlobalPartsList/Page_CreateModifyGlobalParts/input_Dt_PartsNo', [('partsNo') : partsNo])

			// Check if the parts number is present
			if (WebUI.verifyElementPresent(partsNoObj, 3, FailureHandling.OPTIONAL)) {
				// Take full page screenshot at the end of the page
				if (rowNum == testDataRowNumbers || rowNum == 8 || isNextPage) {
					isNextPage = false
					WebUI.scrollToElement(partsNoObj, 3)
					WebUI.takeFullPageScreenshot([findTestObject('Object Repository/Navbar_Brivge/div_Navbar_WholeMenu')])
					// Log info when parts number is found
					KeywordUtil.logInfo("Parts No: $partsNo found in Global Parts List")
				} else {
					// Log info when parts number is found
					KeywordUtil.logInfo("Parts No: $partsNo found in Global Parts List")
				}
			} else {
				// Check if the 'Next Page' button is clickable
				if (WebUI.verifyElementClickable(goToNextPageObj, FailureHandling.OPTIONAL)) {
					WebUI.scrollToElement(goToNextPageObj, 3)
					// Take full page screenshot before moving to the next page
					WebUI.takeFullPageScreenshot([findTestObject('Object Repository/Navbar_Brivge/div_Navbar_WholeMenu')])
					WebUI.click(goToNextPageObj)

					// Verify if the parts number is found on the next page
					if (WebUI.verifyElementPresent(partsNoObj, 3, FailureHandling.OPTIONAL)) {
						KeywordUtil.logInfo("Parts No: $partsNo found in Global Parts List")
					}

					isNextPage = true
				} else {
					// Add parts number to the list if not found and no next page available
					partsNoNotFound.add(partsNo)
				}
			}
		}

		// Check if any parts numbers were not found
		if (!partsNoNotFound.isEmpty()) {
			def errorMessage = "Parts numbers not found in the Global Parts List: $partsNoNotFound"
			// Mark the test step as failed and throw an exception to fail the test case
			KeywordUtil.markFailed(errorMessage)
			throw new AssertionError(errorMessage)
		}
	}

	@Keyword(keywordObject='Verify Processes')
	def verifySubmittedGlobalParts() {
		// Retrieve test data from the specified file
		def testData = findTestData('Data Files/Scenario 13/S13_TC030-Create Global Parts')

		// Get the row numbers in the test data
		def testDataRowNumbers = testData.getRowNumbers()

		// Find the input search object on the page
		def inputSearchObj = findTestObject('Object Repository/Page_GlobalPartsList/input_Search_Dt_GlobalPartsList')

		// Iterate through the rows of test data starting from row 8
		for(def rowNum = 8; rowNum <= testDataRowNumbers ; rowNum++) {
			// Get the parts number value from the test data
			def partsNo = testData.getValue(3, rowNum)

			// Find the parts number object on the page using the partsNo value
			def partsNoObj = findTestObject('Object Repository/Page_GlobalPartsList/div_Dt_GlobalPartsList_PartsNo', [('partsNo') : partsNo])

			// Set the text of the input search object to the partsNo value
			WebUI.setText(inputSearchObj, partsNo)

			// Wait for the partsNoObj to be present on the page with a timeout of 0 seconds
			WebUI.waitForElementPresent(partsNoObj, 0)

			// Verify that the partsNoObj is present on the page with a timeout of 0 seconds
			WebUI.verifyElementPresent(partsNoObj, 0)

			// Log an informational message indicating that the partsNo is in the Global Parts List
			KeywordUtil.logInfo('Parts No: ' + partsNo + ' is in the Global Parts List')

			// Clear the text of the input search object
			CustomKeywords.'commonUtils.clearElementText'(inputSearchObj)
		}
	}

	@Keyword(keywordObject='Verify Processes')
	def verifyPartsMonitoringDetailStatus(TestData testdata, String status) {

		// Iterate over each row in the testdata
		for(def rowNum=1; rowNum<=testdata.getRowNumbers(); rowNum++) {

			// Get the PartsNo value from the testdata
			def partsNo = testdata.getValue('PartsNo', rowNum)

			// Find the TestObject for the partMonitorDetail_Status
			def partMonitorDetail_StatusObj = findTestObject('Object Repository/Page_SO_MonitoringDetail/div_PartsMonitorDetail_Status', [('partsNo') : partsNo])

			// Verify the element's text matches the provided status
			WebUI.verifyElementText(partMonitorDetail_StatusObj, status, FailureHandling.STOP_ON_FAILURE)

			// Add an informational comment for each iteration
			KeywordUtil.logInfo("Verified Parts Monitoring Detail Status ' $status ' for PartsNo: $partsNo")

		}
	}
	@Keyword
	def verifyDynamicExcelCellValue(String excelFilePath, String sheetName, String startDate, String endDate, String expectedValue) {
		try {
			// Specify the Excel file path
			def excelData = TestDataFactory.findTestData("YourTestDataSheetName")
			excelData.setExcelFile(excelFilePath, sheetName)

			// Find the header row to locate the date columns
			def headerRow = excelData.getAllData().get(0)

			// Find the column numbers for the start and end dates
			int startColumn = -1
			int endColumn = -1

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy")

			for (int colNum = 0; colNum < headerRow.size(); colNum++) {
				def cellValue = headerRow.get(colNum).toString().trim()

				if (cellValue.equals(startDate)) {
					startColumn = colNum
				} else if (cellValue.equals(endDate)) {
					endColumn = colNum
				}
			}

			if (startColumn != -1 && endColumn != -1) {
				// Loop through rows to find the expected value within the date range
				def rows = excelData.getAllData().subList(1, excelData.getAllData().size())
				for (def row : rows) {
					def cellValue = row.get(startColumn).toString().trim()

					if (cellValue.equals(startDate)) {
						// Verify the expected value in the corresponding row
						def valueCellValue = row.get(endColumn).toString().trim()

						if (valueCellValue.equals(expectedValue)) {
							println("Found the expected value '$expectedValue' in the date range: $startDate to $endDate")
							return
						}
					}
				}

				// If the expected value is not found, mark the verification as failed
				throw new Exception("Expected value '$expectedValue' not found in the date range: $startDate to $endDate")
			} else {
				throw new Exception("Start date '$startDate' or end date '$endDate' not found in the header row.")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Verification failed: " + e.getMessage())
		}
	}

}