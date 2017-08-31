package com.taskcity.data.dto;

import java.util.HashMap;
import java.util.Map;

import com.taskcity.data.MyJSONTaskDS;
import com.taskcity.data.MyJSONUserDS;
import com.taskcity.data.TaskDataSource;
import com.taskcity.data.UserDataSource;

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
