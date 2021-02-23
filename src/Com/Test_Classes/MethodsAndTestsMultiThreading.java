package Com.Test_Classes;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Com.App_Functions.WebDriver_Instance;


public class MethodsAndTestsMultiThreading {
			
	Map<String, ArrayList<WebDriver>> drivers = new HashMap<String,ArrayList<WebDriver>>();		
	@Parameters({"browser","runParallel","threads","mode"})
	@BeforeTest()
	public Map<String, ArrayList<WebDriver>> createBrowserInstance(String browser, String runParallel,
       String threads,String mode) throws MalformedURLException {
	    ArrayList<WebDriver> driver = new ArrayList<WebDriver> ();							
		for (int i = 0; i < Integer.valueOf(threads); i++) {			
			driver.add(WebDriver_Instance.getDriverInstance(mode,browser));
			System.out.println("browser is  " + browser);			 
		}
		drivers.put(browser, driver);
		return drivers;
	}

	@Parameters({"browser","runParallel","threads"})
	@Test
	public void fetchTheData(String browser, String runParallel, String threads){		
	try{
		int indexMethodLevel = 0;
		if(runParallel.equals("methods")){
			indexMethodLevel = 0;
		}
	drivers.get(browser).get(indexMethodLevel).get("https://www.techlistic.com/p/demo-selenium-practice.html");	
	System.out.println("rowIndex Driver is  : " + drivers.get(browser).get(indexMethodLevel));
	System.out.println("colIndex Driver is  : " + drivers.get(browser).get(indexMethodLevel));
	getThevalueforMatchingRowAndColumn(getTheRowIndex("Burj Khalifa" , drivers.get(browser).get(indexMethodLevel)),
			getTheColumnIndex("Built",drivers.get(browser).get(indexMethodLevel)),drivers.get(browser).get(indexMethodLevel));
	System.out.println("match value coming from the Driver is  : " + drivers.get(browser).get(indexMethodLevel));			
	}catch(Exception e){
		
	  }
	}
    @Parameters({"browser","runParallel","threads"})
    @Test
    public void homeButton(String browser,String runParallel, String threads) throws MalformedURLException, Throwable{
    try{	
    	int indexMethodLevel = 0;
    	if(runParallel.equals("methods") && Integer.valueOf(threads) >= 2){
		indexMethodLevel = 1;			
		}
    drivers.get(browser).get(indexMethodLevel).get("https://www.techlistic.com/p/demo-selenium-practice.html");
    WebDriverWait wait = new WebDriverWait(drivers.get(browser).get(indexMethodLevel),30);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Home')]"))).click();
    System.out.println("mhomeButton clicked and Driver is  : " + drivers.get(browser).get(indexMethodLevel));
    }catch(NoSuchElementException e){}
}
    
    @Parameters({"browser","runParallel","threads"})
    @Test
    public void top10Button(String browser,String runParallel, String threads) throws MalformedURLException, Throwable{
    	try{
    	int indexMethodLevel = 0;
    	if(runParallel.equals("methods") && Integer.valueOf(threads) >= 3){
		indexMethodLevel = 2;		
		}
    drivers.get(browser).get(indexMethodLevel).get("https://www.techlistic.com/p/demo-selenium-practice.html");
    WebDriverWait wait = new WebDriverWait(drivers.get(browser).get(indexMethodLevel),30);
    WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Top 10')]")));
    ele.click();
    System.out.println("Top 10 clicked and Driver is  : " + drivers.get(browser).get(indexMethodLevel));
    	}catch(NoSuchElementException e){}
    	}
    
    @Parameters({"browser","threads"})
	@AfterTest()
	public void closeBrowser(String browser, String threads){    	  
			   
    	for(int i = 0 ; i< Integer.valueOf(threads) ; i++){
    		
    		
    	if(drivers.get(browser).get(i) != null){
    		System.out.println("Browser : " + browser + " Session Closed : " + drivers.get(browser).get(i));
    		drivers.get(browser).get(i).quit();
      		
    	}
		}	    
    }
	public  int getTheColumnIndex(String colName , WebDriver driver){
		int index = -1;
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
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
		WebDriverWait wait = new WebDriverWait(driver, 60);
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
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement elementInTable = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath
				("//tr["+rowIndex+"]/td["+colIndex+"]")));
				
		System.out.println(elementInTable.getText());
	}
    
	
}
