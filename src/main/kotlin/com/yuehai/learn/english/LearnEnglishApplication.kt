package com.yuehai.learn.english

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@MapperScan("com.yuehai.learn.english.mapper")
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class LearnEnglishApplication

fun main(args: Array<String>) {
    runApplication<LearnEnglishApplication>(*args)
}