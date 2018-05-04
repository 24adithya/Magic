package com.pack.java.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Test {

	private final static Logger LOG = LogManager.getLogger(Log4j2Test.class);
	
	public static void main(String[] args) throws Exception {
		LOG.debug("Inside main");
		
		Log4j2Test log4j2Test = new Log4j2Test();
		log4j2Test.testLogging();
	}
	
	private void testLogging() throws Exception {
		LOG.debug("Inside testLogging");
		LOG.info("Logging 1..");
		LOG.info("Logging 2..");
		try {
			throw new Exception("logging exception !");
		} catch(Exception ex) {
			LOG.catching(ex);
			throw new Exception("logging exception 2 !");
		}
		finally {
			LOG.debug("testLogging completed finally..");
		}
	}
}
