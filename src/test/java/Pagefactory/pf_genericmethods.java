package Pagefactory;

import org.openqa.selenium.WebElement;

public class pf_genericmethods {
	
//	to enter text in textbox
	public void cl_entertext(WebElement ele,String text){
		ele.sendKeys(text);
	}
	
	
	
//	to click on any element
	public void cl_click(WebElement ele){
		ele.click();
	}
	
	
	
	

}
