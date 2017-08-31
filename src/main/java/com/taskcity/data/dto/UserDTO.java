package com.taskcity.data.dto;

import org.json.JSONObject;

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
