package api.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class XLUtility {

    //Java library classes
    public FileInputStream fi;
    public FileOutputStream fo;
    // Apache library classes
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    //constructor
    public XLUtility(String path){
        this.path = path;
    }

    // get no of rows in a sheet
    public int getRowCount(String sheetName) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet  = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowCount;

    }

    // get no of cells in a row
    public int getCellCount(String sheetName, int rowNum) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet  = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellCount;

    }

    //get cell data
    public String getCellData(String sheetName, int rowNum, int column) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet  = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(column);

        DataFormatter formatter = new DataFormatter();
        String data;
        try{
            //returns the formatted value of a cell as a String
            data = formatter.formatCellValue(cell);
        }
        catch(Exception e){
            data = "";
        }
        workbook.close();
        fi.close();
        return data;

    }

    //set cell data
    public String setCellData(String sheetName, int rowNum, int column, String data) throws IOException {

        File xlfile = new File(path);
        if(!xlfile.exists()) // if no file exists then create a new file
        {
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
        }
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        if(workbook.getSheetIndex(sheetName)==-1)// if sheet does not exist then create a new sheet{
        {
            workbook.createSheet(sheetName);
        }
        sheet  = workbook.getSheet(sheetName);

        if(sheet.getRow(rowNum) == null) // if row does not exist then create new row
        {
            sheet.createRow(rowNum);
        }
        row = sheet.getRow(rowNum);

        cell = row.createCell(column);
        cell.setCellValue(data);
        fo= new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        return data;

    }

    // fill green color in cell
    public void fillGreenColor(String sheetName, int rowNum, int column) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet  = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(column);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillForegroundColor(FillPatternType.SOLID_FOREGROUND.getCode());

        cell.setCellStyle(style);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();

    }

    // fill red color in cell
    public void fillRedColor(String sheetName, int rowNum, int column) throws IOException {

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet  = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(column);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillForegroundColor(FillPatternType.SOLID_FOREGROUND.getCode());

        cell.setCellStyle(style);
        workbook.write(fo);

        workbook.close();
        fi.close();
        fo.close();

    }






}
