package org.google.web.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class WebDriverFactory {

	private static EventFiringWebDriver webDriver;
	String url;
	private String browser;
	private boolean grid;
	 public static String browserType = BrowserType.FIREFOX;
	  private final static long MULTI_THREAD_START_UP_DELAY = 5000;
	/*
	 private static List<WebDriver> webDrivers = Collections.synchronizedList(new ArrayList<WebDriver>());
	  private static ThreadLocal<WebDriver> driverForThread = new ThreadLocal<WebDriver>() {

	    @Override
	    protected WebDriver initialValue() {
	      if (webDrivers.size() > 0) {
	        try {
	          Thread.sleep(MULTI_THREAD_START_UP_DELAY);
	        } catch (InterruptedException e) {
	          e.printStackTrace();
	        }
	      }
	      WebDriver driver = loadWebDriver();
	      webDrivers.add(driver);
	      return driver;
	    }

		
	  };


	  private static WebDriver loadWebDriver() {
			// TODO Auto-generated method stub
			return new FirefoxDriver();
		}
	  */
	public WebDriverFactory() {
		// Initialize parameters
		url = System.getProperty("url", "http://www.google.com");
		browser = System.getProperty("browser", "firefox");
		grid = Boolean
				.parseBoolean(System.getProperty("seleniumGrid", "false"));
	}

	@BeforeStory
	public void start() throws InterruptedException {
		MyEventListener eventListener = new MyEventListener(webDriver);
		webDriver = new EventFiringWebDriver(new FirefoxDriver())
				.register(eventListener);
		webDriver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@AfterStory
	public void stop() {
		webDriver.close();
		webDriver.quit();
	}

	public static WebDriver getWebDriver() {
		return webDriver;
	}
}