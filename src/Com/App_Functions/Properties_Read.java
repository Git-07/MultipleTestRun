package Com.App_Functions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Properties_Read {
	public Properties propertyRead(String path) throws IOException {
		Properties prop = new Properties();
		try {			
			InputStream input = new FileInputStream(path);
			if (input != null) {
				prop.load(input);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File is not present in the specified path");
		}
		return prop;
	}
}
