package com.baktajivan;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    private static final String FILE_NAME = "D:\\GitComm/Assignment 1 Result.xlsx";
    private static XSSFWorkbook workbook = new XSSFWorkbook();
    private static XSSFSheet sheet1 = workbook.createSheet("Students Submit");
    private static XSSFSheet sheet2 = workbook.createSheet("Students Not Submit");
    private static XSSFSheet sheet3 = workbook.createSheet("Unknown List");
    private static Compare comp = new Compare();

    public static void main(String[] args) {

        Compare.main(args);
        Object[][] data1 = Compare.studS;
        Object[][] data2 = Compare.studNS;
        Object[][] data3 = Compare.studU;

        int rowNum = 0;
        System.out.println();
        System.out.println("Creating Main File");

        getRowNum(sheet1, data1, rowNum);

        getRowNum(sheet2, data2, rowNum);

        getRowNum(sheet3, data3, rowNum);

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found or currently open");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done");
    }

    static void getRowNum(XSSFSheet sheet, Object[][] data, int rowNum) {
        for (Object[] datatype : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                sheet.autoSizeColumn(rowNum);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        for (int x = 0; x < sheet.getRow(0).getPhysicalNumberOfCells(); x++) {
            sheet.autoSizeColumn(x);
        }
    }

}
