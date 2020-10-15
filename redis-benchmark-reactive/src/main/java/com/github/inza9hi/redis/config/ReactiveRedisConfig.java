//package com.github.inza9hi.redis.config;
//
//import java.net.UnknownHostException;
//import java.time.Duration;
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisPassword;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
//import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
//
//@Configuration
//public class ReactiveRedisConfig {
//
//
//  @Autowired
//  DefaultRedisConfig redisConfig;
//
//  @Bean
//  public GenericObjectPoolConfig defaultPoolConfig() {
//    GenericObjectPoolConfig config = new GenericObjectPoolConfig();
//    config.setMaxTotal(redisConfig.getMaxActive());
//    config.setMaxIdle(redisConfig.getMaxIdle());
//    config.setMinIdle(redisConfig.getMinIdle());
//    config.setMaxWaitMillis(redisConfig.getMaxWait());
//
//    return config;
//  }
//
//  @Bean
//  public RedisStandaloneConfiguration defaultRedisStandaloneConfig() {
//    RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//    config.setHostName(redisConfig.getHost());
//    config.setPassword(RedisPassword.of(redisConfig.getPassword()));
//    config.setPort(redisConfig.getPort());
//    config.setDatabase(redisConfig.getDatabase());
//    return config;
//  }
//
//
//  @Bean
//  public ReactiveRedisConnectionFactory defaultLettuceConnectionFactory(
//      RedisStandaloneConfiguration defaultRedisConfig,
//      GenericObjectPoolConfig defaultPoolConfig) {
//    LettuceClientConfiguration clientConfig =
//        LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofMillis(redisConfig.getTimeout()))
//            .poolConfig(defaultPoolConfig).build();
//    return new LettuceConnectionFactory(defaultRedisConfig, clientConfig);
//  }
//
//  @Bean
//  public ReactiveStringRedisTemplate reactiveStringRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) throws UnknownHostException {
//    ReactiveStringRedisTemplate template = new ReactiveStringRedisTemplate(reactiveRedisConnectionFactory);
//    return template;
//  }
//
////  @Bean
////  public ObjectMapper objectMapper(){
////    ObjectMapper objectMapper = new ObjectMapper();
////    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////    return objectMapper;
////  }
//
////  @Bean
////  public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(
////      ReactiveRedisConnectionFactory factory) {
//////    Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(
//////        Object.class);
//////    serializer.setObjectMapper(objectMapper());
//////    RedisSerializationContext.RedisSerializationContextBuilder<String, Object> builder = RedisSerializationContext
//////        .newSerializationContext(new StringRedisSerializer());
//////    RedisSerializationContext<String, Object> context = builder.value(serializer)
//////        .build();
////    return new ReactiveRedisTemplate<>(factory, RedisSerializationContext.string());
////  }
//
//}
