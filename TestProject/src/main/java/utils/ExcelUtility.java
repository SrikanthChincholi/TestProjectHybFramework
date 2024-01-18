package utils;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	static FileInputStream fis;
	static String filepath = System.getProperty("user.dir") + "//DataSheets//TestData.xlsx";

	public static Object[][] getExcelData() throws Exception {
		fis = new FileInputStream(filepath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("TestData");
		int noofrows = sh.getPhysicalNumberOfRows();
		int noofcols = sh.getRow(0).getPhysicalNumberOfCells();
		Object data[][] = new Object[noofrows-1][noofcols];
		for (int i = 1; i < noofrows; i++) {
			Row rows = sh.getRow(i);
			for (int j = 0; j < noofcols; j++) {
				Cell cells = rows.getCell(j);
				DataFormatter format = new DataFormatter();
				data[i-1][j] = format.formatCellValue(cells);
			}

		}
		return data;
	}

	

}
