for (def index : (1..datafile.getRowNumbers())) {
	for (def pair : fileColumns) {
		def columnName = pair.key
				
				def columnIndex = pair.value
				
				def dataValue = datafile.getValue(columnName, index)
				
				CustomKeywords.'copyToExcel.exel2'(dataValue, index + startRowFormMinusOne, columnIndex, downloadedFormPath, downloadedFormSheetname)
	}
}


println('Wait for writing to finish')

