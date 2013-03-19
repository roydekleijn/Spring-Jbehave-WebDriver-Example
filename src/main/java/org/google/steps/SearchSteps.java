package org.google.steps;

import static org.hamcrest.MatcherAssert.assertThat;

import org.google.common.BasePage;
import org.google.pages.HomePage;
import org.google.pages.SearchResultsPage;
import org.google.webdriver.DriverProvider;
import org.hamcrest.Matchers;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class SearchSteps extends BasePage {

	public SearchSteps(DriverProvider provider) {
		super(provider);
	}

	@Given("I am on the homepage")
	public void openHomePage() throws InterruptedException {
		new HomePage(getProvider()).init();
	}

	@When("I search for <query>")
	public void clickFirstResult(@Named("query") String query)
			throws InterruptedException {
		new HomePage(getProvider()).searchFor(query);
	}

	@Then("<query> is in resultlist")
	public void queryIsInResultlit(@Named("query") String query) {
		assertThat(
				new SearchResultsPage(getProvider())
						.isQueryInSearchTitlePresent(query),
				Matchers.equalTo(true));
	}
}
