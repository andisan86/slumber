package id.alphait.automation.slumber.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import id.alphait.automation.slumber.utils.PropertyFactory;

/**
 * This class manages reporting to ExtentReport/ExtentX
 * 
 * @author Andi Santoso
 *
 */
public class ExtentManager {
	private static ExtentReports extent;
    private static ExtentTest extentTest;
    private static String testName;
    private static String buildName;
    static PropertyFactory properties = new PropertyFactory();
    
    /**
     * Get instance of ExtentReports. If null, create one.
     * 
     * @return extent
     */
    public synchronized static ExtentReports getInstance() {
    	if (extent == null)
    		createInstance();
    	
        return extent;
    }
    
    /**
     * Get instance of ExtentTest. If null, create one.
     * 
     * @return extentTest
     */
    public synchronized static ExtentTest getExtentTest() {
    	if (extentTest == null) createExtentTest(testName);
    	return extentTest;
    }
    
    /**
     * Create instance of ExtentReports
     * 
     * @return extent
     */
    public synchronized static ExtentReports createInstance() {
    	extent = new ExtentReports();
    	Throwable t = null;
    	try {
    		System.getProperty("extentBuildName").isEmpty();
    	} catch (NullPointerException ne) {
    		t = ne;
    	} finally {
    		if (t == null) {
    	    	buildName = System.getProperty("extentBuildName");	
    		} else {
    			buildName = "BUILD-TEST";
    		}
    	}
    	if (!System.getProperty("mode").equalsIgnoreCase("test")) {
    		extent.attachReporter(setHtmlReporter(), setXReporter());
        } else {
        	extent.attachReporter(setHtmlReporter());
        }
        return extent;
    }
    
    /**
     * Create instance of ExtentTest
     * 
     * @param testName Test (ie. feature) name
     * @return extentTest
     */
    public synchronized static ExtentTest createExtentTest(String testName) {
    	ExtentManager.testName = testName;
        extentTest = extent.createTest(testName);
    	return extentTest;
    }
    
    /**
     * Method which sets HTML report options
     * 
     * @return htmlreporter
     */
    private static ExtentHtmlReporter setHtmlReporter() {
    	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(properties.getValueString("setting.extentReport.report.name"));
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(false);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(buildName);
        htmlReporter.setAppendExisting(true);
        return htmlReporter;
    }
    
    /**
     * Method which sets ExtentX report options
     * 
     * @return xReporter
     */
    private static ExtentXReporter setXReporter() {
    	ExtentXReporter xReporter = new ExtentXReporter(properties.getValueString("setting.extentX.host.address"), properties.getValueInteger("setting.extentX.mongo.port"));
		xReporter.config().setProjectName("Automation Report");
		xReporter.config().setReportName(buildName);
    	xReporter.config().setServerUrl("http://" + properties.getValueString("setting.extentX.host.address") + ":" + properties.getValueInteger("setting.extentX.host.port"));
		xReporter.setAppendExisting(true);
		return xReporter;
    }
}
