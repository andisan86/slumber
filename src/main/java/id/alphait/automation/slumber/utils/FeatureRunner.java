package id.alphait.automation.slumber.utils;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import id.alphait.automation.slumber.config.InitDriver;
import id.alphait.automation.slumber.reporting.ExtentManager;
import id.alphait.automation.slumber.reporting.LogManager;
import id.alphait.automation.slumber.web.Actions;
import id.alphait.automation.slumber.web.Browser;
import id.alphait.automation.slumber.web.WebObjects;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * This class runs the features using TestNG
 * 
 * @author Andi Santoso
 *
 */
@CucumberOptions(
        features = "features"
        , glue = "id.alphait.automation.slumber.glue"
)
public class FeatureRunner {
	private TestNGCucumberRunner testNGCucumberRunner;
	private static ExtentTest extentTest;
	private static String keyID;
	private static Map<String, Object> paramsMap = new HashMap<String, Object>();
	private static ExtentReports extent;
    private WebObjects webObjects;
    private InitDriver initDriver;
    private WebDriver webDriver;
    private Actions actions;
    private LogManager log;

    /**
     * Finish ExtentReport
     */
	@AfterSuite
	public void afterSuite() {
		ExtentManager.getInstance().flush();
	}
	
	/**
	 * Initiates reporting, actions, driver and web object
	 */
	@BeforeSuite
	public void beforeSuite() {
		System.setProperty("mode", "prod");
		extent = ExtentManager.createInstance();
		initDriver = new InitDriver();
        webDriver = initDriver.getDriver();
        webObjects = new WebObjects(webDriver);
        actions = new Actions(webDriver, webObjects);
	}
	
	/**
	 * Get thread ID as map key
	 */
	@BeforeClass
	public void beforeClass() {
		keyID = Long.toString(Thread.currentThread().getId());
	}

	/**
	 * Runs the test
	 * 
	 * @param cucumberFeatureWrapper cucumberFeatureWrapper
	 */
    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeatureWrapper) {
    	String scenarioName = cucumberFeatureWrapper.getCucumberFeature().getGherkinFeature().getName().trim();
		extentTest = ExtentManager.createExtentTest(scenarioName);
		log = new LogManager(extentTest.createNode(com.aventstack.extentreports.gherkin.model.Scenario.class, scenarioName));
		paramsMap.put(keyID, extentTest);
		paramsMap.put(keyID + "Actions", actions);
		paramsMap.put(keyID + "Driver", webDriver);
		paramsMap.put(keyID + "Log", log);
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
