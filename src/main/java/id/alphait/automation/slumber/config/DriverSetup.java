package id.alphait.automation.slumber.config;

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