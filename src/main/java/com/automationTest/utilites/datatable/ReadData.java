package com.automationTest.utilites.datatable;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadData {

    public Sheet readDataTestCase(String strSheet, String strPathDatatable) throws IOException {
        File file = new File(strPathDatatable);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook;
        String fileExtension = FilenameUtils.getExtension(strPathDatatable);

        if (fileExtension.equalsIgnoreCase("xlsx") || fileExtension.equalsIgnoreCase("xlsm")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtension.equalsIgnoreCase("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            workbook = null;
        }

        Sheet sheet = workbook.getSheet(strSheet);
        workbook.close();
        return sheet;
    }


    public Map<String, Object> getData(int intCounterRowDatatable, Sheet sheet) {
        int intCounterRow = getRowByNo(intCounterRowDatatable, sheet);
        int intCounterColumn = sheet.getRow(1).getLastCellNum() - sheet.getRow(1).getFirstCellNum();
        Map<String, Object> mapResult = new HashMap<>();

        for (int j = 1; j < intCounterColumn; j++) {
            String strHeader = sheet.getRow(0).getCell(j).toString();
            Object strValueHeader = getCellData(sheet.getRow(intCounterRow).getCell(j));
            mapResult.put(strHeader, strValueHeader);
        }
        return mapResult;
    }

    private int getRowByNo(int intNo, Sheet sheet) {
        int intCounterRow = 1;
        Object objTemp = getCellData(sheet.getRow(intCounterRow).getCell(0));
        try {
            while (objTemp != null) {
                if ((int) objTemp == intNo) {
                    break;
                }
                intCounterRow++;
                objTemp = getCellData(sheet.getRow(intCounterRow).getCell(0));
            }
        } catch (NullPointerException e) {
            return -1;

        }
        return intCounterRow;

    }

    private Object getCellData(Cell cellData) {
        try {
            String strTypeCell = cellData.getCellType().name();
            switch (strTypeCell) {
                case "STRING":
                    return cellData.getStringCellValue();
                case "NUMERIC":
                    return (int) cellData.getNumericCellValue();
                default:
                    return null;
            }
        } catch (NullPointerException e) {
            return null;
        }
    }

}
