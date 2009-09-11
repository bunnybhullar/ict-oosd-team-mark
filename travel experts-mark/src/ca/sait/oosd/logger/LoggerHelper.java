/**
 * Author : Duminda Gunasekara
 * Date : August 19, 2009
 * Purpose : This is the interface for the Log4j logger
 */

package ca.sait.oosd.logger;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {	
	
	private static Log log;
	
	public LoggerHelper(String clazz) {
		PropertyConfigurator.configure("log4j.properties");
		
		log = LogFactory.getLog(clazz);
	}
	
	
	public void log(LogLevel logLevel, String message) {
		switch(logLevel) {
			case DEBUG :
				log.debug(new Date() + " :: " + message);
				break;
			case INFO :
				log.info(new Date() + " :: " + message);
				break;
			case WARN :
				log.warn(new Date() + " :: " + message);
				break;
			case ERROR :
				log.error(new Date() + " :: " + message);
				break;
			case FATAL :
				log.fatal(new Date() + " :: " + message);
				break;				
			default :
				log.info(new Date() + " :: " + message);
		}
	}
	
	

}
