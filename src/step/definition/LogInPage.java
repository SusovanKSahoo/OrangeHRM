package step.definition;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import utility.PropertyFileUtility;

public class LogInPage {
	WebDriver driver = AbstractPage.getDriver();
	static Logger log = Logger.getLogger(LogInPage.class);
	
	@Given("^open the login page \"([^\"]*)\"$")
	public void open_loginPage(String arg1) throws IOException {
		driver.get(PropertyFileUtility.getElementValue(arg1));
		PropertyConfigurator.configure("log4j.xml");
		log.info("The login page is loaded.");
	}
	
	@When("^enter the username \"([^\"]*)\" \"([^\"]*)\"$")
	public void enter_userName(String arg1, String arg2) throws IOException {
		driver.findElement(By.id(PropertyFileUtility.getElementValue(arg1))).sendKeys(arg2);
		PropertyConfigurator.configure("log4j.xml");
		log.info("Username is entered.");
	}
	
	@When("^enter the password \"([^\"]*)\" \"([^\"]*)\"$")
	public void enter_password(String arg1, String arg2) throws IOException {
		driver.findElement(By.id(PropertyFileUtility.getElementValue(arg1))).sendKeys(arg2);
		log.info("Password is entered");
	}
	
	@When("^click on login button \"([^\"]*)\"$")
	public void click_on_login(String arg1) throws IOException {
		driver.findElement(By.id(PropertyFileUtility.getElementValue(arg1))).click();
		log.info("Login button is clicked.");
	}
}
