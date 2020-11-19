package com.yuehai.learn.english.mapper

import com.yuehai.learn.english.entity.WordEntity
import java.util.*

interface WordMapper {
    fun selectWordLimitDate(year: String, month: String): List<WordEntity>
    fun selectWord(): List<WordEntity>
    fun insertWord(entity: WordEntity): Int
    fun updateWord(entity: WordEntity): Int
    fun selectWordCount(): Int
    fun selectRandomWord(count: Int, startDate: Date, endDate: Date): List<WordEntity>
    fun selectWordById(id: Long): WordEntity?
    fun deleteWord(id: Long): Int
    fun deleteWords(id: LongArray): Int
    fun selectMarkWord(userId: Long, wordId: Long)
}