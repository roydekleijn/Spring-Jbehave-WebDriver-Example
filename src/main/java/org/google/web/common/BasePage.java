package org.google.web.common;

import org.google.webdriver.DriverProvider;
import org.openqa.selenium.WebDriver;

public class BasePage {
	private DriverProvider driverProvider;

	public BasePage(DriverProvider provider) {
		this.driverProvider = provider;
	}

	public DriverProvider getProvider() {
		return driverProvider;
	}

	protected WebDriver getDriver() {
		return driverProvider.get();
	}

}
