package kitabooTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
public class ReportGenerate extends GenericMethod {

	private static XSSFWorkbook workbook = null;
	private static XSSFSheet sheet;

	private static ExtentReports extent;
	private static ExtentTest Parent;
	public static String screenshot;
	public static String screenText = "Above screenshot shows status as: ";
	Constants c= new Constants();

	private static XSSFRow row = null;
	private static XSSFCell cell = null;
	private static XSSFFont font = null;

	static ArrayList<ArrayList<String>> data = TestData.getSheet();

	static Object[][] headerData1 = {
			{"Test Case ID", "Collection Name", "ISBN", "Book Name", "Container xml", "Description", "Actual Value", "Results", "Screenshots" }
	};
	
	static Object[][] headerData = {
			{"Test Case ID", "Question Title", "GUID", "Component Name", "Display Title", "Description", "Actual Value", "Results", "Screenshots" }
	};


	public static void createFolder() throws InterruptedException, IOException {
		try {
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
	
	
	public static void createHeader() throws InterruptedException, IOException {
		try {
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("report");
			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setColor(IndexedColors.ORANGE.getIndex());
			font.setBold(true);
			style.setFont(font);

			int rowCount = 0;

			for (Object[] eBook : headerData) {
				Row row = sheet.createRow(rowCount++);

				int columnCount = 0;

				for (Object field : eBook) {
					Cell cell = row.createCell(columnCount++);
					if (field instanceof String) {
						cell.setCellValue((String) field);
						cell.setCellStyle(style);
					} else if (field instanceof Integer) {
						cell.setCellValue((Integer) field);
					}
				}

			}


			flushWorkbook();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void writeResult(String id,String title, String componentname, String displaytitle, String desc, String actual, String result, String screenshot) {

		try {

			XSSFCellStyle write_style = workbook.createCellStyle();
			XSSFFont write_font=workbook.createFont();
			//write_font.setColor(XSSFFont.COLOR_RED);
			//write_style.setFont(write_font);
			row = sheet.createRow(sheet.getLastRowNum() + 1);


			row.createCell(0).setCellValue(id);
			row.createCell(1).setCellValue(title);
			row.createCell(3).setCellValue(componentname);
			row.createCell(4).setCellValue(displaytitle);
			row.createCell(5).setCellValue(desc);
			row.createCell(6).setCellValue(actual);

			if(result.equalsIgnoreCase("Fail"))
			{
				write_font.setColor(XSSFFont.COLOR_RED);
				write_font.setBold(true);
				write_style.setFont(write_font);
				Cell cell = row.createCell(7);
				cell.setCellValue(result);
				cell.setCellStyle(write_style);
			}

			else
			{
			   row.createCell(7).setCellValue(result);
			}
		
			row.createCell(8).setCellValue(screenshot);

			flushWorkbook();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}


	public static void createHeader1() throws InterruptedException, IOException {
		try {
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("report");
			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setColor(IndexedColors.ORANGE.getIndex());
			font.setBold(true);
			style.setFont(font);

			int rowCount = 0;

			for (Object[] eBook : headerData1) {
				Row row = sheet.createRow(rowCount++);

				int columnCount = 0;

				for (Object field : eBook) {
					Cell cell = row.createCell(columnCount++);
					if (field instanceof String) {
						cell.setCellValue((String) field);
						cell.setCellStyle(style);
					} else if (field instanceof Integer) {
						cell.setCellValue((Integer) field);
					}
				}

			}


			flushWorkbook();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void writeResult1(String id,String title,String bookname , String componentname, String displaytitle, String desc, String actual, String result, String screenshot) {

		try {

			XSSFCellStyle write_style = workbook.createCellStyle();
			XSSFFont write_font=workbook.createFont();
			row = sheet.createRow(sheet.getLastRowNum() + 1);

			row.createCell(0).setCellValue(id);
			row.createCell(1).setCellValue(title);
			row.createCell(2).setCellValue(bookname);
			row.createCell(3).setCellValue(componentname);
			row.createCell(4).setCellValue(displaytitle);
			row.createCell(5).setCellValue(desc);
			row.createCell(6).setCellValue(actual);

			if(result.equalsIgnoreCase("Fail"))
			{
				write_font.setColor(XSSFFont.COLOR_RED);
				write_font.setBold(true);
				write_style.setFont(write_font);
				Cell cell = row.createCell(7);
				cell.setCellValue(result);
				cell.setCellStyle(write_style);
			}

			else
			{
				row.createCell(7).setCellValue(result);
			}

			row.createCell(8).setCellValue(screenshot);

			flushWorkbook();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void flushWorkbook() throws IOException, ParseException {

		FileOutputStream fos = new FileOutputStream(new File(System.getProperty("user.dir") + "\\Reports\\report\\report.xlsx"));
		workbook.write(fos);
		fos.flush();
	}



	public static void logStart()
	{
		extent = new ExtentReports(".\\Reports\\HtmlReport.html", false);
		Parent = extent.startTest("Validate kitaboo functionality");
		extent.flush();
	}


	public static void Pass(String details, String actualResult) throws IOException, InterruptedException
	{
		Reports.details=details;
		Parent.log(LogStatus.PASS, details, actualResult);

		Parent.log(LogStatus.PASS,Parent.addScreenCapture(GenericMethod.addScreenCapture()) + screenText + " Test Pass");

	}

	public static void Fail(String details, String actualResult) throws IOException, InterruptedException
	{
		Reports.details=details;
		Parent.log(LogStatus.FAIL, details, actualResult);
		Parent.log(LogStatus.FAIL,Parent.addScreenCapture(GenericMethod.addScreenCapture()) + screenText + " Test Fail");
	}

	public static void Info(String details, String actualResult) throws IOException, InterruptedException
	{
		Reports.details=details;
		Parent.log(LogStatus.INFO, details, actualResult);
	}

	public static void Skip(String details, String actualResult) throws IOException, InterruptedException
	{
		Reports.details=details;
		Parent.log(LogStatus.SKIP, details, actualResult);
		Parent.log(LogStatus.SKIP,Parent.addScreenCapture(GenericMethod.addScreenCapture()) + screenText +" Test skipped");
	}

	public static void Fatal(String details, String actualResult, String screenshot)
	{
		Reports.details=details;
		Parent.log(LogStatus.FATAL, details);
	}

	public static void Error(String details, String actualResult)
	{
		Reports.details=details;
		Parent.log(LogStatus.ERROR, details, actualResult);
	}

	public static void Warning(String details, String actualResult)
	{
		Reports.details=details;
		Parent.log(LogStatus.WARNING, details, actualResult);
	}

	public static void Unknown(String details, String actualResult)
	{
		Reports.details=details;
		Parent.log(LogStatus.UNKNOWN, details, actualResult);
	}

	public static void after(){

		extent.endTest(Parent);
		extent.flush();
	}



}
