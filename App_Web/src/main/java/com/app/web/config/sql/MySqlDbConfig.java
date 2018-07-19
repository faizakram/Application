package com.app.web.config.sql;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.app.util.constant.CommonConstants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class MySqlDbConfig {


    
	@Autowired
	private Environment propertyReader;
	
	
    private DataSource datasource;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(CommonConstants.MODEL_PACKAGE);
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	    
	    @Bean
	    public DataSource dataSource() {

	        if (datasource == null) {
	            HikariConfig config = new HikariConfig();

	            config.setJdbcUrl(propertyReader.getProperty(CommonConstants.JDBC_URL));
	            config.setUsername(propertyReader.getProperty(CommonConstants.JDBC_USERNAME));
	            config.setPassword(propertyReader.getProperty(CommonConstants.JDBC_CREDENTIAL));

	            /*Changes Related to Database Connection Issue*/
	            config.setIdleTimeout(CommonConstants.IDLE_TIME_OUT_MS);
	            config.setConnectionTimeout(CommonConstants.CONNECTION_TIME_OUT);
	            config.setValidationTimeout(CommonConstants.VALIDATION_TIME_OUT);
	            config.setMaxLifetime(CommonConstants.MAX_LIFE_TIME);
	            /*Database Connection Issue Done Here*/
	            
	            config.setMaximumPoolSize(CommonConstants.TEN);
	            config.setAutoCommit(false);
	            config.addDataSourceProperty(CommonConstants.CACHE_PREP_STMTS,
	            		propertyReader.getProperty(CommonConstants.HIBERNATE_CACHEPREPSTMTS));
	            config.addDataSourceProperty(CommonConstants.PREP_STMT_CACHE_SIZE,
	            		propertyReader.getProperty(CommonConstants.HIBERNATE_PREPSTMTCACHESIZE));
	            config.addDataSourceProperty(CommonConstants.PREP_STMT_CACHE_SQL_LIMIT,
	            		propertyReader.getProperty(CommonConstants.HIBERNATE_PREPSTMTCACHESQLLIMIT));
	            config.addDataSourceProperty(CommonConstants.USE_SERVER_PREP_STMTS,
	            		propertyReader.getProperty(CommonConstants.HIBERNATE_USESERVERPREPSTMTS));

	            datasource = new HikariDataSource(config);
	        }
	        return datasource;

	    }

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put(CommonConstants.HIBERNATE_DIALECT,
				propertyReader.getProperty(CommonConstants.HIBERNATE_DIALECT));
		properties.put(CommonConstants.HIBERNATE_SHOW_SQL,
				propertyReader.getProperty(CommonConstants.HIBERNATE_SHOW_SQL));
		properties.put(CommonConstants.HIBERNATE_FORMAT_SQL,
				propertyReader.getProperty(CommonConstants.HIBERNATE_FORMAT_SQL));
		properties.put(CommonConstants.HIBERNATE_HBM2DDL_AUTO,
				propertyReader.getProperty(CommonConstants.HIBERNATE_HBM2DDL_AUTO));
		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory session) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(session);
		return txManager;
	}
	
}
