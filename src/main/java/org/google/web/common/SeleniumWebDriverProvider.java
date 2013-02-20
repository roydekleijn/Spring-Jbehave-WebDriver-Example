package org.google.web.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class SeleniumWebDriverProvider implements WebDriverProvider {
	private ThreadLocal<SeleniumWebDriver> webDriver = new ThreadLocal<SeleniumWebDriver>();

	@Override
	public SeleniumWebDriver get() {
		SeleniumWebDriver driver = webDriver.get();
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
		webDriver.get().quit();
		webDriver.remove();
	}
}