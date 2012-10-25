package org.google.web.test;

import org.openqa.selenium.WebDriver;

public class DriverProvider {
	WebDriver driver;

	public DriverProvider(WebDriver webDriver) {
		this.driver = webDriver;
	}

}
