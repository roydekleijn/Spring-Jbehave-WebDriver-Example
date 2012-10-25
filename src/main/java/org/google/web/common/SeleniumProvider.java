package org.google.web.common;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStories;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class SeleniumProvider implements WebDriver {
	public EventFiringWebDriver driver;
	private String url;
	private String browser;
	private boolean grid;
	private RemoteWebDriver remoteDriver;
	private DesiredCapabilities capability;

	public SeleniumProvider() throws IOException {
		// Initialize parameters
		url = System.getProperty("url", "http://www.google.com");
		browser = System.getProperty("browser", "firefox");
		grid = Boolean
				.parseBoolean(System.getProperty("seleniumGrid", "false"));
	}

	@BeforeStories
	public void setUp() throws IOException {
		if (grid) {
			if (browser.equals("chrome")) {
				capability = DesiredCapabilities.chrome();
			} else if (browser.equals("firefox")) {
				capability = DesiredCapabilities.firefox();
				capability.setVersion("15");
			} else if (browser.equals("iexplore")) {
				capability = DesiredCapabilities.internetExplorer();
				capability.setVersion("VISTA");
			} else {
				System.out
						.println("Unknown browser. Supported browsers are: chrome, firefox, iexplore");
			}
			remoteDriver = new MyCustomRemoteWebDriver(new URL(
					"http://grid-ip:4444/wd/hub"), capability);
			driver = new EventFiringWebDriver(remoteDriver);
			WebDriverEventListener eventListener = new MyEventListener(
					remoteDriver);
			driver.register(eventListener);

		} else {

			File file = new File("src/test/resources/firebug-1.9.2.xpi");
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.addExtension(file);
			firefoxProfile.setPreference("extensions.firebug.currentVersion",
					"1.9.2"); // Avoid startup screen
			firefoxProfile.setPreference(
					"extensions.firebug.console.enableSites", true);
			WebDriverEventListener eventListener = new MyEventListener(driver);
			driver = new EventFiringWebDriver(new FirefoxDriver())
					.register(eventListener);
		}
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterStories
	public void tearDown() {
		driver.quit();
	}

	@Override
	public void close() {
		driver.close();
	}

	@Override
	public WebElement findElement(By by) {
		return driver.findElement(by);
	}

	@Override
	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	@Override
	public void get(String url) {
		driver.get(url);
	}

	@Override
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	@Override
	public String getPageSource() {
		return driver.getPageSource();
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

	@Override
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	@Override
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	@Override
	public Options manage() {
		return driver.manage();
	}

	@Override
	public Navigation navigate() {
		return driver.navigate();
	}

	@Override
	public void quit() {
		driver.quit();
	}

	@Override
	public TargetLocator switchTo() {
		return driver.switchTo();
	}

}