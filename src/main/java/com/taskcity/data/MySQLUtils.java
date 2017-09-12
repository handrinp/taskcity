package com.taskcity.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.taskcity.Logger;
import com.taskcity.security.Credentials;

public class MySQLUtils {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Logger.log("couldn't load driver", e);
		}
	}

	public static Connection getConnection() throws SQLException {
		Credentials creds = Credentials.getInstance();
		return DriverManager.getConnection(creds.getDBConnectionURL(), creds.getDBUsername(), creds.getDBPassword());
	}
}
