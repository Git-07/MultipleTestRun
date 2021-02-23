package Com.App_Functions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Interaction_Actions {
	WebDriver driver;
	public Interaction_Actions(WebDriver driver){
		this.driver = driver;
	}	
	
	public void moveToTheElement(WebElement element){
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	
	public void douleClick(WebElement element){
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
	}

	public void rightClick(WebElement element){
		Actions action = new Actions(driver);
		action.contextClick(element).build().perform();
	} 
}
