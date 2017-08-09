package com.thetestguys.slumber.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This class creates a driver of choice
 * 
 * @author Andi Santoso
 *
 */
public class InitDriver {

    /**
     * Enum of supported driver types
     */
    public enum DriverType implements DriverSetup {
        FIREFOX {
            public DesiredCapabilities getDesiredCapabilities() {
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                ChromeOptions options = new ChromeOptions();
                options.addArguments(Arrays.asList("--start-maximized"));
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                capabilities.setCapability("takesScreenshot", true);
                return capabilities;
            }

            public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
                return new FirefoxDriver(capabilities);
            }
        },
        CHROME {
            public DesiredCapabilities getDesiredCapabilities() {
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
                HashMap<String, String> chromePreferences = new HashMap<String, String>();
                ChromeOptions options = new ChromeOptions();
                options.addArguments(Arrays.asList("--start-maximized"));
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                chromePreferences.put("profile.password_manager_enabled", "false");
                capabilities.setCapability("chrome.prefs", chromePreferences);
                capabilities.setCapability("takesScreenshot", true);
                return capabilities;
            }

            public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
                return new ChromeDriver(capabilities);
            }
        },
        IE {
            public DesiredCapabilities getDesiredCapabilities() {
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                ChromeOptions options = new ChromeOptions();
                options.addArguments(Arrays.asList("--start-maximized"));
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                capabilities.setCapability("requireWindowsFocus", true);
                capabilities.setCapability("takesScreenshot", true);
                return capabilities;
            }

            public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
                return new InternetExplorerDriver(capabilities);
            }
        },
        PHANTOMJS {
            public DesiredCapabilities getDesiredCapabilities() {
                DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
                final List<String> cliArguments = new ArrayList<String>();
                cliArguments.add("--web-security=false");
                cliArguments.add("--ssl-protocol=any");
                cliArguments.add("--ignore-ssl-errors=true");
                capabilities.setCapability("phantomjs.cli.args", cliArguments);
                capabilities.setCapability("takesScreenshot", true);
                return capabilities;
            }

            public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
                return new InternetExplorerDriver(capabilities);
            }
        }
    }

    private DriverType drivingType;
    private String browser = System.getProperty("browser").toUpperCase();
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch").toUpperCase();
    private final boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver");
    private final String driverLocation = System.getProperty("driverLocation");
    private DesiredCapabilities caps;

    /**
     * Constructor
     */
    public InitDriver() {
        drivingType = DriverType.valueOf(browser.toUpperCase());
        caps = drivingType.getDesiredCapabilities();
        switch(browser) {
            case "FIREFOX":
                System.setProperty("webdriver.gecko.driver", driverLocation);
                break;
            case "CHROME":
                System.setProperty("webdriver.chrome.driver", driverLocation);
                break;
            case "IE":
                System.setProperty("webdriver.ie.driver", driverLocation);
                break;
            case "PHANTOMJS":
                System.setProperty("phantomjs.binary.path", driverLocation);
                break;
            default:
                throw new IllegalArgumentException("Please specify correct browser driver type.");
        }
    }

    /**
     * Method to get created driver
     *
     * @return WebDriver
     */
    public WebDriver getDriver() {
        System.out.println("");
        System.out.println("Current Operating System: " + operatingSystem);
        System.out.println("Current Architecture: " + systemArchitecture);
        System.out.println("Current Driver Location: " + driverLocation);
        System.out.println("");

        if (useRemoteWebDriver) {
            URL seleniumGridURL = null;
            try {
                seleniumGridURL = new URL(System.getProperty("gridURL"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
            String desiredPlatform = System.getProperty("desiredPlatform");

            if (desiredPlatform != null && !desiredPlatform.isEmpty()) {
                caps.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
            }

            if (desiredBrowserVersion != null && !desiredBrowserVersion.isEmpty()) {
                caps.setVersion(desiredBrowserVersion);
            }

            return new RemoteWebDriver(seleniumGridURL, caps);
        } else {

            return drivingType.getWebDriverObject(caps);
        }
    }
}
