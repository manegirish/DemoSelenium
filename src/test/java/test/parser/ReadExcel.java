package test.parser;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel {

	public static void rowParser(String file_path, String work_book) 
							throws IOException, InterruptedException{

		FileInputStream file = new FileInputStream(file_path);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(work_book);
		Iterator<Row> row = sheet.iterator();
		int row_number = 0;
		while (row.hasNext()) {
			Row rowIterator = (Row) row.next();
			Iterator<Cell> cellItr = rowIterator.cellIterator();
			String data = "";
			ArrayList<Object> dataArrayList = new ArrayList<Object>();
			while (cellItr.hasNext()) {
				Cell cell = (Cell) cellItr.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					data = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					data = "" + cell.getBooleanCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					data = "" + cell.getNumericCellValue();
					break;
				}
				dataArrayList.add(data);
			}
			try {
				DataParser.parser(dataArrayList, file_path, work_book, row_number);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			row_number++;
		}
	}
}
