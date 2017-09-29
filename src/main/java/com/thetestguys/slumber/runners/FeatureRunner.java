/*
 * Copyright (c) 2017. The Test Guys
 */

package com.thetestguys.slumber.runners;

import com.cucumber.listener.Reporter;
import com.thetestguys.slumber.utils.PropertyFactory;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import com.cucumber.listener.ExtentProperties;
import com.thetestguys.slumber.config.InitDriver;
import com.thetestguys.slumber.reporting.Log;
import com.thetestguys.slumber.web.Actions;
import com.thetestguys.slumber.web.WebObjects;

/**
 * This class runs the features using TestNG
 * 
 * @author Andi Santoso
 *
 */
@CucumberOptions(
        features = "features"
        , glue = "com.thetestguys.slumber.glue"
        , plugin = {"json:output/runResult.json", "com.cucumber.listener.ExtentCucumberFormatter:"}
)
public class FeatureRunner extends AbstractTestNGCucumberTests {
    private static PropertyFactory properties = new PropertyFactory();
    private static Map<String, Object> paramsMap;
    private static String keyID;
    private static WebObjects webObjects;
    private static InitDriver initDriver;
    private static WebDriver webDriver;
    private static Actions actions;
    private static Log log;

    /**
     * Setup method to be executed before beginning of run
     */
    @BeforeClass
    public void setup() throws Exception {
        initDriver = new InitDriver();
        webDriver = initDriver.getDriver();
        webObjects = new WebObjects(webDriver);
        actions = new Actions(webDriver, webObjects);
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
//        extentProperties.setExtentXServerUrl("http://localhost:1337");
//        extentProperties.setProjectName("TestNGProject");
        extentProperties.setReportPath(properties.getValueString("setting.extentReport.report.name"));
        keyID = Long.toString(Thread.currentThread().getId());
        log = new Log();
        paramsMap = new HashMap<String, Object>();
        paramsMap.put(keyID + "Actions", actions);
        paramsMap.put(keyID + "Driver", webDriver);
        paramsMap.put(keyID + "Log", log);
    }

    /**
     * Teardown method which is executed after run
     */
    @AfterClass
    public void teardown() {
        InputStream in = getClass().getResourceAsStream(properties.getValueString("setting.extentReport.config"));
        try {
            File tempFile = File.createTempFile("extent-config", ".xml");
            tempFile.deleteOnExit();
            FileOutputStream out = new FileOutputStream(tempFile);
            IOUtils.copy(in, out);
            Reporter.loadXMLConfig(tempFile);
        } catch (Exception e) {

        }
    }

    public PropertyFactory getProperties() {
        return properties;
    }

    public void setProperties(PropertyFactory properties) {
        FeatureRunner.properties = properties;
    }
    /**
     * Getter map of parameters created by this runner
     *
     * @return paramsMap
     */
    public Map<String, Object> getParamsMap() {
        return paramsMap;
    }
}
