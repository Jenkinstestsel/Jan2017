package Pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pf_Cartpage extends pf_genericmethods{	
	
	@FindBy(xpath = "//span[contains(text(),'Flood and Fire')]")	
	public WebElement msg_book;
	
	@FindBy(id="frmCart")
	public WebElement frm_cart;
	
	public pf_Cartpage(WebDriver driver){
		
		PageFactory.initElements(driver, this);
	}
		
	

}
