package com.taskcity.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.taskcity.TaskDTO;

public class MyJsonDS implements DataSource {
	private String url;

	public MyJsonDS(String myJsonID) {
		url = "https://api.myjson.com/bins/" + myJsonID;
	}

	@Override
	public List<TaskDTO> getTasks() {
		List<TaskDTO> tasks = new ArrayList<>();
		JSONArray taskList = JSONUtil.getJSON(url)
				.getJSONArray("taskList");
		int numTasks = taskList.length();

		for (int i = 0; i < numTasks; ++i) {
			Object o = taskList.get(i);

			if (o instanceof JSONObject) {
				tasks.add(TaskDTO.of((JSONObject) o));
			}
		}

		return tasks;
	}

	@Override
	public void addTask(TaskDTO task) {
		JSONObject jo = JSONUtil.getJSON(url);
		jo.getJSONArray("taskList")
				.put(task.asJSON());
		JSONUtil.putJSON(url, jo);
	}

	@Override
	public void deleteTask(String idToDelete) {
		JSONObject jo = JSONUtil.getJSON(url);
		JSONArray taskList = jo.getJSONArray("taskList");
		JSONArray newTaskList = new JSONArray();
		int numTasks = taskList.length();

		for (int i = 0; i < numTasks; ++i) {
			Object o = taskList.get(i);

			if (o instanceof JSONObject) {
				JSONObject curTask = (JSONObject) o;
				if (!TaskDTO.of(curTask)
						.getID()
						.equals(idToDelete)) {
					newTaskList.put(curTask);
				}
			}
		}

		jo.put("taskList", newTaskList);
		JSONUtil.putJSON(url, jo);
	}
}
