package Genericlibrary;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class grid_base {

	public WebDriver fd;
	public static ExtentReports extentReports;
	public ExtentTest startTest;
	public String tcid;
	public String order;
	public String browser_type;
	
	
	@BeforeSuite(groups={"Smk","UAT","Reg"})
	public void create_Report(){
		
		extentReports = new ExtentReports("E:\\DecFramework\\Report\\Ecommerce_"+get_datetimestamp() +".html",false);
		
	}
	
	@Parameters({"config"})
	@BeforeMethod(groups={"Smk","UAT","Reg"})
	public void launchApp(String ctype) throws Exception{
		
		DesiredCapabilities cap = new DesiredCapabilities();
		if(ctype.equals("c1")){
			browser_type="chrome";
			cap.setCapability("browserName", "chrome");
			cap.setCapability("platform", "WINDOWS");	
			
		}if(ctype.equals("c2")){
			browser_type="firefox";
			cap.setCapability("browserName", "firefox");
			cap.setCapability("platform", "WINDOWS");
			
		}if(ctype.equals("c3")){
			browser_type="ie";
			cap.setCapability("browserName", "internet explorer");
			cap.setCapability("platform", "WINDOWS");		
			
		}
		
		
		
		fd = new RemoteWebDriver(new URL("http://192.168.0.15:4444/wd/hub"),cap);
		
		
//		fd= new FirefoxDriver();
		fd.get(Utility.getval(Utility.getval("env")));
		fd.manage().window().maximize();
		fd.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		
	}
	
	
	@AfterMethod(groups={"Smk","UAT","Reg"})
	public void cloaseApp(){	
		
		fd.close();
		
		extentReports.endTest(startTest);
		extentReports.flush();
		
	}
	
	public String get_datetimestamp(){
		Date date = new Date();
//		format date
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss");
//		
		 String format = dateFormat.format(date);
		 return format;
	}
	
//	capture snapshot
	public String getScreenshot() throws Exception{
		
		TakesScreenshot sc=(TakesScreenshot)fd;
		File screenshotAs = sc.getScreenshotAs(OutputType.FILE);
		
		String fpath = Utility.getval("Screenshot_path") + tcid + "_" + order + "_" + get_datetimestamp() +".png";
		FileUtils.copyFile(screenshotAs, new File(fpath));
		return fpath;
				
	}
	
//	explicit wait
	public void waits(WebElement ele){
		WebDriverWait wait = new WebDriverWait(fd,120);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	
	
	
	
	
//	Common Validation
//	equals
	public void cv_equals(String actual,String expected,String stepname) throws Exception{
		
		if(actual.equals(expected)){
			startTest.log(LogStatus.PASS, stepname , "Passed as the Step "  + stepname + " ." + startTest.addScreenCapture(getScreenshot()));
			
		}else{
			
			startTest.log(LogStatus.FAIL, stepname , "Failed the Step " +stepname+ " as the actual value is " + actual + " and the expected is " + expected  + startTest.addScreenCapture(getScreenshot()));
		}
		
				
	}
	
	
//	contains
public void cv_contains(String actual,String expected,String stepname) throws Exception{
		
		if(actual.contains(expected)){
			startTest.log(LogStatus.PASS, stepname , "Passed as the Step "  + stepname + " ." + startTest.addScreenCapture(getScreenshot()));
			
		}else{
			
			startTest.log(LogStatus.FAIL, stepname , "Failed the Step " +stepname+ " as the actual value is " + actual + " and the expected is " + expected  + startTest.addScreenCapture(getScreenshot()));
		}
		
				
	}
}
