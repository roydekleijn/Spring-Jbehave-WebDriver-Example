package org.google.web.test;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.junit.Assert;
import org.openqa.selenium.By;

public class HomePage extends WebDriverPage {
	
	public HomePage(WebDriverProvider provider){
		super(provider);
		System.out.println("what is driver: " + provider.get());
	}

	@Given("I am on the homepage")
	public void openHomePage() throws InterruptedException {
		System.out.println("THREAD ID: " + Thread.currentThread().getId());
		manage().deleteAllCookies();
		get("https://www.google.com/webhp?hl=en&noj=1");
	}

	@When("I search for <query>")
	public void clickFirstResult(@Named("query") String query)
			throws InterruptedException {
		findElement(By.cssSelector("input[name='q']")).sendKeys(query);
		findElement(By.cssSelector("input[name='btnK']")).click();
		Assert.assertFalse(true);
	}

}
