package com.user.mgmt.dao;

import java.util.List;

import com.user.mgmt.domain.Users;

public interface UserDao {

	List<Users> getUsers();

	Users getUser(Long id);

	void addOrUpdateUser(Users user);

	void deleteUser(Long id);
}
