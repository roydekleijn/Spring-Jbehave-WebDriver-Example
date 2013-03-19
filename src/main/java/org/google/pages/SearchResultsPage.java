package org.google.pages;

import java.util.List;

import org.google.common.BasePage;
import org.google.webdriver.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends BasePage {

	@FindBy(how = How.CSS, using = "h3.r > a")
	private List<WebElement> searchResultTitles;

	public SearchResultsPage(DriverProvider provider) {
		super(provider);
		PageFactory.initElements(getDriver(), this);
	}

	public boolean isQueryInSearchTitlePresent(String query) {
		for (WebElement resultTitle : searchResultTitles) {
			if (resultTitle.getText().contains(query)) {
				return true;
			}
		}
		return false;
	}
}
