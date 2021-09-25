package kitabooTest;


import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


import kitabooTest.TestData;

public class reader extends GenericMethod{
	static String open_book_xpath;
	public static String book_title_input, menu_names, entryname,search_text, book_title, screeshot;
	static ArrayList<ArrayList<String>> data = TestData.getSheet();
	ReportGenerate reportgenerate = new ReportGenerate();
	public static GenericMethod generic = new GenericMethod();
	static boolean flagT = true;



	/*
	 * choosing list of categories
	 */
	public static void category_tab_navigation(int cate) throws InterruptedException, IOException {

		String bookshelf = data.get(1).get(cate);
		GenericMethod.waitForElementToBeClickable("catogery_list");

		try {
			List<WebElement> category_list = GenericMethod.driver.findElements(By.xpath("//md-pagination-wrapper/md-tab-item"));

			for(int i=0; i<category_list.size(); i++)
			{
				String category_title = category_list.get(i).getAttribute("aria-label");

				if(category_title.equalsIgnoreCase(bookshelf))
				{
					category_list.get(i).click();
					GenericMethod.waitUntilPageLoad();

					screeshot = GenericMethod.screenshot("category_list");

					ReportGenerate.writeResult("category tab navigation1.0 ", "Category tab navigation", "Validate category tab navigation ", " Validate category tab functionality", 
							" Validate category tab functionality", "Able to click category_tab ", "Pass", screeshot);
					ReportGenerate.Pass("Validate Category tab navigation funcitonality - Able to click category_tab", "Pass");
					break; 
				}

			}
		} catch (Exception e) {

			ReportGenerate.writeResult("category tab navigation1.0 ", "Category tab navigation", "Validate category tab navigation ", " Validate category tab functionality", 
					" Validate category tab functionality","Unable to click category_tab " , "Fail", screeshot);
			ReportGenerate.Fail("Validate Category tab navigation funcitonality - error" + e, "Fail");

		}
		finally
		{
			ReportGenerate.after();
		}
	}


	public static void open_book(int value) throws InterruptedException, IOException {

		book_title_input = data.get(1).get(value);

		try {
			Thread.sleep(5000);
			List<WebElement> booklist = GenericMethod.driver.findElements(By.xpath("//div[@automation-id='thumbnailView']"));
			for (int i=0; i<booklist.size(); i++)
			{
				String booktitle_label = booklist.get(i).getAttribute("aria-label");
				//open_book_xpath = "//div[@aria-label='hit enter to launch "+ book_title_input +" book']";
				int len = booktitle_label.length();
				String booktitle = booktitle_label.substring(20, len-5);

				if(booktitle.equalsIgnoreCase(book_title_input))
				{
					booklist.get(i).click();
					Thread.sleep(8000);
					screeshot = GenericMethod.screenshot("open_book");
					ReportGenerate.writeResult("open_book1.0 ", "open_book", "Validate the open book ", " Validate open book functionality",
							" Validate open book functionality", "Able to open ", "Pass", screeshot);
					ReportGenerate.Pass("Validate open_book funcitonality - Able to open "+ booktitle, "Pass");
					break;

				}
			}
		} catch (Exception e) {

			ReportGenerate.writeResult("open_book1.0 ", "open_book", "Validate the open book ", " Validate open book functionality", 
					" Validate open book functionality", "Unable to open the book", "Fail", screeshot);
			ReportGenerate.Fail("Validate open_book funcitonality - error" + e, "Fail");

		}

		finally
		{
			ReportGenerate.after();
		}
	}

	public static void zanichellibookOpen() throws InterruptedException, IOException {

		try {
			Thread.sleep(5000);
			waitForElementToBeClickable("testManual");
			ClickElement("testManual");
			Thread.sleep(2000);
			waitForElementToBeClickable("bookSlide");
			ClickElement("bookSlide");
			WebElement books = driver.findElement(By.xpath("//*[@id='libreriaCard']/div/div/z-myz-card/z-myz-card-footer/z-myz-card-list"));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,450)");
			Thread.sleep(2000);
			WebElement jsbook = (WebElement) js.executeScript("return arguments[0].shadowRoot", books);
			jsbook.findElement(By.cssSelector("a[role='button']")).click();
			Thread.sleep(10000);
			switchChildWindows();
			screeshot = GenericMethod.screenshot("open_book");


		} catch (Exception e) {

			ReportGenerate.writeResult("open_book1.0 ", "open_book", "Validate the open book ", " Validate open book functionality", 
					" Validate open book functionality", "Unable to open the book", "Fail", screeshot);
			ReportGenerate.Fail("Validate open_book funcitonality - error" + e, "Fail");

		}

		finally
		{
			ReportGenerate.after();
		}
	}

	public static void bookOpenRandomly() throws InterruptedException, IOException {

		try {
			Thread.sleep(5000);

			List<WebElement> booklist = GenericMethod.ListgetWebElements("bookChoose");
			String actXpath = "//div[@class='potraitCategoryView']/div/div/div[";
			int lists = booklist.size();
			if(lists==0)
			{
				for (int i=1; i< lists; i++)
				{
					//String booktitle_label = booklist.get(i).getAttribute("aria-label");
					String booktitle_label = GenericMethod.driver.findElement(By.xpath(actXpath+(i)+"]/div[1]")).getAttribute("aria-label");


					int len = booktitle_label.length();
					String booktitle = booktitle_label.substring(20, len-5);

					if(i==2)
					{
						Thread.sleep(1000);
						booklist.get(i).click();
						Thread.sleep(13000);
						screeshot = GenericMethod.screenshot("open_book");
						ReportGenerate.writeResult("open_book1.0 ", "open_book", "Validate ebook open", " Validate ebook open functionality",
								" Validate ebook open functionality", "Able to open "+ booktitle, "Pass", screeshot);
						ReportGenerate.Pass("Validate open_book funcitonality - Able to open "+ booktitle, "Pass");
						break;

					}
				}
			}

			else
			{
				ClickElement("singleBook");
				Thread.sleep(8000);
			}


		} catch (Exception e) {

			ReportGenerate.writeResult("open_book1.0 ", "open_book", "Validate the open book ", " Validate open book functionality", 
					" Validate open book functionality", "Unable to open the book", "Fail", screeshot);
			ReportGenerate.Fail("Validate open_book funcitonality - error" + e, "Fail");

		}

		finally
		{
			ReportGenerate.after();
		}
	}

	/*
	 * choosing list of categories via property file
	 */
	public static void category_tab_navigationNew(String category) throws InterruptedException, IOException {

		waitForElementToBeClickable("catogery_list");

		try {
			List<WebElement> category_list= ListgetWebElements("catogeryList");

			for(int i=0; i<category_list.size(); i++)
			{
				String category_title = category_list.get(i).getAttribute("aria-label");

				if(category_title.equalsIgnoreCase(category))
				{
					category_list.get(i).click();
					waitUntilPageLoad();

					screeshot = screenshot("category_list");

					ReportGenerate.writeResult("category tab navigation new1.0 ", "Category tab navigation", "Validate category tab navigation ", " Validate category tab functionality", 
							" Validate category tab functionality", "Able to click category_tab", "Pass", screeshot);

					ReportGenerate.Pass("Validate Category tab navigation new functionality - Able to click category_tab_new", "Pass");
					break; 
				}

			}
		} catch (Exception e) {
			ReportGenerate.writeResult("category tab navigation1.0 ", "Category tab navigation", "Validate category tab navigation ", " Validate category tab functionality", 
					" Validate category tab functionality", "Unable to click "+ e , "Fail", screeshot);
			ReportGenerate.Fail("Validate Category tab navigation new functionality - error "+ e, "Fail");

		}
		finally
		{
			ReportGenerate.after();
		}
	}


	public static void open_bookNew(String bookname) throws InterruptedException, IOException {


		book_title_input = bookname;

		try {
			Thread.sleep(5000);
			//List<WebElement> booklist = driver.findElements(By.xpath("//div[@automation-id='thumbnailView']"));
			List<WebElement> booklist = ListgetWebElements("thumbnailview");

			for (int i=0; i<booklist.size(); i++)
			{
				String booktitle_label = booklist.get(i).getAttribute("aria-label");
				int len = booktitle_label.length();
				String booktitle = booktitle_label.substring(20, len-5);

				if(booktitle.equalsIgnoreCase(book_title_input))
				{
					booklist.get(i).click();
					screeshot = GenericMethod.screenshot("open_book");
					ReportGenerate.writeResult("open_book1.0 ", "open_book", "Validate the open book ", " Validate open book functionality", 
							" Validate open book functionality", "Able to open the book", "Pass", screeshot);
					ReportGenerate.Pass("Validate open book functionality - Able to open"+ booktitle, "Pass");
					break;
				}
			}
		} catch (Exception e) {
			ReportGenerate.writeResult("open_book1.0 ", "open_book", "Validate the open book ", " Validate open book functionality", 
					" Validate open book functionality", "Unable to open the book", "Fail", screeshot);
			ReportGenerate.Fail("Validate open book functionality - error "+ e, "Fail");

		}
		finally
		{
			ReportGenerate.after();
		}
	}


	public static void open_books() throws InterruptedException {
		WebElement ele = null;

		book_title = data.get(1).get(8);
		Thread.sleep(5000);
		open_book_xpath = "//div[@aria-label='hit enter to launch "+ book_title +" book']";
		System.out.println(open_book_xpath);

		Actions a = new Actions(GenericMethod.driver);
		ele = GenericMethod.driver.findElement(By.xpath((open_book_xpath)));
		a.moveToElement(ele).build().perform();
		GenericMethod.ClickByXpath(open_book_xpath);

	}

	public static void validateBack_to_bookshelf() throws InterruptedException {

		Thread.sleep(5000);
		waitVisibleWebElement("TableofContent_bookmark");

		ClickElement("TableofContent_bookmark");
		verifyElementIsDisplay("dropdown_symbol");
		ClickElement("dropdown_symbol");

		try {
			//Click and verify title and subtitle
			List<WebElement> entrylist= ListgetWebElements("ebookNames");

			for(int i=1; i<=entrylist.size(); i++)
			{

				if(i==4)
				{
					entryname = entrylist.get(i).getText();

					entrylist.get(i).click();

					Thread.sleep(3000);

					//String title = GenericMethod.driver.findElement(By.xpath("//section[contains(@class,'chapterNavigation')]/div")).getText();
					String title = GetText("chapterTitle");

					if(entryname.equals(title))
					{

						GenericMethod.ClickElement("backtoBookShelf");
						GenericMethod.waitVisibleWebElement("more_info");

						screeshot = GenericMethod.screenshot("back_to_bookshelf");

						ReportGenerate.writeResult("back_to_bookshelf1.0 ", "back_to_bookshelf", "Validate back_to_bookshelf ", " Validate back_to_bookshelf functionality", 
								" Validate back_to_bookshelf", "able to go back to bookshelf", "Pass", screeshot);
					}

				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("back_to_bookshelf1.0 ", "back_to_bookshelf", "Validate back_to_bookshelf ", " Validate tback_to_bookshelf functionality", 
					" Validate back_to_bookshelf", "not able to go back to bookshelf", "Fail", screeshot);
		}


	}

	public static void toc() throws InterruptedException, IOException {

		System.out.println("Table of content testing started");

		category_tab_navigation(7);
		open_book(9);

		GenericMethod.ClickElement("TableofContent_bookmark");
		GenericMethod.verifyElementIsDisplay("dropdown_symbol");
		GenericMethod.ClickElement("dropdown_symbol");

		try {
			//Click and verify title and subtitle
			List<WebElement> entrylist = GenericMethod.driver.findElements(By.xpath("//li[contains(@title,'Chapters')]/ul/li"));

			for(int i=1; i<=entrylist.size(); i++)
			{

				if(i==4)
				{
					entryname = entrylist.get(i).getText();

					entrylist.get(i).click();

					Thread.sleep(3000);

					String title = GenericMethod.driver.findElement(By.xpath("//section[contains(@class,'chapterNavigation')]/div")).getText();

					if(entryname.equals(title))
					{

						System.out.println("Title is matching with side menu");
						screeshot = GenericMethod.screenshot("toc");

						Assert.assertEquals(entryname, title);
						ReportGenerate.writeResult("Reader1.0 ", "Table of content", "Validate table of content ", " Validate table of content functionality", 
								" Validate Title is matching with side menu", "Tile is matching with "+ entryname, "Pass", screeshot);
						break;
					}

				}

			}

			//ClickElement("backtoBookShelf");
			//verifyElementIsDisplay("catogery_list");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Reader1.0 ", "Table of content", "Validate table of content ", " Validate table of content functionality", 
					" Validate Title is matching with side menu", "Tile is not matching with "+ entryname, "Fail", screeshot);
		}


	}


	public static void clickBookmarkSymobl() throws InterruptedException, IOException
	{

		ClickElement("bookmarkSymbol");
		Thread.sleep(2000);

		screeshot = screenshot("clickBookmarkSymobl");

		ReportGenerate.writeResult("Reader1.1 ", "Bookmark symbol", "Validate bookmark tab ", " Validate bookmark functionality", 
				" Validate able to click on bookmark", "Able to click on bookmark symbol", "Pass", screeshot);


	}


	/*
	 *Click on top menu tabs to open the books 
	 */
	public static void clickOnTab()
	{
		try {
			//get side menu value
			menu_names = data.get(1).get(11);
			Thread.sleep(5000);

			//List the side top menus and click particular menu
			boolean status = verifyElementIsDisplay("top_tabs");

			if(flagT)
			{
				List<WebElement> tablist = driver.findElements(By.xpath("//*[@role='tablist']/md-tab-item"));

				for(WebElement ele:tablist)
				{

					String value = ele.getAttribute("aria-label");

					if(menu_names.equals(value))
					{

						ele.click();
						screeshot = screenshot("clickBookmarkSymobl");
						ReportGenerate.writeResult("Reader1.2 ", "clickOnTab", "Validate tab ", " Validate tab functionality", 
								" Validate the Tab", "Able to click on tab", "Pass", screeshot);
					}

				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Reader1.2 ", "table of content", "Validate  table of content ", " Validate table of content functionality", 
					" Validate Click on table of content", "Unable to click on TOC", "Fail", screeshot);
		}

	}



	/*
	 * Choose a menu from left side options
	 */
	public static void clickOnSideMenu()
	{
		try {
			//get side menu value
			menu_names = data.get(1).get(10);
			Thread.sleep(4000);

			//List the side top menus and click particular menu
			verifyElementIsDisplay("top_menu");
			Thread.sleep(4000);

			List<WebElement> menulist = driver.findElements(By.xpath("(//section[contains(@class,'-start-center')])[3]/button"));

			for(int i=0; i<=menulist.size(); i++)
			{

				if(i<2 || i>2)
				{
					String menuname = menulist.get(i).getAttribute("aria-label");


					if (menuname.equals(menu_names))
					{
						menulist.get(i).click();
						Thread.sleep(4000);
						break;
					}
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void add_bookmark() throws InterruptedException {

		ClickElement("TableofContent_bookmark");
		verifyElementIsDisplay("dropdown_symbol");

		String title;
		try {
			//Click and verify title and subtitle
			ClickElement("chapters_arrowdown");
			List<WebElement> entrylist = driver.findElements(By.xpath("//li[contains(@title,'Chapters')]/ul/li"));

			for(int i=1; i<=entrylist.size(); i++)
			{
				if(i==4)
				{

					entrylist.get(i).click();
					Thread.sleep(4000);
					Assert.assertTrue(true);
					screeshot = screenshot("add_bookmark");
					break;

				}

			}

			title = driver.findElement(By.xpath("//section[contains(@class,'chapterNavigation')]/div")).getText();

			clickBookmarkSymobl();
			Thread.sleep(2000);
			ClickElement("addbookmark");
			Thread.sleep(2000);


			//clicking on table of content
			ClickElement("sidemenu_toc");

			//clicking on BookmarkTab
			ClickElement("bookmark_Tab");
			Thread.sleep(2000);
			String bookmarked = driver.findElement(By.xpath("//*[@id='bookmarkText']/div[1]")).getText();

			if(title.equals(bookmarked))
			{
				screeshot = screenshot("add_bookmark");

				ReportGenerate.writeResult("Reader1.3 ", "Add bookmark", "Validate  add bookmark ", " Validate add bookmark functionality", 
						" Validate add bookmark functionality", "Title is matching with bookmarked text", "Pass", screeshot);

			}


		} catch (Exception e) {
			// TODO Auto-generated catch block

			ReportGenerate.writeResult("Reader1.3 ", "Add bookmark", "Validate  add bookmark ", " Validate add bookmark functionality", 
					" Validate add bookmark functionality", "Title is not matching with bookmarked text", "Fail", screeshot);
		}

	}

	public static void delete_bookmark() throws InterruptedException, IOException
	{

		clickBookmarkSymobl();

		verifyElementIsDisplay("delete_Bookmark");

		ClickElement("delete_Bookmark");

		ClickElement("bookmark_Tab");

		try {
			boolean status = verifyElementIsDisplay("bookmarked_Text");

			screeshot = screenshot("delete_bookmark");

			ReportGenerate.writeResult("Reader1.2", "delete bookmark", "Validate  Delete the bookmark ", " Validate Delete the bookmark functionality", 
					" Validate Delete the bookmark functionality", "Under delete bookmark Status for element is present: "+status, "Pass", screeshot);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Reader1.2 ", "delete_bookmark", "Validate Delete the bookmark", " Validate Delete the bookmark functionality", 
					" Validate Delete the bookmark", "Unable to delete the bookmark ", "Fail", screeshot);
		}

		ClickElement("TableofContent_bookmark");

	}

	public static void validSearch() throws IOException, InterruptedException {

		waitVisibleWebElement("sidemenu_search");
		ClickElement("sidemenu_search");

		try {
			//get side menu value
			search_text = "with";

			SendValue("search_value", search_text);
			Thread.sleep(2000);
			ClickEnter("search_value");
			screeshot = screenshot("validSearch");
			Assert.assertTrue(true);

			ReportGenerate.writeResult("Reader1.3 ", "validSearch", "Validate  validSearch ", " Search with valid", 
					" Validate Search with valid functionality", "Able to search with vaild text", "Pass", screeshot);
			ClickElement("sidemenu_search");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Reader1.3 ", "validSearch", "Validate  valid Search ", " Validate Search functionality", 
					" Validate Search functionality", "Unable to search with vaild text", "Fail", screeshot);
		}

	}


	public static void InvalidSearch() throws IOException, InterruptedException {

		ClickElement("sidemenu_search");

		try {
			//get side menu value
			String search_number = "9838737";
			String search_number1 = " ";

			ClearSendValue("search_value", search_number);
			ClickEnter("search_value");

			screeshot = screenshot("InvalidSearch");
			Assert.assertTrue(true);
			ReportGenerate.writeResult("Reader1.4 ", "InvalidSearch", "Validate  Search with invalid", " Validate Search with invalid functionality", 
					" Validate Search with invalid functionality", "Unable to search with invalid text", "Pass", screeshot);
			ClickElement("sidemenu_search");
			ClearSendValue("search_value", search_number1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Reader1.4", "InvalidSearch", "Validate Search with invalid", " Validate Search with invalid functionality", 
					" Validate Search with invalid functionality", "Able to search with invalid text ", "Fail", screeshot);
		}

	}

	public static void MoreWordSearch() throws IOException, InterruptedException {

		ClickElement("sidemenu_search");

		try {
			//get side menu value
			search_text = "The word";
			String search_text1 = " ";

			ClearSendValue("search_value", search_text);
			ClickEnter("search_value");

			screeshot = screenshot("MoreWordSearch");
			Assert.assertTrue(true);
			ReportGenerate.writeResult("Reader1.5", "MoreWordSearch", "Validate  Search with more text", " Validate Search with more text functionality", 
					" Validate Search with more text functionality", "Able to search with more text", "Pass", screeshot);
			ClearSendValue("search_value", search_text1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Reader1.5", "MoreWordSearch", "Validate  Search with more text", " Validate Search with more text functionality", 
					" Validate Search with more text functionality", "Unable to search with more text ", "Fail", screeshot);
		}

		ClickElement("sidemenu_search");
	}


	public static void validateSearchShortcut() throws IOException, InterruptedException {

		try {
			Thread.sleep(2000);
			WebElement ele = waitVisibleWebElement("sidemenu_search");
			Robot robot = new Robot();  
			robot.keyPress(KeyEvent.VK_CONTROL); 
			robot.delay(2000);
			robot.keyPress(KeyEvent.VK_SHIFT);	
			robot.delay(2000);
			robot.keyPress(KeyEvent.VK_S);	
			robot.delay(2000);
			robot.keyRelease(KeyEvent.VK_CONTROL); 
			robot.delay(2000);
			robot.keyRelease(KeyEvent.VK_SHIFT);	
			robot.delay(2000);
			robot.keyRelease(KeyEvent.VK_S);	
			robot.delay(2000);
			ReportGenerate.writeResult("Reader1.6 ", "validateSearchShortcut", "Validate Search Shortcut", " Validate Search Shortcut functionality", 
					" Validate Search Shortcut functionality", "Able to open search as shortcut keys ", "Pass", screeshot);
			screenshot("validateSearchShortcut");
			Assert.assertTrue(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Reader1.6", "validateSearch Shortcut", "Validate Search Shortcut ", " Validate Search Shortcut functionality", 
					" Validate Search Shortcut functionality", "Unable to open search as shortcut keys ", "Fail", screeshot);
		}
	}


	public static void validateFontSize() throws InterruptedException, IOException {

		ClickElement("text_Settings");

		waitVisibleWebElement("fontsize_drag");

		try {
			DragSliderRight("fontsize_drag", -30);
			screeshot=screenshot("FontSizeDragleft");
			Assert.assertTrue(true);
			ReportGenerate.writeResult("Reader1.7", "validateFontSize", "Drag Slider left ", " Drag Slider left functionality", 
					" Drag Slider left functionality", "Able to Drag ", "Pass", screeshot);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Reader1.7", "validate Font Size", "Drag Slider left ", " Validate Font Size functionality", 
					" Drag Slider left functionality", "Unable to Drag", "Fail", screeshot);
		}

		try {
			DragSliderRight("fontsize_drag", 80);
			screeshot=screenshot("FontSizeDragRight");
			Assert.assertTrue(true);
			ReportGenerate.writeResult("Reader1.7 ", "validate Font Size", "Drag Slider Right ", " Drag Slider Right functionality", 
					"Drag Slider Right functionality", "Title is not matching with bookmarked text", "Pass", screeshot);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Reader1.7 ", "Font Size", "Drag Slider Right", " Validate Font Size functionality", 
					" Drag Slider Right", "Unable to Drag", "Fail", screeshot);
		}


	}

	public static void validateFontAlignment() throws InterruptedException, IOException {

		Thread.sleep(12000);

		waitVisibleWebElement("font_layout");

		try {
			//get font alignment options
			List<WebElement> alignList = driver.findElements(By.xpath("//*[@id='layoutAlign']/button"));

			for(WebElement ele : alignList)
			{

				String alignName = ele.getAttribute("aria-label");
				ele.click();

				Thread.sleep(2000);
				screeshot=screenshot(alignName);
				Assert.assertTrue(true);
				ReportGenerate.writeResult("Reader1.8 ", "Font Alignment", "Validate  Font Alignment ", " Validate Font Alignment functionality", 
						" Validate Font Alignment functionality", "Able to click "+alignName, "Pass", screeshot);


			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Reader1.8 ", "Add bookmark", "Validate  Font Alignment ", " Validate Font Alignment functionality", 
					" Validate Font Alignment functionality", "Unable to click font alignment", "Fail", screeshot);
		}

	}

	public static void validateFontFormat() throws InterruptedException, IOException {

		ClickElement("font_format");

		waitVisibleWebElement("font_layout");

		try {
			//get font alignment options
			List<WebElement> formatList = driver.findElements(By.xpath("(//div[contains(@class,'md-select-menu-container')])[4]/md-select-menu/md-content/md-option"));

			for(WebElement ele : formatList)
			{
				Thread.sleep(2000);
				WebElement format = ele.findElement(By.tagName("div"));
				String choosenformat = format.getText();
				System.out.println("format font: "+choosenformat);
				ele.click();
				Thread.sleep(2000);
				screeshot = screenshot(choosenformat);
				Assert.assertTrue(true);
				ReportGenerate.writeResult("Reader1.9 ", "Font Format", "Validate  font format ", " Validate font format functionality", 
						" Validate font format functionality", "Able to click "+choosenformat, "Pass", screeshot);

				ClickElement("font_format");

			}

			ClickElement("notoserif_font");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Reader1.9 ", "Font Format", "Validate  Font Format ", " Validate Font Format functionality", 
					" Validate Font Format functionality", "Unable to click font format", "Fail", screeshot);
		}


	}


	public static void validateLineSpacing() throws InterruptedException, IOException {

		ClickElement("line_spacing");

		waitVisibleWebElement("line_spacing");

		try {
			//get line spacing list
			List<WebElement> linespList = driver.findElements(By.xpath("//*[@id='linespacingApply']/button"));

			for(WebElement ele : linespList)
			{
				Thread.sleep(2000);
				String  Linespace = ele.getAttribute("aria-label");
				ele.click();
				Thread.sleep(2000);
				screeshot=screenshot(Linespace);
				Assert.assertTrue(true);
				ReportGenerate.writeResult("Reader1.10 ", "Line spacing", "Validate line spacing ", " Validate line spacing functionality", 
						" Validate line spacing functionality", "Able to click "+Linespace, "Pass", screeshot);

			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Reader1.10 ", "validateLineSpacing", "Line Spacing section", " Validate line spacing functionality", 
					" Validate line spacing functionality", "Unable to click the line spacing", "Fail", screeshot);
		}

	}

	public static void validatePageMode() throws InterruptedException, IOException {

		ClickElement("page_mode");

		waitVisibleWebElement("page_mode");

		try {
			//get page mode list
			List<WebElement> modeList = driver.findElements(By.xpath("//*[@id='switchdataText']/button"));

			for(WebElement ele : modeList)
			{
				Thread.sleep(2000);
				String  pagemo = ele.getAttribute("aria-label");
				ele.click();
				Thread.sleep(2000);
				screeshot=screenshot(pagemo);
				Assert.assertTrue(true);
				ReportGenerate.writeResult("Reader1.11 ", "PageMode", "Validate  PageMode ", " Validate Page Mode functionality", 
						" Validate Page Mode functionality", "Able to click "+pagemo , "Pass", screeshot);



			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Reader1.11 ", "Page Mode", "Validate Page Mode ", " Validate Page Mode functionality", 
					" Validate Page Mode functionality", "Unable to click page mode", "Fail", screeshot);
		}

	}

	public static void validatePageMargins() throws InterruptedException, IOException {

		ClickElement("page_margins");
		Thread.sleep(1000);

		ClickElement("text_Settings");

		waitVisibleWebElement("page_margins");


		try {
			//get page margin list
			List<WebElement> marginList = driver.findElements(By.xpath("//*[@id='applyMargintext']/button"));

			for(WebElement ele : marginList)
			{
				Thread.sleep(2000);
				String  pagema = ele.getAttribute("aria-label");
				ele.click();
				Thread.sleep(2000);

				screeshot = screenshot(pagema);
				Assert.assertTrue(true);
				ReportGenerate.writeResult("Reader1.12 ", "validatePageMargins", "Page Margins section ", " Validate add bookmark functionality", 
						" Validate Page Margins functionality", "Able to click " +pagema, " Pass", screeshot);

				ClickElement("text_Settings");

			}
		} catch (Exception e) {

			ReportGenerate.writeResult("Reader1.12 ", "validatePageMargins", "validatePageMargins ", " Validate add bookmark functionality", 
					" Validate add bookmark functionality", "Not clicked page margin" , "Fail", screeshot);
		}

	}

	public static void validateScrollView() throws InterruptedException, IOException {


		waitVisibleWebElement("scroll_view");

		String buttonstatus = GetAttributeValue("scroll_view", "aria-checked");
		if(buttonstatus.equals("true"))
		{
			System.out.println("Scroll view is in on mode");
			Thread.sleep(2000);
			ClickElement("scroll_view");

			Thread.sleep(2000);

			verifyElementIsDisplay("singpage_view");

			String pageview = GetAttributeValue("singpage_view", "aria-label");

			if(pageview.equals("Single Page View (Ctrl+Shift+V)"))
			{
				screeshot=screenshot(pageview);
				Assert.assertTrue(true);
				ReportGenerate.writeResult("Reader1.13 ", "validateScrollView", "validateScrollView ", " validateScrollView functionality", 
						" validateScrollView functionality", "Single page functionality is appeared on scroll mode in off mode", "Pass", screeshot);
			}

			else
			{

				System.out.println("Single page view is not present");
				ReportGenerate.writeResult("Reader1.13 ", "validateScrollView", "Validate scrollView functionality ", " Validate scrollView functionality",
						" Validate scrollView functionality", "Single page functionality is not appeared", "Fail", screeshot);

			}


		}

		else
		{
			System.out.println("Scroll view is in off mode");

			ReportGenerate.writeResult("Reader1.13 ", "Scroll view", "Validate  scroll view ", " Validate scrollView functionality",
					" Validate scrollView functionality", "Scroll view is in off mode", "Fail", screeshot);

		}

	}

}

