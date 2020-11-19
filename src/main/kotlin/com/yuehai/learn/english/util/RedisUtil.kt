//package com.yuehai.learn.english.unti
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.data.redis.core.StringRedisTemplate
//import org.springframework.stereotype.Component
//import java.util.concurrent.TimeUnit
//
//@Component
//class RedisUtil {
//
//    @Autowired
//    private lateinit var redisTemplate: StringRedisTemplate
//
//    fun haskey(key: String): Boolean {
//        return try {
//            redisTemplate.hasKey(key)
//        } catch (e: Exception) {
//            e.printStackTrace()
//            false
//        }
//    }
//
//    fun get(key: String): String? = redisTemplate.opsForValue()[key]
//
//    /**
//     * 109
//     * 普通缓存放入并设置时间
//     * 110
//     *
//     * @param key   键
//     * 111
//     * @param value 值
//     * 112
//     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
//     * 113
//     * @return true成功 false 失败
//     * 114
//     */
//    fun set(key: String, value: String, time: Long): Boolean {
//        return try {
//            if (time > 0) {
//                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS)
//            } else {
//                set(key, value)
//            }
//            true
//        } catch (e: Exception) {
//            e.printStackTrace()
//            false
//        }
//    }
//
//
//    /**
//     * 92
//     * 普通缓存放入
//     * 93
//     *
//     * @param key   键
//     * 94
//     * @param value 值
//     * 95
//     * @return true成功 false失败
//     * 96
//     */
//    operator fun set(key: String, value: String): Boolean {
//        return try {
//            redisTemplate.opsForValue().set(key, value)
//            true
//        } catch (e: Exception) {
//            e.printStackTrace()
//            false
//        }
//    }
//
//    fun delete(key: String): Boolean = redisTemplate.delete(key)
//}