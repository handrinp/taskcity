package com.taskcity.data;

import org.junit.Assert;
import org.junit.Test;

import com.taskcity.data.dto.TaskDTO;

public class DataSourceTest {
	@Test
	public void testTaskDS() {
		TaskDataSource ds = DataFactory.getInstance()
				.createTaskDataSource("3cpmp");
		int numTasksOriginally = ds.getTasks()
				.size();
		final String testID = "#TESTID";

		// add a task
		TaskDTO newTask = new TaskDTO(testID, "Awesome", "test test test", 0);
		ds.addTask(newTask);
		Assert.assertEquals(numTasksOriginally + 1, ds.numTasks());

		// delete a task
		ds.deleteTask(testID);
		Assert.assertEquals(numTasksOriginally, ds.numTasks());
	}

	@Test
	public void testUserDS() {
		UserDataSource ds = DataFactory.getInstance()
				.createUserDataSource();
		int numUsersOriginally = ds.getUsers()
				.size();
		final String testUsername = "TEST_USERNAME";

		// add a user
		ds.createNewUser(testUsername);
		Assert.assertEquals(numUsersOriginally + 1, ds.numUsers());

		// delete a user
		ds.deleteUser(testUsername);
		Assert.assertEquals(numUsersOriginally, ds.numUsers());
	}
}
