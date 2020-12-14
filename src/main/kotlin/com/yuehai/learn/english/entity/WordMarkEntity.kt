package com.yuehai.learn.english.entity

import java.sql.Timestamp

data class WordMarkEntity(val id: Long,
                          val userPhone: String,
                          val wordId: Long,
                          val wordCreateTime: Timestamp?,
                          val createTime: Timestamp?,
                          val updateTime: Timestamp?,
                          val markCount: Int,
                          val learnCount: Int,
                          val contentEN: String? = null,
                          val contentCN: String? = null)