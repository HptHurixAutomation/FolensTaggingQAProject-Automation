package kitabooTest;

import java.util.ArrayList;

import kitabooTest.Constants;
import kitabooTest.TestData;



public class Login extends GenericMethod {
	
	static ArrayList<ArrayList<String>> data = TestData.getSheet();
	static Constants value = new Constants();
	static String currentURL;
  //@Test
   
  /*public static void signIn() throws InterruptedException {
	  
	  enterValuebyID("userName", Constants.username);
	  enterValuebyID("passwordField", Constants.password);
	  ClickByID("signIn");
	  Thread.sleep(4000);
	  currentURL = driver.getCurrentUrl();
	 
	  if(currentURL.contains("bookshelf"))
	  {
		  ReportGenerate.writeResult("", "", "", "Login Successful", " ", "Pass"); 
		  System.out.println("Pass");
	  }
	  else
		  ReportGenerate.writeResult("", "", "", "Login Successful", " ", "Fail"); 
  }
  
 public void accessCode() {
	  
	 //Click access code tab and enter access code
	  
	  ClickByID("tab-item-244");
	  enterValuebyID("accessUsername", Constants.accesscode);
  }*/
	
	
	/*public static void invalidSignIn() throws InterruptedException, IOException
	{
		//enterValuebyID("userName", Constants.username);
				SendValue("userName", prop.getProperty("TeacherUsername"));
		
		//enterValuebyID("passwordField", Constants.password);
		
		SendValue("passWord", prop.getProperty("TeacherInvalidPassword"));
		
		
		//ClickByID("signIn");
		ClickElement("signInButton");
		waitUntilPageLoad();
		Thread.sleep(2000);
		currentURL = driver.getCurrentUrl();

		if(currentURL.contains("bookshelf"))
		{
			Thread.sleep(1000);
			screeshot = screenshot("signIn");
			ReportGenerate.writeResult("Signin1.0 ", "Login", "Login as " +Constants.username , " Validate the signin", 
					" Validate the signin", "Logined successfully", "Fail", screeshot);
			
			ReportGenerate.Fail("Validate invalid signin funcitonality - Logined successfully", "Fail");
		}
		else
		
			ReportGenerate.writeResult("Signin1.0 ", " ",  "Login as " +Constants.username , " Validate the signin", 
					" Validate the signin", "Unable to login", "Pass", screeshot);
		
		ReportGenerate.Pass("Validate invalid signin funcitonality - Unable to login", "Pass");
		ReportGenerate.after();
	}*/
	
}
