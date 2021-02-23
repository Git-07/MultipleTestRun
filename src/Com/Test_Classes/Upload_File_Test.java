package Com.Test_Classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Com.App_Functions.Logging;
import Com.App_Functions.Properties_Read;
import Com.App_Functions.SelectTheDriver;
import Com.Page_Elements.PeopleHub_ORGPage;

public class Upload_File_Test {
	
	static String _url;
	static String _testDataFilePath;
	PeopleHub_ORGPage _page = new PeopleHub_ORGPage();
	SoftAssert softassert = new SoftAssert();
    SelectTheDriver driverClass = new SelectTheDriver();
    //DriverInstance instance;
	@BeforeSuite/*(groups = { "smoke", "functional" })*/
	public void getTheProperty() throws IOException {
		try {
			Properties_Read pr = new Properties_Read();
			_url = pr.propertyRead("./configs//Configuration.properties").getProperty("TestUrl");
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
	public void fileUploadTest(String browser, String localisation, String locale, String testname) throws IOException, InterruptedException {
		try {
			//driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance().navigate().to(_url);
			 driverClass.selectTheWebDriverInstance(browser).setTheLocale(localisation, locale);
	         driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance().get(_url);
	        // Thread.sleep(30000);
			softassert.assertEquals(driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance().getCurrentUrl(), _url,"Launched Url is not same");
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
			System.out.println("reached here");
	           _page.getUploadElement().click();
	           System.out.println("clicked ldksnclwncdjkncdwkjcd");
			  Logging.getTheLogForElementClicked("upload element is clicked");
			  _page.getUploadElement().sendKeys("C:\\Users\\M1052416\\Desktop\\UploadFile.txt");
			  _page.getSubmitUploadElement().click();
			  
		}
		catch(NullPointerException e){
			Logging.getTheLogForFailureMessage("WebElement for upload object is pointing to null");
		} 
		catch (TimeoutException e) {
			Logging.getTheLogForFailureMessage("wait time exceeded for The upload Element");
			e.getMessage();
		}
		catch (WebDriverException e) {
			Logging.getTheLogForFailureMessage("No WebDriver session found it might had closed unexpectedly");
		}
		softassert.assertAll();
	}

	@Parameters({ "browser" })
	@AfterTest(/*groups = { "smoke", "functional" }*/)
	public void closeTheDriverInstance(String browser) throws IOException, InterruptedException {
        Thread.sleep(3000);
		driverClass.selectTheWebDriverInstance(browser).getWebDriverInstance().quit();
		Logging.getTheLogForSuccessMessage("WebDriver instance for " + browser + " closed succesfully");
		driverClass.selectTheWebDriverInstance(browser).setTheDriverInstance();
	}

}
