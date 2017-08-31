package com.taskcity.data;

import java.util.List;

import com.taskcity.data.dto.TaskDTO;

public interface TaskDataSource {
	public List<TaskDTO> getTasks();

	public int numTasks();

	public void addTask(TaskDTO task);

	public void deleteTask(String id);
}
