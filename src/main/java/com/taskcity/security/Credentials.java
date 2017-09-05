package com.taskcity.security;

import java.io.IOException;
import java.util.Properties;

import com.taskcity.Logger;

public class Credentials {
	private static Credentials instance;

	private Properties properties;

	private Credentials() {
		properties = new Properties();

		try {
			properties.load(Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream("taskcity.properties"));
		} catch (IOException e) {
			Logger.log("Failed loading taskcity.properties", e);
		}
	}

	public static Credentials getInstance() {
		if (instance == null) {
			instance = new Credentials();
		}

		return instance;
	}

	public String getDBUsername() {
		return properties.getProperty("database.username");
	}

	public String getDBPassword() {
		return properties.getProperty("database.password");
	}

	public String getDBConnectionURL() {
		return properties.getProperty("database.connectionURL");
	}
}