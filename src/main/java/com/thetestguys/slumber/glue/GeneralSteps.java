/*
 * Copyright (c) 2017. The Test Guys
 */

package com.thetestguys.slumber.glue;

import com.thetestguys.slumber.pojo.Actor;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.injectors.AnnotatedFieldInjection;
import com.thetestguys.slumber.reporting.Log;
import com.thetestguys.slumber.runners.FeatureRunner;
import com.thetestguys.slumber.web.Actions;
import com.thetestguys.slumber.web.Browser;
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
    private Log log;
    private Actor actor;

    /**
     * Constructor
     */
    public GeneralSteps() {
        setParameters();
    }
    
    /**
     * Given <i>Actor</i> open a browser
     */
    @Given("(.*) open a browser")
    public void Actor_open_a_browser(String actorName) {
        actor = new Actor();
        actor.setName(actorName);
    	log.logStepDef("Given", actor.getName() + " open a browser");
        browser = new Browser(webDriver);
    }

    /**
     * When <i>Actor</i> go to this URL <i>URL</i>
     *
     * @param actorName Actor's name
     * @param URL URL
     */
    @When("(.*) go to this URL (.*)")
    public void Actor_go_to_this_URL(String actorName, String URL) {
    	log.logStepDef("When", actor.getName() + " go to this URL");
        browser.openURL(URL);
    }

    /**
     * When <i>Actor</i> click on this <i>"objectName"</i>
     *
     * @param actorName Actor's name
     * @param objectName Object to be manipulated
     */
    @When("(.*) click on this \"(.*)\"")
    public void Actor_click_on_this(String actorName, String objectName) {
    	log.logStepDef("When", actor.getName() + " click on this " + objectName);
        actions.click(objectName);
    }

    /**
     * When <i>Actor</i> close browser
     *
     * @param actorName Actor's name
     */
    @When("(.*) close browser")
    public void Actor_close_browser(String actorName) {
    	log.logStepDef("When", actor.getName() + " close browser");
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
			if (param.containsKey(currentThread + "Actions")) actions = (Actions) param.get(currentThread + "Actions");		
			if (param.containsKey(currentThread + "Driver")) webDriver = (WebDriver) param.get(currentThread + "Driver");
			if (param.containsKey(currentThread + "Log")) log = (Log) param.get(currentThread + "Log");
		}
    }
}