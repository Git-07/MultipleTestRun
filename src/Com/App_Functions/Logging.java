package Com.App_Functions;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//import Com.Page_Elements.PeopleHub_ORGPage;

public class Logging {
	private static Logger log = LogManager.getLogger(Logging.class.getClass());
	public static void getTheLogForEqualObject(String actual, String expected, String success, String failure){
		if(expected.equals(actual)){
			log.info(success);
		}
		else if(!expected.equals(actual)){
			log.error(failure);
		}
	}
	public static void getTheLogForSuccessMessage(String success){
		log.info(success);
	}
	
	public static void getTheLogForFailureMessage(String failure){
		log.error(failure);
	}
	public static void getTheLogForNullWebElement(String success, String failure,WebElement element){
		if(element != null){
			log.info(success);
		}
		else if(element == null) {
			log.error(failure);
		}
	}
	
	public static void getTheLogForNullWebDriver(String success, String failure, WebDriver driver){
		if(driver != null){
			log.info(success);
		}
		else if(driver == null) {
			log.error(failure);
		}		
	}
 
    public static void getTheLogForElementDisplayed(String success, String failure, WebElement element){
    
    	if(element.isDisplayed() == true){
    		log.debug(success);
    	}
    	else if(element.isDisplayed() == false){
    		log.error(failure);
    	}
    }
    
    public static void getTheLogForElementSelected(String success, String failure, WebElement element){
   
    	if(element.isSelected() == true){
    		log.debug(success);
    	}
    	else if(element.isSelected() == false){
    		log.error(failure);
    	}    	
    }
    
    public static void getTheLogForPropertyFileRead(String success, String failure, String property){
    	
    	if(property != null){
    		log.info(success);
    	}
    	else if(property == null){
    		log.error(failure);
    	}
    }
    
	public static void getTheLogForEmptyListOfWebElement(String success, String failure,List<WebElement> element){
		if(element.size() != 0){
			log.info(success);
		}
		else if(element.size() == 0) {
			log.error(failure);
		}
	}
	
	public static void getTheLogForNullObject(String success, String failure, String object){

		if(object != null ){
			log.info(success);
		}
		else if(object == null){
			log.error(failure);
		}
	}
	
	public static void getTheLogForRetryFailedTest(String message){
		
		log.debug(message);
		
	}
	
	public static void getTheLogForElementClicked(String message){
		log.debug(message);
	}
}
