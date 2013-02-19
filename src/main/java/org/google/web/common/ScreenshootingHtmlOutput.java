package org.google.web.common;

import java.io.PrintStream;

import org.jbehave.core.failures.UUIDExceptionWrapper;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.web.selenium.WebDriverHtmlOutput;
import org.jbehave.web.selenium.WebDriverProvider;

public class ScreenshootingHtmlOutput extends WebDriverHtmlOutput {

	  private MyWebDriverScreenshotOnFailure screenshotMaker;

	  public ScreenshootingHtmlOutput(PrintStream output,
	      StoryReporterBuilder reporterBuilder,
	      WebDriverProvider webDriverProvider) {
	    super(output, reporterBuilder.keywords());
	    screenshotMaker = new MyWebDriverScreenshotOnFailure(
	        webDriverProvider);
	  }

	  @Override
	  public void failed(String step, Throwable storyFailure) {
	    super.failed(step, storyFailure);
	    
	    try {
	      UUIDExceptionWrapper uuidWrappedFailure =
	          (UUIDExceptionWrapper) storyFailure;
	      screenshotMaker.afterScenarioFailure(uuidWrappedFailure);
	    } catch (Exception e) {
	      System.out.println("Screenshot failed");
	    }
	    
	  }
	}