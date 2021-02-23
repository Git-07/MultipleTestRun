package Com.App_Functions;

public class SelectTheDriver {
	public  DriverInstance selectTheWebDriverInstance(String browser){	
		
		if(browser.equalsIgnoreCase("Chrome")){
			return new ChromeWebDriver();
				
		}
		else if(browser.equalsIgnoreCase("InternetExplorer")){
			return new InternetExplorerWebDriver();
		}
		
		return null;
	}
}
