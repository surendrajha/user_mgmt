package com.user.mgmt.service;

import java.util.List;

import com.user.mgmt.domain.Users;
import com.user.mgmt.form.UserForm;

public interface UserService {

	List<Users> getAllUsers();

	UserForm loadUser(Long userId);

	void createOrUpdateUser(UserForm userForm);

	void removeUser(Long userId);

}