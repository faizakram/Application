package com.app.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.auth.service.AuthenticationService;
import com.app.util.constant.CommonConstants;
import com.app.util.error.ErrorCodeHelper;
import com.app.util.request.LoginReq;
import com.app.util.response.ResponseJson;

@RestController
@RequestMapping(CommonConstants.USER_URL)
public class LoginController {

	//private static Logger logger = Logger.getLogger(LoginController.class);
	
	
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
	@RequestMapping(value = CommonConstants.TOKEN_CREATION , method = RequestMethod.POST)
	public ResponseJson tokenCreation(@Valid @RequestBody LoginReq loginReq) {
		response.setResponse(authenticationService.authenticateUser(loginReq));
		return response;
	}
	
	
	/*
	 * @Autowired private LoginServiceInterFace loginServiceInterFace;
	 * 
	 * @Autowired private Captcha captcha;
	 * 
	 * @Autowired AuthenticationClass auth;
	 * 
	 * @Autowired private ModelAndView model;
	 * 
	 * Login and After Logout Page
	 * 
	 * @RequestMapping(value = "/AdminLogin", method = RequestMethod.GET) public
	 * ModelAndView login(@RequestParam(value = "error", required = false) String
	 * error,
	 * 
	 * @RequestParam(value = "logout", required = false) String logout) {
	 * 
	 * if (!auth.getAuthenticationName().getName().equals("anonymousUser") &&
	 * !auth.getAuthenticationType().toString().equals("[ROLE_ANONYMOUS]")) {
	 * model.setViewName("forward:AdminInside");
	 * logger.info(LoginController.class.getMethods()); return model; } if (error !=
	 * null) { model.addObject("error", "Invalid username and password!"); } if
	 * (logout != null) {
	 * 
	 * model.addObject("msg", "You've been logged out successfully."); }
	 * model.setViewName("Login/index");
	 * logger.info(LoginController.class.getMethods()); return model; }
	 * 
	 * 
	 * 
	 * Testing
	 * 
	 * @RequestMapping(value = "/requestForNewCompanyCaptcha", method =
	 * RequestMethod.POST) public @ResponseBody String check(HttpServletRequest
	 * request) { if (!captcha.CheckCaptcha(request)) {
	 * logger.info(LoginController.class.getMethods()); return "false"; } String
	 * companyfullName = request.getParameter("fullName"); String userEmail =
	 * request.getParameter("userEmail"); String url = request.getParameter("url");
	 * System.out.println("companyfullName" + companyfullName);
	 * System.out.println("userEmail" + userEmail); System.out.println("url" + url);
	 * logger.info(LoginController.class.getMethods()); return "true"; }
	 * 
	 * Forgot Password Page
	 * 
	 * @RequestMapping(value = "/ForgotPassword", method = RequestMethod.GET) public
	 * ModelAndView forgotPassword() { model.setViewName("Login/ForgotPassword");
	 * logger.info(LoginController.class.getMethods()); return model; }
	 * 
	 * Registration Page
	 * 
	 * @RequestMapping(value = "/registrationPage", method = RequestMethod.GET)
	 * public ModelAndView registrationPage() {
	 * model.setViewName("Login/RegistrationPage");
	 * logger.info(LoginController.class.getMethods()); return model; }
	 * 
	 * @RequestMapping(value = "/requestForNewCompany", method = RequestMethod.GET)
	 * public ModelAndView requestForNewCompany() {
	 * model.setViewName("Login/requestForNewCompany");
	 * logger.info(LoginController.class.getMethods()); return model; }
	 * 
	 * Lock Screen Page
	 * 
	 * @RequestMapping(value = "/lockScreen", method = RequestMethod.GET) public
	 * ModelAndView lockScreen(HttpServletRequest request) {
	 * model.setViewName("Login/LockScreen");
	 * logger.info(LoginController.class.getMethods()); return model; }
	 * 
	 * Register New Account Request
	 * 
	 * @RequestMapping(value = "/registerNewAccount", method = RequestMethod.POST)
	 * public @ResponseBody boolean registerNewAccount(
	 * 
	 * @RequestParam(value = "fullName", required = true) String fullName,
	 * 
	 * @RequestParam(value = "userEmail", required = true) String userEmail,
	 * 
	 * @RequestParam(value = "password", required = true) String password) {
	 * 
	 * if (!captcha.CheckCaptcha(request)) { return "Try Again!"; } userEmail =
	 * userEmail.toLowerCase(); logger.info(LoginController.class.getMethods());
	 * return loginServiceInterFace.registerNewAccount(fullName, userEmail,
	 * password, password); }
	 * 
	 * Account Verify Request From Admin Side
	 * 
	 * @RequestMapping(value = "/accountVerifiedByAdmin", method =
	 * RequestMethod.GET) public ModelAndView accountVerifiedByAdmin(
	 * 
	 * @RequestParam(value = "accessKey", required = true) String accesskey,
	 * 
	 * @RequestParam(value = "userEmail", required = true) String userEmail,
	 * 
	 * @RequestParam(value = "action", required = true) String action,
	 * 
	 * @RequestParam(value = "fullName", required = true) String fullName,
	 * 
	 * @RequestParam(value = "password", required = true) String password,
	 * 
	 * @RequestParam(value = "userType", required = true) String userType) {
	 * userEmail = userEmail.toLowerCase();
	 * 
	 * if (loginServiceInterFace.findAccessKeyandEmailIdByAdminWithAccept(accesskey,
	 * fullName, userEmail, password, userType) && action.equals("Accept")) {
	 * model.addObject("content", "<h1>" + fullName +
	 * " Account Has Accepted</h1> "); model.addObject("link", "index");
	 * model.addObject("clickContent", "Click Here To Go Login");
	 * model.setViewName("Login/multiWrokingPage");
	 * logger.info(LoginController.class.getMethods()); return model; }
	 * 
	 * else if
	 * (loginServiceInterFace.findAccessKeyandEmailIdByAdminwithRejection(accesskey,
	 * userEmail) && action.equals("Reject")) { model.addObject("content", "<h1>" +
	 * fullName + " Account Has Rejected</h1><h3>This has been Rejected</h3> ");
	 * model.addObject("link", "index"); model.addObject("clickContent",
	 * "Click Here To Go Login"); model.setViewName("Login/multiWrokingPage");
	 * logger.info(LoginController.class.getMethods()); return model; } else {
	 * model.addObject("content",
	 * "<h1>Already Verified</h1><h3>You have verifed this Account.</h3> ");
	 * model.addObject("link", "index"); model.addObject("clickContent",
	 * "Click Here To Go Login"); model.setViewName("Login/multiWrokingPage");
	 * logger.info(LoginController.class.getMethods()); return model; } }
	 * 
	 * Account Verify Request From User Side
	 * 
	 * @RequestMapping(value = "/accountVerifiedByUser", method = RequestMethod.GET)
	 * public ModelAndView accountVerifiedByUser(@RequestParam(value = "email",
	 * required = true) String emailId,
	 * 
	 * @RequestParam(value = "accessKey", required = true) String accessKey) {
	 * 
	 * emailId = emailId.toLowerCase(); if
	 * (loginServiceInterFace.findAccessKeyandEmailIdByUserWithAccept(accessKey,
	 * emailId)) { model.addObject("content",
	 * "<h1>Account Verified From User Side</h1><h3>Wait form Admin Side Verification</h3> "
	 * ); model.addObject("link", "index"); model.addObject("clickContent",
	 * "Click Here To Go Login"); model.setViewName("Login/multiWrokingPage");
	 * logger.info(LoginController.class.getMethods()); return model; } else {
	 * model.addObject("content",
	 * "<h1>No User Found</h1><h3>Try Again to Create Account</h3> ");
	 * model.addObject("link", "registrationPage"); model.addObject("clickContent",
	 * "Click Here To Create Account"); model.setViewName("Login/multiWrokingPage");
	 * logger.info(LoginController.class.getMethods()); return model; }
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/CheckUser", method = RequestMethod.POST)
	 * public @ResponseBody LoginJsonObject checkUser(@RequestParam(value =
	 * "UserId") String userEmail) { userEmail = userEmail.toLowerCase();
	 * LoginJsonObject loginObject =
	 * loginServiceInterFace.findUserByEmailIdUserTable(userEmail);
	 * logger.info(LoginController.class.getMethods()); return loginObject; }
	 * 
	 * @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	 * public @ResponseBody boolean forgotPassword(@RequestParam(value =
	 * "UserEmail", required = true) String userEmail) { userEmail =
	 * userEmail.toLowerCase(); logger.info(LoginController.class.getMethods());
	 * return loginServiceInterFace.forgotPassword(userEmail); }
	 * 
	 * @RequestMapping(value = "/passwordRest", method = RequestMethod.GET) public
	 * ModelAndView passwordRest(@RequestParam(value = "email", required = true)
	 * String emailId,
	 * 
	 * @RequestParam(value = "accessKey", required = true) String accessKey) {
	 * 
	 * emailId = emailId.toLowerCase(); LoginJsonObject json =
	 * loginServiceInterFace.findPasswordAccessKeyandEmailIdByUserWithVerify(
	 * accessKey, emailId); if (json.getAccountStauts().equals("True")) {
	 * model.addObject("emailId", json.getAvailableEmailId());
	 * model.setViewName("Login/restPassword");
	 * logger.info(LoginController.class.getMethods()); return model; } else {
	 * model.addObject("content",
	 * "<h1>Seession Expried</h1><h3>Try Again To Reset Your Password. Next Time Don't Refresh the Page.</h3> "
	 * ); model.addObject("link", "ForgotPassword"); model.addObject("clickContent",
	 * "Click Here To Rest Your password");
	 * model.setViewName("Login/multiWrokingPage");
	 * logger.info(LoginController.class.getMethods()); return model; } }
	 * 
	 * @RequestMapping(value = "/Updatepassword", method = RequestMethod.POST)
	 * public @ResponseBody String updatePasswordSetup(@RequestParam(value =
	 * "UserEmail", required = true) String userEmail,
	 * 
	 * @RequestParam(value = "newPassword", required = true) String password) {
	 * userEmail = userEmail.toLowerCase();
	 * logger.info(LoginController.class.getMethods()); return
	 * loginServiceInterFace.updatePasswordSetup(userEmail, password); }
	 */
}
