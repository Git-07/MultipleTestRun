package Com.Test_Classes;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FindBrokenLinksSeleniumWebDriver {

	
	public static WebDriver driver = null;
	
	public static void main(String [] str) throws IOException{	
	System.setProperty("webdriver.chrome.driver", "D://workspace//CucmberFramework//Drivers//chromedriver.exe");
	DesiredCapabilities dc = DesiredCapabilities.chrome();	
	ChromeOptions options = new ChromeOptions();
	options.addArguments("start-maximized");
	options.addArguments("dsable-extensions");
	driver = new ChromeDriver();
	String homepage = "https://www.zlti.com";
	String url = "";
	HttpURLConnection huc = null;
	int respCode = 200; // for links to be fine or unbroken links
	driver.get(homepage);
	List<WebElement> links = driver.findElements(By.tagName("a"));
	Iterator<WebElement> iwe = links.iterator();
	while(iwe.hasNext()){
		
		driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
		url = iwe.next().getAttribute("href");
		//System.out.println(url);
		if(url == null || url.isEmpty()){
			System.out.println("URL is either not configured for anchor tag or it is empty");
			continue;
			}

			if(!url.startsWith(homepage)){
			System.out.println("URL belongs to another domain, skipping it.");
			continue;
			}
		
		try{
			huc =  (HttpURLConnection) new URL(url).openConnection();
			huc.setRequestMethod("HEAD");
			huc.connect();
			respCode = huc.getResponseCode();
			if(respCode >= 400){
				
				System.out.println(url + " is a broken link");
			}
			else {
				
				//System.out.println(url + " is a valid link");
			}
		}catch(MalformedURLException e){
			//e.printStackTrace();			
			driver.quit();
		}
	}
	
	driver.quit();
	}
}
