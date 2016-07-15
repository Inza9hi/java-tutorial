package com.github.inza9hi.tutorial.spring.data.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by lawulu on 15-12-21.
 */
@Component
public class RedisExample {

    @Autowired
    private StringRedisTemplate  template; // inject the template as ListOperations

    //http://www.cnblogs.com/chanedi/p/4135303.html
    @Resource(name="stringRedisTemplate")
    private ListOperations<String, String> listOps;



    public void addLink(String userId, String url) {
        //template.opsForList().leftPush(usrId,url);
        listOps.leftPush(userId, url);
    }

    public void pub(){
        template.convertAndSend("room","from the client2");
    }
    

}
