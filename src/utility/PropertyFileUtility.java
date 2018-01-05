package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {
	public static String getElementValue(String key) throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\susov\\Desktop\\Tests\\Cucumber Session\\Elements.properties");
		Properties pr = new Properties();
		pr.load(fis);
		return pr.getProperty(key);
	}
}
