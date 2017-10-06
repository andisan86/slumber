/*
 * Copyright (c) 2017. The Test Guys
 */

package com.thetestguys.slumber.web;

import com.paulhammant.ngwebdriver.ByAngular;
import com.thetestguys.slumber.pojo.WebObject;
import org.openqa.selenium.By;
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
        WebObject webObject = webObjects.getWebObject(objectName);
        WebElement element = null;
        if (!webObject.getObjectId().isEmpty()) {
            element = webDriver.findElement(By.id(webObject.getObjectId()));
        } else if (!webObject.getObjectNameAttr().isEmpty()) {
            element = webDriver.findElement(By.name(webObject.getObjectNameAttr()));
        } else if (!webObject.getObjectNgModel().isEmpty()) {
            element = webDriver.findElement(ByAngular.model(webObject.getObjectNgModel()));
        } else if (!webObject.getObjectNgClick().isEmpty()) {
            element = webDriver.findElement(By.xpath("//" + webObject.getObjectType() + "[contains(@ng-click, '" + webObject.getObjectNgClick() + "')]"));
        } else if (!webObject.getObjectNgIf().isEmpty()) {
            element = webDriver.findElement(By.xpath("//" + webObject.getObjectType() + "[contains(@ng-if, '" + webObject.getObjectNgIf() + "')]"));
        } else if (!webObject.getObjectHref().isEmpty()) {
            element = webDriver.findElement(By.xpath("//" + webObject.getObjectType() + "[contains(@href, '" + webObject.getObjectHref() + "')]"));
        } else {    // raw xpath form
            element = webDriver.findElement(By.xpath(objectName));
        }
        return element;
    }
}