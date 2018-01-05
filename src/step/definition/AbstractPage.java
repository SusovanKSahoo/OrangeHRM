package step.definition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AbstractPage {
	private static WebDriver driver = null;
	
	private AbstractPage() {}
	
	public static WebDriver getDriver() {
		if(driver==null) {
			driver = new FirefoxDriver();
		}
		return driver;
	}
}
