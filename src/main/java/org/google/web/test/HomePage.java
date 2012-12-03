package org.google.web.test;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends WebDriverPage {
	
	@FindBy(how = How.CSS, using = "input[name='q']")
	private WebElement searchField;

	@FindBy(how = How.CSS, using = "input[name='btnK']")
	private WebElement searchButton;

	public HomePage(WebDriverProvider provider){
		super(provider);
		System.out.println("what is driver: " + provider.get());
	}
	
	@BeforeScenario
	public void setup() {
		PageFactory.initElements(getDriverProvider().get(), this);
	}

	@Given("I am on the homepage")
	public void openHomePage() throws InterruptedException {
		System.out.println("THREAD ID: " + Thread.currentThread().getId());
		manage().deleteAllCookies();
		get("https://www.google.com/webhp?hl=en&noj=1");
		
		Thread.sleep(5000);
	}

	@When("I search for <query>")
	public void clickFirstResult(@Named("query") String query)
			throws InterruptedException {
		searchField.sendKeys(query);
		searchButton.click();
	}

}
