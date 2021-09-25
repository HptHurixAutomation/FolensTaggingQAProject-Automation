package kitabooTest;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.exception.ZeroException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class SanityTestNew extends GenericMethod{

	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String screenshot, excelValues,removedspac,colA,colB,Browser;
	private String spreadsheetId = "1JtsAlxha2JhY617X9_G5xNpRXo1v-jfXS0aokWkRUNE"; 
	private String range = "Sheet1!A1:B";
	Constants c= new Constants();

	static ArrayList<ArrayList<String>> data = TestData.getSheet();

	@BeforeMethod
	public void setup() throws InterruptedException, IOException
	{

		try {
			File path = new File(System.getProperty("user.dir") + "\\Reports\\report");
			File[] files = path.listFiles();
			for (File file : files) {
				System.out.println("Deleted filename :"+ file.getName());
				file.delete();
			}

			File path1 = new File(System.getProperty("user.dir") + "\\Reports\\screenshot");
			File[] files1 = path1.listFiles();
			for (File file1 : files1) {
				System.out.println("Deleted filename :"+ file1.getName());
				file1.delete();
			}

			File path2 = new File(System.getProperty("user.dir") + "\\Reports\\HTMLReport.html");
			File files2 = path2;
			files2.delete();

			File path11 = new File(System.getProperty("user.dir") + "\\test-output");
			File[] files11 = path11.listFiles();
			for (File file11: files11) {
				System.out.println("Deleted filename :"+ file11.getName());
				file11.delete();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ReportGenerate.logStart();
		dataPropertiesFile();
		/*
		 * Call these method to create folder and header for Excel sheet
		 */
		ReportGenerate.createFolder();

	}


	@Test()
	public void kitabbooexection() throws InterruptedException, IOException, AWTException, GeneralSecurityException
	{

		GenericMethod sheetAPI = new GenericMethod();
		List<List<Object>> values = sheetAPI.getData(spreadsheetId, range);
		
		for(List row : values)
		{
			excelValues = row.toString();

			removedspac = excelValues.replaceAll("[\\p{Ps}\\p{Pe}]", "");
			String[] colRows = removedspac.trim().split(",");

			colA = colRows [0];
			colB = colRows [1];

			if(colA.contains("Browser"))
			{
				Browser = colB;
				invokeBrowser(colB);
			}
			else if(colA.contains("URL"))
			{
				if(colB.startsWith(" http"))
				{
					/*
					 * Generate report for normal scripts
					 */
					ReportGenerate.createHeader();

					launchURL(colB);

					String sheetvalue = values.get(2).toString();
					String removeexspace = sheetvalue.replaceAll("[\\p{Ps}\\p{Pe}]", "");
					String[]splitedValue = removeexspace.trim().split(",");
					String username = splitedValue [1].trim();
					String sheetvalue1 = values.get(3).toString();
					String removeexspace1 = sheetvalue1.replaceAll("[\\p{Ps}\\p{Pe}]", "");
					String[]splitedValue1 = removeexspace1.trim().split(",");
					String password = splitedValue1 [1].trim();

					folensSignIn(username, password);

				}

				else if(colB.contains(prop.getProperty("URLVport")))
				{
					/*
					 * Generate report for Vport voyager
					 */
					ReportGenerate.createHeader1();
					launchURL(colB);
					String sheetvalue = values.get(2).toString();
					String removeexspace = sheetvalue.replaceAll("[\\p{Ps}\\p{Pe}]", "");
					String[]splitedValue = removeexspace.trim().split(",");
					String username = splitedValue [1].trim();
					String sheetvalue1 = values.get(3).toString();
					String removeexspace1 = sheetvalue1.replaceAll("[\\p{Ps}\\p{Pe}]", "");
					String[]splitedValue1 = removeexspace1.trim().split(",");
					String password = splitedValue1 [1].trim();

					//vPortSignIn(username, password);
				}

				else
				{
					System.out.println("There is no URL");
				}

			}
			else if(colA.contains("tagsValidation") &&  colB.contains("Y"))
			{
				ReaderNew.tagsValidation();
			}
			else if(colA.contains("mark_unfavorite") && colB.contains("Y"))
			{
				//bookShelf.mark_unfavorite();
			}

		}
	}


	@AfterMethod
	public static void close() throws IOException, InterruptedException, ParseException
	{

		Browserquit();
	}

}
