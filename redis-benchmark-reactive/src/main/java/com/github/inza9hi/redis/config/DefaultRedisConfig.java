package com.github.inza9hi.redis.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.redis")
@Data
@Component
public  class DefaultRedisConfig {

  private String host;
  private Integer port;
  private String password;
  private Integer database;
  private Integer timeout;

  @Value("${spring.redis.lettuce.pool.max-active:8}")
  private Integer maxActive;
  @Value("${spring.redis.lettuce.pool.max-idle:8}")
  private Integer maxIdle;
  @Value("${spring.redis.lettuce.pool.max-wait:-1}")
  private Long maxWait;
  @Value("${spring.redis.lettuce.pool.min-idle:0}")
  private Integer minIdle;



}

//作者：一灰灰
//    链接：https://juejin.im/post/5bd7a995e51d457abd74306a
//    来源：掘金
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。