package id.alphait.automation.slumber.reporting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import id.alphait.automation.slumber.utils.PropertyFactory;

/**
 * This class describes console logging functionality
 * 
 * @author Andi Santoso
 *
 */
public class LogConsole {
	private final Logger LOGGER = LogManager.getLogger(LogConsole.class.getName());
	private Path logDirPath;
	PropertyFactory properties = new PropertyFactory();
	final Level ASSERT_FAIL = Level.forName("ASSERT_FAIL", 150);
	final Level ASSERT_PASS = Level.forName("ASSERT_PASS", 350);
	final Level STEP = Level.forName("STEP", 450);
	
	/**
	 * Constructor.
	 * 
	 */
	public LogConsole() {
		LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
		InputStream in = getClass().getResourceAsStream(properties.getValueString("setting.log4j2.config"));
		try {
			File tempFile = File.createTempFile("log4j2", ".xml");
		    tempFile.deleteOnExit();
		    FileOutputStream out = new FileOutputStream(tempFile);
		    IOUtils.copy(in, out);
		    context.setConfigLocation(tempFile.toURI());
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * Logs info message.
	 * 
	 * @param message message to be logged.
	 */
	public void logInfo(String message) {
		LOGGER.info(message);
	}
	
	/**
	 * Logs debug message.
	 * 
	 * @param message message to be logged.
	 */
	public void logDebug(String message) {
		LOGGER.debug(message);
	}
	
	/**
	 * Logs error message.
	 * 
	 * @param message message to be logged.
	 */
	public void logError(String message) {
		LOGGER.error(message);
	}
	
	/**
	 * Logs warning message.
	 * 
	 * @param message message to be logged.
	 */
	public void logWarning(String message) {
		LOGGER.warn(message);
	}
	
	/**
	 * Custom log level for Cucumber Step.
	 * 
	 * @param message message to be logged.
	 */
	public void logStep(String message) {
		LOGGER.log(STEP, message);
	}
	
	/**
	 * Custom log level for Pass Assertion.
	 * 
	 * @param message message to be logged.
	 */
	public void logAssertPass(String message) {
		LOGGER.log(ASSERT_PASS, message);
	}
	
	/**
	 * Custom log level for Fail Assertion.
	 * 
	 * @param message message to be logged.
	 */
	public void logAssertFail(String message) {
		LOGGER.log(ASSERT_FAIL, message);
	}
	
	/**
	 * Setter method for log directory path.
	 * 
	 * @param logDirPath new log directory path.
	 */
	public void setLogDirPath(Path logDirPath) {
		this.logDirPath = logDirPath;
	}
	
	/**
	 * Getter method for log directory path.
	 * 
	 * @return current log directory path.
	 */
	public Path getLogDirPath() {
		return logDirPath;
	}
}