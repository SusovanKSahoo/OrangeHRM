package step.definition;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import cucumber.api.java.en.When;
import utility.PropertyFileUtility;

public class AdminModule {
	WebDriver driver = AbstractPage.getDriver();
	static Logger log = Logger.getLogger(AdminModule.class);
	
	@When("^wait for an element \"([^\"]*)\"$")
	public void wait_for_element(String arg1) throws IOException {
		WebDriverWait wait = new WebDriverWait(driver, 25);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertyFileUtility.getElementValue(arg1))));
		PropertyConfigurator.configure("log4j.xml");
		log.info("Waiting for element "+arg1+".");
	}
	
	@When("^mouse over to the element \"([^\"]*)\"$")
	public void mouseOver_to_element(String arg1) throws IOException {
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath(PropertyFileUtility.getElementValue(arg1)));
		action.moveToElement(element).build().perform();
		log.info("Mouse moved to "+arg1);
	}
	
	@When("^click on the element \"([^\"]*)\"$")
	public void click_on_element(String arg1) throws IOException {
		driver.findElement(By.xpath(PropertyFileUtility.getElementValue(arg1))).click();
		log.info(arg1+" is clicked.");
	}
	
	@When("^enter a value \"([^\"]*)\" \"([^\"]*)\"$")
	public void enter_value(String arg1, String arg2) throws IOException {
		driver.findElement(By.xpath(PropertyFileUtility.getElementValue(arg1))).sendKeys(arg2);
		log.info(arg2+" value is entered in "+arg1+" field.");
	}
	
	@When("^select an element from dropdown \"([^\"]*)\" \"([^\"]*)\"$")
	public void select_from_dropdown(String arg1, String arg2) throws IOException {
		WebElement dropBox = driver.findElement(By.xpath(PropertyFileUtility.getElementValue(arg1)));
		Select dropDown = new Select(dropBox);
		dropDown.selectByVisibleText(arg2);
		log.info(arg2+" is selected from the drop-down.");
	}
}
