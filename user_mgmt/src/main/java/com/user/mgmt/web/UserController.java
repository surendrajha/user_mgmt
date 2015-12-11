package com.user.mgmt.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.user.mgmt.domain.Users;
import com.user.mgmt.form.UserForm;
import com.user.mgmt.service.UserService;
import com.user.mgmt.utils.UserFormAction;
import com.user.mgmt.validator.UserFormValidator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserFormValidator formValidator;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String showUserPage() {
		return "users";
	}

	@RequestMapping(value = "/users/add", method = RequestMethod.GET)
	public ModelAndView getAddUserForm() {
		ModelAndView mav = new ModelAndView("users");
		UserForm userForm = new UserForm();
		userForm.setAction(UserFormAction.ADD);
		userForm.setLabel("Add User");
		mav.addObject("userForm", userForm);
		return mav;
	}

	@RequestMapping(value = "/users/{userid}", method = RequestMethod.GET)
	public ModelAndView editUserPage(@PathVariable Long userid) {
		ModelAndView mav = new ModelAndView("users");
		try {
			UserForm userForm = userService.loadUser(userid);
			userForm.setAction(UserFormAction.UPDATE);
			userForm.setLabel("Update User");
			mav.addObject("userForm", userForm);
		} catch (Exception e) {
			System.out.println("Exceprion in editUserPage: ");
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/users/add-update", method = RequestMethod.POST)
	public ModelAndView addUpdateUser(@ModelAttribute UserForm userForm, HttpServletRequest req, BindingResult result) {
		ModelAndView mav = new ModelAndView("users");
		formValidator.validate(userForm, result);
		try {
			if (result.hasErrors()) {
				mav.addObject("userForm", userForm);
			} else {
				userService.createOrUpdateUser(userForm);
				mav.setView(new RedirectView(req.getContextPath() + "/users.html"));
			}
		} catch (Exception e) {
			System.out.println("Exceprion in addUpdateUser: ");
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping("/users/delete/{userid}")
	public ModelAndView removeUser(@PathVariable Long userid, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		try {
			userService.removeUser(userid);
			mav.setView(new RedirectView(req.getContextPath() + "/users.html"));
		} catch (Exception e) {
			System.out.println("Exceprion in removeUser: ");
			e.printStackTrace();
		}
		return mav;
	}

	@ModelAttribute("users")
	public List<Users> getUsers() {
		return userService.getAllUsers();
	}

	@ModelAttribute("userForm")
	public UserForm getUserForm() {
		return new UserForm();
	}

}