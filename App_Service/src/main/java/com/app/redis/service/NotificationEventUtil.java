package com.app.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

/**
 * 
 * Notification util class
 *
 */

@Component
public class NotificationEventUtil {
	
	
	@Autowired
    private  RedisTemplate< String, Object > template;
	
	
	/**
	 * utility method to send runtime notifications through redis
	 * 
	 * @param notification
	 * @param userId
	 */
	public void sendNotification(String notification, Long... userId) {
		 for (Long id : userId) {
			 ChannelTopic config=new ChannelTopic( String.valueOf(id));
			 template.convertAndSend( config.getTopic(),notification);
		 }
		
	}
	
}