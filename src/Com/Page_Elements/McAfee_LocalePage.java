package Com.Page_Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class McAfee_LocalePage {
	static WebDriverWait wait;
	static WebDriver driver;

	public void setTheWebDriverWait(WebDriver driver, long time) {
		wait = new WebDriverWait(driver, time);
	}

	public  WebElement forConsumer(){
		WebElement consumer = null;
		consumer = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='nav consumer']")));
		return consumer;
	}
	public  WebElement myAccount(){
		WebElement account = null;
		account = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='Icon-Allign selectedicon-chevron deselectedicon-chevron menuicon-myaccount']")));
        return account;		
	}
	
	public  WebElement signin(){
		WebElement sign = null;
		sign = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(("[class='list'] [data-pagesection='top nav:myaccount']"))));
		return sign;
	}
}
