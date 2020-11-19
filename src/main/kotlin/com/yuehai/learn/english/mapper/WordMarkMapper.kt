package com.yuehai.learn.english.mapper

import com.yuehai.learn.english.entity.WordMarkEntity
import org.apache.ibatis.annotations.Param

interface WordMarkMapper {
    fun selectWordMarks(@Param("userPhone") userPhone: String): List<WordMarkEntity>
    fun selectWordMark(@Param("userPhone") userPhone: String, @Param("wordId") wordId: Long): WordMarkEntity?
    fun insertWordMark(@Param("userPhone") userPhone: String, @Param("wordId") wordId: Long,  @Param("markCount") markCount: Int): Int
    fun updateWordMark(@Param("id") id: Long, @Param("markCount") markCount: Int): Int
    fun deleteWordMarkByWordId(id: Long)
    fun deleteWordMarkByWordIds(id: LongArray)
}