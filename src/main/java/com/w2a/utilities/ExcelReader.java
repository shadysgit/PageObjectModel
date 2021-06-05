package com.w2a.utilities;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

public class ExcelReader {

    public String path;
    public FileInputStream fis = null;
    public FileOutputStream fout = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    public XSSFCell cell = null;


    public ExcelReader(String path) {
        this.path = path;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //returns the rown count
    public int rowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) {
            return 0;
        } else {
            sheet = workbook.getSheetAt(index);
            return sheet.getLastRowNum()+1;
        }
    }


// returns the data from a cell

    public String getCellData(String sheetName, String colName, int rowNum) {

        try {

            if (rowNum <= 0) return "";

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1) return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            int col_index = -1;
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().equals(colName.trim()))
                    col_index = i;
            }
            if (col_index == -1)
                return "";

            row = sheet.getRow(rowNum - 1);
            if (row == null) return "";

            cell = row.getCell(col_index);
            if (cell == null) return "";

            if (cell.getCellTypeEnum() == CellType.STRING)
                return cell.getStringCellValue();

            else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));

                    cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);

                    cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cal.get(Calendar.MONTH) + "/" +
                            cal.get(Calendar.YEAR) + 1 + "/" + cellText;
                }
                return cellText;
            } else if (cell.getCellTypeEnum() == CellType.BLANK) return "";
            else return String.valueOf(cell.getBooleanCellValue());
        } catch (Exception e) {
            e.printStackTrace();
            return "row " + rowNum + "or Column " + colName + " doesnt exist in xls";
        }
    }


    public String getCellData(String sheetName, int colNum, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);

            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(colNum);
            if (cell == null)
                return "";

            if (cell.getCellTypeEnum() == CellType.STRING)
                return cell.getStringCellValue();

            else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));

                    cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);

                    cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cal.get(Calendar.MONTH) + "/" +
                            cal.get(Calendar.YEAR) + 1 + "/" + cellText;
                }
                return cellText;
            } else if (cell.getCellTypeEnum() == CellType.BLANK) return "";
            else return String.valueOf(cell.getBooleanCellValue());
        } catch (Exception e) {
            e.printStackTrace();
            return "row " + rowNum + "or Column " + colNum + " doesnt exist in xls";
        }
    }

    public boolean setCellData(String sheetName, String colName, int rowNum, String data) {

        try {

            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            int index = workbook.getSheetIndex(sheetName);

            if (rowNum <= 0 || index == -1)
                return false;

            sheet = workbook.getSheetAt(index);
            int colIndex = -1;

            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().equals(colName.trim()))
                    colIndex = i;
            }

            if (colIndex == -1)
                return false;

            sheet.autoSizeColumn(colIndex);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colIndex);
            if (cell == null)
                cell = row.createCell(colIndex);

            cell.setCellValue(data);

            fout = new FileOutputStream(path);
            workbook.write(fout);

            fout.flush();
            fout.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean setCellData(String sheetName, String colName, int rowNum, String data, String url) {
        try {

            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            int index = workbook.getSheetIndex(sheetName);

            if (rowNum <= 0 || index == -1)
                return false;

            sheet = workbook.getSheetAt(index);
            int col_index = -1;
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().equals(colName.trim()))
                    col_index = i;
            }

            if (col_index == -1) {
                return false;
            }

            sheet.autoSizeColumn(col_index);

            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(col_index);
            if (cell == null)
                row.createCell(col_index);

            cell.setCellValue(data);

            XSSFCreationHelper createHelper = workbook.getCreationHelper();

            //cell style for hyperlinks

            CellStyle hlinkStyle = workbook.createCellStyle();
            XSSFFont hlinkFont = workbook.createFont();
            hlinkFont.setUnderline(XSSFFont.U_SINGLE);
            hlinkFont.setColor(IndexedColors.BLUE.getIndex());
            hlinkStyle.setFont(hlinkFont);


            XSSFHyperlink hyperLink = createHelper.createHyperlink(HyperlinkType.FILE);
            hyperLink.setAddress(url);
            cell.setHyperlink(hyperLink);
            cell.setCellStyle(hlinkStyle);

            fout = new FileOutputStream(path);
            workbook.write(fout);
            fout.flush();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean addSheet(String sheetName) {

        try {
            fout = new FileOutputStream(path);
            workbook.createSheet(sheetName);
            workbook.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeSheet(String sheetName) {

        try {

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1) return false;
            fout = new FileOutputStream(path);
            workbook.removeSheetAt(index);
            workbook.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addColumn(String sheetName, String colName) {

        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) return false;
        try {

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            if (row == null) {
                row = sheet.createRow(0);
            }
            if (row.getLastCellNum() == -1) {
                cell = row.createCell(0);
            } else {
                cell = row.createCell(row.getLastCellNum());
            }
            cell.setCellValue(colName);
            XSSFCellStyle style = workbook.createCellStyle();
            //style.setFillForegroundColor();
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);
            fout = new FileOutputStream(path);
            workbook.write(fout);
            fout.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean removeColumn(String sheetName, int colNum) {

        try {
            if (!isSheetExist(sheetName)) return false;
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_40_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.NO_FILL);


            for (int i = 0; i < rowCount(sheetName); i++) {

                row = sheet.getRow(i);
                if (row != null) {
                    cell = row.getCell(colNum);
                    if (cell != null) {
                        cell.setCellStyle(style);
                        row.removeCell(cell);
                    }
                }
            }
            fout = new FileOutputStream(path);
            workbook.write(fout);
            fout.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isSheetExist(String sheetName) {

        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) {
            index = workbook.getSheetIndex(sheetName.toUpperCase());
            return index != -1;
        }
        return true;
    }

    public int getColumnCount(String sheetName) {

        if (!isSheetExist(sheetName)) return -1;
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
        if (row == null) return -1;
        return row.getLastCellNum();
    }


    public int getCellRowNum(String sheetName, String colName, String cellValue) {

        if (!isSheetExist(sheetName)) return -1;
        for (int i = 2; i < rowCount(sheetName); i++) {
            if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) return i;
        }
        return -1;
    }

    public boolean addHyperLink(String sheetName, String screenShotColName, String testCaseName, int index, String url, String message) {

        url = url.replace('\\', '/');
        if (!isSheetExist(sheetName)) return false;

        sheet = workbook.getSheet(sheetName);
        for (int i = 2; i < rowCount(sheetName); i++) {
            if (getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {

                setCellData(sheetName, screenShotColName, i + index, message, url);
                break;
            }

        }
        return true;
    }


}
