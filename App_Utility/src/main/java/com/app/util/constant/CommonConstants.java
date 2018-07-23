package com.app.util.constant;

public class CommonConstants {
	public static final String BASE_PACKAGE = "com.app";
	public static final String DISPATCHER = "dispatcher";
	public static final String SLAH = "/";
	public static final Integer ONE = 1;
	
	public static final String APPLICATION_PROPERTY_READER = "ApplicationProperty";
	public static final String APPLICATION_PROPERTIES_FILENAME = "classpath:application.properties";
    
	/*============================== Hibernate Configuration =========================*/
	public static final String MODEL_PACKAGE = "com.app.model";
	public static final String QUERY_PROPERTIES = "classpath:query.properties";
	public static final String JDBC_URL = "jdbc.url";
	public static final String JDBC_USERNAME = "jdbc.username";
	public static final String JDBC_CREDENTIAL = "jdbc.pass";
	public static final String HIBERNATE_DIALECT = "hibernate.dialect";
	public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	public static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	public static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	/*============================== Hikari Connection Pulling Configuration =========================*/
	public static final Integer IDLE_TIME_OUT_MS = 60000;
	public static final Integer CONNECTION_TIME_OUT = 60000;
	public static final Integer VALIDATION_TIME_OUT = 30000;
	public static final Integer MAX_LIFE_TIME = 60000;
	public static final Integer MAXIMUM_POOL_SIZE = 10;
	public static final String CACHE_PREP_STMTS = "cachePrepStmts";
	public static final String PREP_STMT_CACHE_SIZE = "prepStmtCacheSize";
	public static final String PREP_STMT_CACHE_SQL_LIMIT = "prepStmtCacheSqlLimit";
	public static final String USE_SERVER_PREP_STMTS = "useServerPrepStmts";
	public static final String HIBERNATE_CACHEPREPSTMTS = "hibernate.hikari.dataSource.cachePrepStmts";
	public static final String HIBERNATE_PREPSTMTCACHESIZE = "hibernate.hikari.dataSource.prepStmtCacheSize";
	public static final String HIBERNATE_PREPSTMTCACHESQLLIMIT = "hibernate.hikari.dataSource.prepStmtCacheSqlLimit";
	public static final String HIBERNATE_USESERVERPREPSTMTS = "hibernate.hikari.dataSource.useServerPrepStmts";

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
	public static final String ERROR_PROPERTIES = "classpath:error.properties";
	// Success Property File
	public static final String SUCCESS_PROPERTY = "sucessproperty";
	public static final String SUCCESS_PROPERTIES = "classpath:success.properties";

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
    
    public static final String USER_EMAIL_TXT = "userEmail";
	public static final String USER_URL= "/web/token/v3/";
	public static final String TOKEN_CREATION = "creation";
	public static final String USER_DETAILS = "userInfo/{id}";
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
	public static final String DOWNLOAD_FILE = "/download/";
	public static final String CUSTOMTEST = "customtest";
	public static final String CUSTOM = "/custom/test/";
	public static final String MY_TIME_ZONE = "Asia/Kolkata";
	
	
	

	
	
	public static final String CLASSPATH_MESSAGES_PROPERTIES = "classpath:messages";
	
	/* Changes Related to Database Connection Issue */
	
	/* Database Connection Issue Done Here */
	
	public static final String DAYS = "DAYS";
	public static final String MONTHS = "MONTHS";
	public static final String YEARS = "YEARS";
	public static final String SIMPLE_DATE_FORMAT_VALUE = "yyyy-MM-dd";
	public static final String UTC_TIME_ZONE = "UTC";
	public static final String SIMPLE_DATE_TIME_FORMAT_VALUE = "yyyy-MM-dd HH:mm:ss";
	
	public static final int DEFAULT_PAGE_SIZE = 50;
	public static final int MAX_ALLOWED_PAGE_SIZE = 100;
	public static final int DEFAULT_PAGE_NO = 1;
	public static final String SECRET_KEY = "com.aes.bouncy.secret.key";
	public static final String PROTO_TYPE = "prototype";
	public static final String ID = "id";
	
	
}
