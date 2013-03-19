package org.google.web.pages;

import java.util.concurrent.TimeUnit;

import org.google.web.common.BasePage;
import org.google.webdriver.DriverProvider;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

	public HomePage(DriverProvider provider) {
		super(provider);
		System.out.println("what is driver: " + provider.get());
	}

	@Given("I am on the homepage")
	public void openHomePage() throws InterruptedException {
		getDriver().manage().deleteAllCookies();
		getDriver().get("https://www.google.com/webhp?hl=en&noj=1");
		getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}

	@When("I search for <query>")
	public void clickFirstResult(@Named("query") String query)
			throws InterruptedException {
		getDriver().findElement(By.cssSelector("input[name='q']")).sendKeys(
				query);
		getDriver().findElement(By.cssSelector("input[name='btnK']")).click();
	}

}
