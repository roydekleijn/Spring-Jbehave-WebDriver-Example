package org.google.web.common;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStories;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class SeleniumWebDriver implements WebDriver {
	public EventFiringWebDriver webDriver;
	private String url;
	private String browser;
	private boolean grid;
	private MyCustomRemoteWebDriver remoteDriver;
	private DesiredCapabilities capability;

	public SeleniumWebDriver() throws IOException {
		// Initialize parameters
		url = System.getProperty("url", "http://www.google.com");
		browser = System.getProperty("browser", "firefox");
		grid = Boolean
				.parseBoolean(System.getProperty("seleniumGrid", "false"));
	}

	@BeforeStories
	public void setUp() throws IOException {
		MyEventListener eventListener = new MyEventListener(webDriver);
		webDriver = new EventFiringWebDriver(new FirefoxDriver())
				.register(eventListener);
		webDriver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterStories
	public void tearDown() {
		webDriver.quit();
	}

	@Override
	public void close() {
		webDriver.close();
	}

	@Override
	public WebElement findElement(By by) {
		return webDriver.findElement(by);
	}

	@Override
	public List<WebElement> findElements(By by) {
		return webDriver.findElements(by);
	}

	@Override
	public void get(String url) {
		webDriver.get(url);
	}

	@Override
	public String getCurrentUrl() {
		return webDriver.getCurrentUrl();
	}

	@Override
	public String getPageSource() {
		return webDriver.getPageSource();
	}

	@Override
	public String getTitle() {
		return webDriver.getTitle();
	}

	@Override
	public String getWindowHandle() {
		return webDriver.getWindowHandle();
	}

	@Override
	public Set<String> getWindowHandles() {
		return webDriver.getWindowHandles();
	}

	@Override
	public Options manage() {
		return webDriver.manage();
	}

	@Override
	public Navigation navigate() {
		return webDriver.navigate();
	}

	@Override
	public void quit() {
		webDriver.quit();
	}

	@Override
	public TargetLocator switchTo() {
		return webDriver.switchTo();
	}

}