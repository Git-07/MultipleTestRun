package Com.App_Functions;

import java.util.List;

import org.openqa.selenium.WebElement;

public class ImageSrc_Attribute {
	static int srcValue = -1;
	public static int srcImageRead(String expectedSrcValue, List<WebElement> elements, String attribute){
		
		for (int i = 0 ; i<elements.size(); i++){
			//System.out.println("wkjdbckjwbcdkjc "+elements.get(i).getAttribute(attribute).toString());
			if(elements.get(i).getAttribute(attribute).equals(expectedSrcValue)){
				srcValue = i;
				break;
			}
		}
		return srcValue;
	}
}
