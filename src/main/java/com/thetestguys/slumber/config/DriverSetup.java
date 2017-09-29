/*
 * Copyright (c) 2017. The Test Guys
 */

package com.thetestguys.slumber.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Interface for DriverSetup
 * 
 * @author Andi Santoso
 *
 */
public interface DriverSetup {
    WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities);
    DesiredCapabilities getDesiredCapabilities();
}