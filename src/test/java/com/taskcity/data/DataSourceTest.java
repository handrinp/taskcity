package com.taskcity.data;

import org.junit.Assert;
import org.junit.Test;

import com.taskcity.data.dto.TaskDTO;

public class DataSourceTest {
	private static final String TEST_ID = "#TESTID";
	private static final String TEST_USERNAME = "TEST_USERNAME";

	@Test
	public void testTaskDS() {
		TaskDataSource ds = DataFactory.getInstance()
				.createTaskDataSource(TEST_USERNAME);
		int numTasksOriginally = ds.numTasks();

		// add a task
		TaskDTO newTask = new TaskDTO(TEST_ID, "Awesome", "test test test", 0);
		ds.addTask(newTask);
		Assert.assertEquals(numTasksOriginally + 1, ds.numTasks());

		// delete a task
		ds.deleteTask(TEST_ID);
		Assert.assertEquals(numTasksOriginally, ds.numTasks());
	}

	@Test
	public void testUserDS() {
		UserDataSource ds = DataFactory.getInstance()
				.createUserDataSource();
		int numUsersOriginally = ds.numUsers();

		// add a user
		ds.createNewUser(TEST_USERNAME);
		Assert.assertEquals(numUsersOriginally + 1, ds.numUsers());

		// delete a user
		ds.deleteUser(TEST_USERNAME);
		Assert.assertEquals(numUsersOriginally, ds.numUsers());
	}
}
