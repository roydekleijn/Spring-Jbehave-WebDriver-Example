package org.google.web.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends WebDriverPage {
	
	public SearchResultsPage(WebDriverProvider provider) {
		super(provider);
	}

	@FindBy(how = How.CSS, using = "h3.r")
	private List<WebElement> searchResultTitles;

	private List<String> resultTitles = new ArrayList<String>();

	/*
	@BeforeScenario
	public void setup() {
		PageFactory.initElements(getDriverProvider().get(), this);
	}
	*/	

	@Then("<query> is shown in the searchresulttitles")
	public void verifyTitle(@Named("query") String query)
			throws InterruptedException {
		Iterator<WebElement> itr = searchResultTitles.iterator();
		while (itr.hasNext()) {
			WebElement element = itr.next();
			System.out.println(element.getText());
			resultTitles.add(element.getText());
		}
		assertThat(resultTitles, hasItem(containsString(query)));
	}

}
