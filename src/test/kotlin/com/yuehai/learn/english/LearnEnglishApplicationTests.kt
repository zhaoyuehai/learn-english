package com.yuehai.learn.english

import com.yuehai.learn.english.mapper.WordMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.system.ApplicationHome
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LearnEnglishApplicationTests {

    @Autowired
    private lateinit var wordMapper: WordMapper

    @Test
    fun contextLoads() {
//        wordMapper.selectWord().forEach {
//            println("------$it")
//        }

//        val res = wordMapper.deleteWords(longArrayOf(737, 738, 739, 733, 734, 735, 736))
//        println("------$res")
        val basePath =  ApplicationHome().dir.path
        println("------$basePath")
    }

}
