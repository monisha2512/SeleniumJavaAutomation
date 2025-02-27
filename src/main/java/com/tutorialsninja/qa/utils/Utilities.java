package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
    
    public static final int IMPLICIT_WAIT_TIME = 10;
    public static final int PAGE_LOAD_TIME = 5;

    public static String generateEmailWithTimeStamp() {
        Date date = new Date();
        String timestamp = date.toString().replace(" ", "_").replace(":", "_");
        return "amotoori" + timestamp + "@gmail.com";
    }

    public static Object[][] getTestDataFromExcel(String sheetName) {
        File excelFilePath = new File(System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "main" + File.separator +
                "java" + File.separator + "com" + File.separator +
                "tutorialsninja" + File.separator + "qa" + File.separator +
                "testdata" + File.separator + "TutorialsNinjaTestData.xlsx");
        

        XSSFWorkbook workbook = null;
        FileInputStream excelFile = null;
        Object[][] data = null;

        try {
            excelFile = new FileInputStream(excelFilePath);
            workbook = new XSSFWorkbook(excelFile);
            XSSFSheet sheet = workbook.getSheet(sheetName);


            int rows = sheet.getLastRowNum();
            int cols = sheet.getRow(0).getLastCellNum();

            // Ensure exactly 2 columns (email, password)
            data = new Object[rows][2];

            for (int i = 0; i < rows; i++) {
                XSSFRow row = sheet.getRow(i + 1); // Skip header row
                if (row == null) continue;

                XSSFCell emailCell = row.getCell(0);
                XSSFCell passwordCell = row.getCell(1);

                String email = (emailCell != null) ? emailCell.getStringCellValue() : "";
                String password = "";

                if (passwordCell != null) {
                    switch (passwordCell.getCellType()) {
                        case STRING:
                            password = passwordCell.getStringCellValue();
                            break;
                        case NUMERIC:
                            password = String.valueOf((int) passwordCell.getNumericCellValue()); // Convert double to int
                            break;
                        default:
                            password = "";
                    }
                }

                data[i][0] = email;
                data[i][1] = password;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        } finally {
            try {
                if (workbook != null) workbook.close();
                if (excelFile != null) excelFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data;
    }
    public static String captureScreenshot(WebDriver driver,String testName) {
    	File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);		
		//code for taking screenshot
		String destinationScreenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		
		//for copying the screenshot from srcScreenshot location to destinationScreenshotPath location
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationScreenshotPath;
    	
    }
}
