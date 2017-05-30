package id.alphait.automation.slumber.web;

import id.alphait.automation.slumber.reporting.LogManager;
import id.alphait.automation.slumber.utils.PropertyFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This class handles Browser related actions
 */
public class Browser {
    private WebDriver webDriver;
    private LogManager logs;
    private String testPlatform;
    private String testBrowser;
    private PropertyFactory props = new PropertyFactory();

    /**
     * Simple constructor
     */
    public Browser() {

    }

    /**
     * Constructor
     * @param webDriver
     */
    public Browser(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Open an URL
     * @param URL URL
     */
    public void openURL(String URL) {
        webDriver.get(URL);
    }

    /**
     * Get current URL
     * @return URL
     */
    public String getCurrentURL() {
        return webDriver.getCurrentUrl();
    }

    /**
     * Close browser
     */
    public void closeBrowser() {
        webDriver.quit();
    }

    /**
     * Get test platform
     * @return Test platform
     */
    public String getTestPlatform() {
        return testPlatform;
    }

    /**
     * Get test browser
     * @return Test browser
     */
    public String getTestBrowser() {
        return testBrowser;
    }

    /**
     * Set test platform
     * @param testPlatform Test platform
     */
    public void setTestPlatform(String testPlatform) {
        this.testPlatform = testPlatform;
    }

    /**
     * Set test browser
     * @param testBrowser Test browser
     */
    public void setTestBrowser(String testBrowser) {
        this.testBrowser = testBrowser;
    }

}