package Com.App_Functions;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

public abstract class DriverInstance {
	
	public abstract WebDriver getWebDriverInstance()throws MalformedURLException, IOException;
	public abstract void setTheLocale(String locale, String local);
	public abstract void setTheDriverInstance();
	

}
