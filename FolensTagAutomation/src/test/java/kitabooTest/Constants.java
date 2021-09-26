package kitabooTest;

import java.util.ArrayList;


 public class Constants extends TestData {
	
	public static String URL, accesscode, client, username, password, browser, book_type, book_title,  tc1, tc2, tc3, bookshelf, tc5, tc6, tc7, chromeProfile, sheetname, fos;
	
	ArrayList<ArrayList<String>> data = TestData.getSheet();
	
	public Constants() 
	{

		URL =  data.get(1).get(0);
	
		client = data.get(1).get(1);
		
		username = data.get(1).get(2);
	
		password = data.get(1).get(3);

		browser = data.get(1).get(4);
		
		accesscode = data.get(1).get(5);

		book_type = data.get(1).get(6);
				
		tc1 = data.get(1).get(7);
		
		tc2 = data.get(1).get(8);
		
		tc3 = data.get(1).get(9);
		
		bookshelf = data.get(1).get(10);
		
		tc5 = data.get(1).get(11);
		
		tc6 = data.get(1).get(12);
		
		tc7 = data.get(1).get(13);
		
		
		/*
		 * try { fos = "Result_"+sheetname+"_"+genericMethod.simpleDate(); } catch
		 * (ParseException e) {
		 * 
		 * e.printStackTrace(); }
		 */
		
		
		//chromeProfile = "C:\\Users\\komalavalli.n\\AppData\\Local\\Google\\Chrome\\User Data\\BackupDefault";
	}

}	

