package com.user.mgmt.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.user.mgmt.form.UserForm;

@Component
public class UserFormValidator implements Validator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	String MOBILE_PATTERN = "[0-9]{10}";

	public boolean supports(Class<?> clazz) {
		return UserForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {

		UserForm user = (UserForm) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "required.phone");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "required.address");

		if (!(user.getEmail() != null && user.getEmail().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(user.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "email.pattern.incorrect");
			}
		}

		if (!(user.getPhone() != null && user.getPhone().toString().isEmpty())) {
			pattern = Pattern.compile(MOBILE_PATTERN);
			matcher = pattern.matcher(user.getPhone().toString());
			if (!matcher.matches()) {
				errors.rejectValue("phone", "phone.pattern.incorrect");
			}
		}
	}
}
