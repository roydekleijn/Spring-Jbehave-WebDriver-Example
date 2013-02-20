package org.google.web.common;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;

import java.text.SimpleDateFormat;
import java.util.List;

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
import org.jbehave.web.selenium.WebDriverProvider;
import org.springframework.context.ApplicationContext;

import com.google.common.util.concurrent.MoreExecutors;

public class GoogleWebStories extends JUnitStories {

	private WebDriverProvider driverProvider = new SeleniumWebDriverProvider();
	private Configuration configuration;

	// private static ContextView contextView = new
	// LocalFrameContextView().sized(640,120);
	// private static SeleniumContext seleniumContext = new SeleniumContext();

	public GoogleWebStories() {
		Embedder embedder = configuredEmbedder();
		embedder.embedderControls().doGenerateViewAfterStories(true)
				.doIgnoreFailureInStories(true).doIgnoreFailureInView(true)
				.doVerboseFiltering(true);
		embedder.useExecutorService(MoreExecutors.sameThreadExecutor());
	}

	@Override
	public Configuration configuration() {
		configuration = makeConfiguration(this.getClass(), driverProvider);
		return configuration;
	}

	public static Configuration makeConfiguration(Class<?> embeddableClass,
			WebDriverProvider driverProvider) {
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
		return new SeleniumConfiguration()
				.useWebDriverProvider(driverProvider)
				// .useSeleniumContext(seleniumContext)
				.useFailureStrategy(new FailingUponPendingStep())
				// .useStepMonitor(new SeleniumStepMonitor(contextView,
				// seleniumContext, new SilentStepMonitor()))
				.useStoryLoader(
						new LoadFromClasspath(embeddableClass.getClassLoader()))
				.useStoryReporterBuilder(
						new StoryReporterBuilder()
								.withCodeLocation(
										CodeLocations
												.codeLocationFromClass(embeddableClass))
								.withDefaultFormats()
								.withFormats(
										CONSOLE,
										new ScreenshootingHtmlFormat(
												driverProvider)))
				.useParameterConverters(parameterConverters);// new
																// SeleniumContextOutput(seleniumContext),
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