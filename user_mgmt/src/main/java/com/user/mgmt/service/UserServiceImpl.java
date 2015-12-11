package com.user.mgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.mgmt.dao.UserDao;
import com.user.mgmt.domain.Users;
import com.user.mgmt.form.UserForm;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<Users> getAllUsers() {
		return userDao.getUsers();
	}

	@Override
	public UserForm loadUser(Long userId) {
		UserForm userForm = new UserForm();
		Users user = userDao.getUser(userId);
		userForm.setUserId(user.getId());
		userForm.setFirstName(user.getFirstName());
		userForm.setLastName(user.getLastName());
		userForm.setSex((user.getSex()));
		userForm.setPhone(user.getPhone().toString());
		userForm.setAddress(user.getAddress());
		userForm.setEmail(user.getEmail());
		return userForm;
	}

	@Override
	@Transactional
	public void createOrUpdateUser(UserForm userForm) {
		Users user = new Users();
		if (userForm.getUserId() != null && userForm.getUserId() > 0) {
			user.setId(userForm.getUserId());
		}
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setSex(userForm.getSex());
		user.setEmail(userForm.getEmail());
		user.setPhone(Long.valueOf(userForm.getPhone()));
		user.setAddress(userForm.getAddress());
		userDao.addOrUpdateUser(user);
	}

	@Override
	@Transactional
	public void removeUser(Long userId) {
		userDao.deleteUser(userId);
	}

}