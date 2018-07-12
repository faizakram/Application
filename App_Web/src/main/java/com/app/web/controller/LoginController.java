package com.app.web.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.auth.service.AuthenticationService;
import com.app.util.constant.CommonConstants;
import com.app.util.error.ErrorCodeHelper;
import com.app.util.request.LoginReq;
import com.app.util.request.SearchDTO;
import com.app.util.response.ResponseJson;

@RestController
@RequestMapping(CommonConstants.USER_URL)
public class LoginController {

	private static Logger logger = Logger.getLogger(LoginController.class);
	
	
	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	@Qualifier(CommonConstants.ERROR_CODE_HELPER)
	private ErrorCodeHelper errorCodeHelper;
	
	@Autowired
	private ResponseJson response;
	
	/**
	 * For Token Creation 
	 * @param loginDetails
	 * @return
	 */
	/*@ApiImplicitParams({
    @ApiImplicitParam(name = "name", value = "User's name", required = true, dataType = "string", paramType = "query"),
    @ApiImplicitParam(name = "email", value = "User's email", required = false, dataType = "string", paramType = "query"),
    @ApiImplicitParam(name = "id", value = "User ID", required = true, dataType = "long", paramType = "query")
  })*/
//@PathParam(value = "User's name")
	@RequestMapping(value = CommonConstants.TOKEN_CREATION , method = RequestMethod.POST)
	public ResponseJson tokenCreation(@Valid @RequestBody LoginReq loginReq) {
		response.setResponse(authenticationService.authenticateUser(loginReq));
		return response;
	}
	
	@RequestMapping(value = CommonConstants.USER_DETAILS , method = RequestMethod.GET)
	public ResponseJson getInfo(@PathVariable Integer id, SearchDTO loginReq) {
		
		
		response.setResponse(id + "Class Info" + loginReq);
		return response;
	}
	
	
	
}
