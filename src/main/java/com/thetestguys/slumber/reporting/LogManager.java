package com.thetestguys.slumber.reporting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.cucumber.listener.Reporter;
import com.thetestguys.slumber.utils.PropertyFactory;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.gherkin.model.And;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Then;
import com.aventstack.extentreports.gherkin.model.When;

/**
 * This class describes report logging functionality
 * 
 * @author Andi Santoso
 *
 */
public class LogManager {
	private LogConsole log;
	
	/**
	 * Constructor 
	 *
	 * @param test ExtentTest
	 */
	public LogManager() {
		log = new LogConsole();
	}
	
	/**
	 * Log step definition
	 * 
	 * @param gherkinKeyword Gherkin keyword. Accepts either Given, When, And, Then
	 * @param item item
	 */
	public synchronized void logStepDef(String gherkinKeyword, String item) {
		Reporter.addStepLog(item);
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
		Reporter.addStepLog(item);
		log.logAssertPass(item);
		log.logDebug(item);
	}
	
	/**
	 * Log warning
	 * 
	 * @param item item
	 */
	public synchronized void logWarning(String item) {
		Reporter.addStepLog(item);
		log.logWarning(item);
	}
	
	/**
	 * Log info
	 * 
	 * @param item item
	 */
	public synchronized void logInfo(String item) {
		Reporter.addStepLog(item);
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
			Reporter.addStepLog(item);
			Reporter.addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.logAssertFail(item);
		log.logDebug(item);
	}
}
