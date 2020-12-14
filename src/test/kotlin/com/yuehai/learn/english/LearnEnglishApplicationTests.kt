package com.yuehai.learn.english

import com.yuehai.learn.english.mapper.WordMapper
import com.yuehai.learn.english.mapper.WordMarkMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.text.SimpleDateFormat

@SpringBootTest
class LearnEnglishApplicationTests {

    @Autowired
    private lateinit var wordMapper: WordMapper

    @Autowired
    private lateinit var wordMarkMapper: WordMarkMapper

    @Test
    fun contextLoads() {
//        wordMapper.selectWord().forEach {
//            println("------$it")
//        }

//        val res = wordMapper.deleteWords(longArrayOf(737, 738, 739, 733, 734, 735, 736))
//        println("------$res")
//        val basePath =  ApplicationHome().dir.path
//        println("------$basePath")
//        wordMarkMapper.selectWordMarks("18511073583").forEach { word ->
//            if (word.wordCreateTime == null) {
//                val wordEntity = wordMapper.selectWordById(word.wordId)
//                wordEntity?.createTime?.let {
//                    val res = wordMarkMapper.updateWordCreateTime(word.id, SimpleDateFormat("yyyy-MM-dd").parse(it))
//                    print("${word.wordId}------$res")
//                }
//            }
//        }
    }

}
