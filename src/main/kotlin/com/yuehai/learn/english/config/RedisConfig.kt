//package com.yuehai.learn.english.config
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
//import org.springframework.data.redis.core.RedisTemplate
//import org.springframework.data.redis.core.StringRedisTemplate
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
//import org.springframework.data.redis.serializer.StringRedisSerializer
//import java.io.Serializable
//
//@Configuration
//class RedisConfig {
//    @Bean
//    fun redisTemplate(connectionFactory: LettuceConnectionFactory) = RedisTemplate<String, Serializable>().apply {
//        keySerializer = StringRedisSerializer()
//        valueSerializer = GenericJackson2JsonRedisSerializer()
//        setConnectionFactory(connectionFactory)
//    }
//
//    @Bean
//    fun stringRedisTemplate(connectionFactory: LettuceConnectionFactory) = StringRedisTemplate().apply {
//        setConnectionFactory(connectionFactory)
//    }
//}