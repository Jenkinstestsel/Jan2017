package Testscript;

import java.util.Map74747474;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Genericlibrary.Base;
import Genericlibrary.grid_base;
import Pagefactory.Pf_Productdetails;
import Pagefactory.pf_Cartpage;
import Pagefactory.pf_HomePage;

public class script_cart extends grid_base {
	
	
	@Test(dataProvider="Addbook",dataProviderClass=Dataproviders.dp_cart.class,enabled=true,groups={"UAT","Reg"})
	public void addbook(Map hm) throws Exception{
		
		
		tcid = hm.get("TC_ID").toString();
		order=hm.get("Order").toString();		
		String bookname = hm.get("bookname").toString();
		 startTest = extentReports.startTest(tcid+"_" + order + "_" + browser_type);
		
		 
		cv_contains(fd.getTitle(), "rediff", "Validating home page title"); 
//		**************HOme page ********************
		pf_HomePage pf_HomePage = new pf_HomePage(fd);		
		pf_HomePage.searchoperation(bookname);
		
		waits(pf_HomePage.msg_srchcount);
		
		pf_HomePage.cl_click(pf_HomePage.img_book);
		
//		*************Product deails page *****************************
		Pf_Productdetails pf_Pd= new Pf_Productdetails(fd);
		cv_contains(fd.getTitle(), "fire", "Validating product details page"); 
		pf_Pd.cl_click(pf_Pd.btn_buynow);
		
//		***************Cart Page************************************8
		pf_Cartpage pf_Cartpage = new pf_Cartpage(fd);
		fd.switchTo().frame(pf_Cartpage.frm_cart);
		Thread.sleep(3000);
		
		if(pf_Cartpage.msg_book.isDisplayed() == true){
			
			startTest.log(LogStatus.PASS, "Book avialable validation","Passed as the book is found" + startTest.addScreenCapture(getScreenshot()));
			
		}else{
			
			startTest.log(LogStatus.FAIL, "Book avialable validation","Failed as the book is not found" + startTest.addScreenCapture(getScreenshot()));
			
		}
		
		fd.switchTo().defaultContent();
		
		
		
		
	}
	
	
	
	
	

}
