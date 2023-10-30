import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import java.nio.file.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.util.KeywordUtil

public class ManageFiles {

	@Keyword(keywordObject='Manage Files')
	def getLatestFileFromDirectory(String filetype) {
		String downloadDir = "C:\\Katalon_Downloads"
		File dir = new File(downloadDir)

		// Check if the directory exists and is valid
		if (!dir.exists() || !dir.isDirectory()) {
			throw new IllegalArgumentException("Invalid directory: $downloadDir")
		}

		def files

		// Switch based on the provided file type
		switch (filetype) {
			case "zip":
				files = getFilteredFiles(dir, ".zip")
				break
			case "folder":
				files = getFilteredDirectories(dir)
				break
			case "excel":
				files = getFilteredFiles(dir, ".xlsx")
				break
			case "macroexcel":
				files = getFilteredFiles(dir, ".xlsm")
				break
			default:
				throw new IllegalArgumentException("Unsupported file type: $filetype")
		}

		if (files.length > 0) {
			// Sort files based on last modified timestamp
			files = files.sort { it.lastModified() } as File[]
			File latestFile = files.last()
			String latestFilePath = latestFile.getAbsolutePath()

			// Log the appropriate information based on the file type
			logInfoFile(filetype == "zip" ? "Latest File In Directory: $latestFilePath" :
					filetype == "folder" ? "Latest Folder In Directory: $latestFilePath" :
					"Latest Excel File In Directory: $latestFilePath")
			return latestFilePath
		} else {
			// Log appropriate messages when no files are found for the given file type
			logInfoFile(filetype == "zip" ? "No ZIP files in the specified directory" :
					filetype == "folder" ? "No folders in the specified directory" :
					"No Excel files in the specified directory")
			return null // Or throw an exception or return a specific value to indicate an error condition
		}
	}

	@Keyword(keywordObject='Manage Files')
	def unzipFileIntoParentDirectory(String filepath) {
		// Specify the file path of the zip file
		String filePath = filepath

		// Check if the file path exists
		if (Files.exists(Paths.get(filePath))) {
			// Check if the file has a .zip extension
			if (filePath.toLowerCase().endsWith(".zip")) {
				// Create a ZipInputStream to read the zip file
				ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(filePath))

				// Extract the folder name from the zip file
				String folderName = Paths.get(filePath).getFileName().toString().replaceFirst('[.][^.]+$', '')

				// Create the destination folder with the same name as the zip file
				String destinationFolderPath = Paths.get(filePath).getParent().toString() + "\\" + folderName
				Files.createDirectories(Paths.get(destinationFolderPath))

				// Iterate over each entry in the zip file
				ZipEntry entry = zipInputStream.getNextEntry()
				while (entry != null) {
					// Get the name of the entry (file or folder)
					String destinationPath = destinationFolderPath + "\\" + entry.getName()

					if (!entry.isDirectory()) {
						// Create the directories if they don't exist
						Files.createDirectories(Paths.get(destinationPath).getParent())

						// Copy the contents of the entry to the destination path
						Files.copy(zipInputStream, Paths.get(destinationPath))
					}

					// Move to the next entry
					zipInputStream.closeEntry()
					entry = zipInputStream.getNextEntry()
				}

				// Close the zip input stream
				zipInputStream.close()

				KeywordUtil.logInfo(filePath + ': Successfully Extracted into :' + destinationFolderPath)

			} else {
				KeywordUtil.logInfo('The specified file is not a ZIP file.')
			}
		} else {
			KeywordUtil.logInfo("The specified file does not exist.")
		}
	}

	@Keyword(keywordObject="Manage Files")
	def checkDownloadedCommonMasterExcelFiles(String folderpath) {
		// Specify the path to the extracted folder
		String extractedFolderPath = folderpath

		// Get the test data
		def testData = TestDataFactory.findTestData('Data Files/Scenario 13/Common Master Download')

		// List to store the required file names
		List<String> requiredFileNames = []

		// Loop through the test data and extract the file names
		for (int row = 1; row <= testData.getRowNumbers(); row++) {
			String fileName = testData.getValue('DownloadFileName', row)
			requiredFileNames.add(fileName)
		}

		// List to store the existing file paths
		List<String> existingFilePaths = []

		// Check if all required files exist and collect their paths
		requiredFileNames.each { fileName ->
			File file = new File(extractedFolderPath, fileName)
			if (file.exists() && file.isFile()) {
				existingFilePaths.add(file.getAbsolutePath())
			}
		}

		// Print the result
		if (existingFilePaths.size() == requiredFileNames.size()) {
			println "All required files exist in the extracted folder:"
			existingFilePaths.each { path ->
				KeywordUtil.logInfo(path + ' is Exist')
			}
		} else {
			KeywordUtil.markFailedAndStop('Some required files are missing in the extracted folder.')
		}
	}

	@Keyword(keywordObject='Manage Files')
	def getFileAbsolutePath(String relative_path) {
		// Define the relative path
		String relativePath = relative_path

		// Create a File object using the relative path
		File file = new File(relativePath)

		String absolutePath = file.getAbsolutePath()

		// Print the absolute path
		KeywordUtil.logInfo("Absolute path: " + absolutePath)

		// Return the absolute path
		return absolutePath
	}

	@Keyword(keywordObject='Manage Files')
	def moveFileandRename(String sourcefilepath, String targetfolderpath, String filename) {
		def sourceFilePath = Paths.get(sourcefilepath)
		def targetFolderPath = Paths.get(targetfolderpath)
		def targetFileName = filename

		try {
			// Resolve the target file path by combining the target folder path and the new file name
			def targetFilePath = targetFolderPath.resolve(targetFileName)

			// Check if the target file already exists, and if it does, delete it
			if (Files.exists(targetFilePath)) {
				Files.delete(targetFilePath)
			}

			// Move the source file to the target folder with the new name
			Files.move(sourceFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING)

			println("File moved and renamed successfully.")
		} catch (IOException e) {
			println("Error: ${e.message}")
		}
	}


	/**
	 * Replace the expected file with the contents of the downloaded file specified by its path.
	 * @param downloadedFilePath The path of the downloaded file.
	 * @param expectedFilePath The path of the expected file (the target location where the downloaded file should be replaced).
	 * @return The path of the replaced file.
	 */
	@Keyword
	def replaceExpectedFileWithDownloadedFile(String downloadedFilePath, String expectedFilePath) {
		// Check if the downloaded file exists
		File downloadedFile = new File(downloadedFilePath)
		if (!downloadedFile.exists()) {
			KeywordUtil.markFailed("Downloaded file does not exist.")
			return null
		}

		// Delete the expected file if it exists
		File expectedFile = new File(expectedFilePath)
		if (expectedFile.exists()) {
			expectedFile.delete()
		}

		// Rename the downloaded file to the name of the expected file and move it to the location of the expected file
		downloadedFile.renameTo(expectedFile)

		return expectedFile.getAbsolutePath()
	}

	// NON-KEYWORDS LINE

	def getFilteredFiles(File dir, String extension) {
		dir.listFiles({ file -> file.isFile() && file.name.toLowerCase().endsWith(extension) } as FileFilter)
	}

	def getFilteredDirectories(File dir) {
		dir.listFiles({ file -> file.isDirectory() } as FileFilter)
	}

	def logInfoFile(String message) {
		// Your logging implementation here
		KeywordUtil.logInfo(message)
	}

}

