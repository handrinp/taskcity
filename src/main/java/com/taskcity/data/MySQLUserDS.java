package com.taskcity.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.taskcity.Logger;
import com.taskcity.data.dto.UserDTO;

public class MySQLUserDS implements UserDataSource {
	@Override
	public List<UserDTO> getUsers() {
		List<UserDTO> users = new ArrayList<>();

		try (Connection con = MySQLUtils.getConnection()) {
			String sql = "select * from users";
			ResultSet rs = con.createStatement()
					.executeQuery(sql);

			while (rs.next()) {
				users.add(UserDTO.of(rs));
			}
		} catch (SQLException e) {
			Logger.log("getUsers failed", e);
		}

		return users;
	}

	@Override
	public int numUsers() {
		return MySQLUtils.countResultsForQuery("select * from users");
	}

	@Override
	public UserDTO getUser(String username) {
		UserDTO user = null;

		try (Connection con = MySQLUtils.getConnection()) {
			String sql = "select * from users where username='" + username + "'";
			ResultSet rs = con.createStatement()
					.executeQuery(sql);

			if (rs.next()) {
				user = UserDTO.of(rs);
			}
		} catch (SQLException e) {
			Logger.log("getUser failed", e);
		}

		return user;
	}

	@Override
	public void createNewUser(String username) {
		String sql = "insert into users(username) values (?)";

		try (Connection con = MySQLUtils.getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.execute();
		} catch (SQLException e) {
			Logger.log("createNewUser failed", e);
		}
	}

	@Override
	public void createNewUser(String username, String subjects) {
		String sql = "insert into users(username, subjects) values (?, ?)";

		try (Connection con = MySQLUtils.getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, subjects);
			ps.execute();
		} catch (SQLException e) {
			Logger.log("createNewUser failed", e);
		}
	}

	public void createNewUser(String username, String hash, String salt, List<String> subjects) {
		try (Connection con = MySQLUtils.getConnection()) {
			String sql = "insert into users(username, hash, salt, subjects) values (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, hash);
			ps.setString(3, salt);
			ps.setString(4, subjects.stream()
					.collect(Collectors.joining(";")));
			ps.execute();
		} catch (SQLException e) {
			Logger.log("createNewUser failed", e);
		}
	}

	@Override
	public void deleteUser(String username) {
		try (Connection con = MySQLUtils.getConnection()) {
			String sql = "delete from users where username = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.execute();
		} catch (SQLException e) {
			Logger.log("deleteUser failed", e);
		}
	}
}
