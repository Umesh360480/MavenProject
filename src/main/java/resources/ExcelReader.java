package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	FileInputStream fis;
	XSSFWorkbook wb;
	String requiredSheet;

	public ExcelReader(String filePath, String sheetName) throws IOException {
		fis = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fis);
		Iterator<Sheet> sheetList = wb.iterator();
		while (sheetList.hasNext()) {
			Sheet currentSheetName = sheetList.next();
			if (currentSheetName.getSheetName().equalsIgnoreCase(sheetName)) {
				requiredSheet = currentSheetName.getSheetName();
				break;
			}
		}
		System.out.println(requiredSheet);
	}

	public String getData(String columnName) {
		String data = null;
		int celIndex;
		Iterator<Row> rows = wb.getSheet(requiredSheet).rowIterator();
		if (rows.hasNext()) {
			Row firstRow = rows.next();
			Row secondRow = rows.next();
			Iterator<Cell> firstRowCols = firstRow.cellIterator();
			while (firstRowCols.hasNext()) {
				Cell cel = firstRowCols.next();
				if (cel.getStringCellValue().trim().equalsIgnoreCase(columnName)) {
					celIndex = cel.getColumnIndex();
					Cell reqCel = secondRow.getCell(celIndex);
					if (reqCel.getCellType() == CellType.STRING)
						data = reqCel.getStringCellValue();
					else if (reqCel.getCellType() == CellType.NUMERIC)
						data = String.valueOf(reqCel.getNumericCellValue());
					else if (reqCel.getCellType() == CellType.BOOLEAN)
						data = String.valueOf(reqCel.getBooleanCellValue());
					break;
				}

			}
		}
		System.out.println(data);
		return data;
	}

	public String getDataFromRow(int rowIndex, String columnName) {
		String data = null;
		int celIndex;
		Iterator<Row> rows = wb.getSheet(requiredSheet).rowIterator();
		if (rows.hasNext()) {
			Row firstRow = rows.next();
			Row reqRow = wb.getSheet(requiredSheet).getRow(rowIndex);
			Iterator<Cell> firstRowCols = firstRow.cellIterator();
			while (firstRowCols.hasNext()) {
				Cell cel = firstRowCols.next();
				if (cel.getStringCellValue().trim().equalsIgnoreCase(columnName)) {
					celIndex = cel.getColumnIndex();
					Cell reqCel = reqRow.getCell(celIndex);
					if (reqCel.getCellType() == CellType.STRING)
						data = reqCel.getStringCellValue();
					else if (reqCel.getCellType() == CellType.NUMERIC)
						data = String.valueOf(reqCel.getNumericCellValue());
					else if (reqCel.getCellType() == CellType.BOOLEAN)
						data = String.valueOf(reqCel.getBooleanCellValue());
					break;
				}

			}
		}
		System.out.println(data);
		return data;
	}

}
