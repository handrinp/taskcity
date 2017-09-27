package com.taskcity.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.taskcity.Logger;

public class Credentials {
	private static Credentials instance;
	private static final String PROPERTIES_LOCATION = "/var/lib/taskcity/taskcity.properties";

	private Properties properties;

	private Credentials() {
		properties = new Properties();

		try {
			properties.load(new FileInputStream(PROPERTIES_LOCATION));
		} catch (IOException e) {
			Logger.log("Failed loading properties " + PROPERTIES_LOCATION, e);
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

	public String getEmailTo() {
		return properties.getProperty("admin.email.to");
	}

	public String getEmailFrom() {
		return properties.getProperty("admin.email.from");
	}

	public String getEmailPassword() {
		return properties.getProperty("admin.email.password");
	}

	public String getEmailProperties() {
		return properties.getProperty("admin.email.properties");
	}
}
