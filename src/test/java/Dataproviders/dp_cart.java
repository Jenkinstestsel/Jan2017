package Dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import Genericlibrary.Utility;

public class dp_cart {
	
	
//	dp for Add book
	@DataProvider(name="Addbook")
	public static Iterator<Object[]> validSearch() throws Exception{
		
		return Utility.dp_commonlogic("Cart", "Addbook");
			
	}
	
	
	
	
	

}
