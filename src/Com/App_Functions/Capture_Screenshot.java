package Com.App_Functions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class Capture_Screenshot {
	public static void captureScreenshot(WebDriver driver, String screenshotName){
		try{
			
			TakesScreenshot screen = (TakesScreenshot) driver;
			File source = screen.getScreenshotAs(OutputType.FILE);
			//FileUtils.copyFileToDirectory(source, new File ("./ScreenShots/"+screenshotName));
		    FileUtils.copyFile(source, new File ("./ScreenShots/"+screenshotName+".png"));
		}
		catch(Exception e){
			System.out.println(e);
		}		
	}
	
	public static void imagePresentInWebPage(String srcImage, String screenshotName) throws IOException{
		URL imageUrl = new URL(srcImage);
		BufferedImage saveImage = ImageIO.read(imageUrl);
		ImageIO.write(saveImage, "png", new File("./ScreenShots/"+screenshotName+".png"));		
	}
}
