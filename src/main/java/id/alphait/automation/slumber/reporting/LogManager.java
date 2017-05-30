package id.alphait.automation.slumber.reporting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.gherkin.model.And;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Then;
import com.aventstack.extentreports.gherkin.model.When;
import id.alphait.automation.slumber.utils.PropertyFactory;

/**
 * This class describes report logging functionality
 * 
 * @author Andi Santoso
 *
 */
public class LogManager {
	private LogConsole log;
	ExtentTest test;
	
	/**
	 * Constructor 
	 *
	 * @param test ExtentTest
	 */
	public LogManager(ExtentTest test) {
		this.test = test;
		log = new LogConsole();
	}
	
	/**
	 * Log step definition
	 * 
	 * @param gherkinKeyword Gherkin keyword. Accepts either Given, When, And, Then
	 * @param item item
	 */
	public synchronized void logStepDef(String gherkinKeyword, String item) {
		switch(gherkinKeyword.toUpperCase()) {
		case "GIVEN":
			test.createNode(Given.class, item).info("INFO");
			break;
		case "WHEN":
			test.createNode(When.class, item).info("INFO");
			break;
		case "AND":
			test.createNode(And.class, item).info("INFO");
			break;
		case "THEN":
			test.createNode(Then.class, item).info("INFO");
			break;
		default:
			test.createNode(When.class, item).info("INFO");
			break;
		}
		log.logStep(item);
		log.logDebug(item);
	}
	
	/**
	 * Log debug message to console
	 * 
	 * @param item item
	 */
	public synchronized void logDebug(String item) {
		log.logDebug(item);
	}
	
	/**
	 * Log pass assertion
	 * 
	 * @param item item
	 */
	public synchronized void logPassAssertion(String item) {
		test.createNode(Then.class, item).pass(item);
		log.logAssertPass(item);
		log.logDebug(item);
	}
	
	/**
	 * Log warning
	 * 
	 * @param item item
	 */
	public synchronized void logWarning(String item) {
		test.createNode(Then.class, item).warning(item);
		log.logWarning(item);
	}
	
	/**
	 * Log info
	 * 
	 * @param item item
	 */
	public synchronized void logInfo(String item) {
		test.createNode(Then.class, item).info(item);
		log.logDebug(item);
	}
	
	/**
	 * Log fail assertion
	 * 
	 * @param item item
	 * @param screenshotPath screenshot path
	 */
	public synchronized void logFailAssertion(String item, String screenshotPath) {
		try {
			test.createNode(Then.class, item).fail(item, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.logAssertFail(item);
		log.logDebug(item);
	}
}
