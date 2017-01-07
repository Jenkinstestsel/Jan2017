package Pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pf_Productdetails extends pf_genericmethods {
	
	@FindBy(className="buynowbtnbig")
	public WebElement btn_buynow;
	
	public Pf_Productdetails(WebDriver driver){
		
		PageFactory.initElements(driver, this);
	}
		

}
