package com.taskcity.data;

import java.util.List;

import com.taskcity.data.dto.UserDTO;

public interface UserDataSource {
	public List<UserDTO> getUsers();

	public int numUsers();

	public UserDTO getUser(String username);

	public void createNewUser(String username);

	public void createNewUser(String username, String subjects, String password);

	public void updatePassword(UserDTO user);

	public void deleteUser(String username);
}
