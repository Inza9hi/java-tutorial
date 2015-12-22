package com.github.inza9hi.tutorial.spring.data.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by lawulu on 15-12-21.
 */
@Component
public class RedisExample {
    @Autowired
    private RedisTemplate<String, String> template; // inject the template as ListOperations

    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    public void addLink(String userId, String url) {
        listOps.leftPush(userId, url);
    }
    

}
