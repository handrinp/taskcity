package com.taskcity.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.taskcity.TaskDTO;

public class MyJsonDSTest {
	@Test
	public void testTasksFunctionality() {
		DataSource ds = new MyJsonDS("1kbad");
		List<TaskDTO> tasks = ds.getTasks();

		// horrible, quick and dirty test/hack that only works if I'm busy
		int numTasksOriginally = tasks.size();
		assertTrue(numTasksOriginally > 0);

		// add a task
		TaskDTO newTask = new TaskDTO("#TESTID", "Awesome", "test test test", 0);
		ds.addTask(newTask);
		assertEquals(numTasksOriginally + 1, ds.getTasks()
				.size());

		// delete a task
		ds.deleteTask("#TESTID");
		assertEquals(numTasksOriginally, ds.getTasks()
				.size());
	}
}
