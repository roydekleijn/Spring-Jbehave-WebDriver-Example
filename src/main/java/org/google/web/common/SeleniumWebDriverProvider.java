package org.google.web.common;

import java.io.IOException;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebDriver;

public class SeleniumWebDriverProvider implements WebDriverProvider {
	  private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	  @Override
	  public WebDriver get() {
	    WebDriver driver = webDriver.get();
	    if (driver == null) {
	      System.out.println("webDriver is null");
	    }
	    return driver;
	  }

	  @Override
	  public void initialize() {
	    System.out.println("Initialize method;;");
	    try {
	      webDriver.set(new SeleniumWebDriver());
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	  }

	  @Override
	  public boolean saveScreenshotTo(String path) {
	    // TODO Auto-generated method stub
	    return false;
	  }

	  @Override
	  public void end() {
	    webDriver.get().quit();
	    webDriver.remove();
	  }
	}