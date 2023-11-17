import selenium
import modules.utils as utils
import logging
import time
import openpyxl
import win32com.client as win32

def hello_world(allArgs, *numbers):
  print("Hello, World from print")
  logging.warning("Hello, World from logging.info")
  print("Sum of", numbers)
  s = sum(numbers)
  print("Sum is", s)
  return s

def goto_google(allArgs):
  driver = utils.get_driver(allArgs)
  driver.get("https://google.com")
  input_element = driver.find_element_by_name("q")
  input_element.send_keys("Search and close after 5 seconds")
  input_element.submit()
  time.sleep(5)
  
def disableProtect_excel(allArgs, filename):
   workbook = openpyxl.load_workbook(filename)
   sheet = workbook.active
   #workbook.save(filename)
   workbook.save(filename)
   
def disableProtect_excel_v2(allArgs, file_path, sheetName):
   # Initialize Excel application
    xlapp = win32.DispatchEx('Excel.Application')
    # Suppress display of alerts
    xlapp.DisplayAlerts = False
    # Set application visibility to True
    xlapp.Visible = True
    # Open the specified workbook
    xlbook = xlapp.Workbooks.Open(file_path)
    # Unprotect the workbook
    xlbook.Unprotect()
    # Save the workbook
    xlbook.Save()
    # Close the workbook
    xlbook.Close()
    # Quit the Excel application
    xlapp.Quit()
