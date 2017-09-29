# slumber

**S**e**l**enium Cuc**umber** is a Java application which executes browser automation scenarios written in Gherkin features.

This project attempts to provide a simple and extensible framework for Cucumber-JVM automation.

## Pre-requisites
1. A directory where slumber's executable jar will be placed.
2. In the same directory, create a new directory called `features` which contains all .feature files
3. In the same directory, create a new directory called `drivers` which contains all web driver executables. Currently Chrome, Firefox, IE and PhantomJS are supported.


## How slumber works
1. Clone this repository.
2. Package slumber into an executable jar: ```mvn clean assembly:assembly```
3. Run the executable jar (Chrome as example): ```java -DuseRemoteWebDriver=false -Dbrowser=Chrome -DdriverLocation=drivers\chromedriver.exe -cp slumber.jar com.thetestguys.slumber.utils.RunSlumber```
4. To run the executable jar in a Selenium Grid (Chrome/LINUX as example): ```java -DuseRemoteWebDriver=true -DgridURL=http://localhost:4444/wd/hub -DdesiredPlatform=LINUX -Dbrowser=Chrome -DdriverLocation= -cp slumber.jar com.thetestguys.slumber.utils.RunSlumber```


## Run results
slumber produces three types of output:
* ExtentReport is the main reporting framework, the report can be found under directory `output\report.html`
* Execution run is logged in the console or under directory `logs\logs.html`
* Execution run can also be seen as JSON output under `output\runOutput.json`
