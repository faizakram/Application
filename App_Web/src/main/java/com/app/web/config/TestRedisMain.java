package com.app.web.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.app.redis.service.NotificationEventUtil;

/**
 * main class to test redis,will be removed
 * 
 * @author 13403
 *
 */
public class TestRedisMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext obj = new AnnotationConfigApplicationContext(RedisConfig.class);
		obj.scan("com.app");
		NotificationEventUtil temp = obj.getBean(NotificationEventUtil.class);
		temp.sendNotification("Hey i am test notification2", 2l);
	}

}
