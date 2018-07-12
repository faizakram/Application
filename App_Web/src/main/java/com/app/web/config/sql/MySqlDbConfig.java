package com.app.web.config.sql;

import java.beans.PropertyVetoException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.app.util.constant.CommonConstants;
import com.app.util.reader.PropertyReader;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
public class MySqlDbConfig {

	@Autowired
	@Qualifier(CommonConstants.APPLICATION_PROPERTY_READER)
	private PropertyReader propertyReader;

	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(CommonConstants.MODEL_PACKAGE);
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public ComboPooledDataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(propertyReader.getProperty(CommonConstants.JDBC_DRIVER_CLASS_NAME));
		} catch (PropertyVetoException e) {
		}
		dataSource.setJdbcUrl(propertyReader.getProperty(CommonConstants.JDBC_URL));
		dataSource.setUser(propertyReader.getProperty(CommonConstants.JDBC_USERNAME));
		dataSource.setPassword(propertyReader.getProperty(CommonConstants.JDBC_CREDENTIAL));
		dataSource.setInitialPoolSize(
				Integer.parseInt(propertyReader.getProperty(CommonConstants.CONN_POOL_INITIAL_SIZE)));
		dataSource.setAcquireIncrement(Integer.parseInt(propertyReader.getProperty(CommonConstants.CONN_POOL_ACQ_INC)));
		dataSource.setMinPoolSize(Integer.parseInt(propertyReader.getProperty(CommonConstants.CONN_POOL_MIN_SIZE)));
		dataSource.setMaxPoolSize(Integer.parseInt(propertyReader.getProperty(CommonConstants.CONN_POOL_MAX_SIZE)));
		dataSource.setMaxIdleTime(Integer.parseInt(propertyReader.getProperty(CommonConstants.CONN_POOL_IDLE_PERIOD)));
		dataSource.setPreferredTestQuery(propertyReader.getProperty(CommonConstants.PREFERRED_TEST_QUERY));
		return dataSource;
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
