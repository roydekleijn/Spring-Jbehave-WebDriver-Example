package org.google.web.common;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebDriver;

public class SeleniumWebDriverProvider implements WebDriverProvider {
	 private WebDriver webDriver;

     @Override
     public WebDriver get() {
             return webDriver;
     }

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean saveScreenshotTo(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}

}
