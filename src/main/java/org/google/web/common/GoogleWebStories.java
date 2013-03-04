package org.google.web.common;

import org.google.webdriver.DriverProvider;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;
import org.jbehave.core.steps.spring.SpringApplicationContextFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.WebDriverHtmlOutput;
import org.jbehave.web.selenium.WebDriverProvider;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;

public class GoogleWebStories extends JUnitStories {

	private WebDriverProvider driverProvider = new DriverProvider();

	// private static ContextView contextView = new
	// LocalFrameContextView().sized(640,120);
	// private static SeleniumContext seleniumContext = new SeleniumContext();

	public GoogleWebStories() {
    Embedder embedder = configuredEmbedder();

    embedder.embedderControls().doGenerateViewAfterStories(true)
        .doIgnoreFailureInStories(true).doIgnoreFailureInView(true)
        .doVerboseFiltering(true);
	}

  @Override
  public Configuration configuration() {
    Configuration configuration = makeConfiguration(this.getClass(),
        driverProvider);
    return configuration;
  }

  public static Configuration makeConfiguration(Class<?> embeddableClass,
      WebDriverProvider driverProvider) {
    Properties viewResources = new Properties();
    viewResources.put("decorateNonHtml", "true");

    // Start from default ParameterConverters instance
    ParameterConverters parameterConverters = new ParameterConverters();
    // factory to allow parameter conversion and loading from external
    // resources (used by StoryParser too)
    ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(
        new LocalizedKeywords(),
        new LoadFromClasspath(embeddableClass), parameterConverters);
    // add custom converters
    parameterConverters.addConverters(new DateConverter(
        new SimpleDateFormat("yyyy-MM-dd")),
        new ExamplesTableConverter(examplesTableFactory));

    SeleniumConfiguration seleniumConfig = new SeleniumConfiguration();

    seleniumConfig.useWebDriverProvider(driverProvider);
    seleniumConfig.useStoryLoader(new LoadFromClasspath(embeddableClass.getClassLoader()));

    StoryReporterBuilder reporter = new StoryReporterBuilder();
    reporter.withCodeLocation(
        CodeLocations
            .codeLocationFromClass(embeddableClass))
        .withDefaultFormats()
        .withFormats(XML, CONSOLE, TXT, WebDriverHtmlOutput.WEB_DRIVER_HTML);
    seleniumConfig
        .useFailureStrategy(new FailingUponPendingStep())
        // .useStepMonitor(new SeleniumStepMonitor(contextView,
        // seleniumContext, new SilentStepMonitor()))
        .useStoryReporterBuilder(reporter)
        .useParameterConverters(parameterConverters);// new
    // SeleniumContextOutput(seleniumContext),
    return seleniumConfig;
  }

	@Override
	public InjectableStepsFactory stepsFactory() {
		ApplicationContext context = new SpringApplicationContextFactory(
				"org/google/web/google-webacceptancetest.xml")
				.createApplicationContext();
		return new SpringStepsFactory(configuration(), context);
	}

	@Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(
				codeLocationFromClass(this.getClass()), "**/*.story",
				"**/excluded*.story");
	}

}