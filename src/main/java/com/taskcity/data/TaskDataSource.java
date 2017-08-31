package com.taskcity.data;

import java.util.List;

import com.taskcity.TaskDTO;

public interface DataSource {
	public List<TaskDTO> getTasks();

	public void addTask(TaskDTO task);

	public void deleteTask(String id);
}
