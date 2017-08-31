package com.taskcity.data.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.taskcity.data.DataFactory;

public class UserDTO {
	private String username;
	private String bin;

	public UserDTO(String username, String bin) {
		this.username = username;
		this.bin = bin;
	}

	public static UserDTO of(JSONObject jo) {
		return new UserDTO(jo.getString("name"), jo.getString("bin"));
	}

	public String getUsername() {
		return username;
	}

	public String getBin() {
		return bin;
	}

	public List<TaskDTO> loadSortedTasks() {
		return DataFactory.getInstance()
				.createTaskDataSource(bin)
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
