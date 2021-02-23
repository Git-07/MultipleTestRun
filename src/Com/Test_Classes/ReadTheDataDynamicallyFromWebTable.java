package Com.Test_Classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Com.App_Functions.Logging;
import Com.App_Functions.Properties_Read;
import Com.App_Functions.WebDriver_Instance;

public class ReadTheDataDynamicallyFromWebTable extends WebDriver_Instance {
	
	
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
	
    
/*	public static void main(String [] str) throws IOException{	
	System.setProperty("webdriver.chrome.driver", "D://workspace//CucmberFramework//Drivers//chromedriver.exe");
	DesiredCapabilities dc = DesiredCapabilities.chrome();	
	ChromeOptions options = new ChromeOptions();
	options.addArguments("start-maximized");
	options.addArguments("dsable-extensions");
	driver = new ChromeDriver(options);
	String url = "https://www.techlistic.com/p/demo-selenium-practice.html";
	driver.get(url);
	driver.quit();
	}*/
	//driver.manage().window().maximize();
/* @BeforeSuite(groups = { "smoke", "functional" })
 public void getTheProperty() throws IOException {
	try {
		Properties_Read pr = new Properties_Read();
		_url = pr.propertyRead("./configs//Configuration.properties").getProperty("Url");
		Logging.getTheLogForPropertyFileRead("Url is retrieved from properties file",
				"Url is not retrived from properties file", _url);
		String _testDataFilePath = pr.propertyRead("./configs//Configuration.properties").getProperty("testDataFile");
		Logging.getTheLogForPropertyFileRead("test Data file path is retrieved",
				"test data file path is not retrieved from properties file", _testDataFilePath);
	} catch (FileNotFoundException e) {
		e.getMessage();
	}
}*/
  /*  @Parameters({"browser"})
    @BeforeMethod(groups = { "smoke" })
    public void getTheDriver(String browser) throws MalformedURLException{
    
    	if(browser.equals("Chrome")){
    		
    		//chromeBrowser = browser;
    		chromeDriver = WebDriver_Instance.getDriverInstance();    		
    		driver = chromeDriver;
    		
    	}
    	else if(browser.equals("Firefox")){
    		
    		//fireFoxBrowser = browser;
    		fireFoxDriver = WebDriver_Instance.getDriverInstance();
    		driver = fireFoxDriver; 
    		
    	}
         else if(browser.equals("InternetExplorer")){
    		
        	 //ieBrowser = browser;
    		 ieDriver = WebDriver_Instance.getDriverInstance();
    		 driver = ieDriver; 
    		
    	}
    	
    }*/
   // @Parameters({"browser"})
	@Test(groups = { "smoke" })
	public void fetchTheData(){		
	try{
		
     driver1 = WebDriver_Instance.getDriverInstance();     		

 	driver1.get("https://www.techlistic.com/p/demo-selenium-practice.html");
/*	SessionId sessionChrome1 = ((ChromeDriver)driver1).getSessionId(); 
    System.out.println("Session in fecth data : " + sessionChrome1);*/
	//driver.get(_url);
	//int rowIndex = getTheRowIndex("Burj Khalifa" , driver);
	System.out.println("rowIndex Driver is  : " + driver1);
	//System.out.println("kjvbjkevjkbvejkbv " + rowIndex);
	//int colIndex = getTheColumnIndex("Built",driver);
	System.out.println("colIndex Driver is  : " + driver1);
	//System.out.println("kjvbjkevjkbvejkbv " + colIndex);
	getThevalueforMatchingRowAndColumn(getTheRowIndex("Burj Khalifa" , driver1),
			getTheColumnIndex("Built",driver1),driver1);
	System.out.println("match value Driver is  : " + driver1);
    driver = driver1;
	//Thread.sleep(6000);
	//driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
	//driver.close();
	//driver.manage().deleteAllCookies();
	}catch(Exception e){
		
	  }
	}
    //@Parameters({"browser"})
    @Test(groups = {"smoke"})
    public void homeButton() throws MalformedURLException, Throwable{
 	//if(browser.equals("Chrome")){
    		
    		//chromeBrowser = browser;
 		driver2 = WebDriver_Instance.getDriverInstance();    		
    		//driver2 = chromeDriver2;
    	driver2.get("https://www.techlistic.com/p/demo-selenium-practice.html");
    		
    	//}
   /* 	SessionId sessionChrome = ((ChromeDriver)driver2).getSessionId(); 
    	System.out.println("Session in home : " + sessionChrome);*/
    	driver2.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
    	System.out.println("mhomeButton clicked and Driver is  : " + driver2);
    	Thread.sleep(200);
    	driver = driver2;
    }
    
	@AfterMethod(groups = {"smoke"})
	public void closeBrowser(){    	  
    	   
    	if(driver != null){
      		driver.close();
      		}
    }
	/*try{
  	if(browser.equals("Chrome")){
    		
  		
  		
    	
  	}
    	
  	else if(browser.equals("Firefox")){
    	
  		SessionId session = ((FirefoxDriver)driver).getSessionId();
  		if(session != null){
    	driver.close();
  		}
  	}
    	
  	else if(browser.equals("InternetExplorer")){
   		  
  		driver.close();
       	  	
  	}
  	
		
	}catch(Exception e){}
	
	}*/
    
 /*   @AfterTest(groups = {"smoke"})
    public void activeSession(){
    	
    	 SessionId session1 = ((FirefoxDriver)driver).getSessionId();    	 
    	 SessionId session2 = ((ChromeDriver)driver).getSessionId();
    	 SessionId session3 = ((InternetExplorerDriver)driver).getSessionId();
    	 System.out.println("FirefoxDriver : " + session1);
    	 System.out.println("ChromeDriver : " + session2);
    	 System.out.println("InternetExplorerDriver :" + session3);
    	//driver.quit();
    	SessionId sessionChrome = ((ChromeDriver)driver1).getSessionId(); 
    	System.out.println("Session active after quit: " + sessionChrome);
    	SessionId sessionChrome2 = ((ChromeDriver)driver2).getSessionId(); 
    	System.out.println("Session active after quit: " + sessionChrome2);
    	
    
    }*/


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
