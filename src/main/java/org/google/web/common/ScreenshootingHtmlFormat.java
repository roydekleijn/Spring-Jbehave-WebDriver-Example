package org.google.web.common;

import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.web.selenium.WebDriverProvider;

public class ScreenshootingHtmlFormat extends Format{
	  private WebDriverProvider webDriverProvider;

	  public ScreenshootingHtmlFormat(WebDriverProvider webDriverProvider) {
	      super("HTML");
	      this.webDriverProvider = webDriverProvider;
	  }

	  @Override
	  public StoryReporter createStoryReporter(
	          FilePrintStreamFactory factory,
	          StoryReporterBuilder builder) {
	      factory.useConfiguration(
	              builder.fileConfiguration("html"));
	      return new ScreenshootingHtmlOutput(factory.createPrintStream(),
	                  builder, webDriverProvider)
	              .doReportFailureTrace(builder.reportFailureTrace())
	              .doCompressFailureTrace(builder.compressFailureTrace());
	  }
	}