package com.taskcity.data;

public class DataFactory {
	private static DataFactory instance;

	private DataFactory() {
	}

	public static DataFactory getInstance() {
		if (instance == null) {
			instance = new DataFactory();
		}

		return instance;
	}

	public UserDataSource createUserDataSource() {
		return new MySQLUserDS();
	}

	public TaskDataSource createTaskDataSource(String username) {
		return new MySQLTaskDS(username);
	}
}
