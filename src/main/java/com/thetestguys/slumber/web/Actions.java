package com.thetestguys.slumber.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class handles actions that can be performed on a browser
 */
public class Actions {
    private WebDriver webDriver;
    private WebObjects webObjects;

    /**
     * Constructor
     * @param webDriver WebDriver
     * @param webObjects WebObjects
     */
    public Actions(WebDriver webDriver, WebObjects webObjects) {
        this.webDriver = webDriver;
        this.webObjects = webObjects;
    }

    /**
     * Click an object
     * @param objectName Object to be clicked
     */
    public void click(String objectName) {
        getWebElementFromObjectName(objectName).click();
    }

    /**
     * Enquire if an object is displayed on screen
     * @param objectName Object to be enquired
     * @return True if  displayed, else false
     */
    public Boolean isObjectDisplayed(String objectName) {
        return getWebElementFromObjectName(objectName).isDisplayed();
    }

    /**
     * Helper method to get a web element by its object name
     * @param objectName Object's name
     * @return WebElement of object
     */
    private WebElement getWebElementFromObjectName(String objectName) {
        return webObjects.getWebObject(objectName);
    }
}