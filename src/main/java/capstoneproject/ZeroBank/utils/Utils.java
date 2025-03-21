package capstoneproject.ZeroBank.utils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static void takeScreenshot(WebDriver driver, String fileName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String destPath = "screenshots/" + fileName + "_" + timestamp + ".png";
            FileUtils.copyFile(source, new File(destPath));
            System.out.println("Screenshot taken: " + destPath);
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    public static String readExcelData(String filePath, int sheetIndex, int row, int cell) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row sheetRow = sheet.getRow(row);
        Cell sheetCell = sheetRow.getCell(cell);
        String cellValue = sheetCell.toString();
        workbook.close();
        return cellValue;
    }

    public static void writeExcelData(String filePath, int sheetIndex, int row, int cell, String data) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row sheetRow = sheet.getRow(row);
        Cell sheetCell = sheetRow.createCell(cell);
        sheetCell.setCellValue(data);
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }
}

