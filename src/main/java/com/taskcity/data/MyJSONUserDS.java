package com.taskcity.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.taskcity.data.dto.UserDTO;

public class MyJSONUserDS implements UserDataSource {
	private static final String USERS_BIN = "https://api.myjson.com/bins/lrkot";
	private static final String NEW_USER_URL = "https://api.myjson.com/bins";
	private static final String NEW_USER_JSON = "{\"taskList\":[]}";

	/**
	 * Returns the list of all users in the table.
	 */
	@Override
	public List<UserDTO> getUsers() {
		List<UserDTO> users = new ArrayList<>();
		JSONUtil.getJSON(USERS_BIN)
				.getJSONArray("users")
				.forEach(o -> users.add(UserDTO.of((JSONObject) o)));
		return users;
	}

	/**
	 * Returns the UserDTO object corresponding to the passed in username, or null
	 * if no such user exist.
	 */
	@Override
	public UserDTO getUser(String username) {
		return getUsers().stream()
				.filter(user -> username.equals(user.getUsername()))
				.findFirst()
				.orElse(null);
	}

	/**
	 * Returns the number of users
	 */
	@Override
	public int numUsers() {
		return JSONUtil.getJSON(USERS_BIN)
				.getJSONArray("users")
				.length();
	}

	@Override
	public void createNewUser(String username) {
		// post an empty task object and generate a new bin
		String uri = JSONUtil.postJSON(NEW_USER_URL, new JSONObject(NEW_USER_JSON))
				.getString("uri");
		String bin = uri.substring(1 + uri.lastIndexOf('/'));

		// add the new bin to the users table
		JSONObject jo = JSONUtil.getJSON(USERS_BIN);
		jo.getJSONArray("users")
				.put(new UserDTO(username, bin).asJSON());
		JSONUtil.putJSON(USERS_BIN, jo);
	}

	@Override
	public void createNewUser(String username, String subjects) {
		// ignore subjects because this DS is outdated
		createNewUser(username);
	}

	@Override
	public void deleteUser(String userNameToDelete) {
		JSONObject jo = JSONUtil.getJSON(USERS_BIN);
		List<JSONObject> newUserList = new ArrayList<>();
		jo.getJSONArray("users")
				.forEach(o -> {
					JSONObject curUser = (JSONObject) o;

					if (!curUser.getString("name")
							.equals(userNameToDelete)) {
						newUserList.add(curUser);
					}
				});
		jo.put("users", newUserList);
		JSONUtil.putJSON(USERS_BIN, jo);
	}
}
