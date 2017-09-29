/*
 * Copyright (c) 2017. The Test Guys
 */

package com.thetestguys.slumber.utils;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import java.util.ArrayList;
import java.util.List;

/**
 * This class executes the Cucumber CLI runner
 * 
 * @author Andi Santoso
 *
 */
public class RunSlumber {

	/**
	 * Main constructor
	 * 
	 * @param args args
	 */
    public static void main(String args[]) {
        RunSlumber runSlumber = new RunSlumber();
        runSlumber.runTestNG();
    }

    /**
     * Actual method which runs TestNG tests
     */
    public void runTestNG() {
        TestNG testNG = new TestNG();
        XmlSuite testNGSuite = new XmlSuite();
        testNGSuite.setName("Automation Suite");
        testNGSuite.setParallel(XmlSuite.ParallelMode.TRUE);
        testNGSuite.setThreadCount(5);
        XmlTest testNGTest = new XmlTest(testNGSuite);
        testNGTest.setName("Automation Test");
        testNGTest.setVerbose(1);
        List<XmlPackage> testNGPackages = new ArrayList<XmlPackage>();
        testNGPackages.add(new XmlPackage("com.thetestguys.slumber.runners.*"));
        testNGTest.setXmlPackages(testNGPackages);
        List<XmlTest> testNGTests = new ArrayList<XmlTest>();
        testNGTests.add(testNGTest);
        testNGSuite.setTests(testNGTests);
        List<XmlSuite> testNGSuites = new ArrayList<XmlSuite>();
        testNGSuites.add(testNGSuite);
        testNG.setXmlSuites(testNGSuites);
        //System.out.println(testNGSuite.toXml());
        testNG.run();
    }
}