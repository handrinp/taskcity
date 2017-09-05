package com.taskcity.data.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.taskcity.Logger;
import com.taskcity.data.DataFactory;

public class UserDTO {
	private String username;
	private String bin;
	private String salt;
	private String hash;
	private List<String> subjects;

	public UserDTO(String username, String bin) {
		this.username = username;
		this.bin = bin;
		salt = null;
		hash = null;
		subjects = Arrays.asList("Misc", "School", "Social", "Work");
	}

	public UserDTO(String username, String salt, String hash, List<String> subjects) {
		this.username = username;
		this.salt = salt;
		this.hash = hash;
		this.subjects = new ArrayList<>();
		this.subjects.addAll(subjects);
	}

	public static UserDTO of(JSONObject jo) {
		return new UserDTO(jo.getString("name"), jo.getString("bin"));
	}

	public static UserDTO of(ResultSet rs) {
		UserDTO user = null;

		try {
			String username = rs.getString("username");
			String salt = rs.getString("salt");
			String hash = rs.getString("hash");
			List<String> subjects = Arrays.asList(rs.getString("subjects")
					.split(";"));
			user = new UserDTO(username, salt, hash, subjects);
		} catch (SQLException e) {
			Logger.log("Couldn't create UserDTO from ResultSet", e);
		}

		return user;
	}

	public String getUsername() {
		return username;
	}

	public String getBin() {
		return bin;
	}

	public String getSalt() {
		return salt;
	}

	public String getHash() {
		return hash;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public List<TaskDTO> loadSortedTasks() {
		return DataFactory.getInstance()
				.createTaskDataSource(username)
				.getTasks()
				.stream()
				.sorted(TaskDTO.COMPARATOR)
				.collect(Collectors.toList());
	}

	private static final String JSON_FORMAT = "{\"name\":\"%s\",\"bin\":\"%s\"}";

	public JSONObject asJSON() {
		String json = String.format(JSON_FORMAT, username, bin);
		return new JSONObject(json);
	}

	@Override
	public String toString() {
		return String.format("User %s : [bin: %s]", username, bin);
	}
}
