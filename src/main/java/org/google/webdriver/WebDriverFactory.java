package org.google.webdriver;

import java.io.IOException;
import java.net.URL;

import org.google.webdriver.listeners.MyEventListener;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class WebDriverFactory {
	public static EventFiringWebDriver init(String browser, boolean grid)
			throws IOException {
		EventFiringWebDriver webDriver = null;
		DesiredCapabilities capability = null;
		if (grid) {
			if (browser.equals("chrome")) {
				capability = DesiredCapabilities.chrome();
			} else if (browser.equals("firefox")) {
				capability = DesiredCapabilities.firefox();
			} else if (browser.equals("iexplore")) {
				capability = DesiredCapabilities.internetExplorer();
			} else {
				System.err
						.println("Unknown browser. Supported browsers are: chrome, firefox, iexplore");
			}
			MyCustomRemoteWebDriver remoteDriver = new MyCustomRemoteWebDriver(
					new URL("http://localhost:4444/wd/hub"), capability);
			webDriver = new EventFiringWebDriver(remoteDriver);
			WebDriverEventListener eventListener = new MyEventListener();
			return webDriver.register(eventListener);
		} else {
			capability = new DesiredCapabilities();
			WebDriverEventListener eventListener = new MyEventListener();
			if (browser.equals("firefox")) {
				webDriver = new EventFiringWebDriver(new FirefoxDriver(
						capability)).register(eventListener);
			} else if (browser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"src/test/resources/chromedriver.exe");
				webDriver = new EventFiringWebDriver(new ChromeDriver(
						capability)).register(eventListener);
			} else if (browser.equals("iexplore")) {
				System.setProperty("webdriver.ie.driver",
						"src/test/resources/IEDriverServer.exe");
				return new EventFiringWebDriver(new InternetExplorerDriver(
						capability)).register(eventListener);
			}
		}
		return webDriver;
	}

}
