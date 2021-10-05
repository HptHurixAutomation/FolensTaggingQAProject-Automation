package kitabooTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ReaderNew extends GenericMethod{
	static String open_book_xpath;
	public static String book_title_input, menu_names, tagname1,tagname2,tagname3,entryname,search_text, book_title, screeshot, line,xml_link, isbn, bookTitle, categoryBook, basicxpath, actualxpath, listxpath;
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
			Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\AutoIT\\testAuto2.exe");
			Thread.sleep(35000);
			Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\AutoIT\\testAuto2.exe");
			Thread.sleep(35000);
			containers = ListgetWebElements("addQuestionsContainer");
			String addquestContainer = "//div[@class='item-container']/div[2]/div/div[";

			for(int i=43;i<=300;i++)
			{

				if(i>1)
				{
					Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\AutoIT\\testAuto2.exe");
					Thread.sleep(30000);
					/*Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\AutoIT\\testAuto2.exe");
					Thread.sleep(33000);*/
					WebElement assenmentName = driver.findElement(By.xpath(addquestContainer+(i)+"]/div/div/div[1]/p"));
					String asstName= assenmentName.getText();
					System.out.println("Assessment name is: "+asstName);

					driver.findElement(By.xpath(addquestContainer+(i)+"]/div/div/div[2]/div[2]")).click();
					Thread.sleep(2000);

					driver.findElement(By.xpath(addquestContainer+(i)+"]/div/div/div[2]/div[3]/div/div[5]")).click();
					Thread.sleep(2000);
					try {
						confirmationMessage();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						System.out.println("Alert is not present");
						/*ReportGenerate.writeResult("Tag Validations", "Folens hive", "Verify tags in standard test", " ",
								"common question confirmation yes",  "Error on confirmation yes: "+e2, "Fail", screeshot);
						ReportGenerate.Fail("Verify tags on standard test - error on confirmation yes "+e2, "Fail");*/
					}
					Thread.sleep(2000);
					waitForElementToBeClickable("cancelQuestion");

					List<WebElement> questlist = ListgetWebElements("questionsList");
					String edittests = "//div[contains(@class,'ui-sortable')]/div[";

					for(int j=1;j<=questlist.size();j++)
					{
						Thread.sleep(7000);
						JavascriptExecutor js = (JavascriptExecutor) GenericMethod.driver;
						WebElement questEditSymb = GenericMethod.driver.findElement(By.xpath(edittests+(j)+"]/div[2]/div/div[3]/div/div/div/div/img"));
						js.executeScript("window.scrollBy(0,550)");
						questEditSymb.click();
						Thread.sleep(6000);
						WebElement qEdit = GenericMethod.driver.findElement(By.xpath(edittests+(j)+"]/div[2]/div/div[3]//div[text()='Edit']"));
						qEdit.click();
						Thread.sleep(4000);
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
						Thread.sleep(3000);
						waitForElementToBeClickable("questionTags");
						List<WebElement> taglist = driver.findElements(By.xpath("//div[contains(@class,'assmntSetMain')]/div"));
						String basicXpath = "//div[contains(@class,'assmntSetMain')]/div[";

						for(int x=1; x<=taglist.size();x++)
						{
							if(x>4)
							{
								Thread.sleep(5000);

								try {
									WebElement tags = GenericMethod.driver.findElement(By.xpath(basicXpath+(x)+"]/div[1]/span[2]"));
									if(tags.isDisplayed())
									{
										tagname1 = tags.getText();
										System.out.println("Title name: "+asstName+" tagname: "+tagname1);

										/*
										 * ReportGenerate.writeResult("Tag Validations", "Folens hive",
										 * "Verify tags in standard test", " ", " Test title: "+asstName, " , "+
										 * tagname1 , "Pass", screeshot);
										 * ReportGenerate.Pass("Validate standard test functionality - Test title: "
										 * +asstName+" , "+ tagname1, "Pass");
										 */

									}


									WebElement tags1 = GenericMethod.driver.findElement(By.xpath(basicXpath+(x)+"]/div[2]/span[2]"));
									if(tags1.isDisplayed())
									{
										tagname2 = tags1.getText();
										System.out.println("Title name: "+asstName+" tagname2: "+tagname2);
										/*
										 * ReportGenerate.writeResult("Tag Validations", "Folens hive",
										 * "Verify tags in standard test", " ", " Test title: "+asstName, " , "+
										 * tagname2 , "Pass", screeshot);
										 * ReportGenerate.Pass("Validate standard test functionality - Test title: "
										 * +asstName+" , "+ tagname2, "Pass");
										 */
									}


									WebElement tag2 = GenericMethod.driver.findElement(By.xpath(basicXpath+(x)+"]/div[3]/span[2]"));

									if(tag2.isDisplayed())
									{
										tagname3 = tag2.getText();
										System.out.println("Title name: "+asstName+" tagname3: "+tagname3);

									}
									ReportGenerate.writeResult("Tag Validations", "Test title: "+asstName," ", " "+ tagname1, " "+ tagname2,
											"" + tagname3, "Pass", screeshot);
									ReportGenerate.Pass("Validate standard test functionality - Test title: "+asstName+" ,"+ tagname1+ ", "+tagname2+", "+tagname3, "Pass");

								} catch (Exception e) {
									ReportGenerate.writeResult("Tag Validations", "Test title: "+asstName," ", " "+ tagname1, " "+ tagname2,
											"" + tagname3, "Fails", screeshot);
									ReportGenerate.Pass("Validate standard test functionality - Test title: "+asstName+",,No tags found", "Fail");
								}

							}

							else
							{
								System.out.println("Tags are not present");
							}

						}

						Thread.sleep(4000);
						WebElement chidEdits = null;

						try {
							chidEdits = GenericMethod.driver.findElement(By.xpath("//div[contains(@class,'comprehensiveBodyBackgorundColor')]/div[2]"));

							if(chidEdits.isDisplayed())
							{
								List<WebElement> childquest = GenericMethod.driver.findElements(By.xpath("//div[contains(@class,'comprehensiveBodyBackgorundColor')]/div"));
								String childXpath = "//div[contains(@class,'comprehensiveBodyBackgorundColor')]/div[";
								for(int m=2; m<=childquest.size(); m++)
								{
									WebElement childEditSymb = GenericMethod.driver.findElement(By.xpath(childXpath+(m)+"]/div[4]/div[1]/div/img"));
									JavascriptExecutor js1 = (JavascriptExecutor) GenericMethod.driver;
									js1.executeScript("arguments[0].click();", childEditSymb);
									confirmationMessage();
									Thread.sleep(3000);

									waitForElementToBeClickable("editQuestion");
									ClickElement("editQuestion");

									waitForElementToBeClickable("questionTags");
									List<WebElement> taglist1 = driver.findElements(By.xpath("//div[contains(@class,'assmntSetMain')]/div"));
									String basicXpath1 = "//div[contains(@class,'assmntSetMain')]/div[";

									for(int x=1; x<=taglist1.size();x++)
									{
										if(x>4)
										{
											Thread.sleep(6000);

											try {
												WebElement tags = GenericMethod.driver.findElement(By.xpath(basicXpath1+(x)+"]/div[1]/span[2]"));
												if(tags.isDisplayed())
												{
													tagname1 = tags.getText();
													System.out.println("Title name: "+asstName+" tagname: "+tagname1);

													/*
													 * ReportGenerate.writeResult("Tag Validations", "Folens hive",
													 * "Verify tags in standard test", " ", " Test title: "+asstName, " , "+
													 * tagname1 , "Pass", screeshot);
													 * ReportGenerate.Pass("Validate standard test functionality - Test title: "
													 * +asstName+" , "+ tagname1, "Pass");
													 */

												}

												WebElement tags1 = GenericMethod.driver.findElement(By.xpath(basicXpath1+(x)+"]/div[2]/span[2]"));
												if(tags1.isDisplayed())
												{
													tagname2 = tags1.getText();
													System.out.println("Title name: "+asstName+" tagname2: "+tagname2);
													/*
													 * ReportGenerate.writeResult("Tag Validations", "Folens hive",
													 * "Verify tags in standard test", " ", " Test title: "+asstName, " , "+
													 * tagname2 , "Pass", screeshot);
													 * ReportGenerate.Pass("Validate standard test functionality - Test title: "
													 * +asstName+" , "+ tagname2, "Pass");
													 */
												}

												WebElement tag2 = GenericMethod.driver.findElement(By.xpath(basicXpath1+(x)+"]/div[3]/span[2]"));

												if(tag2.isDisplayed())
												{
													tagname3 = tag2.getText();
													System.out.println("Title name: "+asstName+" tagname3: "+tagname3);

												}
												ReportGenerate.writeResult("Tag Validations", "Test title: "+asstName," ", " "+ tagname1, " "+ tagname2,
														"" + tagname3, "Pass", screeshot);
												ReportGenerate.Pass("Validate standard test functionality - Test title: "+asstName+" ,"+ tagname1+ ", "+tagname2+", "+tagname3, "Pass");

											} catch (Exception e) {
												ReportGenerate.writeResult("Tag Validations", "Test title: "+asstName," ", " "+ tagname1, " "+ tagname2,
														"" + tagname3, "Fails", screeshot);
												ReportGenerate.Pass("Validate standard test functionality - Test title: "+asstName+",,No tags found", "Fail");
											}
										}

										else
										{
											System.out.println("Tags are not present");
										}
									}

									screeshot = screenshot("tagsValidation");
									Thread.sleep(4000);
									ClickElement("questionsBack");
									Thread.sleep(15000);

								}
							}
							else
							{
								Thread.sleep(2000);
								ClickElement("questionsBack");
								Thread.sleep(15000);
							}
						}catch (Exception e) {
							System.out.println("there is no child questions and tags");
						}
						try {
							Thread.sleep(2000);
							waitForElementToBeClickable("questionsBack");
							ClickElement("questionsBack");
							Thread.sleep(8000);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					Thread.sleep(2000);
					waitForElementToBeClickable("mainQuestionBack");
					ClickElement("mainQuestionBack");
					Thread.sleep(8000);
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

		Thread.sleep(5000);


		try {
			waitForElementToBeClickable("confirmationYes");
			ClickElement("confirmationYes");
		} catch (Exception e) {

			waitForElementToBeClickable("anotherConfirYes");
			ClickElement("anotherConfirYes");
		}


	}


}
