package kitaboomain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReportGenerateOld {

	private static XSSFWorkbook workbook = null;
	private static XSSFSheet sheet;

	private static XSSFRow row = null;
	private static XSSFCell cell = null;
	private static XSSFFont font = null;

	static ArrayList<ArrayList<String>> data = TestDataOld.getSheet();
	ConstantsOld c= new ConstantsOld();
	@BeforeTest

	public static void createFolder() throws InterruptedException, IOException {
		try {
			System.out.println(ConstantsOld.browser);
			File file = new File(System.getProperty("user.dir") + "\\Reports\\report");
			if (!file.exists())

			{
				file.mkdir();
				System.out.println("Folder Created");

			} else {
				System.out.println("Folder Exists");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	@Test

	public static void createHeader() throws InterruptedException, IOException {
		try {
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("report");
			row = sheet.createRow(0);

			row.createCell(0).setCellValue("Test Case ID");
			row.createCell(1).setCellValue("Title");
			row.createCell(2).setCellValue("GUID");
			row.createCell(3).setCellValue("Component Name");
			row.createCell(4).setCellValue("Display Title");
			row.createCell(5).setCellValue("Description");
		//	row.createCell(6).setCellValue("Actual Value");
		//	row.createCell(7).setCellValue("Result");

			flushWorkbook();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void writeResult(String id, String componentname, String displaytitle, String desc, String actual, String result) {

		try {

			row = sheet.createRow(sheet.getLastRowNum() + 1);

			row.createCell(0).setCellValue(id);
			row.createCell(1).setCellValue(componentname);
			row.createCell(2).setCellValue(displaytitle);
			row.createCell(3).setCellValue(desc);
			row.createCell(4).setCellValue(actual);
			row.createCell(5).setCellValue(actual);
			
			flushWorkbook();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	static void flushWorkbook() throws IOException, ParseException {

		FileOutputStream fos = new FileOutputStream(new File(System.getProperty("user.dir") + "\\Reports\\report\\report.xlsx"));
		workbook.write(fos);
		fos.flush();
	}

}
