package utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtility {

    private Workbook workbook;

    // Constructor to load the Excel file
    public ExcelUtility(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        workbook = WorkbookFactory.create(fileInputStream);
    }

    // Method to get row count for a specific sheet
    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet '" + sheetName + "' not found.");
        }
        return sheet.getLastRowNum() + 1; // Adding 1 as row index starts from 0
    }

    // Method to get cell count in a row for a specific sheet
    public int getCellCount(String sheetName, int rowNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet '" + sheetName + "' not found.");
        }
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            return 0;
        }
        return row.getLastCellNum(); // Gets total number of cells in the row
    }

    // Method to get cell data from a specific sheet
    public String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet '" + sheetName + "' not found.");
        }
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            return "";
        }
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            return "";
        }
        return getCellValueAsString(cell);
    }

    // Helper method to convert cell values to String
    private String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "UNKNOWN";
        }
    }

    // Method to close the workbook
    public void closeWorkbook() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }
}
