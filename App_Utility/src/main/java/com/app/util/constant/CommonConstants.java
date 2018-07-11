package com.app.util.constant;

public class CommonConstants {
	public static final String BASE_PACKAGE = "com.app";
	public static final String DISPATCHER = "dispatcher";
	public static final String SLAH = "/";
	public static final Integer ONE = 1;
	public static final String QUERY_PROPERTY_READER = "ApplicationProperty";
	public static final String APPLICATION_PROPERTIES_FILENAME = "application.properties";
	public static final String MONGODB_USERNAME = "mongo.username";
	public static final String MONGODB_DB_NAME = "mongo.dbname";
	public static final String MONGODB_AUTHENTICATION = "mongo.password";
	public static final String MONGODB_HOST = "mongo.host";
	public static final String MONGODB_PORT = "mongo.port";
	public static final String MONGODB_CONNECTION_PER_HOST = "mongo.connections-per-host";
	public static final String MONGODB_THREAD_ALLOW = "mongo.threads-allowed-to-block-for-connection-multiplier";
	public static final String MONGODB_CONNECTION_TIMEOUT = "mongo.connect-timeout";
	public static final String MONGODB_SOCKET_TIMEOUT = "mongo.socket-timeout";
	public static final String MONGODB_MAX_WAIT_TIME = "mongo.max-wait-time";
	public static final String JOB_EXPRESSION = "quartz.time.expression";
	public static final String MONGODB_URI = "mongo.mongoAddress";

	public static final String START = "/web/v1/";
	public static final String USER_DETAILS = "userInfo/{id}";
	public static final String CAR_DETAILS = "carInfo/{para1}/{para2}";

	public static final String MODEL_PACKAGE = "com.app.model";
	public static final String JDBC_DRIVER_CLASS_NAME = "jdbc.driverClassName";
	public static final String JDBC_URL = "jdbc.url";
	public static final String JDBC_USERNAME = "jdbc.username";
	public static final String JDBC_CREDENTIAL = "jdbc.password";
	public static final String HIBERNATE_DIALECT = "hibernate.dialect";
	public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	public static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	public static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	public static final String CONN_POOL_MIN_SIZE = "hibernate.conn.pool.min.size";
	public static final String CONN_POOL_MAX_SIZE = "hibernate.conn.pool.max.size";
	public static final String CONN_POOL_IDLE_PERIOD = "hibernate.conn.pool.idle.period";
	public static final String CONN_POOL_INITIAL_SIZE = "hibernate.conn.pool.initial.size";
	public static final String CONN_POOL_ACQ_INC = "hibernate.conn.pool.acq.inc";
	public static final String PREFERRED_TEST_QUERY = "hibernate.preferred.test.query";

	public static final String Id = "id";
	public static final String CAR_COLLECTIONS = "car";
	public static final String ID = "_id";
	public static final String DISPATCHOPTIONSREQUEST = "dispatchOptionsRequest";
	public static final String TRUETXT = "true";
	public static final String X_AUTH_TOKEN = "X-Auth-Token";
	public static final String REQUEST_VALIDATE = "Request validation Token";
	public static final String HEADER_TXT = "header";
	public static final String SWAGGER_STATUS = "swagger.enable";
	public static final String STRING_TXT = "String";

	public static final String SCOPE_REQUEST_TXT = "request";
	

	// Error Property File
	public static final String ERROR_PROPERTY = "errorproperty";
	public static final String ERROR_PROPERTIES = "error.properties";
	// Success Property File
	public static final String SUCCESS_PROPERTY = "sucessproperty";
	public static final String SUCCESS_PROPERTIES = "success.properties";

	// Success Code Information
	public static final String S0001_SUCCESS_CODE = "SM-HTTP_CODE-S0001";
	public static final String S0001_SUCCESS_DESCRIPTION = "SM-HTTP_DESCRIPTION-S0001";
	
	public static final String ERROR_CODE_HELPER = "errorcodehelper";
	public static final String E1000_ERROR_CODE = "CT-E1000-ERROR-CODE";
	public static final String E1000_ERROR_DESCRIPTION = "CT-E1000-ERROR-DESCRIPTION";
	public static final String E1001_ERROR_CODE = "CT-E1001-ERROR-CODE";;
	public static final String E1001_ERROR_DESCRIPTION = "CT-E1001-ERROR-DESCRIPTION";
	public static final String E1002_ERROR_CODE = "CT-E1002-ERROR-CODE";
	public static final String E1002_ERROR_DESCRIPTION = "CT-E1002-ERROR-DESCRIPTION";
	public static final String E1003_ERROR_CODE = "CT-E1003-ERROR-CODE";
	public static final String E1003_ERROR_DESCRIPTION = "CT-E1003-ERROR-DESCRIPTION";
    public static final String E1004_ERROR_CODE = "CT-E1004-ERROR-CODE";
    public static final String E1004_ERROR_DESCRIPTION = "CT-E1004-ERROR-DESCRIPTION";
    public static final String E1005_ERROR_CODE = "CT-E1005-ERROR-CODE";
    public static final String E1005_ERROR_DESCRIPTION = "CT-E1005-ERROR-DESCRIPTION";
    public static final String E1006_ERROR_CODE = "CT-E1006-ERROR-CODE";
    public static final String E1006_ERROR_DESCRIPTION = "CT-E1006-ERROR-DESCRIPTION";
    public static final String E1007_ERROR_CODE = "CT-E1007-ERROR-CODE";
    public static final String E1007_ERROR_DESCRIPTION = "CT-E1007-ERROR-DESCRIPTION";
    public static final String E1008_ERROR_CODE = "CT-E1008-ERROR-CODE";
    public static final String E1008_ERROR_DESCRIPTION = "CT-E1008-ERROR-DESCRIPTION";
    public static final String E1009_ERROR_CODE = "CT-E1009-ERROR-CODE";
    public static final String E1009_ERROR_DESCRIPTION = "CT-E1009-ERROR-DESCRIPTION";
    public static final String E1010_ERROR_CODE = "CT-E1010-ERROR-CODE";
    public static final String E1010_ERROR_DESCRIPTION = "CT-E1010-ERROR-DESCRIPTION";
    
    public static final String COURSE_ADD_DESCRIPTION = "Course added successfully";

	public static final String STUDENT_URL = "/web/Student/v3/";
	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String ADD_STUDENT_DESCRIPTION = "Student Added Successfully";
	public static final String UPDATE_STUDENT_DESCRIPTION = "Student Updated Successfully";
	public static final String ADD_PAYMENT = "addpayment";
	public static final String UPDATE_PAYMENT = "updatepayment";
	public static final String STUDENT_PAYMENT_ADDED_DESCRIPTION = "Payment Added Successfully";
	public static final String STUDENT_PAYMENT_UPDATED_DESCRIPTION = "Payment Updated Successfully";
	public static final String GET_PAYMENT_DETAILS = "getpaymentdetails/{param1}";
	public static final String GET_DETAIL = "getdetail/{param1}";
	public static final String GET_PAYMENT_DETAIL = "getpaymentdetail/{param1}";
	public static final String PARAM1 = "param1";
	public static final String PARAM2 = "param2";
	public static final String DELETE_PAYMENT_DETAIL = "deletepaymentdetail/{param1}";
	public static final String GET_CERTIFICATE = "getcertificate/{registrationNo}";
	public static final String UPDATE_ON_CERTIFICATE = "updateoncertificate";
	public static final String ADD_ON_CERTIFICATE = "addoncertificate";
	public static final String ADD_OFF_CERTIFICATE = "addoffcertificate";
	public static final String UPDATE_OFF_CERTIFICATE = "updateoffcertificate";
	public static final String ADD_RESULT = "addresult";
	public static final String UPDATE_RESULT = "updateResult";
	public static final String DELETE_CERTIFICATE = "deletecertificate/{param1}";
	public static final String GET_RESULT = "getResult/{param1}";
	public static final String PARTNER_URL = "/web/partner/v3/";
	public static final String ADD_UPDATE = "addorupdatepartner";
	public static final String COMMON_URL = "/web/common/v3/";
	public static final String LOGIN_AUTHORIZATION_STATUS = "loginauthstatus";
	public static final String DELETE = "delete/{param1}";
	
	public static final String INSTRUCTOR_URL = "/web/instructor/v3/";
	public static final String COURSE_URL = "/web/course/v3/";
	public static final String USER_AGENT = "User-Agent";
	public static final String GET_BROADCAST = "/broadcast/{param1}";
	public static final CharSequence EDGE_TXT = "Edge/";
	public static final String GET_COURSE_NAME = "getcoursename";
	public static final String GET_PARTNER_NAME = "getpartnername";
	public static final String GET_INSTRUCTOR_NAME = "getinstructorname";
	
	public static final String NAME = "name";
	public static final Integer NAME_LENGTH = 4;
	public static final String USER_EMAIL_TXT = "userEmail";
	public static final String USER_CREDENTIAL_TXT = "userCredential";
	public static final String EMAIL_REGEX = "^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$";
	public static final String MOBILE_REGEX = "^\\+[0-9]{2,3}+-[0-9]{10}$";
	public static final String USER_URL= "/web/token/v3/";
	public static final String TOKEN_CREATION = "creation";
	
    public static final String HEADER_TOKEN = "X-Auth-Token";
    public static final String ROLES = "ROLES";
    public static final String USER_ID = "userId";
    public static final String TOKEN = "token";
	public static final String DOUBLE_QUOTES = "";

	public static final String ROLE_PARTNER = "ROLE_PARTNER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_INSTRUCTOR = "ROLE_INSTRUCTOR";
	public static final String ROLE_STUDENT = "ROLE_STUDENT";
	public static final String ALLOW_ROLE_ALL = "ROLE_ALL";
	public static final String PROFILE_PIC_SIZE = "cybotech.profile.pic.size";
	public static final String DOWNLOAD_FILE = "/download/";
	
}
