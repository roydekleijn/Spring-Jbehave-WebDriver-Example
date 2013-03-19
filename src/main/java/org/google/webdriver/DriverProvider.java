package org.google.webdriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverProvider implements WebDriverProvider {
	private ThreadLocal<EventFiringWebDriver> webDriver = new ThreadLocal<EventFiringWebDriver>();
	private static String browser;
	private static boolean grid;

	static {
		browser = (System.getProperty("browser", "firefox"));
		grid = Boolean
				.parseBoolean(System.getProperty("seleniumGrid", "false"));
	}

	@Override
	public WebDriver get() {
		return webDriver.get();
	}

	@Override
	public void initialize() {
		try {
			webDriver.set(WebDriverFactory.init(browser, grid));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean saveScreenshotTo(String path) {
		File screen = (File) webDriver.get().getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screen, new File(path));
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public void end() {
		webDriver.get().close();
		webDriver.get().quit();
		webDriver.remove();
	}
}
