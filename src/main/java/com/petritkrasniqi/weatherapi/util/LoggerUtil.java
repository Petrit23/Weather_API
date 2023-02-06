package com.petritkrasniqi.weatherapi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtil.class);
	
	public static void logInfo(String msg) {
		LOGGER.info(msg);
	}
	
	public static void logError(String msg) {
		LOGGER.error(msg);
	}

}
