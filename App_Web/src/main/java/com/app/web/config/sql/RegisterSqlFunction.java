package com.app.web.config.sql;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;
/**
 * 
 * Register Sql Function
 *
 */
public class RegisterSqlFunction extends MySQLDialect {

	public RegisterSqlFunction() {
		super();
		registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
	}
}
