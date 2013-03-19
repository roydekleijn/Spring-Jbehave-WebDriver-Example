package org.google.pages;

import java.util.concurrent.TimeUnit;

import org.google.common.BasePage;
import org.google.webdriver.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage implements Page {

	@FindBy(how = How.CSS, using = "input[name='q']")
	private WebElement searchField;

	@FindBy(how = How.CSS, using = "span.lsbb > input")
	private WebElement searchButton;

	public HomePage(DriverProvider provider) {
		super(provider);
		PageFactory.initElements(getDriver(), this);
	}

	@Override
	public void init() {
		getDriver().manage().deleteAllCookies();
		getDriver().get("https://www.google.com/webhp?hl=en&noj=1");
		getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}

	public void searchFor(String query) {
		searchField.sendKeys(query);
		searchButton.click();
	}

}
