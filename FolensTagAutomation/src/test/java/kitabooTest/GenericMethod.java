package kitabooTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;


public class GenericMethod  {

	public static WebDriver driver;
	static String textbyid, textbyclassname, textbytag, textbyxpath, title, lis_item1, lis_item2, lis_item3, lis_item4, lis_item5,parentWindow, resmess,hrefAttr, parentWindow1, alertText, currURL;

	public static String currentURL, attributeValue, spath, screeshot,textValue, FileName;
	public static WebElement element;
	public static WebDriverWait wait30;
	public static Properties object, prop;
	public static Actions action;
	public static JavascriptExecutor executor = (JavascriptExecutor)driver;

	private static final String APPLICATION_NAME = "seleniumkitabboo";
	private static final java.io.File DATA_STORE_DIR = new java.io.File(
			System.getProperty("user.home"), ".credentials/sheets.googleapis.com-java-quickstart");
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "tokens";

	/*
	 * Global instance of the scopes required by this quickstart. If modifying these
	 * scopes, delete your previously saved tokens/ folder.
	 */
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
	private static final String CREDENTIALS_FILE_PATH = System.getProperty("user.dir") + "\\client_secret.json";

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If the credentials.json file cannot be found.
	 */
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {

		// Load client secrets.
		InputStream in =  new FileInputStream(CREDENTIALS_FILE_PATH);

		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}


	/**
	 * Prints the names and majors of students in a sample spreadsheet:
	 * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
	 */
	public List<List<Object>> getData(String spreadsheetId, String range) throws IOException, GeneralSecurityException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		//final String spreadsheetId = "1NIrtOTDlaNt-SJqBdSVfXCbhWL8ocFcbsy7ruRfNgKM";

		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
			return null;
		} else {
			return values;
		}
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

	/**
	 * To load data from property file
	 * 
	 */
	public static Properties dataPropertiesFile() {
		try {
			prop = new Properties();
			FileInputStream con = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config\\data.properties");
			prop.load(con);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}




	/**
	 * To read the locator properties from config file
	 * 
	 */
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
			ReportGenerate.writeResult("readlocators1.0 ", "readlocators", "readlocators", " Validate readlocators",
					" Validate readlocators", "Unable to readlocators: "+e, "Fail", " ");
		} 

		return locatorMethodName;
	}


	public static void switchChildWindows() throws IOException, InterruptedException
	{

		try {
			parentWindow = driver.getWindowHandle();
			Thread.sleep(2000);
			for (String window : driver.getWindowHandles()) {
				if (!window.equalsIgnoreCase(parentWindow)) {
					driver.switchTo().window(window);
					ReportGenerate.writeResult("switch parent window", "Handle switch parent  windows", "Handle switch parent  windows", " switch  parent windows functionality",
							"switch windows functionalities", "Able to handle switch parent windows", "Pass", " ");
					ReportGenerate.Pass("Validate able to switch to child windows - Able to switch child windows", "Pass");
				}
			}

		} catch (NoSuchWindowException e) {
			System.out.println("Window Already closed and window handle is not visible further ...");
		} catch (Exception e) {
			ReportGenerate.writeResult("switch child windows ", "Handle switch child windows", "Handle switch child windows", " switch child windows functionality",
					"switch child windows functionalities", "Unable to handle switch child windows: "+e, "Fail", " ");
			ReportGenerate.Fail("Validate able to switch to child windows - Able to switch child windows", "Fail");
		}

	}

	public static void switchParentWindow() throws IOException, InterruptedException
	{

		try {

			//parentWindow = driver.getWindowHandle();
			Thread.sleep(2000);
			for (String window : driver.getWindowHandles()) {
				if (window.equalsIgnoreCase(parentWindow)) {
					driver.switchTo().window(parentWindow);
					ReportGenerate.writeResult("switch parent window", "Handle switch parent  windows", "Handle switch parent  windows", " switch  parent windows functionality",
							"switch windows functionalities", "Able to handle switch parent windows: ", "Pass", " ");
					ReportGenerate.Pass("Validate able to switch to child windows - Able to switch Parent windows", "Pass");
				}
			}

		} catch (NoSuchWindowException e) {
			System.out.println("Window Already closed and window handle is not visible further ...");
		} catch (Exception e) {
			ReportGenerate.writeResult("switch parent window", "Handle switch parent  windows", "Handle switch parent  windows", " switch  parent windows functionality",
					"switch windows functionalities", "Unable to handle switch parent windows: "+e, "Fail", " ");
			ReportGenerate.Fail("Validate able to switch to parent window - Unable to switch Parent window "+ e, "Fail");
		}

	}

	/**
	 * 
	 * 
	 */
	public void wrapperMethods()
	{
		//this.testCaseName = testCaseName;
		//this.dataSet = dataSet;
		System.setProperty("atu.reporter.config", System.getProperty("user.dir")+"\\atu.properties");
	}



	/**
	 * Switch to default frame
	 */
	public static void defaultContentFrame() {
		try {
			driver.switchTo().defaultContent();
			ReportGenerate.writeResult("default Content Frame1.0 ", " default Content Frame", "default Content Frame", " Validate default Content Frame",
					" Validate default Content Frame", "Able to switch default Content Frame ", "Pass", " ");
		}  catch (Exception e) {
			ReportGenerate.writeResult("default Content Frame1.0 ", " default Content Frame", "default Content Frame", " Validate default Content Frame",
					" Validate default Content Frame", "Unable to switch default Content Frame "+ e, "Fail", " ");
		}
	}

	/**
	 * Switch to webelement frame
	 */
	public static void switchToFrameWebElement(String locatorkey) {

		WebElement element=null;
		String locatorMethod = null;
		String locatorValue = null;
		wait30 =  new WebDriverWait(driver, 30);
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
				element = driver.findElement(By.id((locatorValue)));
				driver.switchTo().frame(element);
				break;
			case "name":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				element = driver.findElement(By.name((locatorValue)));
				driver.switchTo().frame(element);
				break;
			case "class":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				element = driver.findElement(By.className((locatorValue)));
				driver.switchTo().frame(element);
				break;
			case "linkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				element = driver.findElement(By.linkText((locatorValue)));
				driver.switchTo().frame(element);
				break;
			case "partiallinkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				element = driver.findElement(By.partialLinkText((locatorValue)));
				driver.switchTo().frame(element);
				break;
			case "tagname":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				element = driver.findElement(By.tagName((locatorValue)));
				driver.switchTo().frame(element);
				break;
			case "css":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				element = driver.findElement(By.cssSelector((locatorValue)));
				driver.switchTo().frame(element);
				break;
			case "xpath":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				element = driver.findElement(By.xpath((locatorValue)));
				driver.switchTo().frame(element);
				break;

			default:
				break;
			}
		}
		catch(NoSuchElementException e) 
		{
			ReportGenerate.writeResult("switch To Frame WebElement1.0 ", "switch To Frame WebElement", "switch To Frame WebElement"," ", " Validate switch To Frame WebElement", 
					" Validate switch To Frame WebElement", "Unable to switch To Frame WebElement: "+e, "Fail");

		}
		catch (WebDriverException e)
		{
			ReportGenerate.writeResult("switch To Frame WebElement1.0 ", "switch To Frame WebElement", "switch To Frame WebElement"," Validate switch To Frame WebElement", 
					" Validate switch To Frame WebElement", "Unable to switch To Frame WebElement: "+e, "Fail", " ");
		} 

	}



	/*
	 * 
	 * Switch to window using title
	 */
	public static void SwitchUsingTitle(String title) throws InterruptedException {
		Thread.sleep(3000);
		Set<String> totalWindow = driver.getWindowHandles();

		if (totalWindow.size() >= 1) {
			for (String loopWindow : totalWindow) {
				System.out.println(loopWindow);
				driver.switchTo().window(loopWindow);
				System.out.println(driver.getTitle().trim() + "******" + title);
				if (driver.getTitle().trim().contains(title)) {
					break;
				}
			}
		} else {
			System.out.println("No Windows was Found");
		}
	}


	/**
	 * Wait for an element
	 * 
	 */
	public static WebElement getWebElement(String locatorKey) throws InterruptedException {

		WebElement element = null;
		String locatorMethod = null;
		String locatorValue = null;

		try {
			String[] locatorMethodName = readlocators(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			ReportGenerate.writeResult("readlocatorst1.0 ", "getWebElementWait", "getWebElementWait", " Validate getWebElementWait",
					" Validate getWebElementWait", "Unable to getWebElementWait: "+e, "Fail", " ");
		}
		try {
			switch (locatorMethod) {
			case "id":
				element = driver.findElement(By.id((locatorValue)));
				break;
			case "name":
				element = driver.findElement(By.name((locatorValue)));
				break;
			case "class":
				element = driver.findElement(By.className((locatorValue)));
				break;
			case "linkText":
				element = driver.findElement(By.linkText((locatorValue)));
				break;
			case "partiallinkText":
				element = driver.findElement(By.partialLinkText((locatorValue)));
				break;
			case "tagname":
				element = driver.findElement(By.tagName((locatorValue)));
				break;
			case "css":
				element = driver.findElement(By.cssSelector((locatorValue)));
				break;
			case "xpath":
				element = driver.findElement(By.xpath((locatorValue)));
				break;

			default:
				break;
			}
		} catch (NoSuchWindowException e) {
			System.out.println("Window Already closed and elment is not visible further ...");
			ReportGenerate.writeResult("getWebElementWait1.0 ", "getWebElementWait", "getWebElementWait", " Validate getWebElementWait",
					" Validate getWebElementWait", "Unable to getWebElementWait: "+e, "Fail", " ");
		} catch (Exception e) {
			ReportGenerate.writeResult("getWebElementWait1.0 ", "ClickEnter", "getWebElementWait", " Validate getWebElementWait",
					" Validate getWebElementWait", "Unable to getWebElementWait: "+e, "Fail", " ");
		}
		return element;
	}

	/**
	 * Wait for an element
	 * 
	 */
	public static WebElement waitVisibleWebElement(String locatorKey) throws InterruptedException {

		WebElement element = null;
		String locatorMethod = null;
		String locatorValue = null;
		wait30 =  new WebDriverWait(driver, 30);

		try {
			String[] locatorMethodName = readlocators(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			ReportGenerate.writeResult("readlocators1.0 ", "waitVisibleWebElement", "waitVisibleWebElement", " Validate waitVisibleWebElement",
					" Validate waitVisibleWebElement", "Unable to waitVisibleWebElement: "+e, "Fail", " ");
		}
		try {
			switch (locatorMethod) {
			case "id":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
				element = driver.findElement(By.id((locatorValue)));
				break;
			case "name":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
				element = driver.findElement(By.name((locatorValue)));
				break;
			case "class":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
				element = driver.findElement(By.className((locatorValue)));
				break;
			case "linkText":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
				element = driver.findElement(By.linkText((locatorValue)));
				break;
			case "partiallinkText":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
				element = driver.findElement(By.partialLinkText((locatorValue)));
				break;
			case "tagname":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
				element = driver.findElement(By.tagName((locatorValue)));
				break;
			case "css":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
				element = driver.findElement(By.cssSelector((locatorValue)));
				break;
			case "xpath":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
				element = driver.findElement(By.xpath((locatorValue)));
				break;

			default:
				break;
			}
		} catch (NoSuchWindowException e) {
			System.out.println("Window Already closed and elment is not visible further ...");
			ReportGenerate.writeResult("waitVisibleWebElement1.0 ", "waitVisibleWebElement", "waitVisibleWebElement", " Validate waitVisibleWebElement",
					" Validate waitVisibleWebElement", "Unable to waitVisibleWebElement: "+e, "Fail", " ");
		} catch (Exception e) {
			ReportGenerate.writeResult("waitVisibleWebElement1.0 ", "waitVisibleWebElement", "waitVisibleWebElement", " Validate waitVisibleWebElement",
					" Validate waitVisibleWebElement", "Unable to waitVisibleWebElement: "+e, "Fail", " ");
		}
		return element;
	}


	/**
	 * Verify element is displayed
	 */
	public static boolean verifyElementIsDisplay(String locatorKey) throws InterruptedException {
		Thread.sleep(3000);
		element = getWebElement(locatorKey);
		boolean status = element.isDisplayed();
		try {
			if (status==true)
			{
				return true;
			}
			else
				return false;
		} catch (NoSuchElementException e) {

			return false;
		}

	}


	/**
	 * wait and click the element
	 * @throws InterruptedException 
	 * 
	 */
	public static void ClickElement(String locatorkey) throws InterruptedException {
		//WebElement element = null;
		String locatorMethod = null;
		String locatorValue = null;
		wait30 =  new WebDriverWait(driver, 30);
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
			ReportGenerate.writeResult("ClickElement1.0 ", "ClickEnter", "ClickEnter", " Validate ClickEnter",
					" Validate ClickEnter", "Unable to ClickEnter: "+e, "Fail", " ");

		}

	}

	public static void waitForElementToBeClickable(String locatorkey) {
		String locatorMethod = null;
		String locatorValue = null;
		wait30 =  new WebDriverWait(driver, 30);
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
				wait30.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
				break;
			case "name":
				wait30.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
				break;
			case "class":
				wait30.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
				break;
			case "linkText":
				wait30.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
				break;
			case "partiallinkText":
				wait30.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
				break;
			case "tagname":
				wait30.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
				break;
			case "css":
				wait30.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
				break;
			case "xpath":
				wait30.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
				break;

			default:
				break;
			}
		} catch (Exception e) {
			/*ReportGenerate.writeResult("waitToBeclickable","waitToBeclickable" ,
					" ","",
					"The Locator Method and Value should be available in the page and "+e.getMessage(), " ","","Fail", " Find Element By " + locatorMethod + " with Value " + locatorValue +" in the webpage"," ");*/
			ReportGenerate.writeResult("waitToBeclickable ", "waitToBeclickable functionality", " ", " The Locator Method and Value should be available in the page and "+e.getMessage(),
					"Find Element By " + locatorMethod + " with Value " + locatorValue +" in the webpage", "Unable to GetAttributeValue: "+e, "Fail", " ");
		}

	}

	/**
	 * wait and send the value
	 */
	public static void SendValue(String locatorkey,String value) {
		WebElement element = null;
		String locatorMethod = null;
		String locatorValue = null;
		wait30 =  new WebDriverWait(driver, 30);
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
				driver.findElement(By.id((locatorValue))).sendKeys(value);
				break;
			case "name":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				driver.findElement(By.name((locatorValue))).sendKeys(value);
				break;
			case "class":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				driver.findElement(By.className((locatorValue))).sendKeys(value);
				break;
			case "linkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				driver.findElement(By.linkText((locatorValue))).sendKeys(value);
				break;
			case "partiallinkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				driver.findElement(By.partialLinkText((locatorValue))).sendKeys(value);
				break;
			case "tagname":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				driver.findElement(By.tagName((locatorValue))).sendKeys(value);
				break;
			case "css":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				driver.findElement(By.cssSelector((locatorValue))).sendKeys(value);
				break;
			case "xpath":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				driver.findElement(By.xpath((locatorValue))).sendKeys(value);
				break;

			default:
				break;
			}
		}
		catch(NoSuchElementException e) 
		{
			ReportGenerate.writeResult("SendValue1.0 ", "ClickEnter", "ClickEnter", " Validate ClickEnter",
					" Validate ClickEnter", "Unable to ClickEnter: "+e, "Fail", " ");

			//System.out.println("Error in click element");
		}

	}



	/**
	 * get the attribute value
	 */
	public static String GetAttributeValue(String locatorkey,String value) {

		WebElement element=null;
		String locatorMethod = null;
		String locatorValue = null;
		wait30 =  new WebDriverWait(driver, 30);
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
				element = driver.findElement(By.id((locatorValue)));
				attributeValue = element.getAttribute(value);
				break;
			case "name":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				element = driver.findElement(By.name((locatorValue)));
				attributeValue = element.getAttribute(value);
				break;
			case "class":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				element = driver.findElement(By.className((locatorValue)));
				attributeValue = element.getAttribute(value);
				break;
			case "linkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				element = driver.findElement(By.linkText((locatorValue)));
				attributeValue = element.getAttribute(value);
				break;
			case "partiallinkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				element = driver.findElement(By.partialLinkText((locatorValue)));
				attributeValue = element.getAttribute(value);
				break;
			case "tagname":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				element = driver.findElement(By.tagName((locatorValue)));
				attributeValue = element.getAttribute(value);
				break;
			case "css":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				element = driver.findElement(By.cssSelector((locatorValue)));
				attributeValue = element.getAttribute(value);
				break;
			case "xpath":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				element = driver.findElement(By.xpath((locatorValue)));
				attributeValue = element.getAttribute(value);
				break;

			default:
				break;
			}
		}
		catch(NoSuchElementException e) 
		{
			ReportGenerate.writeResult("GetAttributeValue1.0 ", "GetAttributeValue", "GetAttributeValue", " Validate GetAttributeValue",
					" Validate GetAttributeValue", "Unable to GetAttributeValue: "+e, "Fail", " ");

		}

		return attributeValue;

	}


	/**
	 * get the attribute value
	 */
	public static String javaScriptClick(String locatorkey) {

		WebElement element=null;
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		String locatorMethod = null;
		String locatorValue = null;
		wait30 =  new WebDriverWait(driver, 30);
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
				element = driver.findElement(By.id((locatorValue)));
				executor.executeScript("arguments[0].click();", element);
				break;
			case "name":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				element = driver.findElement(By.name((locatorValue)));
				executor.executeScript("arguments[0].click();", element);
				break;
			case "class":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				element = driver.findElement(By.className((locatorValue)));
				executor.executeScript("arguments[0].click();", element);
				break;
			case "linkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				element = driver.findElement(By.linkText((locatorValue)));
				executor.executeScript("arguments[0].click();", element);
				break;
			case "partiallinkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				element = driver.findElement(By.partialLinkText((locatorValue)));
				executor.executeScript("arguments[0].click();", element);
				break;
			case "tagname":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				element = driver.findElement(By.tagName((locatorValue)));
				executor.executeScript("arguments[0].click();", element);
				break;
			case "css":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				element = driver.findElement(By.cssSelector((locatorValue)));
				executor.executeScript("arguments[0].click();", element);
				break;
			case "xpath":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				element = driver.findElement(By.xpath((locatorValue)));
				executor.executeScript("arguments[0].click();", element);
				break;

			default:
				break;
			}
		}
		catch(NoSuchElementException e) 
		{
			ReportGenerate.writeResult("Click by javascript1.0 ", "Click by javascript", "Click by javascript", " Validate Click by javascript",
					" Validate Click by javascript", "Unable to Click by javascript: "+e, "Fail", " ");

		}

		return attributeValue;

	}

	/**
	 * Mose hover action
	 */
	public static void mouseHoverAction(String locatorkey) {

		WebElement element=null;
		String locatorMethod = null;
		String locatorValue = null;
		wait30 =  new WebDriverWait(driver, 30);
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
				element = driver.findElement(By.id((locatorValue)));
				action.moveToElement(element).perform();
				break;
			case "name":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				element = driver.findElement(By.name((locatorValue)));
				action.moveToElement(element).perform();
				break;
			case "class":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				element = driver.findElement(By.className((locatorValue)));

				action.moveToElement(element).perform();
				break;
			case "linkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				element = driver.findElement(By.linkText((locatorValue)));
				action.moveToElement(element).perform();
				break;
			case "partiallinkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				element = driver.findElement(By.partialLinkText((locatorValue)));
				action.moveToElement(element).perform();
				break;
			case "tagname":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				element = driver.findElement(By.tagName((locatorValue)));
				action.moveToElement(element).perform();
				break;
			case "css":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				element = driver.findElement(By.cssSelector((locatorValue)));
				action.moveToElement(element).perform();
				break;
			case "xpath":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				element = driver.findElement(By.xpath((locatorValue)));
				action.moveToElement(element).perform();
				break;

			default:
				break;
			}
		}
		catch(NoSuchElementException e) 
		{
			ReportGenerate.writeResult("mouse Hover Action1.0 ", "mouse Hover Action", "mouse Hover Action", " Validate mouse Hover Action",
					" Validate mouse Hover Action", "Unable to mouse hover Action: "+e, "Fail", " ");

		}


	}



	/**
	 * Webelement
	 */
	public static WebElement webElement(String locatorkey) {

		WebElement element=null;
		String locatorMethod = null;
		String locatorValue = null;
		wait30 =  new WebDriverWait(driver, 30);
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
				element = driver.findElement(By.id((locatorValue)));
				break;
			case "name":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				element = driver.findElement(By.name((locatorValue)));
				break;
			case "class":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				element = driver.findElement(By.className((locatorValue)));
				break;
			case "linkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				element = driver.findElement(By.linkText((locatorValue)));
				break;
			case "partiallinkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				element = driver.findElement(By.partialLinkText((locatorValue)));
				break;
			case "tagname":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				element = driver.findElement(By.tagName((locatorValue)));
				break;
			case "css":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				element = driver.findElement(By.cssSelector((locatorValue)));
				break;
			case "xpath":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				element = driver.findElement(By.xpath((locatorValue)));
				break;

			default:
				break;
			}
		}
		catch(NoSuchElementException e) 
		{
			ReportGenerate.writeResult("webElement1.0 ", "webElement", "GetAttributeValue", " Validate webElement",
					" Validate webElement", "Unable to webElement: "+e, "Fail", " ");

		}
		catch (WebDriverException e)
		{
			ReportGenerate.writeResult("webElement1.0 ", "GetAttributeValue", "webElement", " Validate webElement",
					" Validate webElement", "Unable to webElement: "+e, "Fail", " ");
		} 

		return element;

	}


	public static List<WebElement> ListgetWebElements(String locatorKey) {
		List<WebElement> element = null;
		String locatorMethod = null;
		String locatorValue = null;
		try {
			String[] locatorMethodName = readlocators(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			System.out.println("Issue on locator properties: "+ e);
		}
		try {
			switch (locatorMethod) {
			case "id":
				element = driver.findElements(By.id((locatorValue)));
				break;
			case "name":
				element = driver.findElements(By.name((locatorValue)));
				break;
			case "class":
				element = driver.findElements(By.className((locatorValue)));
				break;
			case "linkText":
				element = driver.findElements(By.linkText((locatorValue)));
				break;
			case "partiallinkText":
				element = driver.findElements(By.partialLinkText((locatorValue)));
				break;
			case "tagname":
				element = driver.findElements(By.tagName((locatorValue)));
				break;
			case "css":
				element = driver.findElements(By.cssSelector((locatorValue)));
				break;
			case "xpath":
				element = driver.findElements(By.xpath((locatorValue)));
				break;

			default:
				break;
			}
		} catch(NoSuchElementException e) 
		{
			ReportGenerate.writeResult("webElement1.0 ", "webElement", " list getelement", " Validate webElement",
					" Validate list webElement", "Unable to webElement: "+e, "Fail", " ");

		}

		return element;
	}


	/**
	 * get text value
	 */
	public static String GetText(String locatorkey) {

		WebElement element=null;
		String locatorMethod = null;
		String locatorValue = null;
		wait30 =  new WebDriverWait(driver, 30);
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
				element = driver.findElement(By.id((locatorValue)));
				textValue = element.getText();
				break;
			case "name":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				element = driver.findElement(By.name((locatorValue)));
				textValue = element.getText();
				break;
			case "class":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				element = driver.findElement(By.className((locatorValue)));
				textValue = element.getText();
				break;
			case "linkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				element = driver.findElement(By.linkText((locatorValue)));
				textValue = element.getText();
				break;
			case "partiallinkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				element = driver.findElement(By.partialLinkText((locatorValue)));
				textValue = element.getText();
				break;
			case "tagname":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				element = driver.findElement(By.tagName((locatorValue)));
				textValue = element.getText();
				break;
			case "css":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				element = driver.findElement(By.cssSelector((locatorValue)));
				textValue = element.getText();
				break;
			case "xpath":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				element = driver.findElement(By.xpath((locatorValue)));
				textValue = element.getText();
				break;

			default:
				break;
			}
		}
		catch(NoSuchElementException e) 
		{
			ReportGenerate.writeResult("GetText1.0 ", "GetTextValue", "GetTextValue", " Validate Get Text Value",
					" Validate Get Text Value", "Unable to  Get Text Value: "+e, "Fail", " ");

		}


		return textValue;

	}

	public static void waitUntilPageLoad() throws InterruptedException {

		wait30 = new WebDriverWait(driver, 30);
		Thread.sleep(4000);
		wait30.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState"
						).equals("complete");
			}
		});
	}

	public static void DragSliderRight(String locatorkey,int value)
	{

		WebElement element = null;
		String locatorMethod = null;
		String locatorValue = null;
		wait30 =  new WebDriverWait(driver, 30);
		action = new Actions(driver);
		try {
			String[] locatorMethodName = readlocators(locatorkey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			ReportGenerate.writeResult("readlocators1.0 ", "DragSliderRight", "DragSliderRight", " Validate DragSliderRight",
					" Validate DragSliderRight", "Unable to DragSliderRight: "+e, "Fail", " ");

		}
		try {

			Thread.sleep(3000);
			switch (locatorMethod) {
			case "id":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorValue)));
				element = driver.findElement(By.id((locatorValue)));
				action.dragAndDropBy(element,value,0).perform();
				break;
			case "name":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				element = driver.findElement(By.name((locatorValue)));
				action.dragAndDropBy(element,value,0).perform();
				break;
			case "class":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				element = driver.findElement(By.className((locatorValue)));
				action.dragAndDropBy(element,value,0).perform();
				break;
			case "linkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				element = driver.findElement(By.linkText((locatorValue)));
				action.dragAndDropBy(element,value,0).perform();
				break;
			case "partiallinkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				element = driver.findElement(By.partialLinkText((locatorValue)));
				action.dragAndDropBy(element,value,0).perform();
				break;
			case "tagname":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				element = driver.findElement(By.tagName((locatorValue)));
				action.dragAndDropBy(element,value,0).perform();
				break;
			case "css":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				element = driver.findElement(By.cssSelector((locatorValue)));
				action.dragAndDropBy(element,value,0).perform();
				break;
			case "xpath":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				element = driver.findElement(By.xpath((locatorValue)));
				action.dragAndDropBy(element,value,0).perform();
				break;

			default:
				break;
			}
		} catch (NoSuchWindowException e) {
			System.out.println("Window Already closed and elment is not visible further ...");
		} catch (Exception e) {
			ReportGenerate.writeResult("DragSliderRight1.0 ", "DragSliderRight", "DragSliderRight", " Validate DragSliderRight",
					" Validate DragSliderRight", "Unable to DragSliderRight: "+e, "Fail", " ");
		}


	}


	public static void DragSliderVertical(String locatorkey,int value)
	{

		WebElement element = null;
		String locatorMethod = null;
		String locatorValue = null;
		wait30 =  new WebDriverWait(driver, 30);
		action = new Actions(driver);
		try {
			String[] locatorMethodName = readlocators(locatorkey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			ReportGenerate.writeResult("readlocators1.0 ", "DragSliderVertical", "DragSliderVertical", " Validate DragSliderVertical",
					" Validate DragSliderVertical", "Unable to DragSliderVertical: "+e, "Fail", " ");

		}
		try {

			Thread.sleep(3000);
			switch (locatorMethod) {
			case "id":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
				element = driver.findElement(By.id((locatorValue)));
				action.dragAndDropBy(element,0,value).build().perform();
				break;
			case "name":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
				element = driver.findElement(By.name((locatorValue)));
				action.dragAndDropBy(element,0,value).build().perform();
				break;
			case "class":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
				element = driver.findElement(By.className((locatorValue)));
				action.dragAndDropBy(element,0,value).perform();
				break;
			case "linkText":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
				element = driver.findElement(By.linkText((locatorValue)));
				action.dragAndDropBy(element,0,value).build().perform();
				break;
			case "partiallinkText":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
				element = driver.findElement(By.partialLinkText((locatorValue)));
				action.dragAndDropBy(element,0,value).build().perform();
				break;
			case "tagname":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
				element = driver.findElement(By.tagName((locatorValue)));
				action.dragAndDropBy(element,0,value).build().perform();
				break;
			case "css":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
				element = driver.findElement(By.cssSelector((locatorValue)));
				action.dragAndDropBy(element,0,value).build().perform();
				break;
			case "xpath":
				wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
				element = driver.findElement(By.xpath((locatorValue)));
				action.dragAndDropBy(element, 0 ,value).build().perform();
				break;

			default:
				break;
			}
		} catch (NoSuchWindowException e) {
			System.out.println("Window Already closed and elment is not visible further ...");
		} catch (Exception e) {
			ReportGenerate.writeResult("DragSliderVertical1.0 ", "DragSliderVerticalt", "DragSliderVertical", " Validate DragSliderVertical",
					" Validate DragSliderVertical", "Unable to DragSliderVertical: "+e, "Fail", " ");
		}


	}


	/**
	 * wait and clear the value and send the value
	 */
	public static void ClearSendValue(String locatorkey,String value) {
		String locatorMethod = null;
		String locatorValue = null;
		wait30 =  new WebDriverWait(driver, 30);
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
				driver.findElement(By.id((locatorValue))).clear();
				driver.findElement(By.id((locatorValue))).sendKeys(value);
				break;
			case "name":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				driver.findElement(By.name((locatorValue))).clear();
				driver.findElement(By.name((locatorValue))).sendKeys(value);
				break;
			case "class":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				driver.findElement(By.className((locatorValue))).clear();
				driver.findElement(By.className((locatorValue))).sendKeys(value);
				break;
			case "linkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				driver.findElement(By.linkText((locatorValue))).clear();
				driver.findElement(By.linkText((locatorValue))).sendKeys(value);
				break;
			case "partiallinkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				driver.findElement(By.partialLinkText((locatorValue))).clear();
				driver.findElement(By.partialLinkText((locatorValue))).sendKeys(value);
				break;
			case "tagname":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				driver.findElement(By.tagName((locatorValue))).clear();
				driver.findElement(By.tagName((locatorValue))).sendKeys(value);
				break;
			case "css":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				driver.findElement(By.cssSelector((locatorValue))).clear();
				driver.findElement(By.cssSelector((locatorValue))).sendKeys(value);
				break;
			case "xpath":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				driver.findElement(By.xpath((locatorValue))).clear();
				driver.findElement(By.xpath((locatorValue))).sendKeys(value);
				break;

			default:
				break;
			}
		}
		catch(NoSuchElementException e) 
		{
			ReportGenerate.writeResult("ClearSendValue1.0 ", "ClearSendValue", "ClearSendValue", " Validate ClearSendValue",
					" Validate ClearSendValue", "Unable to ClearSendValue: "+e, "Fail", " ");

			//System.out.println("Error in click element");
		}
		catch (WebDriverException e)
		{
			ReportGenerate.writeResult("ClearSendValue1.0 ", "ClearSendValue", "ClearSendValue", " Validate ClearSendValue",
					" Validate ClearSendValue", "Unable to ClearSendValue: "+e, "Fail", " ");
		} 

	}



	/**
	 * wait and click on enter button
	 */
	public static void ClickEnter(String locatorkey) {
		String locatorMethod = null;
		String locatorValue = null;
		wait30 =  new WebDriverWait(driver, 30);
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
				driver.findElement(By.id((locatorValue))).sendKeys(Keys.ENTER);
				break;
			case "name":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				driver.findElement(By.name((locatorValue))).sendKeys(Keys.ENTER);
				break;
			case "class":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				driver.findElement(By.className((locatorValue))).sendKeys(Keys.ENTER);
				break;
			case "linkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				driver.findElement(By.linkText((locatorValue))).sendKeys(Keys.ENTER);
				break;
			case "partiallinkText":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				driver.findElement(By.partialLinkText((locatorValue))).sendKeys(Keys.ENTER);
				break;
			case "tagname":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				driver.findElement(By.tagName((locatorValue))).sendKeys(Keys.ENTER);
				break;
			case "css":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				driver.findElement(By.cssSelector((locatorValue))).sendKeys(Keys.ENTER);
				break;
			case "xpath":
				wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				driver.findElement(By.xpath((locatorValue))).sendKeys(Keys.ENTER);
				break;

			default:
				break;
			}
		}
		catch(NoSuchElementException e) 
		{
			ReportGenerate.writeResult("ClickEnter1.0 ", "ClickEnter", "ClickEnter", " Validate ClickEnter",
					" Validate ClickEnter", "Unable to ClickEnter: "+e, "Fail", " ");

		}
		catch (WebDriverException e)
		{
			ReportGenerate.writeResult("ClickEnter1.0 ", "ClickEnter", "ClickEnter", " Validate ClickEnter",
					" Validate ClickEnter", "Unable to ClickEnter: "+e, "Fail", " ");
		} 

	}


	/**
	 * click by xpath
	 * @throws InterruptedException 
	 * 
	 */
	public static void ClickByXpath(String locatebyxpath) throws InterruptedException
	{
		try {
			/*Wait<WebDriver> wait = new FluentWait<WebDriver>(driver) 
					.withTimeout( 30, TimeUnit.SECONDS ) 
					     .pollingEvery( 5, TimeUnit.SECONDS ) 
					     .ignoring( NoSuchElementException.class, StaleElementReferenceException.class ); */
			wait30 =  new WebDriverWait(driver, 30);

			WebElement ele = driver.findElement(By.xpath(locatebyxpath));
			wait30.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatebyxpath)));
			ele.click();
			waitUntilPageLoad();
		}
		catch (NoSuchElementException e) 
		{
			ReportGenerate.writeResult("ClickByXpath1.0 ", "ClickByXpath", "ClickByXpath", " Validate click by xpath",
					" Validate click by xpath", "Unable to click by xpath: "+e, "Fail", " ");	

		}
		catch (StaleElementReferenceException e)
		{
			ReportGenerate.writeResult("ClickByXpath1.0 ", "ClickByXpath", "ClickByXpath", " Validate click by xpath",
					" Validate click by xpath", "Unable to click by xpath: "+e, "Fail", " ");
		} 
	}


	/**
	 * get current URL
	 * 
	 */
	public String getCurrentURL() throws IOException
	{
		try {
			currURL = driver.getCurrentUrl();
		} catch (WebDriverException e)	{
			ReportGenerate.writeResult("get current URL1.0 ", "get current URL", "get current URL", " Validate get current URL",
					" Validate get current URL", "Unable to get the current url", "Fass", " ");
		}
		return currURL;
	}
	
	
	


	/**
	 * Clear and press enter button
	 * 
	 */
	public static void enterValuebyID(String id, String value)
	{
		try	{
			driver.findElement(By.id(id)).clear();
			driver.findElement(By.id(id)).sendKeys(value, Keys.ENTER);
		}
		catch(NoSuchElementException e)
		{
			ReportGenerate.writeResult("EnterValueByID1.0 ", "EnterValueByID", "Enter value by ID", " Validate enter value by ID",
					"  Validate enter value by ID", " Unable to send the value", "Fail", " ");
		}
		catch(WebDriverException e)
		{
			ReportGenerate.writeResult("EnterValueByID1.0 ", "EnterValueByID", "Enter value by ID", " Validate enter value by ID",
					"  Validate enter value by ID", " Unable to send the value", "Fail", " ");
		}
	}




	/**
	 * Signin folens hive tags
	 * @throws IOException 
	 */
	public static void folensSignIn(String username, String password) throws InterruptedException, IOException {

		//enterValuebyID("userName", Constants.username);
		try {
			SendValue("geographyUsername", username);
			SendValue("geographyPassword", password);
			ClickElement("geographySignin");
			Thread.sleep(8000);
			screeshot = screenshot("signIn");
			ReportGenerate.writeResult("Signin1.0 ", "Login", "Login as " +Constants.username , " Validate the signin", 
					" Validate the signin", "Logged successfully", "Pass", screeshot);
			ReportGenerate.Pass("Validate signin functionality - Logged as "+prop.getProperty("gmailaccount")+ " successfully", "Pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportGenerate.writeResult("Signin1.0 ",  "Login as " +Constants.username , " Validate the signin",
					" Validate the signin"," " ,"Unable to login "+ e, "Fail", screeshot);
			ReportGenerate.Fail("Validate signin functionality - Unable to login "+ e, "Fail");
		}

		finally
		{
			ReportGenerate.after();
		}

	}



	/**
	 * click by ID
	 * @throws InterruptedException 
	 * 
	 */
	public static void ClickByID(String id) throws InterruptedException
	{
		try {
			driver.findElement(By.id(id)).click();
			waitUntilPageLoad();
		}
		catch (NoSuchElementException e) 
		{
			ReportGenerate.writeResult("ClickByID1.0 ", "Click by Id", "Click by Id", " Validate click by id",
					" Validate click by id", "Able to click by id", "Pass", " ");	

		}
		catch (WebDriverException e)
		{
			ReportGenerate.writeResult("ClickByID1.0 ", "Click by Id", "Click by Id", " Validate click by id",
					" Validate click by id", "Unable to click by id", "Fail", " ");
		} 
	}



	/**
	 * Verify browser and invoke the browser
	 * @throws IOException 
	 * 
	 */
	public static WebDriver invokeBrowser(String browser) throws InterruptedException, IOException
	{

		try {
			if(browser.toLowerCase().equals(" ie"))
			{
				System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			else if(browser.toLowerCase().equals(" chrome"))
			{
				System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
				//WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			else if(browser.toLowerCase().equals(" firefox"))
			{
				//driver = new FirefoxDriver();
				System.setProperty("webdriver.gecko.driver",".\\driver\\geckodriver.exe" );  
				driver= new FirefoxDriver();
			}
			else if(browser.toLowerCase().equals(" edge"))
			{
				//driver = new FirefoxDriver();
				System.setProperty("webdriver.edge.driver",".\\driver\\msedgedriver.exe" );  
				driver= new EdgeDriver();
			}

			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			/*ReportGenerate.writeResult("Invoke browser1.0 ", "Invoke the browser on"+browser + " browser" , "Invoked the browser", " Validate the browser invoke", 
					"Validate the browser invoke", "Able to invoke "+ browser + " browser", "Pass", "");*/

			ReportGenerate.Pass("Able to invoke "+browser + " browser", "Pass");

		} catch (Exception e) 
		{
			// TO Write the error in Excel
			ReportGenerate.writeResult("Invoke browser1.0 ", "Invoked browser on"+browser + " browser" , "Invoke the browser", " Validate the browser invoke", 
					"Validate the browser invoke", "Unable to invoke browser: "+ e, "Fail", "");
			ReportGenerate.Fail("Validate Invoke browser functionality - Unable to invoke browser: "+ e, "Fail");
		}
		finally
		{
			ReportGenerate.after();
		}
		return driver;
	}


	public static  WebDriver launchURL(String url) throws InterruptedException
	{

		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(4000);

		return driver;
	}

	public static String addScreenCapture() throws IOException, InterruptedException {
		Thread.sleep(2000);

		Date dat = new Date();

		FileName = dat.toString().replace(":", "").replace(" ", "") + ".png";
		TakesScreenshot scrnshot = ((TakesScreenshot) driver);
		File scrFile = scrnshot.getScreenshotAs(OutputType.FILE);

		File Dest = new File(System.getProperty("user.dir") + "\\Reports\\screenshot\\"+ FileName);
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
	}

	public static String screenshot(String filename) throws IOException, InterruptedException
	{
		try {
			Thread.sleep(2000);
			TakesScreenshot scrShot =((TakesScreenshot)driver);

			//Call getScreenshotAs method to create image file

			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

			//Move image file to new destination

			File DestFile=new File(System.getProperty("user.dir") + "\\Reports\\screenshot\\"+filename+".png");

			//Copy file at destination

			FileUtils.copyFile(SrcFile, DestFile);
			spath = DestFile.toString();


		} catch (WebDriverException e)	{
			ReportGenerate.writeResult("Take screen shot", "Take screen shot", "Take screen shot", " Validate screen shot",
					" Validate screen shot", "Unable to take screen shot", "Fail", " ");
		}
		return spath;

	}

	public static String getTextbyid(String id)
	{
		try	{
			textbyid = driver.findElement(By.id(id)).getText();
		}	
		catch(NoSuchElementException e) 
		{
			ReportGenerate.writeResult("GetText by id ", "Get text by id", "Get Text", " Validate get text",
					"Validate get text", "Able to get text", "Pass", " ");
		}
		catch (WebDriverException e)
		{
			ReportGenerate.writeResult("GetText by id ", "Get text by id", "Get Text", " Validate get text",
					"Validate get text", "Unable to get text", "Fail", " ");
		}
		return textbyid;

	}

	public static void Browserquit() throws IOException, ParseException
	{
		try {

			driver.quit();
		} 
		catch (WebDriverException exe)
		{
			ReportGenerate.writeResult("Close the browser ", "Close browser", "Closing all browser", " Validate browser are closing",
					" Validate browser are closing", "Unable to close the browser", "Fail", " ");
		} 	

	}





}
