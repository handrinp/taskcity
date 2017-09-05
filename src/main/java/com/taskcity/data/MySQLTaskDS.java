package com.taskcity.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taskcity.Logger;
import com.taskcity.data.dto.TaskDTO;

public class MySQLTaskDS implements TaskDataSource {
	private String username;

	public MySQLTaskDS(String username) {
		this.username = username;
	}

	@Override
	public List<TaskDTO> getTasks() {
		List<TaskDTO> users = new ArrayList<>();

		try (Connection con = MySQLUtils.getConnection()) {
			String sql = "select * from tasks where username='" + username + "'";
			ResultSet rs = con.createStatement()
					.executeQuery(sql);

			while (rs.next()) {
				users.add(TaskDTO.of(rs));
			}
		} catch (SQLException e) {
			Logger.log("getTasks failed", e);
		}

		return users;
	}

	@Override
	public int numTasks() {
		return MySQLUtils.countResultsForQuery("select * from tasks where username='" + username + "'");
	}

	@Override
	public void addTask(TaskDTO task) {
		try (Connection con = MySQLUtils.getConnection()) {
			String sql = "insert into tasks(taskid, subject, description, due, username) values (?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, task.getID());
			ps.setString(2, task.getSubject());
			ps.setString(3, task.getDescription());
			ps.setLong(4, task.getDue());
			ps.setString(5, username);
			ps.execute();
		} catch (SQLException e) {
			Logger.log("addTask failed", e);
		}
	}

	@Override
	public void deleteTask(String id) {
		try (Connection con = MySQLUtils.getConnection()) {
			String sql = "delete from tasks where taskid = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			Logger.log("deleteUser failed", e);
		}
	}
}
