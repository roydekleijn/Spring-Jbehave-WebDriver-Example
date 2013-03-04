package org.google.webdriver.listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MyEventListener implements WebDriverEventListener {
	List<String> ignoreElements = new ArrayList<String>();

	public MyEventListener() {
		ignoreElements.add("");
	}

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		System.out.println("afterChangeValueOf");
	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		System.out.println("afterClickOn");
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("afterFindBy");
	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
		System.out.println("afterNavigateBack");
	}

	@Override
	public void afterNavigateForward(WebDriver arg0) {
		System.out.println("afterNavigateForward");
	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		System.out.println("afterNavigateTo");
	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		System.out.println("afterNavigateTo");
	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		System.out.println("beforeChangeValueOf");
	}

	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		System.out.println("beforeChangeValueOf");

	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Wait for element is present");
		if (!ignoreElements.contains(by.toString())) {
			new WebDriverWait(driver, 30, 500).until(ExpectedConditions
					.visibilityOfElementLocated(by));
		}
	}

	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		System.out.println("beforeNavigateBack");
	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		System.out.println("beforeNavigateBack");
	}

	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		System.out.println("beforeNavigateTo");
	}

	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		System.out.println("beforeScript");
	}

	@Override
	public void onException(Throwable er, WebDriver driver) {
		System.out.println("onException");
		System.out.println("getLocalizedMessage " + er.getLocalizedMessage());
		System.out.println("getStackTrace " + er.getStackTrace());
		System.out.println("getMessage " + er.getMessage());
		System.out.println("getCause " + er.getCause());
	}
}
