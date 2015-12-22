package com.github.inza9hi.tutorial.spring.data.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lawulu on 15-12-22.
 */
public class RedisExampleTest extends AbstractApplicationContext{

    @Autowired
    RedisExample redisExample;

    @Test
    public void testList(){
        redisExample.addLink("userId","baidu.com");

    }

}
