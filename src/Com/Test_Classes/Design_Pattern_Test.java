package Com.Test_Classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Com.App_Functions.Capture_Screenshot;
import Com.App_Functions.ImageSrc_Attribute;
import Com.App_Functions.Interaction_Actions;
import Com.App_Functions.Logging;
import Com.App_Functions.Properties_Read;
import Com.App_Functions.SelectTheDriver;
import Com.Page_Elements.PeopleHub_ORGPage;
import Com.Utility_Function.ExcelData_Read;
import Com.Utility_Function.ExtentReport_Reporting;

public class Design_Pattern_Test {
	static String _url;
	static String _testDataFilePath;
	PeopleHub_ORGPage _page = new PeopleHub_ORGPage();
	
    SelectTheDriver driverClass = new SelectTheDriver();
    //DriverInstance instance;
    ExtentReport_Reporting er = new ExtentReport_Reporting();
	@BeforeSuite/*(groups = { "smoke", "functional" })*/
	public void getTheProperty() throws IOException {
		try {
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
 
	@Parameters({ "browser" ,"localisation", "locale", "testname" })
	@Test/*(groups = { "smoke" })*/
	public void orgatakeSrcImageCES(String browser, String localisation, String locale, String testname) throws IOException {
		SoftAssert softassert = new SoftAssert();
		try { 
			//er.generateReport("Design Pattern Test");
			//driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance().navigate().to(_url);
			 driverClass.selectTheWebDriverInstance(browser).setTheLocale(localisation, locale);
	         driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance().get(_url);	 
	        System.out.println("Test from test tag of testng.xml ->  " + testname + "   " + 
	        		driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance()); 
			softassert.assertEquals(driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance().getCurrentUrl()+"ghshgs", _url,"Launched Url is not same");
			//softassert.assertAll();
			Capture_Screenshot.captureScreenshot(driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance(), "orgatakeSrcImageCES_Home Page");
			
			Logging.getTheLogForSuccessMessage("WebDriver instance for browser " + browser + " launched successfully");
			Logging.getTheLogForEqualObject(_url, driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance().getCurrentUrl(),
					"expected Url is launched", "Url launched is not the expected");
			_page.setTheWebDriverWait(driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance(), 30);

		}catch(NullPointerException e){
			Logging.getTheLogForFailureMessage("No WebDriver Object for "+ browser+" browser is alive or is unreachable");
		}
		catch (WebDriverException e) {
			Logging.getTheLogForFailureMessage("No WebDriver session found it might had closed unexpectedly");
		}

		try {
			Interaction_Actions interaction = new Interaction_Actions(driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance());
			softassert.assertNotNull(interaction, "interaction object is null");
			interaction.moveToTheElement(PeopleHub_ORGPage.getTheCesElement());
			Logging.getTheLogForSuccessMessage("ces element is present");
		}
		catch(NullPointerException e){
			Logging.getTheLogForFailureMessage("WebElement CesElement object is pointing to null");
		} 
		catch (TimeoutException e) {
			Logging.getTheLogForFailureMessage("Ces Element is not present");
			e.getMessage();
		}
		catch (WebDriverException e) {
			Logging.getTheLogForFailureMessage("No WebDriver session found it might had closed unexpectedly");
		}
		try {
			softassert.assertNotNull(_page.getTheImageElement().get(0).getAttribute("src"));
			Capture_Screenshot.imagePresentInWebPage(_page.getTheImageElement().get(0).getAttribute("src"),
					"orgatakeSrcImageCES");
			/*Capture_Screenshot.imagePresentInWebPage(_page.getTheImageElement().get(0).getAttribute("src"),
					"orgatakeSrcImageCES1");*/
			//er.setTheScreenshotPath(System.getProperty("user.dir")+"/ScreenShots/"+browser + "_orgatakeSrcImageCES.png");
			Logging.getTheLogForSuccessMessage("Image is captured successfully");
		}
		catch (TimeoutException e) {
			Logging.getTheLogForFailureMessage("wait time exceeded for The Image Element");
			e.getMessage();
		}		
		
		softassert.assertAll();
	}

	@Parameters({ "browser", "src", "attribute", "pageurl"})
	@Test/*(groups = { "functional" })*/
	public void orgbImmersiveAurora(String browser, String src, String attribute, String pageurl) throws IOException {
		int i = -1;
		SoftAssert softassert = new SoftAssert();
		try {
			i = ImageSrc_Attribute.srcImageRead(ExcelData_Read.getTheExcelData("TC_001", 0, src, _testDataFilePath),
					_page.getImmerseElement(),
					ExcelData_Read.getTheExcelData("TC_001", 0, attribute, _testDataFilePath));
			Logging.getTheLogForSuccessMessage("immerse element is present");
		}catch(NullPointerException e){
			Logging.getTheLogForFailureMessage("WebElement object for immerse element poiting to null");
		} 
		catch (TimeoutException e) {
			Logging.getTheLogForFailureMessage("immerse element is not present");
			e.getMessage();
		}		
		try {
			_page.getImmerseElement().get(i).click();
			Logging.getTheLogForSuccessMessage("immerse link is clicked");
		}catch(NullPointerException e){
			Logging.getTheLogForFailureMessage("WebElement object for immerse element poiting to null");
		} 
		catch (ArrayIndexOutOfBoundsException e) {
			Logging.getTheLogForFailureMessage("there are no child element under immerse element ");
			e.getMessage();
		} catch (TimeoutException e) {
			Logging.getTheLogForFailureMessage("immerse link is not available to click");
			e.getMessage();
		} catch (NoSuchElementException e) {
			Logging.getTheLogForFailureMessage("No such element found");
		}
		try{
		String mainWindow = driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance().getWindowHandle();
		softassert.assertNotNull(mainWindow, "Main window is null");
		Set<String> windows = driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance().getWindowHandles();
		String currentUrl = "";
		for (String window : windows) {
			softassert.assertNotNull(window, "child window is null");
			if (!window.equals(mainWindow)) {
				try{
				//WebDriver_Instance.getDriverInstance().switchTo().window(window).getCurrentUrl();
					driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance().switchTo().window(window).getCurrentUrl();
					currentUrl = driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance().getCurrentUrl();
				Logging.getTheLogForEqualObject(currentUrl,
						ExcelData_Read.getTheExcelData("TC_001", 0, pageurl, _testDataFilePath),
						"Window switched to Augmented reality page",
						"Window did not switched to Augmented reality page");
				//driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance().close();
			}
			catch(NullPointerException e){
				Logging.getTheLogForFailureMessage("Main window is null");
			}	
			catch(NoSuchWindowException e){
			  Logging.getTheLogForFailureMessage("No such Window found");	
			}
		}
		}
		Capture_Screenshot.captureScreenshot(driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance(),
				"orgbImmersiveAurora");
		Capture_Screenshot.captureScreenshot(driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance(),
				"orgbImmersiveAurora1");
		//er.setTheScreenshotPath(System.getProperty("user.dir")+"/ScreenShots/"+browser + "_orgbImmersiveAurora.png");
		softassert.assertEquals(ExcelData_Read.getTheExcelData("TC_001", 0, pageurl, _testDataFilePath), currentUrl,"Current url is not equal to pageurl");
		}catch(NullPointerException e){
			Logging.getTheLogForFailureMessage("No WebDriver Object for "+ browser+" browser is alive or is unreachable");
		}
		 catch (WebDriverException e) {
				Logging.getTheLogForFailureMessage("No Main window found as WebDriver session was closed unexpectedly");
			}
		softassert.assertAll();
	}

	@Parameters({ "browser" })
	@AfterTest(groups = { "smoke", "functional" })
	public void closeTheDriverInstance(String browser) throws IOException {

		driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance().quit();
		Logging.getTheLogForSuccessMessage("WebDriver instance for " + browser + " closed succesfully");
		driverClass.selectTheWebDriverInstance(browser).setTheDriverInstance();
		
		
	}

}
