package com.yuehai.learn.english.entity

import java.sql.Timestamp

data class WordLearnEntity(val id: Long, val userPhone: String, val createTime: Timestamp, val count: Int)