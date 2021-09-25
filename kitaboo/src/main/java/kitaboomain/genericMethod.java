package kitaboomain;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLHandshakeException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

public class genericMethod  {


	public static WebDriver driver;
	Alert alert;

	static String textbyid, textbyclassname, textbytag, textbyxpath, title, lis_item1, lis_item2, lis_item3, lis_item4, lis_item5,parentWindow, resmess,hrefAttr, parentWindow1, alertText, currURL;
	static boolean result1, result2;
	static String uri, date = null;
	static String[] splitString1;
	static String[] temp;
	static int statusCode, tabCount;
	static int size;
	static Properties object = null;
	static String currentURL;

	static List<WebElement> itemsbyclass, itemsbyxpath, itemsbytagname, itemsbyxpath_1;
	static WebElement ele_xpath;
	static WebDriverWait wait30 = new WebDriverWait(driver, 30);
	static DateFormat format;
	static Date dt;

	
	
	public static void signIn() throws InterruptedException {
		  
		  enterValuebyID("userName", ConstantsOld.username);
		  enterValuebyID("passwordField", ConstantsOld.password);
		  ClickByID("signIn");
		  Thread.sleep(4000);
		  currentURL = driver.getCurrentUrl();
		 
		  if(currentURL.contains("bookshelf"))
		  {
			  ReportGenerateOld.writeResult("", "", "", "Login Successful", " ", "Pass"); 
			  System.out.println("Pass");
		  }
		  else
			  ReportGenerateOld.writeResult("", "", "", "Login Successful", " ", "Fail"); 
	  }
	
	
	/**
	 * To load properties from properties file
	 * 
	 */
	public static Properties loadPropertiesFile(String propFilePath) {
		Properties properties = null;
		try {
			properties = new Properties();
			FileInputStream fis = new FileInputStream(propFilePath);
			properties.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static String[] readlocators(String locatorKey) {

		String objectFileName = "src/test/resources/config/locators.properties";
		object = loadPropertiesFile(objectFileName);
		String[] locatorMethodName = null;
		try {
			locatorKey = locatorKey.replace(" ", "").replace(":", "");
			String objectValue = object.getProperty(locatorKey);
			locatorMethodName = objectValue.split("#");
		}
		catch(NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", "" , "locatore key was not Found....", " ", "Fail");	
		} 

		return locatorMethodName;
	}


	public static void ClickElement(String locatorkey) {
		WebElement element = null;
		String locatorMethod = null;
		String locatorValue = null;
		try {
			String[] locatorMethodName = readlocators(locatorkey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {

			System.out.println("Issue on locator properties: "+ e);
		}

		try {
			switch (locatorMethod) {
			case "id":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorValue)));
				driver.findElement(By.id((locatorValue))).click();
				break;
			case "name":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				driver.findElement(By.name((locatorValue))).click();
				break;
			case "class":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				driver.findElement(By.className((locatorValue))).click();
				break;
			case "linkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				driver.findElement(By.linkText((locatorValue))).click();
				break;
			case "partiallinkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				driver.findElement(By.partialLinkText((locatorValue))).click();
				break;
			case "tagname":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				driver.findElement(By.tagName((locatorValue))).click();
				break;
			case "css":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				driver.findElement(By.cssSelector((locatorValue))).click();
				break;
			case "xpath":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				driver.findElement(By.xpath((locatorValue))).click();
				break;

			default:
				break;
			}
		}
		catch(NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", locatorMethod +  "-" , "Element Not Found....", " ", "Fail");
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", locatorMethod +  "-" , "Driver Not Found", " ", "Fail");
		} 

	}

	public void wrapperMethods()
	{
		//this.testCaseName = testCaseName;
		//this.dataSet = dataSet;
		System.setProperty("atu.reporter.config", System.getProperty("user.dir")+"\\atu.properties");
	}

	public WebDriver invokeApplication(String browser, String url) throws InterruptedException, IOException
	{

		try {
			if(browser.toLowerCase().equals("ie"))
			{
				System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			else if(browser.toLowerCase().equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if(browser.toLowerCase().equals("firefox"))
			{
				driver = new FirefoxDriver();
			}
			//ATUReports.setWebDriver(driver);

			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			ReportGenerateOld.writeResult(" ", " ", " ", url +" Launched Successfully", " ", "Pass");

		} catch (Exception e) 
		{
			// TO Write the error in Excel
			ReportGenerateOld.writeResult(" ", " ", " ", "Browser Not Launched!!!!", " ", "Fail");
		}
		return driver;
	}

	public static void enterValuebyID(String id, String value)
	{
		try	{
			driver.findElement(By.id(id)).clear();
			driver.findElement(By.id(id)).sendKeys(value, Keys.ENTER);
		}
		catch(NoSuchElementException e)
		{
			ReportGenerateOld.writeResult("", "", id +  "-" + value, "Element Not Found with", " ", "Fail");	
		}
		catch(WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", id +  "-" + value, "Driver Not Found" , " ", "Fail");
		}
	}

	public void enterValueByName(String name, String value)
	{
		try{
			driver.findElement(By.name(name)).clear();
			driver.findElement(By.name(name)).sendKeys(value);
		} 
		catch (NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", name +  "-" + value, "Element Not Found....", " ", "Fail");
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", name +  "-" + value, "Driver Not Found", " ", "Fail");
		} 
	}

	public void enterByclassname(String classname,String value)
	{
		try{

			driver.findElement(By.className(classname)).clear();
			driver.findElement(By.className(classname)).sendKeys(value);

		} 
		catch(NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", classname +  "-" + value, "Element Not Found....", " ", "Fail");
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", classname +  "-" + value, "Driver Not Found", " ", "Fail");
		} 
	}

	public void enterBylinktext(String linktext,String value)
	{
		try
		{
			driver.findElement(By.linkText(linktext)).clear();
			driver.findElement(By.className(linktext)).sendKeys(value);
		}
		catch(NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", linktext +  "-" + value, "Element Not Found....", " ", "Fail");	
		} 
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", linktext +  "-" + value, "Driver Not Found", " ", "Fail");
		} 
	}

	public void enterBycssSelector(String css,String value)
	{
		try	{
			driver.findElement(By.cssSelector(css)).clear();
			driver.findElement(By.className(css)).sendKeys(value);
		} 
		catch(NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", css +  "-" + value, "Element Not Found....", " ", "Fail");	
		}
		catch (WebDriverException e) 
		{
			ReportGenerateOld.writeResult("", "", css +  "-" + value, "Driver Not Found", " ", "Fail");
		} 
	}

	public void enterByxpath(String xpath,String value)
	{
		try{
			WebDriverWait wait90 = new WebDriverWait(driver, 90);
			ele_xpath = wait90.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			ele_xpath.clear();
			ele_xpath.sendKeys(value);
		} 
		catch(NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", xpath +  "-" + value, "Element Not Found....", " ", "Fail");	
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", xpath +  "-" + value, "Driver Not Found", " ", "Fail");
		}
	}

	public List<WebElement> elementsbyclass(String name)
	{
		try	{
			itemsbyclass  = driver.findElements(By.className(name));
		}
		catch(NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", name, "Element Not Found....", " ", "Fail");	

		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", name, "Driver Not Found", " ", "Fail");
		}
		return itemsbyclass;
	}

	public static List<WebElement> elementsbyxpath(String xpathlocator)
	{
		try{
			WebDriverWait wait = new WebDriverWait(driver, 40);
			itemsbyxpath  = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathlocator)));

		}
		catch(NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", xpathlocator, "Element Not Found....", " ", "Fail");		
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", xpathlocator, "Driver Not Found8", " ", "Fail");
		}
		return itemsbyxpath;
	}

	public List<WebElement> elementsbytagname(String tag)
	{
		try{
			itemsbytagname  = driver.findElements(By.tagName(tag));
		}
		catch(NoSuchElementException exc) 
		{
			ReportGenerateOld.writeResult("", "", tag, "Element Not Found....", " ", "Fail");		
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", tag, "Driver Not Found", " ", "Fail");
		}
		return itemsbytagname;
	}

	public void filterlist(String xpath)
	{
		try{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			itemsbyxpath_1  = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));

			lis_item1 = itemsbyxpath_1.get(1).findElement(By.tagName("a")).getAttribute("title");
			lis_item2 = itemsbyxpath_1.get(2).findElement(By.tagName("span")).getAttribute("class");
			lis_item3 = itemsbyxpath_1.get(3).findElement(By.tagName("span")).getAttribute("class");
			lis_item4 = itemsbyxpath_1.get(4).findElement(By.tagName("a")).getAttribute("title");
			lis_item5 = itemsbyxpath_1.get(5).findElement(By.tagName("span")).getAttribute("data-attr");
		}
		catch(NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", xpath, "Element Not Found....", " ", "Fail");		
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", xpath, "Driver Not Found", " ", "Fail");
		}

	}

	public WebElement webelementbyxpath(String xpath)
	{
		try
		{
			ele_xpath = driver.findElement(By.xpath(xpath));
		}
		catch(NoSuchElementException exc) 
		{
			ReportGenerateOld.writeResult("", "", xpath, "Element Not Found....", " ", "Fail");		
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", xpath, "Driver Not Found11", " ", "Fail");
		}
		return ele_xpath;
	}

	public void ClickByText(String text)
	{
		try {
			driver.findElement(By.linkText(text)).click();
		}
		catch (NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", text, "Element Not Found....", " ", "Fail");		
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", text, "Driver Not Found", " ", "Fail");
		} 
	}

	public static void ClickByID(String id)
	{
		try {
			driver.findElement(By.id(id)).click();
		}
		catch (NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", id, "Element Not Found....", " ", "Fail");		
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", id, "Driver Not Found", " ", "Fail");
		} 
	}

	public void ClickByClassName(String classname)
	{
		try {
			driver.findElement(By.className(classname)).click();

		}
		catch (NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", classname, "Element Not Found....", " ", "Fail");		
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", classname, "Driver Not Found", " ", "Fail");
		} 
	}

	public void ClickByName(String name)
	{
		try {
			driver.findElement(By.name(name)).click();

		}
		catch (NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", name, "Element Not Found....", " ", "Fail");		
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", name, "Driver Not Found", " ", "Fail");
		} 
	}

	public static void ClickByXpath(String locatebyxpath)
	{
		try {
			/*Wait<WebDriver> wait = new FluentWait<WebDriver>(driver) 
					.withTimeout( 30, TimeUnit.SECONDS ) 
					     .pollingEvery( 5, TimeUnit.SECONDS ) 
					     .ignoring( NoSuchElementException.class, StaleElementReferenceException.class ); */

			WebDriverWait wait = new WebDriverWait(driver, 30);

			WebElement ele = driver.findElement(By.xpath(locatebyxpath));
			WebElement ele1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatebyxpath)));

			ele1.click();
		}
		catch (NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", locatebyxpath, "Element Not Found....", " ", "Fail");		
		}
		catch (StaleElementReferenceException e)
		{
			ReportGenerateOld.writeResult("", "", locatebyxpath, "Stale Element Reference Exception", "Element couldn't be locate ", "Fail");
		} 
	}

	private FluentWait<WebDriver> WebDriverWait(WebDriver driver2, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public void ClickByCSS(String css)
	{
		try {
			driver.findElement(By.cssSelector(css)).click();
		}
		catch (NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", css, "Element Not Found....", " ", "Fail");		
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", css, "Driver Not Found", " ", "Fail");
		} 
	}

	public static String getTextbyid(String id)
	{
		try	{
			textbyid = driver.findElement(By.id(id)).getText();
		}	
		catch(NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", id, "Element Not Found....", " ", "Fail");	
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", id, "Driver Not Found", " ", "Fail");
		}
		return textbyid;
	}

	public String getTextbyClass(String classname)
	{
		try {
			textbyclassname = driver.findElement(By.className(classname)).getText();
		}
		catch (NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", classname, "Element Not Found....", " ", "Fail");	
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", classname, "Driver Not Found", " ", "Fail");
		}
		return textbyclassname;
	}

	public String getTextbyTag(String tag)
	{
		try {
			textbytag = driver.findElement(By.tagName(tag)).getText();
		}
		catch (NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", tag, "Element Not Found....", " ", "Fail");	
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", tag, "Driver Not Found", " ", "Fail");
		}
		return textbytag;
	}

	public static String getTextbyxpath(String xpath)
	{
		try	{
			wait30 = new WebDriverWait(driver, 30);
			ele_xpath = wait30.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(xpath))));
			textbyxpath = ele_xpath.getText();
		} 
		catch (NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", xpath, "Element Not Found....", " ", "Fail");	
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", xpath, "Driver Not Found", " ", "Fail");
		}
		return textbyxpath;
	}

	public String WaitforalertHandle() throws NoAlertPresentException
	{
		try {
			wait30 = new WebDriverWait(driver, 15);
			if(wait30.until(ExpectedConditions.alertIsPresent()) != null)
			{
				alert = driver.switchTo().alert();
				alertText = alert.getText();
			}
		}
		catch (NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", "", "Element Not Found....", " ", "Fail");	
		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", "", "Driver Not Found", " ", "Fail");
		}
		return alertText;
	}

	public boolean alertHandle()
	{
		try {	
			alert = driver.switchTo().alert();
			alertText = alert.getText();
			return true;
		}
		catch (NoAlertPresentException e) 
		{
			//	ReportGenerate.writeResult("", "", "", "No Alert Present....", " ", "PASS");
			return false;

		}
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", "", "Driver Not Found", " ", "Fail");
		}
		return false;
	}

	public void driverWaitAndClick(String id)
	{
		try {
			WebDriverWait Wait = new WebDriverWait(driver,90);
			Wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id))).click();
		}
		catch (NoSuchElementException e) 
		{
			ReportGenerateOld.writeResult("", "", "", "Element Not Found....", "", "Fail");	

		}
	}

	public void switchWindow()
	{
		try	{
			parentWindow = driver.getWindowHandle();
			Set <String> handles = driver.getWindowHandles();

			for(String WindowHandles : handles)
			{
				driver.switchTo().window(WindowHandles);
				//	System.out.println(WindowHandles);
			}

		}	
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", "SwitchWindow",  "Driver Not Found", "", "Fail");
		}

	}

	public String switchWindowClose()
	{
		try	{
			String parentWindow1 = driver.getWindowHandle();
			Set <String> handle = driver.getWindowHandles();

			for(String WindowHandles1 : handle)
			{
				driver.switchTo().window(WindowHandles1);
				System.out.println(WindowHandles1);
			}
			uri = driver.getCurrentUrl();
			driver.close();
			driver.switchTo().window(parentWindow1);
		}	
		catch (WebDriverException e)
		{
			ReportGenerateOld.writeResult("", "", "",  "Driver Not Found24", "", "Fail");
		}
		return uri;
	}

	public void closeApplication() throws IOException, ParseException
	{
		try {

			driver.quit();
		} 
		catch (WebDriverException exe)
		{
			ReportGenerateOld.writeResult("", "", "", "Driver could not be closed for unknown reason !!!", " ", "Fail");
		} 	

	}

	static String simpleDate() throws ParseException{
		try {
			format = new SimpleDateFormat("MM-dd-YYYY");
			dt = new Date();
			date = format.format(dt);
		}
		catch (Exception exe)
		{
			ReportGenerateOld.writeResult("", "", "",  "Date not created", "", "Fail");
		} 	
		return date;
	}

	public String[] splitString(String regexp, String title, int limit) throws IOException
	{
		try {
			splitString1 = title.split(regexp, limit);

			if(splitString1[1].contains(regexp))
			{
				temp = splitString1[1].split(regexp, limit);
			}	
			else
			{
				temp = splitString1;
			}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{

			ReportGenerateOld.writeResult("", "", "",  "No text available", " ", "Fail");
		}
		return temp;
	}

	public int uricheck(String url) throws IOException
	{
		try {
			URL url1 = new URL(url);
			HttpURLConnection http = (HttpURLConnection)url1.openConnection();
			http.connect();
			statusCode = http.getResponseCode();
			resmess = http.getResponseMessage();
			System.out.println(statusCode); 
			System.out.println(resmess);

		} catch (SSLHandshakeException e) {
			// TODO Auto-generated catch block
			ReportGenerateOld.writeResult("", "", "",  "ValidatorException", "", "Fail");
			//e.printStackTrace();
		}
		return statusCode;
	}

	public String getAttrbyXpath(String xpath) throws IOException
	{
		try {
			hrefAttr = driver.findElement(By.xpath(xpath)).getAttribute("href");
		} catch (WebDriverException e)	{
			ReportGenerateOld.writeResult("", "", xpath,  "Driver Not Found", "", "Fail");
		}
		return hrefAttr;
	}

	/*public int openNewTab() throws IOException
		{
		 try {
				driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");

				 ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				 tabCount = tabs.size();
				 System.out.println(tabCount);
				 driver.switchTo().window(tabs.get(tabCount-2));
				 driver.get("chrome://downloads");


			} catch (WebDriverException e)	{
				ReportGenerate.writeResult("", "", "",  "Driver Not Found", "", "Fail");
		  	}
		 	return tabCount;
		  }*/

	public String getCurrentURL() throws IOException
	{
		try {
			currURL = driver.getCurrentUrl();
		} catch (WebDriverException e)	{
			ReportGenerateOld.writeResult("", "", "",  "Driver Not Found", "", "Fail");
		}
		return currURL;
	}

	public static void screenshot(String filename) throws IOException
	{
		try {
			TakesScreenshot scrShot =((TakesScreenshot)driver);

			//Call getScreenshotAs method to create image file

			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

			//Move image file to new destination

			File DestFile=new File(System.getProperty("user.dir") + "\\Reports\\screenshot\\"+filename+".png");

			//Copy file at destination

			FileUtils.copyFile(SrcFile, DestFile);

		} catch (WebDriverException e)	{
			ReportGenerateOld.writeResult("", "", "",  "Driver Not Found", "", "Fail");
		}

	}

}


