package Com.App_Functions;

import Com.App_Functions.Properties_Read;
import Com.App_Functions.Logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class WebDriver_Instance {

	static SoftAssert softassert = new SoftAssert();
	static String _locale = null;
	static WebDriver _driver = null;
	static String _browser = null;
	static String _chromeWebDriverPath;
	static String _ieWebDriverPath;
	static String _firefoxWebDriverPath;
	public static WebDriver _ieDriver = null;
	public static WebDriver _chromeDriver = null;
	public static WebDriver _firefoxDriver = null;
	public static String _url = null;
    static String localisation = null; 
	// http://127.0.0.1:4444/wd/hub
	@Parameters({"localisation"})
    @BeforeSuite(/*groups={"smoke" ,"functional"}*/)
	public void setTheWebDriverPath(String local) throws IOException {

		try {
            localisation = local;			
			Properties_Read pr = new Properties_Read();
			_chromeWebDriverPath = pr.propertyRead("./configs//Configuration.properties")
					.getProperty("chromeWebDriver");
			Logging.getTheLogForPropertyFileRead("Chrome web driver path is retrieved",
					"Chrome web driver path is not retrieved", _chromeWebDriverPath);
			_ieWebDriverPath = pr.propertyRead("./configs//Configuration.properties").getProperty("ieWebDriver");
			Logging.getTheLogForPropertyFileRead("Internet explorer web driver path is retrieved",
					"Internet explorer web driver path is not retrieved", _ieWebDriverPath);
			_firefoxWebDriverPath = pr.propertyRead("./configs//Configuration.properties").getProperty("firefoxWebDriver");
			Logging.getTheLogForPropertyFileRead("FireFox web driver path is retrieved",
					"Internet explorer web driver path is not retrieved", _firefoxWebDriverPath);

			_url = pr.propertyRead("./configs//Configuration.properties").getProperty("Url");
			Logging.getTheLogForPropertyFileRead("Url is retrieved from properties file",
					"Url is not retrived from properties file", _url);
			String _testDataFilePath = pr.propertyRead("./configs//Configuration.properties").getProperty("testDataFile");
			Logging.getTheLogForPropertyFileRead("test Data file path is retrieved",
					"test data file path is not retrieved from properties file", _testDataFilePath);
		} catch (FileNotFoundException e) {
                Logging.getTheLogForFailureMessage("Properties file not found");
		}
	}
	@Parameters({"browser"})
    @BeforeTest(/*groups={"smoke" ,"functional"}*/)
    public void selectTheBrowser(String browser){
		
    	_browser = browser;
    
    }
	/*
	 * private WebDriver_Instance() {
	 * 
	 * System.out.println("Instance of the Singleton Class created"); }
	 */
 // need to remove the String browser parameter from the below method
	public static void setTheLocale(String locale){
		_locale = locale;
	} 
  
	public  static WebDriver getDriverInstance(String mode,String _browser) throws MalformedURLException {
		//if (_chromeDriver == null && browser.equals("Chrome")) {
		if (/*_chromeDriver == null &&*/ _browser.equals("Chrome")) {
			DesiredCapabilities dc = DesiredCapabilities.chrome();
			if(mode.equals("local")){
			System.out.println("Reached in Chrome");
			System.setProperty("webdriver.chrome.driver", _chromeWebDriverPath);			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("dsable-extensions");
		/*	if(localisation.equals("yes")){				
			Map<String,String> prefs = new HashMap<String,String>();
			prefs.put("intl.accept_languages",_locale);
			options.setExperimentalOption("prefs", prefs);
			}*/
			 dc.setCapability(ChromeOptions.CAPABILITY, options);
			_chromeDriver = new ChromeDriver(options);
			_driver = _chromeDriver;
			}
			else if(mode.equals("SeleniumGrid")){
			try {
				_chromeDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), dc);
				_chromeDriver.manage().window().maximize();
				_driver = _chromeDriver;
			} 
			catch (UnreachableBrowserException e) {
				Logging.getTheLogForFailureMessage("unreachable "+ _browser + " browser exception");
				e.getMessage();
			}
			}
		} 
		else if (/*_ieDriver == null && */_browser.equals("InternetExplorer")) {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			if(mode.equals("local")){
			System.out.println("Reached in IE");
			System.setProperty("webdriver.ie.driver", _ieWebDriverPath);		
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability("ignoreZoomSetting", true);
			_ieDriver = new InternetExplorerDriver();
			_ieDriver.manage().window().maximize();			
			_driver = _ieDriver;
			}

			else if(mode.equals("SeleniumGrid")){
			try {

				_ieDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
				_ieDriver.manage().window().maximize();
				_driver = _ieDriver;
				//_driver.get(_url);
			}
			catch (UnreachableBrowserException e) {
				Logging.getTheLogForFailureMessage("unreachable "+ _browser + " browser exception");
				e.getMessage();

			}
			}
		}
		else if (/*_firefoxDriver == null &&*/ _browser.equals("Firefox")) {
		    DesiredCapabilities capabilities = DesiredCapabilities.firefox();	
			if(mode.equals("local")){
			System.out.println("Reached in Firefox");
			System.setProperty("webdriver.gecko.driver", _firefoxWebDriverPath);	    
			capabilities.setPlatform(Platform.WINDOWS);
			_firefoxDriver = new FirefoxDriver();
			_firefoxDriver.manage().window().maximize();
			 _driver = _firefoxDriver;
			}
			 else if(mode.equals("SeleniumGrid")){
			try {
				File pathBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			    FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
				FirefoxOptions options = new FirefoxOptions();
				capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
				_firefoxDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"),capabilities);
				_firefoxDriver.manage().window().maximize();
				_driver = _firefoxDriver;
			}
			catch (UnreachableBrowserException e) {
				Logging.getTheLogForFailureMessage("unreachable "+ _browser + " browser exception");
				e.getMessage();

			}
			 }
		}
		return _driver;
	}

}
