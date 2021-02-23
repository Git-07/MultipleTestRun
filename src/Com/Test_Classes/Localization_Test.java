package Com.Test_Classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Com.Page_Elements.McAfee_LocalePage;
import Com.App_Functions.Capture_Screenshot;
import Com.App_Functions.Logging;
import Com.App_Functions.Properties_Read;
import Com.App_Functions.WebDriver_Instance;

public class Localization_Test {

    static String _url;
    static String _testDataFilePath;
    static int _startOfTest;
    String _region;
    SoftAssert softassert = new SoftAssert();
    McAfee_LocalePage _localepg = new McAfee_LocalePage();
    /*@Parameters({"testcases"})
    @Factory
    public Object[] getTheClassInstance(int testcases){
    	Object[] classinstance = new Object[testcases];
    	for(int i=0 ; i<testcases ; i++){
    		classinstance[i] = new Localization_Test();
    	}
		return classinstance;
    }*/
    @Parameters({"startoftest"})
	@BeforeSuite/*(groups = { "smoke", "functional" })*/
	public void getTheProperty(int startoftest) throws IOException {
		try {
			_startOfTest = startoftest;
			System.out.println("wdbwcdbkjwbckjwbc start of test Before suite " +_startOfTest);
			Properties_Read pr = new Properties_Read();
			_url = pr.propertyRead("./configs//Configuration.properties").getProperty("Url");
			Logging.getTheLogForPropertyFileRead("Url is retrieved from properties file",
					"Url is not retrived from properties file", _url);
			_testDataFilePath = pr.propertyRead("./configs//Configuration.properties").getProperty("testDataFile");
			Logging.getTheLogForPropertyFileRead("test Data file path is retrieved",
					"test data file path is not retrieved from properties file", _testDataFilePath);
		} catch (FileNotFoundException e) {
 			e.getMessage();
		}
	}
	@DataProvider(name = "RegionDetails")
	public static Object[][] returnTheRegions(){
		
		return new Object[][]{{"Chrome","TC_001","it-it"},{"Chrome","TC_002","en-in"},{"Chrome","TC_003","es-es"},{"Chrome","TC_004","ja-jp"},{"Chrome","TC_005","en-gb"}};
	}
	/*@Parameters({"browser"})
	@Test(dataProvider = "RegionDetails")
	public void getTheLocaleSpecificWebsite(String browser, String region) throws MalformedURLException, InterruptedException{*/
	//@Parameters({"browser","test","locale"})
	@Test(dataProvider = "RegionDetails")
	public void getTheLocaleSpecificWebsite(String browser, String test, String locale) throws InterruptedException, FileNotFoundException, IOException{
		System.out.println(Localization_Test.class.getClassLoader());
		_region = locale;
		try{			
		//_region =  ExcelData_Read.getTheExcelData(test+String.valueOf(_startOfTest), 0, locale, _testDataFilePath);
		//System.out.println("sljnjnvj " + _region);
		//WebDriver_Instance.getDriverInstance(browser).get(_url);
		WebDriver_Instance.setTheLocale(_region);
		//System.out.println("sljnjnvj " + _url);
		WebDriver_Instance.getDriverInstance().get(_url);
		//WebDriver_Instance.getDriverInstance(browser).get(_url+region+"/index.html");
		//System.out.println("khbckbcke "+ WebDriver_Instance.getDriverInstance().getCurrentUrl());
		Logging.getTheLogForSuccessMessage("WebDriver is launced for " + browser);
		softassert.assertEquals(WebDriver_Instance.getDriverInstance().getCurrentUrl(), 
				_url+_region+"/index.html","Launched url is not same");
		_localepg.setTheWebDriverWait(WebDriver_Instance.getDriverInstance(), 30);
		
	}catch(NullPointerException e){
		Logging.getTheLogForFailureMessage("WebDriver object is null");
	}
	catch(WebDriverException e){
		Logging.getTheLogForFailureMessage("WebDriver unreachable or it may have died");
	}
	 try{
		 _localepg.forConsumer().click();
		 Logging.getTheLogForSuccessMessage("consumer link is clicked successfully");
		 softassert.assertEquals(WebDriver_Instance.getDriverInstance().getCurrentUrl(), _url+_region+"/store/m0/index.html");		 
	 }catch(NullPointerException e){
		 Logging.getTheLogForFailureMessage(_region + " WebElement for consumer is null");
	 }
	 catch(NoSuchElementException e){
		 Logging.getTheLogForFailureMessage(_region + " No consumer web element is present");
	 }
	 catch(TimeoutException e){
		 Logging.getTheLogForFailureMessage(_region +" Time out waiating for consumer Web element");
	 }
	 try{
		 _localepg.myAccount().click();
		 Logging.getTheLogForSuccessMessage(_region + " my account clicked successfully");
	 }catch(NullPointerException e){
		 Logging.getTheLogForFailureMessage(_region + " WebElement for myaccount is null");
	 }
	 catch(NoSuchElementException e){
		 Logging.getTheLogForFailureMessage(_region+ " No myaccount web element is present");
	 }
	 catch(TimeoutException e){
		 Logging.getTheLogForFailureMessage(_region + " Time out waiating for sign in Web element");
	 }
	try{
		_localepg.signin().click();
		Logging.getTheLogForSuccessMessage(_region + " Sign in is clicked successfully");
	}
	catch(NullPointerException e){
		Logging.getTheLogForFailureMessage(_region + " WebElement for sign in is null");
	}
	 catch(TimeoutException e){
		 Logging.getTheLogForFailureMessage(_region + " Time out waiating for sign in Web element");
	 }
	Thread.sleep(4000);
	Capture_Screenshot.captureScreenshot(WebDriver_Instance.getDriverInstance(), _region);
	_startOfTest++;
	System.out.println("_startOfTest wdkbdwcbkwbckjw " + _startOfTest);

	}
	
	@Parameters({"browser"})
	@AfterMethod
	public void closeTheDriverInstance(String browser) throws MalformedURLException{
		WebDriver_Instance.getDriverInstance().quit();
		Logging.getTheLogForSuccessMessage("WebDriver instance for " + browser + " closed succesfully");
		WebDriver_Instance._chromeDriver = null;
	}
}
