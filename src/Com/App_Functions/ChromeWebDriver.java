package Com.App_Functions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class ChromeWebDriver extends DriverInstance {
	static String _chromeWebDriverPath;
	protected static WebDriver _chromeDriver;
	static String _localisation;
	static String _locale;
	public void setTheWebDriverPath(String local) throws IOException {

		try {
            _localisation = local;			
			Properties_Read pr = new Properties_Read();
			_chromeWebDriverPath = pr.propertyRead("./configs//Configuration.properties")
					.getProperty("chromeWebDriver");
			Logging.getTheLogForPropertyFileRead("Chrome web driver path is retrieved",
					"Chrome web driver path is not retrieved", _chromeWebDriverPath);
		} catch (FileNotFoundException e) {
                Logging.getTheLogForFailureMessage("Properties file not found");
		}
	}
	@Override
	public WebDriver getWebDriverInstance() throws MalformedURLException,IOException {
	      if (_chromeDriver == null) {
			try {			
				Properties_Read pr = new Properties_Read();
				_chromeWebDriverPath = pr.propertyRead("./configs//Configuration.properties")
						.getProperty("chromeWebDriver");
				Logging.getTheLogForPropertyFileRead("Chrome web driver path is retrieved",
						"Chrome web driver path is not retrieved", _chromeWebDriverPath);
	
			} catch (FileNotFoundException e) {
	                Logging.getTheLogForFailureMessage("Properties file not found");
			}
			//System.out.println("wdnclwnclkwncdlwdcjwcd : "+ _chromeWebDriverPath);
			// System.out.println("chrome driver path:
			// "+_chromeWebDriverPath);
			// System.setProperty("webdriver.chrome.driver",
			// "C://Users//M1052416//Downloads//chromedriver_win32//chromedriver.exe");
			//System.setProperty("webdriver.chrome.driver", _chromeWebDriverPath);
			//DesiredCapabilities dc = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("dsable-extensions");
			//System.out.println("dcknwjcnwlknclkwnclkncdwlknwcdk " + _localisation);
			//try { 
		/*	if(_localisation.equals("yes")){				
			Map<String,String> prefs = new HashMap<String,String>();
			prefs.put("intl.accept_languages",_locale);
			options.setExperimentalOption("prefs", prefs);
			}*/
			//dc.setCapability(ChromeOptions.CAPABILITY, options);
			_chromeDriver = new ChromeDriver(options);
			//}catch(NullPointerException e){
			//	Logging.getTheLogForFailureMessage("The Localisation is set as No");
			//}
			/*try {
			 	 //System.out.println("slkdnclkdwnclkdwnclkwnclkwnclk");
				_chromeDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), dc);
				//System.out.println("here the chrome driver is : " + _chromeDriver);
				
			} 
			catch (UnreachableBrowserException e) {
				Logging.getTheLogForFailureMessage("unreachable browser exception");
				e.getMessage();
			}*/
		}
		return _chromeDriver;
	}
	@Override
	public void setTheLocale(String local,String locale) {
		_locale = locale;
		_localisation = local;	
		
	}
	@Override
	public void setTheDriverInstance() {
		_chromeDriver = null;
		
	}

}
