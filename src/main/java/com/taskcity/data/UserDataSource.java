package com.taskcity.data;

import java.util.List;

import com.taskcity.data.dto.UserDTO;

public interface UserDataSource {
	public List<UserDTO> getUsers();

	public int numUsers();

	public void createNewUser(String username);

	public void deleteUser(String username);
}
