package kitabooTest;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

public class FixedLayout extends GenericMethod{
	static String open_book_xpath;
	public static String book_title_input, menu_names, entryname,search_text, book_title, screeshot, browser;
	static ArrayList<ArrayList<String>> data = TestData.getSheet();
	ReportGenerate reportgenerate = new ReportGenerate();
	static boolean flagT = true;

	static String stickyValue ="Glassware such as beakers, conical flasks, test-tubes and watch-glasses allows you to mix and heat chemicals. most glassware in the labortory is made of Pyrex ";

	public static void validateThumbnail() throws InterruptedException, IOException
	{

		//open_book();
		Thread.sleep(14000);

		try {
			ClickElement("Thumbnail");
			Thread.sleep(5000);

			waitForElementToBeClickable("ThumbnailHistoryInput");

			screeshot = screenshot("Thumbnail");
			ReportGenerate.writeResult("validateThumbnail1.0 ", "validateThumbnail", "Validate Thumbnail ", " Validate Thumbnail functionality", 
					" Validate Thumbnail functionality", "Able to click Thumbnail", "Pass", screeshot);
			ReportGenerate.Pass("Validate Thumbnail functionality - Able to click Thumbnail", "Pass");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("validateThumbnail1.0 ", "validateThumbnail", "Validate Thumbnail ", " Validate Thumbnail functionality", 
					" Validate Thumbnail functionality", "Unable to click Thumbnail"+ e, "Fail", screeshot);
			ReportGenerate.Fail("Validate Thumbnail functionality - Unable to click Thumbnail"+ e, "Fail");
		}

		try {
			ClickElement("ThumbnailHistoryInput");

			Thread.sleep(2000);

			SendValue("ThumbnailHistoryInput", "5");

			ClickEnter("ThumbnailHistoryInput");

			Thread.sleep(5000);

			screeshot = screenshot("ThumbnailHistoryInput");
			ReportGenerate.writeResult("validateThumbnailHistoryInput1.0 ", "validateThumbnailHistoryInput", "Validate Thumbnail History Input ", " Validate ThumbnailHistory functionality", 
					" Validate Thumbnail history input functionality", "Able to enter Thumbnail history input as page no. 5", "Pass", screeshot);
			ReportGenerate.Pass("Validate Thumbnail History Input functionality - Able to view page no. 5" , "Pass");

			ClickElement("Thumbnail");
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("validateThumbnailHistoryInput1.0 ", "validateThumbnailHistoryInput", "Validate Thumbnail History Input ", " Validate ThumbnailHistory functionality", 
					" Validate Thumbnail history input functionality", "Unable to enter Thumbnail history input "+ e, "Fail", screeshot);
			ReportGenerate.Fail("Validate Thumbnail History Input functionality - Unable to enter Thumbnail history input "+ e, "Fail");
		}


		try {
			ClickElement("ThumbnailHistoryInput");

			Thread.sleep(4000);

			SendValue("ThumbnailHistoryInput", "1");

			ClickEnter("ThumbnailHistoryInput");

			Thread.sleep(2000);

			screeshot = screenshot("ThumbnailHistoryInput");
			ReportGenerate.writeResult("validateThumbnailHistoryInput1.0 ", "validateThumbnailHistoryInput", "Validate Thumbnail History Input ", " Validate ThumbnailHistory functionality", 
					" Validate Thumbnail history input functionality", "Able to enter and redirect to page no. 1", "Pass", screeshot);
			ReportGenerate.Pass("Validate Thumbnail History Input functionality - Able to enter and redirect to page no. 1", "Pass");

			ClickElement("Thumbnail");
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("validateThumbnailHistoryInput1.0 ", "validateThumbnailHistoryInput", "Validate Thumbnail History Input ", " Validate ThumbnailHistory functionality", 
					" Validate Thumbnail history input functionality", "Unable to enter Thumbnail history input "+ e, "Fail", screeshot);
			ReportGenerate.Fail("Validate Thumbnail History Input functionality - Unable to enter Thumbnail history input "+ e, "Fail");
		}

		try {
			ClickElement("ThumbnailHistoryInput");

			Thread.sleep(2000);

			SendValue("ThumbnailHistoryInput", "4");

			ClickEnter("ThumbnailHistoryInput");

			Thread.sleep(4000);

			screeshot = screenshot("ThumbnailHistoryInput");
			ReportGenerate.writeResult("validateThumbnailHistoryInput1.0 ", "validateThumbnailHistoryInput", "Validate Thumbnail History Input ", " Validate ThumbnailHistory functionality", 
					" Validate Thumbnail history input functionality", "Able to enter and redirect to page no. 4", "Pass", screeshot);
			ReportGenerate.Pass("Validate Thumbnail History Input functionality - Able to enter and redirect to page no. 4", "Pass");

			ClickElement("Thumbnail");
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("validateThumbnailHistoryInput1.0 ", "validateThumbnailHistoryInput", "Validate Thumbnail History Input ", " Validate ThumbnailHistory functionality", 
					" Validate Thumbnail history input functionality", "Unable to enter Thumbnail history input "+ e, "Fail", screeshot);
			ReportGenerate.Fail("Validate Thumbnail history input functionality - Unable to enter Thumbnail history input "+ e, "Fail");
		}

		try {
			try {
				ClickElement("ThumbnailhistoryPrevious");
			} catch (Exception e) {
				ClickElement("historyPrevious1");
			}

			Thread.sleep(3000);

			screeshot = screenshot("ThumbnailhistoryPrevious");
			ReportGenerate.writeResult("Thumbnail history Previous1.0 ", "Thumbnail history Previous", "Thumbnail history Previous ", " Validate Thumbnail history Previous functionality", 
					" Validate Thumbnail history Previous functionality", "Able to click Thumbnail history Previous input", "Pass", screeshot);
			ReportGenerate.Pass("Validate history Previous functionality - Able to click Thumbnail history Previous input", "Pass");

			ClickElement("Thumbnail");
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Thumbnail history Previous1.0 ", "Thumbnail history Previous", "Validate Thumbnail history Previous ", " Validate Thumbnail history Previous functionality", 
					" Validate Thumbnail history Previous functionality", "Unable to enter Thumbnail history Previous "+ e, "Fail", screeshot);
			ReportGenerate.Fail("Validate history Previous functionality - Unable to enter Thumbnail history Previous "+ e, "Fail");
		}

		try {
			try {
				ClickElement("ThumbnailhistoryNext");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ClickElement("historyNext1");
			}

			Thread.sleep(4000);

			screeshot = screenshot("ThumbnailhistoryNext");
			ReportGenerate.writeResult("Thumbnail history Next1.0 ", "Thumbnail history Next", "Thumbnail history Next", " Validate Thumbnail history Next functionality", 
					" Validate Thumbnail history Next functionality", "Able to click Thumbnail history Next", "Pass", screeshot);
			ReportGenerate.Pass("Validate Thumbnail history Next functionality - Able to click Thumbnail history Next", "Pass");

			ClickElement("backtoBookShelf");
			verifyElementIsDisplay("catogery_list");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Thumbnail history Next1.0 ", "Thumbnail history Next", "Validate Thumbnail history Next", " Validate Thumbnail history Next functionality", 
					" Validate Thumbnail history Next functionality", "Unable to enter Thumbnail history Next "+ e, "Fail", screeshot);
			ReportGenerate.Fail("Validate Thumbnail history Next functionality - Unable to enter Thumbnail history Next"+ e, "Fail");
		}
		finally
		{
			ReportGenerate.after();
		}

	}

	public static void validateSingleDoubleview() throws InterruptedException, IOException
	{

		//open_book();

		Thread.sleep(14000);

		for(int i=0; i<2; i++)
		{

			ClickElement("PageView");
			Thread.sleep(3000);

			String pageValue = GetAttributeValue("PageView", "aria-label");


			if(pageValue.equals("Single Page View (Ctrl+Shift+V)"))
			{
				screeshot = screenshot(pageValue);
				ReportGenerate.writeResult("Validate page view1.0 ", "validate page view",  " Validate page view functionality"," ", 
						" Validate Thumbnail functionality", "Able to view as "+pageValue, "Pass", screeshot);
				ReportGenerate.Pass("Validate page view functionality - Able to view as "+pageValue, "Pass");

			}

			else if(pageValue.equals("Double Page View (Ctrl+Shift+V)"))
			{
				screeshot = screenshot(pageValue);
				ReportGenerate.writeResult("Validate page view1.0 ", "validate page view",  " Validate page view functionality", " ", 
						" Validate Thumbnail functionality", "Able to view as "+pageValue, "Pass", screeshot);
				ReportGenerate.Pass("Validate page view functionality - Able to view as "+pageValue, "Pass");
			}

			else
			{
				ReportGenerate.writeResult("Validate page view1.0 ", "validate page view",  " Validate page view functionality", " ", 
						" Validate Thumbnail functionality", "Unable to click "+pageValue, "Fail", screeshot);
				ReportGenerate.Fail("Validate page view functionality - Unable to click" , "Fail");
				ReportGenerate.after();
			}

		}

		ClickElement("backtoBookShelf");
		verifyElementIsDisplay("catogery_list");


	}



	public static void validateFixedToHeightView() throws InterruptedException, IOException
	{

		try {
			Thread.sleep(14000);

			ClickElement("FixedWidth");

			Thread.sleep(2000);

			screeshot = screenshot("FixedWidth");
			ReportGenerate.writeResult("Validate Fixed To Height View1.0 ", "validate Fixed To Height View", " validate Fixed To Height View", " Validate Fixed To Height View functionality", 
					" Validate Fixed To Height View functionality", "Able to view as Fixed To Height View ", "Pass", screeshot);
			ReportGenerate.Pass("Validate Fixed To Height View functionality - Able to view as Fixed To Height View ", "Pass");

			ClickElement("backtoBookShelf");
			verifyElementIsDisplay("catogery_list");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Validate Fixed To Height View1.0 ", "validate Fixed To Height View", " validate Fixed To Height View", " Validate Fixed To Height View functionality", 
					" Validate Fixed To Height View functionality", "Unable to view as Fixed To Height View "+ e, "Fail", screeshot);
			ReportGenerate.Fail("Validate Fixed To Height View functionality "+ e, "Fail");

		}
		finally
		{
			ReportGenerate.after();
		}
	}


	public static void validateZoom() throws InterruptedException, IOException
	{

		try {
			Thread.sleep(13000);
			ClickElement("FixedLayoutZoom");

			Thread.sleep(2000);

			DragSliderVertical("ZoomSlider", 80);

			screeshot = screenshot("DragSliderVertical");
			ReportGenerate.writeResult("Validate zoom1.0 ", "validate zoom View", " validate zoom  View", " Validate zoom View functionality", 
					" Validate zoom functionality", "Able to zoom", "Pass", screeshot);
			ReportGenerate.Pass("Validate zoom functionality - Able to zoom", "Pass");

			ClickElement("backtoBookShelf");
			verifyElementIsDisplay("catogery_list");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Validate zoom1.0 ", "validate zoom View", " validate zoom  View", " Validate zoom View functionality", 
					" Validate zoom functionality", "Unable to zoom"+ e, "Fail", screeshot);
			ReportGenerate.Fail("Validate zoom functionality - error"+e, "Fail");

		}
		finally
		{
			ReportGenerate.after();
		}

	}


	public static void validatePenTool() throws InterruptedException, IOException
	{

		try {
			handleAlert();
			waitForElementToBeClickable("penTool");

			ClickElement("penTool");

			List<WebElement> color_list = GenericMethod.driver.findElements(By.xpath("//*[@id='penPanel']/div/div[1]/button"));

			for(WebElement ele : color_list)
			{

				String getColor = ele.getAttribute("aria-label");

				if(getColor.equals("Black"))
				{
					ele.click();

					defaultContentFrame();

					switchToFrameWebElement("frame5");

					Thread.sleep(2000);

					if(SanityTestNew.Browser.contains("chrome"))
					{
						DragSliderVertical("penDrag", 200);
					}
					else if(SanityTestNew.Browser.contains("firefox"))
					{
						defaultContentFrame();
						switchToFrameWebElement("frame4");
						DragSliderVertical("fireFox1", 200);

					}

					Thread.sleep(1000);

					defaultContentFrame();

					screeshot = screenshot(getColor);
					ReportGenerate.writeResult("Validate pen tool1.0 ", "validate pen tool", " validate pen tool", " Validate pen tool functionality", 
							" Validate pen tool functionality", "Able to draw "+ getColor + " in pen tool", "Pass", screeshot);
					ReportGenerate.Pass("Validate pen tool functionality - Able to draw "+ getColor + " in pen tool", "Pass");

					defaultContentFrame();
				}

				else if(getColor.equals("Red"))
				{

					ele.click();
					defaultContentFrame();

					switchToFrameWebElement("frame5");

					Thread.sleep(2000);

					DragSliderVertical("penDrag1", 200);
					defaultContentFrame();
					screeshot = screenshot(getColor);
					ReportGenerate.writeResult("Validate pen tool1.0 ", "validate pen tool", " validate pen tool", " Validate pen tool functionality", 
							" Validate pen tool functionality", "Able to draw "+ getColor + " in pen tool", "Pass", screeshot);
					ReportGenerate.Pass("Validate pen tool functionality - Able to draw "+ getColor + " in pen tool", "Pass");

					defaultContentFrame();

				}

				else if(getColor.equals("Purple"))
				{

					ele.click();
					defaultContentFrame();

					switchToFrameWebElement("frame5");

					Thread.sleep(2000);

					DragSliderVertical("penDrag2", 200);

					defaultContentFrame();

					screeshot = screenshot(getColor);
					ReportGenerate.writeResult("Validate pen tool1.0 ", "validate pen tool", " validate pen tool", " Validate pen tool functionality", 
							" Validate pen tool functionality", "Able to draw "+getColor+ " in pen tool", "Pass", screeshot);
					ReportGenerate.Pass("Validate pen tool functionality - Able to draw "+ getColor + " in pen tool", "Pass");

					defaultContentFrame();

				}

				else if(getColor.equals("Green"))
				{

					ele.click();
					defaultContentFrame();

					switchToFrameWebElement("frame5");

					Thread.sleep(2000);
					if(SanityTestNew.Browser.contains("chrome"))
					{
						DragSliderVertical("penDrag3", 200);
					}
					else if(SanityTestNew.Browser.contains("firefox"))
					{
						defaultContentFrame();
						switchToFrameWebElement("frame4");
						DragSliderVertical("fireFox2", 200);


					}
					Thread.sleep(1000);

					defaultContentFrame();
					screeshot = screenshot(getColor);
					ReportGenerate.writeResult("Validate pen tool1.0 ", "validate pen tool", " validate pen tool", " Validate pen tool functionality", 
							" Validate pen tool functionality", "Able to draw "+getColor+ " in pen tool", "Pass", screeshot);
					ReportGenerate.Pass("Validate pen tool functionality - Able to draw "+ getColor + " in pen tool", "Pass");

					defaultContentFrame();

				}

				else if(getColor.equals("Blue"))
				{

					ele.click();
					defaultContentFrame();

					switchToFrameWebElement("frame5");

					Thread.sleep(2000);
					if(SanityTestNew.Browser.contains("chrome"))
					{
						DragSliderVertical("penDrag4", 200);
					}
					else if(SanityTestNew.Browser.contains("firefox"))
					{
						defaultContentFrame();
						switchToFrameWebElement("frame4");
						DragSliderVertical("fireFox3", 200);


					}
					Thread.sleep(1000);

					defaultContentFrame();
					screeshot = screenshot(getColor);
					ReportGenerate.writeResult("Validate pen tool1.0 ", "validate pen tool", " validate pen tool", " Validate pen tool functionality", 
							" Validate pen tool functionality", "Able to draw "+getColor+ " in pen tool", "Pass", screeshot);
					ReportGenerate.Pass("Validate pen tool functionality - Able to draw "+getColor+ " in pen tool", "Pass");

					defaultContentFrame();

				}

				else 
				{

					System.out.println("Color is not present");

				}

			}



		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Validate pen tool1.0 ", "validate pen tool", " validate pen tool", " Validate pen tool functionality", 
					" Validate pen tool functionality", "Unable to draw using pen tool", "Fail", screeshot);
			ReportGenerate.Fail("Validate pen tool functionality - error" + e, "Fail");

		}
		finally
		{
			ReportGenerate.after();
		}
	}

	public static void validateStickyNotes() throws InterruptedException, IOException
	{

		try {
			handleAlert();
			ClickElement("stickyNote");

			defaultContentFrame();
			Robot rsticky = new Robot();
			rsticky.mouseMove(600, 300);    
			rsticky.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1000);
			rsticky.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(1000);

			SendValue("enterStickytext", stickyValue);
			waitForElementToBeClickable("stickySave");

			ClickElement("stickySave");

			defaultContentFrame();

			ClickElement("stickyNote");

			defaultContentFrame();

			rsticky.mouseMove(600, 350);    
			rsticky.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1000);
			rsticky.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(1000);

			waitForElementToBeClickable("stickyPink");

			ClickElement("stickyPink");

			waitForElementToBeClickable("enterStickytext");

			SendValue("enterStickytext", stickyValue);

			waitForElementToBeClickable("stickySave");

			ClickElement("stickySave");

			defaultContentFrame();

			ClickElement("stickyNote");

			defaultContentFrame();

			rsticky.mouseMove(600, 400);    
			rsticky.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1000);
			rsticky.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(1000);

			waitForElementToBeClickable("stickyPurple");

			ClickElement("stickyPurple");

			waitForElementToBeClickable("enterStickytext");

			SendValue("enterStickytext", stickyValue);

			waitForElementToBeClickable("stickySave");

			ClickElement("stickySave");

			defaultContentFrame();

			ClickElement("stickyNote");

			defaultContentFrame();

			rsticky.mouseMove(600, 450);    
			rsticky.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1000);
			rsticky.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(1000);

			waitForElementToBeClickable("stickyGreen");

			ClickElement("stickyGreen");

			waitForElementToBeClickable("enterStickytext");

			SendValue("enterStickytext", stickyValue);
			waitForElementToBeClickable("stickySave");
			ClickElement("stickySave");

			defaultContentFrame();

			ClickElement("stickyNote");

			defaultContentFrame();

			rsticky.mouseMove(600, 500);    
			rsticky.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1000);
			rsticky.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(1000);

			waitForElementToBeClickable("stickyBlue");

			ClickElement("stickyBlue");

			waitForElementToBeClickable("enterStickytext");

			SendValue("enterStickytext", stickyValue);

			waitForElementToBeClickable("stickySave");

			ClickElement("stickySave");

			screeshot = screenshot("sticky");
			//defaultContentFrame();

			Thread.sleep(1000);

			ReportGenerate.writeResult("Validate Sticky Notes 1.0 ", "validate Sticky Notes", " validate Sticky Notes", " Validate Sticky Notes functionality", 
					" Validate Sticky Notes functionality", "Able to save sticky notes in different color", "Pass", screeshot);
			ReportGenerate.Pass("Validate Sticky Notes functionality - Able to save sticky notes in different color", "Pass");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Validate Sticky Notes1.0 ", "validate Sticky Notes", " validate Sticky Notes", " Validate Sticky Notes functionality", 
					" Validate Sticky Notes functionality", "Unable to save sticky notes in different color: "+ e, "Fail", screeshot);
			ReportGenerate.Fail("Validate Sticky Notes functionality - error" + e, "Fail");
		}
		finally
		{
			ReportGenerate.after();
		}

	}

	public static void validateDeleteStickyNotes() throws InterruptedException, IOException
	{

		try {
			handleAlert();
			waitForElementToBeClickable("stickyNote");
			ClickElement("stickyNote");

			defaultContentFrame();
			Robot rsticky = new Robot();
			rsticky.mouseMove(600, 300);    
			rsticky.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1000);
			rsticky.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(1000);

			waitForElementToBeClickable("StickyDelete");
			ClickElement("StickyDelete");
			Thread.sleep(1000);
			defaultContentFrame();
			waitForElementToBeClickable("stickyNote");
			ClickElement("stickyNote");
			Thread.sleep(1000);
			rsticky.mouseMove(600, 350);    
			rsticky.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1000);
			rsticky.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(1000);

			waitForElementToBeClickable("StickyDelete");

			ClickElement("StickyDelete");

			Thread.sleep(1000);
			defaultContentFrame();
			waitForElementToBeClickable("stickyNote");
			ClickElement("stickyNote");
			Thread.sleep(1000);

			rsticky.mouseMove(600, 400);    
			rsticky.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1000);
			rsticky.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(1000);
			waitForElementToBeClickable("StickyDelete");

			ClickElement("StickyDelete");

			Thread.sleep(1000);
			defaultContentFrame();
			Thread.sleep(1000);
			waitForElementToBeClickable("stickyNote");
			ClickElement("stickyNote");
			Thread.sleep(1000);

			rsticky.mouseMove(600, 450);    
			rsticky.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1000);
			rsticky.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			waitForElementToBeClickable("StickyDelete");

			ClickElement("StickyDelete");

			Thread.sleep(1000);
			defaultContentFrame();
			waitForElementToBeClickable("stickyNote");
			ClickElement("stickyNote");
			Thread.sleep(1000);

			rsticky.mouseMove(600, 500);    
			rsticky.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1000);
			rsticky.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(1000);

			waitForElementToBeClickable("StickyDelete");

			ClickElement("StickyDelete");
			defaultContentFrame();
			screeshot = screenshot("Deletesticky");
			Thread.sleep(1000);

			ReportGenerate.writeResult("Validate delete Sticky Notes 1.0 ", "validate delete Sticky Notes", " Delete Sticky Notes", " Validate delete Sticky Notes functionality", 
					" Validate delete Sticky Notes functionality", "Able to delete sticky notes in different color", "Pass", screeshot);
			ReportGenerate.Pass("Validate Sticky Notes functionality - Able to delete sticky notes", "Pass");



		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Validate delete Sticky Notes1.0 ", "validate delete Sticky Notes", "delete Sticky Notes", " Validate  delete Sticky Notes functionality", 
					" Validate delete  Sticky Notes functionality", "Unable to delete  sticky notes in different color: "+ e, "Fail", screeshot);
			ReportGenerate.Fail("Validate Sticky Notes functionality - error" + e, "Fail");
		}
		finally
		{
			ReportGenerate.after();
		}

	}


	public static boolean isAlertPresent(){
		try{
			driver.switchTo().alert();
			return true;
		}catch(NoAlertPresentException ex){
			return false;
		}
	}

	public static void handleAlert() throws InterruptedException
	{
		driver.navigate().refresh();
		Thread.sleep(7000);
		if(isAlertPresent()){
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
		}
		waitUntilPageLoad();
		Thread.sleep(14000);
	}
	public static void validateDeletePen() throws InterruptedException, IOException
	{

		try {

			handleAlert();

			waitForElementToBeClickable("penTool");

			ClickElement("penTool");

			waitForElementToBeClickable("clearAll");
			ClickElement("clearAll");
			Thread.sleep(2000);

			ClickElement("GoBackBookshelfPopup");
			Thread.sleep(2000);

			/*Boolean  statuss = verifyElementIsDisplay("GoBackBookshelfPopup");
			if(statuss==true)
			{
				waitForElementToBeClickable("GoBackBookshelfPopup");
				ClickElement("GoBackBookshelfPopup");
				Thread.sleep(5000);

			}
			else
			{
				System.out.println("No back to book self popup came");
			}*/

			//ClickElement("penTool");
			waitForElementToBeClickable("PenSave");
			ClickElement("PenSave");

			screeshot = screenshot("validateDeletePen");
			ReportGenerate.writeResult("Validate pen tool delete1.0 ", "pen tool delete", " pen tool delete and save", " Validate delete pen tool functionality", 
					" Validate delete pen tool functionality", "Able to delete pen mark and save", "Pass", screeshot);
			ReportGenerate.Pass("Validate pen tool functionality - Able to delete pen mark and save", "Pass");


			/*GenericMethod.ClickElement("backtoBookShelf");
			GenericMethod.waitForElementToBeClickable("catogery_list");*/

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Validate pen tool delete1.0 ", "pen tool delete", " pen tool delete and save", " Validate delete pen tool functionality", 
					" Validate delete pen tool functionality", "Able to delete pen mark and save", "Fail", screeshot);
			ReportGenerate.Fail("Validate pen tool delete - error" + e, "Fail");

		}
		finally
		{
			ReportGenerate.after();
		}
	}


}
