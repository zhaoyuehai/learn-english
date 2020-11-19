package com.yuehai.learn.english.mapper

import com.yuehai.learn.english.entity.WordExtraEntity
import org.apache.ibatis.annotations.Param

interface WordExtraMapper {
    fun selectWordExtra(@Param("wordId") wordId: Long): List<WordExtraEntity>
    fun insertWordExtra(entity: WordExtraEntity): Int
}