package Com.Test_Classes;

import Com.PeopleHub_Interface.Class1_Interface;
import Com.App_Functions.Logging;
import Com.Page_Elements.PeopleHub_ORGPage;
import Com.App_Functions.Interaction_Actions;
import Com.App_Functions.Properties_Read;
import Com.App_Functions.SelectTheDriver;
import Com.App_Functions.Capture_Screenshot;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
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
import Com.App_Functions.WebDriver_Instance;
import Com.App_Functions.ImageSrc_Attribute;
import Com.Utility_Function.ExcelData_Read;
import Com.Utility_Function.ExtentReport_Reporting;

public class Test_Class1 implements Class1_Interface {
	static String _url;
	static String _testDataFilePath;
	PeopleHub_ORGPage _page = new PeopleHub_ORGPage();
	SoftAssert softassert = new SoftAssert();
	SelectTheDriver driverClass = new SelectTheDriver();
	ExtentReport_Reporting er = new ExtentReport_Reporting();
	@BeforeSuite(groups = { "smoke", "functional" })
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

	@Parameters({ "browser" })
	@Test(groups = { "smoke" })
	public void orgatakeSrcImageCES(String browser) throws IOException {
		try {
			//WebDriver_Instance.getDriverInstance().switchTo().
			WebDriver_Instance.getDriverInstance().get(_url);
			softassert.assertNotNull(WebDriver_Instance.getDriverInstance(), browser + " instance is not null");
			Logging.getTheLogForNullWebDriver("WebDriver instance for " + browser + " launched successfully",
					"WebDriver instance for " + browser + " is unreachable",
					WebDriver_Instance.getDriverInstance());
			softassert.assertEquals(WebDriver_Instance.getDriverInstance().getCurrentUrl(), _url,
					"Launched Url is not same");
			Logging.getTheLogForEqualObject(_url, WebDriver_Instance.getDriverInstance().getCurrentUrl(),
					"expected Url is launched", "Url launched is not the expected");
			_page.setTheWebDriverWait(WebDriver_Instance.getDriverInstance(), 30);

		}catch(NullPointerException e){
			Logging.getTheLogForFailureMessage("No WebDriver Object for "+ browser+" browser is alive or is unreachable");
		}
		catch (WebDriverException e) {
			Logging.getTheLogForFailureMessage("No WebDriver session found it might had closed unexpectedly");
		}

		try {
			Interaction_Actions interaction = new Interaction_Actions(WebDriver_Instance.getDriverInstance());
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
			Capture_Screenshot.imagePresentInWebPage(_page.getTheImageElement().get(0).getAttribute("src"),
					browser + "_orgatakeSrcImageCES");
			Logging.getTheLogForSuccessMessage("Image is captured successfully");
		}
		catch (TimeoutException e) {
			Logging.getTheLogForFailureMessage("wait time exceeded for The Image Element");
			e.getMessage();
		}
		
		softassert.assertAll();
	}

	@Parameters({ "browser", "src", "attribute", "pageurl" })
	//@Test(groups = { "functional" })
	public void orgbImmersiveAurora(String browser, String src, String attribute, String pageurl) throws IOException {
		int i = -1;
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
		String mainWindow = WebDriver_Instance.getDriverInstance().getWindowHandle();
		softassert.assertNotNull(mainWindow, "Main window is null");
		Set<String> windows = WebDriver_Instance.getDriverInstance().getWindowHandles();
		String currentUrl = "";
		for (String window : windows) {
			softassert.assertNotNull(window, "child window is null");
			if (!window.equals(mainWindow)) {
				try{
				WebDriver_Instance.getDriverInstance().switchTo().window(window).getCurrentUrl();
				currentUrl = WebDriver_Instance.getDriverInstance().getCurrentUrl();
				Logging.getTheLogForEqualObject(currentUrl,
						ExcelData_Read.getTheExcelData("TC_001", 0, pageurl, _testDataFilePath),
						"Window switched to Augmented reality page",
						"Window did not switched to Augmented reality page");
			}
			catch(NullPointerException e){
				Logging.getTheLogForFailureMessage("Main window is null");
			}	
			catch(NoSuchWindowException e){
			  Logging.getTheLogForFailureMessage("No such Window found");	
			}
		}
		}
		Capture_Screenshot.captureScreenshot(WebDriver_Instance.getDriverInstance(),
				browser + "_orgbImmersiveAurora");
		//er.generateReport(result.getMethod().getMethodName(),WebDriver_Instance.getDriverInstance(), "_orgatakeSrcImageCES");
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
	public void closeTheDriverInstance(String browser) throws MalformedURLException {

		WebDriver_Instance.getDriverInstance().quit();
		Logging.getTheLogForSuccessMessage("WebDriver instance for " + browser + " closed succesfully");
	}

}
