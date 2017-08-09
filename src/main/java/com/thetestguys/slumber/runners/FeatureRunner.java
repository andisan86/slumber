package com.thetestguys.slumber.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.cucumber.listener.ExtentProperties;
import com.thetestguys.slumber.config.InitDriver;
import com.thetestguys.slumber.reporting.LogManager;
import com.thetestguys.slumber.web.Actions;
import com.thetestguys.slumber.web.WebObjects;

/**
 * This class runs the features using TestNG
 * 
 * @author Andi Santoso
 *
 */
@CucumberOptions(
        features = "src/test/resources/features"
        , glue = "id.alphait.automation.slumber.glue"
        , plugin = {"com.cucumber.listener.ExtentCucumberFormatter:"}
)
public class FeatureRunner {
	private TestNGCucumberRunner testNGCucumberRunner;
	private static String keyID;
	private static Map<String, Object> paramsMap;
    private WebObjects webObjects;
    private InitDriver initDriver;
    private WebDriver webDriver;
    private Actions actions;
    private LogManager log;

    public FeatureRunner() {
    	
    }
	
	/**
	 * Initiates reporting, actions, driver and web object
	 */
	@BeforeSuite
	public void beforeSuite() {
		//System.setProperty("mode", "prod");
		initDriver = new InitDriver();
        webDriver = initDriver.getDriver();
        webObjects = new WebObjects(webDriver);
        actions = new Actions(webDriver, webObjects);
		ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath("./report.html");
	}
	
	/**
	 * Get thread ID as map key
	 */
	@BeforeClass
	public void beforeClass() {
		keyID = Long.toString(Thread.currentThread().getId());
		log = new LogManager();
		paramsMap = new HashMap<String, Object>();
		paramsMap.put(keyID + "Actions", actions);
		paramsMap.put(keyID + "Driver", webDriver);
		paramsMap.put(keyID + "Log", log);
	}

	/**
	 * Runs the test
	 * 
	 * @param cucumberFeatureWrapper cucumberFeatureWrapper
	 */
    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeatureWrapper) {
        testNGCucumberRunner.runCucumber(cucumberFeatureWrapper.getCucumberFeature());
    }

    /**
     * Finish the test
     */
    @AfterTest
    public void afterTest() {
        testNGCucumberRunner.finish();
    }

    /**
     * Get bunch of features as TestNG's DataProvider
     * 
     * @return features
     */
    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    /**
     * Create Cucumber runner of this class
     */
    @BeforeTest
    public void setup() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    /**
     * Getter map of parameters created by this runner
     * 
     * @return paramsMap
     */
	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}
}
