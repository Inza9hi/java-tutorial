package com.github.inza9hi.tutorial.spring.data.redis;

import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * Created by lawulu on 15-12-23.
 */
public class RedisMessageListener implements MessageListener {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RedisMessageListener.class);

    @Override
    public void onMessage(Message message, byte[] bytes) {
        LOG.info( "Message received: " + message.toString() );
    }
}
