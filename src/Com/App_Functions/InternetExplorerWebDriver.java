package Com.App_Functions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class InternetExplorerWebDriver extends DriverInstance{
	static String _locale = null;
	static WebDriver _driver = null;
	static String _browser = null;
	static String _ieWebDriverPath;
	public static WebDriver _ieDriver = null;
	public static WebDriver _chromeDriver = null;
    static String _localisation = null; 
	public void setTheWebDriverPath(String local) throws IOException {

		try {
            _localisation = local;			
			Properties_Read pr = new Properties_Read();
			_ieWebDriverPath = pr.propertyRead("./configs//Configuration.properties")
					.getProperty("ieWebDriver");
			Logging.getTheLogForPropertyFileRead("Chrome web driver path is retrieved",
					"Chrome web driver path is not retrieved", _ieWebDriverPath);
		} catch (FileNotFoundException e) {
                Logging.getTheLogForFailureMessage("Properties file not found");
		}
	}
	@Override
	public WebDriver getWebDriverInstance() throws MalformedURLException, IOException {
		if (_ieDriver == null) {
			try {			
				Properties_Read pr = new Properties_Read();
				_ieWebDriverPath = pr.propertyRead("./configs//Configuration.properties")
						.getProperty("ieWebDriver");
				Logging.getTheLogForPropertyFileRead("Chrome web driver path is retrieved",
						"Chrome web driver path is not retrieved", _ieWebDriverPath);
			} catch (FileNotFoundException e) {
	                Logging.getTheLogForFailureMessage("Properties file not found");
			}
			// System.setProperty("webdriver.ie.driver",
			// "C://Users//M1052416//Downloads//IEDriverServer_x64_2.51.0//IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", _ieWebDriverPath);
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability("ignoreZoomSetting", true);
			// ieDriver = new InternetExplorerDriver(capabilities);
			// ieDriver.manage().window().maximize();
			try {

				_ieDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
			}
			catch (UnreachableBrowserException e) {
				Logging.getTheLogForFailureMessage("unreachable "+ _browser + " browser exception");
				e.getMessage();

			}

		}
		return _ieDriver;
	}

	@Override
	public void setTheLocale(String locale, String local) {
		_locale = locale;
		_localisation = local;	
		
		
	}
	@Override
	public void setTheDriverInstance() {
		_ieDriver = null;
		
	}

}
