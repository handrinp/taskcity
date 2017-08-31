package com.taskcity.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.taskcity.data.dto.TaskDTO;

public class MyJSONTaskDS implements TaskDataSource {
	private String url;

	public MyJSONTaskDS(String myJsonID) {
		url = "https://api.myjson.com/bins/" + myJsonID;
	}

	@Override
	public List<TaskDTO> getTasks() {
		List<TaskDTO> tasks = new ArrayList<>();
		JSONUtil.getJSON(url)
				.getJSONArray("taskList")
				.forEach(o -> tasks.add(TaskDTO.of((JSONObject) o)));
		return tasks;
	}

	@Override
	public int numTasks() {
		return JSONUtil.getJSON(url)
				.getJSONArray("taskList")
				.length();
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
		List<JSONObject> newTaskList = new ArrayList<>();

		jo.getJSONArray("taskList")
				.forEach(o -> {
					JSONObject curTask = (JSONObject) o;

					if (!curTask.getString("taskid")
							.equals(idToDelete)) {
						newTaskList.add(curTask);
					}
				});

		jo.put("taskList", newTaskList);
		JSONUtil.putJSON(url, jo);
	}
}
