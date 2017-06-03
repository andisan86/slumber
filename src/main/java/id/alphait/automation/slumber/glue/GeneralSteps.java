package id.alphait.automation.slumber.glue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import id.alphait.automation.slumber.reporting.LogManager;
import id.alphait.automation.slumber.runners.FeatureRunner;
import id.alphait.automation.slumber.web.Actions;
import id.alphait.automation.slumber.web.Browser;
import org.openqa.selenium.WebDriver;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.injectors.AnnotatedFieldInjection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class contains all general steps
 * 
 * @author Andi Santoso
 * 
 */
public class GeneralSteps {
    private Browser browser;
    private WebDriver webDriver;
    private Actions actions;
    private LogManager log;

    /**
     * Constructor
     */
    public GeneralSteps() {
    	setParameters();
    }
    
    /**
     * Given I open a browser
     */
    @Given("I open a browser")
    public void I_open_a_browser() {
    	log.logStepDef("Given", "I open a browser");
        browser = new Browser(webDriver);
    }

    /**
     * When I go to this URL URL
     * @param URL
     */
    @When("I go to this URL (.*)")
    public void I_go_to_this_URL(String URL) {
    	log.logStepDef("When", "I go to this URL");
        browser.openURL(URL);
    }

    /**
     * When I click on this "objectName"
     * @param objectName Object to be manipulated
     */
    @When("I click on this \"(.*)\"")
    public void I_click_on_this(String objectName) {
    	log.logStepDef("When", "I click on this " + objectName);
        actions.click(objectName);
    }

    /**
     * When I close browser
     */
    @When("I close browser")
    public void I_close_browser() {
    	log.logStepDef("When", "I close browser");
        browser.closeBrowser();
    }
    
    /**
     * This method is used to get actions, drivers and logs objects created when starting test
     */
    private void setParameters() {
    	String currentThread = Long.toString(Thread.currentThread().getId());
		DefaultPicoContainer pico = new DefaultPicoContainer(new AnnotatedFieldInjection());
		pico.addComponent(FeatureRunner.class);
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		params.add(pico.getComponent(FeatureRunner.class).getParamsMap());
		for (Map<String, Object> param : params) {
			if (param.containsKey(currentThread)) {
				actions = (Actions) param.get(currentThread + "Actions");
				webDriver = (WebDriver) param.get(currentThread + "Driver");
				log = (LogManager) param.get(currentThread + "Log");
				break;
			}
		}
    }
}