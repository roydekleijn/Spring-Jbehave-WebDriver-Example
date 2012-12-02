package org.google.web.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.google.web.common.SeleniumWebDriverProvider;
import org.google.web.common.WebDriverFactory;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class HomePage extends WebDriverPage {
	
//	protected WebDriver driver;

	@FindBy(how = How.CSS, using = "input[name='q']")
	private WebElement searchField;

	@FindBy(how = How.CSS, using = "input[name='btnK']")
	private WebElement searchButton;

//	public HomePage(SeleniumWebDriverProvider driver){
//		this.driver = driver;
//		
//	}

	public HomePage(WebDriverProvider provider){
		super(provider);
		System.out.println("what is driver: " + provider.get());
	}
	
	@BeforeScenario
	public void setup() {
		PageFactory.initElements(getDriverProvider().get(), this);
	}

	@Given("I am on the homepage")
	public void openHomePage() {
		System.out.println("THREAD ID: " + Thread.currentThread().getId());
//		PageFactory.initElements(provider.get(), this);
		get("https://www.google.com/webhp?hl=en&noj=1");
		/*
		File f = driver.getScreenshotAs(OutputType.FILE);
		long filename = System.nanoTime();
		File o = new File("target/screenshots/xz" + filename + ".png");
		System.out.println("<img src='target/screenshots/" + filename
		+ ".png' />");

		try {
			FileUtils.copyFile(f, o);
		} catch (IOException ex) {
		
		ex.printStackTrace();
		}
		*/
	}

	@When("I search for <query>")
	public void clickFirstResult(@Named("query") String query)
			throws InterruptedException {
		findElement(By.cssSelector("input[name='q']")).sendKeys(query);
		findElement(By.cssSelector("input[name='btnK']")).click();
		//searchField.sendKeys(query);
		//searchButton.click();
	}

}
