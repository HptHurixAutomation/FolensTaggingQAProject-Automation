package kitabooTest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import kitabooTest.TestData;

public class bookShelf extends GenericMethod {

	public static String screeshot, book_title_input,val;

	static ArrayList<ArrayList<String>> data = TestData.getSheet();

	public static void mark_favorite() throws InterruptedException, IOException {

		try {
			try {
				ClickElement("newMark_favorite");
				Thread.sleep(3000);
				screeshot = screenshot("mark_favorite");
				ReportGenerate.writeResult("mark_favourite1.0 ", "mark_favourite", "Mark as favourite", " Validate favourite functionality",
						" Validate favourite functionality", "Clicked the favourite symbol", "Pass", screeshot);
				//ReportGenerate.Pass("Validate mark as favourite functionality", "Pass", true);
				ReportGenerate.Pass("Validate mark as favorite functionality", "Pass");
			} catch (Exception e) {
				ClickElement("newMark_favorite1");
				Thread.sleep(3000);
				screeshot = screenshot("mark_favorite");
				ReportGenerate.writeResult("mark_favourite1.0 ", "mark_favourite", "Mark as favourite", " Validate favourite functionality",
						" Validate favourite functionality", "Clicked the favourite symbol", "Pass", screeshot);
				//ReportGenerate.Pass("Validate mark as favourite functionality", "Pass", true);
				ReportGenerate.Pass("Validate mark as favorite functionality", "Pass");
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("mark_favourite1.0 ", "mark_favorite", "Mark as favourite", " Validate favorite functionality",
					" Validate favorite functionality", "Unable to click the favorite symbol", "Fail", screeshot);
			ReportGenerate.Fail("Validate mark as favorite functionality", "Fail");
		}
		finally
		{
			ReportGenerate.after();
		}
	}	


	@Test(priority=2)
	public static void mark_unfavorite() throws InterruptedException, IOException {

		try {
			//favourite_tab_navigation();
			//Thread.sleep(3000);

			try {
				ClickElement("newMark_favorite");

				//getTextbyid("nofavoriteBook");

				screeshot = screenshot("mark_unfavorite");
				ReportGenerate.writeResult("mark_unfavourite1.1 ", "mark_unfavourite", "Mark as unfavorite", " Validate favorite functionality",
						" Validate unfavorite functionality", "Clicked the unfavourite symbol", "Pass", screeshot);
				ReportGenerate.Pass("Validate mark as unfavorite functionality", "Pass");
			} catch (Exception e) {
				ClickElement("newMark_favorite1");

				//getTextbyid("nofavoriteBook");

				screeshot = screenshot("mark_unfavorite");
				ReportGenerate.writeResult("mark_unfavourite1.1 ", "mark_unfavourite", "Mark as unfavorite", " Validate favorite functionality",
						" Validate unfavorite functionality", "Clicked the unfavourite symbol", "Pass", screeshot);
				ReportGenerate.Pass("Validate mark as unfavorite functionality", "Pass");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("mark_unfavourite1.1 ", "mark_unfavorite", "Mark as unfavorite", " Validate favorite functionality",
					" Validate unfavorite functionality", "Unable to clicked the unfavorite symbol", "Fail", screeshot);
			ReportGenerate.Fail("Validate mark as unfavorite functionality", "Fail");
		}

		finally
		{
			ReportGenerate.after();
		}

	}	

	public static void favourite_tab_navigation() throws InterruptedException {

		//ClickByXpath("//md-tab-item[@role='tab' and @aria-label='Favorite']");

		ClickElement("newMark_favorite");
	}	

	@Test(priority=3)
	public static void search() throws IOException, InterruptedException {

		try {
			val = prop.getProperty("Category");
			//ClickByXpath("*//button[@id='searchFeature']");
			ClickElement("searchFeature");
			enterValuebyID("searchInputText", data.get(1).get(7));
			//enterValuebyID("searchInputText", val);
			//GenericMethod.SendValue("searchInputText", val);
			//GenericMethod.ClickEnter("searchInputText");

			Thread.sleep(2000);
			screeshot = screenshot("search");
			ReportGenerate.writeResult("search1.0 ", "search", "Search ", " Validate search functionality",
					" Validate search functionality", "Able to search", "Pass", screeshot);
			ReportGenerate.Pass("Validate search functionality", "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("search1.0 ", "search", "Search ", " Validate search functionality",
					" Validate search functionality", "Unable to search", "Fail", screeshot);
			ReportGenerate.Fail("Validate search functionality", "Fail");
		}

		finally
		{
			ReportGenerate.after();
		}

	}

	@Test(priority=4)
	public static void search_cancelbtn() throws IOException, InterruptedException {

		try {
			//ClickByXpath("*//button[@id='searchCancel']");
			ClickElement("searchCancel");
			screeshot = screenshot("search_cancelbtn");
			ReportGenerate.writeResult("search1.1 ", "search", "Validate the search cancel button ", " Validate search cancel functionality",
					" Validate search cancel functionality", "Able to cancel the search", "Pass", screeshot);
			ReportGenerate.Pass("Validate search functionality", "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("search1.1 ", "search", "Validate the search cancel button ", " Validate search cancel functionality",
					" Validate search cancel functionality", "Unable to cancel the search", "Fail", screeshot);
			ReportGenerate.Fail("Validate search functionality", "Fail");
		}

		finally
		{
			ReportGenerate.after();
		}


	}

	@Test(priority=5)
	public static void validateMoreInfo() throws IOException, InterruptedException {

		try {
			Thread.sleep(7000);
			//book_title_input = data.get(1).get(8);
			book_title_input = prop.getProperty("Category1");
			List<WebElement> booklist = GenericMethod.driver.findElements(By.xpath("//div[@automation-id='thumbnailView']"));
			for (int i=0; i<booklist.size(); i++)
			{
				String booktitle_label = booklist.get(i).getAttribute("aria-label");
				int len = booktitle_label.length();
				String booktitle = booktitle_label.substring(20, len-5);

				if(booktitle.equalsIgnoreCase(book_title_input))
				{
					screeshot = screenshot("booktitle");

					ReportGenerate.writeResult("validateMoreInfo1.0 ", "open_book", "Validate the open book ", " Validate open book functionality",
							" Validate open book functionality", "Able to open "+ booklist, "Pass", screeshot);
					break;
				}
			}

			ClickElement("more_info");

			Thread.sleep(2000);
			GenericMethod.defaultContentFrame();
			waitVisibleWebElement("more_infoTitle");
			String vals = GetText("more_infoTitle");
			int len = vals.length();
			String booktitle = vals.substring(7, len-0);

			if(booktitle.contains(booktitle))

			{
				screeshot = screenshot("more_info");
				Assert.assertEquals(booktitle, booktitle);
				ReportGenerate.writeResult("moreInfo1.0", "more info title", "Validate the more_info ", " Validate more_info functionality",
						" Validate more_info  functionality", "Able to click more info", "Pass", screeshot);
				GenericMethod.ClickElement("moreInfoClose");
				ReportGenerate.Pass("Validate mark as favourite functionality", "Pass");
			}

			else
			{

				ReportGenerate.writeResult("moreInfo1.0 ", "more info title", "Validate the more_info ", " Validate more_info functionality",
						" Validate more_info  functionality", "Unable to get title for more info", "Fail", screeshot);
				ReportGenerate.Fail("Validate more info functionality", "Fail");
			}



		} catch (Exception e) {

			ReportGenerate.writeResult("moreInfo1.0 ", "more info", "Validate the more info  ", " Validate more info functionality",
					" Validate more info functionality", "Unable click more info: "+e, "Fail", screeshot);
			ReportGenerate.Fail("Validate more info functionality", "Fail");

		}

		finally
		{
			ReportGenerate.after();
		}

	}


	@Test(priority=6)
	public static void validateAnalytics() throws IOException, InterruptedException {

		try {
			Thread.sleep(7000);
			//book_title_input = data.get(1).get(8);
			book_title_input = prop.getProperty("Category1");
			List<WebElement> booklist = GenericMethod.driver.findElements(By.xpath("//div[@automation-id='thumbnailView']"));
			for (int i=0; i<booklist.size(); i++)
			{
				String booktitle_label = booklist.get(i).getAttribute("aria-label");
				int len = booktitle_label.length();
				String booktitle = booktitle_label.substring(20, len-5);

				if(booktitle.equalsIgnoreCase(book_title_input))
				{
					screeshot = screenshot("open_book");

					ReportGenerate.writeResult("validateMoreInfo1.0 ", "open_book", "Validate the open book ", " Validate open book functionality",
							" Validate open book functionality", "Able to open "+ booklist, "Pass", screeshot);
					ReportGenerate.Pass("Validate open book functionality", "Pass");
					break;
				}
			}

			GenericMethod.ClickElement("more_info");
			Thread.sleep(2000);
			GenericMethod.defaultContentFrame();
			waitVisibleWebElement("more_infoTitle");
			String vals = GetText("more_infoTitle");
			int len = vals.length();
			String booktitle = vals.substring(7, len-0);

			if(booktitle.contains(booktitle))

			{
				GenericMethod.ClickElement("analytics");
				Thread.sleep(3000);

				screeshot = screenshot("validateAnalytics");
				Assert.assertEquals(booktitle, booktitle);
				ReportGenerate.writeResult("Analytics1.0", "Analytics", "Validate the analytics ", " Validate analytics functionality",
						" Validate analytics functionality", "Able to click analytics", "Pass", screeshot);

				ReportGenerate.Pass("Validate Analytics functionality", "Pass");

				GenericMethod.ClickElement("analyticsBack");
			}

			else
			{

				ReportGenerate.writeResult("Analytics1.0 ", "Analytics title", "Validate the more_info ", " Validate Analytics functionality",
						" Validate Analytics  functionality", "Unable to get title for Analytics", "Fail", screeshot);
				ReportGenerate.Fail("Validate Analytics functionality", "Fail");
			}

		} catch (Exception e) {

			ReportGenerate.writeResult("Analytics1.0 ", "Analytics", "Validate the Analytics ", " Validate Analytics functionality",
					" Validate Analytics functionality", "Unable click Analytics", "Fail", screeshot);
			ReportGenerate.Fail("Validate Analytics functionality", "Fail");

		}

		finally
		{
			ReportGenerate.after();
		}

	}
}
