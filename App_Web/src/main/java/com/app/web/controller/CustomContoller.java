package com.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.custom.service.CustomService;
import com.app.util.constant.CommonConstants;
import com.app.util.custom.annotation.AllowedRoles;
import com.app.util.response.ResponseJson;

@RestController
@RequestMapping(CommonConstants.CUSTOM)
public class CustomContoller {

	@Autowired
	private ResponseJson response;
	@Autowired
	private CustomService customService;

	@AllowedRoles(values = { CommonConstants.ROLE_ADMIN })
	@RequestMapping(value = CommonConstants.CUSTOMTEST, method = RequestMethod.GET)
	public ResponseJson getInfo(@RequestAttribute("userId") Integer Id) {
		System.out.println("Id " + Id );
		response.setResponse(customService.getCustomUser());
		return response;
	}
}
