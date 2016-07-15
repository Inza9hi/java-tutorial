package com.github.inza9hi.tutorial.spring.data.redis;

/**
 * Created by lawulu on 15-12-23.
 */

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicLong;

//@Component
public class RedisPublisher {

  //  @Autowired


    private final RedisTemplate< String, Object > template;
    private final ChannelTopic topic;

    public RedisPublisher( final RedisTemplate< String, Object > template,
                               final ChannelTopic topic ) {
        this.template = template;
        this.topic = topic;
    }

    private final AtomicLong counter = new AtomicLong(0);

    @Scheduled(fixedDelay = 100)
    public void publish() {
        template.convertAndSend(topic.getTopic(), "Message " + counter.incrementAndGet() +
                ", " + Thread.currentThread().getName());
    }
}
