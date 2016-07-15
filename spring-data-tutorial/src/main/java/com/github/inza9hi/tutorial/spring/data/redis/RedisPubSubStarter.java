package com.github.inza9hi.tutorial.spring.data.redis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by lawulu on 15-12-23.
 */
public class RedisPubSubStarter {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext( AppConfig.class );
    }
}
