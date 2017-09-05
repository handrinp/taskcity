package com.taskcity;

import javax.servlet.ServletContext;

public class Logger {
	private static ServletContext logger;

	public static void setLogger(ServletContext newLogger) {
		logger = newLogger;
	}

	public static void log(String msg) {
		System.err.println(msg);

		if (logger != null) {
			logger.log(msg);
		}
	}

	public static void log(String msg, Throwable t) {
		System.err.println(msg);
		t.printStackTrace();

		if (logger != null) {
			logger.log(msg, t);
		}
	}
}
