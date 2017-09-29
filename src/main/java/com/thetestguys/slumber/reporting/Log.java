/*
 * Copyright (c) 2017. The Test Guys
 */

package com.thetestguys.slumber.reporting;

import java.io.IOException;
import com.cucumber.listener.Reporter;

/**
 * This class describes report logging functionality
 * 
 * @author Andi Santoso
 *
 */
public class Log {
	private LogConsole logConsole;
	
	/**
	 * Constructor
	 */
	public Log() {
		logConsole = new LogConsole();
	}
	
	/**
	 * Log step definition
	 * 
	 * @param gherkinKeyword Gherkin keyword. Accepts either Given, When, And, Then
	 * @param item item
	 */
	public synchronized void logStepDef(String gherkinKeyword, String item) {
		Reporter.addStepLog(item);
		logConsole.logStep(item);
		logConsole.logDebug(item);
	}
	
	/**
	 * Log debug message to console
	 * 
	 * @param item item
	 */
	public synchronized void logDebug(String item) {
		logConsole.logDebug(item);
	}
	
	/**
	 * Log pass assertion
	 * 
	 * @param item item
	 */
	public synchronized void logPassAssertion(String item) {
		Reporter.addStepLog(item);
		logConsole.logAssertPass(item);
		logConsole.logDebug(item);
	}
	
	/**
	 * Log warning
	 * 
	 * @param item item
	 */
	public synchronized void logWarning(String item) {
		Reporter.addStepLog(item);
		logConsole.logWarning(item);
	}
	
	/**
	 * Log info
	 * 
	 * @param item item
	 */
	public synchronized void logInfo(String item) {
		Reporter.addStepLog(item);
		logConsole.logDebug(item);
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
		logConsole.logAssertFail(item);
		logConsole.logDebug(item);
	}
}
