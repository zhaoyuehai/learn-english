package com.yuehai.learn.english.entity

import java.sql.Timestamp

data class WordMarkEntity(val id: Long, val userPhone: String, val wordId: Long, val createTime: Timestamp?, val updateTime: Timestamp?, val markCount: Int)