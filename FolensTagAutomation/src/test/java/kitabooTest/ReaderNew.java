package kitabooTest;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class ReaderNew extends GenericMethod{
	static String open_book_xpath;
	public static String book_title_input, menu_names, entryname,search_text, book_title, screeshot, line,xml_link, isbn, bookTitle, categoryBook, basicxpath, actualxpath, listxpath;
	static ArrayList<ArrayList<String>> data = TestData.getSheet();
	ReportGenerate reportgenerate = new ReportGenerate();
	public static GenericMethod generic = new GenericMethod();
	public static WebElement element;
	static boolean flagT = true;




	public static void tagsValidation() throws InterruptedException, IOException {

		try {
			waitForElementToBeClickable("assignmentDropDown");
			ClickElement("assignmentDropDown");
			Thread.sleep(1000);

			waitForElementToBeClickable("studentTest");
			ClickElement("studentTest");
			Thread.sleep(2000);

			waitForElementToBeClickable("newStandardTest");

			waitForElementToBeClickable("addQuestionsContainer");

			List<WebElement> containers = ListgetWebElements("addQuestionsContainer");
			Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\AutoIT\\AutoITScrollUp.exe");
			Thread.sleep(35000);
			containers = ListgetWebElements("addQuestionsContainer");
			String addquestContainer = "//div[@class='item-container']/div[2]/div/div[";

			for(int i=17;i<=containers.size();i++)
			{
				
				if(i>1)
				{
					Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\AutoIT\\AutoITScrollUp.exe");
					Thread.sleep(35000);
					WebElement assenmentName = driver.findElement(By.xpath(addquestContainer+(i)+"]/div/div/div[1]/p"));
					String asstName= assenmentName.getText();
					System.out.println("Assessment name is: "+asstName);

					driver.findElement(By.xpath(addquestContainer+(i)+"]/div/div/div[2]/div[2]")).click();
					Thread.sleep(1000);

					driver.findElement(By.xpath(addquestContainer+(i)+"]/div/div/div[2]/div[3]/div/div[5]")).click();

					confirmationMessage();
					Thread.sleep(1000);
					waitForElementToBeClickable("cancelQuestion");

					List<WebElement> questlist = ListgetWebElements("questionsList");
					String edittests = "//div[contains(@class,'ui-sortable')]/div[";

					for(int j=1;j<=questlist.size();j++)
					{
						JavascriptExecutor js = (JavascriptExecutor) GenericMethod.driver;
						WebElement questEditSymb = GenericMethod.driver.findElement(By.xpath(edittests+(j)+"]/div[2]/div/div[3]/div/div/div/div/img"));
						js.executeScript("window.scrollBy(0,550)");
						questEditSymb.click();
						Thread.sleep(1000);
						WebElement qEdit = GenericMethod.driver.findElement(By.xpath(edittests+(j)+"]/div[2]/div/div[3]//div[text()='Edit']"));
						qEdit.click();
						Thread.sleep(3000);
						//confirmationMessage();
						try {
							waitForElementToBeClickable("confirmatEdit");
							ClickElement("confirmatEdit");
						} catch (Exception e1) {
							ReportGenerate.writeResult("Tag Validations", "Folens hive", "Verify tags in standard test", " ",
									" Test title: "+asstName,  "Error on confirmation yes: "+e1, "Fail", screeshot);
							ReportGenerate.Fail("Verify tags on standard test - error on confirmation yes "+ e1, "Fail");

						}
						waitForElementToBeClickable("editQuestion");
						ClickElement("editQuestion");
						Thread.sleep(1000);
						waitForElementToBeClickable("questionTags");
						List<WebElement> taglist = driver.findElements(By.xpath("//div[contains(@class,'assmntSetMain')]/div"));
						String basicXpath = "//div[contains(@class,'assmntSetMain')]/div[";

						for(int x=1; x<=taglist.size();x++)
						{
							if(x>4)
							{
								Thread.sleep(1000);
								String tagname1;
								String tagname2;
								String tagname3;
								try {
									WebElement tags = GenericMethod.driver.findElement(By.xpath(basicXpath+(x)+"]/div[1]/span[2]"));
									tagname1 = tags.getText();
									System.out.println("Title name: "+asstName+" tagname: "+tagname1);
									WebElement tags1 = GenericMethod.driver.findElement(By.xpath(basicXpath+(x)+"]/div[2]/span[2]"));
									tagname2 = tags1.getText();
									System.out.println("Title name: "+asstName+" tagname2: "+tagname2);

									WebElement tag2 = GenericMethod.driver.findElement(By.xpath(basicXpath+(x)+"]/div[3]/span[2]"));

									tagname3 = tag2.getText();
									screeshot = screenshot("tagsValidation");
									
									ReportGenerate.writeResult("Tag Validations", "Folens hive", "Verify tags in standard test", " ",
											" Test title: "+asstName,  ","+ tagname1 +" , "+tagname2 +" , "+ tagname3 , "Pass", screeshot);
									ReportGenerate.Pass("Validate standard test functionality - Test title: " +asstName+ " "+tagname1 +" and: "+tagname2 +" and: "+tagname3, "Pass");
								} catch (Exception e) {
									ReportGenerate.writeResult("Tag Validations", "Folens hive", "Verify tags in standard test", " ",
											" Test title: "+asstName,  "Error on getting tags: "+e, "Fail", screeshot);
									ReportGenerate.Fail("Verify tags on standard test - error on getting tags "+ e, "Fail");

								}

							}
							else
							{
								System.out.println("Tags are not present");
							}
						}

						ClickElement("questionsBack");
						Thread.sleep(8000);
					}
					ClickElement("mainQuestionBack");
					Thread.sleep(6000);
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Folens hive tags ", " ", " ", " ",
					" Validate standard test subjects", "Unable to get the tags: "+e , "Fail", screeshot);
			ReportGenerate.Fail("Verify standard test functionality - error "+ e, "Fail");
		}

		finally
		{
			ReportGenerate.after();
		}

	}

	public static void confirmationMessage() throws InterruptedException, IOException
	{
		try {
			waitForElementToBeClickable("confirmationYes");
			ClickElement("confirmationYes");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			waitForElementToBeClickable("anotherConfirYes");
			ClickElement("anotherConfirYes");
			ReportGenerate.writeResult("Tag Validations", "Folens hive", "Verify tags in standard test", " ",
					"common question confirmation yes",  "Error on confirmation yes: "+e, "Fail", screeshot);
			ReportGenerate.Fail("Verify tags on standard test - error on confirmation yes "+ e, "Fail");
		}
		Thread.sleep(5000);
	}

	public static void validatePageMode() throws InterruptedException, IOException {

		GenericMethod.ClickElement("page_mode");

		GenericMethod.waitVisibleWebElement("page_mode");

		try {
			//get page mode list
			List<WebElement> modeList = GenericMethod.driver.findElements(By.xpath("//*[@id='switchdataText']/button"));

			for(WebElement ele : modeList)
			{
				Thread.sleep(2000);
				String  pagemo = ele.getAttribute("aria-label");
				ele.click();
				Thread.sleep(2000);
				screeshot=screenshot(pagemo);
				ReportGenerate.writeResult("Reader1.11 ", "PageMode", "Validate  PageMode ", " Validate Page Mode functionality",
						" Validate Page Mode functionality", "Able to click "+pagemo , "Pass", screeshot);
				ReportGenerate.Pass("Validate Page Mode functionality - Able to click "+ pagemo, "Pass");

			}

			/*ClickElement("backtoBookShelf");
			verifyElementIsDisplay("catogery_list");
			Thread.sleep(2000);*/

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Reader1.11 ", "Page Mode", "Validate Page Mode ", " Validate Page Mode functionality",
					" Validate Page Mode functionality", "Unable to click page mode "+ e, "Fail", screeshot);
			ReportGenerate.Fail("Validate Page Mode functionality - error: " +e, "Fail");
		}
		finally
		{
			ReportGenerate.after();
		}

	}

	public static void validatePageMargins() throws InterruptedException, IOException {

		GenericMethod.ClickElement("page_margins");
		Thread.sleep(1000);

		GenericMethod.ClickElement("text_Settings");

		GenericMethod.waitVisibleWebElement("page_margins");


		try {
			//get page margin list
			List<WebElement> marginList = GenericMethod.driver.findElements(By.xpath("//*[@id='applyMargintext']/button"));

			for(WebElement ele : marginList)
			{
				Thread.sleep(2000);
				String  pagema = ele.getAttribute("aria-label");
				ele.click();
				Thread.sleep(2000);

				screeshot = screenshot(pagema);
				ReportGenerate.writeResult("Reader1.12 ", "validatePageMargins", "Page Margins section ", " Validate Page Margins functionality",
						" Validate Page Margins functionality", "Able to click " +pagema, " Pass", screeshot);
				ReportGenerate.Pass("Validate Page Margins functionality - Able to click " +pagema, "Pass");

				ClickElement("text_Settings");

			}

			/*ClickElement("backtoBookShelf");
			verifyElementIsDisplay("catogery_list");
			Thread.sleep(2000);*/

		} catch (Exception e) {
			ReportGenerate.writeResult("Reader1.12 ", "validatePageMargins", "validatePageMargins ", " Validate Page Margins functionality",
					" Validate Page Margins functionality", "Not clicked page margin" , "Fail", screeshot);
			ReportGenerate.Fail("Validate Page Margins functionality error: " + e, "Fail");
		}
		finally
		{
			ReportGenerate.after();
		}

	}


	public static void validateFilterHighlight() throws IOException, InterruptedException
	{

		try {
			waitUntilPageLoad();
			Thread.sleep(12000);
			waitForElementToBeClickable("MyData");
			ClickElement("MyData");

			Thread.sleep(1000);

			waitForElementToBeClickable("myDataFilter");
			ClickElement("myDataFilter");

			waitForElementToBeClickable("Highlight");
			ClickElement("Highlight");

			Thread.sleep(1000);
			screeshot = screenshot("myDataFilter");
			ReportGenerate.writeResult("My data Filter ", "Validate my data filter for highlight", "Validate my data filter for highlight", "My data filter for highlight",		
					" Validate Page Margins functionality", "Able to view the highlight list on filter", " Pass", screeshot);
			ReportGenerate.Pass("Validate Page Margins functionality - Able to view the highlight list on filter", "Pass");

		} catch (Exception e) {
			ReportGenerate.writeResult("My data Filter ", "Validate my data filter for highlight", "Validate my data filter for highlight", "My data filter for highlight",		
					" Validate Page Margins functionality", "Unable to view the highlight list on filter", "Fail", screeshot);
			ReportGenerate.Fail("Unable to view the highlight list in filter: " + e, "Fail");
		}

		try {
			waitForElementToBeClickable("highlightFilterAll");
			ClickElement("highlightFilterAll");
			Thread.sleep(1000);
			screeshot = screenshot("highlightFilterAll");
			ReportGenerate.writeResult("My data Filter ", "Validate All filter for highlight", "Validate All filter for highlight", "Validate All filter for highlight",		
					" Validate All filter for highlight", "Able to unfilter the highlight", " Pass", screeshot);
			ReportGenerate.Pass("Validate Page Margins functionality - Able to unfilter the highlight", "Pass");

			ClickElement("backtoBookShelf");
			verifyElementIsDisplay("catogery_list");
			Thread.sleep(2000);

		} catch (Exception e) {
			ReportGenerate.writeResult("My data Filter ", "Validate All filter for highlight", "Validate All filter for highlight", "Validate All filter for highlight",		
					" Validate All filter for highlight", "Unable to unfilter the highlight "+ e , "Fail", screeshot);
			ReportGenerate.Fail("Unable to unfilter all the highlight: " + e, "Fail");
		}


		finally
		{
			ReportGenerate.after();
		}

	}

	public static void validateScrollView() throws InterruptedException, IOException {

		GenericMethod.waitVisibleWebElement("scroll_view");

		String buttonstatus = GetAttributeValue("scroll_view", "aria-checked");
		if(buttonstatus.equals("true"))
		{
			//System.out.println("Scroll view is in on mode");
			Thread.sleep(2000);
			GenericMethod.ClickElement("scroll_view");

			Thread.sleep(2000);

			GenericMethod.verifyElementIsDisplay("singpage_view");

			String pageview = GetAttributeValue("singpage_view", "aria-label");

			if(pageview.equals("Single Page View (Ctrl+Shift+V)"))
			{
				screeshot=screenshot(pageview);
				ReportGenerate.writeResult("Reader1.13 ", "validateScrollView", "validateScrollView ", " validateScrollView functionality",
						" validateScrollView functionality", "Single page functionality is appeared on scroll mode in off mode", "Pass", screeshot);
				ReportGenerate.Pass("Validate Scroll view functionality", "Pass");
			}

			else
			{
				ReportGenerate.writeResult("Reader1.13 ", "validateScrollView", "Validate scrollView functionality ", " Validate scrollView functionality",
						" Validate scrollView functionality", "Single page functionality is not appeared", "Fail", screeshot);
				ReportGenerate.Fail("Validate Scroll view functionality", "Fail");

			}

		}

		else
		{
			ReportGenerate.writeResult("Reader1.13 ", "Scroll view", "Validate  scroll view ", " Validate scrollView functionality",
					" Validate scrollView functionality", "Scroll view is in off mode", "Fail", screeshot);
			ReportGenerate.Fail("Validate Scroll view functionality", "Fail");

			ReportGenerate.after();

		}

		ClickElement("backtoBookShelf");
		verifyElementIsDisplay("catogery_list");
		Thread.sleep(2000);

	}

}
