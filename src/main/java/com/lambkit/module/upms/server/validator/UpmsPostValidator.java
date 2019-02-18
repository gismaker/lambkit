package com.lambkit.module.upms.server.validator;

import com.jfinal.core.Controller;
import com.lambkit.web.validator.BaseValidator;

public abstract class UpmsPostValidator extends BaseValidator {

	@Override
	protected boolean isValidate(Controller c) {
		if (c.getRequest().getMethod().equals("POST")) {
			return true;
		} else {
			return false;
		}
	}
}
