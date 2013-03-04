package org.google.webdriver;

import java.net.URL;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyCustomRemoteWebDriver extends RemoteWebDriver implements TakesScreenshot {
	public MyCustomRemoteWebDriver(URL remoteAddress,
			Capabilities desiredCapabilities) {
		super(remoteAddress, desiredCapabilities);
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> target)
			throws WebDriverException {
		if ((Boolean) getCapabilities().getCapability(
				CapabilityType.TAKES_SCREENSHOT)) {
			String base64Str = execute(DriverCommand.SCREENSHOT).getValue()
					.toString();
			return target.convertFromBase64Png(base64Str);
		}
		return null;
	}
}