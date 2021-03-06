package com.app.web.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.auth.service.AuthenticationService;
import com.app.util.CommonConstants;
import com.app.util.dto.LoginReq;
import com.app.util.dto.ResponseJson;
import com.app.util.dto.SearchDTO;
import com.app.util.dto.UserDataForm;
import com.app.util.error.ErrorCodeHelper;

@RestController
@RequestMapping(CommonConstants.USER_URL)
public class LoginController {

	private static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private ResponseJson response;

	@Autowired
	private RestTemplate restTempalte;

	/**
	 * For Token Creation
	 * 
	 * @param loginDetails
	 * @return
	 */

	@RequestMapping(value = CommonConstants.TOKEN_CREATION, method = RequestMethod.POST)
	public ResponseJson tokenCreation(@Valid @RequestBody LoginReq loginReq) {
		response.setResponse(authenticationService.authenticateUser(loginReq));
		return response;
	}

	@RequestMapping(value = CommonConstants.USER_DETAILS, method = RequestMethod.GET)
	public ResponseJson getInfo(@PathVariable Integer id, SearchDTO loginReq) {
		response.setResponse(id + "Class Info" + loginReq);
		return response;
	}

	/* you can also remove @ModelAttribute that works same */
	@RequestMapping(value = "userInfoTest", method = RequestMethod.POST)
	public ResponseJson getInfo(@ModelAttribute UserDataForm user) {
		response.setResponse("Class Info" + user);
		return response;
	}

	@RequestMapping(value = "abc", method = RequestMethod.GET)
	public ResponseEntity<String> getInfo() {
		LoginReq loginReq = new LoginReq();
		loginReq.setUserEmail("faiz.krm@gmail.com");
		loginReq.setUserCredential("12345678");
		ResponseEntity<String> response = restTempalte
				.postForEntity("http://localhost:8080/Spring_Web_App/web/token/v3/creation", loginReq, String.class);

		return response;
	}

}
