package kitabooTest;


import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.*;


public class SanityTest extends GenericMethod  {

	Constants c= new Constants();
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;

	static ArrayList<ArrayList<String>> data = TestData.getSheet();


	@BeforeMethod
	public void beforeMethod() throws InterruptedException, IOException {
		Reports.logStart();
		/*
		 * Call these method to create folder and header for Excel sheet
		 */
		ReportGenerate.createFolder();
		ReportGenerate.createHeader();

		/*
		 *  Launch browser with the URL provided in Testdata
		 */
		//invokeApplication(Constants.browser, Constants.URL);

		//sign in
		//signIn();

	}


	@Test
	public void execute() throws InterruptedException, IOException, AWTException {


		System.out.println("Executing test");
		FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+"\\testdata\\TestData.xlsx"));
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);

		int columnCount = sheet.getRow(0).getLastCellNum();

		for(int i=0; i<columnCount; i++)

		{
			Thread.sleep(1000);
			String colheader = data.get(0).get(i);

			String headerValue = data.get(1).get(i);

			//System.out.println("column header: "+colheader  + "header value: "+ headerValue);

			if(colheader.contains("mark_favorite") &&  headerValue.contains("Y"))
			{
				//bookShelf.mark_favorite();
			}
			else if(colheader.contains("mark_unfavorite") && headerValue.contains("Y"))
			{
				//bookShelf.mark_unfavorite();
			}

			else if(colheader.equals("search") && headerValue.contains("Y"))
			{
				//bookShelf.search();
			}

			else if(colheader.equals("search_cancelbtn") && headerValue.contains("Y"))
			{
				//bookShelf.search_cancelbtn();

			}

			else if(colheader.equals("validateMoreInfo") && headerValue.contains("Y"))

			{
				reader.category_tab_navigation(7);
				reader.open_book(9);
			}


			else if(colheader.equals("validateAnalytics") && headerValue.contains("Y"))
			{
				reader.category_tab_navigation(7);
				reader.open_book(9);
			}

			else if(colheader.contains("toc") && headerValue.contains("Y"))
			{
				reader.toc();
			}

			else if(colheader.contains("add_bookmark") && headerValue.contains("Y"))
			{
				reader.category_tab_navigation(7);
				reader.open_book(9);
				//reader.add_bookmark();
			}

			else if(colheader.contains("delete_bookmark") && headerValue.contains("Y"))
			{
				reader.delete_bookmark();
			}

			else if(colheader.contains("validSearch") && headerValue.contains("Y"))
			{
				reader.validSearch();
			}

			else if(colheader.contains("InvalidSearch") && headerValue.contains("Y"))
			{
				reader.InvalidSearch();
			}

			else if(colheader.contains("MoreWordSearch") && headerValue.contains("Y"))
			{
				reader.MoreWordSearch();
			}

			else if(colheader.contains("validateSearchShortcut") && headerValue.contains("Y"))
			{
				reader.validateSearchShortcut();
			}
			else if(colheader.contains("validateFontSize") && headerValue.contains("Y"))
			{
				reader.validateFontSize();
			}

			else if(colheader.contains("validateFontAlignment") && headerValue.contains("Y"))
				
			{
				//reader.category_tab_navigation(7);
				//reader.open_book(9);
				reader.validateFontAlignment();
			}

			else if(colheader.contains("validateFontFormat") && headerValue.contains("Y"))
			{
				reader.validateFontFormat();
			}

			else if(colheader.contains("validateLineSpacing") && headerValue.contains("Y"))
			{
				reader.validateLineSpacing();
			}

			else if(colheader.contains("validatePageMode") && headerValue.contains("Y"))
			{
				reader.validatePageMode();
			}

			else if(colheader.contains("validatePageMargins") && headerValue.contains("Y"))
			{
				reader.validatePageMargins();
			}

			else if(colheader.contains("validateScrollView") && headerValue.contains("Y"))
			{
				reader.validateScrollView();
			}


			else if(colheader.contains("validateBack_to_bookshelf") && headerValue.contains("Y"))

			{
				reader.category_tab_navigation(7);
				reader.open_book(9);
				reader.validateBack_to_bookshelf();
			}

			else if(colheader.contains("validateThumbnail") && headerValue.contains("Y"))
			{

				reader.category_tab_navigation(8);
				reader.open_book(8);
			}

			else if(colheader.contains("validateSingleDoubleview") && headerValue.contains("Y"))
			{
				reader.category_tab_navigation(8);
				reader.open_book(8);
			}

			else if(colheader.contains("validateFixedToHeightView") && headerValue.contains("Y"))
			{
				reader.category_tab_navigation(8);
				reader.open_book(8);

			}
			else if(colheader.contains("validateZoom") && headerValue.contains("Y"))
			{

				reader.category_tab_navigation(8);
				reader.open_book(8);
			}



			else if(colheader.contains("validatePenTool") && headerValue.contains("Y"))
			{
				reader.category_tab_navigation(8);
				reader.open_book(8);
			}

			else if(colheader.contains("validateStickyNotes") && headerValue.contains("Y"))
			{
				//reader.category_tab_navigation(8);
				//reader.open_book(8);
			}

		}

	}


	@AfterMethod
	public void afterMethod() throws IOException, ParseException {

		/*
		 * Closing webdriver browser
		 */

		GenericMethod.Browserquit();

	}

}
