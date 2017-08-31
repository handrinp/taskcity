package com.taskcity.data;

import java.util.HashMap;
import java.util.Map;

public class DataFactory {
	private static DataFactory instance;

	private Map<String, TaskDataSource> taskDSCache;

	private DataFactory() {
		taskDSCache = new HashMap<>();
	}

	public static DataFactory getInstance() {
		if (instance == null) {
			instance = new DataFactory();
		}

		return instance;
	}

	public UserDataSource createUserDataSource() {
		return new MyJSONUserDS();
	}

	public TaskDataSource createTaskDataSource(String bin) {
		return retrieveTaskDSFromCache(bin);
	}

	private TaskDataSource retrieveTaskDSFromCache(String bin) {
		if (!taskDSCache.containsKey(bin)) {
			taskDSCache.put(bin, new MyJSONTaskDS(bin));
		}

		return taskDSCache.get(bin);
	}
}
