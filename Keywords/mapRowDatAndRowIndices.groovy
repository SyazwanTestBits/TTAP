import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil


public class mapRowDatAndRowIndices {

	@Keyword
	def extractPartsWithIndices(String filePath,int partsNoColumnIndex,int startingRow) {
		def partToIndexMap = [:] // Create a map to store Part No. as keys and row indices as values

		def inputFile = new File(filePath)
		def workbook = new XSSFWorkbook(inputFile)
		def sheet = workbook.getSheetAt(0) // Assuming you're working with the first sheet

		for (int rowIndex = startingRow; rowIndex <= sheet.lastRowNum; rowIndex++) {
			def row = sheet.getRow(rowIndex)
			if (row) {
				def cell = row.getCell(partsNoColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
				def partNumber = cell.toString()

				// Check if the partNumber is already in the map, if yes, update the index
				if (partToIndexMap.containsKey(partNumber)) {
					partToIndexMap[partNumber] = rowIndex
				} else {
					partToIndexMap.put(partNumber, rowIndex)
				}
			}
		}

		workbook.close()
		KeywordUtil.logInfo('PartMap:' + partToIndexMap)
		return partToIndexMap
	}


	@Keyword
	def extractPartsWithIndicesWithSheetList(String filePath,List<Integer> listNumber,int startingRow, int sheettnum) {
		def partToIndexMap = [:] // Create a map to store Part No. as keys and row indices as val
		def inputFile = new File(filePath)
		def workbook = new XSSFWorkbook(inputFile)
		def sheet = workbook.getSheetAt(sheettnum)

		for (int rowIndex = startingRow; rowIndex <= sheet.lastRowNum; rowIndex++) {
			def row = sheet.getRow(rowIndex)

			String searchValue=''

			for (int colnum : listNumber) {

				def cell = row.getCell(colnum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
				def partNumber = cell.toString()


				searchValue=searchValue+partNumber+" "
			}


			// Check if the partNumber is already in the map, if yes, update the index
			if (partToIndexMap.containsKey(searchValue)) {
				partToIndexMap[searchValue] = rowIndex
			} else {
				partToIndexMap.put(searchValue, rowIndex)
			}
		}

		workbook.close()
		KeywordUtil.logInfo('PartMap:' + partToIndexMap)
		return partToIndexMap
	}



	@Keyword
	def extractPartsWithIndicesSkipNull(String filePath,int partsNoColumnIndex,int startingRow) {
		def partToIndexMap = [:] // Create a map to store Part No. as keys and row indices as values

		def inputFile = new File(filePath)
		def workbook = new XSSFWorkbook(inputFile)
		def sheet = workbook.getSheetAt(0) // Assuming you're working with the first sheet

		for (int rowIndex = startingRow; rowIndex <= sheet.lastRowNum; rowIndex++) {
			def row = sheet.getRow(rowIndex)
			if (row) {
				def cell = row.getCell(partsNoColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)

				def partNumber = cell.toString()

				if(partNumber != '') {

					if (partToIndexMap.containsKey(partNumber)) {
						partToIndexMap[partNumber] = rowIndex
					} else {
						partToIndexMap.put(partNumber, rowIndex)
					}

				}

				// Check if the partNumber is already in the map, if yes, update the index

			}
		}

		workbook.close()
		KeywordUtil.logInfo('PartMap:' + partToIndexMap)
		return partToIndexMap
	}
}
