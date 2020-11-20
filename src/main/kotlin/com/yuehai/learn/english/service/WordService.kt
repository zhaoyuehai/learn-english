package com.yuehai.learn.english.service

import com.yuehai.learn.english.bean.ResultBean
import com.yuehai.learn.english.bean.WordBean
import com.yuehai.learn.english.bean.WordExtraBean
import com.yuehai.learn.english.bean.WordMarkBean

interface WordService {
    fun selectWord(date: String?, pageNum: Int, pageSize: Int): ResultBean
    fun insertWord(wordBean: WordBean): ResultBean
    fun updateWord(wordBean: WordBean): ResultBean
    fun deleteWord(id: Long): ResultBean

    /**
     *暂时没用到
     */
    fun randomWords(count: Int?, startDate: String?, endDate: String?): ResultBean
    fun randomWords(count: Int?, monthDate: String?): ResultBean
    fun markWord(userPhone: String, wordMarkBean: WordMarkBean): ResultBean
    fun deleteWords(ids: LongArray): ResultBean
    fun insertExtraWord(wordExtraBean: WordExtraBean): ResultBean
    fun selectExtraWords(wordId: Long): ResultBean
    fun selectWordMarks(userPhone: String, pageNum: Int, pageSize: Int): ResultBean
    fun selectWordLearnRecords(userPhone: String): ResultBean
}