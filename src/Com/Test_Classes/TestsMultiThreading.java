package Com.Test_Classes;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Com.App_Functions.WebDriver_Instance;

public class TestsMultiThreading {
	
	WebDriver chromeDriver = null;
	WebDriver ieDriver = null;
	WebDriver fireFoxDriver = null;
	WebDriver driver = null;
	WebDriver driver1 = null;
	WebDriver driver2 = null;
	String chromeBrowser1 = null;
	WebDriver chromeDriver2 = null;
	String fireFoxBrowser = null;
	String ieBrowser = null;
	HashMap<String,WebDriver> drivers = new HashMap<String,WebDriver>();
	    @Parameters({"browser"})
	    @BeforeTest(groups = { "smoke" })
	    public void getTheDriver(String browser) throws MalformedURLException{	
	    	if(browser.equals("Chrome")){
	    		chromeDriver = WebDriver_Instance.getDriverInstance(browser);    		
	    	}
	    	else if(browser.equals("Firefox")){

	    		fireFoxDriver = WebDriver_Instance.getDriverInstance(browser);
	    		
	    	}
	         else if(browser.equals("InternetExplorer")){

	    		 ieDriver = WebDriver_Instance.getDriverInstance(browser);

	    	}
	    	
	    }
	    @Parameters({"browser"})
		@Test(groups = { "smoke" })
		public void fetchTheData(String browser){		
		try{ 		
			
		drivers.put("Chrome",chromeDriver);
		drivers.put("Firefox",fireFoxDriver);
		drivers.put("InternetExplorer",ieDriver);
		drivers.get(browser).get("https://www.techlistic.com/p/demo-selenium-practice.html");
		System.out.println("rowIndex Driver is  : " + drivers.get(browser));
		System.out.println("colIndex Driver is  : " + drivers.get(browser));
		getThevalueforMatchingRowAndColumn(getTheRowIndex("Burj Khalifa" , drivers.get(browser)),
				getTheColumnIndex("Built",drivers.get(browser)),drivers.get(browser));
		System.out.println("match value Driver is  : " + drivers.get(browser));			
		}catch(Exception e){
			
		  }
		}
	    @Parameters({"browser"})
	    @Test(groups = {"smoke"})
	    public void homeButton(String browser) throws MalformedURLException, Throwable{
	    
	    drivers.get(browser).findElement(By.xpath("//a[contains(text(),'Home')]")).click();
	    System.out.println("mhomeButton clicked and Driver is  : " + drivers.get(browser));
	    }
	    @Parameters({"browser"})
		@AfterTest()
		public void closeBrowser(String browser){    	  
				   
	    	if(drivers.get(browser) != null){
	    		drivers.get(browser).close();
	      		}
			}	    

		public  int getTheColumnIndex(String colName , WebDriver driver){
			int index = -1;
			WebDriverWait wait = new WebDriverWait(driver, 4);
			List<WebElement> col = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//thead/tr//th")));
			for(int  i=0 ; i < col.size() ; i++){
			
				if(col.get(i).getText().equals(colName)){
					index = i;
					//System.out.println("ihohre " + i);
					
					break;
				}
				
			}
			return index;
		}
		
		public  int getTheRowIndex (String rowName,WebDriver driver){
			int index = -1;
			WebDriverWait wait = new WebDriverWait(driver, 4);
			List<WebElement> row = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tbody/tr//th")));
			for(int  i=0 ; i < row.size() ; i++){
				//System.out.println("ihohre " + i);
				if(row.get(i).getText().equals(rowName)){
					index = i;
					break;
				}
				
			}
			
			return index + 1;
			
		}
		
		public  void getThevalueforMatchingRowAndColumn(int rowIndex, int colIndex, WebDriver driver){
			
			WebElement elementInTable = driver.findElement(By.xpath
					("//tr["+rowIndex+"]/td["+colIndex+"]"));
					
			System.out.println(elementInTable.getText());
		}
	    
		

}
