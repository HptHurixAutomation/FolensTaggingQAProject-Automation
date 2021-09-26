package kitaboomain;

import java.text.ParseException;
import java.util.ArrayList;


 public class ConstantsOld extends TestDataOld {
	
	public static String URL, accesscode, client, username, password, browser, book_type, book_title,  tc1, tc2, tc3, tc4, tc5, tc6, tc7, chromeProfile, sheetname, fos;
	
	ArrayList<ArrayList<String>> data = TestDataOld.getSheet();
	
	public ConstantsOld() 
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
		
		tc4 = data.get(1).get(10);
		
		tc5 = data.get(1).get(11);
		
		tc6 = data.get(1).get(12);
		
		tc7 = data.get(1).get(13);
		
		
		/*
		 * try { fos = "Result_"+sheetname+"_"+genericMethod.simpleDate(); } catch
		 * (ParseException e) {
		 * 
		 * e.printStackTrace(); }
		 */
		
		
		chromeProfile = "C:\\Users\\komalavalli.n\\AppData\\Local\\Google\\Chrome\\User Data\\BackupDefault";
	}

}	

